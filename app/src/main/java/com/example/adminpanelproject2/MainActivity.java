package com.example.adminpanelproject2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.adminpanelproject2.Adapter.CustomerAdapter;
import com.example.adminpanelproject2.model.CustomerDetailsModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button addCustomer;
    private RecyclerView recyclerView;
    private CustomerAdapter adapter;
    private List<CustomerDetailsModel> customerList;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        addCustomer = findViewById(R.id.addCus);

        dbHelper = new DBHelper(this);
        customerList = dbHelper.getAllCustomers();
        adapter = new CustomerAdapter(customerList);
        recyclerView.setAdapter(adapter);


        addCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,AddCustomer.class);
                startActivity(intent);
            }
        });

    }
}