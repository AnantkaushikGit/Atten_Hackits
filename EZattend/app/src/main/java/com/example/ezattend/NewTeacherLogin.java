package com.example.ezattend;

import android.os.Bundle;
import android.text.TextUtils; // Import TextUtils
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class NewTeacherLogin extends AppCompatActivity {

    EditText Tech_name, Tech_uid, Tech_pass;
    Button but_add;
    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_new_teacher_login);

        Tech_name = findViewById(R.id.new_tech_username);
        Tech_uid = findViewById(R.id.new_tech_id);
        Tech_pass = findViewById(R.id.new_tech_pass);
        but_add = findViewById(R.id.btn_new_tech_signup);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("teacher");

        but_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = Tech_name.getText().toString().trim();
                String userID = Tech_uid.getText().toString().trim();
                String password = Tech_pass.getText().toString().trim();

                // 1. Input Validation
                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(userID) || TextUtils.isEmpty(password)) {
                    Toast.makeText(NewTeacherLogin.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                // 2. Check if UserID already exists to prevent overwriting
                reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            Toast.makeText(NewTeacherLogin.this, "User ID already exists. Please choose another.", Toast.LENGTH_SHORT).show();
                        } else {
                            // If userID is unique, save the new teacher data
                            // Ensure HelpercCass constructor fields match the variable names here
                            HelpercCass helperclass = new HelpercCass(name, userID, password);
                            reference.child(userID).setValue(helperclass);

                            Toast.makeText(NewTeacherLogin.this, "Registration successful", Toast.LENGTH_SHORT).show();

                            // Optional: Clear the EditText fields after successful registration
                            Tech_name.setText("");
                            Tech_uid.setText("");
                            Tech_pass.setText("");
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(NewTeacherLogin.this, "Database Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}