package DAO;

import DTO.SanPhamDTO;
import java.util.ArrayList;

public class TestDAO {

    public static void main(String[] args) {

        SanPhamDAO dao = new SanPhamDAO();

        ArrayList<SanPhamDTO> list = dao.getAll();

        for (SanPhamDTO sp : list) {
            System.out.println(
                sp.getMaSp() + " | "
                + sp.getTenSp() + " | "
                + sp.getDonGia() + " | "
                + sp.getSoLuong()
            );
        }
    }
}