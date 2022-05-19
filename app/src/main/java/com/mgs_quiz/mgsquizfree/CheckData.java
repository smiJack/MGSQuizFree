package com.mgs_quiz.mgsquizfree;

import android.content.SharedPreferences;

import java.util.Calendar;

import static com.mgs_quiz.mgsquizfree.GameData.SP_SCOREU;
import static com.mgs_quiz.mgsquizfree.GameData.SP_SCOREU_DIF;
import static com.mgs_quiz.mgsquizfree.GameData.SP_SCOT_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_TIMED;
import static com.mgs_quiz.mgsquizfree.GameData.SP_TIMED_DIF;
import static com.mgs_quiz.mgsquizfree.GameData.SP_TIMEU;
import static com.mgs_quiz.mgsquizfree.GameData.SP_TIMEU_DIF;

public final class CheckData {
    private CheckData() {
    }

    public static boolean rnu(SharedPreferences preferences) {
        boolean score = preferences.getInt(SP_SCOT_KEY, 0) >= (preferences.getInt(SP_SCOREU, 0) + SP_SCOREU_DIF);
        boolean dif = Calendar.getInstance().getTimeInMillis() >= (preferences.getLong(SP_TIMEU, 0) + SP_TIMEU_DIF);
        return score && dif;
    }

    public static boolean rnd(SharedPreferences preferences) {
        return Calendar.getInstance().getTimeInMillis() >= (preferences.getLong(SP_TIMED, 0) + SP_TIMED_DIF);
    }
}
