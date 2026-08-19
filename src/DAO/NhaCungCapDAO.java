package DAO;

import DTO.NhaCungCapDTO;

import java.sql.*;
import java.util.ArrayList;

public class NhaCungCapDAO {

    // =========================
    // LẤY TẤT CẢ
    // =========================
    public ArrayList<NhaCungCapDTO> getAll() {

        ArrayList<NhaCungCapDTO> list = new ArrayList<>();

        String sql = "SELECT * FROM nha_cung_cap";

        try (
            Connection conn = MyConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {

                NhaCungCapDTO ncc = new NhaCungCapDTO();

                ncc.setMaNcc(rs.getInt("ma_ncc"));
                ncc.setTenNcc(rs.getString("ten_ncc"));
                ncc.setSoDienThoai(rs.getString("so_dien_thoai"));
                ncc.setEmail(rs.getString("email"));
                ncc.setDiaChi(rs.getString("dia_chi"));
                ncc.setTrangThai(rs.getInt("trang_thai"));

                list.add(ncc);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }


    // =========================
    // LẤY THEO MÃ
    // =========================
    public NhaCungCapDTO getById(int maNcc) {

        String sql = """
                     SELECT *
                     FROM nha_cung_cap
                     WHERE ma_ncc = ?
                     """;

        try (
            Connection conn = MyConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, maNcc);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                return new NhaCungCapDTO(
                    rs.getInt("ma_ncc"),
                    rs.getString("ten_ncc"),
                    rs.getString("so_dien_thoai"),
                    rs.getString("email"),
                    rs.getString("dia_chi"),
                    rs.getInt("trang_thai")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    // =========================
    // THÊM
    // =========================
    public boolean insert(NhaCungCapDTO ncc) {

        String sql = """
                     INSERT INTO nha_cung_cap
                     (
                         ten_ncc,
                         so_dien_thoai,
                         email,
                         dia_chi,
                         trang_thai
                     )
                     VALUES (?, ?, ?, ?, ?)
                     """;

        try (
            Connection conn = MyConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setString(1, ncc.getTenNcc());
            ps.setString(2, ncc.getSoDienThoai());
            ps.setString(3, ncc.getEmail());
            ps.setString(4, ncc.getDiaChi());
            ps.setInt(5, ncc.getTrangThai());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }


    // =========================
    // SỬA
    // =========================
    public boolean update(NhaCungCapDTO ncc) {

        String sql = """
                     UPDATE nha_cung_cap
                     SET ten_ncc = ?,
                         so_dien_thoai = ?,
                         email = ?,
                         dia_chi = ?,
                         trang_thai = ?
                     WHERE ma_ncc = ?
                     """;

        try (
            Connection conn = MyConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setString(1, ncc.getTenNcc());
            ps.setString(2, ncc.getSoDienThoai());
            ps.setString(3, ncc.getEmail());
            ps.setString(4, ncc.getDiaChi());
            ps.setInt(5, ncc.getTrangThai());
            ps.setInt(6, ncc.getMaNcc());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }


    // =========================
    // XÓA
    // =========================
    public boolean delete(int maNcc) {

        String sql = """
                     DELETE FROM nha_cung_cap
                     WHERE ma_ncc = ?
                     """;

        try (
            Connection conn = MyConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, maNcc);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }


    // =========================
    // TÌM KIẾM
    // =========================
    public ArrayList<NhaCungCapDTO> search(String keyword) {

        ArrayList<NhaCungCapDTO> list = new ArrayList<>();

        String sql = """
                     SELECT *
                     FROM nha_cung_cap
                     WHERE ten_ncc LIKE ?
                        OR so_dien_thoai LIKE ?
                     """;

        try (
            Connection conn = MyConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            String value = "%" + keyword + "%";

            ps.setString(1, value);
            ps.setString(2, value);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                NhaCungCapDTO ncc = new NhaCungCapDTO(
                    rs.getInt("ma_ncc"),
                    rs.getString("ten_ncc"),
                    rs.getString("so_dien_thoai"),
                    rs.getString("email"),
                    rs.getString("dia_chi"),
                    rs.getInt("trang_thai")
                );

                list.add(ncc);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}