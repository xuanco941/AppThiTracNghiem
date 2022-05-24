package com.example.appthitracnghiem.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appthitracnghiem.Activitys.DeThiActivity;
import com.example.appthitracnghiem.Activitys.MainActivity;
import com.example.appthitracnghiem.Commons.Common;
import com.example.appthitracnghiem.Model.DeThi;
import com.example.appthitracnghiem.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DeThiAdapter extends RecyclerView.Adapter<DeThiAdapter.DeThiHolder> {

    private List<DeThi> deThiList;
    private Context context;

    public DeThiAdapter(Context context,List<DeThi> deThiList) {
        this.deThiList = deThiList;
        this.context = context;
    }

    @NonNull
    @Override
    public DeThiHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dethi,parent,false);
        return new DeThiHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DeThiHolder holder, int position) {
        DeThi deThi = deThiList.get(position);
        if(deThi==null)
            return;
        holder.tv_tendethi.setText(deThi.getTenDeThi());
        holder.tv_thoigianthi.setText(deThi.getThoiGianLamBai()+"'");

        //Hiển thị thời gian làm bài
        String strThoiGianLamBai= deThi.getThoiGianThi();
        SimpleDateFormat fm1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        SimpleDateFormat fm2 = new SimpleDateFormat("HH:mm");
        Date date=null;
            try {
                    date = fm1.parse(strThoiGianLamBai);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
        holder.tv_GioLamBai.setText("Thời làm bài: "+fm2.format(date));
        holder.cv_dethi.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                //Lay thoi gian hien tai va thoi gian thi
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

                Date crtime=  calendar.getTime();
                Date timecsdl=null;
                try {
                    timecsdl= simpleDateFormat.parse(deThi.getThoiGianThi());
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                //thoi gian ket thuc
                calendar.setTime(timecsdl);
                calendar.add(Calendar.MINUTE,deThi.getThoiGianLamBai());
                Date timeend=calendar.getTime();

                if(timecsdl.compareTo(crtime)>0)
                    Toast.makeText(context, "Hiện chưa tới giờ thi vui lòng quay lại sau !", Toast.LENGTH_SHORT).show();
                else if(crtime.compareTo(timeend)>0)
                    Toast.makeText(context, "Đã hết thời gian làm bài thi !", Toast.LENGTH_SHORT).show();
                else {
                    //Chuyển sang activity main
                    Intent intent = new Intent(context, MainActivity.class);
                    Common.IDDETHI = deThi.getIDDeThi();
                    Common.THOI_GIAN_THI=deThi.getThoiGianLamBai()*60; //giây
                    Common.SOLUONGCAUHOI=deThi.getSoLuongCauHoi();
                    Common.TEN_DE_THI=deThi.getTenDeThi();
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if(deThiList!=null)
            return deThiList.size();
        return 0;
    }

    public class DeThiHolder extends RecyclerView.ViewHolder{
        private TextView tv_thoigianthi;
        private TextView tv_tendethi;
        private TextView tv_GioLamBai;
        private CardView cv_dethi;
        public DeThiHolder(@NonNull View itemView) {
            super(itemView);
            tv_thoigianthi=itemView.findViewById(R.id.tv_thoigianthi);
            tv_tendethi=itemView.findViewById(R.id.tv_TenDeThi);
            tv_GioLamBai = itemView.findViewById(R.id.tv_giolambai  );
            cv_dethi=itemView.findViewById(R.id.cv_DeThi);
        }
    }
}
