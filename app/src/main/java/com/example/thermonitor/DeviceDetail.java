package com.example.thermonitor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class DeviceDetail extends AppCompatActivity {

    Firebase myFirebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_detail);

        final TextView myTextView = findViewById(R.id.temperature);
        Firebase.setAndroidContext(getApplicationContext());

        myFirebase = new Firebase("https://thermonitor-95c0c.firebaseio.com/Temperature");

        myFirebase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Integer reading = dataSnapshot.getValue(Integer.class);
                myTextView.setText("Temperature:"+ reading + "/n");
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                    myTextView.setText("Error found!!!");
            }
        });
    }
}
