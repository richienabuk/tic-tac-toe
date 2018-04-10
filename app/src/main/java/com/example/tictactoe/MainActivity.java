package com.example.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button[][] cells = new Button[3][3];

    private boolean gamer1Turn = true;

    private int roundCount;

    private int gamer1Points;
    private int gamer2Points;

    private TextView textViewPlayer1;
    private TextView textViewPlayer2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewPlayer1 = findViewById(R.id.text_view_p1);
        textViewPlayer2 = findViewById(R.id.text_view_p2);

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
    }

    @Override
    public void onClick(View v) {
        if (!((Button) v).getText().toString().equals("")) {
            return;
        }

        if (gamer1Turn) {
            ((Button) v).setText("X");
        } else {
            ((Button) v).setText("O");
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
        Toast.makeText(this, "1st Player wins!", Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
    }

    private void gamer2Wins() {
        gamer2Points++;
        Toast.makeText(this, "2nd Player wins!", Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
    }

    private void draw() {
        Toast.makeText(this, "Draw!", Toast.LENGTH_SHORT).show();
        resetBoard();
    }

    private void updatePointsText() {
        textViewPlayer1.setText("1st Player: " + gamer1Points);
        textViewPlayer2.setText("2nd Player: " + gamer2Points);
    }

    private void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cells[i][j].setText("");
            }
        }

        roundCount = 0;
        gamer1Turn = true;
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