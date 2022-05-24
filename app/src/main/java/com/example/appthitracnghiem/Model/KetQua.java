package com.example.appthitracnghiem.Model;

public class KetQua {
    private int IDDeThi,IDHocSinh;
    private double Diem;

    public KetQua(int IDDeThi, int IDHocSinh, double diem) {
        this.IDDeThi = IDDeThi;
        this.IDHocSinh = IDHocSinh;
        Diem = diem;
    }

    public KetQua() {
    }

    public int getIDDeThi() {
        return IDDeThi;
    }

    public void setIDDeThi(int IDDeThi) {
        this.IDDeThi = IDDeThi;
    }

    public int getIDHocSinh() {
        return IDHocSinh;
    }

    public void setIDHocSinh(int IDHocSinh) {
        this.IDHocSinh = IDHocSinh;
    }

    public double getDiem() {
        return Diem;
    }

    public void setDiem(double diem) {
        Diem = diem;
    }
}
