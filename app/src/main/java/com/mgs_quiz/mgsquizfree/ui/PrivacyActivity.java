package com.mgs_quiz.mgsquizfree.ui;

import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mgs_quiz.mgsquizfree.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;

import static com.mgs_quiz.mgsquizfree.LogMessage.save;

public class PrivacyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy);

        TextView tv = findViewById(R.id.privacyDesc);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            tv.setText(Html.fromHtml(getPolicy(), Html.FROM_HTML_MODE_LEGACY));
        } else {
            tv.setText(Html.fromHtml(getPolicy()));
        }
        tv.setMovementMethod(LinkMovementMethod.getInstance());

        FloatingActionButton fab = findViewById(R.id.privacyfab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private String getPolicy() {
        InputStream is;

        if (Locale.getDefault().getLanguage().equals("de")) {
            is = getResources().openRawResource(R.raw.privacy);
        } else {
            is = getResources().openRawResource(R.raw.privacy_en);
        }

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(is));
        String line = "";
        StringBuilder sb = new StringBuilder();

        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e1) {
            Log.e("PrivacyActivity", "Error privacy: " + line, e1);
            e1.printStackTrace();
            save(this, "privacy: " + e1.getMessage());
        }
        return sb.length() < 2 ? getString(R.string.eDefault) : sb.toString();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
