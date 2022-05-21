package com.mgs_quiz.mgsquizfree.ui;

import static com.mgs_quiz.mgsquizfree.APR.app_launched;
import static com.mgs_quiz.mgsquizfree.AppAdRequest.getAdRequest;
import static com.mgs_quiz.mgsquizfree.GameData.CAT_ALL;
import static com.mgs_quiz.mgsquizfree.GameData.DEFZEITSEC;
import static com.mgs_quiz.mgsquizfree.GameData.DEFZEITSECEXP;
import static com.mgs_quiz.mgsquizfree.GameData.DIFFICULTY_A;
import static com.mgs_quiz.mgsquizfree.GameData.DIFFICULTY_D;
import static com.mgs_quiz.mgsquizfree.GameData.IKEY_CATEGORY;
import static com.mgs_quiz.mgsquizfree.GameData.IKEY_DIFFICULTY;
import static com.mgs_quiz.mgsquizfree.GameData.IKEY_Q_ANSWERED;
import static com.mgs_quiz.mgsquizfree.GameData.IKEY_Q_ANSWERED_ROW;
import static com.mgs_quiz.mgsquizfree.GameData.IKEY_Q_TOTAL;
import static com.mgs_quiz.mgsquizfree.GameData.IKEY_SCORE;
import static com.mgs_quiz.mgsquizfree.GameData.IKEY_SPIELZEIT;
import static com.mgs_quiz.mgsquizfree.GameData.INTERSTITIAL;
import static com.mgs_quiz.mgsquizfree.GameData.INTERSTITIAL_NOS;
import static com.mgs_quiz.mgsquizfree.GameData.MAX_SCOREA;
import static com.mgs_quiz.mgsquizfree.GameData.MAX_SCOREAEXP;
import static com.mgs_quiz.mgsquizfree.GameData.MAX_SCORED;
import static com.mgs_quiz.mgsquizfree.GameData.MAX_SCOREDEXP;
import static com.mgs_quiz.mgsquizfree.GameData.SP_CHANGED;
import static com.mgs_quiz.mgsquizfree.GameData.SP_DEF;
import static com.mgs_quiz.mgsquizfree.GameData.SP_INT_E;
import static com.mgs_quiz.mgsquizfree.GameData.SP_NAMES;
import static com.mgs_quiz.mgsquizfree.GameData.SP_NAME_EEA;
import static com.mgs_quiz.mgsquizfree.GameData.SP_QTOTALOVA_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_QTOTALOV_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_SCAT_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_SCDT_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_SCOT_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_USERNAME;
import static com.mgs_quiz.mgsquizfree.GetData.getKeys;
import static com.mgs_quiz.mgsquizfree.GetFormattedNumber.getFormattedNumber;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.mgs_quiz.mgsquizfree.R;
import com.mgs_quiz.mgsquizfree.U313;

import java.util.Locale;

public class ScoreActivity extends AppCompatActivity {

    private ImageView ivTrophy;
    private TextView congratsScore;
    private TextView questionsAnswered;
    private TextView lblQAnsweredRowC;
    private TextView tvQAnsweredRowC;
    private TextView lblQAnsweredRowR;
    private TextView tvQAnsweredRowR;
    private TextView scoreCurrent;
    private TextView scoreHighest;
    private TextView scoreTotal;
    private TextView aktuelleZeit;
    private TextView bestzeit;
    private TextView overallScore;

    private FloatingActionButton fabShare;
    private FloatingActionButton fabOverview;
    private FloatingActionButton fabPlayAgain;
    private FloatingActionButton fabStats;
    private Button btnOverview;
    private Button btnPlayAgain;
    private Button btnStats;

    private Context context;
    private SharedPreferences sharedPrefs;
    private SharedPreferences.Editor prefsEditor;

    private String category;
    private String difficulty;
    private int scoreScot = 0;
    private int totalScore;

    private boolean updateNecessary = true;
    private InterstitialAd ad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        SharedPreferences eea = getSharedPreferences(SP_NAME_EEA, MODE_PRIVATE);
        final TextView placeholder = findViewById(R.id.scoreAdPlaceholder);

        AdView view = findViewById(R.id.scoreView);
        AdRequest request = getAdRequest(eea);
        view.setAdListener(new AdListener(){
            @Override
            public void onAdFailedToLoad(LoadAdError adError) {
                placeholder.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAdLoaded() {
                placeholder.setVisibility(View.GONE);
            }
        });
        view.loadAd(request);

        initE();
        initL();
        preloadInterstitial(eea);

        if (savedInstanceState != null) updateNecessary = false;
        updateCongrats();
    }

