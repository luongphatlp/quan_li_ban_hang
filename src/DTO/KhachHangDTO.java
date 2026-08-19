package DTO;

import java.time.LocalDateTime;

public class KhachHangDTO {
    private int maKh;
    private String hoTen;
    private String soDienThoai;
    private String email;
    private String diaChi;
    private LocalDateTime ngayTao;
    private int trangThai;

    public KhachHangDTO() {
    }

    public KhachHangDTO(int maKh, String hoTen, String soDienThoai,
                        String email, String diaChi,
                        LocalDateTime ngayTao, int trangThai) {
        this.maKh = maKh;
        this.hoTen = hoTen;
        this.soDienThoai = soDienThoai;
        this.email = email;
        this.diaChi = diaChi;
        this.ngayTao = ngayTao;
        this.trangThai = trangThai;
    }

    public int getMaKh() {
        return maKh;
    }

    public void setMaKh(int maKh) {
        this.maKh = maKh;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public LocalDateTime getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(LocalDateTime ngayTao) {
        this.ngayTao = ngayTao;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }
}