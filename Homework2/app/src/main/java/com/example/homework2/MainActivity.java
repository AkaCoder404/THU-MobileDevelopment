package com.example.homework2;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    boolean isShowName = false;
    String idName = "李天勤 2018080106";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // simpleButton
        Button simpleButton = (Button) findViewById(R.id.simpleButton);
        // initialize simpleText
        TextView simpleText = (TextView) findViewById(R.id.simpleText);
        simpleText.setText("");


        simpleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(getApplicationContext(), idName, Toast.LENGTH_LONG).show();//display the text of button1
                if (isShowName) simpleText.setText("");
                else simpleText.setText(idName);
                isShowName = !isShowName;
            }
        });
    }

    // Call function when button is pressed
    private void onClickSimpleButton(TextView textView) {
        if (isShowName) textView.setText("");
        else textView.setText(idName);
        isShowName = !isShowName;
    }

}