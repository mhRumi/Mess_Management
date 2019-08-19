package com.example.messmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button signin, signup;
    private String Name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signin = findViewById(R.id.signin);
        signup = findViewById(R.id.signup);

        signin.setOnClickListener(this);
        signup.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if(view.getId() == R.id.signin){

            SharedPreferences sharedPreferences = getSharedPreferences("login",MODE_PRIVATE);

            if(sharedPreferences.contains("Name") && sharedPreferences.contains("Password")){
                Name = sharedPreferences.getString("Name","nothing");
                Intent intent = new Intent(getApplicationContext(),Profile.class);
                intent.putExtra("Name",Name);
                startActivity(intent);
            }else{
                Intent intent = new Intent(this,SignIn.class);
                startActivity(intent);
            }
        }
        else if(view.getId() == R.id.signup){
            Intent intent = new Intent(this,SignUp.class);
            startActivity(intent);
        }

    }
}
