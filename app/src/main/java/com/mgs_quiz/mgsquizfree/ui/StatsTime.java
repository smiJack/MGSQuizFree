package com.mgs_quiz.mgsquizfree.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mgs_quiz.mgsquizfree.R;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import androidx.fragment.app.Fragment;

import static com.mgs_quiz.mgsquizfree.GameData.DEFZEITSEC;
import static com.mgs_quiz.mgsquizfree.GameData.SP_DEF;
import static com.mgs_quiz.mgsquizfree.GameData.SP_NAMES;
import static com.mgs_quiz.mgsquizfree.GameData.SP_SC1AHZ_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_SC1DHZ_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_SC2AHZ_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_SC2DHZ_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_SC3AHZ_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_SC3DHZ_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_SC4AHZ_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_SC4DHZ_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_SC5AHZ_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_SC5DHZ_KEY;

public class StatsTime extends Fragment {

    private TextView tvMgs1n;
    private TextView tvMgs1h;
    private TextView tvMgs2n;
    private TextView tvMgs2h;
    private TextView tvMgs3n;
    private TextView tvMgs3h;
    private TextView tvMgs4n;
    private TextView tvMgs4h;
    private TextView tvMgs5n;
    private TextView tvMgs5h;
    private View view;
    private Map<TextView, Integer> views;
    SharedPreferences sharedPrefs;

    public StatsTime() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.stats_time, container, false);
        initE();
        initV();
        showData();

        return view;
    }

    private void initE() {
        try {
            tvMgs1n = view.findViewById(R.id.tvMGS1NQuizDurationStats);
            tvMgs1h = view.findViewById(R.id.tvMGS1HQuizDurationStats);
            tvMgs2n = view.findViewById(R.id.tvMGS2NQuizDurationStats);
            tvMgs2h = view.findViewById(R.id.tvMGS2HQuizDurationStats);
            tvMgs3n = view.findViewById(R.id.tvMGS3NQuizDurationStats);
            tvMgs3h = view.findViewById(R.id.tvMGS3HQuizDurationStats);
            tvMgs4n = view.findViewById(R.id.tvMGS4NQuizDurationStats);
            tvMgs4h = view.findViewById(R.id.tvMGS4HQuizDurationStats);
            tvMgs5n = view.findViewById(R.id.tvMGS5NQuizDurationStats);
            tvMgs5h = view.findViewById(R.id.tvMGS5HQuizDurationStats);
            views = new HashMap<>();
            sharedPrefs = view.getContext().getApplicationContext().getSharedPreferences(SP_NAMES, Context.MODE_PRIVATE);
        } catch (Exception e) {
        }
    }

    private void initV() {
        int a1 = sharedPrefs.getInt(SP_SC1AHZ_KEY, SP_DEF);
        int d1 = sharedPrefs.getInt(SP_SC1DHZ_KEY, SP_DEF);
        int a2 = sharedPrefs.getInt(SP_SC2AHZ_KEY, SP_DEF);
        int d2 = sharedPrefs.getInt(SP_SC2DHZ_KEY, SP_DEF);
        int a3 = sharedPrefs.getInt(SP_SC3AHZ_KEY, SP_DEF);
        int d3 = sharedPrefs.getInt(SP_SC3DHZ_KEY, SP_DEF);
        int a4 = sharedPrefs.getInt(SP_SC4AHZ_KEY, SP_DEF);
        int d4 = sharedPrefs.getInt(SP_SC4DHZ_KEY, SP_DEF);
        int a5 = sharedPrefs.getInt(SP_SC5AHZ_KEY, SP_DEF);
        int d5 = sharedPrefs.getInt(SP_SC5DHZ_KEY, SP_DEF);

        views = new LinkedHashMap<>();
        views.put(tvMgs1n, a1);
        views.put(tvMgs1h, d1);
        views.put(tvMgs2n, a2);
        views.put(tvMgs2h, d2);
        views.put(tvMgs3n, a3);
        views.put(tvMgs3h, d3);
        views.put(tvMgs4n, a4);
        views.put(tvMgs4h, d4);
        views.put(tvMgs5n, a5);
        views.put(tvMgs5h, d5);
    }

    private void showData() {
        for (Map.Entry<TextView, Integer> entry : views.entrySet()) {
            TextView tv = entry.getKey();
            int value = entry.getValue();

            if (value == SP_DEF || value == DEFZEITSEC) {
                tv.setText(getString(R.string.scoreBestzeitNo));
            } else {
                int a = value / 60;
                int b = value % 60;
                tv.setText(getString(R.string.scoreTime, a, b));
            }
        }
    }
}
