package com.example.instagramcolne;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SingupLogIn extends AppCompatActivity {

    Button btnLogin, btnSingUp;
    TextView gotoL, gotoS;
    ConstraintLayout loginScreen, SingScreen;
    EditText singEditName, singEditPass , logname, logpass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singup_log_in);


        // Start Animation

        ConstraintLayout constraintLayout = findViewById(R.id.layout);
        AnimationDrawable animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();


        // EditText for SingUp
        singEditName = findViewById(R.id.singname);
        singEditPass = findViewById(R.id.singpass);

        // EditText for Login
        logname = findViewById(R.id.logname);
        logpass = findViewById(R.id.logpass);

        // toggle singup / login
        gotoL = findViewById(R.id.goTologin);
        gotoS = findViewById(R.id.goToSing);

        // btn for login and sing
        btnLogin = findViewById(R.id.btnLogin);
        btnSingUp = findViewById(R.id.btnsing);

        loginScreen = findViewById(R.id.Login);
        SingScreen = findViewById(R.id.SingUp);


        // login user
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ParseUser.logInInBackground(logname.getText().toString(), logpass.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {

                        if (user != null && e == null) {
                            Intent i = new Intent(SingupLogIn.this, WellcomeActivity.class);
                            i.putExtra("name", logname.getText().toString());
                            startActivity(i);
                            FancyToast.makeText(getApplicationContext(),"LogIn Success",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,false).show();
                        } else {
                            FancyToast.makeText(getApplicationContext(),"Rong user name or password",FancyToast.LENGTH_LONG,FancyToast.ERROR,false).show();

                        }
                    }
                });

            }
        });


        // sing up
        btnSingUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ParseUser user = new ParseUser();
                user.setUsername(singEditName.getText().toString());
                user.setPassword(singEditPass.getText().toString());
                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            FancyToast.makeText(getApplicationContext(),"SingUp Success",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,false).show();
                        } else {
                            FancyToast.makeText(getApplicationContext(),"Error",FancyToast.LENGTH_LONG,FancyToast.ERROR,false).show();
                        }
                    }
                });

            }
        });



        // go to Sing up
        gotoS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             loginScreen.setVisibility(View.GONE);
             SingScreen.setVisibility(View.VISIBLE);
            }
        });

        // go to Login
        gotoL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginScreen.setVisibility(View.VISIBLE);
                SingScreen.setVisibility(View.GONE);
            }
        });

    }
}
