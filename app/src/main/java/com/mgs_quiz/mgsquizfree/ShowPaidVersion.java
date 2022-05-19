package com.mgs_quiz.mgsquizfree;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public abstract class ShowPaidVersion {

    private static final String PAID_MARKET_URL = "market://details?id=com.mgs_quiz.mgsquiz";
    private static final String PAID_BROWSER_URL = "https://play.google.com/store/apps/details?id=com.mgs_quiz.mgsquiz";

    public static void showPaidVersion(Context appContext) {
        try {
            appContext.startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse(PAID_MARKET_URL)));
        } catch (Exception e) {
            appContext.startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse(PAID_BROWSER_URL)));
        }
    }
}
