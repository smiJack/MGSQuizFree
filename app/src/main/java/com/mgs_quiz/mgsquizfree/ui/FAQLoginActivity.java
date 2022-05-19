package com.mgs_quiz.mgsquizfree.ui;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.mgs_quiz.mgsquizfree.R;

public class FAQLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faqlogin);

        initL();
    }

    private void initL() {
        View back = findViewById(R.id.faqLoginfab);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                close();
            }
        });
    }

    private void close() {
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        close();
    }
}
