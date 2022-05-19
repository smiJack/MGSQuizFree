package com.mgs_quiz.mgsquizfree.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.RequestConfiguration;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mgs_quiz.mgsquizfree.GdprHelper;
import com.mgs_quiz.mgsquizfree.R;

import androidx.appcompat.app.AppCompatActivity;

import static com.mgs_quiz.mgsquizfree.APR.app_launched;
import static com.mgs_quiz.mgsquizfree.AppAdRequest.getAdRequest;
import static com.mgs_quiz.mgsquizfree.BDT.setBackground;
import static com.mgs_quiz.mgsquizfree.DialogShareFriend.shareWithFriend;
import static com.mgs_quiz.mgsquizfree.EnableFunction.enableButton;
import static com.mgs_quiz.mgsquizfree.GameData.ENABLE_WORLD;
import static com.mgs_quiz.mgsquizfree.GameData.SP_BIRTHDAY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_NAMES;
import static com.mgs_quiz.mgsquizfree.GameData.SP_NAME_EEA;
import static com.mgs_quiz.mgsquizfree.GameData.SP_USERNAME;
import static com.mgs_quiz.mgsquizfree.GetData.getBirthdayMessage;
import static com.mgs_quiz.mgsquizfree.U313.x1003;

public class OverviewActivity extends AppCompatActivity {

    private TextView name;
    private TextView welcome;
    private Button btnNewQuiz;
    private Button btnProfile;
    private Button btnWorld;
    private Button btnStats;
    private Button btnSendRequest;
    private Button btnIviteFriend;
    private Button btnFAQs;
    private Button btnAbout;
    private FloatingActionButton fabShare;
    private LinearLayout birthdayLayout;
    private TextView birthdayTV;
    private AdView mView;

    private Context context;
    private SharedPreferences sharedPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);

        SharedPreferences eea = getSharedPreferences(SP_NAME_EEA, MODE_PRIVATE);
        final TextView placeholder = findViewById(R.id.overviewAdPlaceholder);

        GdprHelper helper = new GdprHelper(OverviewActivity.this, eea);
        helper.initialize();

        RequestConfiguration configuration =
                new RequestConfiguration.Builder().build();
        MobileAds.setRequestConfiguration(configuration);

        //MobileAds.initialize(this);
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        MobileAds.setAppMuted(true);

        mView = findViewById(R.id.overviewView);
        AdRequest request = getAdRequest(eea);
        mView.setAdListener(new AdListener(){
            @Override
            public void onAdFailedToLoad(int error) {
                placeholder.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAdLoaded() {
                placeholder.setVisibility(View.GONE);
            }
        });
        mView.loadAd(request);

        initE();
        initL();

        loadData();
        x1003(context);
    }

    private void initE() {
        name = findViewById(R.id.appNameOverview);
        welcome = findViewById(R.id.welcomeOverview);
        btnNewQuiz = findViewById(R.id.btnStartNewQuiz);
        btnProfile = findViewById(R.id.btnShowProfile);
        btnStats = findViewById(R.id.btnShowStatsOverview);
        btnWorld = findViewById(R.id.btnShowWorld);
        btnSendRequest = findViewById(R.id.overviewSendRequestBtn);
        btnIviteFriend = findViewById(R.id.overviewBtnTellAFriend);
        btnFAQs = findViewById(R.id.overviewBtnFaq);
        btnAbout = findViewById(R.id.overviewBtnAbout);
        fabShare = findViewById(R.id.overviewFabShare);
        birthdayLayout = findViewById(R.id.overviewBirthdayLayout);
        birthdayTV = findViewById(R.id.overviewBirthdayTV);

        context = this;
        sharedPrefs = getApplicationContext().getSharedPreferences(SP_NAMES, MODE_PRIVATE);
    }

    private void initL() {
        btnNewQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startQuiz();
            }
        });

        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showProfile();
            }
        });

        btnStats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, StatsActivity.class));
            }
        });

        btnWorld.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, WorldActivity.class));
            }
        });

        btnSendRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, FeedbackActivity.class));
            }
        });

        btnIviteFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareWithFriend(context, sharedPrefs);
            }
        });

        fabShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareWithFriend(context, sharedPrefs);
            }
        });

        btnFAQs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, FAQsActivity.class));
            }
        });

        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, AboutActivity.class));
            }
        });
    }

    @Override
    protected void onDestroy() {
        if (mView != null) {
            mView.destroy();
        }
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        if (mView != null) {
            mView.pause();
        }
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mView != null) {
            mView.resume();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        showBirthdayCongrats();
        app_launched(context);
    }

    private void loadData() {
        if (sharedPrefs.contains(SP_USERNAME)) {
            welcome.setText(getString(R.string.welcome, sharedPrefs.getString(SP_USERNAME, getString(R.string.comrade))));
        } else {
            welcome.setText(R.string.welcomeDefUser);
        }

        enableButton(sharedPrefs, btnWorld, ENABLE_WORLD);
    }

    private void showBirthdayCongrats() {
        if (getBirthdayMessage(this, sharedPrefs.getString(SP_BIRTHDAY, getString(R.string.defaultString)),
                sharedPrefs.getString(SP_USERNAME, getString(R.string.unknownComrade)), birthdayTV)) {
            setBackground(this, R.color.appYellow);
            welcome.setVisibility(View.GONE);
            birthdayLayout.setVisibility(View.VISIBLE);
            name.setTextColor(Color.BLACK);
            birthdayTV.setTextColor(Color.BLACK);
        } else {
            setBackground(this, R.color.colorPrimaryDark);
            welcome.setVisibility(View.VISIBLE);
            birthdayLayout.setVisibility(View.GONE);
            name.setTextColor(getResources().getColor(R.color.appWhite));
        }
    }

    private void showProfile() {
        startActivity(new Intent(context, UserActivity.class));
    }

    private void startQuiz() {
        startActivity(new Intent(context, CategoryActivity.class));
    }
}
