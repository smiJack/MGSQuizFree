package com.mgs_quiz.mgsquizfree;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;

import androidx.appcompat.app.AlertDialog;

import static com.mgs_quiz.mgsquizfree.GameData.SP_KEYRDONTSHOW;
import static com.mgs_quiz.mgsquizfree.GameData.SP_KEYRFIRST;
import static com.mgs_quiz.mgsquizfree.GameData.SP_KEYRLAUN;
import static com.mgs_quiz.mgsquizfree.GameData.SP_NAMER;
import static com.mgs_quiz.mgsquizfree.ShowPlayEntry.showPlayEntry;

public class APR {

    private final static String APP_TITLE = "Metal Gear Solid Quiz";

    private final static int DAYS_UNTIL_PROMPT = 0;
    private final static int LAUNCHES_UNTIL_PROMPT = 30;

    public static void app_launched(Context mContext) {
        SharedPreferences sharedPrefs = mContext.getSharedPreferences(SP_NAMER, 0);
        if (sharedPrefs.getBoolean(SP_KEYRDONTSHOW, false)) { return ; }

        SharedPreferences.Editor editor = sharedPrefs.edit();

        long launch_count = sharedPrefs.getLong(SP_KEYRLAUN, 0) + 1;
        editor.putLong(SP_KEYRLAUN, launch_count);

        // Get date of first launch
        Long firstLaunch = sharedPrefs.getLong(SP_KEYRFIRST, 0);
        if (firstLaunch == 0) {
            firstLaunch = System.currentTimeMillis();
            editor.putLong(SP_KEYRFIRST, firstLaunch);
        }

        // Wait at least n days before opening
        if (launch_count >= LAUNCHES_UNTIL_PROMPT) {
            if (System.currentTimeMillis() >= firstLaunch +
                    (DAYS_UNTIL_PROMPT * 24 * 60 * 60 * 1000)) {
                showRateDialog(mContext, editor);

                // reset launch_count to 0
                editor.putLong(SP_KEYRLAUN, 0);
            }
        }

        editor.apply();
    }

    @SuppressLint("SetTextI18n")
    private static void showRateDialog(final Context mContext, final SharedPreferences.Editor editor) {

        AlertDialog materialAlertDialogBuilder = new AlertDialog.Builder(mContext)
                .setTitle(mContext.getString(R.string.rate_title))
                .setMessage(mContext.getString(R.string.rate_message))
                .setPositiveButton(mContext.getString(R.string.rate_ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        showPlayEntry(mContext);
                        dialog.dismiss();
                    }
                })
                .setNeutralButton(mContext.getString(R.string.rate_cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setNegativeButton(mContext.getString(R.string.rate_no), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (editor != null) {
                            editor.putBoolean(SP_KEYRDONTSHOW, true);
                            editor.commit();
                        }
                        dialog.dismiss();
                    }
                }).create();

        materialAlertDialogBuilder.show();
    }
}