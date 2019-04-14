package com.example.thermonitor;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class DeviceDetail extends AppCompatActivity {
    String espinfo;
    Firebase myFirebase;
    DatabaseReference temperatures;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_detail);
//        Bundle extras = getIntent().getExtras();
//        if(extras!= null){
//            espinfo = extras.getString("temperature");
//        }
        Intent intent = getIntent();
        espinfo = intent.getStringExtra("esp");

      final TextView myTextView = findViewById(R.id.temperature);
        Firebase.setAndroidContext(getApplicationContext());

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(espinfo);
//        Query query = ref.child(espinfo).orderByChild("temperature").addListenerForSingleValueEvent(new com.google.firebase.database.ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull com.google.firebase.database.DataSnapshot dataSnapshot) {
//                Integer reading = dataSnapshot.getValue(Integer.class);
//                myTextView.setText("Temperature:"+ reading + "/n");
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                myTextView.setText("Error found!!!");
//            }
//        });
        com.google.firebase.database.Query firebaseSearchQuery = ref.orderByChild("temperature");
        firebaseSearchQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Integer reading = dataSnapshot.getValue(Integer.class);
                myTextView.setText("Temperature:"+ reading + "/n");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                myTextView.setText("Error found!!!");
            }
        });


    }
}
