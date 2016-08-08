package castor.pe.desappcastor.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import castor.pe.desappcastor.R;
import castor.pe.desappcastor.utils.Constants;

public class PasswordActivity extends AppCompatActivity {

    // Storage Access Class
    SharedPreferences sharedPreferences;

    private Button saveButton;
    private Button cancelButton;

    private EditText currentPasswordEditText;
    private EditText newPasswordEditText;
    private EditText confirmPasswordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        currentPasswordEditText = (EditText)findViewById(R.id.currentPasswordEditText);
        newPasswordEditText = (EditText)findViewById(R.id.newPasswordEditText);
        confirmPasswordEditText = (EditText)findViewById(R.id.confirmPasswordEditText);

        cancelButton = (Button)findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AccountActivity.class));
            }
        });

        sharedPreferences = getSharedPreferences(Constants.SHARED_PREF_KEY, MODE_PRIVATE);
        final String userId = sharedPreferences.getString(Constants.PREF_USER_ID, "");

        saveButton = (Button)findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if( (newPasswordEditText.getText().toString().equals(confirmPasswordEditText.getText().toString())) &&
                        !(currentPasswordEditText.getText().toString().equals(newPasswordEditText.getText().toString()))   ){
                    changePassword(userId);
                }

            }
        });

    }

    private void changePassword(String userId){

        final JSONObject requestJsonObject = new JSONObject();

        try {
            requestJsonObject.put("userPassword", newPasswordEditText.getText().toString());
        } catch (Exception ex) {
            System.out.println("error 1");
            ex.printStackTrace();
        }

        final Response.Listener<JSONObject> responseListener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if (response == null) {
                    System.out.println("NO AUTHORIZED");
                } else {
                    System.out.println("LOG OFF");
                    sharedPreferences = getSharedPreferences(Constants.SHARED_PREF_KEY, MODE_PRIVATE);
                    SharedPreferences.Editor e = sharedPreferences.edit();
                    e.remove(Constants.PREF_USER_ID);
                    e.remove(Constants.PREF_USER_TYPE);
                    e.remove(Constants.PREF_USER_FIRSTNAME);
                    e.remove(Constants.PREF_USER_LASTNAME);
                    e.commit();
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                }
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                System.out.println("error 3: "+error.getMessage());
            }
        };

        JsonObjectRequest jsonRequest = new JsonObjectRequest(
                Request.Method.PUT,
                Constants.REGISTER_URL+userId,
                requestJsonObject,
                responseListener,
                errorListener   );

        Volley.newRequestQueue(this).add(jsonRequest);

    }
}
