package com.example.scanner.impl;


import android.app.Instrumentation;
import android.os.Handler;
import android.os.Looper;
import android.text.SpannableStringBuilder;
import android.view.KeyEvent;
import android.widget.TextView;

import com.example.scanner.MainActivity;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Date;

import me.f1reking.serialportlib.listener.ISerialPortDataListener;

public class ISerialPortDataListenerImple implements ISerialPortDataListener {
    @Override
    public void onDataReceived(byte[] bytes) {
        System.out.println(Arrays.toString(bytes));
        String value;
        try {
            value = new String(bytes, "UTF-8");
            value = "" + new Date() + " " + value;
            appendText(value);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return;
        }
//        Instrumentation inst = new Instrumentation();
//        for (int i = 0; i < text.length(); i++) {
//            char ch = text.charAt(i);
//            Integer anInt = null;
//            if ('\n' == ch) {
////            if ('\r' == ch || '\n' == ch) {
//                anInt = 66;
//            }
//            else {
//                try {
//                    anInt = KeyEvent.class.getField("KEYCODE_" + ch).getInt(null);
////                    System.out.println("Key event " + ch  + " is " + anInt);
//                } catch (IllegalAccessException e) {
//                    e.printStackTrace();
//                } catch (NoSuchFieldException e) {
//                    e.printStackTrace();
//                }
//            }
//            if (anInt != null) {
//                inst.sendKeyDownUpSync(anInt);
//            }
//        }
    }

    @Override
    public void onDataSend(byte[] bytes) {
        System.out.println(Arrays.toString(bytes));
    }


    private void appendText(final String value){
        boolean post = new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                String oldValue = ((SpannableStringBuilder) MainActivity.showResultView.getText()).toString();
                String newValue = oldValue + value;

                MainActivity.showResultView.setText(newValue);
                //do stuff like remove view etc
//                adapter.remove(selecteditem);
            }
        });
    }
}
