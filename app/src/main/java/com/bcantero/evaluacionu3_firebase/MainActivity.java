package com.bcantero.evaluacionu3_firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.functions.FirebaseFunctions;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView id_listView;

    MyArrayAdapter arrayAdapter;
    List<Sensor> sensorList;
    ListView SensorListView;
    private FirebaseFunctions mFunctions;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference dbReference;

    private Sensor sensoractual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorList = new ArrayList<>();
        arrayAdapter =  new MyArrayAdapter(sensorList, getBaseContext(), getLayoutInflater());
        id_listView = (ListView) findViewById(R.id.id_listView);
        id_listView.setAdapter(arrayAdapter);

        mFunctions = FirebaseFunctions.getInstance();

        initFirebaseDB();
        loadDataFromFirebase();

        id_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {

                Intent actualizar =  new Intent(MainActivity.this, SensorUpdate.class);

                sensoractual = sensorList.get(i);
                String idd;

                //dbReference.child("sensor").child(sensoractual.getIdSensor()).setValue(sensoractual);
                //Toast.makeText(this, "Sensor actualizado!", Toast.LENGTH_SHORT).show();

                idd = sensoractual.getId_Sensor().toString();

                actualizar.putExtra("id", idd);
                startActivity(actualizar);


                //Toast.makeText(getApplicationContext(), idd, Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void loadDataFromFirebase() {
        dbReference.child("sensor").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                sensorList.clear();
                for (DataSnapshot ds : snapshot.getChildren()) {
                    sensorList.add(ds.getValue(Sensor.class));
                }
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getBaseContext(), "Error, "+ error.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void updateUser(View view) {

        //sensoractual.setNombreSensor(lblnombre.getText().toString());
        //sensoractual.setTipoSensor(lbltipo.getText().toString());

        Intent actualizar =  new Intent(MainActivity.this, SensorUpdate.class);

        String idd;

        //dbReference.child("sensor").child(sensoractual.getIdSensor()).setValue(sensoractual);
        //Toast.makeText(this, "Sensor actualizado!", Toast.LENGTH_SHORT).show();

        idd = sensoractual.getId_Sensor().toString();

        actualizar.putExtra("id", idd);
        startActivity(actualizar);
    }

    private void initFirebaseDB() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        dbReference = firebaseDatabase.getReference();
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }

}