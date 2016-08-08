package castor.pe.desappcastor.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import castor.pe.desappcastor.R;
import castor.pe.desappcastor.activities.MainActivity;
import castor.pe.desappcastor.utils.Constants;


public class LoginFragment extends Fragment {

    private EditText accountEditText;
    private EditText passwordEditText;
    private Button loginButton;

    private static final String INDICE_SECCION = "upc.edu.pe.castor.LoginFragmentTab.extra.INDICE_SECCION";
    // Storage Access Class
    SharedPreferences sharedPreferences;

    public static LoginFragment nuevaInstancia(int indiceSeccion) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        args.putInt(INDICE_SECCION, indiceSeccion);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_login, container, false);

        accountEditText = (EditText)view.findViewById(R.id.accountEditText);
        passwordEditText = (EditText)view.findViewById(R.id.passwordEditText);
        loginButton = (Button)view.findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if( !accountEditText.getText().toString().equals("") &&
                        !passwordEditText.getText().toString().equals("")   ) {

                    final JSONObject jsonObject = new JSONObject();

                    try {
                        jsonObject.put("account", String.valueOf(accountEditText.getText()));
                        jsonObject.put("password", String.valueOf(passwordEditText.getText()));
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

                                    Context context = getActivity();

                                    sharedPreferences = context.getSharedPreferences(Constants.SHARED_PREF_KEY, Context.MODE_PRIVATE);

                                    String id = String.valueOf(response.get("id"));
                                    String firstName = String.valueOf(response.get("firstName"));
                                    String lastName = String.valueOf(response.get("lastName"));
                                    String userType = String.valueOf(response.get("profileId"));

                                    SharedPreferences.Editor editor = sharedPreferences.edit();

                                    editor.putString(Constants.PREF_USER_ID, id);
                                    editor.putString(Constants.PREF_USER_FIRSTNAME, firstName);
                                    editor.putString(Constants.PREF_USER_LASTNAME, lastName);
                                    editor.putString(Constants.PREF_USER_TYPE, userType);
                                    editor.commit();

                                    startActivity(new Intent(getContext(), MainActivity.class));
                                }
                            } catch (JSONException e) {
                                System.out.println("error 2a");
                            }
                        }
                    };

                    Response.ErrorListener errorListener = new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            System.out.println("error 3");
                        }
                    };

                    JsonObjectRequest jsonRequest = new JsonObjectRequest(  Request.Method.POST,
                            Constants.LOGIN_URL,
                            jsonObject,
                            responseListener,
                            errorListener   );

                    Volley.newRequestQueue(getContext()).add(jsonRequest);

                }
                /*JsonArrayRequest jsonRequest = new JsonArrayRequest(
                        Request.Method.GET, LOGIN_URL, null, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {

                        System.out.println("EN EL ON RESPONSE!!!!");

                        try {
                            for(int i=0; i < response.length(); i++) {
                                JSONObject jsonobject = response.getJSONObject(i);
                                String description    = jsonobject.getString("description");
                                System.out.println("description: "+description);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });*/



            }
        });

        return view;
    }
}
