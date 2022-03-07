package com.example.cajeo_automatico;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class ConsignacionDeDinero {

    private String saldoUsuario;
    private String cedulaUsuario;

    public ConsignacionDeDinero(String cedula, String saldo){
        this.cedulaUsuario = cedula;
        this.saldoUsuario = saldo;
    };

    public void ingresarDineroABaseDeDatos(Context context){
        AdminitradorBD adminitradorBD = new AdminitradorBD(context, "BaseDeDatos", null,1);
        SQLiteDatabase BaseDeDatos = adminitradorBD.getWritableDatabase();

        if (!cedulaUsuario.isEmpty() && !saldoUsuario.isEmpty()) {

                if (Integer.parseInt(saldoUsuario) >= 10000){
                    ContentValues registrar = new ContentValues();
                    registrar.put("cedula",cedulaUsuario);
                    registrar.put("saldo", saldoUsuario);
                    BaseDeDatos.insert("usuario", null ,registrar);
                    BaseDeDatos.close();
                    Toast.makeText(context, "Consignacion exitosa", Toast.LENGTH_LONG).show();
                } else{
                    Toast.makeText(context, "valide que el saldo a consignar sea mayor a $10.000",Toast.LENGTH_LONG).show();
                }

        } else {
            Toast.makeText(context, "Ingrese todos los campos", Toast.LENGTH_LONG).show();
        }
    };

}
