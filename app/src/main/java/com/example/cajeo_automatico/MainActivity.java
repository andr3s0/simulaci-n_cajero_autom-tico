package com.example.cajeo_automatico;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText ceduladelUsuario, dineroAConsignar, retirarDinero;
    TextView saldoTotal;
    String cedula, dinero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ceduladelUsuario = (EditText) findViewById(R.id.editTextCeduladelUsuario);
        dineroAConsignar = (EditText) findViewById(R.id.editTextConsignarDinero);
        retirarDinero = (EditText) findViewById(R.id.editTextTextRetirarDinero);
        saldoTotal = (TextView) findViewById(R.id.textViewTotalDinero);

        MostrarUsuarioBaseDeDatos mostrarUsuario = new MostrarUsuarioBaseDeDatos();
        saldoTotal.setText(mostrarUsuario.mostrarLosUsuario(this));
    };

    public void consignarDinero(View view){
        cedula = ceduladelUsuario.getText().toString();
        dinero = dineroAConsignar.getText().toString();

        ConsignacionDeDinero consignacionDeDinero = new ConsignacionDeDinero(cedula, dinero);
        consignacionDeDinero.ingresarDineroABaseDeDatos(this);
        limpiarLosCampos();
    };

    public void consultarUsuario(View view){
        cedula = ceduladelUsuario.getText().toString();
        ConsultarUsuario consultarUsuario = new ConsultarUsuario(cedula);
        saldoTotal.setText(consultarUsuario.mostrarUsuario(this));
    };

    public void retirarDinero(View view){
        cedula = ceduladelUsuario.getText().toString();
        dinero = retirarDinero.getText().toString();
        RetirarDinero retirarDinero = new RetirarDinero(cedula, dinero);
        retirarDinero.retirarDinero(this);

        MostrarUsuarioBaseDeDatos mostrarUsuario = new MostrarUsuarioBaseDeDatos(cedula);
        saldoTotal.setText(mostrarUsuario.mostrarLosUsuario(this));
    };

    public void limpiarLosCampos(){
        retirarDinero.setText("");
        dineroAConsignar.setText("");
    }

}