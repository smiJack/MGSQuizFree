package com.mgs_quiz.mgsquizfree.ui;

import static com.mgs_quiz.mgsquizfree.CheckInput.isBirthdayInvalid;
import static com.mgs_quiz.mgsquizfree.CheckInput.isEmailInvalid;
import static com.mgs_quiz.mgsquizfree.CheckInput.isEmpty;
import static com.mgs_quiz.mgsquizfree.CheckInput.isGenderInvalid;
import static com.mgs_quiz.mgsquizfree.CheckInput.isPasswordInvalid;
import static com.mgs_quiz.mgsquizfree.CheckInput.isUsernameLengthInvalid;
import static com.mgs_quiz.mgsquizfree.GameData.DEFZEITSEC;
import static com.mgs_quiz.mgsquizfree.GameData.GENDER_M;
import static com.mgs_quiz.mgsquizfree.GameData.PLAY_MUSIC;
import static com.mgs_quiz.mgsquizfree.GameData.SP_BIRTHDAY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_CHANGED;
import static com.mgs_quiz.mgsquizfree.GameData.SP_COUNTRY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_COUNTRY_CODE;
import static com.mgs_quiz.mgsquizfree.GameData.SP_DEF;
import static com.mgs_quiz.mgsquizfree.GameData.SP_DRES;
import static com.mgs_quiz.mgsquizfree.GameData.SP_EMAIL;
import static com.mgs_quiz.mgsquizfree.GameData.SP_GENDER;
import static com.mgs_quiz.mgsquizfree.GameData.SP_NAMES;
import static com.mgs_quiz.mgsquizfree.GameData.SP_QLANG;
import static com.mgs_quiz.mgsquizfree.GameData.SP_REGISTERDATE;
import static com.mgs_quiz.mgsquizfree.GameData.SP_SC1AHZ_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_SC1AH_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_SC1AT_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_SC1DHZ_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_SC1DH_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_SC1DT_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_SC2AHZ_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_SC2AH_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_SC2AT_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_SC2DHZ_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_SC2DH_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_SC2DT_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_SC3AHZ_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_SC3AH_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_SC3AT_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_SC3DHZ_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_SC3DH_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_SC3DT_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_SC4AHZ_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_SC4AH_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_SC4AT_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_SC4DHZ_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_SC4DH_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_SC4DT_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_SC5AHZ_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_SC5AH_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_SC5AT_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_SC5DHZ_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_SC5DH_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_SC5DT_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_SCAT_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_SCDT_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_SCOT_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_SDK;
import static com.mgs_quiz.mgsquizfree.GameData.SP_TIMEU;
import static com.mgs_quiz.mgsquizfree.GameData.SP_UDATE;
import static com.mgs_quiz.mgsquizfree.GameData.SP_UID;
import static com.mgs_quiz.mgsquizfree.GameData.SP_USERNAME;
import static com.mgs_quiz.mgsquizfree.GameData.USERDATA;
import static com.mgs_quiz.mgsquizfree.GameData.USERNAMES_COLL;
import static com.mgs_quiz.mgsquizfree.GameData.USERNAME_UID;
import static com.mgs_quiz.mgsquizfree.GameData.USERSTATS;
import static com.mgs_quiz.mgsquizfree.GameData.USERS_COLL;
import static com.mgs_quiz.mgsquizfree.GetData.getDate;
import static com.mgs_quiz.mgsquizfree.GetData.getQLang;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;
import com.google.firebase.firestore.WriteBatch;
import com.hbb20.CountryCodePicker;
import com.mgs_quiz.mgsquizfree.GameData;
import com.mgs_quiz.mgsquizfree.GetData;
import com.mgs_quiz.mgsquizfree.R;
import com.mgs_quiz.mgsquizfree.model.Stats;
import com.mgs_quiz.mgsquizfree.model.User;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Random;

