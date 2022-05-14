package com.example.scanner.controller;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.scanner.impl.ISerialPortDataListenerImple;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import me.f1reking.serialportlib.SerialPortHelper;
import me.f1reking.serialportlib.entity.Device;

public class ScannerController {
    private SerialPortHelper opendSerialPortHelper;
    public List<String> listDevice() {
        SerialPortHelper help = new SerialPortHelper();
        List<Device> allDevices = help.getAllDevices();
        ArrayList<String> nameList = new ArrayList<>();
        for (Device d: allDevices ) {
            nameList.add(d.getFile().getAbsolutePath());
        }
        return nameList;
    }

    public boolean open(String portName, Integer baudRateValue) {
        if (opendSerialPortHelper != null) {
            opendSerialPortHelper.close();
        }
        SerialPortHelper serialPortHelper = new SerialPortHelper.Builder(portName, baudRateValue).build();
        boolean open = serialPortHelper.open();
        if (!open) {
            return false;
        }
        serialPortHelper.setISerialPortDataListener(new ISerialPortDataListenerImple());
        opendSerialPortHelper = serialPortHelper;
        return true;
    }
}
