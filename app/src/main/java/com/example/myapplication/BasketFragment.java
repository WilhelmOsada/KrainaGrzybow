package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.security.ProtectionDomain;
import java.util.ArrayList;
import java.util.List;

public class BasketFragment extends Fragment {

    private RecyclerView recyclerView;
    private ProductAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_basket, container, false);

        // Prepare your product list for the basket
        List< Product> productList = new ArrayList< Product>() /* get your products for the basket */;

        Product test = new Product();
        test.price = 100;
        test.name = "Grzib";

        productList.add( test);

        // Set up the RecyclerView
        adapter = new ProductAdapter( productList, true);
        recyclerView = view.findViewById( R.id.recycler_view_basket);

        recyclerView.setLayoutManager( new LinearLayoutManager( getContext()));
        recyclerView.setAdapter( adapter);

        return view;
    }
}
