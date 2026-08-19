package DTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PhieuNhapDTO {
    private int maPn;
    private int maNcc;
    private int maNv;
    private LocalDateTime ngayNhap;
    private BigDecimal tongTien;
    private int trangThai;

    public PhieuNhapDTO() {
    }

    public PhieuNhapDTO(int maPn, int maNcc, int maNv,
                        LocalDateTime ngayNhap,
                        BigDecimal tongTien, int trangThai) {
        this.maPn = maPn;
        this.maNcc = maNcc;
        this.maNv = maNv;
        this.ngayNhap = ngayNhap;
        this.tongTien = tongTien;
        this.trangThai = trangThai;
    }

    public int getMaPn() {
        return maPn;
    }

    public void setMaPn(int maPn) {
        this.maPn = maPn;
    }

    public int getMaNcc() {
        return maNcc;
    }

    public void setMaNcc(int maNcc) {
        this.maNcc = maNcc;
    }

    public int getMaNv() {
        return maNv;
    }

    public void setMaNv(int maNv) {
        this.maNv = maNv;
    }

    public LocalDateTime getNgayNhap() {
        return ngayNhap;
    }

    public void setNgayNhap(LocalDateTime ngayNhap) {
        this.ngayNhap = ngayNhap;
    }

    public BigDecimal getTongTien() {
        return tongTien;
    }

    public void setTongTien(BigDecimal tongTien) {
        this.tongTien = tongTien;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }
}