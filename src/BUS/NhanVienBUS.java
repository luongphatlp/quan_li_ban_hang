package BUS;

import DAO.NhanVienDAO;
import DTO.NhanVienDTO;

import java.util.ArrayList;

public class NhanVienBUS {

    private final NhanVienDAO dao = new NhanVienDAO();

    public ArrayList<NhanVienDTO> getAll() {
        return dao.getAll();
    }

    public boolean insert(NhanVienDTO nv) {

        if (nv == null) {
            return false;
        }

        if (nv.getHoTen() == null ||
            nv.getHoTen().trim().isEmpty()) {
            return false;
        }

        if (nv.getTaiKhoan() == null ||
            nv.getTaiKhoan().trim().isEmpty()) {
            return false;
        }

        if (nv.getMatKhau() == null ||
            nv.getMatKhau().trim().isEmpty()) {
            return false;
        }

        return dao.insert(nv);
    }

    public boolean update(NhanVienDTO nv) {

        if (nv == null || nv.getMaNv() <= 0) {
            return false;
        }

        return dao.update(nv);
    }

    public boolean delete(int maNv) {

        if (maNv <= 0) {
            return false;
        }

        return dao.delete(maNv);
    }

    public NhanVienDTO login(
            String taiKhoan,
            String matKhau) {

        if (taiKhoan == null ||
            taiKhoan.trim().isEmpty()) {
            return null;
        }

        if (matKhau == null ||
            matKhau.trim().isEmpty()) {
            return null;
        }

        return dao.login(taiKhoan, matKhau);
    }
}