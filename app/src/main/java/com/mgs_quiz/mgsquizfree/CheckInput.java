package com.mgs_quiz.mgsquizfree;

import android.content.Context;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

import static com.mgs_quiz.mgsquizfree.GameData.*;

public class CheckInput {

    public static boolean isEmpty(Context context, View... views) {
        boolean isEmpty = false;
        String data = null;
        EditText editText = null;
        TextView textView = null;

        for (View view : views) {
            if (view instanceof EditText) {
                editText = (EditText) view;
                editText.setError(null);
                data = editText.getText().toString().trim();

                if (data == null || data.isEmpty()) {
                    editText.setError(context.getText(R.string.eInputRequired));
                    editText.requestFocus();
                    isEmpty = true;
                }
            } else if (view instanceof TextView) {
                textView = (TextView) view;
                textView.setError(null);
                data = textView.getText().toString().trim();

                if (data == null || data.isEmpty()) {
                    textView.setError(context.getText(R.string.eInputRequired));
                    textView.requestFocus();
                    isEmpty = true;
                }
            }
        }
        return isEmpty;
    }

    public static boolean isEmailInvalid(Context context, EditText etEmailAddress) {
        String email = etEmailAddress.getText().toString().trim();

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches() ||
                email.length() < GameData.EMAIL_LENGTH_MIN || email.length() > GameData.EMAIL_LENGTH_MAX) {
            etEmailAddress.setError(context.getText(R.string.eMailAddressInvalid));
            etEmailAddress.requestFocus();
            return true;
        }

        if (email.startsWith(".") || email.startsWith(":") ||
                email.startsWith(",") || email.startsWith(";") ||
                email.startsWith("@") || email.startsWith("<") ||
                email.startsWith(">")) {
            etEmailAddress.setError(context.getText(R.string.eMailWithInvalidChar));
            etEmailAddress.requestFocus();
            return true;
        }

        return false;
    }

    public static boolean isBirthdayInvalid(Context context, Calendar birthday, TextView tvError) {
        int birthYear = birthday.get(Calendar.YEAR);
        tvError.setVisibility(View.GONE);

        if (birthYear > BIRTHDAY_MIN || birthYear < BIRTHDAY_MAX) {
            if (birthYear > BIRTHDAY_MIN) {
                tvError.setText(context.getText(R.string.eBirthdayTooRecent));
            } else {
                tvError.setText(context.getText(R.string.eBirthdayTooOld));
            }
            tvError.setVisibility(View.VISIBLE);
            return true;
        }
        tvError.setVisibility(View.GONE);
        return false;
    }

    public static boolean isGenderInvalid(Context context, String strGender, TextView tvError) {
        if (strGender != null && (strGender.equals(GENDER_M) || strGender.equals(GENDER_W))) {
            tvError.setText(context.getText(R.string.eGeneral));
            tvError.setVisibility(View.GONE);
            return false;
        }
        tvError.setText(context.getText(R.string.eGenderSelection));
        tvError.setVisibility(View.VISIBLE);
        return true;
    }

    public static boolean isUsernameLengthInvalid(Context context, EditText etUsername) {
        String username = etUsername.getText().toString().trim();

        if (username.length() < USERNAME_LENGTH_MIN || username.length() > USERNAME_LENGTH_MAX) {
            if (username.length() < USERNAME_LENGTH_MIN) {
                etUsername.setError(context.getText(R.string.eUsernameTooShort));
            } else {
                etUsername.setError(context.getText(R.string.eUsernameTooLong));
            }
            etUsername.requestFocus();
            return true;
        }
        return false;
    }

    public static boolean isPasswordInvalid(Context context, EditText etPassword) {
        String password = etPassword.getText().toString().trim();

        if (password.length() < PASSWORD_LENGTH_MIN || password.length() > PASSWORD_LENGTH_MAX) {
            if (password.length() < PASSWORD_LENGTH_MIN) {
                etPassword.setError(context.getText(R.string.ePasswordToShort));
            } else {
                etPassword.setError(context.getText(R.string.ePasswordToLong));
            }
            etPassword.requestFocus();
            return true;
        }
        return false;
    }
}
