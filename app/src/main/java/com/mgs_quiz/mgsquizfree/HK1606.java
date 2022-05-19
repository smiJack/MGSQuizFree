package com.mgs_quiz.mgsquizfree;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class HK1606 {
    private static final String TAG = "HK1606";

    private HK1606() {
    }

    public static void hk(Context context, View view) {
        try {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        } catch (Exception e) {
            Log.e(TAG, "hk: " + e.getMessage(), e.getCause());
            e.printStackTrace();
        }
    }
}
