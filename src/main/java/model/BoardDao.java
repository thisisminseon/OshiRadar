package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BoardDao {

    private DataSource ds;

    public BoardDao() {
        try {
            Context ctx = new InitialContext();
            ds = (DataSource) ctx.lookup("java:comp/env/jdbc/oracle");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* ===============================
       Board List
    =============================== */
    public List<BoardDto> getList(int page, String type, String keyword) {

        List<BoardDto> list = new ArrayList<>();
        int pageSize = 10;
        int start = (page - 1) * pageSize + 1;
        int end = page * pageSize;

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM ( ")
           .append("SELECT ROWNUM rn, a.* FROM ( ")
           .append("SELECT bp.post_id, bp.title, bp.view_count, bp.created_at, u.nickname ")
           .append("FROM board_posts bp ")
           .append("JOIN users u ON bp.user_id = u.user_id ")
           .append("WHERE bp.status = 'Y' ");

        if (keyword != null && !keyword.trim().isEmpty()) {
            if ("content".equals(type)) {
                sql.append("AND bp.content LIKE ? ");
            } else {
                sql.append("AND bp.title LIKE ? ");
            }
        }

        sql.append("ORDER BY bp.post_id DESC ")
           .append(") a ")
           .append(") WHERE rn BETWEEN ? AND ?");

        try (
            Connection conn = ds.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql.toString())
        ) {
            int idx = 1;

            if (keyword != null && !keyword.trim().isEmpty()) {
                ps.setString(idx++, "%" + keyword + "%");
            }

            ps.setInt(idx++, start);
            ps.setInt(idx, end);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                BoardDto dto = new BoardDto();
                dto.setPostId(rs.getInt("post_id"));
                dto.setTitle(rs.getString("title"));
                dto.setViewCount(rs.getInt("view_count"));
                dto.setCreatedAt(rs.getDate("created_at"));
                dto.setNickname(rs.getString("nickname"));
                list.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public int getTotalPage(String type, String keyword) {

        int total = 0;
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT COUNT(*) FROM board_posts bp ")
           .append("WHERE bp.status = 'Y' ");

        if (keyword != null && !keyword.trim().isEmpty()) {
            if ("content".equals(type)) {
                sql.append("AND bp.content LIKE ? ");
            } else {
                sql.append("AND bp.title LIKE ? ");
            }
        }

        try (
            Connection conn = ds.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql.toString())
        ) {
            if (keyword != null && !keyword.trim().isEmpty()) {
                ps.setString(1, "%" + keyword + "%");
            }

            ResultSet rs = ps.executeQuery();
            if (rs.next()) total = rs.getInt(1);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return (int) Math.ceil(total / 10.0);
    }

    /* ===============================
       Board Detail
    =============================== */
    public BoardDto getPost(int postId) {

        BoardDto dto = null;
        String sql =
            "SELECT bp.*, u.nickname " +
            "FROM board_posts bp " +
            "JOIN users u ON bp.user_id = u.user_id " +
            "WHERE bp.post_id = ? AND bp.status = 'Y'";

        try (
            Connection conn = ds.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1, postId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                dto = new BoardDto();
                dto.setPostId(rs.getInt("post_id"));
                dto.setUserId(rs.getInt("user_id"));
                dto.setTitle(rs.getString("title"));
                dto.setContent(rs.getString("content"));
                dto.setImagePath(rs.getString("image_path"));
                dto.setViewCount(rs.getInt("view_count"));
                dto.setStatus(rs.getString("status"));
                dto.setCreatedAt(rs.getDate("created_at"));
                dto.setUpdatedAt(rs.getDate("updated_at"));
                dto.setNickname(rs.getString("nickname"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dto;
    }

    public void increaseViewCount(int postId) {
        String sql =
            "UPDATE board_posts SET view_count = view_count + 1 WHERE post_id = ?";

        try (
            Connection conn = ds.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1, postId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* ===============================
       Insert / Update / Delete
    =============================== */
    public void insert(BoardDto dto) {

        String sql =
            "INSERT INTO board_posts " +
            "(post_id, user_id, title, content, image_path) " +
            "VALUES (seq_board_posts.NEXTVAL, ?, ?, ?, ?)";

        try (
            Connection conn = ds.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1, dto.getUserId());
            ps.setString(2, dto.getTitle());
            ps.setString(3, dto.getContent());
            ps.setString(4, dto.getImagePath());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(BoardDto dto) {

        String sql =
            "UPDATE board_posts " +
            "SET title = ?, content = ?, image_path = ?, updated_at = SYSDATE " +
            "WHERE post_id = ?";

        try (
            Connection conn = ds.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setString(1, dto.getTitle());
            ps.setString(2, dto.getContent());
            ps.setString(3, dto.getImagePath());
            ps.setInt(4, dto.getPostId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(int postId) {

        String sql =
            "UPDATE board_posts SET status = 'N' WHERE post_id = ?";

        try (
            Connection conn = ds.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1, postId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* ===============================
       Comment
    =============================== */
    public List<BoardCommentDto> getCommentList(int postId) {

        List<BoardCommentDto> list = new ArrayList<>();

        String sql =
            "SELECT bc.comment_id, bc.user_id, bc.content, bc.image_path, bc.created_at, u.nickname " +
            "FROM board_comments bc " +
            "JOIN users u ON bc.user_id = u.user_id " +
            "WHERE bc.post_id = ? AND bc.status = 'Y' " +
            "ORDER BY bc.comment_id ASC";

        try (
            Connection conn = ds.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1, postId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                BoardCommentDto dto = new BoardCommentDto();
                dto.setCommentId(rs.getInt("comment_id"));
                dto.setUserId(rs.getInt("user_id"));
                dto.setContent(rs.getString("content"));
                dto.setImagePath(rs.getString("image_path"));
                dto.setCreatedAt(rs.getDate("created_at"));
                dto.setNickname(rs.getString("nickname"));
                list.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public void insertComment(int postId, int userId, String content, String imagePath) {

        String sql =
            "INSERT INTO board_comments " +
            "(comment_id, post_id, user_id, content, image_path) " +
            "VALUES (seq_board_comments.NEXTVAL, ?, ?, ?, ?)";

        try (
            Connection conn = ds.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1, postId);
            ps.setInt(2, userId);
            ps.setString(3, content);
            ps.setString(4, imagePath);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteComment(int commentId) {

        String sql =
            "UPDATE board_comments SET status = 'N' WHERE comment_id = ?";

        try (
            Connection conn = ds.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1, commentId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}