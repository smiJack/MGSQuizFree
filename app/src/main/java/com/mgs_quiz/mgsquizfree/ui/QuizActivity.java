package com.mgs_quiz.mgsquizfree.ui;

import static com.mgs_quiz.mgsquizfree.AppAdRequest.getAdRequest;
import static com.mgs_quiz.mgsquizfree.GameData.BKEY_ANSWEREDQCOUNTER;
import static com.mgs_quiz.mgsquizfree.GameData.BKEY_ANSWERS_SELECTABLE;
import static com.mgs_quiz.mgsquizfree.GameData.BKEY_GIVEN_ANSWER;
import static com.mgs_quiz.mgsquizfree.GameData.BKEY_QANSWERED;
import static com.mgs_quiz.mgsquizfree.GameData.BKEY_QANSWEREDROW;
import static com.mgs_quiz.mgsquizfree.GameData.BKEY_QANSWEREDSECTION;
import static com.mgs_quiz.mgsquizfree.GameData.BKEY_QUESTIONS;
import static com.mgs_quiz.mgsquizfree.GameData.BKEY_SCORE;
import static com.mgs_quiz.mgsquizfree.GameData.BKEY_SELECTED_VIEW;
import static com.mgs_quiz.mgsquizfree.GameData.BKEY_SPIELZEIT;
import static com.mgs_quiz.mgsquizfree.GameData.BKEY_TIMELEFTINMILLIS;
import static com.mgs_quiz.mgsquizfree.GameData.CAT_ALL;
import static com.mgs_quiz.mgsquizfree.GameData.COUNTDOWN_INTERVAL;
import static com.mgs_quiz.mgsquizfree.GameData.COUNTDOWN_IN_MILLIS;
import static com.mgs_quiz.mgsquizfree.GameData.IKEY_CATEGORY;
import static com.mgs_quiz.mgsquizfree.GameData.IKEY_DIFFICULTY;
import static com.mgs_quiz.mgsquizfree.GameData.IKEY_Q_ANSWERED;
import static com.mgs_quiz.mgsquizfree.GameData.IKEY_Q_ANSWERED_ROW;
import static com.mgs_quiz.mgsquizfree.GameData.IKEY_Q_TOTAL;
import static com.mgs_quiz.mgsquizfree.GameData.IKEY_SCORE;
import static com.mgs_quiz.mgsquizfree.GameData.IKEY_SPIELZEIT;
import static com.mgs_quiz.mgsquizfree.GameData.INTERSTITIAL;
import static com.mgs_quiz.mgsquizfree.GameData.PLAY_MUSIC;
import static com.mgs_quiz.mgsquizfree.GameData.SCORE_LIMIT_E;
import static com.mgs_quiz.mgsquizfree.GameData.SP_DEFZEITQ;
import static com.mgs_quiz.mgsquizfree.GameData.SP_DEFZEITSTR;
import static com.mgs_quiz.mgsquizfree.GameData.SP_NAMES;
import static com.mgs_quiz.mgsquizfree.GameData.SP_NAME_EEA;
import static com.mgs_quiz.mgsquizfree.GameData.SP_QLANG;
import static com.mgs_quiz.mgsquizfree.GetData.getRate;
import static com.mgs_quiz.mgsquizfree.GetData.getTimeLimit;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.RequestConfiguration;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.mgs_quiz.mgsquizfree.R;
import com.mgs_quiz.mgsquizfree.dao.QuizDBHelper;
import com.mgs_quiz.mgsquizfree.model.Question;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;
import java.util.Random;

public class QuizActivity extends AppCompatActivity {

    private boolean playMusic = false;
    private TextView tvQuestionsCount;
    private TextView tvCategory;
    private TextView tvDifficulty;
    private TextView tvCountdown;
    private TextView tvQuestion;
    private TextView tvOption1;
    private TextView tvOption2;
    private TextView tvOption3;
    private TextView tvOption4;

    private Context context;
    private Handler handler;
    private Runnable runQuestion;
    private Runnable runSolution;
    private SharedPreferences sharedPrefs;
    private View.OnClickListener optionsListener;
    private View viewSelected;

    private CountDownTimer countDownTimer;
    private ColorStateList defaultTextColorCountdown;
    private ColorStateList defaultTextColorOptions;
    private long timeLeftInMillis;

    private QuizDBHelper quizDB;
    private ArrayList<Question> questions = new ArrayList<>();
    // count the number of shown questions
    private int answeredQuestionsCounter;
    private int questionCountTotal;
    private String difficultyLevel;
    private String category;
    private Question currentQuestion;
    private double score;
    private int scoreLimit;
    private double scoreRate;
    private String answer;
    private boolean answersSelectable;
    private int qAnswered;
    private int qAnsweredRow;
    private int qAnsweredSection;
    private boolean runCountdown;
    private int spielzeit;

