package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import util.DBManager;

public class CafeDao {

    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    public CafeDao() {
        conn = DBManager.getInstance();
    }

    // Get all cafes
    public List<CafeDto> getList() {

        List<CafeDto> list = new ArrayList<>();

        String sql =
            "SELECT id, name, description, image, latitude, longitude " +
            "FROM nearcafe " +
            "ORDER BY id DESC";

        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                CafeDto dto = new CafeDto();

                dto.setId(rs.getInt("id"));
                dto.setName(rs.getString("name"));
                dto.setDescription(rs.getString("description"));
                dto.setImage(rs.getString("image"));
                dto.setLatitude(rs.getDouble("latitude"));
                dto.setLongitude(rs.getDouble("longitude"));

                dto.setDistance(0.0);

                list.add(dto);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }

        return list;
    }

    // Get total count
    public int getTotalCount() {

        int count = 0;
        String sql = "SELECT COUNT(*) FROM nearcafe";

        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            if (rs.next()) {
                count = rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }

        return count;
    }

    // Get cafe by id
    public CafeDto getById(int id) {

        CafeDto dto = null;

        String sql =
            "SELECT id, name, description, image, latitude, longitude " +
            "FROM nearcafe " +
            "WHERE id = ?";

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                dto = new CafeDto();

                dto.setId(rs.getInt("id"));
                dto.setName(rs.getString("name"));
                dto.setDescription(rs.getString("description"));
                dto.setImage(rs.getString("image"));
                dto.setLatitude(rs.getDouble("latitude"));
                dto.setLongitude(rs.getDouble("longitude"));

                dto.setDistance(0.0);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }

        return dto;
    }

    // Close resources
    private void close() {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}