package DTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class HoaDonDTO {
    private int maHd;
    private Integer maKh;
    private int maNv;
    private LocalDateTime ngayLap;
    private BigDecimal tongTien;
    private BigDecimal tienGiam;
    private BigDecimal thanhTien;
    private String trangThai;

    public HoaDonDTO() {
    }

    public HoaDonDTO(int maHd, Integer maKh, int maNv,
                     LocalDateTime ngayLap,
                     BigDecimal tongTien,
                     BigDecimal tienGiam,
                     BigDecimal thanhTien,
                     String trangThai) {
        this.maHd = maHd;
        this.maKh = maKh;
        this.maNv = maNv;
        this.ngayLap = ngayLap;
        this.tongTien = tongTien;
        this.tienGiam = tienGiam;
        this.thanhTien = thanhTien;
        this.trangThai = trangThai;
    }

    public int getMaHd() {
        return maHd;
    }

    public void setMaHd(int maHd) {
        this.maHd = maHd;
    }

    public Integer getMaKh() {
        return maKh;
    }

    public void setMaKh(Integer maKh) {
        this.maKh = maKh;
    }

    public int getMaNv() {
        return maNv;
    }

    public void setMaNv(int maNv) {
        this.maNv = maNv;
    }

    public LocalDateTime getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(LocalDateTime ngayLap) {
        this.ngayLap = ngayLap;
    }

    public BigDecimal getTongTien() {
        return tongTien;
    }

    public void setTongTien(BigDecimal tongTien) {
        this.tongTien = tongTien;
    }

    public BigDecimal getTienGiam() {
        return tienGiam;
    }

    public void setTienGiam(BigDecimal tienGiam) {
        this.tienGiam = tienGiam;
    }

    public BigDecimal getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(BigDecimal thanhTien) {
        this.thanhTien = thanhTien;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
}