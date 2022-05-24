package com.example.appthitracnghiem.Commons;

import com.example.appthitracnghiem.Model.CauHoi;
import com.example.appthitracnghiem.Model.ChiTietDeThi;

import java.util.ArrayList;
import java.util.List;

public class Common {
    public static final String DATABASE_NAME= "AppThiTracNghiem.db";

    public static  int ID_HOCSINH;
    public static  String TEN_HOC_SINH;
    public static  int LOP;

    public static  int IDDETHI;
    public static String  TEN_DE_THI;

    public static  int IDMONTHI;
    public static  int SOLUONGCAUHOI;
    public static  int THOI_GIAN_THI; //giây
    public static  int THOI_GIAN_LAM_BAI;
    public static int SO_CAU_DUNG ;

    public static List<ChiTietDeThi> chiTietDeThiList=new ArrayList<>();
    public static List<CauHoi> cauHoiList=new ArrayList<>();
}
