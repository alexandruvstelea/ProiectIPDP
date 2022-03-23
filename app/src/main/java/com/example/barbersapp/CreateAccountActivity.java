package com.example.barbersapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CreateAccountActivity extends AppCompatActivity {

    private EditText userNameInput, firstNameInput, lastNameInput, passwordInput, ageInput;
    private Spinner genderInput;
    private Button createAccountButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        //CHANGE ACTIVITY BG COLOR
        View view = this.getWindow().getDecorView();
        view.setBackgroundColor(Color.rgb(0, 0, 0));

        userNameInput = findViewById(R.id.userNameCreation);
        firstNameInput = findViewById(R.id.firstNameCreation);
        lastNameInput = findViewById(R.id.lastNameCreation);
        passwordInput = findViewById(R.id.passwordCreation);
        ageInput = findViewById(R.id.ageCreation);
        genderInput = findViewById(R.id.genderCreation);
        createAccountButton = findViewById(R.id.createButton);

        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!(userNameInput.getText().toString().isEmpty() || firstNameInput.getText().toString().isEmpty() || lastNameInput.getText().toString().isEmpty() || passwordInput.getText().toString().isEmpty() || ageInput.getText().toString().isEmpty() || genderInput.getSelectedItem().toString() == "Gender"))
                    if (Integer.valueOf(ageInput.getText().toString()) > 15) {
                        String userName = userNameInput.getText().toString();
                        String firstName = firstNameInput.getText().toString();
                        String lastName = lastNameInput.getText().toString();
                        String password = passwordInput.getText().toString();
                        int age = Integer.valueOf(ageInput.getText().toString());
                        String gender = genderInput.getSelectedItem().toString();
                        createUser(userName, firstName, lastName, password, age, gender);
                    } else
                        Toast.makeText(CreateAccountActivity.this, "You need to be at least 16yo to register!", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(CreateAccountActivity.this, "Please enter all the data!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onBackPressed() {
        Intent intent = new Intent(CreateAccountActivity.this, LogInActivity.class);
        startActivity(intent);
    }

    private void createUser(String userName, String firstName, String lastName, String password, int age, String gender) {
        UserDB db = UserDB.getDBInstance(this.getApplicationContext());
        List<String> currentUserNames = db.userDAO().getAllExistingUserNames();
        if (checkUniqueUserName(currentUserNames, userName.toUpperCase())) {
            User user = new User(userName.toUpperCase(), firstName.toUpperCase(), lastName.toUpperCase(), password, gender.toUpperCase());
            db.userDAO().insertUser(user);
            Toast.makeText(CreateAccountActivity.this, "Account created", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(CreateAccountActivity.this, LogInActivity.class);
            startActivity(intent);
            finish();
        }
        else
            Toast.makeText(CreateAccountActivity.this, "UserName already exists", Toast.LENGTH_SHORT).show();
    }

    private boolean checkUniqueUserName(List<String> userNamesList, String newUserName) {
        for (String userName : userNamesList)
            if (userName.equals(newUserName))
                return false;
        return true;
    }
}