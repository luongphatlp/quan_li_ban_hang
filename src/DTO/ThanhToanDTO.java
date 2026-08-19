package DTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ThanhToanDTO {
    private int maTt;
    private int maHd;
    private String phuongThuc;
    private BigDecimal soTien;
    private LocalDateTime ngayThanhToan;
    private String trangThai;

    public ThanhToanDTO() {
    }

    public ThanhToanDTO(int maTt, int maHd,
                        String phuongThuc,
                        BigDecimal soTien,
                        LocalDateTime ngayThanhToan,
                        String trangThai) {
        this.maTt = maTt;
        this.maHd = maHd;
        this.phuongThuc = phuongThuc;
        this.soTien = soTien;
        this.ngayThanhToan = ngayThanhToan;
        this.trangThai = trangThai;
    }

    public int getMaTt() {
        return maTt;
    }

    public void setMaTt(int maTt) {
        this.maTt = maTt;
    }

    public int getMaHd() {
        return maHd;
    }

    public void setMaHd(int maHd) {
        this.maHd = maHd;
    }

    public String getPhuongThuc() {
        return phuongThuc;
    }

    public void setPhuongThuc(String phuongThuc) {
        this.phuongThuc = phuongThuc;
    }

    public BigDecimal getSoTien() {
        return soTien;
    }

    public void setSoTien(BigDecimal soTien) {
        this.soTien = soTien;
    }

    public LocalDateTime getNgayThanhToan() {
        return ngayThanhToan;
    }

    public void setNgayThanhToan(LocalDateTime ngayThanhToan) {
        this.ngayThanhToan = ngayThanhToan;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
}