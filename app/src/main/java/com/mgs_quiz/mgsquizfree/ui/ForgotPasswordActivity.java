package com.mgs_quiz.mgsquizfree.ui;

import android.content.Context;
import android.content.DialogInterface;
import androidx.annotation.NonNull;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.mgs_quiz.mgsquizfree.R;

import java.util.Locale;

import static com.mgs_quiz.mgsquizfree.CheckInput.isEmailInvalid;
import static com.mgs_quiz.mgsquizfree.HK1606.hk;

public class ForgotPasswordActivity extends AppCompatActivity {

    private Context context;
    private EditText etEmail;
    private TextView tvError;
    private Button btnRequestPW;
    private FloatingActionButton fabBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        initE();
        initL();
    }

    private void initE() {
        context = this;
        etEmail = findViewById(R.id.fpwWmailET);
        tvError = findViewById(R.id.fpwErrorTV);
        btnRequestPW = findViewById(R.id.fpwResetPWBtn);
        fabBack = findViewById(R.id.fpwFab);
    }

    private void initL() {
        btnRequestPW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvError.setText("");
                hk(context, btnRequestPW);
                requestPW();
            }
        });
        fabBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private void requestPW() {
        if (!isEmailInvalid(context, etEmail)) {
            FirebaseAuth mAuth = FirebaseAuth.getInstance();
            mAuth.setLanguageCode(Locale.getDefault().getCountry());
            final String eMail = etEmail.getText().toString().trim();

            mAuth.sendPasswordResetEmail(eMail).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    showResetPWInfo(eMail);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    showErrorInfo(e);
                }
            });
        }
    }

    private void showErrorInfo(Exception e) {
        if (e instanceof FirebaseAuthInvalidUserException) {
            tvError.setText(R.string.eMailAddressNoRecord);
        } else {
            tvError.setText(R.string.eGeneral);
        }
    }

    private void showResetPWInfo(String mail) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(getString(R.string.passwordForgotDialogDesc, mail));
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                onBackPressed();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
