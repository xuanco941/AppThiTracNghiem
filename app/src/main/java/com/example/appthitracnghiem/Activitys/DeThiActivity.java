package com.example.appthitracnghiem.Activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.icu.text.DateFormat;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appthitracnghiem.Adapters.DeThiAdapter;

import com.example.appthitracnghiem.Commons.Common;
import com.example.appthitracnghiem.ConfigDB.Database;
import com.example.appthitracnghiem.Model.DeThi;
import com.example.appthitracnghiem.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DeThiActivity extends AppCompatActivity {

    private DeThiAdapter deThiAdapter;
    private RecyclerView rcvDeThi;
    private TextView tvInfoDeThi;

    String strCurrentDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_de_thi);

        //Lay ngay thang nam  hien tai
        Calendar calendar = Calendar.getInstance();
        Date date =calendar.getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy ");
        strCurrentDate = simpleDateFormat.format(date);
        strCurrentDate+="%";

        initView();
    }

    private void initView() {
        rcvDeThi=findViewById(R.id.rsv_dethi);
        tvInfoDeThi = findViewById(R.id.tv_infoDeThi);
        deThiAdapter = new DeThiAdapter(this,getListDeThi());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvDeThi.setLayoutManager(linearLayoutManager);
        rcvDeThi.setAdapter(deThiAdapter);
    }

    private List<DeThi> getListDeThi(){
        List<DeThi> list =new ArrayList<>();
        try {
            SQLiteDatabase db= Database.initDatabase(DeThiActivity.this, Common.DATABASE_NAME);
            Cursor cursor = db.rawQuery("SELECT * FROM DeThi WHERE IDMonThi=? AND IDLop=? AND ThoiGianThi LIKE ? AND NOT EXISTS (SELECT * FROM KetQua WHERE DeThi.IDDeThi=KetQua.IDDeThi AND IDHocSinh=?)",
                    new String[]{Common.IDMONTHI+"",Common.LOP+"",strCurrentDate,Common.ID_HOCSINH+""});
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                DeThi deThi = new DeThi();
                deThi.setIDDeThi(cursor.getInt(0));
                deThi.setTenDeThi(cursor.getString(1));
                deThi.setThoiGianLamBai(cursor.getInt(2));
                deThi.setThoiGianThi(cursor.getString(3));
                deThi.setSoLuongCauHoi(cursor.getInt(4));
                deThi.setIDMonThi(cursor.getInt(5));
                deThi.setIDLop(cursor.getInt(6));
                list.add(deThi);
                cursor.moveToNext();
            }
            cursor.close();
            db.close();
        }
        catch(SQLException e) {
            Toast.makeText(DeThiActivity.this,"Lỗi kết nối tới CSDL",Toast.LENGTH_SHORT).show();
        }
      //Hiển thị thông báo nếu chưa có đề thi
        if(list.size()==0)
            tvInfoDeThi.setVisibility(View.VISIBLE);
        return list;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        initView();
    }
}