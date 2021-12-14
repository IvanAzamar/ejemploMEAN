package com.example.consumoapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
EditText nombre, descripcion;
Button guardar;
TextView resultado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        relacionVista();
    }

    public void relacionVista(){
        nombre=(EditText) findViewById(R.id.nombre);
        descripcion=(EditText) findViewById(R.id.descripcion);
        resultado=(TextView)findViewById(R.id.resultadoAPI);
        guardar=(Button) findViewById(R.id.guardar);
    }

    public void ingresarArtista(View v){
        RequestQueue servicio= Volley.newRequestQueue(this);
        String url="http://192.168.0.2:3977/api/insertarArtista";
        StringRequest respuesta= new StringRequest(Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
resultado.setText(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
 Toast.makeText(getApplicationContext(),"Error en la Red " + error,Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> valores= new HashMap<String,String>();
                valores.put("nombre",nombre.getText().toString());
                valores.put("descripion",descripcion.getText().toString());
                return valores;
            }
        };
servicio.add(respuesta);
    }
}