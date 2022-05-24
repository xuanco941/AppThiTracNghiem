package com.example.appthitracnghiem.Model;

public class MonThi {
    private int IDMonThi;
    private String TenMon;
    private byte[] HinhAnh;

    public MonThi(int IDMonThi, String tenMon, byte[] hinhAnh) {
        this.IDMonThi = IDMonThi;
        TenMon = tenMon;
        HinhAnh = hinhAnh;
    }

    public MonThi() {
    }

    public int getIDMonThi() {
        return IDMonThi;
    }

    public void setIDMonThi(int IDMonThi) {
        this.IDMonThi = IDMonThi;
    }

    public String getTenMon() {
        return TenMon;
    }

    public void setTenMon(String tenMon) {
        TenMon = tenMon;
    }

    public byte[] getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(byte[] hinhAnh) {
        HinhAnh = hinhAnh;
    }
}
