package DTO;

import java.math.BigDecimal;

public class SanPhamDTO {
    private int maSp;
    private String tenSp;
    private int maLoai;
    private BigDecimal donGia;
    private int soLuong;
    private String donViTinh;
    private String moTa;
    private int trangThai;

    public SanPhamDTO() {
    }

    public SanPhamDTO(int maSp, String tenSp, int maLoai,
                      BigDecimal donGia, int soLuong,
                      String donViTinh, String moTa, int trangThai) {
        this.maSp = maSp;
        this.tenSp = tenSp;
        this.maLoai = maLoai;
        this.donGia = donGia;
        this.soLuong = soLuong;
        this.donViTinh = donViTinh;
        this.moTa = moTa;
        this.trangThai = trangThai;
    }

    public int getMaSp() {
        return maSp;
    }

    public void setMaSp(int maSp) {
        this.maSp = maSp;
    }

    public String getTenSp() {
        return tenSp;
    }

    public void setTenSp(String tenSp) {
        this.tenSp = tenSp;
    }

    public int getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(int maLoai) {
        this.maLoai = maLoai;
    }

    public BigDecimal getDonGia() {
        return donGia;
    }

    public void setDonGia(BigDecimal donGia) {
        this.donGia = donGia;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getDonViTinh() {
        return donViTinh;
    }

    public void setDonViTinh(String donViTinh) {
        this.donViTinh = donViTinh;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        return tenSp;
    }
}