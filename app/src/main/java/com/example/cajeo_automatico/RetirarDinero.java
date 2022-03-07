package com.example.cajeo_automatico;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class RetirarDinero {

    private String cedulaUsuario;
    private String dineroRetirado;

    public RetirarDinero(String cedula, String dineroRetirado) {
        this.cedulaUsuario = cedula;
        this.dineroRetirado = dineroRetirado;
    }

    public void retirarDinero(Context context){
        AdminitradorBD adminitradorBD = new AdminitradorBD(context, "BaseDeDatos", null, 1);
        SQLiteDatabase baseDeDatos = adminitradorBD.getWritableDatabase();

        int saldoActual = 0;

        if (!cedulaUsuario.isEmpty() && !dineroRetirado.isEmpty()){
            Cursor fila = baseDeDatos.rawQuery("select saldo from usuario where cedula="+cedulaUsuario, null);

            if (fila.moveToFirst()){
                if (Integer.parseInt(dineroRetirado) <= Integer.parseInt(fila.getString(0))){
                    saldoActual = Integer.parseInt(fila.getString(0)) - Integer.parseInt(dineroRetirado);
                    baseDeDatos.execSQL("UPDATE usuario SET saldo="+saldoActual+" where cedula="+cedulaUsuario);
                    baseDeDatos.close();
                }else {
                    Toast.makeText(context, "El saldo supera al saldo que tienes", Toast.LENGTH_LONG).show();
                    return;
                }
            } else{
                Toast.makeText(context, "El usuario no existe", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(context, "valide que el campo de la cedula y campo de retirar esten llenos", Toast.LENGTH_LONG).show();
            return;
        };
        baseDeDatos.close();
    };
}
