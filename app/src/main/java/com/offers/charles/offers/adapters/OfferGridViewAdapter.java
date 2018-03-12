package com.offers.charles.offers.adapters;

import android.content.Context;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.offers.charles.offers.R;
import com.offers.charles.offers.fragments.OfferDetailsScreenFragment;
import com.offers.charles.offers.models.OfferModel;
import com.offers.charles.offers.utils.DimenUtils;
import com.offers.charles.offers.utils.FragmentManipulationUtils;
import com.offers.charles.offers.utils.SharedPreferenceUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OfferGridViewAdapter extends RecyclerView.Adapter<OfferGridViewAdapter.OfferGridItemViewHolder> {

    private List<OfferModel> offersList;
    private Context context;
    private WindowManager windowManager;

    public OfferGridViewAdapter(List<OfferModel> offersList, Context context, WindowManager windowManager) {
        this.offersList = offersList;
        this.context = context;
        this.windowManager = windowManager;
    }

    @Override
    public OfferGridItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item, parent, false);
        return new OfferGridItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final OfferGridItemViewHolder holder, int position) {
        final OfferModel model = offersList.get(position);
        Glide
            .with(context)
            .load(model.getUrl())
            .into(holder.gridItemImageView);
        holder.gridItemCurrentValueText.setText(model.getCurrentValue());
        holder.gridItemName.setText(model.getName());
        holder.gridItemImageView.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DimenUtils.getGridItemHeight(context, windowManager, 0.64)));
        holder.itemView.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        FragmentManipulationUtils.pushScreen(
                                ((AppCompatActivity)context).getSupportFragmentManager(),
                                R.id.main_activity_container,
                                OfferDetailsScreenFragment.getInstance(model),
                                model.getId());
                    }
                }
        );
        if (SharedPreferenceUtils.containsItem(context, model.getId())) {
            holder.gridFavorite.setImageDrawable(context.getDrawable(R.drawable.heart));
            holder.gridFavorite.setColorFilter(context.getResources().getColor(R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
        } else {
            holder.gridFavorite.setImageDrawable(context.getDrawable(R.drawable.heart_outline));
            holder.gridFavorite.setColorFilter(context.getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
        }

    }

    @Override
    public int getItemCount() {
        return offersList.size();
    }

    public static class OfferGridItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.grid_item_image)
        ImageView gridItemImageView;
        @BindView(R.id.grid_item_current_value)
        TextView gridItemCurrentValueText;
        @BindView(R.id.grid_item_name)
        TextView gridItemName;
        @BindView(R.id.grid_item_favorite)
        ImageView gridFavorite;

        View itemView;

        public OfferGridItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.itemView = itemView;
        }
    }
}