    private Drawable qAus;
    private Drawable qRic;
    private Drawable qFal;
    private Drawable qSta;

    private InterstitialAd ad;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        SharedPreferences eea = getSharedPreferences(SP_NAME_EEA, MODE_PRIVATE);
        final TextView placeholder = findViewById(R.id.quizAdPlaceholder);

        RequestConfiguration configuration =
                new RequestConfiguration.Builder().build();
        MobileAds.setRequestConfiguration(configuration);

        AdView view = findViewById(R.id.quizView);
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

        ad.setFullScreenContentCallback(new FullScreenContentCallback() {
            @Override
            public void onAdClicked() {
                super.onAdClicked();
            }

            public void onAdDismissedFullScreenContent() {
                // Called when fullscreen content is dismissed.
                finishQuiz();
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


        initE();
        initV();
        initL();

        if (savedInstanceState != null) {
            restoreInstance(savedInstanceState);

            if (answersSelectable) {
                startCountdownTimer();
            } else {
                showSolution(viewSelected);
            }
        } else {
            showNextQuestion();
        }

        displayQuizData();
    }

    private void initE() {
        tvQuestionsCount = findViewById(R.id.tvQuestionsCountQuiz);
        tvCategory = findViewById(R.id.tvCategoryQuiz);
        tvDifficulty = findViewById(R.id.tvDifficultyLevelQuiz);
        tvCountdown = findViewById(R.id.tvCountdownQuiz);
        tvQuestion = findViewById(R.id.tvQuestionQuiz);
        tvOption1 = findViewById(R.id.tvOption1Quiz);
        tvOption2 = findViewById(R.id.tvOption2Quiz);
        tvOption3 = findViewById(R.id.tvOption3Quiz);
        tvOption4 = findViewById(R.id.tvOption4Quiz);

        context = this;
        handler = new Handler(getMainLooper());
        runQuestion = new Runnable() {
            @Override
            public void run() {
                showNextQuestion();
            }
        };
        runSolution = new Runnable() {
            @Override
            public void run() {
                showSolution(viewSelected);
            }
        };
        sharedPrefs = getApplicationContext().getSharedPreferences(SP_NAMES, MODE_PRIVATE);

        quizDB = QuizDBHelper.getInstance(context);
    }

    private void initV() {
        timeLeftInMillis = 0;

        defaultTextColorCountdown = tvCountdown.getTextColors();
        defaultTextColorOptions = tvOption1.getTextColors();
        difficultyLevel = getIntent().getStringExtra(IKEY_DIFFICULTY);
        category = getIntent().getStringExtra(IKEY_CATEGORY);

        score = 0;
        scoreLimit = getTimeLimit(difficultyLevel);
        scoreRate = getRate(difficultyLevel);

        if (difficultyLevel == null || category == null) {
            returnToMain();
        }

        String qLang = sharedPrefs.getString(SP_QLANG, Locale.getDefault().getLanguage());
        questions = quizDB.getAllQuestionsOfCategory(category, difficultyLevel, (qLang.length()>2) ? qLang.substring(qLang.indexOf("(")+1, qLang.length()-1) : qLang);
        answeredQuestionsCounter = 0;
        questionCountTotal = questions.size();
        answersSelectable = true;
        qAnswered = 0;
        qAnsweredRow = 0;
        qAnsweredSection = 0;
        runCountdown = false;
        spielzeit = 0;

        playMusic = sharedPrefs.getBoolean(PLAY_MUSIC, false);
        qAus = getDrawable(R.drawable.question_s);
        qRic = getDrawable(R.drawable.question_c);
        qFal = getDrawable(R.drawable.question_f);
        qSta = getDrawable(R.drawable.question);
    }

    private void initL() {
        optionsListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answersSelectable = false;
                optionsSelectable(answersSelectable);
                checkAnswer(view);
            }
        };

        tvOption1.setOnClickListener(optionsListener);
        tvOption2.setOnClickListener(optionsListener);
        tvOption3.setOnClickListener(optionsListener);
        tvOption4.setOnClickListener(optionsListener);
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (runCountdown && viewSelected != null) {
            showSolution(viewSelected);
        } else if (runCountdown) {
            startCountdownTimer();
        }
    }

    private void displayQuizData() {
        tvQuestionsCount.setText(updateQuestionsCounter(answeredQuestionsCounter, questionCountTotal));
        tvCategory.setText(getString(R.string.category,
                (category.equalsIgnoreCase(CAT_ALL)) ?
                        getString(R.string.metal_gear_solid_expert) : category));
        tvDifficulty.setText(getString(R.string.difficulty, difficultyLevel));
    }

