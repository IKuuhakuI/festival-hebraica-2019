package com.ort.luiz.projeto_danca;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class PedidoActivity extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference idRef;

    TextView txtNome, txtEmail, txtPedido;
    Button btnVoltarEventos, btnEnviar;

    String id;

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
            idRef = database.getReference("Pedidos");
            idRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    id = String.valueOf(Integer.parseInt(dataSnapshot.child("QuantidadePedidos").getValue().toString()) + 1);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    alert(databaseError.getMessage());
                }
            });

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



            if(id != null) {
                idRef.child(id).child("Nome_Pessoa").setValue(nome);
                idRef.child(id).child("Email_Pessoa").setValue(email);
                idRef.child(id).child("Horario_Pedido").setValue(hora_atual);
                idRef.child(id).child("Pedido").setValue(pedido);

                idRef.child("QuantidadePedidos").setValue(id);
            } else {
                alert("Clique novamente para confirmar");
            }
        }));

    }

    private void alert(String msg){
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }
}
