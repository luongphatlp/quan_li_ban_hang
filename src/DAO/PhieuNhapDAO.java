package DAO;

import DTO.PhieuNhapDTO;

import java.sql.*;
import java.util.ArrayList;

public class PhieuNhapDAO {

    // =========================
    // LẤY TẤT CẢ
    // =========================
    public ArrayList<PhieuNhapDTO> getAll() {

        ArrayList<PhieuNhapDTO> list = new ArrayList<>();

        String sql = """
                     SELECT *
                     FROM phieu_nhap
                     ORDER BY ngay_nhap DESC
                     """;

        try (
            Connection conn = MyConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {

                PhieuNhapDTO pn = new PhieuNhapDTO();

                pn.setMaPn(rs.getInt("ma_pn"));
                pn.setMaNcc(rs.getInt("ma_ncc"));
                pn.setMaNv(rs.getInt("ma_nv"));

                Timestamp timestamp =
                        rs.getTimestamp("ngay_nhap");

                if (timestamp != null) {
                    pn.setNgayNhap(
                        timestamp.toLocalDateTime()
                    );
                }

                pn.setTongTien(
                    rs.getBigDecimal("tong_tien")
                );

                pn.setTrangThai(
                    rs.getInt("trang_thai")
                );

                list.add(pn);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }


    // =========================
    // LẤY THEO MÃ
    // =========================
    public PhieuNhapDTO getById(int maPn) {

        String sql = """
                     SELECT *
                     FROM phieu_nhap
                     WHERE ma_pn = ?
                     """;

        try (
            Connection conn = MyConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, maPn);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                PhieuNhapDTO pn = new PhieuNhapDTO();

                pn.setMaPn(rs.getInt("ma_pn"));
                pn.setMaNcc(rs.getInt("ma_ncc"));
                pn.setMaNv(rs.getInt("ma_nv"));

                Timestamp timestamp =
                        rs.getTimestamp("ngay_nhap");

                if (timestamp != null) {
                    pn.setNgayNhap(
                        timestamp.toLocalDateTime()
                    );
                }

                pn.setTongTien(
                    rs.getBigDecimal("tong_tien")
                );

                pn.setTrangThai(
                    rs.getInt("trang_thai")
                );

                return pn;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    // =========================
    // THÊM
    // =========================
    public int insert(PhieuNhapDTO pn) {

        String sql = """
                     INSERT INTO phieu_nhap
                     (
                         ma_ncc,
                         ma_nv,
                         ngay_nhap,
                         tong_tien,
                         trang_thai
                     )
                     VALUES (?, ?, ?, ?, ?)
                     """;

        try (
            Connection conn = MyConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(
                sql,
                Statement.RETURN_GENERATED_KEYS
            )
        ) {

            ps.setInt(1, pn.getMaNcc());
            ps.setInt(2, pn.getMaNv());

            if (pn.getNgayNhap() != null) {
                ps.setTimestamp(
                    3,
                    Timestamp.valueOf(pn.getNgayNhap())
                );
            } else {
                ps.setTimestamp(
                    3,
                    new Timestamp(System.currentTimeMillis())
                );
            }

            ps.setBigDecimal(4, pn.getTongTien());
            ps.setInt(5, pn.getTrangThai());

            int result = ps.executeUpdate();

            if (result > 0) {

                ResultSet rs = ps.getGeneratedKeys();

                if (rs.next()) {
                    return rs.getInt(1);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }


    // =========================
    // SỬA
    // =========================
    public boolean update(PhieuNhapDTO pn) {

        String sql = """
                     UPDATE phieu_nhap
                     SET ma_ncc = ?,
                         ma_nv = ?,
                         ngay_nhap = ?,
                         tong_tien = ?,
                         trang_thai = ?
                     WHERE ma_pn = ?
                     """;

        try (
            Connection conn = MyConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, pn.getMaNcc());
            ps.setInt(2, pn.getMaNv());
            ps.setTimestamp(
                3,
                Timestamp.valueOf(pn.getNgayNhap())
            );
            ps.setBigDecimal(4, pn.getTongTien());
            ps.setInt(5, pn.getTrangThai());
            ps.setInt(6, pn.getMaPn());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }


    // =========================
    // XÓA
    // =========================
    public boolean delete(int maPn) {

        String sql = """
                     DELETE FROM phieu_nhap
                     WHERE ma_pn = ?
                     """;

        try (
            Connection conn = MyConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, maPn);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    public int insert(
            Connection conn,
            PhieuNhapDTO pn) throws Exception {

        String sql = """
            INSERT INTO phieunhap
            (mancc, manv, ngaynhap, tongtien)
            VALUES (?, ?, ?, ?)
        """;

        try (PreparedStatement ps =
                     conn.prepareStatement(
                             sql,
                             Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, pn.getMaNcc());
            ps.setInt(2, pn.getMaNv());
            ps.setObject(
                    3,
                    pn.getNgayNhap()
            );
            ps.setBigDecimal(
                    4,
                    pn.getTongTien()
            );

            int result = ps.executeUpdate();

            if (result == 0) {
                return -1;
            }

            try (ResultSet rs =
                         ps.getGeneratedKeys()) {

                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }

        return -1;
    }

}