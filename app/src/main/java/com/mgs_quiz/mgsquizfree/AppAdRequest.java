package com.mgs_quiz.mgsquizfree;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.AdRequest;

import static com.mgs_quiz.mgsquizfree.GameData.SP_EEA_NPA;

public class AppAdRequest {

    public static AdRequest getAdRequest(SharedPreferences eea) {
        int npa = eea.getInt(SP_EEA_NPA, 1);
        if (npa == 1) {
            Bundle extras = new Bundle();
            extras.putString("npa", "1");
            return new AdRequest.Builder().addNetworkExtrasBundle(AdMobAdapter.class, extras).build();
        }
        return new AdRequest.Builder().build();
    }
}
