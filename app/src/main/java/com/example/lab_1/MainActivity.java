package com.example.lab_1;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    TextView tvInfo;
    EditText etInput;
    Button bControl;
    int guess;
    boolean gameFinish;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        tvInfo = (TextView)findViewById(R.id.textView);
        etInput = (EditText)findViewById(R.id.editTextNumberDecimal);
        bControl = (Button) findViewById(R.id.button);
        guess = (int) (Math.random() * 100);
        gameFinish = false;
    }
    private boolean TryParse(){
        try {
            Integer.parseInt(etInput.getText().toString());
            return true;
        }catch (Exception exc){
            tvInfo.setText(R.string.error);
            return false;
        }
    }
    public void OnClick(View v){
        if(!TryParse()){
            return;
        }
        int value = Integer.parseInt(etInput.getText().toString());
        if(value < 0 || value > 100){
            tvInfo.setText(R.string.error);
        }
        else {
            if(gameFinish){
                guess = (int) (Math.random() * 100);
                bControl.setText(R.string.input_value);
                gameFinish = false;
            }
            else {
                if(guess == value) {
                    tvInfo.setText(R.string.hit);
                    bControl.setText(R.string.play_more);
                    gameFinish = true;
                    return;
                }
                else if(guess > value){
                    tvInfo.setText(R.string.behind);
                }
                else {
                    tvInfo.setText(R.string.ahead);
                }
            }
        }
    }
}