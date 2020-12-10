package com.example.choremates;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ConfirmEmailActivity extends AppCompatActivity {

    EditText email;
    Button next;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_email);

        next = findViewById(R.id.next);
        email = findViewById(R.id.emailCPass);
        db = new DatabaseHelper(this);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailStr = email.getText().toString();
                boolean emailExists = db.checkEmail(emailStr);
                if (emailStr.equals("")) {
                    CustomAlertDialog.openDialogNoTitle(ConfirmEmailActivity.this, ConfirmEmailActivity.this,
                            (ConstraintLayout) findViewById(R.id.layoutDialogContainer),
                            "Please enter an email");
                } else if (emailExists) {
                    CustomAlertDialog.openDialogNoTitle(ConfirmEmailActivity.this, ConfirmEmailActivity.this,
                            (ConstraintLayout) findViewById(R.id.layoutDialogContainer),
                            "No account found\nwith that email.");
                } else {
                    Intent intent = new Intent (ConfirmEmailActivity.this, ChangePasswordActivity.class);
                    intent.putExtra("EMAIL", emailStr);
                    startActivity(intent);
                }
            }
        });
    }
}

