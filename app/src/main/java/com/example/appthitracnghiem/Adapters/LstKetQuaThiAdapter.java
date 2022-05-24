package com.example.appthitracnghiem.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.appthitracnghiem.Commons.Common;
import com.example.appthitracnghiem.Model.ChiTietBaiLam;
import com.example.appthitracnghiem.R;

import java.util.ArrayList;
import java.util.List;

public class LstKetQuaThiAdapter extends ArrayAdapter<ChiTietBaiLam> {

    static List<ChiTietBaiLam> chiTietBaiLam;

    public LstKetQuaThiAdapter(@NonNull Context context, int resource, @NonNull List<ChiTietBaiLam> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View v = convertView;
        if (v == null) {
            v = View.inflate(parent.getContext(), R.layout.item_list_ketquathi_t, null);
        }
        else v = convertView;

        //Bind dữ liệu phần tử vào View
        ChiTietBaiLam chiTietBaiLam = getItem(position);
        TextView txtCauHoiLst = v.findViewById(R.id.txtCauHoiLst);
        TextView txtCauTraLoiLst = v.findViewById(R.id.txtCauTraLoiLst);

        txtCauHoiLst.setText(chiTietBaiLam.getCauHoi());
        txtCauTraLoiLst.setText(chiTietBaiLam.getDapAnChon());

        return v;
    }

    public static List<ChiTietBaiLam> getChiTietBaiLam() {
        chiTietBaiLam = new ArrayList<>();
        String chiTietCauHoi, chiTietDapAn;

        for (int i = 0; i < Common.chiTietDeThiList.size(); i++) {
            chiTietCauHoi = Common.chiTietDeThiList.get(i).getChiTietCauHoi();
            chiTietDapAn = Common.chiTietDeThiList.get(i).getChiTietDapAn();
            chiTietBaiLam.add(new ChiTietBaiLam(
                    "Câu " + (i + 1), chiTietCauHoi, chiTietDapAn));
        }
        return chiTietBaiLam;
    }
}
