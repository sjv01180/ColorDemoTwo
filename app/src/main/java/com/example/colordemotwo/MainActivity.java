package com.example.colordemotwo;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private String[] tempColors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tempColors = new String[6];
        addListener();
        String[] colors = getResources().getStringArray(R.array.colors);
        setTempColors(colors);

        setColors(this.getTempColors()[0], (EditText) findViewById(R.id.input_1));
        setColors(this.getTempColors()[1], (EditText) findViewById(R.id.input_2));
        setColors(this.getTempColors()[2], (EditText) findViewById(R.id.input_3));
        setColors(this.getTempColors()[3], (EditText) findViewById(R.id.input_4));
        setColors(this.getTempColors()[4], (EditText) findViewById(R.id.input_5));
        setColors(this.getTempColors()[5], (EditText) findViewById(R.id.input_6));
    }

    /**
     * modifies tempColor by copying from a reference array
     * @param reference the reference array
     */
    private void setTempColors(String[] reference) {
        for(int i = 0; i < tempColors.length; i++) {
            tempColors[i] = reference[i];
        }
    }

    /**
     * Grabs tempColors;
     */
    private String[] getTempColors() {
        return tempColors;
    }

    /**
     * Listens for user input and calls a function upon hitting the submit button
     */
    private void addListener() {
        EditText.OnEditorActionListener listener = new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionID, KeyEvent e) {
                if(actionID == EditorInfo.IME_ACTION_DONE) {
                    submitColor(v);
                    InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    return true;
                }
                return false;
            }
        };
        EditText input = findViewById(R.id.input_1);
        input.setOnEditorActionListener(listener);

        EditText input2 = findViewById(R.id.input_2);
        input2.setOnEditorActionListener(listener);

        EditText input3 = findViewById(R.id.input_3);
        input3.setOnEditorActionListener(listener);

        EditText input4 = findViewById(R.id.input_4);
        input4.setOnEditorActionListener(listener);

        EditText input5 = findViewById(R.id.input_5);
        input5.setOnEditorActionListener(listener);

        EditText input6 = findViewById(R.id.input_6);
        input6.setOnEditorActionListener(listener);
    }

    /**
     * Get the color name of the input field and calls guessColor for color matching
     * @param view
     */
    public void submitColor(View view) {
        EditText input;
        String name;
        switch(view.getId()) {
            case (R.id.input_1):
                input = (EditText) findViewById(R.id.input_1);
                name = input.getText().toString().toLowerCase();
                guessColor(name, 0);
                break;
            case (R.id.input_2):
                input = (EditText) findViewById(R.id.input_2);
                name = input.getText().toString().toLowerCase();
                guessColor(name, 1);
                break;
            case (R.id.input_3):
                input = (EditText) findViewById(R.id.input_3);
                name = input.getText().toString().toLowerCase();
                guessColor(name, 2);
                break;
            case (R.id.input_4):
                input = (EditText) findViewById(R.id.input_4);
                name = input.getText().toString().toLowerCase();
                guessColor(name, 3);
                break;
            case (R.id.input_5):
                input = (EditText) findViewById(R.id.input_5);
                name = input.getText().toString().toLowerCase();
                guessColor(name, 4);
                break;
            case (R.id.input_6):
                input = (EditText) findViewById(R.id.input_6);
                name = input.getText().toString().toLowerCase();
                guessColor(name, 5);
                break;
            default:
                throw new RuntimeException("unknown input error");
        }

    }

    /**
     * Sets background color and clears text values for each EditText tag.
     * @param colorCell the cell of color array used to edit background coloring
     * @param input the EditText ID to edit the background color and text
     */
    private void setColors(String colorCell, EditText input) {
        int index1 = getResources().getIdentifier(colorCell, "color", this.getPackageName());
        int bg_color = ContextCompat.getColor(this, index1);
        input.setBackgroundColor(bg_color);
        input.setText("");
    }

    /**
     * compares a string passed in with the index of the temporary array
     * @param colorName the user input to compare with an array cell
     * @param index the index of tempColors to compare with the user input
     */
    private void guessColor(String colorName, int index) {
        String[] colorsTemp = this.getTempColors();
        int colorID = getResources().getIdentifier(colorName, "color", this.getPackageName());
        if(colorID != 0 && colorName.equals(colorsTemp[index])){
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
        }
        else Toast.makeText(this, "Incorrect. Try again", Toast.LENGTH_SHORT).show();
    }

    /**
     * Shuffles the color string array with fisher-yates and sets the temporary color array in addition to setting background colors
     * @param view button passed into function
     */
    public void shuffle(View view) {
        String[] colors = getResources().getStringArray(R.array.colors);
        Random shuffler = new Random();
        for(int i = colors.length - 1; i > 0; i--) {
            int index = shuffler.nextInt(i);
            String tmp = colors[index];
            colors[index] = colors[i];
            colors[i] = tmp;
        }

        setTempColors(colors);
        setColors(colors[0], (EditText) findViewById(R.id.input_1));
        setColors(colors[1], (EditText) findViewById(R.id.input_2));
        setColors(colors[2], (EditText) findViewById(R.id.input_3));
        setColors(colors[3], (EditText) findViewById(R.id.input_4));
        setColors(colors[4], (EditText) findViewById(R.id.input_5));
        setColors(colors[5], (EditText) findViewById(R.id.input_6));
    }
}
