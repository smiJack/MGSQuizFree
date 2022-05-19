package com.mgs_quiz.mgsquizfree;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.text.Html;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

import static com.mgs_quiz.mgsquizfree.GameData.SP_USERNAME;

public abstract class DialogShareFriend {

    public static void shareWithFriend(final Context context, final SharedPreferences preferences) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(R.string.shareAppDialog)
                .setPositiveButton(R.string.dialogYea, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        showShareDialog(context, preferences);
                    }
                })
                .setNegativeButton(R.string.dialogNo, null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private static void showShareDialog(final Context context, final SharedPreferences preferences) {
        TextView title = new TextView(context);
        title.setPadding(16,8,16,8);
        title.setTextSize(16);
        title.setGravity(Gravity.CENTER);
        title.setTypeface(null, Typeface.BOLD);
        title.setText(Html.fromHtml(context.getString(R.string.shareAppTitle)));

        AlertDialog.Builder shareDialog = new AlertDialog.Builder(context);
        shareDialog.setCustomTitle(title);
        shareDialog.setSingleChoiceItems(R.array.shareAppWithFriendChooser, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                showUserMessage(context, which, preferences);
            }
        })
                .setNegativeButton(R.string.shareCancel, null)
                .setCancelable(true);
        AlertDialog  share = shareDialog.create();
        share.show();
    }

    private static void showUserMessage(Context context, int position, SharedPreferences sharedPrefs) {
        String user;
        if (position == 0) {
            user = sharedPrefs.getString(SP_USERNAME, null);
        } else if (position == 1) {
            user = null;
        } else {
            Toast.makeText(context, R.string.eSelectOption, Toast.LENGTH_LONG).show();
            return;
        }

        String android = "android: ";
        String message = (user==null) ? context.getString(R.string.shareApp) : context.getString(R.string.shareAppWithUsername, user);
        Intent app = new Intent(Intent.ACTION_SEND);
        app.setType("text/plain");
        app.putExtra(Intent.EXTRA_SUBJECT, android + (R.string.app_name));
        app.putExtra(Intent.EXTRA_TEXT, message);

        try {
            context.startActivity(Intent.createChooser(app, context.getString(R.string.shareSelectApp)));
        } catch (Exception e) {
            Toast.makeText(context, R.string.eGeneral, Toast.LENGTH_LONG).show();
        }
    }
}
