package com.hackja.sb.discoauthenticator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Starting Login Activity
        intent = new Intent(this, LoginActivity.class);
        //startActivity(intent);
        startActivityForResult(intent, 0); //0 requests boolean, 1 sends it back
        super.onCreate(savedInstanceState);



        firebaseAuth.getInstance();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==1) {
            boolean valid = intent.getBooleanExtra("valid", false);
            if (valid) {
                setContentView(R.layout.activity_main);
                Log.d("check", "success");
            } else {
                Log.d("check", "failure");
                intent = new Intent(this, LoginActivity.class);
                startActivityForResult(intent, 0);
            }
        }
    }
}
