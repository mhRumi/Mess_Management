package com.example.messmanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import static com.android.volley.Request.Method.POST;

public class FinalCalculation extends AppCompatActivity {

    private WebView webView;
    String htmlFinalMiddle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_calculation);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        webView = findViewById(R.id.webviewFinal);
        calculation();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }


    private void calculation() {

        StringRequest stringRequest = new StringRequest(POST, Constant.url_finalCalculation, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    String[] content = response.toString().split("\n");
                    for(int i=0; i<content.length; i++){
                        String[] element = content[i].split("@");

                        htmlFinalMiddle +=   "   <tr>\n" +
                                "<td>"+element[0]+"</td>\n" +
                                "<td>"+element[1]+"</td>\n" +
                                "<td>"+element[2]+"</td>\n" +
                                "<td>"+element[3]+"</td>\n" +
                                "<td>"+element[4]+"</td>\n" +
                                "  </tr>\n" ;
                    }


                    String htmlTable = Constant.htmlFinalFirst+htmlFinalMiddle+Constant.htmlFinalLast;
                    webView.loadDataWithBaseURL(null,htmlTable,"text/html","utf-8",null);

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
        });

        MySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }
}
