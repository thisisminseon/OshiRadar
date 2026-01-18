package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.DBManager;
import util.PasswordBcrypt;

public class UserDao {

    // 아이디 중복 체크
    public int isIdExist(String loginId) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String sql = "SELECT COUNT(*) FROM users WHERE login_id = ?";

        try {
            conn = DBManager.getInstance();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, loginId);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(rs, pstmt, conn);
        }
        return -1;
    }

    // 회원가입
    public UserDto userSave(UserDto dto) {

        if (isIdExist(dto.getLoginId()) > 0) {
            return null;
        }

        Connection conn = null;
        PreparedStatement pstmt = null;

        String sql =
            "INSERT INTO users ( " +
            "user_id, login_id, user_name, birth_date, nickname, password, phone_1, phone_2, gender, address " +
            ") VALUES ( " +
            "seq_users_id.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ? " +
            ")";

        try {
            conn = DBManager.getInstance();
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, dto.getLoginId());
            pstmt.setString(2, dto.getUserName());
            pstmt.setDate(3, new java.sql.Date(dto.getBirthDate().getTime()));
            pstmt.setString(4, dto.getNickname());
            pstmt.setString(5, dto.getPassword());
            pstmt.setString(6, dto.getPhone1());
            pstmt.setString(7, dto.getPhone2());
            pstmt.setString(8, String.valueOf(dto.getGender()));
            pstmt.setString(9, dto.getAddress());

            int result = pstmt.executeUpdate();
            if (result == 1) {
                return dto;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(null, pstmt, conn);
        }
        return null;
    }

    // 로그인용 사용자 조회
    public UserDto findByLoginId(String loginId) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String sql =
            "SELECT user_id, login_id, user_name, birth_date, nickname, password, " +
            "phone_1, phone_2, gender, address, created_at, updated_at " +
            "FROM users WHERE login_id = ?";

        try {
            conn = DBManager.getInstance();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, loginId);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                UserDto dto = new UserDto();
                dto.setUserId(rs.getInt("user_id"));
                dto.setLoginId(rs.getString("login_id"));
                dto.setUserName(rs.getString("user_name"));
                dto.setBirthDate(rs.getDate("birth_date"));
                dto.setNickname(rs.getString("nickname"));
                dto.setPassword(rs.getString("password"));
                dto.setPhone1(rs.getString("phone_1"));
                dto.setPhone2(rs.getString("phone_2"));
                dto.setGender(rs.getString("gender").charAt(0));
                dto.setAddress(rs.getString("address"));
                dto.setCreatedAt(rs.getDate("created_at"));
                dto.setUpdatedAt(rs.getDate("updated_at"));
                return dto;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(rs, pstmt, conn);
        }
        return null;
    }

    private void close(ResultSet rs, PreparedStatement pstmt, Connection conn) {
        try {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public UserDto login(String loginId, String password) {

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String sql =
            "SELECT user_id, login_id, user_name, birth_date, nickname, password, " +
            "phone_1, phone_2, gender, address, created_at, updated_at " +
            "FROM users WHERE login_id = ?";

        try {
            conn = DBManager.getInstance();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, loginId);
            rs = pstmt.executeQuery();

            if (rs.next()) {

                String dbPassword = rs.getString("password");

                
                if (!PasswordBcrypt.checkPassword(password, dbPassword)) {
                    return null;
                }

                UserDto dto = new UserDto();
                dto.setUserId(rs.getInt("user_id"));
                dto.setLoginId(rs.getString("login_id"));
                dto.setUserName(rs.getString("user_name"));
                dto.setBirthDate(rs.getDate("birth_date"));
                dto.setNickname(rs.getString("nickname"));
                dto.setPassword(dbPassword);
                dto.setPhone1(rs.getString("phone_1"));
                dto.setPhone2(rs.getString("phone_2"));
                dto.setGender(rs.getString("gender").charAt(0));
                dto.setAddress(rs.getString("address"));
                dto.setCreatedAt(rs.getDate("created_at"));
                dto.setUpdatedAt(rs.getDate("updated_at"));

                return dto;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(rs, pstmt, conn);
        }

        return null;
    }
}