package model;

import java.sql.*;
import java.util.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class FurimaDao {

    private DataSource ds;

    public FurimaDao() {
        try {
            Context ctx = new InitialContext();
            ds = (DataSource) ctx.lookup("java:comp/env/jdbc/oracle");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // list (Paging)
    public List<FurimaDto> getList(int start, int end) {
        List<FurimaDto> list = new ArrayList<>();

        String sql =
            "SELECT * FROM ( " +
            "  SELECT ROWNUM rnum, A.* FROM ( " +
            "    SELECT * FROM furima_posts " +
            "    WHERE status='Y' ORDER BY post_id DESC " +
            "  ) A " +
            ") WHERE rnum BETWEEN ? AND ?";

        try (
            Connection conn = ds.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1, start);
            ps.setInt(2, end);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                FurimaDto dto = new FurimaDto();
                dto.setPostId(rs.getInt("post_id"));
                dto.setUserId(rs.getInt("user_id"));
                dto.setTitle(rs.getString("title"));
                dto.setDescription(rs.getString("description"));
                dto.setPrice(rs.getInt("price"));
                dto.setImagePath(rs.getString("image_path"));
                dto.setStatus(rs.getString("status"));
                dto.setCreatedAt(rs.getDate("created_at"));
                list.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // Total Counts
    public int getTotalCount() {
        int count = 0;
        String sql = "SELECT COUNT(*) FROM furima_posts WHERE status='Y'";

        try (
            Connection conn = ds.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
        ) {
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return count;
    }

    // Single record
    public FurimaDto getPost(int postId) {
        FurimaDto dto = null;

        String sql =
            "SELECT * FROM furima_posts " +
            "WHERE post_id=? AND status='Y'";

        try (
            Connection conn = ds.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1, postId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                dto = new FurimaDto();
                dto.setPostId(rs.getInt("post_id"));
                dto.setUserId(rs.getInt("user_id"));
                dto.setTitle(rs.getString("title"));
                dto.setDescription(rs.getString("description"));
                dto.setPrice(rs.getInt("price"));
                dto.setImagePath(rs.getString("image_path"));
                dto.setStatus(rs.getString("status"));
                dto.setCreatedAt(rs.getDate("created_at"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dto;
    }

    // furima Post Registration
    public void insert(FurimaDto dto) {
        String sql =
            "INSERT INTO furima_posts " +
            "(post_id, user_id, title, description, price, image_path) " +
            "VALUES (seq_furima_posts.NEXTVAL, ?, ?, ?, ?, ?)";

        try (
            Connection conn = ds.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1, dto.getUserId());
            ps.setString(2, dto.getTitle());
            ps.setString(3, dto.getDescription());
            ps.setInt(4, dto.getPrice());
            ps.setString(5, dto.getImagePath());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}