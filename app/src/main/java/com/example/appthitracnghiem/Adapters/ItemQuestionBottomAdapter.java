package com.example.appthitracnghiem.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appthitracnghiem.Commons.Common;
import com.example.appthitracnghiem.Interfaces.IClickListenerQuestionBottom;
import com.example.appthitracnghiem.Model.CauHoi;
import com.example.appthitracnghiem.R;


import java.util.List;

public class ItemQuestionBottomAdapter extends RecyclerView.Adapter<ItemQuestionBottomAdapter.ItemQuestionHolder> {
    private List<CauHoi> cauHoiList;
    private IClickListenerQuestionBottom iClickListenerQuestionBottom;

    public ItemQuestionBottomAdapter(List<CauHoi> cauHoiList, IClickListenerQuestionBottom iClickListenerQuestionBottom) {
        this.cauHoiList = cauHoiList;
        this.iClickListenerQuestionBottom = iClickListenerQuestionBottom;
    }

    @NonNull
    @Override
    public ItemQuestionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_bottom_question,parent,false);
        return new ItemQuestionHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemQuestionHolder holder, int position) {
        CauHoi cauHoi = cauHoiList.get(position);
        if(cauHoi==null) return;

        int index=position;
        holder.tvQuestion.setText(String.valueOf(position+1));

        if(Common.chiTietDeThiList.get(position).getDapAnLuaChon()!=null)
            holder.tvQuestion.setBackgroundResource(R.drawable.custom_bottom_question_active);
        holder.tvQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iClickListenerQuestionBottom.clickItemQuestion(index);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(cauHoiList!=null){
            return cauHoiList.size();
        }
        return 0;
    }

    public class ItemQuestionHolder extends RecyclerView.ViewHolder{

        private TextView tvQuestion;
        public ItemQuestionHolder (@NonNull View itemView) {
            super(itemView);
            tvQuestion=itemView.findViewById(R.id.tv_itemQuesiton);
        }
    }
}
