package com.example.appthitracnghiem.Model;

public class Lop {
    private int IDLop;
    private String TenLop;

    public Lop() {
    }

    public Lop(int IDLop, String tenLop) {
        this.IDLop = IDLop;
        TenLop = tenLop;
    }

    public int getIDLop() {
        return IDLop;
    }

    public void setIDLop(int IDLop) {
        this.IDLop = IDLop;
    }

    public String getTenLop() {
        return TenLop;
    }

    public void setTenLop(String tenLop) {
        TenLop = tenLop;
    }
}
