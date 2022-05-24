package com.example.appthitracnghiem.Model;

public class ChiTietDeThi {
    private int IDDeThi,IDCauHoi,IDHocSinh;
    private String DapAnLuaChon;

    private String chiTietDapAn;
    private String chiTietCauHoi;

    public ChiTietDeThi(int IDDeThi, int IDCauHoi,int IDHocSinh, String dapAnLuaChon) {
        this.IDDeThi = IDDeThi;
        this.IDCauHoi = IDCauHoi;
        this.IDHocSinh=IDHocSinh;
        DapAnLuaChon = dapAnLuaChon;
    }

    public ChiTietDeThi() {
    }

    public int getIDDeThi() {
        return IDDeThi;
    }

    public void setIDDeThi(int IDDeThi) {
        this.IDDeThi = IDDeThi;
    }

    public int getIDCauHoi() {
        return IDCauHoi;
    }

    public void setIDCauHoi(int IDCauHoi) {
        this.IDCauHoi = IDCauHoi;
    }

    public int getIDHocSinh() {
        return IDHocSinh;
    }

    public void setIDHocSinh(int IDHocSinh) {
        this.IDHocSinh = IDHocSinh;
    }

    public String getDapAnLuaChon() {
        return DapAnLuaChon;
    }

    public void setDapAnLuaChon(String dapAnLuaChon) {
        DapAnLuaChon = dapAnLuaChon;
    }

    public String getChiTietDapAn() {
        return chiTietDapAn;
    }

    public void setChiTietDapAn(String chiTietDapAn) {
        this.chiTietDapAn = chiTietDapAn;
    }

    public String getChiTietCauHoi() {
        return chiTietCauHoi;
    }

    public void setChiTietCauHoi(String chiTietCauHoi) {
        this.chiTietCauHoi = chiTietCauHoi;
    }
}
