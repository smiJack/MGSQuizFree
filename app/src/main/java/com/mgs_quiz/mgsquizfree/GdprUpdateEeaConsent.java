package com.mgs_quiz.mgsquizfree;

import android.content.SharedPreferences;

import static com.mgs_quiz.mgsquizfree.GameData.SP_EEA_NPA;
import static com.mgs_quiz.mgsquizfree.GameData.SP_EEA_UNKNOWN;

public abstract class GdprUpdateEeaConsent {

    public static boolean updateEeaConsent(SharedPreferences sharedPreferences, int npa, boolean unknown) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(SP_EEA_NPA, npa);
        editor.putBoolean(SP_EEA_UNKNOWN, unknown);
        return editor.commit();
    }
}
