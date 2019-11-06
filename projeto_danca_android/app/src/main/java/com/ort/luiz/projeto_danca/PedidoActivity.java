package com.ort.luiz.projeto_danca;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class PedidoActivity extends AppCompatActivity {
    FirebaseDatabase database;

    TextView txtNome, txtEmail, txtPedido;
    Button btnVoltarEventos, btnEnviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido);

        database = FirebaseDatabase.getInstance();

        btnVoltarEventos = findViewById(R.id.btnVoltarEventosId);
        btnEnviar = findViewById(R.id.btnEnviarId);

        btnVoltarEventos.setOnClickListener((V)->{
            btnVoltarEventos.setBackgroundResource(R.color.White);
            startActivity(new Intent(this, MainActivity.class));
        });

        btnEnviar.setOnClickListener((v -> {
            btnEnviar.setBackgroundResource(R.color.White);

            txtNome = findViewById(R.id.txtNomeId);
            txtEmail = findViewById(R.id.txtEmailId);
            txtPedido = findViewById(R.id.txtPedidoId);

            SimpleDateFormat dateFormat_hora = new SimpleDateFormat("HH:mm:ss");

            String nome = txtNome.getText().toString();
            String email = txtEmail.getText().toString();
            String pedido = txtPedido.getText().toString();

            Calendar cal = Calendar.getInstance();
            Date data_atual = cal.getTime();


            String hora_atual = dateFormat_hora.format(data_atual);

            alert(hora_atual);
        }));
    }

    private void alert(String msg){
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }
}
