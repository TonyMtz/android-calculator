package com.gamesrum.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.Callable;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class LauncherActivity extends Activity implements View.OnClickListener {

    @InjectView(R.id.display)
    TextView display;
    @InjectView(R.id.btnPlus)
    TextView btnPlus;
    @InjectView(R.id.btnMinus)
    TextView btnMinus;
    @InjectView(R.id.btnDot)
    TextView btnDot;
    @InjectView(R.id.btnEquals)
    TextView btnEquals;

    @InjectView(R.id.btn0)
    TextView btn0;
    @InjectView(R.id.btn1)
    TextView btn1;
    @InjectView(R.id.btn2)
    TextView btn2;
    @InjectView(R.id.btn3)
    TextView btn3;
    @InjectView(R.id.btn4)
    TextView btn4;
    @InjectView(R.id.btn5)
    TextView btn5;
    @InjectView(R.id.btn6)
    TextView btn6;
    @InjectView(R.id.btn7)
    TextView btn7;
    @InjectView(R.id.btn8)
    TextView btn8;
    @InjectView(R.id.btn9)
    TextView btn9;

    private StringBuffer outputText;
    private Engine engine;
    private boolean shouldErase = false;

    private void updateWith(String input) {
        if (shouldErase) {
            outputText.setLength(0);
            shouldErase = false;
        }

        outputText.append(input);
        display.setText(outputText.toString());
    }

    private double invokeOperator(Method method) throws Exception {
        double input;
        double total;

        try {
            input = Double.parseDouble(outputText.toString());
        } catch (Exception e) {
            input = 0;
        }

        total = (double) method.invoke(engine, input);

        outputText.setLength(0);
        outputText.append(total);
        display.setText(outputText.toString());

        shouldErase = true;

        return total;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        engine = new Engine();
        outputText = new StringBuffer();

        ButterKnife.inject(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btnPlus.setOnClickListener(this);
        btnMinus.setOnClickListener(this);
        btnEquals.setOnClickListener(this);
        btnDot.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        double input;
        double total;

        switch (v.getId()) {

            case R.id.btn0:
                updateWith("0");
                break;

            case R.id.btn1:
                updateWith("1");
                break;

            case R.id.btn2:
                updateWith("2");
                break;

            case R.id.btn3:
                updateWith("3");
                break;

            case R.id.btn4:
                updateWith("4");
                break;

            case R.id.btn5:
                updateWith("5");
                break;

            case R.id.btn6:
                updateWith("6");
                break;

            case R.id.btn7:
                updateWith("7");
                break;

            case R.id.btn8:
                updateWith("8");
                break;

            case R.id.btn9:
                updateWith("9");
                break;

            case R.id.btnPlus:
                Method sum = null;
                try {
                    sum = Engine.class.getMethod("add", double.class);
                    invokeOperator(sum);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            case R.id.btnMinus:
                Method sub = null;
                try {
                    sub = Engine.class.getMethod("sub", double.class);
                    invokeOperator(sub);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            case R.id.btnEquals:
                Method equals = null;
                try {
                    equals = Engine.class.getMethod("equals", double.class);
                    invokeOperator(equals);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}
