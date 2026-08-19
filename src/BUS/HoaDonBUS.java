package BUS;

import DAO.HoaDonDAO;
import DTO.HoaDonDTO;

import java.util.ArrayList;

public class HoaDonBUS {

    private final HoaDonDAO dao =
            new HoaDonDAO();

    public ArrayList<HoaDonDTO> getAll() {
        return dao.getAll();
    }

    public HoaDonDTO getById(int maHd) {
        return dao.getById(maHd);
    }

    public int insert(HoaDonDTO hd) {

        if (hd == null) {
            return -1;
        }

        if (hd.getMaNv() <= 0) {
            return -1;
        }

        if (hd.getTongTien() == null ||
            hd.getTongTien().signum() < 0) {
            return -1;
        }

        if (hd.getTienGiam() == null ||
            hd.getTienGiam().signum() < 0) {
            return -1;
        }

        if (hd.getThanhTien() == null ||
            hd.getThanhTien().signum() < 0) {
            return -1;
        }

        return dao.insert(hd);
    }

    public boolean updateTrangThai(
            int maHd,
            String trangThai) {

        if (maHd <= 0) {
            return false;
        }

        if (trangThai == null ||
            trangThai.trim().isEmpty()) {
            return false;
        }

        return dao.updateTrangThai(
            maHd,
            trangThai
        );
    }

    public boolean delete(int maHd) {

        if (maHd <= 0) {
            return false;
        }

        return dao.delete(maHd);
    }
}