package com.example.assignment2_ponhvathvann_1502538;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class VolleyRequestExercise extends AppCompatActivity {

    TextView output;
    Button btnRequest;
    RequestQueue queue = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley_request_exercise);

        queue = Volley.newRequestQueue(getApplicationContext());
        output = (TextView)findViewById(R.id.txtViewOutput);
        btnRequest = (Button)findViewById(R.id.btnGetAklRestaurant);

        btnRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url ="https://developers.zomato.com/api/v2.1/search?entity_id=70&entity_type=city&lat=-36.84842&lon=174.76337";

                JsonObjectRequest jsObjRequest = new JsonObjectRequest
                        (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {

                                StringBuilder restaurants = new StringBuilder();

                                try {
                                    JSONArray data = response.getJSONArray("restaurants");

                                    for(int index = 0; index < data.length(); index++)
                                    {
                                        JSONObject restaurant_info = data.getJSONObject(index);
                                        JSONObject restaurant = restaurant_info.getJSONObject("restaurant");
                                        JSONObject location = restaurant.getJSONObject("location");
                                        JSONObject rating = restaurant.getJSONObject("user_rating");


                                        String restaurant_name = restaurant.getString("name");
                                        String restaurant_address = location.getString("address");
                                        String restaurant_contact = restaurant.getString("phone_numbers");
                                        String restaurant_cuisine = restaurant.getString("cuisines");
                                        String restaurant_average_cost_for_2 = restaurant.getString("average_cost_for_two");
                                        String restaurant_aggregate_rating = rating.getString("aggregate_rating");
                                        String restaurant_rating_text = rating.getString("rating_text");
                                        String restaurant_reviews_count = restaurant.getString("all_reviews_count");

                                        System.out.println(restaurant_name);
                                        System.out.println(restaurant_address);
                                        System.out.println(restaurant_contact);
                                        System.out.println(restaurant_cuisine);
                                        System.out.println(restaurant_average_cost_for_2);
                                        System.out.println(restaurant_aggregate_rating);
                                        System.out.println(restaurant_rating_text);
                                        System.out.println(restaurant_reviews_count);




//                                        restaurants.append(properties.getString("locality")+"\n");
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
//                                output.setText(restaurants.toString());
                            }
                        }, new Response.ErrorListener() {

                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.e("error",error.getLocalizedMessage());

                            }
                        })
                        {
                            //Request Header
                            @Override
                            public Map<String, String> getHeaders() throws AuthFailureError {
                                Map<String, String> params = new HashMap<String, String>();
                                params.put("Content-Type", "application/json; charset=UTF-8");
                                params.put("user-key", "7e0ecb646b8b71ac0cd781e4ad519efb");
                                return params;
                            }
                        };
                    // Add the request to the RequestQueue.
                queue.add(jsObjRequest);
            }
        });
    }
}