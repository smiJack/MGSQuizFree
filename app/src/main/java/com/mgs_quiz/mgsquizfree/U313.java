package com.mgs_quiz.mgsquizfree;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mgs_quiz.mgsquizfree.model.Ranking;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Calendar;

import androidx.annotation.NonNull;

import static com.mgs_quiz.mgsquizfree.CheckData.rnd;
import static com.mgs_quiz.mgsquizfree.CheckData.rnu;
import static com.mgs_quiz.mgsquizfree.GameData.RANKING_COLL;
import static com.mgs_quiz.mgsquizfree.GameData.SP_NAMEEA;
import static com.mgs_quiz.mgsquizfree.GameData.SP_NAMEED;
import static com.mgs_quiz.mgsquizfree.GameData.SP_NAMEQ;
import static com.mgs_quiz.mgsquizfree.GameData.SP_NAMES;
import static com.mgs_quiz.mgsquizfree.GameData.SP_NAMES_EXTENSION;
import static com.mgs_quiz.mgsquizfree.GameData.SP_NAMETS;
import static com.mgs_quiz.mgsquizfree.GameData.SP_TIMED;
import static com.mgs_quiz.mgsquizfree.LogMessage.save;

public class U313 {

    private U313() {
    }

    public static void x1003(Context appContext) {
        SharedPreferences preferences = appContext.getSharedPreferences(SP_NAMES, Context.MODE_PRIVATE);

        if (rnd(preferences)) {
            CollectionReference ranking = FirebaseFirestore.getInstance().collection(RANKING_COLL);

            x1003_1(ranking.document(SP_NAMEEA), (SP_NAMEEA + SP_NAMES_EXTENSION), appContext);
            x1003_1(ranking.document(SP_NAMEED), (SP_NAMEED + SP_NAMES_EXTENSION), appContext);
            x1003_1(ranking.document(SP_NAMEQ), (SP_NAMEQ + SP_NAMES_EXTENSION), appContext);
            x1003_1(ranking.document(SP_NAMETS), (SP_NAMETS + SP_NAMES_EXTENSION), appContext);
        } else {
        }
    }

    public static void x1004(Context appContext, FirebaseAuth mAuth, SharedPreferences sharedPreferences) {
        if (rnu(sharedPreferences)) {
            U260735.u260736(appContext, mAuth, sharedPreferences);
        } else {
            save(appContext, "rnu <> ok: " + Calendar.getInstance().getTime() + "\n");
        }
    }

    private static void x1003_1(final DocumentReference document, final String file, final Context appContext) {
        document.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Ranking ranking = documentSnapshot.toObject(Ranking.class);
                x1003_2(appContext, file, ranking);

                save(appContext, "ddl ok " + file + " => " + Calendar.getInstance().getTime()
                 + "\n" + file + "\n\n");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                save(appContext, e.getMessage() + "\nx1003_1() onFailure " + file);
            }
        });
    }

    private static void x1003_2(Context appContext, String file, Ranking ranking) {
        SharedPreferences preferences = appContext.getSharedPreferences(SP_NAMES, Context.MODE_PRIVATE);
        updateT(preferences, SP_TIMED);

        try (ObjectOutputStream rankingFile =
                     new ObjectOutputStream(new BufferedOutputStream(appContext.openFileOutput(file, Context.MODE_PRIVATE)))) {
            rankingFile.writeObject(ranking);
        } catch (FileNotFoundException e) {
            save(appContext, (e.getMessage() + "\nx1003_2() FileNotFound"));
        } catch (IOException e) {
            save(appContext, (e.getMessage() + "\nx1003_2() IO"));
        }
    }

    private static void updateT(SharedPreferences preferences, String key) {
        preferences.edit().putLong(key, Calendar.getInstance().getTimeInMillis()).apply();
    }
}
