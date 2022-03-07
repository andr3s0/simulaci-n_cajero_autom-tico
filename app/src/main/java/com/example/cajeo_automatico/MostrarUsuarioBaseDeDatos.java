package com.example.cajeo_automatico;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.ContentInfo;
import android.view.View;

public class MostrarUsuarioBaseDeDatos {

    private String saldo;
    private String cedula;

    public MostrarUsuarioBaseDeDatos() {

    };

    public MostrarUsuarioBaseDeDatos(String cedula) {
        this.cedula = cedula;
    };

    public String mostrarLosUsuario(Context context) {
        AdminitradorBD adminBD = new AdminitradorBD(context, "BaseDeDatos", null, 1);
        SQLiteDatabase baseDeDatos = adminBD.getWritableDatabase();
        Cursor fila = baseDeDatos.rawQuery("select saldo, cedula from usuario where cedula="+cedula, null);

        if (fila.moveToFirst()) {
            saldo = ( "Cedula: "+fila.getString(1)+" - "+ "Saldo = $" + fila.getString(0));
            baseDeDatos.close();
        };
        baseDeDatos.close();

        return saldo;
    }
}
