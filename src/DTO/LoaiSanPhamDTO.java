package DTO;

public class LoaiSanPhamDTO {
    private int maLoai;
    private String tenLoai;
    private int trangThai;

    public LoaiSanPhamDTO() {
    }

    public LoaiSanPhamDTO(int maLoai, String tenLoai, int trangThai) {
        this.maLoai = maLoai;
        this.tenLoai = tenLoai;
        this.trangThai = trangThai;
    }

    public int getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(int maLoai) {
        this.maLoai = maLoai;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        return tenLoai;
    }
}