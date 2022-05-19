package com.mgs_quiz.mgsquizfree;

import android.app.Activity;

public class BDT {

    public static void setBackground(Activity activity, int color) {
        activity.getWindow().getDecorView().setBackgroundColor(activity.getResources().getColor(color));
    }
}