    private String updateQuestionsCounter(int answeredQuestionsCounter, int questionCountTotal) {
        return String.format(getResources().getString(R.string.questionsCount),
                String.valueOf(answeredQuestionsCounter), String.valueOf(questionCountTotal));
    }

    private void showNextQuestion() {
        // check whether there are still questions to show
        if (answeredQuestionsCounter < questionCountTotal) {
            currentQuestion = questions.get(answeredQuestionsCounter);

            // reset the Textcolor to default textcolor
            tvOption1.setBackground(qSta);
            tvOption2.setBackground(qSta);
            tvOption3.setBackground(qSta);
            tvOption4.setBackground(qSta);
            if (viewSelected != null) {
                // resets the textColor of the previous selection to default before the selectedView is reseted
                ((TextView)viewSelected).setTextColor(defaultTextColorOptions);
            }

            answer = null;
            viewSelected = null;

            ArrayList<String> options = new ArrayList<>();
            options.add(currentQuestion.getOption1());
            options.add(currentQuestion.getOption2());
            options.add(currentQuestion.getOption3());
            options.add(currentQuestion.getOption4());
            Collections.shuffle(options);

            currentQuestion.setOption1(options.get(0));
            currentQuestion.setOption2(options.get(1));
            currentQuestion.setOption3(options.get(2));
            currentQuestion.setOption4(options.get(3));

            if (category.equalsIgnoreCase(CAT_ALL)) {
                tvQuestion.setText(currentQuestion.getCategory() + ": " + currentQuestion.getQuestion());
            } else {
                tvQuestion.setText(currentQuestion.getQuestion());
            }
            tvOption1.setText(currentQuestion.getOption1());
            tvOption2.setText(currentQuestion.getOption2());
            tvOption3.setText(currentQuestion.getOption3());
            tvOption4.setText(currentQuestion.getOption4());

            answeredQuestionsCounter++;
            tvQuestionsCount.setText(updateQuestionsCounter(answeredQuestionsCounter, questionCountTotal));

            answersSelectable = true;
            optionsSelectable(answersSelectable);

            timeLeftInMillis = COUNTDOWN_IN_MILLIS;
            startCountdownTimer();
        } else {
            if (ad != null && (new Random().nextInt(5) > 1)) {
                ad.show(QuizActivity.this);
            } else {
                finishQuiz();
            }
        }
    }

    private void checkAnswer(View view) {
        countDownTimer.cancel();
        viewSelected = view;

        view.setBackground(qAus);
        Animation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(1000);
        anim.setStartOffset(20);
        anim.setRepeatMode(Animation.REVERSE);
        anim.setRepeatCount(2);
        viewSelected.startAnimation(anim);

        answer = ((TextView) viewSelected).getText().toString();

        if (answer.equals(currentQuestion.getOptionC())) {
            int time = (int) timeLeftInMillis / 1000;
            if (scoreLimit == SCORE_LIMIT_E) {
                score++;
            } else if (time >= scoreLimit) {
                score += scoreRate;
            } else {
                score++;
            }
            qAnswered++;

            if (category.equals(CAT_ALL)) {
                qAnsweredSection++;
                if (qAnsweredSection > qAnsweredRow) {
                    qAnsweredRow++;
                }
            }
        } else {
            qAnsweredSection = 0;
        }
        saveQuizDuration(answer.equals(currentQuestion.getOptionC()));

        handler.postDelayed(runSolution, 3000);
    }

    private void saveQuizDuration(boolean answered) {
        int dauer = (int) (COUNTDOWN_IN_MILLIS / 1000 - timeLeftInMillis / 1000);
        if (dauer <= SP_DEFZEITQ && !answered) {
            spielzeit += dauer + SP_DEFZEITSTR;
        } else {
            spielzeit += COUNTDOWN_IN_MILLIS / 1000 - timeLeftInMillis / 1000;
        }
    }

    private void showSolution(View view) {
        try {
            if (view != null) {
                if (currentQuestion.getOptionC().equals(answer)) {
                    view.setBackground(qRic);
                } else {
                    ((TextView)view).setTextColor(defaultTextColorCountdown);
                    view.setBackground(qFal);
                }
            } else {
                // means view IS null, therefore qAnsweredSection must be reset to 0
                qAnsweredSection = 0;
            }

            if (tvOption1.getText().toString().equals(currentQuestion.getOptionC())) {
                tvOption1.setBackground(qRic);
            }

            if (tvOption2.getText().toString().equals(currentQuestion.getOptionC())) {
                tvOption2.setBackground(qRic);
            }

            if (tvOption3.getText().toString().equals(currentQuestion.getOptionC())) {
                tvOption3.setBackground(qRic);
            }

            if (tvOption4.getText().toString().equals(currentQuestion.getOptionC())) {
                tvOption4.setBackground(qRic);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        handler.postDelayed(runQuestion, 1200);
    }

    private void optionsSelectable(boolean selectable) {
        tvOption1.setClickable(selectable);
        tvOption2.setClickable(selectable);
        tvOption3.setClickable(selectable);
        tvOption4.setClickable(selectable);
    }

    private void startCountdownTimer() {
        countDownTimer = new CountDownTimer(timeLeftInMillis, COUNTDOWN_INTERVAL) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateCountdown();
            }

            @Override
            public void onFinish() {
                saveQuizDuration(false);
                showSolution(null);
            }
        }.start();
    }

