package BUS;

import DAO.ThanhToanDAO;
import DTO.ThanhToanDTO;

import java.util.ArrayList;

public class ThanhToanBUS {

    private final ThanhToanDAO dao =
            new ThanhToanDAO();

    public ArrayList<ThanhToanDTO> getAll() {
        return dao.getAll();
    }

    public ArrayList<ThanhToanDTO> getByHoaDon(
            int maHd) {

        return dao.getByHoaDon(maHd);
    }

    public boolean insert(ThanhToanDTO tt) {

        if (tt == null) {
            return false;
        }

        if (tt.getMaHd() <= 0) {
            return false;
        }

        if (tt.getPhuongThuc() == null ||
            tt.getPhuongThuc().trim().isEmpty()) {
            return false;
        }

        if (tt.getSoTien() == null ||
            tt.getSoTien().signum() < 0) {
            return false;
        }

        return dao.insert(tt);
    }

    public boolean delete(int maTt) {

        if (maTt <= 0) {
            return false;
        }

        return dao.delete(maTt);
    }
}