package BUS;

import DAO.SanPhamDAO;
import DTO.SanPhamDTO;

import java.util.ArrayList;
import java.util.List;

public class SanPhamBUS {

    private final SanPhamDAO dao = new SanPhamDAO();
    private List<SanPhamDTO> ds=new ArrayList<>();
    public void docDanhSach(){
        ds=dao.getAll();
    }
    public List<SanPhamDTO> getDS() {
        return ds;
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