    private void initE() {
        ivTrophy = findViewById(R.id.ivTrophyScore);
        congratsScore = findViewById(R.id.tvCongratsScore);
        questionsAnswered = findViewById(R.id.scoreTVQuestionsAnswered);
        lblQAnsweredRowC = findViewById(R.id.scoreLblQuestionsAnsweredRowCurrent);
        tvQAnsweredRowC = findViewById(R.id.scoreTVQuestionsAnsweredRowCurrent);
        lblQAnsweredRowR = findViewById(R.id.scoreLblQuestionsAnsweredRowRecord);
        tvQAnsweredRowR = findViewById(R.id.scoreTVQuestionsAnsweredRowRecord);
        scoreCurrent = findViewById(R.id.tvScoreCurrentScore);
        scoreHighest = findViewById(R.id.tvScoreHighestScore);
        scoreTotal = findViewById(R.id.tvScoreTotalScore);

        aktuelleZeit = findViewById(R.id.tvAktuelleZeitScore);
        bestzeit = findViewById(R.id.tvBestzeitScore);
        overallScore = findViewById(R.id.tvScoreOverallScore);

        fabShare = findViewById(R.id.scoreFabShare);
        fabOverview = findViewById(R.id.scoreFabMainMenu);
        fabPlayAgain = findViewById(R.id.scoreFabPlayAgain);
        fabStats = findViewById(R.id.scoreFabShowStats);
        btnOverview = findViewById(R.id.btnMainMenuScore);
        btnPlayAgain = findViewById(R.id.btnPlayAgainScore);
        btnStats = findViewById(R.id.btnShowStatsScore);

        context = this;
        sharedPrefs = getApplicationContext().getSharedPreferences(SP_NAMES, MODE_PRIVATE);
        prefsEditor = sharedPrefs.edit();
    }

