package com.example.appthitracnghiem.Adapters;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.appthitracnghiem.Fragments.QuestionFragment;
import com.example.appthitracnghiem.Model.CauHoi;
import java.util.List;

public class QuestionFragmentAdapter extends FragmentStateAdapter {
    private List<CauHoi> cauHoiList;
    public QuestionFragmentAdapter(@NonNull FragmentActivity fragmentActivity, List<CauHoi> list) {
        super(fragmentActivity);
        this.cauHoiList=list;

    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if(cauHoiList.isEmpty()||cauHoiList==null){
            return null;
        }
        CauHoi cauHoi = cauHoiList.get(position);
        QuestionFragment questionFragment =new QuestionFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("question_object",cauHoi);
        bundle.putSerializable("index",position);
        questionFragment.setArguments(bundle);
        return questionFragment;
    }

    @Override
    public int getItemCount() {
        if(cauHoiList!=null){
            return cauHoiList.size();
        }
        return 0;
    }

}
