package com.example.appthitracnghiem.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appthitracnghiem.Commons.Common;
import com.example.appthitracnghiem.ConfigDB.Database;
import com.example.appthitracnghiem.R;

public class LoginActivity extends AppCompatActivity {

    EditText usernameEditText;
    EditText passwordEditText;
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(view -> {
            String username = usernameEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();
            if (!username.matches("") && !password.matches("")) {
                SQLiteDatabase db = Database.initDatabase(LoginActivity.this, Common.DATABASE_NAME);
                String sql = "SELECT * FROM HocSinh WHERE UserName = " + "'" + username + "'";
                Cursor cursor = db.rawQuery(sql, null);
                cursor.moveToFirst();
                if (cursor.getCount() > 0) {
                    String passDB = cursor.getString(5);
                    if (passDB.equals(password)) {
                        Common.ID_HOCSINH = cursor.getInt(0);
                        Common.TEN_HOC_SINH = cursor.getString(1);
                        Common.LOP = cursor.getInt(3);
                        Intent i = new Intent(LoginActivity.this, MonThiActivity.class);
                        startActivity(i);
                    } else {
                        passwordEditText.setText("");
                        passwordEditText.setError("M???t kh???u kh??ng ch??nh x??c.");
                    }
                } else {
                    usernameEditText.setError("T??i kho???n kh??ng t???n t???i");
                    Toast.makeText(LoginActivity.this, "T??i kho???n kh??ng t???n t???i.", Toast.LENGTH_LONG).show();
                }
                cursor.close();
                db.close();

            } else {
                if (username.matches("")) {
                    usernameEditText.setError("Ch??a ??i???n t??i kho???n.");
                } else if (password.matches("")) {
                    passwordEditText.setError("Ch??a ??i???n m???t kh???u.");
                }

            }


        });
    }
}