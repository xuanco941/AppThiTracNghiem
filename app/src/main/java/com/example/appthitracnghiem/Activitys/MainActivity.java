package com.example.appthitracnghiem.Activitys;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appthitracnghiem.Adapters.QuestionFragmentAdapter;
import com.example.appthitracnghiem.Commons.Common;
import com.example.appthitracnghiem.ConfigDB.Database;
import com.example.appthitracnghiem.Fragments.QuestionBottomSheetFragment;
import com.example.appthitracnghiem.Interfaces.IClickListenerQuestionBottom;
import com.example.appthitracnghiem.Model.CauHoi;
import com.example.appthitracnghiem.Model.ChiTietDeThi;
import com.example.appthitracnghiem.Model.DeThi;
import com.example.appthitracnghiem.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ViewPager2 view_pager;
    private TextView tv_nopbai, tv_info;
    private TextView tv_iconlist;

    private TextView tv_timer;
    private Boolean isTimerRunning = false;
    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addControls();
        addEventControls();
        getData(); //Lấy danh sách câu hỏi.
        initListChiTietDeThi();

        QuestionFragmentAdapter questionFragmentAdapter = new QuestionFragmentAdapter(this, Common.cauHoiList);
        view_pager.setAdapter(questionFragmentAdapter);
    }

    private void addControls() {
        tv_nopbai = findViewById(R.id.tv_nopbai);
        view_pager = findViewById(R.id.view_pager);
        tv_timer = findViewById(R.id.tv_timmer);
        tv_info = findViewById(R.id.tv_info);
        tv_iconlist = findViewById(R.id.icon_list);
        activeTimer();
    }

    private void addEventControls() {
        tv_nopbai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Hiển thị dialog xác nhận nộp bài
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("Thông báo");
                dialog.setMessage("Chưa hết thời gian làm bài bạn chắc chắn muốn nộp bài ?");
                dialog.setIcon(R.drawable.warning);

                dialog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        callKetQuaThiActivity();
                    }
                });
                dialog.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                dialog.show();
            }
        });

        tv_iconlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                QuestionBottomSheetFragment questionBottomSheetFragment = new QuestionBottomSheetFragment(Common.cauHoiList, new IClickListenerQuestionBottom() {
                    @Override
                    public void clickItemQuestion(int index) {
                        //chuyển đến câu hỏi tương ứng
                        view_pager.setCurrentItem(index);
                    }
                });
                questionBottomSheetFragment.show(getSupportFragmentManager(), questionBottomSheetFragment.getTag());
            }
        });
    }

    private void getData() {
        try {
            SQLiteDatabase db = Database.initDatabase(MainActivity.this, Common.DATABASE_NAME);
            Cursor cursor = db.rawQuery("SELECT IDCauHoi,CauHoi,DapAnA,DapAnB,DapAnC,DapAnD,DapAn " +
                            "FROM CauHoi WHERE IDMonThi=? AND IDLop =? " +
                            "ORDER BY random() LIMIT ?",
                    new String[]{Common.IDMONTHI + "", Common.LOP + "", Common.SOLUONGCAUHOI + ""});

            cursor.moveToFirst();
            while (!cursor.isAfterLast())
            {
                CauHoi cauHoi = new CauHoi();
                cauHoi.setIDCauHoi(cursor.getInt(0));
                cauHoi.setCauHoi(cursor.getString(1));
                cauHoi.setDapAnA(cursor.getString(2));
                cauHoi.setDapAnB(cursor.getString(3));
                cauHoi.setDapAnC(cursor.getString(4));
                cauHoi.setDapAnD(cursor.getString(5));
                //bổ sung thêm chỗ này
                cauHoi.setDapAn(cursor.getString(6));
                Common.cauHoiList.add(cauHoi);
                cursor.moveToNext();
            }
            cursor.close();
            db.close();
        }
        catch (SQLException e) {
            Toast.makeText(MainActivity.this, "Lỗi kết nối tới CSDL", Toast.LENGTH_SHORT).show();
        }
    }

    private void initListChiTietDeThi() {
        for (int i = 0; i < Common.cauHoiList.size(); i++) {
            ChiTietDeThi chiTietDeThi = new ChiTietDeThi(Common.IDDETHI, Common.cauHoiList.get(i).getIDCauHoi(),Common.ID_HOCSINH, null);
            Common.chiTietDeThiList.add(chiTietDeThi);
        }
    }

    private void activeTimer() {
        isTimerRunning = true;
        countDownTimer = new CountDownTimer(Common.THOI_GIAN_THI * 1000 + 100, 1000) {
            @Override
            public void onTick(long l) {
                updateTimmer((int) l / 1000);
            }

            @Override
            public void onFinish() {
                callKetQuaThiActivity();
            }
        }.start();
    }

    int secondLeft = 0;

    private void updateTimmer(int thoigianconlai) {
        secondLeft = thoigianconlai;
        int minutes = thoigianconlai / 60;
        int seconds = thoigianconlai - (minutes * 60);
        String secondsString = Integer.toString(seconds);
        if (seconds <= 9) {
            secondsString = "0" + secondsString;
        }
        tv_timer.setText( Integer.toString(minutes) + ":" + secondsString);
    }

    private void callKetQuaThiActivity() {
        countDownTimer.cancel();
        view_pager.setAdapter(null);

        //Tính thời gian làm bài
        getCountTime();
        //Cập nhật chi tiết đề thi vào CSDL
        insertChiTietDeThi();
        //Chuyển hướng Activity KetQuaThi
        Intent i = new Intent(MainActivity.this, KetQuaActivity.class);
        startActivity(i);

        tv_info.setVisibility(View.VISIBLE);
    }

    private void insertChiTietDeThi() {
        for (int i=0;i<Common.chiTietDeThiList.size();i++){
            ChiTietDeThi chiTietDeThi = Common.chiTietDeThiList.get(i);
            ContentValues contentValues = new ContentValues();
            contentValues.put("IDDeThi",chiTietDeThi.getIDDeThi());
            contentValues.put("IDCauHoi",chiTietDeThi.getIDCauHoi());
            contentValues.put("IDHocSinh",chiTietDeThi.getIDHocSinh());
            contentValues.put("DapAnLuaChon",chiTietDeThi.getDapAnLuaChon());

            try {
                SQLiteDatabase db= Database.initDatabase(MainActivity.this, Common.DATABASE_NAME);
                db.insert("ChiTietDeThi",null,contentValues);
            }
            catch(SQLException e) {
                Toast.makeText(MainActivity.this,"Lỗi kết nối tới CSDL",Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void getCountTime() {
        Common.THOI_GIAN_LAM_BAI = Common.THOI_GIAN_THI - secondLeft;
    }
}
