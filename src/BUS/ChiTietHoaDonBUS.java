package BUS;

import DAO.ChiTietHoaDonDAO;
import DTO.ChiTietHoaDonDTO;

import java.util.ArrayList;

public class ChiTietHoaDonBUS {

    private final ChiTietHoaDonDAO dao =
            new ChiTietHoaDonDAO();

    public ArrayList<ChiTietHoaDonDTO> getByHoaDon(
            int maHd) {

        return dao.getByHoaDon(maHd);
    }

    public boolean insert(ChiTietHoaDonDTO ct) {

        if (ct == null) {
            return false;
        }

        if (ct.getMaHd() <= 0 ||
            ct.getMaSp() <= 0) {
            return false;
        }

        if (ct.getSoLuong() <= 0) {
            return false;
        }

        if (ct.getDonGia() == null ||
            ct.getDonGia().signum() < 0) {
            return false;
        }

        return dao.insert(ct);
    }

    public boolean delete(int maHd, int maSp) {

        if (maHd <= 0 || maSp <= 0) {
            return false;
        }

        return dao.delete(maHd, maSp);
    }
}