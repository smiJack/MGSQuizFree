package com.mgs_quiz.mgsquizfree.ui;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.hbb20.CountryCodePicker;
import com.mgs_quiz.mgsquizfree.R;

import java.util.Calendar;

import androidx.appcompat.app.AppCompatActivity;

import static com.mgs_quiz.mgsquizfree.BDT.setBackground;
import static com.mgs_quiz.mgsquizfree.CheckInput.isBirthdayInvalid;
import static com.mgs_quiz.mgsquizfree.CheckInput.isEmpty;
import static com.mgs_quiz.mgsquizfree.GameData.PLAY_MUSIC;
import static com.mgs_quiz.mgsquizfree.GameData.SP_BIRTHDAY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_COUNTRY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_COUNTRY_CODE;
import static com.mgs_quiz.mgsquizfree.GameData.SP_EMAIL;
import static com.mgs_quiz.mgsquizfree.GameData.SP_GENDER;
import static com.mgs_quiz.mgsquizfree.GameData.SP_NAMES;
import static com.mgs_quiz.mgsquizfree.GameData.SP_QLANG;
import static com.mgs_quiz.mgsquizfree.GameData.SP_UDATE;
import static com.mgs_quiz.mgsquizfree.GameData.SP_USERNAME;
import static com.mgs_quiz.mgsquizfree.GetData.getBirthdayLocal;
import static com.mgs_quiz.mgsquizfree.GetData.getBirthdayMessageShort;
import static com.mgs_quiz.mgsquizfree.GetData.getDate;
import static com.mgs_quiz.mgsquizfree.GetData.getGender;
import static com.mgs_quiz.mgsquizfree.GetData.getUserBirthday;

public class UserActivity extends AppCompatActivity {

    private boolean birthdayChanged;
    private boolean dataChanged;
    private Calendar calendarUser;
    private Context context;
    private SharedPreferences sharedPrefs;

    private FloatingActionButton fabDone;
    private FloatingActionButton fabBack;
    private ImageView playMusic;
    private ProgressBar progressBar;
    private TextView tvName;
    private TextView tvEmail;
    private TextView tvBirthday;
    private TextView tvGender;
    private CountryCodePicker ccp;
    private TextView qLangChooser;
    private TextView bdInfo;
    private TextView tvError;

