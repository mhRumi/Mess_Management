package com.example.messmanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
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

public class Profile extends AppCompatActivity implements View.OnClickListener {
    private EditText  date, amount, meal;
    private TextView username;
    private Button submit;
    private ProgressBar updateprogress;
    private DatePicker datePicker;
    public static String Name, Date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Bundle bundle = getIntent().getExtras();
        Name = bundle.getString("Name");

        username = findViewById(R.id.username);
        username.setText(Name);
        datePicker = findViewById(R.id.picdate);
        meal = findViewById(R.id.meal);
        date = findViewById(R.id.date);
        amount = findViewById(R.id.amount);
        submit = findViewById(R.id.submit);
        submit.setOnClickListener(this);
        date.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.logout){
            this.finish();
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
        }
        else if(item.getItemId() == R.id.dash){
            Intent intent = new Intent(getApplicationContext(),DashBoard.class);
            startActivity(intent);
        }

        else if(item.getItemId() == R.id.myinfo){
            Intent intent = new Intent(getApplicationContext(),Personal_info.class);
            startActivity(intent);
        }
        else if(item.getItemId() == R.id.create_bazar_shcedule){

            if(Name.equalsIgnoreCase("admin")){
                Intent intent = new Intent(this,BazarListCreate.class);
                startActivity(intent);
            }
        }
        else if(item.getItemId() == R.id.bazar_shcedule){
            Intent intent = new Intent(this,ShowBazarList.class);
            startActivity(intent);
        }
        else if(item.getItemId() == R.id.calculation){
            Intent intent = new Intent(this,FinalCalculation.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.date){
            date.setText(currentDate());
        }

        if(view.getId() == R.id.submit){
            update(Date);
        }


    }

    String currentDate(){
        StringBuilder date = new StringBuilder();
        if(datePicker.getDayOfMonth()<10){
            date.append("0"+datePicker.getDayOfMonth()+"/");
        }else
        date.append(datePicker.getDayOfMonth()+"/");
        if(datePicker.getMonth() <10){
            date.append("0"+(datePicker.getMonth()+1)+"/");
        }else
        date.append((datePicker.getMonth()+1)+"/");
        date.append(datePicker.getYear());
        return date.toString();
    }

    private void update(String datepic) {

        final String Date = date.getText().toString().trim();
        final String Amount = amount.getText().toString().trim();
        final String Meal = meal.getText().toString().trim();

        if(Date.isEmpty()){
            date.setError("please enter your name");
            date.requestFocus();
            return;
        }

        if(Amount.isEmpty()){
            amount.setError("please enter amount");
            amount.requestFocus();
            return;
        }

        if(Meal.isEmpty()){
            meal.setError("please enter today's meal");
            meal.requestFocus();
            return;
        }

        StringRequest stringRequest = new StringRequest(POST, Constant.url_updatebazar, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Toast.makeText(getApplicationContext(),response.toString(),Toast.LENGTH_SHORT).show();
                    if(response.toString().equals("Updated successfully")){

                        date.setText(null);
                        amount.setText(null);
                        meal.setText(null);

                    }else{
                        //progressBar.setVisibility(View.GONE);
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
                params.put("Amount",Amount);
                params.put("Meal",Meal);

                return params;
            }
        };

        MySingleton.getInstance(this).addToRequestQueue(stringRequest);


    }
}
