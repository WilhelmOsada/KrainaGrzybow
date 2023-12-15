package com.example.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class BasketFragment extends Fragment {

    private RecyclerView recyclerView;
    private static ProductAdapter adapter;
    private static List<Product> basketList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_basket, container, false);

        adapter = new ProductAdapter(basketList, true);
        recyclerView = view.findViewById(R.id.recycler_view_basket);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        Button checkoutButton = view.findViewById(R.id.btn_checkout);
        checkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String paysafecardTestURL = "https://api.test.paysafe.com/";

                // Create an Intent to open a web browser
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(paysafecardTestURL));

                // Check if there is an app to handle the Intent
                if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                    startActivity(intent);
                } else {
                    // Handle the case where no app can handle the Intent
                    Toast.makeText(getContext(), "No app to handle the request", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button priceButton = view.findViewById(R.id.btn_price);
        priceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Calculate the total price of all products in the basket
                double totalPrice = calculateTotalPrice();

                // Set the total price as text on the priceButton
                String totalPriceText = totalPrice + "       ";
                priceButton.setText(totalPriceText);
            }
        });


        return view;
    }

    // Function to calculate the total price of all products in the basket
    private double calculateTotalPrice() {
        double totalPrice = 0;

        for (Product product : basketList) {
            // Assuming each product has a getPrice() method
            totalPrice += product.getPrice() * product.getQuantity();
        }

        return totalPrice;
    }

    public static void addToBasket(Product product) {
        // Check if the product is already in the basketList
        for (Product p : basketList) {
            if (p.getName().equals(product.getName())) {
                // Update the quantity and notify the adapter
                p.setQuantity(p.getQuantity() + 1);
                if (adapter != null) {
                    adapter.notifyDataSetChanged();
                }
                return; // Exit the method if the product is found
            }
        }

        // If the product is not in the basketList, add it with quantity 1
        product.setQuantity(1);
        basketList.add(product);
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }

    }

    public static void removeFromBasket(Product product) {
        basketList.remove(product);
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }
}
