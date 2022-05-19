package com.mgs_quiz.mgsquizfree.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mgs_quiz.mgsquizfree.HK1606;
import com.mgs_quiz.mgsquizfree.R;
import com.mgs_quiz.mgsquizfree.U313;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import static com.mgs_quiz.mgsquizfree.CheckInput.isEmpty;
import static com.mgs_quiz.mgsquizfree.CheckInput.isPasswordInvalid;
import static com.mgs_quiz.mgsquizfree.GameData.*;
import static com.mgs_quiz.mgsquizfree.GetData.getQLang;

public class LogInActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private EditText etEmailAddress;
    private EditText etPassword;
    private Button btnLogIn;
    private TextView tvRegister;
    private TextView tvRegisterWhy;
    private TextView tvLogInError;
    private TextView tvLogInForgotPW;

    private String strEmail;
    private String strPW;
    private String TAG;

    private FirebaseAuth mAuth;
    private Context context;
    private SharedPreferences sharedPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        );

        initE();
        initL();

        if (mAuth.getCurrentUser() != null) {
            if (sharedPrefs.contains(SP_USERNAME)) {
                x1003();
                x1004();
                showOverview();
            } else {
                disablePB(false);
                getUserData(mAuth.getCurrentUser());
            }
        }
    }

    private void initE() {
        progressBar = findViewById(R.id.progressbarLogIn);
        etEmailAddress = findViewById(R.id.emailAddressLogIn);
        etPassword = findViewById(R.id.passwordLogIn);
        btnLogIn = findViewById(R.id.logInButton);
        tvRegister = findViewById(R.id.registerTextView);
        tvRegisterWhy = findViewById(R.id.registerWhy);
        tvLogInError = findViewById(R.id.tvLogInError);
        tvLogInForgotPW = findViewById(R.id.logInforgotPWtv);

        TAG = "LogInAct";

        mAuth = FirebaseAuth.getInstance();
        mAuth.useAppLanguage();
        context = this;
        sharedPrefs = getApplicationContext().getSharedPreferences(SP_NAMES, MODE_PRIVATE);
    }

    private void initL() {
        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HK1606.hk(context, btnLogIn);
                logIn();
            }
        });

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startRegisterActivity();
            }
        });

        tvRegisterWhy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, FAQLoginActivity.class));
            }
        });

        tvLogInForgotPW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, ForgotPasswordActivity.class));
            }
        });
    }

    private void logIn() {
        resetErrorNote();

        if (!isEmpty(context, etEmailAddress, etPassword)) {
            strEmail = etEmailAddress.getText().toString().trim();
            strPW = etPassword.getText().toString().trim();

            if (isPasswordInvalid(context, etPassword)) {
                return;
            }

            disablePB(false);

            mAuth.signInWithEmailAndPassword(strEmail, strPW)
                    .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            if (authResult.getUser() != null) {
                                getUserData(authResult.getUser());
                            }
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            if (e instanceof FirebaseAuthInvalidCredentialsException
                                    || e instanceof FirebaseAuthInvalidUserException) {
                                etPassword.setError(getString(R.string.eUsernamePassword));
                                tvLogInError.setText(getText(R.string.eUsernamePassword));
                            } else {
                                tvLogInError.setText(e.getMessage());
                            }
                            tvLogInError.setVisibility(View.VISIBLE);
                            disablePB(true);
                        }
                    });
        }
    }

    private void getUserData(FirebaseUser user) {
        CollectionReference userdata = FirebaseFirestore.getInstance().collection(USERS_COLL);
        userdata.document(user.getUid()).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {

                        if (!sharedPrefs.contains(SP_USERNAME) || !sharedPrefs.contains(SP_SC1AT_KEY)) {
                            setPrefs(documentSnapshot);
                        }
                        x1003();
                        showOverview();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        showErrorNote(getString(R.string.eServerConnection));
                        disablePB(true);
                    }
                });
    }

    private void setPrefs(DocumentSnapshot documentSnapshot) {
        try {
            SharedPreferences.Editor prefsEditor = sharedPrefs.edit();
            prefsEditor.putString(SP_UID, mAuth.getUid());
            prefsEditor.putString(SP_USERNAME, documentSnapshot.getString(USERDATA_USERNAME));
            prefsEditor.putString(SP_EMAIL, documentSnapshot.getString(USERDATA_EMAIL));
            prefsEditor.putString(SP_GENDER, documentSnapshot.getString(USERDATA_GENDER));
            prefsEditor.putString(SP_BIRTHDAY, documentSnapshot.getString(USERDATA_BIRTHDAY));
            prefsEditor.putString(SP_COUNTRY, documentSnapshot.getString(USERDATA_COUNTRY));
            prefsEditor.putString(SP_COUNTRY_CODE, documentSnapshot.getString(USERDATA_COUNTRYCODE));
            prefsEditor.putString(SP_REGISTERDATE, documentSnapshot.getString(USERDATA_REGISTERDATE));
            prefsEditor.putInt(SP_SDK, documentSnapshot.getDouble(USERDATA_SDK).intValue());
            prefsEditor.putString(SP_DRES, documentSnapshot.getString(USERDATA_DRES));
            prefsEditor.putBoolean(PLAY_MUSIC, true);
            prefsEditor.putString(SP_QLANG, getQLang(context));

            prefsEditor.putInt(SP_SC1AH_KEY, documentSnapshot.getLong(USTATS_SC1AH).intValue());
            prefsEditor.putInt(SP_SC1AT_KEY, documentSnapshot.getLong(USTATS_SC1AT).intValue());
            prefsEditor.putInt(SP_SC1DH_KEY, documentSnapshot.getLong(USTATS_SC1DH).intValue());
            prefsEditor.putInt(SP_SC1DT_KEY, documentSnapshot.getLong(USTATS_SC1DT).intValue());
            prefsEditor.putInt(SP_SC2AH_KEY, documentSnapshot.getLong(USTATS_SC2AH).intValue());
            prefsEditor.putInt(SP_SC2AT_KEY, documentSnapshot.getLong(USTATS_SC2AT).intValue());
            prefsEditor.putInt(SP_SC2DH_KEY, documentSnapshot.getLong(USTATS_SC2DH).intValue());
            prefsEditor.putInt(SP_SC2DT_KEY, documentSnapshot.getLong(USTATS_SC2DT).intValue());
            prefsEditor.putInt(SP_SC3AH_KEY, documentSnapshot.getLong(USTATS_SC3AH).intValue());
            prefsEditor.putInt(SP_SC3AT_KEY, documentSnapshot.getLong(USTATS_SC3AT).intValue());
            prefsEditor.putInt(SP_SC3DH_KEY, documentSnapshot.getLong(USTATS_SC3DH).intValue());
            prefsEditor.putInt(SP_SC3DT_KEY, documentSnapshot.getLong(USTATS_SC3DT).intValue());
            prefsEditor.putInt(SP_SC4AH_KEY, documentSnapshot.getLong(USTATS_SC4AH).intValue());
            prefsEditor.putInt(SP_SC4AT_KEY, documentSnapshot.getLong(USTATS_SC4AT).intValue());
            prefsEditor.putInt(SP_SC4DH_KEY, documentSnapshot.getLong(USTATS_SC4DH).intValue());
            prefsEditor.putInt(SP_SC4DT_KEY, documentSnapshot.getLong(USTATS_SC4DT).intValue());
            prefsEditor.putInt(SP_SC5AH_KEY, documentSnapshot.getLong(USTATS_SC5AH).intValue());
            prefsEditor.putInt(SP_SC5AT_KEY, documentSnapshot.getLong(USTATS_SC5AT).intValue());
            prefsEditor.putInt(SP_SC5DH_KEY, documentSnapshot.getLong(USTATS_SC5DH).intValue());
            prefsEditor.putInt(SP_SC5DT_KEY, documentSnapshot.getLong(USTATS_SC5DT).intValue());

            prefsEditor.putInt(SP_SC1AHZ_KEY, documentSnapshot.getLong(USTATS_SC1AHZ).intValue());
            prefsEditor.putInt(SP_SC1DHZ_KEY, documentSnapshot.getLong(USTATS_SC1DHZ).intValue());
            prefsEditor.putInt(SP_SC2AHZ_KEY, documentSnapshot.getLong(USTATS_SC2AHZ).intValue());
            prefsEditor.putInt(SP_SC2DHZ_KEY, documentSnapshot.getLong(USTATS_SC2DHZ).intValue());
            prefsEditor.putInt(SP_SC3AHZ_KEY, documentSnapshot.getLong(USTATS_SC3AHZ).intValue());
            prefsEditor.putInt(SP_SC3DHZ_KEY, documentSnapshot.getLong(USTATS_SC3DHZ).intValue());
            prefsEditor.putInt(SP_SC4AHZ_KEY, documentSnapshot.getLong(USTATS_SC4AHZ).intValue());
            prefsEditor.putInt(SP_SC4DHZ_KEY, documentSnapshot.getLong(USTATS_SC4DHZ).intValue());
            prefsEditor.putInt(SP_SC5AHZ_KEY, documentSnapshot.getLong(USTATS_SC5AHZ).intValue());
            prefsEditor.putInt(SP_SC5DHZ_KEY, documentSnapshot.getLong(USTATS_SC5DHZ).intValue());

            prefsEditor.putInt(SP_SCALLAH_KEY, documentSnapshot.getLong(USTATS_SCALLAH).intValue());
            prefsEditor.putInt(SP_SCALLAT_KEY, documentSnapshot.getLong(USTATS_SCALLAT).intValue());
            prefsEditor.putInt(SP_SCALLDH_KEY, documentSnapshot.getLong(USTATS_SCALLDH).intValue());
            prefsEditor.putInt(SP_SCALLDT_KEY, documentSnapshot.getLong(USTATS_SCALLDT).intValue());
            prefsEditor.putInt(SP_SCALLAHZ, documentSnapshot.getLong(USTATS_SCALLAHZ).intValue());
            prefsEditor.putInt(SP_SCALLDHZ, documentSnapshot.getLong(USTATS_SCALLDHZ).intValue());
            prefsEditor.putInt(SP_QTOTALALLA_KEY, documentSnapshot.getLong(USTATS_QTOTALALLA).intValue());
            prefsEditor.putInt(SP_QTOTALALLD_KEY, documentSnapshot.getLong(USTATS_QTOTALALLD).intValue());
            prefsEditor.putInt(SP_QTOTALAALLA_KEY, documentSnapshot.getLong(USTATS_QTOTALAALLA).intValue());
            prefsEditor.putInt(SP_QTOTALAALLD_KEY, documentSnapshot.getLong(USTATS_QTOTALAALLD).intValue());

            prefsEditor.putInt(SP_SCAT_KEY, documentSnapshot.getLong(USTATS_SCAT).intValue());
            prefsEditor.putInt(SP_SCDT_KEY, documentSnapshot.getLong(USTATS_SCDT).intValue());
            prefsEditor.putInt(SP_SCOT_KEY, documentSnapshot.getLong(USTATS_SCOT).intValue());
            prefsEditor.putInt(SP_QTOTALA_KEY, documentSnapshot.getLong(USTATS_QTOTALA).intValue());
            prefsEditor.putInt(SP_QTOTALAA_KEY, documentSnapshot.getLong(USTATS_QTOTALAA).intValue());
            prefsEditor.putInt(SP_QTOTALD_KEY, documentSnapshot.getLong(USTATS_QTOTALD).intValue());
            prefsEditor.putInt(SP_QTOTALDA_KEY, documentSnapshot.getLong(USTATS_QTOTALDA).intValue());
            prefsEditor.putInt(SP_QTOTALOV_KEY, documentSnapshot.getLong(USTATS_QTOTALOV).intValue());
            prefsEditor.putInt(SP_QTOTALOVA_KEY, documentSnapshot.getLong(USTATS_QTOTALOVA).intValue());
            prefsEditor.putInt(SP_QTOTAL1A_KEY, documentSnapshot.getLong(USTATS_QTOTAL1A).intValue());
            prefsEditor.putInt(SP_QTOTAL1D_KEY, documentSnapshot.getLong(USTATS_QTOTAL1D).intValue());
            prefsEditor.putInt(SP_QTOTALA1A_KEY, documentSnapshot.getLong(USTATS_QTOTALA1A).intValue());
            prefsEditor.putInt(SP_QTOTALA1D_KEY, documentSnapshot.getLong(USTATS_QTOTALA1D).intValue());
            prefsEditor.putInt(SP_QTOTAL2A_KEY, documentSnapshot.getLong(USTATS_QTOTAL2A).intValue());
            prefsEditor.putInt(SP_QTOTAL2D_KEY, documentSnapshot.getLong(USTATS_QTOTAL2D).intValue());
            prefsEditor.putInt(SP_QTOTALA2A_KEY, documentSnapshot.getLong(USTATS_QTOTALA2A).intValue());
            prefsEditor.putInt(SP_QTOTALA2D_KEY, documentSnapshot.getLong(USTATS_QTOTALA2D).intValue());
            prefsEditor.putInt(SP_QTOTAL3A_KEY, documentSnapshot.getLong(USTATS_QTOTAL3A).intValue());
            prefsEditor.putInt(SP_QTOTAL3D_KEY, documentSnapshot.getLong(USTATS_QTOTAL3D).intValue());
            prefsEditor.putInt(SP_QTOTALA3A_KEY, documentSnapshot.getLong(USTATS_QTOTALA3A).intValue());
            prefsEditor.putInt(SP_QTOTALA3D_KEY, documentSnapshot.getLong(USTATS_QTOTALA3D).intValue());
            prefsEditor.putInt(SP_QTOTAL4A_KEY, documentSnapshot.getLong(USTATS_QTOTAL4A).intValue());
            prefsEditor.putInt(SP_QTOTAL4D_KEY, documentSnapshot.getLong(USTATS_QTOTAL4D).intValue());
            prefsEditor.putInt(SP_QTOTALA4A_KEY, documentSnapshot.getLong(USTATS_QTOTALA4A).intValue());
            prefsEditor.putInt(SP_QTOTALA4D_KEY, documentSnapshot.getLong(USTATS_QTOTALA4D).intValue());
            prefsEditor.putInt(SP_QTOTAL5A_KEY, documentSnapshot.getLong(USTATS_QTOTAL5A).intValue());
            prefsEditor.putInt(SP_QTOTAL5D_KEY, documentSnapshot.getLong(USTATS_QTOTAL5D).intValue());
            prefsEditor.putInt(SP_QTOTALA5A_KEY, documentSnapshot.getLong(USTATS_QTOTALA5A).intValue());
            prefsEditor.putInt(SP_QTOTALA5D_KEY, documentSnapshot.getLong(USTATS_QTOTALA5D).intValue());
            prefsEditor.putInt(SP_QCANSWERSA_KEY, documentSnapshot.getLong(USTATS_QCANSWERSA).intValue());
            prefsEditor.putInt(SP_QCANSWERSD_KEY, documentSnapshot.getLong(USTATS_QCANSWERSD).intValue());
            prefsEditor.putString(SP_UDATE, documentSnapshot.getString(USTATS_UDATE));
            prefsEditor.putLong(SP_TIMEU, System.currentTimeMillis());

            prefsEditor.commit();
            setCCP();
        } catch (Exception e) {
            showErrorNote(e.getMessage());
        }
        x1003();
        showOverview();
    }

    private void x1003() {
        U313.x1003(getApplicationContext());
    }

    private void x1004() {
        U313.x1004(getApplicationContext(), mAuth, sharedPrefs);
    }

    private void setCCP() {
        String ccp = sharedPrefs.getString(SP_COUNTRY_CODE, getString(R.string.defaultString));
        if (!ccp.equalsIgnoreCase(getString(R.string.defaultString))) {
            SharedPreferences preferences = getApplicationContext().getSharedPreferences(SP_NAMEC, MODE_PRIVATE);
            preferences.edit().putString(SP_CCP, ccp).apply();
        }
    }

    private void startRegisterActivity() {
        mAuth.signOut();
        startActivity(new Intent(this, RegisterActivity.class));
    }

    private void showOverview() {
        disablePB(true);
        startActivity(new Intent(context, OverviewActivity.class));
        finish();
    }

    private void disablePB(boolean jein) {
        if (jein) {
            progressBar.setVisibility(View.GONE);
        } else {
            progressBar.setVisibility(View.VISIBLE);
        }
    }

    private void showErrorNote(String message) {
        String e = getString(R.string.eRetrievingData);
        if (message != null)
            e += "\n" + message;
        tvLogInError.setText(e);
        tvLogInError.setVisibility(View.VISIBLE);
    }

    private void resetErrorNote() {
        tvLogInError.setText(getText(R.string.eGeneral));
        tvLogInError.setVisibility(View.GONE);
    }
}
