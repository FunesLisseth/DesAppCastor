package castor.pe.desappcastor.fragments;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import castor.pe.desappcastor.activities.MainActivity;
import castor.pe.desappcastor.utils.Constants;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment {


    private static final String INDICE_SECCION = "upc.edu.pe.castor.LoginFragmentTab.extra.INDICE_SECCION";

    private Button registerButton;
    private EditText emailEditText;
    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText phoneEditText;
    private Spinner documentoTypeSpinner;
    private EditText documentEditText;

    // Storage Access Class
    SharedPreferences sharedPreferences;

    public static RegisterFragment nuevaInstancia(int indiceSeccion) {
        RegisterFragment fragment = new RegisterFragment();
        Bundle args = new Bundle();
        args.putInt(INDICE_SECCION, indiceSeccion);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_register, container, false);

        emailEditText = (EditText)rootView.findViewById(R.id.emailEditText);
        firstNameEditText = (EditText)rootView.findViewById(R.id.firstNameEditText);
        lastNameEditText = (EditText)rootView.findViewById(R.id.lastNameEditText);
        phoneEditText = (EditText)rootView.findViewById(R.id.phoneEditText);
        documentoTypeSpinner = (Spinner) rootView.findViewById(R.id.documentTypeSpinner);
        documentEditText = (EditText)rootView.findViewById(R.id.documentEditText);

        final List<String> list=new ArrayList<String>();
        list.add("DNI");
        list.add("CE");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        documentoTypeSpinner.setAdapter(dataAdapter);

        registerButton = (Button)rootView.findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                saveClient();

            }
        });

        return rootView;
    }

    private void saveClient(){

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

        JsonObjectRequest jsonRequest = new JsonObjectRequest(
                Request.Method.POST,
                Constants.REGISTER_URL,
                requestJsonObject,
                responseListener,
                errorListener   );

        Volley.newRequestQueue(getContext()).add(jsonRequest);


    }

}
