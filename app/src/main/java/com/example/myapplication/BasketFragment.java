
package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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

        Button checkoutButton = view.findViewById( R.id.btn_checkout);
        checkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //PATRYK
            }
        });

        return view;
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