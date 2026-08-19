package DAO;

import DTO.ChiTietHoaDonDTO;

import java.sql.*;
import java.util.ArrayList;

public class ChiTietHoaDonDAO {

    // =========================
    // LẤY CHI TIẾT THEO HÓA ĐƠN
    // =========================
    public ArrayList<ChiTietHoaDonDTO> getByHoaDon(int maHd) {

        ArrayList<ChiTietHoaDonDTO> list = new ArrayList<>();

        String sql = """
                     SELECT *
                     FROM chi_tiet_hoa_don
                     WHERE ma_hd = ?
                     """;

        try (
            Connection conn = MyConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, maHd);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                ChiTietHoaDonDTO ct =
                        new ChiTietHoaDonDTO();

                ct.setMaHd(rs.getInt("ma_hd"));
                ct.setMaSp(rs.getInt("ma_sp"));
                ct.setSoLuong(rs.getInt("so_luong"));
                ct.setDonGia(rs.getBigDecimal("don_gia"));
                ct.setThanhTien(
                    rs.getBigDecimal("thanh_tien")
                );

                list.add(ct);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }


    // =========================
    // THÊM
    // =========================
    public boolean insert(ChiTietHoaDonDTO ct) {

        String sql = """
                     INSERT INTO chi_tiet_hoa_don
                     (
                         ma_hd,
                         ma_sp,
                         so_luong,
                         don_gia,
                         thanh_tien
                     )
                     VALUES (?, ?, ?, ?, ?)
                     """;

        try (
            Connection conn = MyConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, ct.getMaHd());
            ps.setInt(2, ct.getMaSp());
            ps.setInt(3, ct.getSoLuong());
            ps.setBigDecimal(4, ct.getDonGia());
            ps.setBigDecimal(5, ct.getThanhTien());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }


    // =========================
    // XÓA
    // =========================
    public boolean delete(int maHd, int maSp) {

        String sql = """
                     DELETE FROM chi_tiet_hoa_don
                     WHERE ma_hd = ?
                       AND ma_sp = ?
                     """;

        try (
            Connection conn = MyConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, maHd);
            ps.setInt(2, maSp);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean insert(
            Connection conn,
            ChiTietHoaDonDTO ct) throws Exception {

        String sql = """
            INSERT INTO chitiethoadon
            (mahd, masp, soluong, dongia)
            VALUES (?, ?, ?, ?)
        """;

        try (PreparedStatement ps =
                     conn.prepareStatement(sql)) {

            ps.setInt(1, ct.getMaHd());
            ps.setInt(2, ct.getMaSp());
            ps.setInt(3, ct.getSoLuong());

            ps.setBigDecimal(
                    4,
                    ct.getDonGia()
            );

            return ps.executeUpdate() > 0;
        }
    }

}