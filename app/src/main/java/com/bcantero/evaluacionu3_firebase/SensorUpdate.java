package com.bcantero.evaluacionu3_firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SensorUpdate extends AppCompatActivity implements View.OnClickListener{

    private TextView lblnombre, lbltipo, lblvalor,lblubicacion, lblobserv, lblfecha;

    private DatabaseReference dbReference;
    FirebaseDatabase database;

    private Sensor sensorActual;

    private Button update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_update);


        lblnombre = (TextView) findViewById(R.id.lblnombre);
        lbltipo = (TextView) findViewById(R.id.lbltipo);
        lblvalor = (TextView) findViewById(R.id.lblvalor);
        lblubicacion = (TextView) findViewById(R.id.lblubicacion);
        lblobserv = (TextView) findViewById(R.id.lblobserv);
        lblfecha = (TextView) findViewById(R.id.lblfecha);

        update = (Button) findViewById(R.id.update);
        update.setOnClickListener(this);

        Bundle id =  getIntent().getExtras();
        String idSensor = id.getString("id");

        database = FirebaseDatabase.getInstance();

        dbReference = database.getReference();

        dbReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){

                    lblnombre.setText(snapshot.child("sensor").child(idSensor).child("sensorName").getValue().toString());
                    lbltipo.setText(snapshot.child("sensor").child(idSensor).child("typeSensor").getValue().toString());
                    lblvalor.setText(snapshot.child("sensor").child(idSensor).child("valueSensor").getValue().toString());
                    lblubicacion.setText(snapshot.child("sensor").child(idSensor).child("locationSensor").getValue().toString());
                    lblobserv.setText(snapshot.child("sensor").child(idSensor).child("observationSensor").getValue().toString());
                    lblfecha.setText(snapshot.child("sensor").child(idSensor).child("dateSensor").getValue().toString());

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.update:

                Bundle id =  getIntent().getExtras();
                String idSensor = id.getString("id");

                String txtObserv = lblobserv.getText().toString();

                dbReference.child("sensor").child(idSensor).child("observationSensor").setValue(txtObserv);

                Toast.makeText(getApplicationContext(), "Regitro actualizado", Toast.LENGTH_SHORT).show();

                Intent intent =  new Intent(this, MainActivity.class);
                startActivity(intent);

                break;

        }
    }
}