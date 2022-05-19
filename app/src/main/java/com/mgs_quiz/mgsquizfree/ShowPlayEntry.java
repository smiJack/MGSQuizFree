package com.mgs_quiz.mgsquizfree;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public abstract class ShowPlayEntry {

    private final static String APP_NAME = "com.mgs_quiz.mgsquizfree";
    private static final String MARKET_URL = "market://details?id=" + APP_NAME;
    private static final String BROWSER_URL = "https://play.google.com/store/apps/details?id=" + APP_NAME;

    public static void showPlayEntry(Context context) {
        try {
            context.startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse(MARKET_URL)));
        } catch (Exception e) {
            context.startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse(BROWSER_URL)));
        }
    }
}
