package DAO;

import DTO.ChiTietPhieuNhapDTO;

import java.sql.*;
import java.util.ArrayList;

public class ChiTietPhieuNhapDAO {

    // =========================
    // LẤY THEO PHIẾU NHẬP
    // =========================
    public ArrayList<ChiTietPhieuNhapDTO> getByPhieuNhap(int maPn) {

        ArrayList<ChiTietPhieuNhapDTO> list = new ArrayList<>();

        String sql = """
                     SELECT *
                     FROM chi_tiet_phieu_nhap
                     WHERE ma_pn = ?
                     """;

        try (
            Connection conn = MyConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, maPn);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                ChiTietPhieuNhapDTO ct =
                        new ChiTietPhieuNhapDTO();

                ct.setMaPn(rs.getInt("ma_pn"));
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
    public boolean insert(ChiTietPhieuNhapDTO ct) {

        String sql = """
                     INSERT INTO chi_tiet_phieu_nhap
                     (
                         ma_pn,
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

            ps.setInt(1, ct.getMaPn());
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
    public boolean delete(int maPn, int maSp) {

        String sql = """
                     DELETE FROM chi_tiet_phieu_nhap
                     WHERE ma_pn = ?
                       AND ma_sp = ?
                     """;

        try (
            Connection conn = MyConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, maPn);
            ps.setInt(2, maSp);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean insert(
            Connection conn,
            ChiTietPhieuNhapDTO ct) throws Exception {

        String sql = """
            INSERT INTO chitietphieunhap
            (mapn, masp, soluong, dongia)
            VALUES (?, ?, ?, ?)
        """;

        try (PreparedStatement ps =
                     conn.prepareStatement(sql)) {

            ps.setInt(1, ct.getMaPn());
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