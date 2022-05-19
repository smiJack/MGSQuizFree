package com.mgs_quiz.mgsquizfree;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;
import com.google.firebase.firestore.WriteBatch;
import com.mgs_quiz.mgsquizfree.model.Stats;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;

import static com.mgs_quiz.mgsquizfree.GameData.SP_BIRTHDAY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_CHANGED;
import static com.mgs_quiz.mgsquizfree.GameData.SP_COUNTRY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_COUNTRY_CODE;
import static com.mgs_quiz.mgsquizfree.GameData.SP_SCOREU;
import static com.mgs_quiz.mgsquizfree.GameData.SP_SCOT_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_TIMEU;
import static com.mgs_quiz.mgsquizfree.GameData.SP_UDATE;
import static com.mgs_quiz.mgsquizfree.GameData.USERDATA;
import static com.mgs_quiz.mgsquizfree.GameData.USERSTATS;
import static com.mgs_quiz.mgsquizfree.GameData.USERS_COLL;
import static com.mgs_quiz.mgsquizfree.GetData.getDate;
import static com.mgs_quiz.mgsquizfree.GetData.getStats;
import static com.mgs_quiz.mgsquizfree.LogMessage.save;

public class U260735 {

    private U260735() { }

    public static void u260736(final Context appContext, FirebaseAuth mAuth, final SharedPreferences prefs) {
        try {
            Map<String, Map<String, String>> userHashMap = new HashMap<>();
            userHashMap.put(USERDATA, getUserMap(prefs));

            Map<String, Stats> statsHashMap = new HashMap<>();
            Stats stats = getStats(appContext);
            statsHashMap.put(USERSTATS, stats);

            WriteBatch writeBatch = FirebaseFirestore.getInstance().batch();
            DocumentReference dr = FirebaseFirestore.getInstance().collection(USERS_COLL).document(mAuth.getUid());
            writeBatch.set(dr, userHashMap, SetOptions.merge());

            if (stats != null) {
                writeBatch.set(dr, statsHashMap, SetOptions.merge());
            }

            writeBatch.commit().addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {

                    prefs.edit().putString(SP_UDATE, getDate()).apply();
                    prefs.edit().putBoolean(SP_CHANGED, false).apply();
                    prefs.edit().putInt(SP_SCOREU, prefs.getInt(SP_SCOT_KEY, 0)).apply();
                    prefs.edit().putLong(SP_TIMEU, Calendar.getInstance().getTimeInMillis()).apply();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(appContext, "Error 21!", Toast.LENGTH_LONG).show();
                    save(appContext, "error 21!\n" + e.getMessage());
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            save(appContext, "e 22: " + e.getMessage());
        }
    }

    private static Map<String, String> getUserMap(SharedPreferences prefs) {
        Map<String, String> userMap = new HashMap<>();
        userMap.put(SP_BIRTHDAY, prefs.getString(SP_BIRTHDAY, "12.06.2008"));
        userMap.put(SP_COUNTRY, prefs.getString(SP_COUNTRY, "Japan"));
        userMap.put(SP_COUNTRY_CODE, prefs.getString(SP_COUNTRY_CODE, "jp"));
        return userMap;
    }
}