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

public class StudentLoginPage extends AppCompatActivity {

    private EditText id_stud, pass_stud;
    private Button std_Login_Btn;
    private TextView pass_forgot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_student_login_page);

        // Initialize views
        id_stud = findViewById(R.id.id_stud);
        pass_stud = findViewById(R.id.pass_stud);
        std_Login_Btn = findViewById(R.id.std_Login_Btn);
        pass_forgot = findViewById(R.id.pass_forgot);

        // Set up login button click listener
        std_Login_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String studentId = id_stud.getText().toString().trim();
                String password = pass_stud.getText().toString().trim();

                if (password.isEmpty()) {
                    Toast.makeText(StudentLoginPage.this, "Password cannot be empty", Toast.LENGTH_SHORT).show();
                } else if (studentId.equals("@student") && password.equals("student1234")) {
                    Intent intent = new Intent(StudentLoginPage.this, StudentMainPage.class);
                    startActivity(intent);
                    finish(); // Close the login page so the user can't go back to it
                } else {
                    Toast.makeText(StudentLoginPage.this, "Invalid ID or Password", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Set up "Forgot Password" text view click listener
        pass_forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(StudentLoginPage.this, "Work under progress", Toast.LENGTH_SHORT).show();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}