package com.example.ezattend;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class TeacherLoginPage extends AppCompatActivity {

    EditText id_tech, pass_tech;
    Button tec_Login_Btn;
    TextView pass_forgot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_login_page);

        // Initialize views
        id_tech = findViewById(R.id.id_tech);
        pass_tech = findViewById(R.id.pass_tech);
        tec_Login_Btn = findViewById(R.id.tec_Login_Btn);
        pass_forgot = findViewById(R.id.pass_forgot);

        tec_Login_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!validateUsername() || !validatePassword()) {
                    return;
                } else {
                    checkUser();
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
    }

    public Boolean validateUsername() {
        String val = id_tech.getText().toString().trim();
        if (val.isEmpty()) {
            id_tech.setError("Username cannot be empty");
            return false;
        } else {
            id_tech.setError(null);
            return true;
        }
    }

    public Boolean validatePassword() {
        String val = pass_tech.getText().toString().trim();
        if (val.isEmpty()) {
            pass_tech.setError("Password cannot be empty");
            return false;
        } else {
            pass_tech.setError(null);
            return true;
        }
    }

    public void checkUser() {
        String teacherUsername = id_tech.getText().toString().trim();
        String teacherPassword = pass_tech.getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("teacher");

        // Fix 1: The query should use "userID" to match the database key
        Query checkUserDatabase = reference.orderByChild("userID").equalTo(teacherUsername);

        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                        // Fix 2: Get the password using the correct database key "password"
                        String passwordFromDB = userSnapshot.child("password").getValue(String.class);

                        if (passwordFromDB != null && passwordFromDB.equals(teacherPassword)) {
                            id_tech.setError(null);
                            Intent intent = new Intent(TeacherLoginPage.this, Teacher_Main_Page.class);
                            startActivity(intent);
                            finish();
                            return;
                        }
                    }
                    pass_tech.setError("Invalid password");
                    pass_tech.requestFocus();
                } else {
                    id_tech.setError("User does not exist");
                    id_tech.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(TeacherLoginPage.this, "Database error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}