    private String name;
    private String email;
    private String birthday;
    private String country;
    private String countryCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        initE();
        showProgressBar(true);
        initL();
        initV();
        showUserData();
        showBirthdayBackground();
    }

    private void initE() {
        calendarUser = Calendar.getInstance();
        context = this;
        dataChanged = false;
        sharedPrefs = getApplicationContext().getSharedPreferences(SP_NAMES, MODE_PRIVATE);

        fabBack = findViewById(R.id.userBackFab);
        fabDone = findViewById(R.id.userDoneFab);
        playMusic = findViewById(R.id.userPlayMusic);
        progressBar = findViewById(R.id.progressbarUser);
        tvName = findViewById(R.id.profileNameUser);
        tvEmail = findViewById(R.id.emailUser);
        tvBirthday = findViewById(R.id.birthdayUser);
        tvGender = findViewById(R.id.genderUser);
        ccp = findViewById(R.id.ccpUser);
        qLangChooser = findViewById(R.id.userTvQLang);
        bdInfo = findViewById(R.id.userTVbdInfo);
        tvError = findViewById(R.id.tvErrorUser);
    }

    private void initL() {
        fabBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        fabDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isInputValid()) {
                    dataChanged = true;
                    fabDone.getBackground().setTint(getResources().getColor(R.color.etGrey));

                    showBirthdayBackground();
                    showBirthdayMessage(birthdayChanged);
                    birthdayChanged = false;
                }
            }
        });

        final DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                setBirthday(year, monthOfYear, dayOfMonth);
                activateFabDone();
            }
        };

        tvBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePicker(dateSetListener);
            }
        });

        ccp.setOnCountryChangeListener(new CountryCodePicker.OnCountryChangeListener() {
            @Override
            public void onCountrySelected() {
                country = ccp.getSelectedCountryEnglishName();
                countryCode = ccp.getSelectedCountryNameCode();

                activateFabDone();
            }
        });

        qLangChooser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAvailableLanguages();
            }
        });

        playMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean play = false;
                if (sharedPrefs.getBoolean(PLAY_MUSIC, false)) {
                    playMusic.setImageResource(R.drawable.ic_volume_off_black_24dp);
                } else {
                    play = true;
                    playMusic.setImageResource(R.drawable.ic_volume_up_black_24dp);
                }
                sharedPrefs.edit().putBoolean(PLAY_MUSIC, play).apply();
            }
        });
    }

    private void initV() {
        name = sharedPrefs.getString(SP_USERNAME, getString(R.string.unknownComrade));
        email = sharedPrefs.getString(SP_EMAIL, getString(R.string.defaultString));
        birthday = sharedPrefs.getString(SP_BIRTHDAY, getString(R.string.defaultString));
        country = sharedPrefs.getString(SP_COUNTRY, ccp.getSelectedCountryEnglishName());
        countryCode = sharedPrefs.getString(SP_COUNTRY_CODE, ccp.getSelectedCountryCode());

        calendarUser = getUserBirthday(birthday);
        birthdayChanged = false;
        birthday = getBirthdayLocal(context, calendarUser);
        if (!sharedPrefs.getBoolean(PLAY_MUSIC, false)) playMusic.setImageResource(R.drawable.ic_volume_off_black_24dp);
        qLangChooser.setText(sharedPrefs.getString(SP_QLANG, getString(R.string.defaultString)));
    }

    private void activateFabDone() {
        dataChanged = true;
        fabDone.getBackground().setTint(getResources().getColor(R.color.colorAccent));
    }

    @Override
    public void onBackPressed() {
        if (dataChanged) {
            showProgressBar(true);
            updatePrefs();
        }
        finish();
    }

    private void showAvailableLanguages() {
        final int[] selection = new int[]{-1};
        final AlertDialog.Builder langChooser = new AlertDialog.Builder(context);
        langChooser.setTitle(Html.fromHtml(getString(R.string.userprofileSelectQLang)))
                .setSingleChoiceItems(R.array.questions_lang, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        selection[0] = which;
                    }
                })
                .setNegativeButton(R.string.shareCancel, null)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (selection[0]) {
                            case 0:
                                qLangChooser.setText(getString(R.string.questionLangEN));
                                sharedPrefs.edit().putString(SP_QLANG, getString(R.string.questionLangEN)).apply();
                                break;
                            case 1:
                                qLangChooser.setText(getString(R.string.questionLangDE));
                                sharedPrefs.edit().putString(SP_QLANG, getString(R.string.questionLangDE)).apply();
                                break;
                            case 2:
                                qLangChooser.setText(getString(R.string.questionLangRU));
                                sharedPrefs.edit().putString(SP_QLANG, getString(R.string.questionLangRU)).apply();
                                break;
                            default:
                                qLangChooser.setText(getString(R.string.questionLangEN));
                                sharedPrefs.edit().putString(SP_QLANG, getString(R.string.questionLangEN)).apply();
                        }
                        activateFabDone();
                    }
                })
                .setCancelable(true)
                .create();
        langChooser.show();
    }

    private void showBirthdayBackground() {
        if (getBirthdayMessageShort(UserActivity.this, getDate(calendarUser),
                sharedPrefs.getString(SP_USERNAME, getString(R.string.unknownComrade)), bdInfo)) {
            setBackground(UserActivity.this, R.color.appYellow);
        } else {
            setBackground(UserActivity.this, R.color.colorPrimaryDark);
        }
    }

    private void showBirthdayMessage(boolean showMessage) {
        if (!showMessage) {
            return;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(UserActivity.this);

        builder.setMessage(bdInfo.getText())
                .setPositiveButton(R.string.ok, null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showUserData() {
        tvName.setText(name);
        tvEmail.setText(getString(R.string.email, email));
        tvBirthday.setText(getString(R.string.birthdayUser, birthday));
        tvGender.setText(getString(R.string.genderUser, getGender(context, sharedPrefs.getString(SP_GENDER, getString(R.string.defaultString)))));

        showProgressBar(false);
    }

    private void setBirthday(int year, int monthOfYear, int dayOfMonth) {
        calendarUser.set(Calendar.YEAR, year);
        calendarUser.set(Calendar.MONTH, monthOfYear);
        calendarUser.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        birthday = getBirthdayLocal(context, calendarUser);
        tvBirthday.setText(getString(R.string.birthdayUser, getBirthdayLocal(context, calendarUser)));

        birthdayChanged = true;
    }

    private void showDatePicker(DatePickerDialog.OnDateSetListener dateSetListener) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(context,
                android.R.style.Theme_Holo_Dialog,
                dateSetListener,
                calendarUser.get(Calendar.YEAR),
                calendarUser.get(Calendar.MONTH), calendarUser.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    private void showProgressBar(boolean anzeigen) {
        if (anzeigen) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    private boolean isInputValid() {
        if (isEmpty(context, tvName, tvBirthday)) {
            return false;
        }

        if (country.isEmpty() || countryCode.isEmpty()) {
            return false;
        }

        return !isBirthdayInvalid(context, calendarUser, tvError);
    }

    private void updatePrefs() {
        SharedPreferences.Editor prefsEditor = sharedPrefs.edit();
        prefsEditor.putString(SP_USERNAME, name);
        prefsEditor.putString(SP_EMAIL, email);
        prefsEditor.putString(SP_BIRTHDAY, getDate(calendarUser));
        prefsEditor.putString(SP_UDATE, getDate());

        if (country != null && country.length() > 3) {
            prefsEditor.putString(SP_COUNTRY, country);
        }
        if (countryCode != null && countryCode.length() > 1) {
            prefsEditor.putString(SP_COUNTRY_CODE, countryCode);
        }

        prefsEditor.apply();
    }

    private void showError(String error, boolean anzeigen) {
        tvError.setText(error);
        if (anzeigen) {
            tvError.setVisibility(View.VISIBLE);
        } else {
            tvError.setVisibility(View.GONE);
        }
    }
}
