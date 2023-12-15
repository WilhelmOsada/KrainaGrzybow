
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

    private List<Product> productList; // Assuming you have a Product class
    private static boolean isBasketView;

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

    public void filterList(List<Product> filteredList) {
        productList = filteredList;
        notifyDataSetChanged();
    }

    // ViewHolder class
    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        // Views from the layout
        ImageView productImage;
        TextView productName;
        TextView productPrice;
        FrameLayout containerAction;

        private int quantity = 1; // Default quantity

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.image_product);
            productName = itemView.findViewById(R.id.text_product_name);
            productPrice = itemView.findViewById(R.id.text_product_price);
            containerAction = itemView.findViewById(R.id.container_action);
        }

        public void bind(Product product) {
            productName.setText(product.getName());
            productPrice.setText(String.valueOf(product.getPrice()));

            if (isBasketView) {
                setupBasketView(product);
            } else {
                Button addButton = new Button(itemView.getContext());
                addButton.setText("Add");

                addButton.setOnClickListener(v -> {
                    // When "Add" is clicked, add the product to the basket
                    BasketFragment.addToBasket(product);
                });

                containerAction.removeAllViews();
                containerAction.addView(addButton);
            }
        }

        private void setupBasketView(Product product) {
            containerAction.removeAllViews();

            LinearLayout quantityLayout = new LinearLayout(itemView.getContext());
            quantityLayout.setOrientation(LinearLayout.HORIZONTAL);

            Button minusButton = new Button(itemView.getContext());
            minusButton.setText("-");
            TextView quantityText = new TextView(itemView.getContext());
            quantityText.setText(String.valueOf(product.getQuantity())); // Set the current quantity
            Button plusButton = new Button(itemView.getContext());
            plusButton.setText("+");

            quantityLayout.addView(minusButton);
            quantityLayout.addView(quantityText);
            quantityLayout.addView(plusButton);

            containerAction.addView(quantityLayout);

            minusButton.setOnClickListener(v -> {
                // When "-" is clicked, decrease the quantity and update the TextView
                int newQuantity = product.getQuantity() - 1;
                if (newQuantity >= 1) {
                    product.setQuantity(newQuantity);
                    quantityText.setText(String.valueOf(newQuantity));
                } else {
                    // If the new quantity is less than or equal to 0, remove the product from the basketList
                    BasketFragment.removeFromBasket(product);
                }
            });

            plusButton.setOnClickListener(v -> {
                // When "+" is clicked, increase the quantity and update the TextView
                int newQuantity = product.getQuantity() + 1;
                product.setQuantity(newQuantity);
                quantityText.setText(String.valueOf(newQuantity));
            });
        }
    }
}
