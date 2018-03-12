package com.offers.charles.offers.fragments;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.offers.charles.offers.R;
import com.offers.charles.offers.models.OfferModel;
import com.offers.charles.offers.utils.SharedPreferenceUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OfferDetailsScreenFragment extends Fragment {

    private static final String MODEL_TAG = "offer_model";

    @BindView(R.id.details_image)
    ImageView detailImageView;
    @BindView(R.id.details_cash_back_value)
    TextView detailsCashBackTextView;
    @BindView(R.id.details_name)
    TextView detailsNameTextView;
    @BindView(R.id.details_description)
    TextView detailsDescriptionTextView;
    @BindView(R.id.details_term)
    TextView detailsTermTextView;
    @BindView(R.id.details_favorite_button)
    Button detailsFavoriteButton;
    @BindView(R.id.details_favorite)
    ImageView detailsFavoriteImageView;

    public static OfferDetailsScreenFragment getInstance(OfferModel model) {
        OfferDetailsScreenFragment fragment = new OfferDetailsScreenFragment();
        Bundle extras = new Bundle();
        extras.putParcelable(MODEL_TAG, model);
        fragment.setArguments(extras);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.offer_details_screen_fragment, container, false);
        ButterKnife.bind(this, view);

        OfferModel model = getArguments().getParcelable(MODEL_TAG);
        setUpDetailsView(model);
        return view;
    }

    private void setUpDetailsView(final OfferModel model) {
        Glide.with(getContext()).load(model.getUrl()).into(detailImageView);
        detailsCashBackTextView.setText(model.getCurrentValue());
        detailsNameTextView.setText(model.getName());
        detailsDescriptionTextView.setText(model.getDescription());
        detailsTermTextView.setText(model.getTerms());
        detailsFavoriteButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (SharedPreferenceUtils.containsItem(getContext(), model.getId())) {
                            SharedPreferenceUtils.removeFromRecord(getContext(), model.getId());
                            toggleFavoriteImageView(false);
                            Toast.makeText(getContext(), "Item has been removed from favorite list", Toast.LENGTH_SHORT).show();
                        } else {
                            SharedPreferenceUtils.addToRecord(getContext(), model.getId());
                            toggleFavoriteImageView(true);
                            Toast.makeText(getContext(), "Item has been added to favorite list", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
        initializeFavoriteImageView(model);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(model.getName());
    }

    private void initializeFavoriteImageView(OfferModel model) {
        if (SharedPreferenceUtils.containsItem(getContext(), model.getId())) {
            toggleFavoriteImageView(true);
        } else {
            toggleFavoriteImageView(false);
        }
    }

    private void toggleFavoriteImageView(boolean favorite) {
        if (favorite) {
            detailsFavoriteImageView.setImageDrawable(getContext().getDrawable(R.drawable.heart));
            detailsFavoriteImageView.setColorFilter(getContext().getResources().getColor(R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
        } else {
            detailsFavoriteImageView.setImageDrawable(getContext().getDrawable(R.drawable.heart_outline));
            detailsFavoriteImageView.setColorFilter(getContext().getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
        }
    }
}
