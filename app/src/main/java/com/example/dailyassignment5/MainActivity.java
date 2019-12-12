package com.example.dailyassignment5;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.dailyassignment5.util.AvailableGymMaterials;

import java.lang.reflect.Array;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //for remembering the options and what has been purchased
    private SharedPreferences sharedPreferences;
    private final String PURCHASED_ITEMS_KEY = "purchased_items_key";
    private SharedPreferences.Editor spEditor;

    //for the ListViews
    private ListView availableToPurchase;
    private ListView purchasedItems;
    private List<String> availableItems;
    private List<String> availablePurchasedItems;
    AvailableGymMaterials availableGymMaterials;
    String preferencesPurchased;

    public ImageView gymIcon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //intitalize variables, getExtras, and set up the listview
        initializers();
        setUpListViews();
    }

    //initialize instance variables
    private void initializers(){
        //set up the shared preferences
        sharedPreferences = getSharedPreferences("gym_shared_pref", Context.MODE_PRIVATE);
        spEditor = sharedPreferences.edit();

        preferencesPurchased = sharedPreferences.getString(PURCHASED_ITEMS_KEY,"");
        //
        availableToPurchase = findViewById(R.id.availableToPurchase_ListView);
        purchasedItems = findViewById(R.id.currentlyOwnedByGym_ListView);
        gymIcon = findViewById(R.id.gymIcon);
        //
        availableGymMaterials = new AvailableGymMaterials();
        availableItems = availableGymMaterials.getAllMaterials();
        availablePurchasedItems = availableGymMaterials.convertString(preferencesPurchased);
        Glide.with(this).asGif()
                .load("https://media.giphy.com/media/JJt9Kx3lVCMlG/giphy.gif")
                .into(gymIcon);
    }

    private void setUpListViews(){
        //for the top ListView (the available to purchase ones)
        ArrayAdapter<String> equipmentAdapter = new ArrayAdapter<String>(
                this,
                R.layout.listview_layout,
                R.id.gymItem_TextView,
                availableItems);
        availableToPurchase.setAdapter(equipmentAdapter);
        //set the adapter and the onclicklistener
        availableToPurchase.setAdapter(equipmentAdapter);
        availableToPurchase.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, PurchaseEquipment.class);
                intent.putExtra("EquipmentPosition",position);
                startActivityForResult(intent,707);
            }
        });

        //for the bottom ListView (the available to purchase ones)
        ArrayAdapter<String> equipmentPurchaseAdapter = new ArrayAdapter<String>(
                this,
                R.layout.listview_layout,
                R.id.gymItem_TextView,
                availablePurchasedItems);

        purchasedItems.setAdapter(equipmentAdapter);
        //set the adapter and the onclicklistener
        purchasedItems.setAdapter(equipmentPurchaseAdapter);
        purchasedItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView equipmentTextView = view.findViewById(R.id.gymItem_TextView);
                Toast.makeText(MainActivity.this, equipmentTextView.getText().toString(),Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==707&&resultCode==AppCompatActivity.RESULT_OK){

            preferencesPurchased += " ";
            preferencesPurchased += data.getStringExtra("EquipmentPurchased");
            preferencesPurchased = preferencesPurchased.trim();
            availablePurchasedItems = availableGymMaterials.convertString(preferencesPurchased);
            spEditor.putString(PURCHASED_ITEMS_KEY,preferencesPurchased);
            spEditor.apply();
            setUpListViews();
        }
    }
}
