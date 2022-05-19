package com.mgs_quiz.mgsquizfree;

import android.content.SharedPreferences;
import android.widget.Button;

import static com.mgs_quiz.mgsquizfree.GameData.SP_SCOT_KEY;

public class EnableFunction {

    public static void enableButton(SharedPreferences preferences, Button button, int[] limits) {
        if (preferences.getInt(SP_SCOT_KEY, 0) > limits[2]) {
            button.setEnabled(true);
        } else if (preferences.getInt(SP_SCOT_KEY, 0) > limits[1]) {
            button.setEnabled(false);
            button.setText(R.string.unknown1);
        } else if (preferences.getInt(SP_SCOT_KEY, 0) > limits[0]) {
            button.setEnabled(false);
            button.setText(R.string.unknown2);
        } else if (preferences.getInt(SP_SCOT_KEY, 0) <= limits[0]) {
            button.setEnabled(false);
            button.setText(R.string.unknown3);
        }
    }
}
