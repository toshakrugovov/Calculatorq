package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TwoLineListItem;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {


    private Button One, Two, Three, Four, Five, Six, Seven, Eight, Nine, Zero;


    private Button Plus, Minus, Delenie, Umnozh, Result, clear, koren, kvadrat, percent;

    private TextView Formula, EndResult;
    private double valueFirst = Double.NaN;

    private double valueSecond = Double.NaN;

    private char Action;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        setupView();

        View.OnClickListener numberClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button button = (Button) view;
                Formula.setText(Formula.getText().toString() + button.getText().toString());
            }


        };
        One.setOnClickListener(numberClickListener);
        Two.setOnClickListener(numberClickListener);
        Three.setOnClickListener(numberClickListener);
        Four.setOnClickListener(numberClickListener);
        Five.setOnClickListener(numberClickListener);
        Six.setOnClickListener(numberClickListener);
        Seven.setOnClickListener(numberClickListener);
        Eight.setOnClickListener(numberClickListener);
        Nine.setOnClickListener(numberClickListener);
        Zero.setOnClickListener(numberClickListener);

        View.OnClickListener actionOnClickListener = new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Button button = (Button) view;
                calculate();
                Action = button.getText().charAt(0);
                Formula.setText(String.valueOf(valueFirst) + Action);
                EndResult.setText(null);

            }

        };


        Plus.setOnClickListener(actionOnClickListener);
        Minus.setOnClickListener(actionOnClickListener);
        Umnozh.setOnClickListener(actionOnClickListener);
        Delenie.setOnClickListener(actionOnClickListener);
        percent.setOnClickListener(actionOnClickListener);
        clear.setOnClickListener(actionOnClickListener);
        koren.setOnClickListener(actionOnClickListener);
        kvadrat.setOnClickListener(actionOnClickListener);


        Result.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                calculate();
                Action = '=';
                EndResult.setText(String.valueOf(valueFirst));
                Formula.setText(null);
            }
        });

        koren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Action = '√';
                calculate();
                Formula.setText("√");
                EndResult.setText(null);
                valueFirst = Double.NaN;
            }
        });
        percent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Action = '%';
                calculate();
                Formula.setText("%");
                EndResult.setText(null);
                valueFirst = Double.NaN;
            }
        });


        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EndResult.setText("");  // Clears the result TextView
                Formula.setText("");    // Clears the formula TextView
                valueFirst = Double.NaN; // Resets the first value
                valueSecond = Double.NaN; // Resets the second value
            }
        });
    }

    private void setupView() {
        One = (Button) findViewById(R.id.One);
        Two = (Button) findViewById(R.id.Two);
        Three = (Button) findViewById(R.id.Three);
        Four = (Button) findViewById(R.id.Four);
        Five = (Button) findViewById(R.id.Five);
        Six = (Button) findViewById(R.id.Six);
        Seven = (Button) findViewById(R.id.Seven);
        Eight = (Button) findViewById(R.id.Eight);
        Nine = (Button) findViewById(R.id.Nine);
        Zero = (Button) findViewById(R.id.Zero);
        Minus = (Button) findViewById(R.id.Minus);
        Plus = (Button) findViewById(R.id.Plus);
        Delenie = (Button) findViewById(R.id.Delenie);
        Umnozh = (Button) findViewById(R.id.Umnozh);
        Result = (Button) findViewById(R.id.Result);
        Formula = (TextView) findViewById(R.id.Formula);
        EndResult = (TextView) findViewById(R.id.EndResult);
        kvadrat = (Button) findViewById(R.id.kvadrat);
        clear = (Button) findViewById(R.id.clear);
        koren = (Button) findViewById(R.id.koren);
        percent = (Button) findViewById(R.id.percent);


    }

    private void calculate() {
        if (!Double.isNaN(valueFirst) || (Action == '√') || (Action == '%')) {
            try {
                String textFormula = Formula.getText().toString();
                int indexAction = textFormula.indexOf(Action);
                if (indexAction != -1) {
                    String numberValue = textFormula.substring(indexAction + 1);
                    valueSecond = Double.parseDouble(numberValue);
                    switch (Action) {
                        case '+':
                            valueFirst += valueSecond;
                            break;
                        case '-':
                            valueFirst -= valueSecond;
                            break;
                        case '*':
                            valueFirst *= valueSecond;
                            break;
                        case '/':
                            if (valueSecond == 0) {
                                valueFirst = 0.0;
                            } else {
                                valueFirst /= valueSecond;
                            }
                            break;
                        case '√':
                            valueFirst = Math.sqrt(valueSecond);
                            break;
                        case '=':
                            valueFirst = valueSecond;
                            break;
                        case '%':
                            valueFirst = valueSecond / 100;
                            break;
                        case '^':
                            valueFirst = Math.pow(valueFirst, valueSecond);
                            break;
                    }

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                valueFirst = Double.parseDouble(Formula.getText().toString());
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        EndResult.setText(String.valueOf(valueFirst));
        Formula.setText("");
    }
}



