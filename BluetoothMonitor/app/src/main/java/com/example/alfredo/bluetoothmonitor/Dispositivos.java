package com.example.alfredo.bluetoothmonitor;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Set;

public class Dispositivos extends AppCompatActivity {

    private static final String TAG = "DispositivosBT";
    public static String EXTRA_DEVICES_ADDRESS = "device_address";
    ListView rvDispositivos;

    private BluetoothAdapter btAdapter;
    private ArrayAdapter<String> pairedDevicesArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispositivos);
    }

    @Override
    protected void onResume() {
        super.onResume();

        verificarEstadoBT();

        pairedDevicesArrayAdapter = new ArrayAdapter<String>(this, R.layout.nombre_dispositivos);
        rvDispositivos = findViewById(R.id.rvListaDispositivos);
        rvDispositivos.setAdapter(pairedDevicesArrayAdapter);
        rvDispositivos.setOnItemClickListener(deviceClickListener);

        btAdapter = BluetoothAdapter.getDefaultAdapter();
        Set<BluetoothDevice> pairedDevices = btAdapter.getBondedDevices();

        if(pairedDevices.size() > 0){
            for(BluetoothDevice device: pairedDevices){
                pairedDevicesArrayAdapter.add(device.getName() + "\n" + device.getAddress());
            }
        }
    }

    private AdapterView.OnItemClickListener deviceClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String info = ((TextView) view).getText().toString();
            String address = info.substring(info.length() - 17);

            Intent i = new Intent(Dispositivos.this, MainActivity.class);
            i.putExtra(EXTRA_DEVICES_ADDRESS, address);
            startActivity(i);
        }
    };

    private void verificarEstadoBT(){
        btAdapter = BluetoothAdapter.getDefaultAdapter();
        if(btAdapter == null){
            Toast.makeText(getBaseContext(), "El dispositivo tiene problemas con la conexión Bluetooth", Toast.LENGTH_LONG).show();
        }
        else{
            if(btAdapter.isEnabled()){
                Log.d(TAG, "Bluetooth activado");
            }else{
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivity(enableBtIntent);
            }
        }
    }
}
