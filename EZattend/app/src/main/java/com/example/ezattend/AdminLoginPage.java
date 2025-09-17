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

public class AdminLoginPage extends AppCompatActivity {

    private EditText idAdmin;
    private EditText passAdmin;
    private Button admLoginBtn;
    private TextView passForgot; // Declare the TextView for the "Forgot Password?" link

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_admin_login_page);

        // Find views by their IDs
        idAdmin = findViewById(R.id.id_admin);
        passAdmin = findViewById(R.id.pass_admin);
        admLoginBtn = findViewById(R.id.adm_Login_Btn);
        passForgot = findViewById(R.id.pass_forgot); // Initialize the TextView

        // Set up the click listener for the login button
        admLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the text from the EditText fields
                String userId = idAdmin.getText().toString();
                String password = passAdmin.getText().toString();

                // Check if the credentials are correct
                if (userId.equals("@admin") && password.equals("admin1234")) {
                    // Correct credentials: navigate to AdminMainPage
                    Intent intent = new Intent(AdminLoginPage.this, AdminMainPage.class);
                    startActivity(intent);
                    finish(); // This prevents the user from going back
                } else {
                    // Incorrect credentials: show a pop-up (Toast)
                    Toast.makeText(AdminLoginPage.this, "Wrong ID or Password", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Set up the click listener for the "Forgot password?" link
        passForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show a Toast message with the contact details
                Toast.makeText(AdminLoginPage.this, "Contact dev - +91 70179 xxxxx", Toast.LENGTH_LONG).show();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}