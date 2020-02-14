package com.example.assignment12;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class favouritePlace extends AppCompatActivity {

    DatabaseHelper mDataBase;
    List<FavouritePlaceClass> placesList;
    ListView listView;


    placeAdapter placeAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav_place);

//        listView = findViewById(R.id.LVPlaces);
        listView = findViewById(R.id.listView);
        placesList = new ArrayList<>();

        // mDataBase = openOrCreateDatabase(MainActivity.DATABASE_NAME,MODE_PRIVATE,null);
        mDataBase = new DatabaseHelper(this);
        loadPlaces();


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                FavouritePlaceClass place = placesList.get(position);
                double latitude = Double.parseDouble(place.getLatitude());
                double longitude = Double.parseDouble(place.getLongitude());

                Intent intent = new Intent(favouritePlace.this,MainActivity.class);
                intent.putExtra("latitude",latitude);
                intent.putExtra("longitude",longitude);
                startActivity(intent);
                Toast.makeText(favouritePlace.this, "cell clicked", Toast.LENGTH_SHORT).show();
            }
        });
//        listView.setOnItemClickListener(new AdapterView().OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                favouritePlace place = placesList.get(position);
//                double latitude = Double.parseDouble(place.getFavLatitude());
//                double longitude = Double.parseDouble(place.getFavLongitude());
//
//                Intent intent = new Intent(favouritePlace.this,MainActivity.class);
//                intent.putExtra("latitude",latitude);
//                intent.putExtra("longitude",longitude);
//                startActivity(intent);
//                Toast.makeText(favouritePlace.this, "cell clicked", Toast.LENGTH_SHORT).show();
//            }
//        });


    }

    private void loadPlaces() {
        /*
        String sql = "SELECT * FROM employees";


        Cursor cursor = mDataBase.rawQuery(sql, null);

         */
        Cursor cursor = mDataBase.getAllPlaces();

        if(cursor.moveToFirst()){
            placesList.clear();
            do{
                placesList.add(new FavouritePlaceClass(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getDouble(2),
                        cursor.getDouble(3),
                        cursor.getString(4)
                ));
            }while (cursor.moveToNext());

            cursor.close();
        }

            //show item in a listView
            //we use a custom adapter to show employees

            placeAdapter = new placeAdapter(this, R.layout.list_layout, placesList, mDataBase);
//            placeAdapter.notifyDataSetChanged();
            listView.setAdapter(placeAdapter);

        }
    }

//    private void deleteplace(final int position) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(fav_place_Activity.this);
//        builder.setTitle("Are you sure?");
//        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                /*
//                String sql = "DELETE FROM employees WHERE id = ?";
//                mDatabase.execSQL(sql,new Integer[]{employee.getId()});
//
//                 */
//
//                final favoritePlace place = placesList.get(position);
//                int id = place.getId();
//            }
//        });
//        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//
//
//            }
//        });
//        AlertDialog alertDialog = builder.create();
//        alertDialog.show();
//    }



