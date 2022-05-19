package com.mgs_quiz.mgsquizfree.ui;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.mgs_quiz.mgsquizfree.LogMessage;
import com.mgs_quiz.mgsquizfree.R;

public class AppLogActivity extends AppCompatActivity {

    private TextView tvLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_log);

        tvLog = findViewById(R.id.appLogTVLogMessage);
    }

    public void loadLog(View view) {
        tvLog.setText(LogMessage.load(getApplicationContext()));
    }

    public void deleteLog(View view) {
        if (LogMessage.deleteFile(getApplicationContext())) {
            Toast.makeText(this, "File deleted", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "something went wrong", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
