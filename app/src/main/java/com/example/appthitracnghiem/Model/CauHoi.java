package com.example.appthitracnghiem.Model;

import java.io.Serializable;

public class CauHoi implements Serializable{
    private int IDCauHoi,IDMonThi,IDLop;
    private String CauHoi,DapAnA,DapAnB,DapAnC,DapAnD,DapAn;

    public CauHoi(int IDCauHoi, int IDMonThi, int IDLop, String cauHoi, String dapAnA, String dapAnB, String dapAnC, String dapAnD, String dapAn) {
        this.IDCauHoi = IDCauHoi;
        this.IDMonThi = IDMonThi;
        this.IDLop = IDLop;
        CauHoi = cauHoi;
        DapAnA = dapAnA;
        DapAnB = dapAnB;
        DapAnC = dapAnC;
        DapAnD = dapAnD;
        DapAn = dapAn;
    }

    public CauHoi() {
    }

    public int getIDCauHoi() {
        return IDCauHoi;
    }

    public void setIDCauHoi(int IDCauHoi) {
        this.IDCauHoi = IDCauHoi;
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

    public String getCauHoi() {
        return CauHoi;
    }

    public void setCauHoi(String cauHoi) {
        CauHoi = cauHoi;
    }

    public String getDapAnA() {
        return DapAnA;
    }

    public void setDapAnA(String dapAnA) {
        DapAnA = dapAnA;
    }

    public String getDapAnB() {
        return DapAnB;
    }

    public void setDapAnB(String dapAnB) {
        DapAnB = dapAnB;
    }

    public String getDapAnC() {
        return DapAnC;
    }

    public void setDapAnC(String dapAnC) {
        DapAnC = dapAnC;
    }

    public String getDapAnD() {
        return DapAnD;
    }

    public void setDapAnD(String dapAnD) {
        DapAnD = dapAnD;
    }

    public String getDapAn() {
        return DapAn;
    }

    public void setDapAn(String dapAn) {
        DapAn = dapAn;
    }
}
