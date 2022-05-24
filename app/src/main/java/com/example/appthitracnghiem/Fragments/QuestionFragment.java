package com.example.appthitracnghiem.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.appthitracnghiem.Commons.Common;
import com.example.appthitracnghiem.Model.CauHoi;
import com.example.appthitracnghiem.R;


public class QuestionFragment extends Fragment {
    private TextView tv_Question;
    private TextView tv_AnswerA;
    private TextView tv_AnswerB;
    private TextView tv_AnswerC;
    private TextView tv_AnswerD;
    private TextView tv_STTQuestion;

    private View mView;
    private int index;



    public QuestionFragment() {

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_question, container, false);
        tv_Question = mView.findViewById(R.id.tv_question);
        tv_AnswerA = mView.findViewById(R.id.tv_answerA);
        tv_AnswerB = mView.findViewById(R.id.tv_answerB);
        tv_AnswerC = mView.findViewById(R.id.tv_answerC);
        tv_AnswerD = mView.findViewById(R.id.tv_answerD);
        tv_STTQuestion = mView.findViewById(R.id.tv_STTCauHoi);

        Bundle bundle=getArguments();
        if(bundle!=null){
            CauHoi cauHoi = (CauHoi) bundle.get("question_object");
            index = (int) bundle.get("index");
            int stt=index+1;
            tv_STTQuestion.setText("Câu "+stt);
            tv_Question.setText(cauHoi.getCauHoi());
            tv_AnswerA.setText(cauHoi.getDapAnA());
            tv_AnswerB.setText(cauHoi.getDapAnB());
            tv_AnswerC.setText(cauHoi.getDapAnC());
            tv_AnswerD.setText(cauHoi.getDapAnD());
        }

        //Set sự kiện khi người dùng chọn đáp án
        tv_AnswerA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addQuestionAnswer("A");
                resetBackground();
                addDetailQuestionAnswer(tv_AnswerA.getText().toString());
                addDetailQuestion(tv_Question.getText().toString());
                tv_AnswerA.setBackgroundResource(R.color.purple_200);
            }
        });
        tv_AnswerB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addQuestionAnswer("B");
                resetBackground();
                addDetailQuestionAnswer(tv_AnswerB.getText().toString());
                addDetailQuestion(tv_Question.getText().toString());
                tv_AnswerB.setBackgroundResource(R.color.purple_200);
            }
        });
        tv_AnswerC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addQuestionAnswer("C");
                resetBackground();
                addDetailQuestionAnswer(tv_AnswerC.getText().toString());
                addDetailQuestion(tv_Question.getText().toString());
                tv_AnswerC.setBackgroundResource(R.color.purple_200);
            }
        });
        tv_AnswerD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addQuestionAnswer("D");
                resetBackground();
                addDetailQuestionAnswer(tv_AnswerD.getText().toString());
                addDetailQuestion(tv_Question.getText().toString());
                tv_AnswerD.setBackgroundResource(R.color.purple_200);
            }
        });
        if(Common.chiTietDeThiList.get(index).getDapAnLuaChon()!=null)
        {
            switch (Common.chiTietDeThiList.get(index).getDapAnLuaChon()){
                case "A":
                    tv_AnswerA.setBackgroundResource(R.color.purple_200);
                    break;
                case "B":
                    tv_AnswerB.setBackgroundResource(R.color.purple_200);
                    break;
                case "C":
                    tv_AnswerC.setBackgroundResource(R.color.purple_200);
                    break;
                case "D":
                    tv_AnswerD.setBackgroundResource(R.color.purple_200);
                    break;
            }
        }
        return mView;
    }
    private void resetBackground(){
        tv_AnswerA.setBackgroundResource(R.color.white);
        tv_AnswerB.setBackgroundResource(R.color.white);
        tv_AnswerC.setBackgroundResource(R.color.white);
        tv_AnswerD.setBackgroundResource(R.color.white);
    }

    private void addQuestionAnswer(String answer) {
        Common.chiTietDeThiList.get(index).setDapAnLuaChon(answer);
    }
    private void addDetailQuestion(String strCauHoi) {
        Common.chiTietDeThiList.get(index).setChiTietCauHoi(strCauHoi);
    }

    private void addDetailQuestionAnswer(String strCauTraLoi) {
        Common.chiTietDeThiList.get(index).setChiTietDapAn(strCauTraLoi);
    }
}