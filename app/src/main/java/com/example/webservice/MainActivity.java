package com.example.webservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.TextureView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

import WebService.Asynchtask;
import WebService.WebService;

public class MainActivity extends AppCompatActivity
implements  Asynchtask {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void btlogin (View view)  {
        EditText Nombre = findViewById(R.id.txtnombre);
        EditText Clave = findViewById(R.id.txtclave);
        String nombre;
        String clave;
        nombre =Nombre.getText().toString();
        clave = Clave.getText().toString();
        //codigo para conecta a intenet
        Map<String, String> datos = new HashMap<String, String>();

        WebService ws= new WebService( "https://revistas.uteq.edu.ec/ws/login.php?usr"
                +nombre+ "&pass" +clave,datos,
                MainActivity.this, MainActivity.this);
        ws.execute("GET");

    }

    @Override
    public void processFinish(String result) throws JSONException {
        TextView txtmensaje = (TextView) findViewById( R.id.txtmensaje) ;
        if(result.equals("Login Correcto")) {
            Intent intent = new Intent( MainActivity.this,
                    LISTADEBANCO.class   );
            startActivity(intent);
        } else
        txtmensaje.setText("Respuesta del servidor" + result);

    }
}