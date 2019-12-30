package android.example.esm.loginmodule;

import android.example.esm.API.APIClient;
import android.example.esm.API.APIInterface;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.example.esm.R;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {

    EditText signUpUsername, signUpPassword, signUpEmail, signUpFirstName, signUpLastName;
    Button signUpButton;
    String firstName, lastName, email, username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        signUpFirstName = findViewById(R.id.signup_first_name);
        signUpLastName = findViewById(R.id.signup_last_name);
        signUpUsername = findViewById(R.id.signup_username);
        signUpPassword = findViewById(R.id.signup_password);
        signUpEmail = findViewById(R.id.signup_email);
        signUpButton = findViewById(R.id.signup_button);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firstName = signUpFirstName.getText().toString();
                lastName = signUpLastName.getText().toString();
                email = signUpEmail.getText().toString();
                username = signUpUsername.getText().toString();
                password = signUpPassword.getText().toString();

                APIInterface apiInterface = APIClient.getAPIClient().create(APIInterface.class);
                Call<Account> signUpAttempt= apiInterface.createAccount(firstName, lastName, email, username, password);
                signUpAttempt.enqueue(new Callback<Account>() {
                    @Override
                    public void onResponse(Call<Account> call, Response<Account> response) {
                        String message = response.toString();
                        Log.d("response", message);
                        Toast.makeText(SignUpActivity.this, message, Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void onFailure(Call<Account> call, Throwable t) {

                    }
                });


/*
                Call<Account> signUpAttempt = apiInterface.getAllUsers();
                signUpAttempt.enqueue(new Callback<Account>() {
                    @Override
                    public void onResponse(Call<Account> call, Response<Account> response) {
                        String res = response.body().toString();
                        Log.d("response", res);
                        Toast.makeText(SignUpActivity.this, "response", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(Call<Account> call, Throwable t) {
                        Toast.makeText(SignUpActivity.this, "fail", Toast.LENGTH_LONG).show();
                    }
                });*/
            }

        });



    }
}
