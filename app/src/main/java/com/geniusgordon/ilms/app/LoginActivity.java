package com.geniusgordon.ilms.app;

import android.content.Intent;
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

import com.android.volley.NoConnectionError;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.geniusgordon.ilms.R;
import com.geniusgordon.ilms.app.main.MainActivity;
import com.geniusgordon.ilms.http.LoginAsyncTask;
import com.geniusgordon.ilms.http.ProfileRequest;
import com.geniusgordon.ilms.http.RequestQueueSingleton;
import com.geniusgordon.ilms.model.Account;
import com.geniusgordon.ilms.model.LoginStatus;
import com.geniusgordon.ilms.model.Preferences;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {

    private Account account;

    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.username) EditText usernameTxt;
    @Bind(R.id.password) EditText passwordTxt;
    @Bind(R.id.loginBtn) Button loginBtn;
    @Bind(R.id.loginProgressBar) ProgressBar loginProgressBar;

    private Response.ErrorListener errorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            error.printStackTrace();
            if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                Toast.makeText(getApplicationContext(), getString(R.string.timeout), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(),
                        getString(R.string.incorrect_user_passwd),
                        Toast.LENGTH_SHORT).show();
            }
            loginProgressBar.setVisibility(View.INVISIBLE);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getString(R.string.login));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
        final  String username = usernameTxt.getText().toString();
        String password = passwordTxt.getText().toString();
        if (username.trim().length() == 0 || password.trim().length() == 0) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.empty_user_passwd), Toast.LENGTH_SHORT).show();
            return;
        }

        loginProgressBar.setVisibility(View.VISIBLE);
//        LoginRequest request = new LoginRequest(username, password,
//            new Response.Listener<LoginStatus>() {
//                @Override
//                public void onResponse(LoginStatus response) {
//                    Log.d("Login response", response.getEmail());
//                    account = new Account();
//                    account.setStudentId(username);
//                    account.setEmail(response.getEmail());
//                    getAccountProfile();
//                }
//            }, errorListener);
//        RequestQueueSingleton.getInstance(getApplicationContext()).addToRequestQueue(request);

        LoginAsyncTask task = new LoginAsyncTask() {
            @Override
            protected void onPostExecute(LoginStatus loginStatus) {
                super.onPostExecute(loginStatus);
                if (loginStatus != null) {
                    if (loginStatus.isStatus()) {
                        Log.d("Login", loginStatus.getEmail());
                        account = new Account();
                        account.setStudentId(username);
                        account.setEmail(loginStatus.getEmail());
                        getAccountProfile();
                    } else {
                        Log.d("Login", loginStatus.getMsg());
                        loginProgressBar.setVisibility(View.INVISIBLE);
                        if (loginStatus.getMsg() != null)
                            Toast.makeText(getApplicationContext(), loginStatus.getMsg(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), getString(R.string.timeout), Toast.LENGTH_SHORT).show();
                    loginProgressBar.setVisibility(View.INVISIBLE);
                }
            }
        };
        task.execute(username, password);
    }

    public void getAccountProfile() {
        if (!(account.getAvatarUrl() == null || account.getAvatarUrl().equals("")))
            return;

        ProfileRequest request = new ProfileRequest(new Response.Listener<Account>() {
            @Override
            public void onResponse(Account response) {
                account.setName(response.getName());
                account.setAvatarUrl(response.getAvatarUrl());
                account.setLastLogin(response.getLastLogin());
                account.setLoginCount(response.getLoginCount());
                Preferences.getInstance(getApplicationContext()).saveAccount(account);
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                finishAffinity();
                startActivity(intent);
            }
        }, errorListener);
        RequestQueueSingleton.getInstance(getApplicationContext()).addToRequestQueue(request);
    }
}
