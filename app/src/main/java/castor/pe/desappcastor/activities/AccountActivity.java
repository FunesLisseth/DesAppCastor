package castor.pe.desappcastor.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import castor.pe.desappcastor.R;
import castor.pe.desappcastor.utils.Constants;

public class AccountActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    private EditText emailEditText;
    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText phoneEditText;
    private Spinner documentoTypeSpinner;
    private EditText documentEditText;
    private CardView changePasswordCardView;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        emailEditText = (EditText)findViewById(R.id.emailEditText);
        firstNameEditText = (EditText)findViewById(R.id.firstNameEditText);
        lastNameEditText = (EditText)findViewById(R.id.lastNameEditText);
        phoneEditText = (EditText)findViewById(R.id.phoneEditText);
        documentoTypeSpinner = (Spinner) findViewById(R.id.documentTypeSpinner);
        documentEditText = (EditText)findViewById(R.id.documentEditText);

        changePasswordCardView = (CardView)findViewById(R.id.changePasswordCardView);
        changePasswordCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), PasswordActivity.class));
            }
        });

        final List<String> list=new ArrayList<String>();
        list.add("DNI");
        list.add("CE");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        documentoTypeSpinner.setAdapter(dataAdapter);

        sharedPreferences = getSharedPreferences(Constants.SHARED_PREF_KEY, MODE_PRIVATE);
        final String userId = sharedPreferences.getString(Constants.PREF_USER_ID, "");

        loadData(userId);

        saveButton = (Button)findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData(userId);
            }
        });

    }

    private void loadData(String userId){

        final Response.Listener<JSONObject> responseListener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if (response == null) {
                        System.out.println("NO AUTHORIZED");
                    } else {
                        String firstName = String.valueOf(response.get("firstName"));
                        String lastName = String.valueOf(response.get("lastName"));
                        String phone = String.valueOf(response.get("phone"));
                        String email = String.valueOf(response.get("email"));
                        String documentId = String.valueOf(response.get("documentId"));
                        String document = String.valueOf(response.get("document"));

                        emailEditText.setText(email);
                        firstNameEditText.setText(firstName);
                        lastNameEditText.setText(lastName);
                        phoneEditText.setText(phone);
                        documentoTypeSpinner.setSelection(Integer.parseInt(documentId)-1);
                        documentEditText.setText(document);

                        System.out.println("AUTHORIZED");

                    }
                } catch (JSONException e) {
                    System.out.println("error 2a");
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
                Request.Method.GET,
                Constants.REGISTER_URL+userId,
                null,
                responseListener,
                errorListener   );

        Volley.newRequestQueue(this).add(jsonRequest);

    }

    private void saveData(String userId){

        final JSONObject requestJsonObject = new JSONObject();

        try {
            if( documentoTypeSpinner.getSelectedItem().toString().equals("DNI") ){
                requestJsonObject.put("documentId", "1");
            }else if( documentoTypeSpinner.getSelectedItem().toString().equals("CE") ){
                requestJsonObject.put("documentId", "2");
            }
            requestJsonObject.put("document", documentEditText.getText().toString());
            requestJsonObject.put("firstName", firstNameEditText.getText().toString());
            requestJsonObject.put("lastName", lastNameEditText.getText().toString());
            requestJsonObject.put("phone", phoneEditText.getText().toString());
            requestJsonObject.put("email", emailEditText.getText().toString());
        } catch (Exception ex) {
            System.out.println("error 1");
            ex.printStackTrace();
        }

        final Response.Listener<JSONObject> responseListener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if (response == null) {
                        System.out.println("NO AUTHORIZED");
                    } else {
                        String firstName = String.valueOf(response.get("firstName"));
                        String lastName = String.valueOf(response.get("lastName"));
                        String phone = String.valueOf(response.get("phone"));
                        String email = String.valueOf(response.get("email"));
                        String documentId = String.valueOf(response.get("documentId"));
                        String document = String.valueOf(response.get("document"));

                        emailEditText.setText(email);
                        firstNameEditText.setText(firstName);
                        lastNameEditText.setText(lastName);
                        phoneEditText.setText(phone);
                        documentoTypeSpinner.setSelection(Integer.parseInt(documentId)-1);
                        documentEditText.setText(document);

                        System.out.println("AUTHORIZED");

                    }
                } catch (JSONException e) {
                    System.out.println("error 2a");
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
