package BUS;

import DAO.ChiTietHoaDonDAO;
import DAO.HoaDonDAO;
import DAO.MyConnection;
import DAO.SanPhamDAO;
import DTO.ChiTietHoaDonDTO;
import DTO.HoaDonDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class BanHangBUS {

    private final HoaDonDAO hoaDonDAO =
            new HoaDonDAO();

    private final ChiTietHoaDonDAO chiTietDAO =
            new ChiTietHoaDonDAO();

    private final SanPhamDAO sanPhamDAO =
            new SanPhamDAO();

    public boolean banHang(
            HoaDonDTO hoaDon,
            ArrayList<ChiTietHoaDonDTO> danhSachChiTiet) {

        if (hoaDon == null ||
            danhSachChiTiet == null ||
            danhSachChiTiet.isEmpty()) {

            return false;
        }

        Connection conn = null;

        try {

            conn = MyConnection.getConnection();

            if (conn == null) {
                return false;
            }

            // =========================================
            // Bắt đầu transaction
            // =========================================

            conn.setAutoCommit(false);

            // =========================================
            // 1. Kiểm tra dữ liệu
            // =========================================

            for (ChiTietHoaDonDTO ct :
                    danhSachChiTiet) {

                if (ct == null) {
                    throw new Exception(
                            "Chi tiết hóa đơn không hợp lệ"
                    );
                }

                if (ct.getMaSp() <= 0) {
                    throw new Exception(
                            "Mã sản phẩm không hợp lệ"
                    );
                }

                if (ct.getSoLuong() <= 0) {
                    throw new Exception(
                            "Số lượng bán phải > 0"
                    );
                }

                if (ct.getDonGia() == null ||
                    ct.getDonGia().signum() < 0) {

                    throw new Exception(
                            "Đơn giá không hợp lệ"
                    );
                }

                // Kiểm tra tồn kho
                int tonKho =
                        sanPhamDAO.getSoLuong(
                                conn,
                                ct.getMaSp()
                        );

                if (tonKho < 0) {
                    throw new Exception(
                            "Không tìm thấy sản phẩm: "
                            + ct.getMaSp()
                    );
                }

                if (tonKho < ct.getSoLuong()) {

                    throw new Exception(
                            "Sản phẩm "
                            + ct.getMaSp()
                            + " không đủ tồn kho. "
                            + "Tồn: "
                            + tonKho
                            + ", bán: "
                            + ct.getSoLuong()
                    );
                }
            }

            // =========================================
            // 2. Tạo hóa đơn
            // =========================================

            int maHd =
                    hoaDonDAO.insert(
                            conn,
                            hoaDon
                    );

            if (maHd <= 0) {
                throw new Exception(
                        "Không thể tạo hóa đơn"
                );
            }

            // =========================================
            // 3. Thêm chi tiết + trừ kho
            // =========================================

            for (ChiTietHoaDonDTO ct :
                    danhSachChiTiet) {

                ct.setMaHd(maHd);

                // Thêm chi tiết hóa đơn
                boolean insertDetail =
                        chiTietDAO.insert(
                                conn,
                                ct
                        );

                if (!insertDetail) {
                    throw new Exception(
                            "Không thể thêm chi tiết hóa đơn"
                    );
                }

                // Trừ tồn kho
                boolean updateStock =
                        sanPhamDAO.truSoLuong(
                                conn,
                                ct.getMaSp(),
                                ct.getSoLuong()
                        );

                if (!updateStock) {
                    throw new Exception(
                            "Không thể trừ tồn kho"
                    );
                }
            }

            // =========================================
            // 4. Tất cả thành công
            // =========================================

            conn.commit();

            return true;

        } catch (Exception e) {

            // =========================================
            // Có lỗi → rollback toàn bộ
            // =========================================

            if (conn != null) {

                try {
                    conn.rollback();

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }

            e.printStackTrace();

            return false;

        } finally {

            if (conn != null) {

                try {

                    conn.setAutoCommit(true);
                    conn.close();

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}