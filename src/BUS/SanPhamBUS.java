package BUS;

import DAO.SanPhamDAO;
import DTO.SanPhamDTO;

import java.util.ArrayList;

public class SanPhamBUS {

    private final SanPhamDAO dao = new SanPhamDAO();

    public ArrayList<SanPhamDTO> getAll() {
        return dao.getAll();
    }

    public SanPhamDTO getById(int maSp) {
        return dao.getById(maSp);
    }

    public boolean insert(SanPhamDTO sp) {

        if (sp == null) {
            return false;
        }

        if (sp.getTenSp() == null ||
            sp.getTenSp().trim().isEmpty()) {
            return false;
        }

        if (sp.getMaLoai() <= 0) {
            return false;
        }

        if (sp.getDonGia() == null ||
            sp.getDonGia().signum() < 0) {
            return false;
        }

        if (sp.getSoLuong() < 0) {
            return false;
        }

        return dao.insert(sp);
    }

    public boolean update(SanPhamDTO sp) {

        if (sp == null || sp.getMaSp() <= 0) {
            return false;
        }

        if (sp.getTenSp() == null ||
            sp.getTenSp().trim().isEmpty()) {
            return false;
        }

        if (sp.getDonGia() == null ||
            sp.getDonGia().signum() < 0) {
            return false;
        }

        if (sp.getSoLuong() < 0) {
            return false;
        }

        return dao.update(sp);
    }

    public boolean delete(int maSp) {

        if (maSp <= 0) {
            return false;
        }

        return dao.delete(maSp);
    }


}