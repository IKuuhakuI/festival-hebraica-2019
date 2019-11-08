package com.ort.luiz.projeto_danca;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class OrdemActivity extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference acontecendoRef;

    TextView scrollingText;

    Button btnVoltarOrdem, btnShow1, btnShow2, btnShow3, btnShow4;

    String selecionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordem);

        database = FirebaseDatabase.getInstance();

        scrollingText = findViewById(R.id.scrollingTextId7);

        acontecendoRef = database.getReference("Acontecendo_agora");
        acontecendoRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                scrollingText = findViewById(R.id.scrollingTextId7);
                String valor = dataSnapshot.getValue().toString();
                scrollingText.setText(valor);
                scrollingText.setSelected(true);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) { }});

        btnVoltarOrdem = findViewById(R.id.btnVoltarOrdemId);
        btnVoltarOrdem.setOnClickListener((V)->{
            btnVoltarOrdem.setBackgroundResource(R.color.White);
            startActivity(new Intent(this, MainActivity.class));
        });

        btnShow1 = findViewById(R.id.btnShow1Id);
        btnShow2 = findViewById(R.id.btnShow2Id);
        btnShow3 = findViewById(R.id.btnShow3Id);
        btnShow4 = findViewById(R.id.btnShow4Id);

        btnShow1.setOnClickListener((V) -> {
            selecionado = "Show1";
            btnShow1.setBackgroundResource(R.color.White);
            btnShow2.setBackgroundResource(R.drawable.button_border);
            btnShow3.setBackgroundResource(R.drawable.button_border);
            btnShow4.setBackgroundResource(R.drawable.button_border);
        });

        btnShow2.setOnClickListener((V) -> {
            selecionado = "Show2";
            btnShow1.setBackgroundResource(R.drawable.button_border);
            btnShow2.setBackgroundResource(R.color.White);
            btnShow3.setBackgroundResource(R.drawable.button_border);
            btnShow4.setBackgroundResource(R.drawable.button_border);
        });

        btnShow3.setOnClickListener((V) -> {
            selecionado = "Show3";
            btnShow1.setBackgroundResource(R.drawable.button_border);
            btnShow2.setBackgroundResource(R.drawable.button_border);
            btnShow3.setBackgroundResource(R.color.White);
            btnShow4.setBackgroundResource(R.drawable.button_border);
        });

        btnShow4.setOnClickListener((V) -> {
            selecionado = "Show4";
            btnShow1.setBackgroundResource(R.drawable.button_border);
            btnShow2.setBackgroundResource(R.drawable.button_border);
            btnShow3.setBackgroundResource(R.drawable.button_border);
            btnShow4.setBackgroundResource(R.color.White);
        });
    }


}
