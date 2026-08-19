package DAO;

import DTO.NhanVienDTO;

import java.sql.*;
import java.util.ArrayList;

public class NhanVienDAO {

    // =========================
    // LẤY TẤT CẢ
    // =========================
    public ArrayList<NhanVienDTO> getAll() {

        ArrayList<NhanVienDTO> list = new ArrayList<>();

        String sql = "SELECT * FROM nhan_vien";

        try (
            Connection conn = MyConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {

                NhanVienDTO nv = new NhanVienDTO();

                nv.setMaNv(rs.getInt("ma_nv"));
                nv.setHoTen(rs.getString("ho_ten"));
                nv.setSoDienThoai(rs.getString("so_dien_thoai"));
                nv.setEmail(rs.getString("email"));
                nv.setTaiKhoan(rs.getString("tai_khoan"));
                nv.setMatKhau(rs.getString("mat_khau"));
                nv.setVaiTro(rs.getString("vai_tro"));
                nv.setTrangThai(rs.getInt("trang_thai"));

                list.add(nv);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }


    // =========================
    // ĐĂNG NHẬP
    // =========================
    public NhanVienDTO login(String taiKhoan, String matKhau) {

        String sql = """
                     SELECT *
                     FROM nhan_vien
                     WHERE tai_khoan = ?
                       AND mat_khau = ?
                       AND trang_thai = 1
                     """;

        try (
            Connection conn = MyConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setString(1, taiKhoan);
            ps.setString(2, matKhau);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                NhanVienDTO nv = new NhanVienDTO();

                nv.setMaNv(rs.getInt("ma_nv"));
                nv.setHoTen(rs.getString("ho_ten"));
                nv.setSoDienThoai(rs.getString("so_dien_thoai"));
                nv.setEmail(rs.getString("email"));
                nv.setTaiKhoan(rs.getString("tai_khoan"));
                nv.setMatKhau(rs.getString("mat_khau"));
                nv.setVaiTro(rs.getString("vai_tro"));
                nv.setTrangThai(rs.getInt("trang_thai"));

                return nv;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    // =========================
    // THÊM
    // =========================
    public boolean insert(NhanVienDTO nv) {

        String sql = """
                     INSERT INTO nhan_vien
                     (
                         ho_ten,
                         so_dien_thoai,
                         email,
                         tai_khoan,
                         mat_khau,
                         vai_tro,
                         trang_thai
                     )
                     VALUES (?, ?, ?, ?, ?, ?, ?)
                     """;

        try (
            Connection conn = MyConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setString(1, nv.getHoTen());
            ps.setString(2, nv.getSoDienThoai());
            ps.setString(3, nv.getEmail());
            ps.setString(4, nv.getTaiKhoan());
            ps.setString(5, nv.getMatKhau());
            ps.setString(6, nv.getVaiTro());
            ps.setInt(7, nv.getTrangThai());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }


    // =========================
    // SỬA
    // =========================
    public boolean update(NhanVienDTO nv) {

        String sql = """
                     UPDATE nhan_vien
                     SET ho_ten = ?,
                         so_dien_thoai = ?,
                         email = ?,
                         tai_khoan = ?,
                         mat_khau = ?,
                         vai_tro = ?,
                         trang_thai = ?
                     WHERE ma_nv = ?
                     """;

        try (
            Connection conn = MyConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setString(1, nv.getHoTen());
            ps.setString(2, nv.getSoDienThoai());
            ps.setString(3, nv.getEmail());
            ps.setString(4, nv.getTaiKhoan());
            ps.setString(5, nv.getMatKhau());
            ps.setString(6, nv.getVaiTro());
            ps.setInt(7, nv.getTrangThai());
            ps.setInt(8, nv.getMaNv());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }


    // =========================
    // XÓA
    // =========================
    public boolean delete(int maNv) {

        String sql = """
                     DELETE FROM nhan_vien
                     WHERE ma_nv = ?
                     """;

        try (
            Connection conn = MyConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, maNv);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}