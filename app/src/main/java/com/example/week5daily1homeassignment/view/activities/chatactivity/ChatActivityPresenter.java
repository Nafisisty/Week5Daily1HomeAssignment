package com.example.week5daily1homeassignment.view.activities.chatactivity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.week5daily1homeassignment.model.user.User;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;

public class ChatActivityPresenter {

    ChatActivityContract chatActivityContract;
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    String userName;
    User user;

    private static final String USERS = "users";


    public ChatActivityPresenter(ChatActivityContract chatActivityContract) {
        this.chatActivityContract = chatActivityContract;
    }

    public void getUserData() {


        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference(USERS);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                User userFromDatabase = new User();

                Iterator<DataSnapshot> iterator = dataSnapshot.getChildren().iterator();

                while (iterator.hasNext()) {
                    String jsonResponse = iterator.next().getValue().toString();
                    userFromDatabase = new Gson().fromJson(jsonResponse, User.class);
                    Log.d("TAG", "onCreateJson: " + userFromDatabase.getMessegeReceived());
                }
                chatActivityContract.sendToChatActivity(userFromDatabase);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


    public void saveUserInfoToFirebaseDB(String message) {

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        String currentTime = dateFormat.format(calendar.getTime());

        User user = new User("'" + userName + "'", "'" + currentTime + "'","'" + message + "'");

        String key = databaseReference.getKey();
        databaseReference.child(key).setValue(user);
        databaseReference.child(key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d("TAG", "saveUserInfoToFirebaseDB: " + dataSnapshot.getKey() +  " = " + dataSnapshot.getValue());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        chatActivityContract.sendToChatActivity(user);
    }

    public void getUserNameFromFirebase(Bundle bundle) {

        FirebaseUser firebaseUser = bundle.getParcelable("user");

        userName = firebaseUser.getEmail();

    }
}
