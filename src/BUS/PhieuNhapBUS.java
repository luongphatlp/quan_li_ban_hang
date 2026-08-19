package BUS;

import DAO.PhieuNhapDAO;
import DTO.PhieuNhapDTO;

import java.util.ArrayList;

public class PhieuNhapBUS {

    private final PhieuNhapDAO dao =
            new PhieuNhapDAO();

    public ArrayList<PhieuNhapDTO> getAll() {
        return dao.getAll();
    }

    public PhieuNhapDTO getById(int maPn) {
        return dao.getById(maPn);
    }

    public int insert(PhieuNhapDTO pn) {

        if (pn == null) {
            return -1;
        }

        if (pn.getMaNcc() <= 0 ||
            pn.getMaNv() <= 0) {
            return -1;
        }

        if (pn.getTongTien() == null ||
            pn.getTongTien().signum() < 0) {
            return -1;
        }

        return dao.insert(pn);
    }

    public boolean update(PhieuNhapDTO pn) {

        if (pn == null || pn.getMaPn() <= 0) {
            return false;
        }

        return dao.update(pn);
    }

    public boolean delete(int maPn) {

        if (maPn <= 0) {
            return false;
        }

        return dao.delete(maPn);
    }
}