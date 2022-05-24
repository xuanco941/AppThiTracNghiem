package com.example.appthitracnghiem.Fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appthitracnghiem.Adapters.ItemQuestionBottomAdapter;
import com.example.appthitracnghiem.Interfaces.IClickListenerQuestionBottom;
import com.example.appthitracnghiem.Model.CauHoi;
import com.example.appthitracnghiem.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.List;

public class QuestionBottomSheetFragment extends BottomSheetDialogFragment {
    private List<CauHoi> cauHoiList;
    private IClickListenerQuestionBottom iClickListenerQuestionBottom;

    public QuestionBottomSheetFragment(List<CauHoi> cauHoiList, IClickListenerQuestionBottom iClickListenerQuestionBottom) {
        this.cauHoiList = cauHoiList;
        this.iClickListenerQuestionBottom = iClickListenerQuestionBottom;
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        BottomSheetDialog bottomSheetDialog= (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_bottom_sheet,null);
        bottomSheetDialog.setContentView(view);

        RecyclerView rcvData = view.findViewById(R.id.rcv_data);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),5);
        rcvData.setLayoutManager(gridLayoutManager);

        ItemQuestionBottomAdapter itemAdater = new ItemQuestionBottomAdapter(cauHoiList, new IClickListenerQuestionBottom() {
            @Override
            public void clickItemQuestion(int index) {
                bottomSheetDialog.dismiss();
                iClickListenerQuestionBottom.clickItemQuestion(index);
            }
        });
        rcvData.setAdapter(itemAdater);
        return bottomSheetDialog;
    }
}
