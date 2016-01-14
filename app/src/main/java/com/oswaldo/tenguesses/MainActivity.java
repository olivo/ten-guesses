package com.oswaldo.tenguesses;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    static int secretNumber;
    static int remainingGuesses;

    public void checkGuess(View view) {

        String guess = ((EditText)findViewById(R.id.editText)).getText().toString();
        int guessNumber = Integer.parseInt(guess);

        if (secretNumber == guessNumber) {

            Toast.makeText(getApplicationContext(), "YOU WON!!!", Toast.LENGTH_LONG).show();
        }
        else {

            Toast.makeText(getApplicationContext(),
                    "The secret number is " + (secretNumber < guessNumber ? "smaller" : "larger") +
                            " than " + guessNumber, Toast.LENGTH_SHORT).show();
            remainingGuesses--;
            updateGuessesText();
        }

        if (remainingGuesses == 0) {

            Toast.makeText(getApplicationContext(),
                    "You lost :( . The secret number was " + secretNumber, Toast.LENGTH_LONG).show();
            initializeData();
        }
    }

    private void updateGuessesText() {

        ((TextView)findViewById(R.id.numGuessesText))
                .setText("You have " + remainingGuesses + " guesses remaining.");
    }

    private void initializeData() {

        Random random = new Random();
        secretNumber = random.nextInt(1000001);
        remainingGuesses = 10;
        updateGuessesText();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeData();
    }
}
