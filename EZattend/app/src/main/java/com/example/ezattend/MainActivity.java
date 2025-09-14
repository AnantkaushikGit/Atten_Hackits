package com.example.ezattend;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button studentPage, teacherPage, adminPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        studentPage = findViewById(R.id.std_Login_Base_Btn);
        studentPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, StudentLoginPage.class);
                startActivity(intent);
            }
        });

        teacherPage = findViewById(R.id.tec_Login_Base_Btn);
        teacherPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TeacherLoginPage.class);
                startActivity(intent);
            }
        });

        adminPage = findViewById(R.id.adm_Login_Base_Btn);
        adminPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AdminLoginPage.class);
                startActivity(intent);
            }
        });

    }
}