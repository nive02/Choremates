package com.example.choremates;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity{

    ImageView back_arrow;
    TextView forgot_password, help, register;
    Button login;
    EditText email, password;
    DatabaseHelper db;
    public static String email_string;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        back_arrow = findViewById(R.id.back_arrow);
        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        forgot_password = findViewById(R.id.forgot_password_Text);
        forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (LoginActivity.this, ConfirmEmailActivity.class);
                startActivity(intent);
            }
        });

        help = findViewById(R.id.help);
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomAlertDialog.openDialog(LoginActivity.this, LoginActivity.this,
                        (ConstraintLayout)findViewById(R.id.layoutDialogContainer), getResources().getString(R.string.login_message)
                        , "Help");
            }
        });
        register = findViewById(R.id.register2);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

        db = new DatabaseHelper(this);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        login = findViewById(R.id.loginButton2);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailStr = email.getText().toString();
                String passwordStr = password.getText().toString();
                boolean checkCredentials = db.checkCredentials(emailStr, passwordStr);
                if (emailStr.equals("")) {
                    CustomAlertDialog.openDialogNoTitle(LoginActivity.this, LoginActivity.this,
                            (ConstraintLayout) findViewById(R.id.layoutDialogContainer),
                            "Please enter an email");
                } else if (passwordStr.equals("")) {
                    CustomAlertDialog.openDialogNoTitle(LoginActivity.this, LoginActivity.this,
                            (ConstraintLayout) findViewById(R.id.layoutDialogContainer),
                            "Please enter a password");
                } else {
                    if (checkCredentials) {
                        email_string = email.getText().toString();
                        Intent intent = new Intent(LoginActivity.this, ChoresActivity.class);
                        startActivity(intent);
                    } else {
                        boolean emailExists = db.checkEmail(emailStr);
                        if (emailExists) {
                            CustomAlertDialog.openDialogNoTitle(LoginActivity.this, LoginActivity.this,
                                    (ConstraintLayout) findViewById(R.id.layoutDialogContainer),
                                    "No account found\nwith that email.");
                        } else {
                            CustomAlertDialog.openDialogNoTitle(LoginActivity.this, LoginActivity.this,
                                    (ConstraintLayout) findViewById(R.id.layoutDialogContainer),
                                    "Wrong email or\npassword");
                        }//end else
                    }//end else
                }//end else
            }//end method onClick
        });//end onClickListened
    }//end onCreate

    public static String getEmail(){
        return email_string;
    }
}//end class
