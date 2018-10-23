package com.example.alfredo.bluetoothmonitor;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;


public class MainActivity extends AppCompatActivity {

    private TextView lblTemperatura, lblHumedad, lblLuminosidad;
    private SeekBar sbrBrillo;
    private TextInputEditText txtMensajes;
    private Button btnEnviarMensaje;

    private Handler bluetoothIn;
    final int handlerState = 0;
    private BluetoothAdapter btAdapter = null;
    private BluetoothSocket btSocket = null;
    private StringBuilder dataStringIN = new StringBuilder();
    private ConnectedThread myConexionBT;

    //Identificador único de servicio
//    private static final UUID BTMODULEUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B4FB");
    private static final UUID BTMODULEUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    //String para la  MAC
    private static String address = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
  //      Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);


        lblTemperatura = findViewById(R.id.lblTemperaturaReal);
        lblHumedad = findViewById(R.id.lblHumedadReal);
        lblLuminosidad = findViewById(R.id.lblLuminosidadReal);
        sbrBrillo = findViewById(R.id.sbrBrillo);
        txtMensajes = findViewById(R.id.txtMensajes);
        btnEnviarMensaje = findViewById(R.id.btnEnviarMensaje);

        bluetoothIn = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if(msg.what == handlerState){
                    String readMessage = (String) msg.obj;

                    dataStringIN.append(readMessage);

                    int endOfLineIndex = dataStringIN.indexOf("#");

                    if(endOfLineIndex > 0){
                        String dataInPrint = dataStringIN.substring(0, endOfLineIndex);
                        dataStringIN.delete(0, dataStringIN.length());
                        lblTemperatura.setText(dataInPrint.substring(0, 4));
                        lblHumedad.setText(dataInPrint.substring(4, 8));
                        lblLuminosidad.setText(dataInPrint.substring(8, 12));
                    }


                }

            }
        };



        btAdapter = BluetoothAdapter.getDefaultAdapter();
        verificarEstadoBT();


        sbrBrillo.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                myConexionBT.write(progress + "");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        btnEnviarMensaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myConexionBT.write(txtMensajes.getText().toString());
            }
        });

    }



    private BluetoothSocket createBluetoothSocket(BluetoothDevice device) throws IOException{
        return device.createInsecureRfcommSocketToServiceRecord(BTMODULEUUID);
    }

    @Override
    protected void onResume() {
        super.onResume();


        Intent intent = getIntent();
        address = intent.getStringExtra(Dispositivos.EXTRA_DEVICES_ADDRESS);
        Toast.makeText(getBaseContext(), address, Toast.LENGTH_LONG).show();

        BluetoothDevice device = btAdapter.getRemoteDevice(address);

        try{
            btSocket = createBluetoothSocket(device);

        }catch(IOException e){
            Toast.makeText(getBaseContext(), "La creación del Socket ha fallado", Toast.LENGTH_LONG);

        }
        //Se establece la conexión Bluetooth

        try{
            btSocket.connect();

        }catch(IOException e){
            e.printStackTrace();
            try {
                btSocket.close();
            }catch(IOException e2){

            }
        }

        myConexionBT = new ConnectedThread(btSocket);
        myConexionBT.start();




    }

    @Override
    protected void onPause() {
        super.onPause();

        try {
            //Se cierra el socket antes de cerrar la aplicación
            btSocket.close();
        }catch(IOException e){}
    }

    private void verificarEstadoBT(){
        if(btAdapter == null){
            Toast.makeText(getBaseContext(), "Hay un problema con la conexión a Bluetooth", Toast.LENGTH_LONG).show();
        }else{
            if(btAdapter.isEnabled()){

            }else{
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivity(enableBtIntent);
            }
        }
    }

    private class ConnectedThread extends Thread{
        private final InputStream inStream;
        private final OutputStream outStream;

        public ConnectedThread (BluetoothSocket socket){
            InputStream tmpIn = null;
            OutputStream tmpOut = null;
            try {
                tmpIn = socket.getInputStream();
                tmpOut = socket.getOutputStream();
            }catch(IOException e){

            }

            inStream= tmpIn;
            outStream = tmpOut;


        }

        public void run(){
            byte[] buffer = new byte[256];
            int bytes;
            //Se mantiene en modo escucha para el ingreso de datos
            while(true){
                try {
                    bytes = inStream.read(buffer);
                    String readMessage = new String(buffer, 0, bytes);
                    bluetoothIn.obtainMessage(handlerState, bytes, -1, readMessage).sendToTarget();


                }catch(IOException e){
                    break;
                }


            }



        }

        public void write(String input){
            try {
                outStream.write(input.getBytes());
            }catch(IOException e){
                Toast.makeText(getBaseContext(), "La conexión ha fallado", Toast.LENGTH_LONG).show();
                finish();
            }
        }
    }
}
