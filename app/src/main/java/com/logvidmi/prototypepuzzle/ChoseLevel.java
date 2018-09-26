package com.logvidmi.prototypepuzzle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.logvidmi.prototypepuzzle.setup.ApplicationFactory;

public class ChoseLevel extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chose_level);
        setControllers();
    }

    private void setControllers() {
        Button easyButton = (Button) findViewById(R.id.easy);
        easyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApplicationFactory.getApplicationFactory().setColumns(3);
                ApplicationFactory.getApplicationFactory().setRows(3);
                startGame();
            }
        });
    }

    private void startGame() {
        Intent chooseImageForPuzzleIntent = new Intent(this, ChooseImageForPuzzle.class);
        startActivity(chooseImageForPuzzleIntent);
    }


}
