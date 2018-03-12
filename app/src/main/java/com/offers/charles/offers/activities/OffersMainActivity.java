package com.offers.charles.offers.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.offers.charles.offers.R;
import com.offers.charles.offers.fragments.OffersMainScreenFragment;
import com.offers.charles.offers.utils.FragmentManipulationUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OffersMainActivity extends AppCompatActivity {

    @BindView(R.id.main_activity_container)
    FrameLayout mMainContainer;
    @BindView(R.id.tool_bar)
    Toolbar mToolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offers_main);
        ButterKnife.bind(this);
        setSupportActionBar(mToolBar);
        setUpToolBar();
        setUpMainScreen();
    }

    private void setUpToolBar() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.main_screen_title);
    }

    private void setUpMainScreen() {
        Fragment fragment = new OffersMainScreenFragment();
        FragmentManipulationUtils.pushInitialScreen(
                getSupportFragmentManager(),
                R.id.main_activity_container,
                fragment,
                "offers_screen");
    }

    @Override
    public void onBackPressed() {
        FragmentManipulationUtils.handleBackPressed(getSupportFragmentManager(), this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
