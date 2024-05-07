package com.example.adminpanelproject2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.adminpanelproject2.model.CustomerDetailsModel;
import com.example.adminpanelproject2.model.ProductDetailsModel;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    public static final String dbName = "Admin.db";

    public DBHelper(@Nullable Context context) {
        super(context, dbName, null, 1);
    }

    private static final String TABLE_CUSTOMER_DETAILS = "customer_details";
    private static final String KEY_ID = "id";

    // Customer Details Table - column names
    private static final String KEY_CUSTOMER_NAME = "customer_name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_MOBILE_NUMBER = "mobile_number";
    private static final String KEY_CITY = "city";

    // Define table and column names for product details
    private static final String TABLE_PRODUCT_DETAILS = "product_details";
    private static final String KEY_PRODUCT_NAME = "product_name";
    private static final String KEY_QUANTITY = "quantity";
    private static final String KEY_PRICING = "pricing";
    private static final String KEY_MRP = "mrp";
    private static final String KEY_CUSTOMER_ID_FOREIGN = "customer_id"; // Foreign key


    // Table Create Statement
    // Customer Details table create statement
    private static final String CREATE_TABLE_CUSTOMER_DETAILS = "CREATE TABLE " + TABLE_CUSTOMER_DETAILS + "("
            + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_CUSTOMER_NAME + " TEXT,"
            + KEY_EMAIL + " TEXT,"
            + KEY_MOBILE_NUMBER + " TEXT,"
            + KEY_CITY + " TEXT"
            + ")";

    private static final String CREATE_TABLE_PRODUCT_DETAILS = "CREATE TABLE " + TABLE_PRODUCT_DETAILS + "("
            + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_PRODUCT_NAME + " TEXT,"
            + KEY_QUANTITY + " INTEGER,"
            + KEY_PRICING + " REAL,"
            + KEY_MRP + " REAL,"
            + KEY_CUSTOMER_ID_FOREIGN + " INTEGER," // Foreign key column
            + "FOREIGN KEY(" + KEY_CUSTOMER_ID_FOREIGN + ") REFERENCES " + TABLE_CUSTOMER_DETAILS + "(" + KEY_ID + ")" // Foreign key constraint
            + ")";


    @Override
    public void onCreate(SQLiteDatabase db) {
        // Log a message when onCreate is called
        Log.d("DBHelper", "onCreate method called");
        db.execSQL("create table users (username Text primary key, password TEXT)");
        db.execSQL(CREATE_TABLE_CUSTOMER_DETAILS);
        db.execSQL(CREATE_TABLE_PRODUCT_DETAILS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists users");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CUSTOMER_DETAILS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCT_DETAILS);
    }

    public boolean insertUserData(String username,String password)
    {
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username",username);
        contentValues.put("password",password);
        long result = myDB.insert("users",null,contentValues);
        if (result==-1)
            return false;
        else
            return true;
    }

    public boolean checkUsername(String username)
    {
        SQLiteDatabase myDB = this.getReadableDatabase();
        Cursor cursor = myDB.rawQuery("select * from users where username=?",new String[]{username});
        if (cursor.getCount()>0){
            return true;
        }
        else return false;
    }

    public boolean checkUser(String username,String pass){
        SQLiteDatabase myDB =this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("select * from users where username = ? and password = ?",new String[]{username,pass});
        if (cursor.getCount()>0){
            return true;
        }
        else return false;
    }

    // Insert customer details
    public long insertCustomerDetails(CustomerDetailsModel customer) {
        SQLiteDatabase db = this.getWritableDatabase();
        long insertedId = -1; // Initialize insertedId to -1

        try {
            ContentValues values = new ContentValues();
            values.put(KEY_CUSTOMER_NAME, customer.getCustomerName());
            values.put(KEY_EMAIL, customer.getEmail());
            values.put(KEY_MOBILE_NUMBER, customer.getMobileNumber());
            values.put(KEY_CITY, customer.getCity());

            // insert row
            insertedId = db.insert(TABLE_CUSTOMER_DETAILS, null, values);
        } catch (Exception e) {
            // Log any exceptions that occur
            Log.e("DBHelper", "Error inserting customer details: " + e.getMessage());
        } finally {
            // close db connection
            db.close();
        }

        return insertedId; // Return the inserted row ID
    }

    // Insert product details method
    public boolean insertProductDetails(ProductDetailsModel product) {
        SQLiteDatabase db = this.getWritableDatabase();

        try {
            ContentValues values = new ContentValues();
            values.put(KEY_PRODUCT_NAME, product.getProductName());
            values.put(KEY_QUANTITY, product.getQuantity());
            values.put(KEY_PRICING, product.getPricing());
            values.put(KEY_MRP, product.getMrp());
            values.put(KEY_CUSTOMER_ID_FOREIGN, product.getCustomerId());

            // Insert row
            long result = db.insert(TABLE_PRODUCT_DETAILS, null, values);
            return result != -1;
        } catch (Exception e) {
            Log.e("DBHelper", "Error inserting product details: " + e.getMessage());
            return false;
        } finally {
            db.close();
        }
    }

    public List<CustomerDetailsModel> getAllCustomers() {
        List<CustomerDetailsModel> customerList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_CUSTOMER_DETAILS, null);
        if (cursor.moveToFirst()) {
            do {
                long id = cursor.getLong(cursor.getColumnIndexOrThrow(KEY_ID));
                String customerName = cursor.getString(cursor.getColumnIndexOrThrow(KEY_CUSTOMER_NAME));
                String email = cursor.getString(cursor.getColumnIndexOrThrow(KEY_EMAIL));
                String mobileNumber = cursor.getString(cursor.getColumnIndexOrThrow(KEY_MOBILE_NUMBER));
                String city = cursor.getString(cursor.getColumnIndexOrThrow(KEY_CITY));

                CustomerDetailsModel customer = new CustomerDetailsModel(id, customerName, email, mobileNumber, city);
                customerList.add(customer);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return customerList;
    }


}
