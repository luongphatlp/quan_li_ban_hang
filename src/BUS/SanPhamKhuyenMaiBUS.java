package BUS;

import DAO.SanPhamKhuyenMaiDAO;
import DTO.SanPhamKhuyenMaiDTO;

import java.util.ArrayList;

public class SanPhamKhuyenMaiBUS {

    private final SanPhamKhuyenMaiDAO dao =
            new SanPhamKhuyenMaiDAO();

    public ArrayList<SanPhamKhuyenMaiDTO> getAll() {
        return dao.getAll();
    }

    public boolean insert(SanPhamKhuyenMaiDTO spkm) {

        if (spkm == null) {
            return false;
        }

        if (spkm.getMaKm() <= 0 ||
            spkm.getMaSp() <= 0) {
            return false;
        }

        return dao.insert(spkm);
    }

    public boolean delete(int maKm, int maSp) {

        if (maKm <= 0 || maSp <= 0) {
            return false;
        }

        return dao.delete(maKm, maSp);
    }

    public boolean deleteByKhuyenMai(int maKm) {

        if (maKm <= 0) {
            return false;
        }

        return dao.deleteByKhuyenMai(maKm);
    }

    public ArrayList<Integer> getSanPhamByKhuyenMai(
            int maKm) {

        return dao.getSanPhamByKhuyenMai(maKm);
    }
}