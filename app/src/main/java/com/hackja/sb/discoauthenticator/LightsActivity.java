package com.hackja.sb.discoauthenticator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class LightsActivity extends AppCompatActivity {

    CheckBox light1;
    CheckBox light2;
    CheckBox light3;
    CheckBox light4;
    CheckBox light5;
    Button submit;

    int ans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lights);

        ans=0;

        light1=(CheckBox)findViewById(R.id.light1);
        light2=(CheckBox)findViewById(R.id.light2);
        light3=(CheckBox)findViewById(R.id.light3);
        light4=(CheckBox)findViewById(R.id.light4);
        light5=(CheckBox)findViewById(R.id.light5);
        submit=(Button)findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (light1.isChecked()){
                    ans+=Math.pow(10, 0);
                }
                if (light2.isChecked()){
                    ans+=Math.pow(10, 1);
                }
                if (light3.isChecked()){
                    ans+=Math.pow(10, 2);
                }
                if (light4.isChecked()){
                    ans+=Math.pow(10, 3);
                }
                if (light5.isChecked()){
                    ans+=Math.pow(10, 4);
                }

                Log.d("Code", "input: "+ans);
                Intent returnIntent = new Intent();
                returnIntent.putExtra("result", Integer.toString(ans));
                Log.d("Code", "hey i'm about to enter onActivityResult okay");
                setResult(3, returnIntent);
                finish();//check if the code is right before moving on, this is just a test
            }
        });
    }
}
