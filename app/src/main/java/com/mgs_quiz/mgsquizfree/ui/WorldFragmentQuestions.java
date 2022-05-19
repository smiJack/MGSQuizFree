package com.mgs_quiz.mgsquizfree.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mgs_quiz.mgsquizfree.LogMessage;
import com.mgs_quiz.mgsquizfree.R;
import com.mgs_quiz.mgsquizfree.model.Ranking;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import static com.mgs_quiz.mgsquizfree.GameData.BKEY_RANKING;
import static com.mgs_quiz.mgsquizfree.GetFormattedNumber.getFormattedNumber;

public class WorldFragmentQuestions extends Fragment {

    private Ranking ranking;
    private View view;

    private TextView u1;
    private TextView v1;
    private TextView u2;
    private TextView v2;
    private TextView u3;
    private TextView v3;
    private TextView u4;
    private TextView v4;
    private TextView u5;
    private TextView v5;
    private TextView u6;
    private TextView v6;
    private TextView u7;
    private TextView v7;
    private TextView u8;
    private TextView v8;
    private TextView u9;
    private TextView v9;
    private TextView u10;
    private TextView v10;

    public WorldFragmentQuestions() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.world_questions, container, false);

        initE();
        setData();

        return view;
    }

    private void initE() {
        u1 = view.findViewById(R.id.worldQU1);
        v1 = view.findViewById(R.id.worldQV1);
        u2 = view.findViewById(R.id.worldQU2);
        v2 = view.findViewById(R.id.worldQV2);
        u3 = view.findViewById(R.id.worldQU3);
        v3 = view.findViewById(R.id.worldQV3);
        u4 = view.findViewById(R.id.worldQU4);
        v4 = view.findViewById(R.id.worldQV4);
        u5 = view.findViewById(R.id.worldQU5);
        v5 = view.findViewById(R.id.worldQV5);
        u6 = view.findViewById(R.id.worldQU6);
        v6 = view.findViewById(R.id.worldQV6);
        u7 = view.findViewById(R.id.worldQU7);
        v7 = view.findViewById(R.id.worldQV7);
        u8 = view.findViewById(R.id.worldQU8);
        v8 = view.findViewById(R.id.worldQV8);
        u9 = view.findViewById(R.id.worldQU9);
        v9 = view.findViewById(R.id.worldQV9);
        u10 = view.findViewById(R.id.worldQU10);
        v10 = view.findViewById(R.id.worldQV10);
    }

    private void setData() {
        try {
            ranking = (Ranking) getArguments().getSerializable(BKEY_RANKING);
            if (ranking != null) {
                u1.setText(ranking.getU1());
                v1.setText(getString(R.string.statsAnswers, getFormattedNumber(ranking.getV1())));
                u2.setText(ranking.getU2());
                v2.setText(getString(R.string.statsAnswers, getFormattedNumber(ranking.getV2())));
                u3.setText(ranking.getU3());
                v3.setText(getString(R.string.statsAnswers, getFormattedNumber(ranking.getV3())));
                u4.setText(ranking.getU4());
                v4.setText(getString(R.string.statsAnswers, getFormattedNumber(ranking.getV4())));
                u5.setText(ranking.getU5());
                v5.setText(getString(R.string.statsAnswers, getFormattedNumber(ranking.getV5())));
                u6.setText(ranking.getU6());
                v6.setText(getString(R.string.statsAnswers, getFormattedNumber(ranking.getV6())));
                u7.setText(ranking.getU7());
                v7.setText(getString(R.string.statsAnswers, getFormattedNumber(ranking.getV7())));
                u8.setText(ranking.getU8());
                v8.setText(getString(R.string.statsAnswers, getFormattedNumber(ranking.getV8())));
                u9.setText(ranking.getU9());
                v9.setText(getString(R.string.statsAnswers, getFormattedNumber(ranking.getV9())));
                u10.setText(ranking.getU10());
                v10.setText(getString(R.string.statsAnswers, getFormattedNumber(ranking.getV10())));
            }
        } catch (Exception e) {
            LogMessage.save(getContext(), e.getMessage() + "\n" + getClass().getName());
        }
    }
}
