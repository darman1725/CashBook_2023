package com.example.mycashbook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mycashbook.R;

public class LoginActivity extends Activity {
    EditText username, password;
    Button loginButton;
    TextView regist;

    private com.example.mycashbook.DBHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        databaseHelper = new com.example.mycashbook.DBHelper(getApplicationContext());

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);
        regist = findViewById(R.id.regist);

        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!username.getText().toString().isEmpty() && !password.getText().toString().isEmpty()) {
                    if (databaseHelper.checkUser(username.getText().toString().trim()
                            , password.getText().toString().trim())) {
                        Intent accountsIntent = new Intent(getApplicationContext(), com.example.mycashbook.HomeActivity.class);
                        accountsIntent.putExtra("username", username.getText().toString().trim());
                        emptyInputEditText();
                        startActivity(accountsIntent);
                        finish();
                    } else {
                        // Snack Bar to show success message that record is wrong
                        Toast.makeText(getApplicationContext(), "Username atau Password Salah", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Username atau Password Kosong", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void emptyInputEditText() {
        username.setText(null);
        password.setText(null);
    }
}