    private void updateCountdown() {
        int secondsLeft = (int) timeLeftInMillis / 1000;
        String secondsAsText = "";
        if (secondsLeft < 10) {
            secondsAsText = "0" + secondsLeft;
            tvCountdown.setTextColor(Color.RED);
        } else {
            secondsAsText = String.valueOf(secondsLeft);
            tvCountdown.setTextColor(defaultTextColorCountdown);
        }
        tvCountdown.setText(secondsAsText);
    }

    private void finishQuiz() {
        Intent intent = new Intent(context, ScoreActivity.class);
        intent.putExtra(IKEY_CATEGORY, category);
        intent.putExtra(IKEY_DIFFICULTY, difficultyLevel);
        intent.putExtra(IKEY_Q_ANSWERED, qAnswered);
        intent.putExtra(IKEY_Q_ANSWERED_ROW, qAnsweredRow);
        intent.putExtra(IKEY_Q_TOTAL, questionCountTotal);
        intent.putExtra(IKEY_SCORE, score);
        intent.putExtra(IKEY_SPIELZEIT, spielzeit);
        finish();
        startActivity(intent);
    }

    private void returnToMain() {
        startActivity(new Intent(this, OverviewActivity.class));
        Toast.makeText(this, getText(R.string.eQuestions), Toast.LENGTH_LONG).show();
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (playMusic) {
            if (mediaPlayer == null) {
                mediaPlayer = MediaPlayer.create(context, R.raw.main_theme);
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mp.seekTo(0);
                        mp.start();
                    }
                });
            }
            mediaPlayer.start();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (countDownTimer != null) {
            runCountdown = true;
            countDownTimer.cancel();
            handler.removeCallbacksAndMessages(null);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        handler.removeCallbacksAndMessages(null);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(BKEY_ANSWEREDQCOUNTER, answeredQuestionsCounter);
        outState.putBoolean(BKEY_ANSWERS_SELECTABLE, answersSelectable);
        outState.putInt(BKEY_QANSWERED, qAnswered);
        outState.putInt(BKEY_QANSWEREDROW, qAnsweredRow);
        outState.putInt(BKEY_QANSWEREDSECTION, qAnsweredSection);
        outState.putDouble(BKEY_SCORE, score);
        outState.putParcelableArrayList(BKEY_QUESTIONS, questions);
        outState.putInt(BKEY_SPIELZEIT, spielzeit);
        outState.putLong(BKEY_TIMELEFTINMILLIS, timeLeftInMillis);
        if (!answersSelectable) {
            outState.putInt(BKEY_SELECTED_VIEW, viewSelected.getId());
            outState.putString(BKEY_GIVEN_ANSWER, answer);
            super.onSaveInstanceState(outState);
        }
    }

    private void restoreInstance(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        try {
            answeredQuestionsCounter = savedInstanceState.getInt(BKEY_ANSWEREDQCOUNTER);
            answersSelectable = savedInstanceState.getBoolean(BKEY_ANSWERS_SELECTABLE);
            qAnswered = savedInstanceState.getInt(BKEY_QANSWERED);
            qAnsweredRow = savedInstanceState.getInt(BKEY_QANSWEREDROW);
            qAnsweredSection = savedInstanceState.getInt(BKEY_QANSWEREDSECTION);
            score = savedInstanceState.getDouble(BKEY_SCORE);
            questions = savedInstanceState.getParcelableArrayList(BKEY_QUESTIONS);
            spielzeit = savedInstanceState.getInt(BKEY_SPIELZEIT);
            timeLeftInMillis = savedInstanceState.getLong(BKEY_TIMELEFTINMILLIS);
            answer = savedInstanceState.getString(BKEY_GIVEN_ANSWER);

            viewSelected  = findViewById(savedInstanceState.getInt(BKEY_SELECTED_VIEW));
        } catch (Exception e) {
            System.out.println("exception in restoreInstance()");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        currentQuestion = questions.get(answeredQuestionsCounter-1);

        tvQuestion.setText(currentQuestion.getQuestion());
        tvOption1.setText(currentQuestion.getOption1());
        tvOption2.setText(currentQuestion.getOption2());
        tvOption3.setText(currentQuestion.getOption3());
        tvOption4.setText(currentQuestion.getOption4());
    }
}
