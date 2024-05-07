package com.example.adminpanelproject2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.adminpanelproject2.model.CustomerDetailsModel;
import com.example.adminpanelproject2.model.ProductDetailsModel;

public class AddCustomer extends AppCompatActivity {
    EditText customerNameEditText, emailEditText, mobileEditText, cityEditText;
    EditText productNameEditText, quantityEditText, pricingEditText, mrpEditText;
    EditText addressEditText, cityShippingEditText, pincodeEditText;
    Button submitButton;
    DBHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);

        customerNameEditText = findViewById(R.id.customerNameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        mobileEditText = findViewById(R.id.mobileEditText);
        cityEditText = findViewById(R.id.cityEditText);
        productNameEditText = findViewById(R.id.productNameEditText);
        quantityEditText = findViewById(R.id.quantityEditText);
        pricingEditText = findViewById(R.id.pricingEditText);
        mrpEditText = findViewById(R.id.mrpEditText);
        addressEditText = findViewById(R.id.addressEditText);
        cityShippingEditText = findViewById(R.id.cityShippingEditText);
        pincodeEditText = findViewById(R.id.pincodeEditText);
        submitButton = findViewById(R.id.submitButton);

        dbHelper = new DBHelper(this);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String customerName = customerNameEditText.getText().toString().trim();
                String email = emailEditText.getText().toString().trim();
                String mobile = mobileEditText.getText().toString().trim();
                String city = cityEditText.getText().toString().trim();


                if (!customerName.isEmpty() && !email.isEmpty() && !mobile.isEmpty() && !city.isEmpty()) {

                    // Create a CustomerDetailsModel object
                    CustomerDetailsModel customer = new CustomerDetailsModel(0, customerName, email, mobile, city);
                    // Insert customer details into the database
                    boolean inserted = dbHelper.insertCustomerDetails(customer);

                    if (inserted) {
                        Toast.makeText(AddCustomer.this, "Customer details added successfully", Toast.LENGTH_SHORT).show();
                        // Clear input fields after successful addition
                        customerNameEditText.getText().clear();
                        emailEditText.getText().clear();
                        mobileEditText.getText().clear();
                        cityEditText.getText().clear();
                    } else {
                        Toast.makeText(AddCustomer.this, "Failed to add customer details", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(AddCustomer.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                }
            }




        });
    }
}