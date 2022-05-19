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

public class WorldFragmentExpertB extends Fragment {

    private Ranking ranking;
    private int stringID;
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


    public WorldFragmentExpertB() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.world_expert_b, container, false);

        initE();
        setData();

        return view;
    }


    private void initE() {
        stringID = R.string.scorePoints;
        u1 = view.findViewById(R.id.worldEBU1);
        v1 = view.findViewById(R.id.worldEBV1);
        u2 = view.findViewById(R.id.worldEBU2);
        v2 = view.findViewById(R.id.worldEBV2);
        u3 = view.findViewById(R.id.worldEBU3);
        v3 = view.findViewById(R.id.worldEBV3);
        u4 = view.findViewById(R.id.worldEBU4);
        v4 = view.findViewById(R.id.worldEBV4);
        u5 = view.findViewById(R.id.worldEBU5);
        v5 = view.findViewById(R.id.worldEBV5);
        u6 = view.findViewById(R.id.worldEBU6);
        v6 = view.findViewById(R.id.worldEBV6);
        u7 = view.findViewById(R.id.worldEBU7);
        v7 = view.findViewById(R.id.worldEBV7);
        u8 = view.findViewById(R.id.worldEBU8);
        v8 = view.findViewById(R.id.worldEBV8);
        u9 = view.findViewById(R.id.worldEBU9);
        v9 = view.findViewById(R.id.worldEBV9);
        u10 = view.findViewById(R.id.worldEBU10);
        v10 = view.findViewById(R.id.worldEBV10);
    }

    private void setData() {
        try {
            ranking = (Ranking) getArguments().getSerializable(BKEY_RANKING);
            if (ranking != null) {
                u1.setText(ranking.getU1());
                v1.setText(getString(stringID, getFormattedNumber(ranking.getV1())));
                u2.setText(ranking.getU2());
                v2.setText(getString(stringID, getFormattedNumber(ranking.getV2())));
                u3.setText(ranking.getU3());
                v3.setText(getString(stringID, getFormattedNumber(ranking.getV3())));
                u4.setText(ranking.getU4());
                v4.setText(getString(stringID, getFormattedNumber(ranking.getV4())));
                u5.setText(ranking.getU5());
                v5.setText(getString(stringID, getFormattedNumber(ranking.getV5())));
                u6.setText(ranking.getU6());
                v6.setText(getString(stringID, getFormattedNumber(ranking.getV6())));
                u7.setText(ranking.getU7());
                v7.setText(getString(stringID, getFormattedNumber(ranking.getV7())));
                u8.setText(ranking.getU8());
                v8.setText(getString(stringID, getFormattedNumber(ranking.getV8())));
                u9.setText(ranking.getU9());
                v9.setText(getString(stringID, getFormattedNumber(ranking.getV9())));
                u10.setText(ranking.getU10());
                v10.setText(getString(stringID, getFormattedNumber(ranking.getV10())));
            }
        } catch (Exception e) {
            LogMessage.save(getContext(), e.getMessage() + "\n" + getClass().getName());
        }
    }
}
