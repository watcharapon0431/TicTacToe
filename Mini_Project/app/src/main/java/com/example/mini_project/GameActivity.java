package com.example.mini_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    protected Button[][] buttons = new Button[3][3];
    protected boolean player1Turn = true;
    protected int roundCount;
    protected int playerOPoints;
    protected int playerXPoints;
    protected Button btnHome;
    protected Button btnReset;
    protected TextView txtScoreO;
    protected TextView txtScoreX;
    protected TextView txtPnameO;
    protected TextView txtPnameX;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Bundle bundle = getIntent().getExtras();
        String PnameO = bundle.getString("PnameO");
        String PnameX = bundle.getString("PnameX");
        txtPnameO = findViewById(R.id.txtPnameO);
        txtPnameX = findViewById(R.id.txtPnameX);
        txtPnameO.setText(PnameO);
        txtPnameX.setText(PnameX);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String buttonID = "box_" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                txtScoreO = findViewById(R.id.txtScoreO);
                txtScoreX = findViewById(R.id.txtScoreX);
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(this);
            }
        }

        btnHome = findViewById(R.id.btnHome);
        btnReset = findViewById(R.id.btnReset);

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (!((Button) v).getText().toString().equals("")) {
            return;
        }
        if (player1Turn) {
            ((Button) v).setText("O");
        } else {
            ((Button) v).setText("X");
        }
        roundCount++;
        if (checkForWin()) {
            if (player1Turn) {
                playerOWins();
            } else {
                playerXWins();
            }
        } else if (roundCount == 9) {
            draw();
        } else {
            player1Turn = !player1Turn;
        }
    }

    private boolean checkForWin() {
        String[][] field = new String[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = buttons[i][j].getText().toString();
            }
        }
        for (int i = 0; i < 3; i++) {
            if (field[i][0].equals(field[i][1])
                    && field[i][0].equals(field[i][2])
                    && !field[i][0].equals("")) {
                return true;
            }
        }
        for (int i = 0; i < 3; i++) {
            if (field[0][i].equals(field[1][i])
                    && field[0][i].equals(field[2][i])
                    && !field[0][i].equals("")) {
                return true;
            }
        }
        if (field[0][0].equals(field[1][1])
                && field[0][0].equals(field[2][2])
                && !field[0][0].equals("")) {
            return true;
        }
        if (field[0][2].equals(field[1][1])
                && field[0][2].equals(field[2][0])
                && !field[0][2].equals("")) {
            return true;
        }
        return false;
    }

    private void playerOWins() {
        playerOPoints++;
        Toast.makeText(this, "Player O wins!", Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
    }

    private void playerXWins() {
        playerXPoints++;
        Toast.makeText(this, "Player X wins!", Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
    }

    private void draw() {
        Toast.makeText(this, "Draw!", Toast.LENGTH_SHORT).show();
        resetBoard();
    }

    private void updatePointsText() {
        txtScoreO.setText(String.valueOf(playerOPoints));
        txtScoreX.setText(String.valueOf(playerXPoints));
    }

    private void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }
        roundCount = 0;
        player1Turn = true;
    }

    private void resetGame() {
        playerOPoints = 0;
        playerXPoints = 0;
        updatePointsText();
        resetBoard();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("roundCount", roundCount);
        outState.putInt("playerOPoints", playerOPoints);
        outState.putInt("playerXPoints", playerXPoints);
        outState.putBoolean("player1Turn", player1Turn);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        roundCount = savedInstanceState.getInt("roundCount");
        playerOPoints = savedInstanceState.getInt("playerOPoints");
        playerXPoints = savedInstanceState.getInt("playerXPoints");
        player1Turn = savedInstanceState.getBoolean("player1Turn");
    }
}