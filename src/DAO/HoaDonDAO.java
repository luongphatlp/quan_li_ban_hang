package DAO;

import DTO.HoaDonDTO;

import java.sql.*;
import java.util.ArrayList;

public class HoaDonDAO {

    // =========================
    // LẤY TẤT CẢ
    // =========================
    public ArrayList<HoaDonDTO> getAll() {

        ArrayList<HoaDonDTO> list = new ArrayList<>();

        String sql = """
                     SELECT *
                     FROM hoa_don
                     ORDER BY ngay_lap DESC
                     """;

        try (
            Connection conn = MyConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {

                HoaDonDTO hd = new HoaDonDTO();

                hd.setMaHd(rs.getInt("ma_hd"));

                int maKh = rs.getInt("ma_kh");

                if (rs.wasNull()) {
                    hd.setMaKh(null);
                } else {
                    hd.setMaKh(maKh);
                }

                hd.setMaNv(rs.getInt("ma_nv"));

                Timestamp timestamp =
                        rs.getTimestamp("ngay_lap");

                if (timestamp != null) {
                    hd.setNgayLap(
                        timestamp.toLocalDateTime()
                    );
                }

                hd.setTongTien(
                    rs.getBigDecimal("tong_tien")
                );

                hd.setTienGiam(
                    rs.getBigDecimal("tien_giam")
                );

                hd.setThanhTien(
                    rs.getBigDecimal("thanh_tien")
                );

                hd.setTrangThai(
                    rs.getString("trang_thai")
                );

                list.add(hd);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }


    // =========================
    // LẤY THEO MÃ
    // =========================
    public HoaDonDTO getById(int maHd) {

        String sql = """
                     SELECT *
                     FROM hoa_don
                     WHERE ma_hd = ?
                     """;

        try (
            Connection conn = MyConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, maHd);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                HoaDonDTO hd = new HoaDonDTO();

                hd.setMaHd(rs.getInt("ma_hd"));

                int maKh = rs.getInt("ma_kh");

                if (rs.wasNull()) {
                    hd.setMaKh(null);
                } else {
                    hd.setMaKh(maKh);
                }

                hd.setMaNv(rs.getInt("ma_nv"));

                Timestamp timestamp =
                        rs.getTimestamp("ngay_lap");

                if (timestamp != null) {
                    hd.setNgayLap(
                        timestamp.toLocalDateTime()
                    );
                }

                hd.setTongTien(
                    rs.getBigDecimal("tong_tien")
                );

                hd.setTienGiam(
                    rs.getBigDecimal("tien_giam")
                );

                hd.setThanhTien(
                    rs.getBigDecimal("thanh_tien")
                );

                hd.setTrangThai(
                    rs.getString("trang_thai")
                );

                return hd;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    // =========================
    // THÊM
    // =========================
    public int insert(HoaDonDTO hd) {

        String sql = """
                     INSERT INTO hoa_don
                     (
                         ma_kh,
                         ma_nv,
                         ngay_lap,
                         tong_tien,
                         tien_giam,
                         thanh_tien,
                         trang_thai
                     )
                     VALUES (?, ?, ?, ?, ?, ?, ?)
                     """;

        try (
            Connection conn = MyConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(
                sql,
                Statement.RETURN_GENERATED_KEYS
            )
        ) {

            if (hd.getMaKh() == null) {
                ps.setNull(1, Types.INTEGER);
            } else {
                ps.setInt(1, hd.getMaKh());
            }

            ps.setInt(2, hd.getMaNv());

            if (hd.getNgayLap() != null) {
                ps.setTimestamp(
                    3,
                    Timestamp.valueOf(hd.getNgayLap())
                );
            } else {
                ps.setTimestamp(
                    3,
                    new Timestamp(System.currentTimeMillis())
                );
            }

            ps.setBigDecimal(4, hd.getTongTien());
            ps.setBigDecimal(5, hd.getTienGiam());
            ps.setBigDecimal(6, hd.getThanhTien());
            ps.setString(7, hd.getTrangThai());

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
    // CẬP NHẬT TRẠNG THÁI
    // =========================
    public boolean updateTrangThai(
            int maHd,
            String trangThai) {

        String sql = """
                     UPDATE hoa_don
                     SET trang_thai = ?
                     WHERE ma_hd = ?
                     """;

        try (
            Connection conn = MyConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setString(1, trangThai);
            ps.setInt(2, maHd);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }


    // =========================
    // XÓA
    // =========================
    public boolean delete(int maHd) {

        String sql = """
                     DELETE FROM hoa_don
                     WHERE ma_hd = ?
                     """;

        try (
            Connection conn = MyConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, maHd);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public int insert(
            Connection conn,
            HoaDonDTO hd) throws Exception {

        String sql = """
            INSERT INTO hoadon
            (makh, manv, ngaylap,
             tongtien, tiengiam, thanhtien, trangthai)
            VALUES (?, ?, ?, ?, ?, ?, ?)
        """;

        try (PreparedStatement ps =
                     conn.prepareStatement(
                             sql,
                             Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, hd.getMaKh());
            ps.setInt(2, hd.getMaNv());

            ps.setObject(
                    3,
                    hd.getNgayLap()
            );

            ps.setBigDecimal(
                    4,
                    hd.getTongTien()
            );

            ps.setBigDecimal(
                    5,
                    hd.getTienGiam()
            );

            ps.setBigDecimal(
                    6,
                    hd.getThanhTien()
            );

            ps.setString(
                    7,
                    hd.getTrangThai()
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