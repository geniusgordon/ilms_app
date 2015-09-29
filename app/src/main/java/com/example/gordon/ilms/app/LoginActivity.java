package com.example.gordon.ilms.app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.gordon.ilms.R;
import com.example.gordon.ilms.http.LoginRequest;
import com.example.gordon.ilms.http.RequestQueueSingleton;
import com.example.gordon.ilms.model.Account;
import com.example.gordon.ilms.model.LoginStatus;
import com.example.gordon.ilms.model.Preferences;

import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private EditText usernameTxt;
    private EditText passwordTxt;
    private Button loginBtn;
    private ProgressBar loginProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Log In");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        usernameTxt = (EditText) findViewById(R.id.username);
        passwordTxt = (EditText) findViewById(R.id.password);
        loginBtn = (Button) findViewById(R.id.loginBtn);
        loginProgressBar = (ProgressBar) findViewById(R.id.loginProgressBar);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    private void login() {
        loginProgressBar.setVisibility(View.VISIBLE);
        final String username = usernameTxt.getText().toString();
        String password = passwordTxt.getText().toString();
        LoginRequest request = new LoginRequest(username, password,
            new Response.Listener<LoginStatus>() {
                @Override
                public void onResponse(LoginStatus response) {
                    Log.d("Login response", response.getEmail());
                    Account account = new Account();
                    account.setStudentId(username);
                    account.setEmail(response.getEmail());
                    Intent intent = new Intent();
                    intent.putExtra("account", account);
                    Preferences.getInstance(getApplicationContext()).saveAccount(account);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                    if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                        Toast.makeText(getApplicationContext(), "無法連線，請稍後再試", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "帳密有誤", Toast.LENGTH_SHORT).show();
                    }
                    loginProgressBar.setVisibility(View.INVISIBLE);
                }
            });
        RequestQueueSingleton.getInstance(getApplicationContext()).addToRequestQueue(request);
    }
}
