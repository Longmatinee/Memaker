package com.example.matinee.memaker;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
    private EditText userText;
    private TextView memText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        memText = (TextView)findViewById(R.id.memText);
        userText = (EditText)findViewById(R.id.userText);
    }
// Button which add text to your mem
    public void onClickAddText(View view) {
        if (userText.getText().length() == 0) {
            memText.setText("Не придумал мем");
        } else {
            memText.setText(userText.getText());
        }
    }
}
