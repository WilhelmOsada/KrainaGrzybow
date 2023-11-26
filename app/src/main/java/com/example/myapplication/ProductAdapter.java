package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private List< Product> productList; // Assuming you have a Product class
    public static boolean isBasketView = false;

    // Constructor
    public ProductAdapter(List<Product> productList, boolean isBasketView) {
        this.productList = productList;
        this.isBasketView = isBasketView;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.bind(product);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    // ViewHolder class
    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        // Views from the layout
        ImageView productImage;
        TextView productName;
        TextView productPrice;
        FrameLayout containerAction;

        public ProductViewHolder( @NonNull View itemView) {
            super( itemView);
            productImage = itemView.findViewById(R.id.image_product);
            productName = itemView.findViewById(R.id.text_product_name);
            productPrice = itemView.findViewById(R.id.text_product_price);
            containerAction = itemView.findViewById(R.id.container_action);
        }

        public void bind( Product product) {
            // Set data to your views
            productName.setText( product.getName());
            productPrice.setText( String.valueOf( product.getPrice()));

            if( isBasketView)
            {
                setupBasketView();
            }
            else
            {
                // Add an "Add" button dynamically
                Button addButton = new Button(itemView.getContext());
                addButton.setText( "Add");
                containerAction.addView(addButton);

                // Set onClickListener for addButton to handle add action
                addButton.setOnClickListener(v -> {
                    // TODO: Implement your add logic here
                });
            }
        }

        private void setupBasketView() {
            containerAction.removeAllViews(); // Clear any previous views

            LinearLayout quantityLayout = new LinearLayout(itemView.getContext());
            quantityLayout.setOrientation( LinearLayout.HORIZONTAL);

            Button minusButton = new Button(itemView.getContext());
            minusButton.setText("-");

            TextView quantityText = new TextView(itemView.getContext());
            quantityText.setText("1"); // Set the current quantity

            Button plusButton = new Button(itemView.getContext());
            plusButton.setText("+");

            quantityLayout.addView(minusButton);
            quantityLayout.addView(quantityText);
            quantityLayout.addView(plusButton);

            containerAction.addView(quantityLayout);

            // Set onClickListeners for plusButton and minusButton to handle quantity changes
            minusButton.setOnClickListener(v -> {
                // TODO: Implement decrease quantity logic
            });

            plusButton.setOnClickListener(v -> {
                // TODO: Implement increase quantity logic
            });
        }
    }
}

