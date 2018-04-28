package com.hackja.sb.discoauthenticator;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {
    //Views
    EditText email, password;
    Button login;
    //Firebase
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference usersRef = firebaseDatabase.getReference("users");
    DatabaseReference codeRef = firebaseDatabase.getReference("currentCode");

    Intent intent;

    ArrayList<User> users = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //Views
        email = findViewById(R.id.editTextEmail);
        password = findViewById(R.id.editTextPassword);
        login = findViewById(R.id.buttonLogIn);

        intent = new Intent(this, LightsActivity.class);

        //Firebase
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validEntry()) {
                    resetCode();
                    finish();
                }
                else{
                    Toast.makeText(LoginActivity.this, "Unregistered User", Toast.LENGTH_SHORT).show();
                }
            }
        });
        usersRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot userSnapshot : dataSnapshot.getChildren()){
                    users.add(new User((String)userSnapshot.child("email").getValue(), (String) userSnapshot.child("password").getValue()));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
    public void resetCode(){
        boolean [] lightsOn=new boolean[5];
        boolean valid=false;
        for (int i=0; i<5; i++){
            int r=(int)(Math.random()*2);
            if (r==0){
                lightsOn[i]=true;
                valid=true;
            } else lightsOn[i]=false;
        }
        if (!valid){
            int r=(int)(Math.random()*5);
            lightsOn[r]=true;
        }
        int ans=0;
        for (int i=0; i<lightsOn.length; i++){
            if (lightsOn[i]){
                ans+=Math.pow(10, i); //descending order, 5th, 4th, 3rd, 2nd, 1st
            }
        }
        Log.d("Code", ans+"");
        codeRef.setValue(ans);
    }

    @Override
    public void onBackPressed() {

    }
    public boolean validEntry(){
        User entry = new User(email.getText().toString(), password.getText().toString());
        for(User user: users){
            if(entry.isEqual(user))
                return true;
        }
        return false;
    }
}
