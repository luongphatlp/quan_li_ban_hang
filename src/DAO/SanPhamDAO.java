package DAO;

import DTO.SanPhamDTO;

import java.sql.*;
import java.util.ArrayList;

public class SanPhamDAO {

    // =========================
    // LẤY TẤT CẢ SẢN PHẨM
    // =========================
    public ArrayList<SanPhamDTO> getAll() {

        ArrayList<SanPhamDTO> list = new ArrayList<>();

        String sql = "SELECT * FROM san_pham";

        try (
            Connection conn = MyConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {

                SanPhamDTO sp = new SanPhamDTO();

                sp.setMaSp(rs.getInt("ma_sp"));
                sp.setTenSp(rs.getString("ten_sp"));
                sp.setMaLoai(rs.getInt("ma_loai"));
                sp.setDonGia(rs.getBigDecimal("don_gia"));
                sp.setSoLuong(rs.getInt("so_luong"));
                sp.setDonViTinh(rs.getString("don_vi_tinh"));
                sp.setMoTa(rs.getString("mo_ta"));
                sp.setTrangThai(rs.getInt("trang_thai"));

                list.add(sp);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }


    // =========================
    // LẤY SẢN PHẨM THEO MÃ
    // =========================
    public SanPhamDTO getById(int maSp) {

        String sql = """
                     SELECT *
                     FROM san_pham
                     WHERE ma_sp = ?
                     """;

        try (
            Connection conn = MyConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, maSp);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                return new SanPhamDTO(
                    rs.getInt("ma_sp"),
                    rs.getString("ten_sp"),
                    rs.getInt("ma_loai"),
                    rs.getBigDecimal("don_gia"),
                    rs.getInt("so_luong"),
                    rs.getString("don_vi_tinh"),
                    rs.getString("mo_ta"),
                    rs.getInt("trang_thai")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    // =========================
    // THÊM SẢN PHẨM
    // =========================
    public boolean insert(SanPhamDTO sp) {

        String sql = """
                     INSERT INTO san_pham
                     (
                         ten_sp,
                         ma_loai,
                         don_gia,
                         so_luong,
                         don_vi_tinh,
                         mo_ta,
                         trang_thai
                     )
                     VALUES (?, ?, ?, ?, ?, ?, ?)
                     """;

        try (
            Connection conn = MyConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setString(1, sp.getTenSp());
            ps.setInt(2, sp.getMaLoai());
            ps.setBigDecimal(3, sp.getDonGia());
            ps.setInt(4, sp.getSoLuong());
            ps.setString(5, sp.getDonViTinh());
            ps.setString(6, sp.getMoTa());
            ps.setInt(7, sp.getTrangThai());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }


    // =========================
    // SỬA SẢN PHẨM
    // =========================
    public boolean update(SanPhamDTO sp) {

        String sql = """
                     UPDATE san_pham
                     SET
                         ten_sp = ?,
                         ma_loai = ?,
                         don_gia = ?,
                         so_luong = ?,
                         don_vi_tinh = ?,
                         mo_ta = ?,
                         trang_thai = ?
                     WHERE ma_sp = ?
                     """;

        try (
            Connection conn = MyConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setString(1, sp.getTenSp());
            ps.setInt(2, sp.getMaLoai());
            ps.setBigDecimal(3, sp.getDonGia());
            ps.setInt(4, sp.getSoLuong());
            ps.setString(5, sp.getDonViTinh());
            ps.setString(6, sp.getMoTa());
            ps.setInt(7, sp.getTrangThai());
            ps.setInt(8, sp.getMaSp());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }


    // =========================
    // XÓA SẢN PHẨM
    // =========================
    public boolean delete(int maSp) {

        String sql = """
                     DELETE FROM san_pham
                     WHERE ma_sp = ?
                     """;

        try (
            Connection conn = MyConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, maSp);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }


    // =========================
    // TÌM KIẾM THEO TÊN
    // =========================
    public ArrayList<SanPhamDTO> searchByName(String keyword) {

        ArrayList<SanPhamDTO> list = new ArrayList<>();

        String sql = """
                     SELECT *
                     FROM san_pham
                     WHERE ten_sp LIKE ?
                     """;

        try (
            Connection conn = MyConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setString(1, "%" + keyword + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                SanPhamDTO sp = new SanPhamDTO(
                    rs.getInt("ma_sp"),
                    rs.getString("ten_sp"),
                    rs.getInt("ma_loai"),
                    rs.getBigDecimal("don_gia"),
                    rs.getInt("so_luong"),
                    rs.getString("don_vi_tinh"),
                    rs.getString("mo_ta"),
                    rs.getInt("trang_thai")
                );

                list.add(sp);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }


    // =========================
    // CẬP NHẬT TỒN KHO
    // =========================
    public boolean updateSoLuong(int maSp, int soLuongMoi) {

        String sql = """
                     UPDATE san_pham
                     SET so_luong = ?
                     WHERE ma_sp = ?
                     """;

        try (
            Connection conn = MyConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, soLuongMoi);
            ps.setInt(2, maSp);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    
    public int getSoLuong(Connection conn, int maSp)
            throws Exception {

            String sql = """
                SELECT soluong
                FROM sanpham
                WHERE masp = ?
            """;

            try (PreparedStatement ps = conn.prepareStatement(sql)) {

                ps.setInt(1, maSp);

                try (ResultSet rs = ps.executeQuery()) {

                    if (rs.next()) {
                        return rs.getInt("soluong");
                    }
                }
            }

            return -1;
        }

        public boolean congSoLuong(
                Connection conn,
                int maSp,
                int soLuong) throws Exception {

            String sql = """
                UPDATE sanpham
                SET soluong = soluong + ?
                WHERE masp = ?
            """;

            try (PreparedStatement ps =
                         conn.prepareStatement(sql)) {

                ps.setInt(1, soLuong);
                ps.setInt(2, maSp);

                return ps.executeUpdate() > 0;
            }
        }

        public boolean truSoLuong(
                Connection conn,
                int maSp,
                int soLuong) throws Exception {

            String sql = """
                UPDATE sanpham
                SET soluong = soluong - ?
                WHERE masp = ?
                AND soluong >= ?
            """;

            try (PreparedStatement ps =
                         conn.prepareStatement(sql)) {

                ps.setInt(1, soLuong);
                ps.setInt(2, maSp);
                ps.setInt(3, soLuong);

                return ps.executeUpdate() > 0;
            }
        }
}