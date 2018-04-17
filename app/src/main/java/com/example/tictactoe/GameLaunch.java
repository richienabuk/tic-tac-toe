/*
 * Copyright 2018 The Android Open Source Project
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
import android.widget.EditText;

public class GameLaunch extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamelaunch);
    }


    public void submitName(View view) {
        EditText p1 = findViewById(R.id.player1);
        EditText p2 = findViewById(R.id.player2);
        String player1NameText = p1.getText().toString();
        String player2NameText = p2.getText().toString();

        if(player1NameText.equals("")){
            player1NameText="1st Player";
            p1.setText(R.string.player_1_default_name);
        }
        if(player2NameText.equals("")){
            player2NameText="2nd Player";
            p2.setText(R.string.player_2_default_name);
        }
        Intent intent = new Intent(this,Gamer.class);
        intent.putExtra("1st Player",player1NameText);
        intent.putExtra("2nd Player",player2NameText);
        if(intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
            finish();
        }
    }

}