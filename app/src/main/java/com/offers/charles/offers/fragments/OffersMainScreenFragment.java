package com.offers.charles.offers.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.offers.charles.offers.R;
import com.offers.charles.offers.adapters.OfferGridViewAdapter;
import com.offers.charles.offers.models.OfferModel;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OffersMainScreenFragment extends Fragment {

    @BindView(R.id.offer_grid_view)
    RecyclerView offerGridView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.offers_main_screen_fragment, container, false);
        ButterKnife.bind(this, view);
        setUpGridView();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(R.string.main_screen_title);
    }

    private void setUpGridView() {
        List<OfferModel> offers = parseJsonFiles();
        offerGridView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        OfferGridViewAdapter adapter = new OfferGridViewAdapter(offers, getContext(), getActivity().getWindowManager());
        offerGridView.setAdapter(adapter);
    }

    private List<OfferModel> parseJsonFiles() {
        List<OfferModel> results = new ArrayList<>();
        Gson gson = new Gson();
        try {
            JSONArray jsonArray = new JSONArray(importJsonFileIntoMemory());
            for (int i = 0; i < jsonArray.length(); i++) {
                results.add(gson.fromJson(jsonArray.getString(i), OfferModel.class));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return results;
    }

    private String importJsonFileIntoMemory() throws IOException {
        InputStream inputStream = null;
        StringBuilder builder = new StringBuilder();

        try {
            String jsonDataString = null;
            inputStream = getResources().openRawResource(R.raw.offers);
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(inputStream, "UTF-8"));
            while ((jsonDataString = bufferedReader.readLine()) != null) {
                builder.append(jsonDataString);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return builder.toString();
    }
}
