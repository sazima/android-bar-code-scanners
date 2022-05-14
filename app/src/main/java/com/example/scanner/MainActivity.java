package com.example.scanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.text.SpannableStringBuilder;
import android.view.MotionEvent;
import android.view.View;
import android.view.autofill.AutofillValue;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.scanner.controller.ScannerController;
import com.example.scanner.impl.ISerialPortDataListenerImple;

import java.util.List;

import me.f1reking.serialportlib.SerialPortHelper;

public class MainActivity extends AppCompatActivity {
    private ScannerController scannerController = new ScannerController();

    public static EditText showResultView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showSpine();
    }


    public void showSpine() {
        Spinner dropdown = findViewById(R.id.spinner);
        EditText baudRateTextView = (EditText)findViewById(R.id.baudRate);
        baudRateTextView.setTextColor(getResources().getColor(R.color.black));
        baudRateTextView.setText("9600");
        showResultView = (EditText)findViewById(R.id.showResult);
        List<String> items = scannerController.listDevice();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
    }

    public void clickStartButton(View view) {
        Spinner dropdown = findViewById(R.id.spinner);
        String portName = dropdown.getSelectedItem().toString();
        EditText baudRateTextView = (EditText)findViewById(R.id.baudRate);
        String baudRateValueStr = ((SpannableStringBuilder) baudRateTextView.getText()).toString();
        Integer baudRateValue = Integer.valueOf(baudRateValueStr);
        scannerController.open(portName, baudRateValue);
    }


    public static void disableSoftInputFromAppearing(EditText editText_input_field) {

        View.OnTouchListener otl = new View.OnTouchListener() {
            public boolean onTouch (View v, MotionEvent event) {
                return true; // the listener has consumed the event
            }
        };
        editText_input_field.setOnTouchListener(otl);
    }
}