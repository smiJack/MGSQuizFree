package com.mgs_quiz.mgsquizfree.ui;

import static com.mgs_quiz.mgsquizfree.GameData.SP_NAMES;
import static com.mgs_quiz.mgsquizfree.GameData.SP_UID;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.mgs_quiz.mgsquizfree.R;

import java.util.Locale;

public class FeedbackActivity extends AppCompatActivity {

    private Spinner spinner;
    private EditText message;
    private Button btnEmail;

    private String strMessage;
    private String strSubject;

    private int count = 0;
    private long start=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        initE();
        initL();

        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
    }

    private void initE() {
        spinner = findViewById(R.id.feedbackSubjectSpinner);
        message = findViewById(R.id.feedbackMessageET);
        btnEmail = findViewById(R.id.feedbackGenerateMailBtn);

        ArrayAdapter arrayAdapter = ArrayAdapter.createFromResource(
                this, R.array.feedback_subjects, R.layout.spinner
        );

        spinner.setAdapter(arrayAdapter);
        spinner.getBackground().setColorFilter(getResources().getColor(R.color.etGrey, null), PorterDuff.Mode.SRC_ATOP);
    }

    private void initL() {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    strSubject = null;
                } else {
                    strSubject = ((TextView)view).getText().toString();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        btnEmail.setOnClickListener(v -> generateMessage());
    }

    private void generateMessage() {
        strMessage = message.getText().toString().trim();

        String sysdata = Build.MANUFACTURER + ":" +
                    Build.MODEL + ":" +
                    Build.VERSION.RELEASE + ":" +
                    Build.VERSION.SDK_INT + "_" +
                    Locale.getDefault().getLanguage() +
                    getApplicationContext().getSharedPreferences(SP_NAMES, MODE_PRIVATE).getString(SP_UID, getString(R.string.defaultString)) + ":" +
                    Build.DISPLAY + ":" + getCalculatedDisplay();

        if (!inputValid()) {
            return;
        }

        Intent eMail = new Intent(Intent.ACTION_SENDTO);
        eMail.setData(Uri.parse("mailto:"));
        eMail.putExtra(Intent.EXTRA_EMAIL, new String[]{getString(R.string.contactAdress)});
        eMail.putExtra(Intent.EXTRA_SUBJECT, strSubject);
        eMail.putExtra(Intent.EXTRA_TEXT, strMessage + getString(R.string.feedbackMessageAboutSystem, sysdata));

        try {
            startActivity(Intent.createChooser(eMail, getString(R.string.feedbackSelectEmailApp)));
        } catch (Exception e) {
            Toast.makeText(this, getString(R.string.eNoEmailApp), Toast.LENGTH_LONG).show();
        }
    }

    private String getCalculatedDisplay() {
        return Resources.getSystem().getDisplayMetrics().widthPixels + "x" +
                Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    private boolean inputValid() {
        if (strMessage.equalsIgnoreCase("")|| strSubject == null || strMessage.length() < 5) {
            message.setError(getString(R.string.eInputRequiredFeedback));
            message.requestFocus();
            return false;
        }
        message.setError(null);
        return true;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            long time= System.currentTimeMillis();

            if (start==0 || (time-start > 5000) ) {
                start=time;
                count=1;
            } else{
                count++;
            }

            if (count==20) {
                startActivity(new Intent(FeedbackActivity.this, AppLogActivity.class));
                finish();
            }
        }
        return super.dispatchTouchEvent(ev);
    }
}
