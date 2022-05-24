package com.example.appthitracnghiem.Model;

public class ChiTietBaiLam {
    String soCauHoi;
    int linkAnh;
    String cauHoi;
    String dapAnChon;

    public ChiTietBaiLam() {
    }

    public ChiTietBaiLam(String soCauHoi, int linkAnh) {
        this.soCauHoi = soCauHoi;
        this.linkAnh = linkAnh;
    }


    public ChiTietBaiLam(String soCauHoi, String cauHoi, String dapAnChon) {
        this.soCauHoi = soCauHoi;
        this.cauHoi = cauHoi;
        this.dapAnChon = dapAnChon;
    }

    public String getSoCauHoi() {
        return soCauHoi;
    }

    public void setSoCauHoi(String soCauHoi) {
        this.soCauHoi = soCauHoi;
    }

    public int getLinkAnh() {
        return linkAnh;
    }

    public void setLinkAnh(int linkAnh) {
        this.linkAnh = linkAnh;
    }

    public String getCauHoi() {
        return cauHoi;
    }

    public void setCauHoi(String cauHoi) {
        this.cauHoi = cauHoi;
    }

    public String getDapAnChon() {
        return dapAnChon;
    }

    public void setDapAnChon(String dapAnChon) {
        this.dapAnChon = dapAnChon;
    }
}
