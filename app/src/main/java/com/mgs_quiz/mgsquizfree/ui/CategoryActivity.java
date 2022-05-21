package com.mgs_quiz.mgsquizfree.ui;

import static com.mgs_quiz.mgsquizfree.AppAdRequest.getAdRequest;
import static com.mgs_quiz.mgsquizfree.EnableFunction.enableButton;
import static com.mgs_quiz.mgsquizfree.GameData.ENABLE_DIF;
import static com.mgs_quiz.mgsquizfree.GameData.ENABLE_EXPERT;
import static com.mgs_quiz.mgsquizfree.GameData.IKEY_CATEGORY;
import static com.mgs_quiz.mgsquizfree.GameData.IKEY_DIFFICULTY;
import static com.mgs_quiz.mgsquizfree.GameData.INTERSTITIAL;
import static com.mgs_quiz.mgsquizfree.GameData.INTERSTITIAL_NOC;
import static com.mgs_quiz.mgsquizfree.GameData.SP_NAMES;
import static com.mgs_quiz.mgsquizfree.GameData.SP_NAME_EEA;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.mgs_quiz.mgsquizfree.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CategoryActivity extends AppCompatActivity {

    private Button btnNormal;
    private Button btnHard;
    private Button tvMgs1;
    private Button tvMgs2;
    private Button tvMgs3;
    private Button tvMgs4;
    private Button tvMgs5;
    private Button tvExpert;
    private Button btnPlay;

    private String difficulty;
    private String category;
    private List<View> categories;
    private List<View> difficulties;
    private SharedPreferences sharedPrefs;
    private InterstitialAd ad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        initE();
        initV();
        initL();

        int count = sharedPrefs.getInt(INTERSTITIAL_NOC, 0);
        SharedPreferences eea = getSharedPreferences(SP_NAME_EEA, MODE_PRIVATE);
        if (count >= 2) {
            count = 0;
            sharedPrefs.edit().putInt(INTERSTITIAL_NOC, count).apply();
            ad.setFullScreenContentCallback(new FullScreenContentCallback() {
                @Override
                public void onAdClicked() {
                    super.onAdClicked();
                }

                public void onAdDismissedFullScreenContent() {
                    // Called when fullscreen content is dismissed.
                    startQuiz();
                }

                @Override
                public void onAdFailedToShowFullScreenContent(AdError adError) {
                    // Called when fullscreen content failed to show.
                }

                @Override
                public void onAdShowedFullScreenContent() {
                    // Called when fullscreen content is shown.
                    // Make sure to set your reference to null so you don't
                    // show it a second time.
                    ad = null;
                }
            });
            InterstitialAd.load(this, INTERSTITIAL, getAdRequest(eea),
                    new InterstitialAdLoadCallback() {
                        @Override
                        public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                            ad = null;
                        }

                        @Override
                        public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                            ad = interstitialAd;
                        }
                    });
        } else {
            count++;
            sharedPrefs.edit().putInt(INTERSTITIAL_NOC, count).apply();
        }

        enableButton(sharedPrefs, tvExpert, ENABLE_EXPERT);
        enableButton(sharedPrefs, btnNormal, ENABLE_DIF);
        enableButton(sharedPrefs, btnHard, ENABLE_DIF);
    }

    private void initE() {
        btnNormal = findViewById(R.id.difficultyNormalCategory);
        btnHard = findViewById(R.id.difficultyHardCategory);
        tvMgs1 = findViewById(R.id.mgs1Category);
        tvMgs2 = findViewById(R.id.mgs2Category);
        tvMgs3 = findViewById(R.id.mgs3Category);
        tvMgs4 = findViewById(R.id.mgs4Category);
        tvMgs5 = findViewById(R.id.mgs5Category);
        tvExpert = findViewById(R.id.categoryTVExpert);
        btnPlay = findViewById(R.id.startQuizCategory);

        categories = new ArrayList<>();
        difficulties = new ArrayList<>();

        sharedPrefs = getApplicationContext().getSharedPreferences(SP_NAMES, MODE_PRIVATE);
    }

    private void initV() {
        category = getString(R.string.tagMGS1);
        categories.add(tvMgs1);
        categories.add(tvMgs2);
        categories.add(tvMgs3);
        categories.add(tvMgs4);
        categories.add(tvMgs5);
        categories.add(tvExpert);
        difficulty = getString(R.string.tagNormal);
        difficulties.add(btnNormal);
        difficulties.add(btnHard);
    }

    private void initL() {
        View.OnClickListener myListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (view == btnNormal || view == btnHard) {
                        for (View view1 : difficulties) {
                            view1.setBackgroundColor(Color.BLACK);
                        }

                        difficulty = ((TextView) view).getTag().toString();
                        view.setBackgroundColor(Color.BLUE);
                    } else {
                        for (View view1 : categories) {
                            view1.setBackgroundColor(Color.BLACK);
                        }
                        category = ((TextView) view).getTag().toString();
                        view.setBackgroundColor(Color.BLUE);
                    }
                } catch (Exception e) {
                    category = null;
                    difficulty = null;
                    showErrorToast(null);
                }
            }
        };

        btnNormal.setOnClickListener(myListener);
        btnHard.setOnClickListener(myListener);
        tvMgs1.setOnClickListener(myListener);
        tvMgs2.setOnClickListener(myListener);
        tvMgs3.setOnClickListener(myListener);
        tvMgs4.setOnClickListener(myListener);
        tvMgs5.setOnClickListener(myListener);
        tvExpert.setOnClickListener(myListener);

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAd();
            }
        });
    }

    private void showErrorToast(String message) {
        if (message == null) {
            message = getString(R.string.eCategory);
        }
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    private void showAd() {
        if (ad != null && (new Random().nextInt(2) == 1)) {
            ad.show(CategoryActivity.this);
        } else {
            startQuiz();
        }
    }

    private void startQuiz() {
        Intent intent = new Intent(this, QuizActivity.class);
        if (difficulty == null || category == null) {
            showErrorToast(null);
            return;
        }
        intent.putExtra(IKEY_CATEGORY, category);
        intent.putExtra(IKEY_DIFFICULTY, difficulty);

        finish();
        startActivity(intent);
    }

}
