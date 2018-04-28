package com.hackja.sb.discoauthenticator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LightsActivity extends AppCompatActivity {

    /*CheckBox light1;
    CheckBox light2;
    CheckBox light3;
    CheckBox light4;
    CheckBox light5;*/
    ImageView light1;
    ImageView light2;
    ImageView light3;
    ImageView light4;
    ImageView light5;

    boolean[] lights;

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


        lights=new boolean[5];
        for (int i=0; i<lights.length; i++){
            lights[i]=false;
        }

        light1=(ImageView)findViewById(R.id.light1);
        light2=(ImageView)findViewById(R.id.light2);
        light3=(ImageView)findViewById(R.id.light3);
        light4=(ImageView)findViewById(R.id.light4);
        light5=(ImageView)findViewById(R.id.light5);
        submit=(Button)findViewById(R.id.submit);

        light1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lights[0]){
                    lights[0]=false;
                    light1.setImageResource(R.drawable.yellowoff);
                } else {
                    lights[0]=true;
                    light1.setImageResource(R.drawable.yellowon);
                }
            }
        });

        light2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lights[1]){
                    lights[1]=false;
                    light2.setImageResource(R.drawable.greenoff);
                } else {
                    lights[1]=true;
                    light2.setImageResource(R.drawable.greenon);
                }
            }
        });

        light3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lights[2]){
                    lights[2]=false;
                    light3.setImageResource(R.drawable.blueoff);
                } else {
                    lights[2]=true;
                    light3.setImageResource(R.drawable.blueon);
                }
            }
        });

        light4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lights[3]){
                    lights[3]=false;
                    light4.setImageResource(R.drawable.greenoff);
                } else {
                    lights[3]=true;
                    light4.setImageResource(R.drawable.greenon);
                }
            }
        });

        light5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lights[4]){
                    lights[4]=false;
                    light5.setImageResource(R.drawable.redoff);
                } else {
                    lights[4]=true;
                    light5.setImageResource(R.drawable.redon);
                }
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ans=0;
                if (lights[4]){
                    ans+=Math.pow(10, 0);
                }
                if (lights[3]){
                    ans+=Math.pow(10, 1);
                }
                if (lights[2]){
                    ans+=Math.pow(10, 2);
                }
                if (lights[1]){
                    ans+=Math.pow(10, 3);
                }
                if (lights[0]){
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
