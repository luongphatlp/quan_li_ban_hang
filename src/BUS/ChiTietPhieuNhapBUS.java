package BUS;

import DAO.ChiTietPhieuNhapDAO;
import DTO.ChiTietPhieuNhapDTO;

import java.util.ArrayList;

public class ChiTietPhieuNhapBUS {

    private final ChiTietPhieuNhapDAO dao =
            new ChiTietPhieuNhapDAO();

    public ArrayList<ChiTietPhieuNhapDTO> getByPhieuNhap(
            int maPn) {

        return dao.getByPhieuNhap(maPn);
    }

    public boolean insert(ChiTietPhieuNhapDTO ct) {

        if (ct == null) {
            return false;
        }

        if (ct.getMaPn() <= 0 ||
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

    public boolean delete(int maPn, int maSp) {

        if (maPn <= 0 || maSp <= 0) {
            return false;
        }

        return dao.delete(maPn, maSp);
    }
}