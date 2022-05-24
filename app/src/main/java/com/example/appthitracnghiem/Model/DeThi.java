package com.example.appthitracnghiem.Model;

public class DeThi {
    private int IDDeThi,ThoiGianLamBai,IDMonThi,IDLop,SoLuongCauHoi;
    private String TenDeThi,ThoiGianThi;

    public DeThi() {
    }

    public DeThi(int IDDeThi, int thoiGianLamBai, int IDMonThi, int IDLop, int soLuongCauHoi, String tenDeThi, String thoiGianThi) {
        this.IDDeThi = IDDeThi;
        ThoiGianLamBai = thoiGianLamBai;
        this.IDMonThi = IDMonThi;
        this.IDLop = IDLop;
        SoLuongCauHoi = soLuongCauHoi;
        TenDeThi = tenDeThi;
        ThoiGianThi = thoiGianThi;
    }

    public int getIDDeThi() {
        return IDDeThi;
    }

    public void setIDDeThi(int IDDeThi) {
        this.IDDeThi = IDDeThi;
    }

    public int getThoiGianLamBai() {
        return ThoiGianLamBai;
    }

    public void setThoiGianLamBai(int thoiGianLamBai) {
        ThoiGianLamBai = thoiGianLamBai;
    }

    public int getIDMonThi() {
        return IDMonThi;
    }

    public void setIDMonThi(int IDMonThi) {
        this.IDMonThi = IDMonThi;
    }

    public int getIDLop() {
        return IDLop;
    }

    public void setIDLop(int IDLop) {
        this.IDLop = IDLop;
    }

    public int getSoLuongCauHoi() {
        return SoLuongCauHoi;
    }

    public void setSoLuongCauHoi(int soLuongCauHoi) {
        SoLuongCauHoi = soLuongCauHoi;
    }

    public String getTenDeThi() {
        return TenDeThi;
    }

    public void setTenDeThi(String tenDeThi) {
        TenDeThi = tenDeThi;
    }

    public String getThoiGianThi() {
        return ThoiGianThi;
    }

    public void setThoiGianThi(String thoiGianThi) {
        ThoiGianThi = thoiGianThi;
    }
}
