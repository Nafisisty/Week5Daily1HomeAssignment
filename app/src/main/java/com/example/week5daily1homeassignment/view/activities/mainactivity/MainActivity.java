package com.example.week5daily1homeassignment.view.activities.mainactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.week5daily1homeassignment.R;
import com.example.week5daily1homeassignment.view.activities.chatactivity.ChatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements MainActivityContract {

    FirebaseAuth mAuth;
    MainActivityPresenter mainActivityPresenter;

    EditText emailEditTextView;
    EditText passwordEditTextView;
    TextView loginResultTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mAuth = FirebaseAuth.getInstance();

        mainActivityPresenter = new MainActivityPresenter(this);

        emailEditTextView = findViewById(R.id.emailEditTextViewId);
        passwordEditTextView = findViewById(R.id.passwordEditTextViewId);
        loginResultTextView = findViewById(R.id.loginResultTextViewId);

    }

    public void onClick(View view) {

        String email = emailEditTextView.getText().toString();
        String password = passwordEditTextView.getText().toString();

        switch (view.getId()) {

            case R.id.signInButtonId:
                mainActivityPresenter.userSignIn(mAuth, email, password);
                break;

            case R.id.signUpButtonId:
                mainActivityPresenter.userSignUp(mAuth, email, password);
                break;

        }


    }

    @Override
    public void sendToActivity(FirebaseUser user) {

        if(user != null){

            successfullLogin(user);

        }
        else {

            loginResultTextView.setText("Login Failed!!!!");

        }

    }

    private void successfullLogin(FirebaseUser userInfo) {

        loginResultTextView.setText("Login Success!!!!");

        Intent intent = new Intent(this, ChatActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("user", userInfo);
        intent.putExtras(bundle);
        startActivity(intent);
    }

}
