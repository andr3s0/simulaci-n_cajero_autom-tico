package com.example.cajeo_automatico;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.EditText;
import android.widget.Toast;

public class ConsultarUsuario {
    private String cedulaUsuario;
    private String saldoTotal;

    public ConsultarUsuario(String cedulaUsuario) {
        this.cedulaUsuario = cedulaUsuario;
    };

    public String mostrarUsuario(Context context){
        AdminitradorBD adminitradorBD = new AdminitradorBD(context, "BaseDeDatos", null, 1);
        SQLiteDatabase baseDeDatos = adminitradorBD.getWritableDatabase();

        if (!cedulaUsuario.isEmpty()){
            Cursor fila = baseDeDatos.rawQuery("select saldo, cedula from usuario where cedula="+cedulaUsuario, null);

            if (fila.moveToFirst()){
                saldoTotal = "cedula: "+fila.getString(1)+" - "+ "Saldo = $" + fila.getString(0);
                baseDeDatos.close();
            } else {
                Toast.makeText(context, "Usuario no existe", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(context, "Ingresa el campo de la cedula para consultar el usuario", Toast.LENGTH_LONG).show();
        };
        baseDeDatos.close();

        return saldoTotal;
    }
}
