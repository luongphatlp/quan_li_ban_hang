package DTO;

public class SanPhamKhuyenMaiDTO {
    private int maKm;
    private int maSp;

    public SanPhamKhuyenMaiDTO() {
    }

    public SanPhamKhuyenMaiDTO(int maKm, int maSp) {
        this.maKm = maKm;
        this.maSp = maSp;
    }

    public int getMaKm() {
        return maKm;
    }

    public void setMaKm(int maKm) {
        this.maKm = maKm;
    }

    public int getMaSp() {
        return maSp;
    }

    public void setMaSp(int maSp) {
        this.maSp = maSp;
    }
}