package com.example.mini_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class GameActivity_Offline extends AppCompatActivity {

    protected EditText edtPnameO;
    protected EditText edtPnameX;
    protected Button btnStart;
    protected Button btnCancle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_offline);

        edtPnameO = findViewById(R.id.edtPnameO);
        edtPnameX = findViewById(R.id.edtPnameX);

        btnStart = findViewById(R.id.btnStart);
        btnCancle = findViewById(R.id.btnCancle);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String PnameO = edtPnameO.getText().toString().trim();
                String PnameX = edtPnameX.getText().toString().trim();

                Intent intent = new Intent(GameActivity_Offline.this, GameActivity.class);
                intent.putExtra("PnameO", PnameO);
                intent.putExtra("PnameX", PnameX);
                startActivity(intent);
            }
        });

        btnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameActivity_Offline.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}