package com.example.week5daily1homeassignment.view.activities.chatactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.week5daily1homeassignment.R;
import com.example.week5daily1homeassignment.model.user.User;

public class ChatActivity extends AppCompatActivity implements ChatActivityContract {

    ChatActivityPresenter chatActivityPresenter;
    TextView messageReceivedTextView;
    EditText messageSentEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        chatActivityPresenter = new ChatActivityPresenter(this);
        messageReceivedTextView = findViewById(R.id.messageReceivedTextViewId);
        messageSentEditText = findViewById(R.id.messageSentEditTextId);


        Bundle bundle = getIntent().getExtras();

        if(bundle != null) {

            chatActivityPresenter.getUserNameFromFirebase(bundle);

        }



        chatActivityPresenter.getUserData();
    }


    public void onClick(View view) {

        String mesg = messageSentEditText.getText().toString();

        chatActivityPresenter.saveUserInfoToFirebaseDB(mesg);

        Toast.makeText(this, "message sent", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void sendToChatActivity(User user) {

        messageReceivedTextView.setText(user.getMessegeReceived());

    }
}
