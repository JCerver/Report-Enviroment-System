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
import java.util.Calendar;
import java.util.UUID;


public class MainActivity extends AppCompatActivity {

    private TextView lblTemperatura, lblHumedad, lblLuminosidad;    //Etiquetas de la interfaz
    private SeekBar sbrBrillo, sbrContraste;                        //Barras de control de brillo y contraste
    private TextInputEditText txtMensajes;                          //Entrada de texto para enviar mensaje
    private Button btnEnviarMensaje, btnActualizar;                 //Botones para enviar mensaje y actualizar datos climatolóticos

    private Handler bluetoothIn;                                    //Es el handler que permite estra al pendiente de la entrada de los datso desde Bluetooth
    final int handlerState = 0;                                     //Esatdo del handler
    private BluetoothAdapter btAdapter = null;                      //Adaptador de bluetooth que nos permite manipular el funcionamiento de bluetooth
    private BluetoothSocket btSocket = null;                        //Socket para poder establecer conexión con dispositivos bluetooth
    private StringBuilder dataStringIN = new StringBuilder();       //Guarda los mensajes que se reciben por bluetooth
    private ConnectedThread myConexionBT;                           //Maneja el hilo de ejecución que está a la espera de recibir o enviar datos por Bluetooth

    //Identificador único de servicio
    private static final UUID BTMODULEUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    //String para la  MAC
    private static String address = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Determinar los componentes desde la interfaz para poderlas utilizar
        lblTemperatura = findViewById(R.id.lblTemperaturaReal);
        lblHumedad = findViewById(R.id.lblHumedadReal);
        lblLuminosidad = findViewById(R.id.lblLuminosidadReal);
        sbrBrillo = findViewById(R.id.sbrBrillo);
        sbrContraste = findViewById(R.id.sbrContraste);
        txtMensajes = findViewById(R.id.txtMensajes);
        btnEnviarMensaje = findViewById(R.id.btnEnviarMensaje);
        btnActualizar = findViewById(R.id.btnActualizar);

        //A la espera de recibir mensajes por bluetooth
        bluetoothIn = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if(msg.what == handlerState){
                    String readMessage = (String) msg.obj;

                    dataStringIN.append(readMessage);
                    //Para encontrar el fin del mensaje se tienen que enviar con el signo gato al final
                    int endOfLineIndex = dataStringIN.indexOf("#");

                    //Mientras no se llegue al final de línea se siguen agregando os bytes a la cadena ya que se envían por partes por el serial.
                    // De esta forma se obtiene el mensaje completo sin cortes
                    if(endOfLineIndex > 0){
                        String dataInPrint = dataStringIN.substring(0, endOfLineIndex);
                        dataStringIN.delete(0, dataStringIN.length());

                        //La cadena recibida contiene 3 valores separados por coma por lo que se dividen y se guardan por separado en un arreglo para luego mosrarlos en los TextView
                        String[] valoresClima = dataInPrint.split(",");
                        lblTemperatura.setText(valoresClima[0] + " C°");
                        lblHumedad.setText(valoresClima[1] + " %");
                        lblLuminosidad.setText(valoresClima[2] + " lx");
                    }


                }

            }
        };



        //Se procede a revisar si la conexión Bluetooth es posible o está activada
        btAdapter = BluetoothAdapter.getDefaultAdapter();
        verificarEstadoBT();


        //Disparador de eventos cuando se cambia el valor del SeekBar de brillo
        sbrBrillo.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //Se necesita enviar un 2 al principio para que Arduino identifique que debe cambiar el brillo
                myConexionBT.write("2" + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //Disparador de eventos cuando se cambia el valor del SeekBar de contraste
        sbrContraste.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progress = 170 - progress;
                //Se necesita enviar un 2 al principio para que Arduino identifique que debe cambiar el contraste
                myConexionBT.write("3" + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        //LA presionar el botón de enviar, se envía tanto mensaje como fecha actual al Arduino
        btnEnviarMensaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Es necesario hacer una pausa antes de enviar la fecha para que Arduino lo reconozca como datos diferentes al mensaje y a la fecha
                myConexionBT.write("1" + txtMensajes.getText().toString());
                try {
                    Thread.sleep(1800);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Calendar fecha = Calendar.getInstance();
                myConexionBT.write("5" + fecha.getTime().getDate() + "/" + fecha.getTime().getMonth() + "/" + (1900 + fecha.getTime().getYear()) + "  " + fecha.getTime().getHours() + ":" + fecha.getTime().getMinutes());
            }
        });

        //Se manda un 4 al Arduino para que nos regrese luego los datos climatológicos
        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myConexionBT.write("4");
            }
        });

    }



    private BluetoothSocket createBluetoothSocket(BluetoothDevice device) throws IOException{
        return device.createInsecureRfcommSocketToServiceRecord(BTMODULEUUID);
    }

    @Override
    protected void onResume() {
        super.onResume();


        //Se detectan los dispositivos Bluetooth vinculados al móvil
        Intent intent = getIntent();
        address = intent.getStringExtra(Dispositivos.EXTRA_DEVICES_ADDRESS);

        BluetoothDevice device = btAdapter.getRemoteDevice(address);

        //Se intenta crear socket de conexion
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
        //Streams que nos permiten dar los datos a mandar o rescatar los datos enviados desde Arduino
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
