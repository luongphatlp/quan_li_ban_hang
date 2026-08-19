package DAO;

import DTO.KhuyenMaiDTO;

import java.sql.*;
import java.util.ArrayList;

public class KhuyenMaiDAO {

    // =========================
    // LẤY TẤT CẢ
    // =========================
    public ArrayList<KhuyenMaiDTO> getAll() {

        ArrayList<KhuyenMaiDTO> list = new ArrayList<>();

        String sql = "SELECT * FROM khuyen_mai";

        try (
            Connection conn = MyConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {

                KhuyenMaiDTO km = new KhuyenMaiDTO();

                km.setMaKm(rs.getInt("ma_km"));
                km.setTenKm(rs.getString("ten_km"));
                km.setLoaiGiam(rs.getString("loai_giam"));
                km.setGiaTriGiam(rs.getBigDecimal("gia_tri_giam"));

                Timestamp start = rs.getTimestamp("ngay_bat_dau");
                Timestamp end = rs.getTimestamp("ngay_ket_thuc");

                if (start != null) {
                    km.setNgayBatDau(start.toLocalDateTime());
                }

                if (end != null) {
                    km.setNgayKetThuc(end.toLocalDateTime());
                }

                km.setMoTa(rs.getString("mo_ta"));
                km.setTrangThai(rs.getInt("trang_thai"));

                list.add(km);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }


    // =========================
    // LẤY THEO MÃ
    // =========================
    public KhuyenMaiDTO getById(int maKm) {

        String sql = """
                     SELECT *
                     FROM khuyen_mai
                     WHERE ma_km = ?
                     """;

        try (
            Connection conn = MyConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, maKm);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                KhuyenMaiDTO km = new KhuyenMaiDTO();

                km.setMaKm(rs.getInt("ma_km"));
                km.setTenKm(rs.getString("ten_km"));
                km.setLoaiGiam(rs.getString("loai_giam"));
                km.setGiaTriGiam(rs.getBigDecimal("gia_tri_giam"));

                Timestamp start = rs.getTimestamp("ngay_bat_dau");
                Timestamp end = rs.getTimestamp("ngay_ket_thuc");

                if (start != null) {
                    km.setNgayBatDau(start.toLocalDateTime());
                }

                if (end != null) {
                    km.setNgayKetThuc(end.toLocalDateTime());
                }

                km.setMoTa(rs.getString("mo_ta"));
                km.setTrangThai(rs.getInt("trang_thai"));

                return km;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    // =========================
    // THÊM
    // =========================
    public boolean insert(KhuyenMaiDTO km) {

        String sql = """
                     INSERT INTO khuyen_mai
                     (
                         ten_km,
                         loai_giam,
                         gia_tri_giam,
                         ngay_bat_dau,
                         ngay_ket_thuc,
                         mo_ta,
                         trang_thai
                     )
                     VALUES (?, ?, ?, ?, ?, ?, ?)
                     """;

        try (
            Connection conn = MyConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setString(1, km.getTenKm());
            ps.setString(2, km.getLoaiGiam());
            ps.setBigDecimal(3, km.getGiaTriGiam());
            ps.setTimestamp(4, Timestamp.valueOf(km.getNgayBatDau()));
            ps.setTimestamp(5, Timestamp.valueOf(km.getNgayKetThuc()));
            ps.setString(6, km.getMoTa());
            ps.setInt(7, km.getTrangThai());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }


    // =========================
    // SỬA
    // =========================
    public boolean update(KhuyenMaiDTO km) {

        String sql = """
                     UPDATE khuyen_mai
                     SET ten_km = ?,
                         loai_giam = ?,
                         gia_tri_giam = ?,
                         ngay_bat_dau = ?,
                         ngay_ket_thuc = ?,
                         mo_ta = ?,
                         trang_thai = ?
                     WHERE ma_km = ?
                     """;

        try (
            Connection conn = MyConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setString(1, km.getTenKm());
            ps.setString(2, km.getLoaiGiam());
            ps.setBigDecimal(3, km.getGiaTriGiam());
            ps.setTimestamp(4, Timestamp.valueOf(km.getNgayBatDau()));
            ps.setTimestamp(5, Timestamp.valueOf(km.getNgayKetThuc()));
            ps.setString(6, km.getMoTa());
            ps.setInt(7, km.getTrangThai());
            ps.setInt(8, km.getMaKm());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }


    // =========================
    // XÓA
    // =========================
    public boolean delete(int maKm) {

        String sql = """
                     DELETE FROM khuyen_mai
                     WHERE ma_km = ?
                     """;

        try (
            Connection conn = MyConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, maKm);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}