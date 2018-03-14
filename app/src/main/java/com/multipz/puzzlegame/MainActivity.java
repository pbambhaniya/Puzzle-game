package com.multipz.puzzlegame;

import android.content.Context;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Context context;

    int clickCount = 0;

    List<Integer> clickedValue = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        context = this;


        for (int i=1; i<=9; i++){
            ((Button) findViewById(getResources().getIdentifier("btn"+i, "id", getPackageName()))).setOnClickListener(this);
        }


    }

    @Override
    public void onClick(View view) {
        int ccc = new Random().nextInt(2);
        ((Button)view).setText(ccc+"");
        clickedValue.add(ccc);
        clickCount++;
        ((Button)view).setClickable(false);

        if (clickCount == 3){

            for (int i=1; i<=9; i++){
                ((Button) findViewById(getResources().getIdentifier("btn"+i, "id", getPackageName()))).setClickable(false);
            }

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    resetAll();
                }
            },1500);

        }
    }

    private void resetAll() {

        clickCount = 0;
        boolean isWin = true;

        for (int i=0; i<clickedValue.size(); i++){
            if (clickedValue.get(0) != clickedValue.get(i)){
                isWin = false;
                break;
            }
        }
        clickedValue.clear();

        if (isWin)
            Toast.makeText(context, "Won", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(context, "Loss", Toast.LENGTH_SHORT).show();

        for (int i=1; i<=9; i++){
            Button btn = ((Button) findViewById(getResources().getIdentifier("btn"+i, "id", getPackageName())));
            btn.setOnClickListener(this);
            btn.setText("?");
            btn.setClickable(true);
        }


    }
}
