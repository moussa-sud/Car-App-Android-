package com.example.sqlitepractice;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText modelEditText;
    private EditText colorEditText;

    private EditText distancePerLetter;
    private Button saveButton , ResetButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        // Initialize your database !!!!!!!!!!!!!! given the name if instance db 

        MyDataBase db = new MyDataBase(this);




        modelEditText = findViewById(R.id.modelEditText);
        colorEditText = findViewById(R.id.colorEditText);
        distancePerLetter = findViewById(R.id.distancePerLetterEditText);
        saveButton = findViewById(R.id.saveButton);
        ResetButton = findViewById(R.id.resetButton);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String model = modelEditText.getText().toString();
                String color = colorEditText.getText().toString();
                double distancePer = Double.parseDouble(distancePerLetter.getText().toString());

                // at the first I used aggregation to sent all the data in one time :)

                Car car = new Car(model ,color , distancePer);

                // After we used aggregation to store data in the Car class ,
                // our method "insertData" in MyDataBase class
                // will retrieve data and sent all to database :)

                db.insertData(car);

                Toast.makeText(MainActivity.this, "Sent Successfully", Toast.LENGTH_SHORT).show();
                db.close();

            }
        });


        ResetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MyDataBase db = new MyDataBase(MainActivity.this);

                ArrayList<Car> car = db.getSpecific("BMW");


                Toast.makeText(MainActivity.this, "Your items are : " + car.size(), Toast.LENGTH_LONG).show();

                db.close();

            }
        });



    }
}