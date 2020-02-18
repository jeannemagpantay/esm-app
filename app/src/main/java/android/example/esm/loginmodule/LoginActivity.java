package android.example.esm.loginmodule;

import android.content.Intent;
import android.example.esm.API.APIClient;
import android.example.esm.API.APIInterface;
import android.example.esm.MainActivity;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.example.esm.R;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    EditText loginUsername;
    EditText loginPassword;
    CardView loginButton;
    //TextView loginCreate;
    String username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginUsername = findViewById(R.id.login_username);
        loginPassword = findViewById(R.id.login_password);
        loginButton = findViewById(R.id.login_button);
        //loginCreate = findViewById(R.id.login_create);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = loginUsername.getText().toString();
                password = loginPassword.getText().toString();
                //Toast.makeText(LoginActivity.this, username, Toast.LENGTH_LONG).show();

                APIInterface apiInterface = APIClient.getAPIClient().create(APIInterface.class);
                Call<Account> loginAttempt = apiInterface.login(username, password);
                loginAttempt.enqueue(new Callback<Account>() {
                    @Override
                    public void onResponse(Call<Account> call, Response<Account> response) {
                        String token = response.toString();
                        Log.d("token", token);
                        int code = response.code();
                        if (code == 404)
                            Toast.makeText(LoginActivity.this, "Account does not exist", Toast.LENGTH_LONG).show();
                        else {
                            Toast.makeText(LoginActivity.this, "Log in", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Account> call, Throwable t) {

                        Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                });
            }
        });

/*
        loginCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
*/



    }

}
