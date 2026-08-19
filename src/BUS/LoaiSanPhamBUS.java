package BUS;

import DAO.LoaiSanPhamDAO;
import DTO.LoaiSanPhamDTO;

import java.util.ArrayList;

public class LoaiSanPhamBUS {

    private final LoaiSanPhamDAO dao = new LoaiSanPhamDAO();

    public ArrayList<LoaiSanPhamDTO> getAll() {
        return dao.getAll();
    }

    public LoaiSanPhamDTO getById(int maLoai) {
        return dao.getById(maLoai);
    }

    public boolean insert(LoaiSanPhamDTO lsp) {

        if (lsp == null) {
            return false;
        }

        if (lsp.getTenLoai() == null ||
            lsp.getTenLoai().trim().isEmpty()) {
            return false;
        }

        return dao.insert(lsp);
    }

    public boolean update(LoaiSanPhamDTO lsp) {

        if (lsp == null || lsp.getMaLoai() <= 0) {
            return false;
        }

        if (lsp.getTenLoai() == null ||
            lsp.getTenLoai().trim().isEmpty()) {
            return false;
        }

        return dao.update(lsp);
    }

    public boolean delete(int maLoai) {

        if (maLoai <= 0) {
            return false;
        }

        return dao.delete(maLoai);
    }


}