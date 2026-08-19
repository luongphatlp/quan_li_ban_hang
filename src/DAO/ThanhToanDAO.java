package DAO;

import DTO.ThanhToanDTO;

import java.sql.*;
import java.util.ArrayList;

public class ThanhToanDAO {

    // =========================
    // LẤY TẤT CẢ
    // =========================
    public ArrayList<ThanhToanDTO> getAll() {

        ArrayList<ThanhToanDTO> list = new ArrayList<>();

        String sql = """
                     SELECT *
                     FROM thanh_toan
                     ORDER BY ngay_thanh_toan DESC
                     """;

        try (
            Connection conn = MyConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {

                ThanhToanDTO tt = new ThanhToanDTO();

                tt.setMaTt(rs.getInt("ma_tt"));
                tt.setMaHd(rs.getInt("ma_hd"));
                tt.setPhuongThuc(rs.getString("phuong_thuc"));
                tt.setSoTien(rs.getBigDecimal("so_tien"));

                Timestamp timestamp =
                        rs.getTimestamp("ngay_thanh_toan");

                if (timestamp != null) {
                    tt.setNgayThanhToan(
                        timestamp.toLocalDateTime()
                    );
                }

                tt.setTrangThai(
                    rs.getString("trang_thai")
                );

                list.add(tt);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }


    // =========================
    // LẤY THEO HÓA ĐƠN
    // =========================
    public ArrayList<ThanhToanDTO> getByHoaDon(int maHd) {

        ArrayList<ThanhToanDTO> list = new ArrayList<>();

        String sql = """
                     SELECT *
                     FROM thanh_toan
                     WHERE ma_hd = ?
                     """;

        try (
            Connection conn = MyConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, maHd);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                ThanhToanDTO tt = new ThanhToanDTO();

                tt.setMaTt(rs.getInt("ma_tt"));
                tt.setMaHd(rs.getInt("ma_hd"));
                tt.setPhuongThuc(rs.getString("phuong_thuc"));
                tt.setSoTien(rs.getBigDecimal("so_tien"));

                Timestamp timestamp =
                        rs.getTimestamp("ngay_thanh_toan");

                if (timestamp != null) {
                    tt.setNgayThanhToan(
                        timestamp.toLocalDateTime()
                    );
                }

                tt.setTrangThai(
                    rs.getString("trang_thai")
                );

                list.add(tt);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }


    // =========================
    // THÊM
    // =========================
    public boolean insert(ThanhToanDTO tt) {

        String sql = """
                     INSERT INTO thanh_toan
                     (
                         ma_hd,
                         phuong_thuc,
                         so_tien,
                         ngay_thanh_toan,
                         trang_thai
                     )
                     VALUES (?, ?, ?, ?, ?)
                     """;

        try (
            Connection conn = MyConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, tt.getMaHd());
            ps.setString(2, tt.getPhuongThuc());
            ps.setBigDecimal(3, tt.getSoTien());

            if (tt.getNgayThanhToan() != null) {
                ps.setTimestamp(
                    4,
                    Timestamp.valueOf(tt.getNgayThanhToan())
                );
            } else {
                ps.setTimestamp(
                    4,
                    new Timestamp(System.currentTimeMillis())
                );
            }

            ps.setString(5, tt.getTrangThai());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }


    // =========================
    // XÓA
    // =========================
    public boolean delete(int maTt) {

        String sql = """
                     DELETE FROM thanh_toan
                     WHERE ma_tt = ?
                     """;

        try (
            Connection conn = MyConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, maTt);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}