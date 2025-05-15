package com.example.mojprofil;

import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView emailTextView, passwordTextView, messageTextView;
    private String currentEmail = "uzytkownik@domena.pl";
    private String currentPassword = "********";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        emailTextView = findViewById(R.id.emailTextView);
        passwordTextView = findViewById(R.id.passwordTextView);
        messageTextView = findViewById(R.id.messageTextView);
        Button editButton = findViewById(R.id.editProfileButton);

        showMessage("Witaj! Aplikacja wykonana przez: Adriana Zycha", Color.GRAY);

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showEmailDialog();
            }
        });
    }

    private void showEmailDialog() {
        final EditText emailInput = new EditText(this);
        emailInput.setHint("Nowy email");
        emailInput.setText(currentEmail);
        emailInput.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);

        new AlertDialog.Builder(this)
                .setTitle("Zmień Email")
                .setMessage("Podaj nowy email:")
                .setView(emailInput)
                .setPositiveButton("Zapisz", (dialog, which) -> {
                    String newEmail = emailInput.getText().toString();
                    if (!newEmail.contains("@")) {
                        showMessage("Błąd: Nieprawidłowy format emaila.", Color.RED);
                    } else {
                        currentEmail = newEmail;
                        emailTextView.setText(newEmail);
                        showPasswordDialog();
                    }
                })
                .setNegativeButton("Anuluj", (dialog, which) -> {
                    showMessage("Edycja profilu anulowana.", Color.GRAY);
                })
                .show();
    }

    private void showPasswordDialog() {
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);

        final EditText passInput = new EditText(this);
        passInput.setHint("Nowe hasło");
        passInput.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        layout.addView(passInput);

        final EditText repeatInput = new EditText(this);
        repeatInput.setHint("Powtórz hasło");
        repeatInput.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        layout.addView(repeatInput);

        new AlertDialog.Builder(this)
                .setTitle("Zmień Hasło")
                .setMessage("Podaj nowe hasło:")
                .setView(layout)
                .setPositiveButton("Zapisz", (dialog, which) -> {
                    String pass = passInput.getText().toString();
                    String repeat = repeatInput.getText().toString();

                    if (!pass.equals(repeat)) {
                        showMessage("Błąd: Hasła nie pasują do siebie.", Color.RED);
                    } else {
                        currentPassword = "********";
                        passwordTextView.setText(currentPassword);
                        showMessage("Profil zaktualizowany! Nowy email: " + currentEmail, Color.GREEN);
                    }
                })
                .setNegativeButton("Anuluj", (dialog, which) -> {
                    showMessage("Edycja profilu anulowana.", Color.GRAY);
                })
                .show();
    }

    private void showMessage(String message, int color) {
        messageTextView.setText(message);
        messageTextView.setTextColor(color);
    }
}