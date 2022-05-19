package com.mgs_quiz.mgsquizfree.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.ads.consent.ConsentInformation;
import com.mgs_quiz.mgsquizfree.GdprHelper;
import com.mgs_quiz.mgsquizfree.LogMessage;
import com.mgs_quiz.mgsquizfree.R;

import java.util.HashMap;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;

import static com.mgs_quiz.mgsquizfree.GameData.SP_NAME_EEA;
import static com.mgs_quiz.mgsquizfree.ShowPaidVersion.showPaidVersion;
import static com.mgs_quiz.mgsquizfree.ShowPlayEntry.showPlayEntry;

public class AboutActivity extends AppCompatActivity {

    private Map<Integer, Integer> menu;
    private TextView rateApp;
    private TextView resetAds;
    private TextView privacyPolicy;
    private TextView showProVersion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        initE();
        initL();
        showAdsSettings();
    }

    private void showAdsSettings() {
        ConsentInformation consentInformation = ConsentInformation.getInstance(AboutActivity.this);
        if (consentInformation.isRequestLocationInEeaOrUnknown()) {
            TextView tv = findViewById(R.id.aboutLblResetAds);
            tv.setVisibility(View.VISIBLE);
        }
    }

    private void initE() {
        menu = new HashMap<>();
        menu.put(R.id.aboutLblSupportApp, R.id.aboutTVSupportAppDesc);
        menu.put(R.id.aboutLblResetAds, R.id.aboutTVResetAdsDesc);
        menu.put(R.id.aboutLblRateApp, R.id.aboutRateAppMessage);
        menu.put(R.id.aboutLblUsername, R.id.aboutTVUsername);
        menu.put(R.id.aboutLblEmail, R.id.aboutTVEmail);
        menu.put(R.id.aboutLblBirthday, R.id.aboutTVBirthday);
        menu.put(R.id.aboutLblDeleteProfile, R.id.aboutTVDeleteProfile);
        menu.put(R.id.aboutLblStoredData, R.id.aboutTVStoredData);
        menu.put(R.id.aboutLblContact, R.id.aboutTVContact);
        menu.put(R.id.aboutLblOther, R.id.aboutTVOther);
        menu.put(R.id.aboutLblPrivacyPolicy, R.id.aboutPrivacyPolicyDesc);

        privacyPolicy = findViewById(R.id.aboutPrivacyPolicyDesc);
        rateApp = findViewById(R.id.aboutRateAppShow);
        resetAds = findViewById(R.id.aboutResetAds);
        showProVersion = findViewById(R.id.aboutTVSupportAppLink);
    }

    private void initL() {
        privacyPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    startActivity(new Intent(AboutActivity.this, PrivacyActivity.class));
                } catch (Exception e) {
                    Toast.makeText(AboutActivity.this, getString(R.string.eGeneral), Toast.LENGTH_LONG).show();
                }
            }
        });

        rateApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPlayEntry(AboutActivity.this);
            }
        });

        resetAds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GdprHelper helper = new GdprHelper(AboutActivity.this, getSharedPreferences(SP_NAME_EEA, MODE_PRIVATE));
                helper.resetConsent();
            }
        });

        showProVersion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPaidVersion(AboutActivity.this);
            }
        });
    }

    public void expandMenu(View view) {
        try {
            if (menu.containsKey(view.getId())) {
                View menuItem = findViewById(menu.get(view.getId()));
                if (menuItem.getVisibility() != View.VISIBLE) {
                    menuItem.setVisibility(View.VISIBLE);
                    if (view.getId() == R.id.aboutLblRateApp) {
                        rateApp.setVisibility(View.VISIBLE);
                    } else if (view.getId() == R.id.aboutLblResetAds) {
                        resetAds.setVisibility(View.VISIBLE);
                    } else if (view.getId() == R.id.aboutLblSupportApp) {
                        showProVersion.setVisibility(View.VISIBLE);
                    }
                } else {
                    menuItem.setVisibility(View.GONE);
                    if (view.getId() == R.id.aboutLblRateApp) {
                        rateApp.setVisibility(View.GONE);
                    } else if (view.getId() == R.id.aboutLblResetAds) {
                        resetAds.setVisibility(View.GONE);
                    } else if (view.getId() == R.id.aboutLblSupportApp) {
                        showProVersion.setVisibility(View.GONE);
                    }
                }
            }
        } catch (Exception e) {
            LogMessage.save(this, e.getMessage());
        }
    }
}
