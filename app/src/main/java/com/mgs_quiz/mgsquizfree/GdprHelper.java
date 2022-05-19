package com.mgs_quiz.mgsquizfree;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.google.ads.consent.ConsentForm;
import com.google.ads.consent.ConsentFormListener;
import com.google.ads.consent.ConsentInfoUpdateListener;
import com.google.ads.consent.ConsentInformation;
import com.google.ads.consent.ConsentStatus;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;

import static com.mgs_quiz.mgsquizfree.GdprHelperDialog.showGdprHelperDialog;
import static com.mgs_quiz.mgsquizfree.GdprUpdateEeaConsent.updateEeaConsent;
import static com.mgs_quiz.mgsquizfree.LogMessage.save;
import static com.mgs_quiz.mgsquizfree.ShowPaidVersion.showPaidVersion;

public class GdprHelper {

    private Context appContext;
    private SharedPreferences sharedPreferences;

    private static final String[] PUBLISHER_ID = {"pub-9109965128312930"};
    private static final String PRIVACY_URL = "https://sites.google.com/view/metal-gear-solid-quiz/startseite/ds_mgsqf";
    private ConsentForm consentForm;

    public GdprHelper(Context appContext, SharedPreferences sharedPreferences) {
        this.appContext = appContext;
        this.sharedPreferences = sharedPreferences;
    }

    public void initialize() {
        final ConsentInformation consentInformation = ConsentInformation.getInstance(appContext);

        consentInformation.requestConsentInfoUpdate(PUBLISHER_ID, new ConsentInfoUpdateListener() {
            @Override
            public void onConsentInfoUpdated(ConsentStatus consentStatus) {
                // User's consent status successfully updated.
                if (consentStatus == ConsentStatus.UNKNOWN
                        && ConsentInformation.getInstance(appContext).isRequestLocationInEeaOrUnknown()) {

                    switch (Locale.getDefault().getLanguage()) {
                        case "de":
                            showGdprHelperDialog(appContext, consentInformation, sharedPreferences);
                            break;
                        case "ru":
                            showGdprHelperDialog(appContext, consentInformation, sharedPreferences);
                            break;
                        default:
                            displayConsentForm();
                    }
                }
            }

            @Override
            public void onFailedToUpdateConsentInfo(String errorDescription) {
                // User's consent status failed to update.
            }
        });
    }

    public void resetConsent() {
        ConsentInformation consentInformation = ConsentInformation.getInstance(appContext);
        consentInformation.reset();
        if (updateEeaConsent(sharedPreferences, 0, true)) {
            initialize();
        }
    }

    private void displayConsentForm() {

        if (getPrivacyUrl() == null) {
            return;
        }

        consentForm = new ConsentForm.Builder(appContext, getPrivacyUrl())
                .withListener(new ConsentFormListener() {
                    @Override
                    public void onConsentFormLoaded() {
                        consentForm.show();
                    }

                    @Override
                    public void onConsentFormOpened() {
                        // Consent form was displayed.
                    }

                    @Override
                    public void onConsentFormClosed(
                            ConsentStatus consentStatus, Boolean userPrefersAdFree) {
                        // Consent form was closed. This callback method contains all the data about user's selection, that you can use.
                        if (userPrefersAdFree) {
                            showPaidVersion(appContext);
                        } else if (consentStatus == ConsentStatus.NON_PERSONALIZED) {
                            updateEeaConsent(sharedPreferences, 1, false);
                        } else if (consentStatus == ConsentStatus.UNKNOWN) {
                            updateEeaConsent(sharedPreferences, 1, true);
                        } else {
                            updateEeaConsent(sharedPreferences, 0, false);
                        }

                    }

                    @Override
                    public void onConsentFormError(String errorDescription) {
                        if (BuildConfig.BUILD_TYPE.equals("debug")) {
                            Toast.makeText(appContext, errorDescription, Toast.LENGTH_LONG).show();
                            save(appContext, errorDescription);

                        }
                    }
                })
                .withPersonalizedAdsOption()
                .withNonPersonalizedAdsOption()
                .withAdFreeOption()
                .build();
        consentForm.load();
    }

    private URL getPrivacyUrl() {
        URL privacyUrl = null;
        try {
            privacyUrl = new URL(PRIVACY_URL);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return privacyUrl;
    }
}
