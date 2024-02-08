package com.example.sqlitepractice;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyDataBase extends SQLiteOpenHelper {


        public static final String DATABASE_NAME = "Car.db";
        public static final String TABLE_NAME = "Car";
        public static final int DATABASE_VERSION = 4;

        public static final String CLM_CAR_ID = "id";

        public static final String CLM_CAR_MODEL= "carName";
        public static final String CLM_MODEL_COLOR = "ModelName";
        public static final String CLM_DIS_PER_LETTER = "disPerle";

        public MyDataBase(@Nullable Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // Define the table creation query
            String createTableQuery = "CREATE TABLE " + TABLE_NAME + " (" +
                    CLM_CAR_ID + "  INTEGER PRIMARY KEY AUTOINCREMENT, "+
                    CLM_CAR_MODEL + " TEXT, " +
                    CLM_MODEL_COLOR + " TEXT, " +
                    CLM_DIS_PER_LETTER + " REAL);";

            // Execute the table creation query
            db.execSQL(createTableQuery);
        }


        long insertData(Car car ){

            SQLiteDatabase db = getWritableDatabase();
            ContentValues values = new ContentValues();

            values.put(CLM_CAR_MODEL, car.getModel());
            values.put( CLM_MODEL_COLOR , car.getColor());
            values.put(CLM_DIS_PER_LETTER , car.getDistancePerLitre());


            return db.insert(TABLE_NAME, null ,values);

        }

    // getCountCar method returns the total number of cars in the database

        long getCountCar(){

            SQLiteDatabase db =getWritableDatabase();

            return DatabaseUtils.queryNumEntries(db , TABLE_NAME);
        }


        boolean  deleteRow(Car car  ){
            SQLiteDatabase db = getWritableDatabase();

            String[] Args = {String.valueOf(car.getId())};

            int result = db.delete(DATABASE_NAME , "id=? " , Args);

            return result > 0 ;
        }


    // getAllCars method retrieves all cars from the database



        public ArrayList<Car> getAllCars(){

            SQLiteDatabase db = getReadableDatabase();

            ArrayList<Car> carOnList = new ArrayList<>();

            Cursor cursor= db.rawQuery("SELECT * FROM  "+ TABLE_NAME, null );

            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                String model = cursor.getString(1);
                String color = cursor.getString(2);
                double dpl = cursor.getDouble(3);

                Car car = new Car(id, model, color, dpl);
                carOnList.add(car);
            }

            cursor.close();

            return carOnList;

        }

         // getSpecific method retrieves cars with a specific model from the database

        public ArrayList<Car> getSpecific(String modelSearch){

            SQLiteDatabase db = getReadableDatabase();

            ArrayList<Car> carOnList = new ArrayList<>();

            Cursor cursor= db.rawQuery("SELECT * FROM  "+ TABLE_NAME + " WHERE "+ CLM_CAR_MODEL +" = ? ", new String[] {modelSearch});

            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                String model = cursor.getString(1);
                String color = cursor.getString(2);
                double dpl = cursor.getDouble(3);

                Car car = new Car(id, model, color, dpl);
                carOnList.add(car);
            }

            cursor.close();

            return carOnList;

        }



    // onUpgrade method is called when the database needs to be upgraded
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

            // Create a new table by calling onCreate
            onCreate(db);


        }
    }