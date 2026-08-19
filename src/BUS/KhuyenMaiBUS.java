package BUS;

import DAO.KhuyenMaiDAO;
import DTO.KhuyenMaiDTO;

import java.util.ArrayList;

public class KhuyenMaiBUS {

    private final KhuyenMaiDAO dao =
            new KhuyenMaiDAO();

    public ArrayList<KhuyenMaiDTO> getAll() {
        return dao.getAll();
    }

    public KhuyenMaiDTO getById(int maKm) {
        return dao.getById(maKm);
    }

    public boolean insert(KhuyenMaiDTO km) {

        if (km == null) {
            return false;
        }

        if (km.getTenKm() == null ||
            km.getTenKm().trim().isEmpty()) {
            return false;
        }

        if (km.getGiaTriGiam() == null ||
            km.getGiaTriGiam().signum() < 0) {
            return false;
        }

        if (km.getNgayBatDau() == null ||
            km.getNgayKetThuc() == null) {
            return false;
        }

        if (km.getNgayKetThuc()
              .isBefore(km.getNgayBatDau())) {
            return false;
        }

        return dao.insert(km);
    }

    public boolean update(KhuyenMaiDTO km) {

        if (km == null || km.getMaKm() <= 0) {
            return false;
        }

        if (km.getNgayBatDau() != null &&
            km.getNgayKetThuc() != null &&
            km.getNgayKetThuc()
              .isBefore(km.getNgayBatDau())) {

            return false;
        }

        return dao.update(km);
    }

    public boolean delete(int maKm) {

        if (maKm <= 0) {
            return false;
        }

        return dao.delete(maKm);
    }
}