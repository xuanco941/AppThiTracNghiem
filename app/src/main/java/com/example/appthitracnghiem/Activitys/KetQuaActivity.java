package com.example.appthitracnghiem.Activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appthitracnghiem.Adapters.GrdKetQuaThiAdapter;
import com.example.appthitracnghiem.Adapters.LstKetQuaThiAdapter;
import com.example.appthitracnghiem.Commons.Common;
import com.example.appthitracnghiem.ConfigDB.Database;
import com.example.appthitracnghiem.Model.ChiTietBaiLam;
import com.example.appthitracnghiem.Model.ChiTietDeThi;
import com.example.appthitracnghiem.Model.KetQua;
import com.example.appthitracnghiem.Model.MonThi;
import com.example.appthitracnghiem.R;

import java.util.ArrayList;
import java.util.List;

public class KetQuaActivity extends AppCompatActivity {

    ViewStub stubGrid;
    ViewStub stubList;
    ListView listView;
    GridView gridView;

    GrdKetQuaThiAdapter grdKetQuaThiAdapter;
    LstKetQuaThiAdapter lstKetQuaThiAdapter;
    List<ChiTietBaiLam> chiTietBaiLam;
    int currentViewMode = 0;

    double DiemBaiThi;
    static final int VIEW_MODE_GRIDVIEW = 0;
    static final int VIEW_MODE_LISTVIEW = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ket_qua);

        System.out.println(Common.cauHoiList.get(0).getCauHoi());
        System.out.println(Common.cauHoiList.get(0).getDapAn());
        stubList = findViewById(R.id.stub_list);
        stubGrid = findViewById(R.id.stub_grid);

        stubList.inflate();

        switchView();
        addControlsInfor();
        //Insert kq thi vào CSDL
        insertDBketquathi();
    }


    private void addControlsInfor() {
        TextView txtDeThi = findViewById(R.id.txtDeThi);
        TextView txtThoiGianLamBai = findViewById(R.id.txtThoiGianLam);
        TextView txtSoCauDung = findViewById(R.id.txtSoCauDung);
        TextView txtDiem = findViewById(R.id.txtDiem);
        TextView txtTenHS=findViewById(R.id.tv_TenHocSinh);

        int minutes = Common.THOI_GIAN_LAM_BAI / 60;
        int seconds = Common.THOI_GIAN_LAM_BAI - (minutes * 60);
        String secondsString = Integer.toString(seconds);
        if (seconds <= 9) {
            secondsString = "0" + secondsString;
        }
        txtDeThi.setText("Đề thi: "+Common.TEN_DE_THI);
        txtThoiGianLamBai.setText("Thời gian làm bài: "+Integer.toString(minutes) + ":" + secondsString);
        txtSoCauDung.setText("Số câu đúng: " + Common.SO_CAU_DUNG + "/" + chiTietBaiLam.size());
        txtTenHS.setText("Tên học sinh: "+Common.TEN_HOC_SINH);
        DiemBaiThi=Common.SO_CAU_DUNG * 1.0 / chiTietBaiLam.size() * 10;
        txtDiem.setText("Điểm: " + String.valueOf(DiemBaiThi));

    }


    public void swtichLayout(View view) {
        if (VIEW_MODE_GRIDVIEW == currentViewMode) {
            currentViewMode = VIEW_MODE_LISTVIEW;
        } else {
            currentViewMode = VIEW_MODE_GRIDVIEW;
        }
        switchView();
    }

    private void switchView() {
        ImageView switchIcon = findViewById(R.id.switchIcon);
        if (VIEW_MODE_GRIDVIEW == currentViewMode) {
            switchIcon.setImageResource(R.drawable.ic_grid_t);
            stubGrid.setVisibility(View.VISIBLE);
            stubList.setVisibility(View.GONE);

            addControlsGrd();
        } else {

            switchIcon.setImageResource(R.drawable.ic_list_t);
            stubGrid.setVisibility(View.GONE);
            stubList.setVisibility(View.VISIBLE);

            addControlsLst();
        }
    }

    private void addControlsLst() {
        listView = findViewById(R.id.lstKetQuaThi);
        chiTietBaiLam = LstKetQuaThiAdapter.getChiTietBaiLam();
        lstKetQuaThiAdapter = new LstKetQuaThiAdapter(
                KetQuaActivity.this, R.layout.item_list_ketquathi_t, chiTietBaiLam);

        listView.setAdapter(lstKetQuaThiAdapter);
    }

    private void addControlsGrd() {
        gridView = findViewById(R.id.grdKetQuaThi);
        chiTietBaiLam = GrdKetQuaThiAdapter.getChiTietBaiLam();
        grdKetQuaThiAdapter = new GrdKetQuaThiAdapter(
                KetQuaActivity.this, R.layout.item_grid_ketquathi_t, chiTietBaiLam);

        gridView.setAdapter(grdKetQuaThiAdapter);
    }
    private void insertDBketquathi() {

        KetQua ketQua = new KetQua(Common.IDDETHI,Common.ID_HOCSINH,DiemBaiThi);
        ContentValues contentValues = new ContentValues();
        contentValues.put("IDDeThi",ketQua.getIDDeThi());
        contentValues.put("IDHocSinh",ketQua.getIDHocSinh());
        contentValues.put("Diem",DiemBaiThi);

            try {
                SQLiteDatabase db= Database.initDatabase(KetQuaActivity.this, Common.DATABASE_NAME);
                db.insert("KetQua",null,contentValues);
                db.close();
            }
            catch(SQLException e) {
                Toast.makeText(KetQuaActivity.this,"Lỗi kết nối tới CSDL",Toast.LENGTH_SHORT).show();
            }
    }
}