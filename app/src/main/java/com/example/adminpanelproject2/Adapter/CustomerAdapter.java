package com.example.adminpanelproject2.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adminpanelproject2.R;
import com.example.adminpanelproject2.model.CustomerDetailsModel;

import java.util.List;

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.ViewHolder> {
    private List<CustomerDetailsModel> customerList;

    public CustomerAdapter(List<CustomerDetailsModel> customerList) {
        this.customerList = customerList;
    }

    public void updateData(List<CustomerDetailsModel> newCustomerList) {
        customerList.clear(); // Clear the existing data
        customerList.addAll(newCustomerList); // Add new data
        notifyDataSetChanged(); // Notify the adapter of the dataset change
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_customer, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CustomerDetailsModel customer = customerList.get(position);
        holder.customerNameTextView.setText(customer.getCustomerName());
        // Set other customer details as needed
    }

    @Override
    public int getItemCount() {
        return customerList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView customerNameTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            customerNameTextView = itemView.findViewById(R.id.customerNameTextView);
            // Initialize other views as needed
        }
    }
}
