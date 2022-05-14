package com.example.scanner;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
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

    private Spinner dropdown;

    private EditText baudRateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dropdown = findViewById(R.id.spinner);
        baudRateTextView = (EditText)findViewById(R.id.baudRate);
//        baudRateTextView.setTextColor(getResources().getColor(R.color.black));
        baudRateTextView.setText("9600");
        showResultView = (EditText)findViewById(R.id.showResult);
        showSpine();
    }


    public void showSpine() {
        List<String> items = scannerController.listDevice();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
    }

    public void clickStartButton(View view) {
        Object selectedItem = dropdown.getSelectedItem();
        if (null == selectedItem) {
            alert("please select port name");
        } else {
            String portName = selectedItem.toString();
            String baudRateValueStr = ((SpannableStringBuilder) baudRateTextView.getText()).toString();
            Integer baudRateValue = Integer.valueOf(baudRateValueStr);
            boolean open = scannerController.open(portName, baudRateValue);
            if (!open) {
                alert("open error");
            }
        }
    }

    private void alert(String msg) {
        new AlertDialog.Builder(MainActivity.this)
                .setTitle("error")
                .setMessage(msg)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();

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