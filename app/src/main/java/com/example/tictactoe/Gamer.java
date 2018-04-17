/*
 * Copyright 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.tictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Gamer extends AppCompatActivity implements View.OnClickListener {

    String player1Name;
    String player2Name;

    private Button[][] cells = new Button[3][3];

    private boolean gamer1Turn = true;

    private int roundCount;

    private int gamer1Points;
    private int gamer2Points;

    private TextView textViewPlayer1;
    private TextView textViewPlayer2;
    private TextView isPlayerTurn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamer);

        Intent intent = getIntent();
        player1Name = intent.getExtras().getString("1st Player");
        player2Name = intent.getExtras().getString("2nd Player");

        textViewPlayer1 = findViewById(R.id.text_view_p1);
        textViewPlayer2 = findViewById(R.id.text_view_p2);
        isPlayerTurn = findViewById(R.id.player_turn);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String cellID = "cell_" + i + j;
                int resID = getResources().getIdentifier(cellID, "id", getPackageName());
                cells[i][j] = findViewById(resID);
                cells[i][j].setOnClickListener(this);
            }
        }

        Button buttonReset = findViewById(R.id.button_reset);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });

        isPlayerTurn.setText(player1Name + "'s Turn");
        textViewPlayer1.setText(player1Name + ": 0");
        textViewPlayer2.setText(player2Name + ": 0");
    }

    @Override
    public void onClick(View v) {
        if (!((Button) v).getText().toString().equals("")) {
            return;
        }

        if (gamer1Turn) {
            ((Button) v).setText("X");
            isPlayerTurn.setText(player2Name + "'s Turn");
        } else {
            ((Button) v).setText("O");
            isPlayerTurn.setText(player1Name + "'s Turn");
        }

        roundCount++;

        if (checkForWin()) {
            if (gamer1Turn) {
                gamer1Wins();
            } else {
                gamer2Wins();
            }
        } else if (roundCount == 9) {
            draw();
        } else {
            gamer1Turn = !gamer1Turn;
        }

    }

    private boolean checkForWin() {
        String[][] field = new String[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = cells[i][j].getText().toString();
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

    private void gamer1Wins() {
        gamer1Points++;
        Toast.makeText(this, player1Name + " wins", Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
    }

    private void gamer2Wins() {
        gamer2Points++;
        Toast.makeText(this, player2Name + " wins", Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
    }

    private void draw() {
        Toast.makeText(this, "Draw!", Toast.LENGTH_SHORT).show();
        resetBoard();
    }

    private void updatePointsText() {
        textViewPlayer1.setText(player1Name + ": " + gamer1Points);
        textViewPlayer2.setText(player2Name + ": " + gamer2Points);
    }

    private void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cells[i][j].setText("");
            }
        }

        roundCount = 0;
        gamer1Turn = true;
        isPlayerTurn.setText(player1Name + "'s Turn");
    }

    private void resetGame() {
        gamer1Points = 0;
        gamer2Points = 0;
        updatePointsText();
        resetBoard();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("roundCount", roundCount);
        outState.putInt("gamer1Points", gamer1Points);
        outState.putInt("gamer2Points", gamer2Points);
        outState.putBoolean("gamer1Turn", gamer1Turn);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        roundCount = savedInstanceState.getInt("roundCount");
        gamer1Points = savedInstanceState.getInt("gamer1Points");
        gamer2Points = savedInstanceState.getInt("gamer2Points");
        gamer1Turn = savedInstanceState.getBoolean("gamer1Turn");
    }
}