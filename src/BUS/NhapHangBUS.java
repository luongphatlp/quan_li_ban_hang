package BUS;

import DAO.ChiTietPhieuNhapDAO;
import DAO.MyConnection;
import DAO.PhieuNhapDAO;
import DAO.SanPhamDAO;
import DTO.ChiTietPhieuNhapDTO;
import DTO.PhieuNhapDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class NhapHangBUS {

    private final PhieuNhapDAO phieuNhapDAO =
            new PhieuNhapDAO();

    private final ChiTietPhieuNhapDAO chiTietDAO =
            new ChiTietPhieuNhapDAO();

    private final SanPhamDAO sanPhamDAO =
            new SanPhamDAO();

    public boolean nhapHang(
            PhieuNhapDTO phieuNhap,
            ArrayList<ChiTietPhieuNhapDTO> danhSachChiTiet) {

        if (phieuNhap == null ||
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

            // Bắt đầu transaction
            conn.setAutoCommit(false);

            // =========================================
            // 1. Kiểm tra dữ liệu chi tiết
            // =========================================

            for (ChiTietPhieuNhapDTO ct :
                    danhSachChiTiet) {

                if (ct == null) {
                    throw new Exception(
                            "Chi tiết phiếu nhập không hợp lệ"
                    );
                }

                if (ct.getMaSp() <= 0) {
                    throw new Exception(
                            "Mã sản phẩm không hợp lệ"
                    );
                }

                if (ct.getSoLuong() <= 0) {
                    throw new Exception(
                            "Số lượng nhập phải > 0"
                    );
                }

                if (ct.getDonGia() == null ||
                    ct.getDonGia().signum() < 0) {

                    throw new Exception(
                            "Đơn giá không hợp lệ"
                    );
                }
            }

            // =========================================
            // 2. Tạo phiếu nhập
            // =========================================

            int maPn =
                    phieuNhapDAO.insert(
                            conn,
                            phieuNhap
                    );

            if (maPn <= 0) {
                throw new Exception(
                        "Không thể tạo phiếu nhập"
                );
            }

            // =========================================
            // 3. Thêm chi tiết + cộng tồn kho
            // =========================================

            for (ChiTietPhieuNhapDTO ct :
                    danhSachChiTiet) {

                ct.setMaPn(maPn);

                // Thêm chi tiết
                boolean insertDetail =
                        chiTietDAO.insert(
                                conn,
                                ct
                        );

                if (!insertDetail) {
                    throw new Exception(
                            "Không thể thêm chi tiết phiếu nhập"
                    );
                }

                // Cộng tồn kho
                boolean updateStock =
                        sanPhamDAO.congSoLuong(
                                conn,
                                ct.getMaSp(),
                                ct.getSoLuong()
                        );

                if (!updateStock) {
                    throw new Exception(
                            "Không thể cập nhật tồn kho"
                    );
                }
            }

            // =========================================
            // 4. Mọi thứ thành công
            // =========================================

            conn.commit();

            return true;

        } catch (Exception e) {

            // =========================================
            // Có lỗi → rollback
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