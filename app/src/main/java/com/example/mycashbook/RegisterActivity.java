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

public class RegisterActivity extends Activity {

    EditText registUsername, registPassword, retypePassword;
    Button regist;
    TextView registAndLogin;
    private com.example.mycashbook.DBHelper databaseHelper;
    private com.example.mycashbook.User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registUsername = findViewById(R.id.registUsername);
        registPassword = findViewById(R.id.registPassword);
        retypePassword = findViewById(R.id.retypePassword);
        regist = findViewById(R.id.regist);
        registAndLogin = findViewById(R.id.registAndLogin);

        databaseHelper = new com.example.mycashbook.DBHelper(this);
        user = new com.example.mycashbook.User();

        registAndLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!registUsername.getText().toString().isEmpty() && !registPassword.getText().toString().isEmpty() && !retypePassword.getText().toString().isEmpty()){
                    if (registPassword.getText().toString().trim().equals(retypePassword.getText().toString().trim())){
                        if (!databaseHelper.checkUser(registUsername.getText().toString().trim())) {
                            user.setUsername(registUsername.getText().toString().trim());
                            user.setPassword(registPassword.getText().toString().trim());
                            databaseHelper.addUser(user);
                            // Snack Bar to show success message that record saved successfully
                            Toast.makeText(getApplicationContext(), "Username berhasil terdaftar", Toast.LENGTH_LONG).show();
                            emptyInputEditText();
                            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                            intent.putExtra("registUsername", registUsername.getText().toString());
                            startActivity(intent);
                            finish();
                        } else {
                            // Snack Bar to show error message that record already exists
                            Toast.makeText(getApplicationContext(), "Username telah terdaftar", Toast.LENGTH_LONG).show();
                        }
                    }else{
                        Toast.makeText(getApplicationContext(), "Password dan Ulangi registPassword tidak sama!", Toast.LENGTH_LONG).show();
                    }
                } else{
                    Toast.makeText(getApplicationContext(), "Tidak boleh ada yang kosong!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void emptyInputEditText() {
        registUsername.setText(null);
        registPassword.setText(null);
        retypePassword.setText(null);
    }
}