public class RegisterActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private EditText etEmailAddress;
    private EditText etPassword;
    private EditText etUsername;
    private EditText etBirthday;
    private CheckBox chbMale;
    private CheckBox chbFemale;
    private CountryCodePicker ccp;
    private TextView tvError;
    private Button btnCreateProfile;

    private Context context;

    private Calendar calendar;

    private String strBirthday;
    private String strCountry;
    private String strCountryCode;
    private String strEmail;
    private String strGender;
    private String strPW;
    private String strRegisteredOn;
    private String strUsername;

    private int intChbDefaultColor;
    private Drawable btnDrawable;

    private FirebaseAuth mAuth;
    private CollectionReference usernames;
    private CollectionReference users;

    private SharedPreferences sharedPrefs;
    private SharedPreferences.Editor prefsEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initE();
        initL();

        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        );
    }

    private void initE() {
        progressBar = findViewById(R.id.progressbarRegister);
        etEmailAddress = findViewById(R.id.emailAddressRegister);
        etPassword = findViewById(R.id.passwordRegister);
        etUsername = findViewById(R.id.usernameRegister);
        etBirthday = findViewById(R.id.birthdayRegister);
        chbMale = findViewById(R.id.genderMaleChBox);
        chbFemale = findViewById(R.id.genderFemaleChBox);
        tvError = findViewById(R.id.tvErrorMessage);
        ccp = findViewById(R.id.ccpRegister);
        btnCreateProfile = findViewById(R.id.createProfileBtn);

        context = RegisterActivity.this;
        calendar = Calendar.getInstance();

        mAuth = FirebaseAuth.getInstance();
        mAuth.useAppLanguage();
        usernames = FirebaseFirestore.getInstance().collection(USERNAMES_COLL);
        users = FirebaseFirestore.getInstance().collection(USERS_COLL);

        sharedPrefs = getApplicationContext().getSharedPreferences(SP_NAMES, MODE_PRIVATE);
        prefsEdit = sharedPrefs.edit();

        intChbDefaultColor = chbFemale.getCurrentTextColor();
        btnDrawable = btnCreateProfile.getBackground();
    }

    private void initL() {
        final DatePickerDialog.OnDateSetListener dateSetListener = (datePicker, year, monthOfYear, dayOfMonth) ->
                setBirthdate(year, monthOfYear, dayOfMonth);

        etBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePicker(dateSetListener);
            }
        });

        btnCreateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createProfile();
            }
        });
    }

    private void showDatePicker(DatePickerDialog.OnDateSetListener dateSetListener) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(RegisterActivity.this,
                        android.R.style.Theme_Holo_Dialog,
                        dateSetListener,
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    private void setBirthdate(int year, int monthOfYear, int dayOfMonth) {
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, monthOfYear);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        etBirthday.setText(GetData.getBirthdayLocal(context, calendar));
    }

    private void createProfile() {
        if (isInputValid()) {
            enableButton(false);
            setFieldsForRegistration();

            if (mAuth.getCurrentUser() != null && mAuth.getCurrentUser().isAnonymous()) {
                isUserExists();
            } else {
                preRegistration();
            }
        }
    }

    private boolean isInputValid() {
        if (isEmpty(context, etEmailAddress, etPassword,
                etUsername, etBirthday)) {
            return false;
        }

        if (isEmailInvalid(context, etEmailAddress)
                || isPasswordInvalid(context, etPassword)
                || isUsernameLengthInvalid(context, etUsername)
                || isBirthdayInvalid(context, calendar, tvError)) {
            return false;
        }

        if (isGenderInvalid(context, strGender, tvError)) {
            chbMale.setTextColor(Color.RED);
            chbFemale.setTextColor(Color.RED);
            return false;
        } else {
            chbMale.setTextColor(intChbDefaultColor);
            chbFemale.setTextColor(intChbDefaultColor);
        }

        if (isCountrySelected()) {
            tvError.setText(getText(R.string.eSelectCountry));
            tvError.setVisibility(View.VISIBLE);
            return false;
        } else {
            tvError.setVisibility(View.GONE);
        }

        return true;
    }

    public void setGender(View view) {
        if (view.getId() == chbMale.getId()) {
            chbFemale.setChecked(false);
            strGender = GENDER_M;
        } else {
            chbMale.setChecked(false);
            strGender = GameData.GENDER_W;
        }
    }

    private boolean isCountrySelected() {
        strCountry = ccp.getSelectedCountryEnglishName();
        strCountryCode = ccp.getSelectedCountryNameCode();
        return strCountry == null || strCountry.isEmpty();
    }

    private void setFieldsForRegistration() {
        strEmail = etEmailAddress.getText().toString().trim();
        strPW = etPassword.getText().toString().trim();
        strUsername = etUsername.getText().toString().trim();
        strBirthday = GetData.getDate(calendar);
        strGender = strGender;
        strCountry = strCountry;
        strCountryCode = strCountryCode;
        strRegisteredOn = GetData.getDate();
    }

    private void preRegistration() {
        mAuth.signInAnonymously()
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        isUserExists();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        String m = "Error!\n" + e.getMessage();
                        Toast.makeText(context, m, Toast.LENGTH_LONG).show();
                        showError(m);
                        enableButton(true);
                    }
                });
    }

    private void isUserExists() {
        usernames.document(strUsername.toLowerCase()).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            strUsername += (new Random().nextInt(20) + 1);
                            etUsername.setText(strUsername);
                            etUsername.setError(getText(R.string.eUsernameInUse));
                            etUsername.requestFocus();

                            enableButton(true);
                        } else {
                            register();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        String m = "Error 12!\n" + e.getMessage();
                        Toast.makeText(context, m, Toast.LENGTH_LONG).show();
                        showError(m);
                        enableButton(true);
                    }
                });
    }

    private void register() {
        progressBar.setVisibility(View.VISIBLE);

        AuthCredential credential = EmailAuthProvider.getCredential(strEmail, strPW);
        mAuth.getCurrentUser().linkWithCredential(credential)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        createUsername();

                        String userID = mAuth.getCurrentUser().getUid();

                        User user = new User(strEmail, strUsername, strGender, strBirthday,
                                strCountry, strCountryCode, strRegisteredOn, Build.VERSION.SDK_INT, getCalculatedDisplay());
                        Stats stats = new Stats(getDate());

                        HashMap<String, User> userHashMap = new HashMap<>();
                        userHashMap.put(USERDATA, user);

                        HashMap<String, Stats> userStatsMap = new HashMap<>();
                        userStatsMap.put(USERSTATS, stats);

                        WriteBatch writeBatch = FirebaseFirestore.getInstance().batch();
                        DocumentReference dr = users.document(userID);
                        writeBatch.set(dr, userHashMap, SetOptions.merge());
                        writeBatch.set(dr, userStatsMap, SetOptions.merge());

                        writeBatch.commit().addOnSuccessListener(aVoid -> {
                            Toast.makeText(context, getText(R.string.account_created_successfully), Toast.LENGTH_LONG).show();
                            createSharedPrefs();
                            progressBar.setVisibility(View.GONE);
                            startLogInActivity();
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                String m = "Error 17!\n" + e.getMessage();
                                Toast.makeText(context, m, Toast.LENGTH_LONG).show();
                                showError(m);
                                enableButton(true);
                            }
                        });
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressBar.setVisibility(View.GONE);
                        if (e instanceof FirebaseAuthUserCollisionException) {
                            Toast.makeText(context, getText(R.string.eMailAddressAlreadyInUse), Toast.LENGTH_LONG).show();
                            etEmailAddress.setError(getText(R.string.eMailAddressAlreadyInUse));
                            etEmailAddress.requestFocus();
                        } else {
                            Toast.makeText(context, "Error 18!\n" + e.getMessage(), Toast.LENGTH_LONG).show();
                            showError("Error 18!\n" + e.getMessage());
                        }
                        enableButton(true);
                    }
                });
    }

    private void createUsername() {
        HashMap<String, String> userIDMap = new HashMap<>();
        userIDMap.put(USERNAME_UID, mAuth.getUid());
        usernames.document(strUsername.toLowerCase()).set(userIDMap)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                showError("Error 0x25\n");
                enableButton(true);
            }
        });
    }

    private void createSharedPrefs() {
        prefsEdit.putString(SP_UID, mAuth.getUid());
        prefsEdit.putString(SP_EMAIL, strEmail);
        prefsEdit.putString(SP_USERNAME, strUsername);
        prefsEdit.putString(SP_GENDER, strGender);
        prefsEdit.putString(SP_BIRTHDAY, strBirthday);
        prefsEdit.putString(SP_COUNTRY, strCountry);
        prefsEdit.putString(SP_COUNTRY_CODE, strCountryCode);
        prefsEdit.putString(SP_REGISTERDATE, strRegisteredOn);
        prefsEdit.putInt(SP_SDK, Build.VERSION.SDK_INT);
        prefsEdit.putString(SP_DRES, getCalculatedDisplay());
        prefsEdit.putBoolean(PLAY_MUSIC, true);
        prefsEdit.putString(SP_QLANG, getQLang(context));

        prefsEdit.putInt(SP_SC1AH_KEY, SP_DEF);
        prefsEdit.putInt(SP_SC1AT_KEY, SP_DEF);
        prefsEdit.putInt(SP_SC1DH_KEY, SP_DEF);
        prefsEdit.putInt(SP_SC1DT_KEY, SP_DEF);
        prefsEdit.putInt(SP_SC2AH_KEY, SP_DEF);
        prefsEdit.putInt(SP_SC2AT_KEY, SP_DEF);
        prefsEdit.putInt(SP_SC2DH_KEY, SP_DEF);
        prefsEdit.putInt(SP_SC2DT_KEY, SP_DEF);
        prefsEdit.putInt(SP_SC3AH_KEY, SP_DEF);
        prefsEdit.putInt(SP_SC3AT_KEY, SP_DEF);
        prefsEdit.putInt(SP_SC3DH_KEY, SP_DEF);
        prefsEdit.putInt(SP_SC3DT_KEY, SP_DEF);
        prefsEdit.putInt(SP_SC4AH_KEY, SP_DEF);
        prefsEdit.putInt(SP_SC4AT_KEY, SP_DEF);
        prefsEdit.putInt(SP_SC4DH_KEY, SP_DEF);
        prefsEdit.putInt(SP_SC4DT_KEY, SP_DEF);
        prefsEdit.putInt(SP_SC5AH_KEY, SP_DEF);
        prefsEdit.putInt(SP_SC5AT_KEY, SP_DEF);
        prefsEdit.putInt(SP_SC5DH_KEY, SP_DEF);
        prefsEdit.putInt(SP_SC5DT_KEY, SP_DEF);

        prefsEdit.putInt(SP_SC1AHZ_KEY, DEFZEITSEC);
        prefsEdit.putInt(SP_SC1DHZ_KEY, DEFZEITSEC);
        prefsEdit.putInt(SP_SC2AHZ_KEY, DEFZEITSEC);
        prefsEdit.putInt(SP_SC2DHZ_KEY, DEFZEITSEC);
        prefsEdit.putInt(SP_SC3AHZ_KEY, DEFZEITSEC);
        prefsEdit.putInt(SP_SC3DHZ_KEY, DEFZEITSEC);
        prefsEdit.putInt(SP_SC4AHZ_KEY, DEFZEITSEC);
        prefsEdit.putInt(SP_SC4DHZ_KEY, DEFZEITSEC);
        prefsEdit.putInt(SP_SC5AHZ_KEY, DEFZEITSEC);
        prefsEdit.putInt(SP_SC5DHZ_KEY, DEFZEITSEC);
        prefsEdit.putInt(SP_SCAT_KEY, SP_DEF);
        prefsEdit.putInt(SP_SCDT_KEY, SP_DEF);
        prefsEdit.putInt(SP_SCOT_KEY, SP_DEF);
        prefsEdit.putString(SP_UDATE, strRegisteredOn);
        prefsEdit.putBoolean(SP_CHANGED, false);
        prefsEdit.putLong(SP_TIMEU, System.currentTimeMillis());

        prefsEdit.apply();
    }

    private void startLogInActivity() {
        mAuth.signOut();
        finish();
        startActivity(new Intent(context, LogInActivity.class));
    }

    private void showError(String message) {
        tvError.setText(message);
        tvError.setTextColor(getResources().getColor(R.color.etGrey));
        tvError.setVisibility(View.VISIBLE);
        enableButton(true);
    }

    private void enableButton(boolean enabled) {
        if (enabled) {
            btnCreateProfile.setBackground(btnDrawable);
        } else {
            btnCreateProfile.setBackgroundColor(getResources().getColor(R.color.etGrey));
        }
        btnCreateProfile.setEnabled(enabled);
    }

    private String getCalculatedDisplay() {
        return Resources.getSystem().getDisplayMetrics().widthPixels + "x" +
                Resources.getSystem().getDisplayMetrics().heightPixels;
    }
}
