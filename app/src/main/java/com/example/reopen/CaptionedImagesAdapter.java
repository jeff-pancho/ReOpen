package com.example.reopen;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class CaptionedImagesAdapter extends RecyclerView.Adapter<CaptionedImagesAdapter.ViewHolder> {
    private List<BusinessListing> businesses;

    private Listener listener;

    interface Listener {
        void onClick(BusinessListing listing);
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;

        public ViewHolder (CardView v) {
            super(v);
            cardView = v;
        }
    }

    public CaptionedImagesAdapter(List<BusinessListing> businesses) {
        this.businesses = businesses;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cv = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_captioned_image, parent, false);
        return new ViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final CardView cardView = holder.cardView;
        final BusinessListing b = businesses.get(position);

        ImageView imageView = cardView.findViewById(R.id.item_image);
        Picasso.get().load(b.getImageURL()).into(imageView);
//        Drawable drawable = ContextCompat.getDrawable(cardView.getContext(), b.getImageResourceId());
//        Drawable drawable = ContextCompat.getDrawable(cardView.getContext(), R.drawable.dummy);

//        imageView.setImageDrawable(drawable);
        imageView.setContentDescription(b.getName());

        TextView textView = cardView.findViewById(R.id.item_text);
        textView.setText(b.getName());

        cardView.setOnClickListener(id -> {
            this.listener.onClick(b);
        });
    }

    @Override
    public int getItemCount() {
        return this.businesses.size();
    }
}
