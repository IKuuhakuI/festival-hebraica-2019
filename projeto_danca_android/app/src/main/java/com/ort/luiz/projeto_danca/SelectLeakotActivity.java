package com.ort.luiz.projeto_danca;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SelectLeakotActivity extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference acontecendoRef, lehakotRef;

    private ListView listaItens;
    private ArrayAdapter<String> adaptador;

    private ArrayList<String> nomes = new ArrayList<>();

    Button btnVoltarLeakot;

    TextView scrollingText;

    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_leakot);
        database = FirebaseDatabase.getInstance();
        listaItens = findViewById(R.id.listViewLeakotId);

        lehakotRef = database.getReference("Lehakot");

        lehakotRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                    for (int i = 1; i < 79; i++) {
                        if (i == 1 || i == 8 || i == 20 || i == 25 || i == 26 || i == 30 || i == 32 || i == 33 || i == 36 || i == 48 || i == 49 || i == 53 || i == 55){
                            continue;
                        }
                            String nomeAtual;

                            String valor = Integer.toString(i);

                            nomeAtual = dataSnapshot.child(valor).child("name").getValue().toString();

                            nomes.add(nomeAtual);
                    }
                    if(count == 0){
                        adaptador = new ArrayAdapter<>(getApplicationContext(),
                        R.layout.row_select_lehakot,
                        nomes);
                        listaItens.setAdapter(adaptador);
                        count++;
                    }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        listaItens.setOnItemClickListener((parent, view, position, id) -> {
                Intent intent = new Intent(SelectLeakotActivity.this, LehakaActivity.class);

                // VERIFICADO
                if(position < 6){
                    intent.putExtra("id", Integer.toString(position+2));
                }

                // VERIFICADO
                else if(position < 17){
                    intent.putExtra("id", Integer.toString(position+3));
                }

                // VERIFICADO
                else if(position < 21){
                    intent.putExtra("id", Integer.toString(position+4));
                }

                // VERIFICADO (21 = HABONITO = 27)
                else if(position < 24){
                    intent.putExtra("id", Integer.toString(position+6));
                }

                // VERIFICADO
                else if(position < 25){
                    intent.putExtra("id", Integer.toString(position+7));
                }

                // VERIFICADO
                else if(position < 27){
                    intent.putExtra("id", Integer.toString(position+9));
                }

                // VERIFICADO
                else if(position < 38){
                    intent.putExtra("id", Integer.toString(position+10));
                }

                // VERIFICADO
                else if(position < 41){
                    intent.putExtra("id", Integer.toString(position+12));
                }

                // VERIFICADO
                else{
                    intent.putExtra("id", Integer.toString(position+14));
                }


                intent.putExtra("lastPage", "lehaka");
                startActivity(intent);
                finish();
        });

        btnVoltarLeakot = findViewById(R.id.btnVoltarLeakotId);
        btnVoltarLeakot.setOnClickListener((V)->{
            btnVoltarLeakot.setBackgroundResource(R.color.White);
            startActivity(new Intent(this, MainActivity.class));
            finish();
        });

        acontecendoRef = database.getReference("Acontecendo_agora");
        acontecendoRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                scrollingText = findViewById(R.id.scrollingTextId3);
                String valor = dataSnapshot.getValue().toString();
                scrollingText.setText(valor);
                scrollingText.setSelected(true);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) { }});
    }


    private void alert(String msg){
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }
}
