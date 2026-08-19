package DAO;

import DTO.KhachHangDTO;

import java.sql.*;
import java.util.ArrayList;

public class KhachHangDAO {

    // =========================
    // LẤY TẤT CẢ
    // =========================
    public ArrayList<KhachHangDTO> getAll() {

        ArrayList<KhachHangDTO> list = new ArrayList<>();

        String sql = "SELECT * FROM khach_hang";

        try (
            Connection conn = MyConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {

                KhachHangDTO kh = new KhachHangDTO();

                kh.setMaKh(rs.getInt("ma_kh"));
                kh.setHoTen(rs.getString("ho_ten"));
                kh.setSoDienThoai(rs.getString("so_dien_thoai"));
                kh.setEmail(rs.getString("email"));
                kh.setDiaChi(rs.getString("dia_chi"));

                Timestamp timestamp = rs.getTimestamp("ngay_tao");

                if (timestamp != null) {
                    kh.setNgayTao(timestamp.toLocalDateTime());
                }

                kh.setTrangThai(rs.getInt("trang_thai"));

                list.add(kh);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }


    // =========================
    // LẤY THEO MÃ
    // =========================
    public KhachHangDTO getById(int maKh) {

        String sql = """
                     SELECT *
                     FROM khach_hang
                     WHERE ma_kh = ?
                     """;

        try (
            Connection conn = MyConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, maKh);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                KhachHangDTO kh = new KhachHangDTO();

                kh.setMaKh(rs.getInt("ma_kh"));
                kh.setHoTen(rs.getString("ho_ten"));
                kh.setSoDienThoai(rs.getString("so_dien_thoai"));
                kh.setEmail(rs.getString("email"));
                kh.setDiaChi(rs.getString("dia_chi"));

                Timestamp timestamp = rs.getTimestamp("ngay_tao");

                if (timestamp != null) {
                    kh.setNgayTao(timestamp.toLocalDateTime());
                }

                kh.setTrangThai(rs.getInt("trang_thai"));

                return kh;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    // =========================
    // THÊM
    // =========================
    public boolean insert(KhachHangDTO kh) {

        String sql = """
                     INSERT INTO khach_hang
                     (ho_ten, so_dien_thoai, email, dia_chi, trang_thai)
                     VALUES (?, ?, ?, ?, ?)
                     """;

        try (
            Connection conn = MyConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setString(1, kh.getHoTen());
            ps.setString(2, kh.getSoDienThoai());
            ps.setString(3, kh.getEmail());
            ps.setString(4, kh.getDiaChi());
            ps.setInt(5, kh.getTrangThai());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }


    // =========================
    // SỬA
    // =========================
    public boolean update(KhachHangDTO kh) {

        String sql = """
                     UPDATE khach_hang
                     SET ho_ten = ?,
                         so_dien_thoai = ?,
                         email = ?,
                         dia_chi = ?,
                         trang_thai = ?
                     WHERE ma_kh = ?
                     """;

        try (
            Connection conn = MyConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setString(1, kh.getHoTen());
            ps.setString(2, kh.getSoDienThoai());
            ps.setString(3, kh.getEmail());
            ps.setString(4, kh.getDiaChi());
            ps.setInt(5, kh.getTrangThai());
            ps.setInt(6, kh.getMaKh());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }


    // =========================
    // XÓA
    // =========================
    public boolean delete(int maKh) {

        String sql = """
                     DELETE FROM khach_hang
                     WHERE ma_kh = ?
                     """;

        try (
            Connection conn = MyConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, maKh);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }


    // =========================
    // TÌM KIẾM
    // =========================
    public ArrayList<KhachHangDTO> search(String keyword) {

        ArrayList<KhachHangDTO> list = new ArrayList<>();

        String sql = """
                     SELECT *
                     FROM khach_hang
                     WHERE ho_ten LIKE ?
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

                KhachHangDTO kh = new KhachHangDTO();

                kh.setMaKh(rs.getInt("ma_kh"));
                kh.setHoTen(rs.getString("ho_ten"));
                kh.setSoDienThoai(rs.getString("so_dien_thoai"));
                kh.setEmail(rs.getString("email"));
                kh.setDiaChi(rs.getString("dia_chi"));

                Timestamp timestamp = rs.getTimestamp("ngay_tao");

                if (timestamp != null) {
                    kh.setNgayTao(timestamp.toLocalDateTime());
                }

                kh.setTrangThai(rs.getInt("trang_thai"));

                list.add(kh);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}