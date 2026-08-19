package BUS;

import DAO.NhaCungCapDAO;
import DTO.NhaCungCapDTO;

import java.util.ArrayList;

public class NhaCungCapBUS {

    private final NhaCungCapDAO dao =
            new NhaCungCapDAO();

    public ArrayList<NhaCungCapDTO> getAll() {
        return dao.getAll();
    }

    public NhaCungCapDTO getById(int maNcc) {
        return dao.getById(maNcc);
    }

    public boolean insert(NhaCungCapDTO ncc) {

        if (ncc == null) {
            return false;
        }

        if (ncc.getTenNcc() == null ||
            ncc.getTenNcc().trim().isEmpty()) {
            return false;
        }

        return dao.insert(ncc);
    }

    public boolean update(NhaCungCapDTO ncc) {

        if (ncc == null || ncc.getMaNcc() <= 0) {
            return false;
        }

        return dao.update(ncc);
    }

    public boolean delete(int maNcc) {

        if (maNcc <= 0) {
            return false;
        }

        return dao.delete(maNcc);
    }

    public ArrayList<NhaCungCapDTO> search(
            String keyword) {

        if (keyword == null) {
            keyword = "";
        }

        return dao.search(keyword);
    }
}