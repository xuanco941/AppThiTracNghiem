package com.example.appthitracnghiem.Model;

public class HocSinh {
    private int IDHocSinh,IDLop;
    private String TenHocSinh,GioiTinh,UserName,Password;

    public HocSinh(int IDHocSinh, int IDLop, String tenHocSinh, String gioiTinh, String userName, String password) {
        this.IDHocSinh = IDHocSinh;
        this.IDLop = IDLop;
        TenHocSinh = tenHocSinh;
        GioiTinh = gioiTinh;
        UserName = userName;
        Password = password;
    }

    public HocSinh() {
    }

    public int getIDHocSinh() {
        return IDHocSinh;
    }

    public void setIDHocSinh(int IDHocSinh) {
        this.IDHocSinh = IDHocSinh;
    }

    public int getIDLop() {
        return IDLop;
    }

    public void setIDLop(int IDLop) {
        this.IDLop = IDLop;
    }

    public String getTenHocSinh() {
        return TenHocSinh;
    }

    public void setTenHocSinh(String tenHocSinh) {
        TenHocSinh = tenHocSinh;
    }

    public String getGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        GioiTinh = gioiTinh;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
