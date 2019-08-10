package com.example.messmanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.android.volley.Request.Method.POST;

public class BazarListCreate extends AppCompatActivity implements View.OnClickListener {
    private Button submit;
    private EditText name, date;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bazar_list_create);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        progressBar = findViewById(R.id.list_progress);
        name = findViewById(R.id.name_list);
        date = findViewById(R.id.date_list);
        submit = findViewById(R.id.submit_list);
        submit.setOnClickListener(this);
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

        progressBar.setVisibility(View.VISIBLE);
        updateBazarList(view);


    }

    private void updateBazarList(final View view) {
        final String Name = name.getText().toString().trim();
        final String Date = date.getText().toString().trim();

        if(Name.isEmpty()){
            name.setError("Please enter valid name");
            name.requestFocus();
            return;
        }
        if(Date.isEmpty()){
            date.setError("Please enter valid date");
            date.requestFocus();
            return;
        }


        StringRequest stringRequest = new StringRequest(POST, Constant.url_updateList, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(),response.toString(),Toast.LENGTH_SHORT).show();
                    if(response.toString().equals("added successfully")){

                        name.setText(null);
                        date.setText(null);

                    }else{
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
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params= new HashMap<>();
                params.put("Name",Name);
                params.put("Date",Date);
                return params;
            }
        };

        MySingleton.getInstance(this).addToRequestQueue(stringRequest);


    }
}
