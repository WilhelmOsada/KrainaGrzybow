package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ShopFragment extends Fragment {

    private SearchView searchView;
    private RecyclerView recyclerView;
    private ProductAdapter adapter;
    private List<Product> productList; // Original list to reset the adapter after searching

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shop, container, false);
        searchView = view.findViewById(R.id.search_view);

        // Set the SearchView to be iconified by default but visible
        searchView.setIconifiedByDefault(true);
        searchView.clearFocus(); // Ensure it does not request focus initially

        // Set an OnClickListener to expand the SearchView when clicked anywhere on it
        searchView.setOnClickListener(v -> {
            searchView.setIconified(false); // This will expand the SearchView
        });

        // Initialize your product list
        productList = new ArrayList<>();

        for (int i = 1; i <= 30; i++) {
            Product test = new Product();
            test.setPrice(100 * i);
            test.setName("Product" + i);
            productList.add(test);
        }

        // Set up the RecyclerView
        adapter = new ProductAdapter(productList, false);
        recyclerView = view.findViewById(R.id.recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        // Set up search functionality
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Not needed for this example
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Update the filter as the user types
                filter(newText);
                return false;
            }
        });

        return view;
    }

    private void filter(String text) {
        List<Product> filteredList = new ArrayList<>();

        for (Product product : productList) {
            if (product.getName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(product);
            }
        }

        adapter.filterList(filteredList);
    }
}
