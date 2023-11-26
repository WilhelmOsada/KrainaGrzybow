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

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.fragment_shop, container, false);
        searchView = view.findViewById( R.id.search_view);

        // Set the SearchView to be iconified by default but visible
        searchView.setIconifiedByDefault(true);
        searchView.clearFocus(); // Ensure it does not request focus initially

        // Set an OnClickListener to expand the SearchView when clicked anywhere on it
        searchView.setOnClickListener( v -> {
            searchView.setIconified( false); // This will expand the SearchView
        });

        // Initialize your product list
        List< Product> productList = new ArrayList< Product>();

        Product test = new Product();
        test.price = 100;
        test.name = "Grzib";

        productList.add( test);

        // Set up the RecyclerView
        adapter = new ProductAdapter( productList, false);
        recyclerView = view.findViewById( R.id.recycler_view);

        recyclerView.setLayoutManager( new LinearLayoutManager( getContext()));
        recyclerView.setAdapter( adapter);

        return view;
    }


}


