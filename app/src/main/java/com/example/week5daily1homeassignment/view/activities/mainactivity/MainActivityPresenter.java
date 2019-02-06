package com.example.week5daily1homeassignment.view.activities.mainactivity;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivityPresenter {

    MainActivityContract mainActivityContract;


    public MainActivityPresenter(MainActivityContract mainActivityContract) {
        this.mainActivityContract = mainActivityContract;
    }

    public void userSignIn(final FirebaseAuth firebaseAuth, String email, String password){


        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new MainActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("TAG", "signInWithEmail:success");
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            mainActivityContract.sendToActivity(user);
                        } else {
                            mainActivityContract.sendToActivity(null);

                        }

                    }
                });
    }


    public void userSignUp(final FirebaseAuth firebaseAuth, String email, String password) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new MainActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("TAG", "createUserWithEmail:success");
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            mainActivityContract.sendToActivity(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("TAG", "createUserWithEmail:failure", task.getException());
                            mainActivityContract.sendToActivity(null);
                        }
                    }
                });
    }



}
