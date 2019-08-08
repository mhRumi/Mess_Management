package com.example.messmanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.android.volley.Request.Method.POST;

public class SignUp extends AppCompatActivity implements View.OnClickListener {
    private EditText name, email, password;
    private Button signup;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        name = findViewById(R.id.Name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        signup = findViewById(R.id.signup);
        progressBar = findViewById(R.id.progress);

        signup.setOnClickListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        register(view);

    }

    private void register(View view) {

        progressBar.setVisibility(view.VISIBLE);

        final String Name = this.name.getText().toString().trim();
        final String Email = this.email.getText().toString().trim();
        final String Password = this.password.getText().toString().trim();

        if(Name.isEmpty()){
            this.name.setError("please fillup name field");
            this.name.requestFocus();
            progressBar.setVisibility(View.GONE);
            return;
        }

        if(Email.isEmpty()){
            this.email.setError("please fillup email field");
            this.email.requestFocus();
            progressBar.setVisibility(View.GONE);
            return;
        }

        if(Password.isEmpty()){
            this.password.setError("please fillup password field");
            this.password.requestFocus();
            progressBar.setVisibility(View.GONE);
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(Email).matches()){
            this.email.setError("Enter a valid email address");
            this.email.requestFocus();
            progressBar.setVisibility(View.GONE);
            return;
        }

        StringRequest stringRequest = new StringRequest(POST, Constant.url_register, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Toast.makeText(getApplicationContext(),response.toString(),Toast.LENGTH_SHORT).show();
                    if(response.toString().equals("Registration successfull")){
                        progressBar.setVisibility(View.GONE);
                        name.setText(null);
                        email.setText(null);
                        password.setText(null);
                        Intent intent = new Intent(getApplicationContext(),Profile.class);
                        intent.putExtra("Name",Name);
                        startActivity(intent);
                    }else{
                        progressBar.setVisibility(View.GONE);
                    }
                    JSONObject jsonObject = new JSONObject(response);
                    Toast.makeText(getApplicationContext(),jsonObject.getString("message"),Toast.LENGTH_SHORT).show();

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params= new HashMap<>();
                params.put("Name",Name);
                params.put("Email",Email);
                params.put("Password",Password);

                return params;
            }
        };

        MySingleton.getInstance(this).addToRequestQueue(stringRequest);


    }
}
