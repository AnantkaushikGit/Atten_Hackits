package com.example.ezattend;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class TeacherLoginPage extends AppCompatActivity {

    private EditText id_tech, pass_tech;
    private Button tec_Login_Btn;
    private TextView pass_forgot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_teacher_login_page);

        // Initialize views
        id_tech = findViewById(R.id.id_tech);
        pass_tech = findViewById(R.id.pass_tech);
        tec_Login_Btn = findViewById(R.id.tec_Login_Btn);
        pass_forgot = findViewById(R.id.pass_forgot);

        // Set up login button click listener
        tec_Login_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String teacherId = id_tech.getText().toString().trim();
                String password = pass_tech.getText().toString().trim();

                if (password.isEmpty()) {
                    Toast.makeText(TeacherLoginPage.this, "Password cannot be empty", Toast.LENGTH_SHORT).show();
                } else if (teacherId.equals("@teacher") && password.equals("teacher1234")) {
                    Intent intent = new Intent(TeacherLoginPage.this, Teacher_Main_Page.class);
                    startActivity(intent);
                    finish(); // Close the login page
                } else {
                    Toast.makeText(TeacherLoginPage.this, "Invalid ID or Password", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Set up "Forgot Password" text view click listener
        pass_forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TeacherLoginPage.this, "Work under progress", Toast.LENGTH_SHORT).show();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}