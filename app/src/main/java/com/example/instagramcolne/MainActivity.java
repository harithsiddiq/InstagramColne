package com.example.instagramcolne;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    EditText name, wg;
    TextView getdata , gotoL, gotoS;
    Button getall;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        name = findViewById(R.id.name);
        wg = findViewById(R.id.wg);
        getdata = findViewById(R.id.getdata);
        getall = findViewById(R.id.getall);

        getdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ParseQuery<ParseObject> parseQuery = ParseQuery.getQuery("Boxer");
                parseQuery.getInBackground("uKD6Doi9RO", new GetCallback<ParseObject>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void done(ParseObject object, ParseException e) {
                        getdata.setText("Name: " +object.getString("Name") +" Wight: " + object.getString("Wight"));
                    }
                });

            }
        });


        getall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ParseQuery<ParseObject> queryAll = ParseQuery.getQuery("Boxer");
                queryAll.whereNotEqualTo("Wight", null);
                queryAll.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {

                        for (ParseObject boxer: objects){
                            getdata.append("Name: " +boxer.get("Name")+"\n");
                        }

                    }
                });
            }
        });
    }

    public void parse(View view) {

        try {
            ParseObject boxer = new ParseObject("Boxer");
            boxer.put("Name",  name.getText().toString());
            boxer.put("Wight", wg.getText().toString());
            boxer.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {
                        FancyToast.makeText(MainActivity.this,name.getText()+" Added success",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();
                    } else {
                        FancyToast.makeText(MainActivity.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR,true).show();
                    }
                }
            });
        }catch (Exception e) {
            FancyToast.makeText(MainActivity.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR,true).show();
        }

    }

    public void SingUp(View view) {

        Intent singup = new Intent(MainActivity.this, SingupLogIn.class);
        startActivity(singup);
    }
}
