package DTO;

public class NhaCungCapDTO {
    private int maNcc;
    private String tenNcc;
    private String soDienThoai;
    private String email;
    private String diaChi;
    private int trangThai;

    public NhaCungCapDTO() {
    }

    public NhaCungCapDTO(int maNcc, String tenNcc,
                         String soDienThoai, String email,
                         String diaChi, int trangThai) {
        this.maNcc = maNcc;
        this.tenNcc = tenNcc;
        this.soDienThoai = soDienThoai;
        this.email = email;
        this.diaChi = diaChi;
        this.trangThai = trangThai;
    }

    public int getMaNcc() {
        return maNcc;
    }

    public void setMaNcc(int maNcc) {
        this.maNcc = maNcc;
    }

    public String getTenNcc() {
        return tenNcc;
    }

    public void setTenNcc(String tenNcc) {
        this.tenNcc = tenNcc;
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

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }
}