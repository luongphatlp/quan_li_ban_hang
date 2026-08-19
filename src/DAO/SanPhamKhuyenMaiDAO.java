package DAO;

import DTO.SanPhamKhuyenMaiDTO;

import java.sql.*;
import java.util.ArrayList;

public class SanPhamKhuyenMaiDAO {

    // =========================
    // LẤY TẤT CẢ
    // =========================
    public ArrayList<SanPhamKhuyenMaiDTO> getAll() {

        ArrayList<SanPhamKhuyenMaiDTO> list = new ArrayList<>();

        String sql = "SELECT * FROM san_pham_khuyen_mai";

        try (
            Connection conn = MyConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {

                SanPhamKhuyenMaiDTO spkm =
                        new SanPhamKhuyenMaiDTO();

                spkm.setMaKm(rs.getInt("ma_km"));
                spkm.setMaSp(rs.getInt("ma_sp"));

                list.add(spkm);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }


    // =========================
    // THÊM
    // =========================
    public boolean insert(SanPhamKhuyenMaiDTO spkm) {

        String sql = """
                     INSERT INTO san_pham_khuyen_mai
                     (ma_km, ma_sp)
                     VALUES (?, ?)
                     """;

        try (
            Connection conn = MyConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, spkm.getMaKm());
            ps.setInt(2, spkm.getMaSp());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }


    // =========================
    // XÓA
    // =========================
    public boolean delete(int maKm, int maSp) {

        String sql = """
                     DELETE FROM san_pham_khuyen_mai
                     WHERE ma_km = ?
                       AND ma_sp = ?
                     """;

        try (
            Connection conn = MyConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, maKm);
            ps.setInt(2, maSp);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }


    // =========================
    // XÓA TOÀN BỘ SẢN PHẨM
    // KHỎI KHUYẾN MÃI
    // =========================
    public boolean deleteByKhuyenMai(int maKm) {

        String sql = """
                     DELETE FROM san_pham_khuyen_mai
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


    // =========================
    // LẤY SẢN PHẨM CỦA KHUYẾN MÃI
    // =========================
    public ArrayList<Integer> getSanPhamByKhuyenMai(int maKm) {

        ArrayList<Integer> list = new ArrayList<>();

        String sql = """
                     SELECT ma_sp
                     FROM san_pham_khuyen_mai
                     WHERE ma_km = ?
                     """;

        try (
            Connection conn = MyConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, maKm);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(rs.getInt("ma_sp"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}