    private void initL() {
        fabShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle(Html.fromHtml(getString(R.string.shareTitle)))
                        .setSingleChoiceItems(R.array.shareScoreNew, -1, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which) {
                                    case 0:
                                        shareResults(getString(R.string.shareScoreTotalNew, totalScore, category, difficulty));
                                        break;
                                    case 1:
                                        shareResults(getString(R.string.shareScoreOverallNew, scoreScot));
                                        break;
                                    default:
                                        shareResults(getString(R.string.shareScoreOverallNew, sharedPrefs.getInt(SP_SCOT_KEY, 0)));
                                        break;
                                }
                            }
                        })
                        .setNegativeButton(R.string.shareCancel, null);
                builder.create().show();
            }
        });

        fabOverview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMainMenu();
            }
        });
        btnOverview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showMainMenu();
            }
        });

        fabPlayAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAd();
            }
        });
        btnPlayAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAd();
            }
        });

        fabStats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showStats();
            }
        });
        btnStats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showStats();
            }
        });
    }

    private void preloadInterstitial(SharedPreferences eea) {
        int count = sharedPrefs.getInt(INTERSTITIAL_NOS, 0);
        if (count >= 2) {
            count = 0;
            sharedPrefs.edit().putInt(INTERSTITIAL_NOS, count).apply();
            ad.setFullScreenContentCallback(new FullScreenContentCallback() {
                @Override
                public void onAdClicked() {
                    super.onAdClicked();
                }

                public void onAdDismissedFullScreenContent() {
                    // Called when fullscreen content is dismissed.
                    playAgain();
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
            sharedPrefs.edit().putInt(INTERSTITIAL_NOS, count).apply();
        }

    }

    private void shareResults(String message) {
        Intent app = new Intent(Intent.ACTION_SEND);
        app.setType("text/plain");
        app.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name));
        app.putExtra(Intent.EXTRA_TEXT, message);

        try {
            startActivity(Intent.createChooser(app, getString(R.string.shareSelectApp)));
        } catch (Exception e) {
            Toast.makeText(this, getString(R.string.eGeneral), Toast.LENGTH_LONG).show();
        }
    }

    private void showMainMenu() {
        startActivity(new Intent(this, OverviewActivity.class));
        finish();
    }

    private void playAgain() {
        String category = getIntent().getStringExtra(IKEY_CATEGORY);
        String difficulty = getIntent().getStringExtra(IKEY_DIFFICULTY);

        if ((category != null && !category.isEmpty() )
                || (difficulty != null && (difficulty.equalsIgnoreCase(DIFFICULTY_A) || difficulty.equalsIgnoreCase(DIFFICULTY_D)))) {
            Intent intent = new Intent(this, QuizActivity.class);
            intent.putExtra(IKEY_CATEGORY, category);
            intent.putExtra(IKEY_DIFFICULTY, difficulty);
            startActivity(intent);
            finish();
        } else {
            errorMessage(null);
        }
    }

    private void showAd() {
        if (ad != null) {
            ad.show(ScoreActivity.this);
        } else {
            playAgain();
        }
    }

    private void updateCongrats() {
        String user = sharedPrefs.getString(SP_USERNAME, null);
        difficulty = getIntent().getStringExtra(IKEY_DIFFICULTY);
        category = getIntent().getStringExtra(IKEY_CATEGORY);
        String key_h = "";
        String key_t = "";
        String key_ts = "";
        String key_z = "";

        String kcqtd = "";
        String kcqtda = "";
        String kqtd = "";
        String kqtda ="";
        String keyQAnsweredInARow = null;

        String message;
        int maxScore = 0;
        int qAnsweredCurrent = getIntent().getIntExtra(IKEY_Q_ANSWERED, SP_DEF);
        int qTotalCurrent = getIntent().getIntExtra(IKEY_Q_TOTAL, SP_DEF);
        int qAnswered = 0;
        int qAnsweredInARowCurrent = getIntent().getIntExtra(IKEY_Q_ANSWERED_ROW, SP_DEF);
        int qAnsweredInARowBest = 0;
        int qTotal = 0;
        int qCatTotal = 0;
        int qCatTotalAns = 0;
        int score = (int) getIntent().getDoubleExtra(IKEY_SCORE, SP_INT_E);
        int spielzeit = getIntent().getIntExtra(IKEY_SPIELZEIT, SP_DEF);
        int spielzeitM = spielzeit / 60;
        int spielzeitS = spielzeit % 60;
        int spielzeitBest = 0;
        int spielzeitBestM = 0;
        int spielzeitBestS = 0;
        int highestScore = 0;
        totalScore = 0;
        scoreScot = sharedPrefs.getInt(SP_SCOT_KEY, SP_DEF) + score;
        int scoreTsDif = 0;

        if (user == null || difficulty == null || category == null || difficulty.isEmpty()
                || category.isEmpty() || score == SP_INT_E) {
            errorMessage(null);
        }

        if (category.equalsIgnoreCase(CAT_ALL)) {
            lblQAnsweredRowC.setVisibility(View.VISIBLE);
            tvQAnsweredRowC.setVisibility(View.VISIBLE);
            lblQAnsweredRowR.setVisibility(View.VISIBLE);
            tvQAnsweredRowR.setVisibility(View.VISIBLE);
        }

        try {
            String[] keys = getKeys(category, difficulty);
            key_h = keys[0];
            key_t = keys[1];
            key_z = keys[2];

            kcqtd = keys[3];
            kcqtda = keys[4];
            kqtd = keys[5];
            kqtda = keys[6];
            if (keys[7] != null) {
                keyQAnsweredInARow = keys[7];
                qAnsweredInARowBest = sharedPrefs.getInt(keyQAnsweredInARow, SP_DEF);
            }

            qTotal = sharedPrefs.getInt(kqtd, SP_DEF);
            qAnswered = sharedPrefs.getInt(kqtda, SP_DEF);
            qCatTotal = sharedPrefs.getInt(kcqtd, SP_DEF);
            qCatTotalAns = sharedPrefs.getInt(kcqtda, SP_DEF);

            qTotal += qTotalCurrent;
            qAnswered += qAnsweredCurrent;
            qCatTotal += qTotalCurrent;
            qCatTotalAns += qAnsweredCurrent;

            if (difficulty.equalsIgnoreCase(DIFFICULTY_A)) {
                maxScore = (category.equalsIgnoreCase(CAT_ALL)) ? MAX_SCOREAEXP : MAX_SCOREA;
                key_ts = SP_SCAT_KEY;
                difficulty = Locale.getDefault().getLanguage().equalsIgnoreCase("ru") ? getString(R.string.difficultyNormal) : difficulty;
            } else if (difficulty.equalsIgnoreCase(DIFFICULTY_D)) {
                maxScore = (category.equalsIgnoreCase(CAT_ALL)) ? MAX_SCOREDEXP : MAX_SCORED;
                key_ts = SP_SCDT_KEY;
                difficulty = Locale.getDefault().getLanguage().equalsIgnoreCase("ru") ? getString(R.string.difficultyHard) : difficulty;
            } else {
                maxScore = MAX_SCOREA;
                key_ts = SP_SCAT_KEY;
            }

            highestScore = sharedPrefs.getInt(key_h, SP_DEF);
            spielzeitBest = sharedPrefs.getInt(key_z, SP_DEF);
            spielzeitBestM = spielzeitBest / 60;
            spielzeitBestS = spielzeitBest % 60;
            totalScore = sharedPrefs.getInt(key_t, SP_DEF);
            totalScore += score;
            scoreTsDif = sharedPrefs.getInt(key_ts, SP_DEF);
            scoreTsDif += score;

            category = (category.equalsIgnoreCase(CAT_ALL)) ? getString(R.string.metal_gear_solid_expert) : category;
        } catch (Exception e) {
            errorMessage(e.getMessage());
        }

        if (score > highestScore) {
            message = getString(R.string.congratsScoreNewHighscore, user, category, difficulty);
        } else if (score == highestScore && score == maxScore) {
            message = getString(R.string.congratsScoreHighscoreAgain, user, category, difficulty);
        } else if (score == highestScore) {
            message = getString(R.string.congratsScoreSameScore, user, score, category, difficulty);
        } else if (score < highestScore) {
            message = getString(R.string.congratsScoreLessScore, user);
            setImageTrophy();
        } else {
            message = getString(R.string.congratsScoreGeneral, user);
            setImageTrophy();
        }

        congratsScore.setText(Html.fromHtml(message));
        questionsAnswered.setText(getString(R.string.questionsAnsweredNumber, qAnsweredCurrent, qTotalCurrent));
        tvQAnsweredRowC.setText(getString(R.string.statsQuestions, getFormattedNumber(qAnsweredInARowCurrent)));
        tvQAnsweredRowR.setText(getString(R.string.statsQuestions, getFormattedNumber(qAnsweredInARowBest)));
        scoreCurrent.setText(getString(R.string.scorePoints, getFormattedNumber(score)));

        if (highestScore > SP_DEF) {
            scoreHighest.setText(getString(R.string.scorePoints, getFormattedNumber(highestScore)));
        } else {
            scoreHighest.setText(R.string.scoreNo);
        }
        scoreTotal.setText(getString(R.string.scorePoints, getFormattedNumber(totalScore)));
        aktuelleZeit.setText(getString(R.string.scoreTime, spielzeitM, spielzeitS));

        if (spielzeitBest == DEFZEITSEC || spielzeitBest == DEFZEITSECEXP || spielzeitBest == 0 ) {
            bestzeit.setText(R.string.scoreBestzeitNo);
        } else {
            bestzeit.setText(getString(R.string.scoreTime, spielzeitBestM, spielzeitBestS));
        }
        overallScore.setText(getString(R.string.scorePoints, getFormattedNumber(scoreScot)));

        if (score > highestScore) {
            prefsEditor.putInt(key_h, score);
        }
        if (sharedPrefs.contains(key_z)) {
            if (spielzeit < spielzeitBest) {
                prefsEditor.putInt(key_z, spielzeit);
            }
        } else {
            prefsEditor.putInt(key_z, spielzeit);
        }
        if (keyQAnsweredInARow != null) {
            if (qAnsweredInARowCurrent > qAnsweredInARowBest) {
                prefsEditor.putInt(keyQAnsweredInARow, qAnsweredInARowCurrent);
            }
        }
        prefsEditor.putInt(key_ts, scoreTsDif);
        prefsEditor.putInt(SP_SCOT_KEY, scoreScot);
        prefsEditor.putInt(key_t, totalScore);

        prefsEditor.putInt(kcqtd, qCatTotal);
        prefsEditor.putInt(kcqtda, qCatTotalAns);
        prefsEditor.putInt(kqtd, qTotal);
        prefsEditor.putInt(kqtda, qAnswered);
        prefsEditor.putInt(SP_QTOTALOV_KEY, (qTotalCurrent + sharedPrefs.getInt(SP_QTOTALOV_KEY, SP_DEF)));
        prefsEditor.putInt(SP_QTOTALOVA_KEY, (qAnsweredCurrent + sharedPrefs.getInt(SP_QTOTALOVA_KEY, SP_DEF)));

        if (updateNecessary) {
            prefsEditor.putBoolean(SP_CHANGED, true);
            prefsEditor.apply();

            app_launched(context);

            U313.x1004(context, FirebaseAuth.getInstance(), sharedPrefs);
        }
    }

    private void errorMessage(String extraMessage) {
        if (extraMessage == null) {
            extraMessage = "";
        }

        Toast.makeText(this, getText(R.string.eDefault) + "\n" + extraMessage, Toast.LENGTH_LONG).show();
        showMainMenu();
    }

    private void setImageTrophy() {
        ivTrophy.setImageResource(R.drawable.emoticon_640);
        ivTrophy.setContentDescription(getString(R.string.eDescTrophySad));
    }

    private void showStats() {
        finish();
        startActivity(new Intent(context, StatsActivity.class));
    }
}
