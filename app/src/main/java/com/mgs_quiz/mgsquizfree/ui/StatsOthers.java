package com.mgs_quiz.mgsquizfree.ui;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mgs_quiz.mgsquizfree.R;

import androidx.fragment.app.Fragment;

import static com.mgs_quiz.mgsquizfree.GameData.SP_DEF;
import static com.mgs_quiz.mgsquizfree.GameData.SP_NAMES;
import static com.mgs_quiz.mgsquizfree.GameData.SP_QCANSWERSA_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_QCANSWERSD_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_QTOTALOVA_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_QTOTALOV_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_SCALLAT_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_SCALLDT_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_SCOT_KEY;
import static com.mgs_quiz.mgsquizfree.GetFormattedNumber.getFormattedNumber;

public class StatsOthers extends Fragment {

    private TextView tvTotalQuestions;
    private TextView tvTotalAnswers;
    private TextView tvQAnsweredRate;
    private TextView tvCorrectAnswersA;
    private TextView tvCorrectAnswersD;
    private TextView tvExpertScoreA;
    private TextView tvExpertScoreD;
    private TextView tvOverallScore;
    private SharedPreferences sharedPrefs;
    private View view;

    public StatsOthers() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.stats_others, container, false);;
        initE();
        showData();

        return view;
    }

    private void initE() {
        tvTotalQuestions = view.findViewById(R.id.tvQTotalStats);
        tvTotalAnswers = view.findViewById(R.id.statsTVQAnsweredTotal);
        tvQAnsweredRate = view.findViewById(R.id.statsTVQAnsweredRate);
        tvCorrectAnswersA = view.findViewById(R.id.tvStatsCorrectAnsweresA);
        tvCorrectAnswersD = view.findViewById(R.id.tvStatsCorrectAnsweresD);
        tvOverallScore = view.findViewById(R.id.statsTVScoreOverall);
        tvExpertScoreA = view.findViewById(R.id.statsTVScoreExpertA);
        tvExpertScoreD = view.findViewById(R.id.statsTVScoreExpertD);
        sharedPrefs = view.getContext().getApplicationContext().getSharedPreferences(SP_NAMES, Context.MODE_PRIVATE);
    }

    private void showData() {
        int questions = sharedPrefs.getInt(SP_QTOTALOV_KEY, SP_DEF);
        int answers = sharedPrefs.getInt(SP_QTOTALOVA_KEY, SP_DEF);
        String rate = "";
        if (questions != 0 && answers != 0) {
            rate = String.format("%.2f %%", ((float)answers/questions*100));
        } else {
            rate = getString(R.string.scoreNo);
        }
        tvTotalQuestions.setText(getString(R.string.statsQuestions, getFormattedNumber(questions)));
        tvTotalAnswers.setText(getString(R.string.statsQuestions, getFormattedNumber(answers)));
        tvQAnsweredRate.setText(rate);
        tvCorrectAnswersA.setText(getString(R.string.statsAnswers, getFormattedNumber(sharedPrefs.getInt(SP_QCANSWERSA_KEY, SP_DEF))));
        tvCorrectAnswersD.setText(getString(R.string.statsAnswers, getFormattedNumber(sharedPrefs.getInt(SP_QCANSWERSD_KEY, SP_DEF))));
        tvExpertScoreA.setText(getString(R.string.scorePoints, getFormattedNumber(sharedPrefs.getInt(SP_SCALLAT_KEY, SP_DEF))));
        tvExpertScoreD.setText(getString(R.string.scorePoints, getFormattedNumber(sharedPrefs.getInt(SP_SCALLDT_KEY, SP_DEF))));
        tvOverallScore.setText(getString(R.string.scorePoints, getFormattedNumber(sharedPrefs.getInt(SP_SCOT_KEY, 0))));
    }
}
