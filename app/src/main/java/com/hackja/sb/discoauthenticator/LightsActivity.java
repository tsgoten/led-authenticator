package com.hackja.sb.discoauthenticator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LightsActivity extends AppCompatActivity {

    CheckBox light1;
    CheckBox light2;
    CheckBox light3;
    CheckBox light4;
    CheckBox light5;
    Button submit;

    int ans;

    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference usersRef = firebaseDatabase.getReference("users");
    DatabaseReference codeRef = firebaseDatabase.getReference("currentCode");

    int lightCode = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lights);

        codeRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("snapshot", dataSnapshot.getValue()+"");
                lightCode = Integer.parseInt(dataSnapshot.getValue().toString());
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        light1=(CheckBox)findViewById(R.id.light1);
        light2=(CheckBox)findViewById(R.id.light2);
        light3=(CheckBox)findViewById(R.id.light3);
        light4=(CheckBox)findViewById(R.id.light4);
        light5=(CheckBox)findViewById(R.id.light5);
        submit=(Button)findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ans=0;
                if (light5.isChecked()){
                    ans+=Math.pow(10, 0);
                }
                if (light4.isChecked()){
                    ans+=Math.pow(10, 1);
                }
                if (light3.isChecked()){
                    ans+=Math.pow(10, 2);
                }
                if (light2.isChecked()){
                    ans+=Math.pow(10, 3);
                }
                if (light1.isChecked()){
                    ans+=Math.pow(10, 4);
                }
                Log.d("ans", ans+"");
                Log.d("lightcode", lightCode+"");
                if(lightCode==ans) {
                    finish();//check if the code is right before moving on, this is just a test
                }
                else{
                    Toast.makeText(LightsActivity.this, "Did not work", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {

    }
}
