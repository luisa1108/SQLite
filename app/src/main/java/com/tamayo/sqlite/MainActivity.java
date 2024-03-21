package com.tamayo.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText nombre, cedula, telefono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cedula = findViewById(R.id.cedula);
        nombre = findViewById(R.id.nombre);
        telefono = findViewById(R.id.telefono);
    }

    public void registrar(View vista){
        AdminDB admin = new AdminDB(this,"estudiantes",null,1);
        SQLiteDatabase baseDatos = admin.getReadableDatabase();
        String documento = cedula.getText().toString();
        String name = nombre.getText().toString();
        String phone = telefono.getText().toString();

        if(!documento.isEmpty()){
            Cursor fila = baseDatos.rawQuery("select cedula from usuario where cedula =" + documento,null);

            if(fila.moveToFirst()){
                String idPersona = fila.getString(0);
                if(!idPersona.isEmpty()){
                    Toast.makeText(this,"USUARIO EXISTE", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(!name.isEmpty() && !phone.isEmpty()){
                        ContentValues registro = new ContentValues();
                        registro.put("cedula", documento);
                        registro.put("nombre", name);
                    }
                }
            }
        }
    }
}