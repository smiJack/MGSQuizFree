package com.mgs_quiz.mgsquizfree.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mgs_quiz.mgsquizfree.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import static com.mgs_quiz.mgsquizfree.GameData.SP_INT_E;
import static com.mgs_quiz.mgsquizfree.GameData.SP_NAMES;
import static com.mgs_quiz.mgsquizfree.GameData.SP_SC1AH_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_SC1DH_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_SC2AH_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_SC2DH_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_SC3AH_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_SC3DH_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_SC4AH_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_SC4DH_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_SC5AH_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_SC5DH_KEY;
import static com.mgs_quiz.mgsquizfree.GetFormattedNumber.getFormattedNumber;

public class StatsHighest extends Fragment {

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
    SharedPreferences sharedPrefs;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.stats_highest, container, false);
        initE();
        showData();

        return view;
    }

    private void initE() {
        tvMgs1n = view.findViewById(R.id.tvMGS1NScoreHighestStats);
        tvMgs1h = view.findViewById(R.id.tvMGS1HScoreHighestStats);
        tvMgs2n = view.findViewById(R.id.tvMGS2NScoreHighestStats);
        tvMgs2h = view.findViewById(R.id.tvMGS2HScoreHighestStats);
        tvMgs3n = view.findViewById(R.id.tvMGS3NScoreHighestStats);
        tvMgs3h = view.findViewById(R.id.tvMGS3HScoreHighestStats);
        tvMgs4n = view.findViewById(R.id.tvMGS4NScoreHighestStats);
        tvMgs4h = view.findViewById(R.id.tvMGS4HScoreHighestStats);
        tvMgs5n = view.findViewById(R.id.tvMGS5NScoreHighestStats);
        tvMgs5h = view.findViewById(R.id.tvMGS5HScoreHighestStats);
        sharedPrefs = getActivity().getApplicationContext().getSharedPreferences(SP_NAMES, Context.MODE_PRIVATE);
    }

    private void showData() {
        tvMgs1n.setText(getString(R.string.scorePoints, getFormattedNumber(sharedPrefs.getInt(SP_SC1AH_KEY, SP_INT_E))));
        tvMgs1h.setText(getString(R.string.scorePoints, getFormattedNumber(sharedPrefs.getInt(SP_SC1DH_KEY, SP_INT_E))));
        tvMgs2n.setText(getString(R.string.scorePoints, getFormattedNumber(sharedPrefs.getInt(SP_SC2AH_KEY, SP_INT_E))));
        tvMgs2h.setText(getString(R.string.scorePoints, getFormattedNumber(sharedPrefs.getInt(SP_SC2DH_KEY, SP_INT_E))));
        tvMgs3n.setText(getString(R.string.scorePoints, getFormattedNumber(sharedPrefs.getInt(SP_SC3AH_KEY, SP_INT_E))));
        tvMgs3h.setText(getString(R.string.scorePoints, getFormattedNumber(sharedPrefs.getInt(SP_SC3DH_KEY, SP_INT_E))));
        tvMgs4n.setText(getString(R.string.scorePoints, getFormattedNumber(sharedPrefs.getInt(SP_SC4AH_KEY, SP_INT_E))));
        tvMgs4h.setText(getString(R.string.scorePoints, getFormattedNumber(sharedPrefs.getInt(SP_SC4DH_KEY, SP_INT_E))));
        tvMgs5n.setText(getString(R.string.scorePoints, getFormattedNumber(sharedPrefs.getInt(SP_SC5AH_KEY, SP_INT_E))));
        tvMgs5h.setText(getString(R.string.scorePoints, getFormattedNumber(sharedPrefs.getInt(SP_SC5DH_KEY, SP_INT_E))));
    }

}
