package com.example.dailyassignment5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.dailyassignment5.util.AvailableGymMaterials;

import java.util.List;

public class PurchaseEquipment extends AppCompatActivity {

    private Button button;
    private EditText editText;
    private TextView equipmentName;
    private AvailableGymMaterials availableGymMaterials;
    private int positionOfEquip;
    private String equipment;
    private String[] URLs;
    public ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_equipment);

        //set up variable and ListViews first
        initializers();

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                button.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                button.setVisibility(View.INVISIBLE);
            }

            @Override
            public void afterTextChanged(Editable s) {
                button.setVisibility(View.VISIBLE);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("EquipmentPurchased",positionOfEquip+","+(editText.getText().toString().trim()));
                setResult(AppCompatActivity.RESULT_OK,intent);
                finish();
            }
        });
    }

    private void initializers(){
        equipmentName = findViewById(R.id.nameEquipment_TextView);
        button = findViewById(R.id.purchaseEquip_Button);
        editText = findViewById(R.id.purchaseEquip_EditText);
        availableGymMaterials = new AvailableGymMaterials();
        positionOfEquip = getIntent().getIntExtra("EquipmentPosition",-1);
        equipment = availableGymMaterials.getMaterials(positionOfEquip);
        equipmentName.setText(equipment);
        button.setVisibility(View.INVISIBLE);
        imageView = findViewById(R.id.equipmentIcon);
        Glide.with(this)
                .load(availableGymMaterials.getURL(positionOfEquip))
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(imageView);

    }

}
