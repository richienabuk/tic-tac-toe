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

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

//     Launches the gamers view
    public void GameLaunch(View view) {
        Intent intent = new Intent(this,GameLaunch.class);
        if(intent.resolveActivity(getPackageManager())!=null) {
            startActivity(intent);
        }
    }

//         Launches the gamers view
    public void HowTo(View view) {
        Intent intent = new Intent(this,HowTo.class);
        if(intent.resolveActivity(getPackageManager())!=null) {
            startActivity(intent);
        }
    }

//     Launches the about developer view
    public void About(View view) {
        Intent intent = new Intent(this, About.class);
        if(intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
        }
    }

}
