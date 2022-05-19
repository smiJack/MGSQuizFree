package com.mgs_quiz.mgsquizfree;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.ads.consent.AdProvider;
import com.google.ads.consent.ConsentInformation;
import com.google.ads.consent.ConsentStatus;

import java.util.List;
import java.util.Set;

import androidx.appcompat.app.AlertDialog;

import static com.mgs_quiz.mgsquizfree.GdprUpdateEeaConsent.updateEeaConsent;
import static com.mgs_quiz.mgsquizfree.ShowPaidVersion.showPaidVersion;

public class GdprHelperDialog {

    private static final String PRIVACY_URL = "https://sites.google.com/view/metal-gear-solid-quiz/startseite/ds_mgsqf";

    private GdprHelperDialog() {}

    public static void showGdprHelperDialog(final Context context, final ConsentInformation consentInformation,
                                            final SharedPreferences eea) {

        boolean showNonPersonalizedAds = false;
        final AlertDialog euDialog;

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View eu_consent_dialog = inflater.inflate(R.layout.consent_eu, null);

        dialogBuilder.setView(eu_consent_dialog)
                .setCancelable(false)
                .setTitle(Html.fromHtml("<font color='#FF7F27'>" +
                        context.getString(R.string.app_name) + "</font>"))
                .setIcon(R.mipmap.ic_launcher_round);

        euDialog = dialogBuilder.create();
        euDialog.show();

        Button btn_eu_consent_yes = eu_consent_dialog.findViewById(R.id.eu_consent_yes);
        Button btn_eu_consent_no = eu_consent_dialog.findViewById(R.id.eu_consent_no);
        Button btn_eu_consent_remove_ads = eu_consent_dialog.findViewById(R.id.euConsentRemoveAds);

        btn_eu_consent_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                euDialog.cancel();
                Toast.makeText(context, context.getString(R.string.eu_thank_you), Toast.LENGTH_LONG).show();
                consentInformation.setConsentStatus(ConsentStatus.PERSONALIZED);
                updateEeaConsent(eea, 0, false);
            }
        });
        btn_eu_consent_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                euDialog.cancel();
                consentInformation.setConsentStatus(ConsentStatus.NON_PERSONALIZED);
                updateEeaConsent(eea, 1, false);
            }
        });
        btn_eu_consent_remove_ads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                euDialog.cancel();
                showPaidVersion(context);
            }
        });

        TextView tv_eu_learn_more = eu_consent_dialog.findViewById(R.id.euConsentLearnMore);
        tv_eu_learn_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                euMoreInfoDialog(context, consentInformation);
            }
        });
    }

    private static void euMoreInfoDialog(Context context, ConsentInformation consentInformation) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        ScrollView scrollView = new ScrollView(context);
        LinearLayout ll = new LinearLayout(context);
        ll.setOrientation(LinearLayout.VERTICAL);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(40, 20, 40, 20);

        TextView appPrivacyPolicy = new TextView(context);
        String link = "<a href=" + PRIVACY_URL + ">" + context.getResources().getString(R.string.app_name)+
                " " + context.getString(R.string.aboutPrivacyPolicy) + "</a>";
        appPrivacyPolicy.setText(Html.fromHtml(link));
        appPrivacyPolicy.setMovementMethod(LinkMovementMethod.getInstance());
        appPrivacyPolicy.setTextColor(Color.BLUE);
        ll.addView(appPrivacyPolicy, params);

        TextView googlePartners = new TextView(context);
        googlePartners.setText(R.string.eu_google_partners);
        googlePartners.setTextColor(context.getResources().getColor(R.color.colorAccent));
        googlePartners.setPadding(40,40,40,20);
        ll.addView(googlePartners, params);

        List<AdProvider> adProviders = consentInformation.getAdProviders();
        for (AdProvider adProvider : adProviders) {
            link = "<a href="+adProvider.getPrivacyPolicyUrlString()+">"+adProvider.getName()+"</a>";
            TextView tv_adprovider = new TextView(context);
            tv_adprovider.setText(Html.fromHtml(link));
            tv_adprovider.setMovementMethod(LinkMovementMethod.getInstance());
            ll.addView(tv_adprovider, params);
        }
        scrollView.addView(ll);

        Spannable title = new SpannableString(context.getString(R.string.app_name));
        title.setSpan(new ForegroundColorSpan(Color.BLUE), 0, title.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        title.setSpan(new StyleSpan(Typeface.BOLD_ITALIC), 0, title.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);

        builder.setTitle(title)
                .setView(scrollView)
                .setPositiveButton(R.string.ok, null);

        final AlertDialog createDialog = builder.create();
        createDialog.show();
    }
}
