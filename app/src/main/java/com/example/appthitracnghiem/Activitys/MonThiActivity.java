package com.example.appthitracnghiem.Activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;

import com.example.appthitracnghiem.Adapters.MonThiAdapter;
import com.example.appthitracnghiem.Commons.Common;
import com.example.appthitracnghiem.ConfigDB.Database;
import com.example.appthitracnghiem.Model.MonThi;
import com.example.appthitracnghiem.R;

import java.util.ArrayList;
import java.util.List;

public class MonThiActivity extends AppCompatActivity {
    private MonThiAdapter monThiAdapter;
    private RecyclerView rcvMonThi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mon_thi);

        initView();
    }
    private void initView() {
        rcvMonThi = findViewById(R.id.rcvMonThi);
        monThiAdapter= new MonThiAdapter(this,getListMonThi());

        GridLayoutManager gridLayoutManager =new GridLayoutManager(this,2);
        rcvMonThi.setLayoutManager(gridLayoutManager);
        rcvMonThi.setAdapter(monThiAdapter);
    }

    private List<MonThi> getListMonThi() {
        List<MonThi> list =new ArrayList<>();
        try {
            SQLiteDatabase db= Database.initDatabase(MonThiActivity.this, Common.DATABASE_NAME);
            Cursor cursor = db.rawQuery("SELECT * FROM MonThi",null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                MonThi monThi = new MonThi();
                monThi.setIDMonThi(cursor.getInt(0));
                monThi.setTenMon(cursor.getString(1));
                monThi.setHinhAnh(cursor.getBlob(2));
                list.add(monThi);
                cursor.moveToNext();
            }
            cursor.close();
            db.close();
        }
        catch(SQLException e) {
            Toast.makeText(MonThiActivity.this,"Lỗi kết nối tới CSDL",Toast.LENGTH_SHORT).show();
        }
        return list;
    }

}