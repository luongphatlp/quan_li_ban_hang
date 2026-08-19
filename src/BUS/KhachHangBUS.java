package BUS;

import DAO.KhachHangDAO;
import DTO.KhachHangDTO;

import java.util.ArrayList;

public class KhachHangBUS {

    private final KhachHangDAO dao = new KhachHangDAO();

    public ArrayList<KhachHangDTO> getAll() {
        return dao.getAll();
    }

    public KhachHangDTO getById(int maKh) {
        return dao.getById(maKh);
    }

    public boolean insert(KhachHangDTO kh) {

        if (kh == null) {
            return false;
        }

        if (kh.getHoTen() == null ||
            kh.getHoTen().trim().isEmpty()) {
            return false;
        }

        if (kh.getSoDienThoai() == null ||
            kh.getSoDienThoai().trim().isEmpty()) {
            return false;
        }

        return dao.insert(kh);
    }

    public boolean update(KhachHangDTO kh) {

        if (kh == null || kh.getMaKh() <= 0) {
            return false;
        }

        if (kh.getHoTen() == null ||
            kh.getHoTen().trim().isEmpty()) {
            return false;
        }

        return dao.update(kh);
    }

    public boolean delete(int maKh) {

        if (maKh <= 0) {
            return false;
        }

        return dao.delete(maKh);
    }

    public ArrayList<KhachHangDTO> search(String keyword) {

        if (keyword == null) {
            keyword = "";
        }

        return dao.search(keyword);
    }
}