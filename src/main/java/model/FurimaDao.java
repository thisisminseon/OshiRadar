package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import util.DBManager;

public class FurimaDao {

	// Product Registration
	public int insert(FurimaDto dto) {

		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql = "INSERT INTO furima_posts ( "
				+ "post_id, user_id, title, description, price, image_path, status, created_at " + ") VALUES ( "
				+ "seq_furima_posts.NEXTVAL, ?, ?, ?, ?, ?, ?, SYSDATE " + ")";

		try {
			conn = DBManager.getInstance();
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, dto.getUserId());
			pstmt.setString(2, dto.getTitle());
			pstmt.setString(3, dto.getDescription());
			pstmt.setInt(4, dto.getPrice());
			pstmt.setString(5, dto.getImagePath());
			pstmt.setString(6, String.valueOf(dto.getStatus()));

			return pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt, conn);
		}

		return 0;
	}

	// ALL Products List
	public List<FurimaDto> findAll() {

		List<FurimaDto> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "SELECT post_id, user_id, title, description, price, image_path, status, created_at "
				+ "FROM furima_posts ORDER BY post_id DESC";

		try {
			conn = DBManager.getInstance();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				FurimaDto dto = new FurimaDto();
				dto.setPostId(rs.getInt("post_id"));
				dto.setUserId(rs.getInt("user_id"));
				dto.setTitle(rs.getString("title"));
				dto.setDescription(rs.getString("description"));
				dto.setPrice(rs.getInt("price"));
				dto.setImagePath(rs.getString("image_path"));
				dto.setStatus(rs.getString("status").charAt(0));
				dto.setCreatedAt(rs.getDate("created_at"));

				list.add(dto);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, conn);
		}

		return list;
	}

	// Detail about a product
	public FurimaDto findById(int postId) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "SELECT post_id, user_id, title, description, price, image_path, status, created_at "
				+ "FROM furima_posts WHERE post_id = ?";

		try {
			conn = DBManager.getInstance();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, postId);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				FurimaDto dto = new FurimaDto();
				dto.setPostId(rs.getInt("post_id"));
				dto.setUserId(rs.getInt("user_id"));
				dto.setTitle(rs.getString("title"));
				dto.setDescription(rs.getString("description"));
				dto.setPrice(rs.getInt("price"));
				dto.setImagePath(rs.getString("image_path"));
				dto.setStatus(rs.getString("status").charAt(0));
				dto.setCreatedAt(rs.getDate("created_at"));
				return dto;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, conn);
		}

		return null;
	}

	// Products List by User
	public List<FurimaDto> findByUser(int userId) {

		List<FurimaDto> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "SELECT post_id, user_id, title, description, price, image_path, status, created_at "
				+ "FROM furima_posts WHERE user_id = ? ORDER BY post_id DESC";

		try {
			conn = DBManager.getInstance();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				FurimaDto dto = new FurimaDto();
				dto.setPostId(rs.getInt("post_id"));
				dto.setUserId(rs.getInt("user_id"));
				dto.setTitle(rs.getString("title"));
				dto.setDescription(rs.getString("description"));
				dto.setPrice(rs.getInt("price"));
				dto.setImagePath(rs.getString("image_path"));
				dto.setStatus(rs.getString("status").charAt(0));
				dto.setCreatedAt(rs.getDate("created_at"));

				list.add(dto);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, conn);
		}

		return list;
	}

	// Update Product Status
	public int updateStatus(int postId, char status) {

		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql = "UPDATE furima_posts SET status = ? WHERE post_id = ?";

		try {
			conn = DBManager.getInstance();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, String.valueOf(status));
			pstmt.setInt(2, postId);

			return pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt, conn);
		}

		return 0;
	}

	/*
	 * ===================== Close =====================
	 */
	private void close(PreparedStatement pstmt, Connection conn) {
		try {
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void close(ResultSet rs, PreparedStatement pstmt, Connection conn) {
		try {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}