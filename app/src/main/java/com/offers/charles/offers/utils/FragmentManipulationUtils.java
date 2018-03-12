package com.offers.charles.offers.utils;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

public class FragmentManipulationUtils {

    public static void pushInitialScreen(FragmentManager fragmentManager, int containerId, Fragment fragment, String tag) {
        fragmentManager.beginTransaction().add(containerId, fragment, tag).commit();
    }

    public static void pushScreen(FragmentManager fragmentManager, int containerId, Fragment fragment, String tag) {
        fragmentManager.beginTransaction().replace(containerId, fragment, tag).addToBackStack(tag).commit();
    }

    public static void popScreen(FragmentManager fragmentManager) {
        fragmentManager.popBackStack();
    }

    public static void handleBackPressed(FragmentManager fragmentManager, Activity activity) {
        if (fragmentManager.getBackStackEntryCount() > 0) {
            popScreen(fragmentManager);
        } else {
            activity.finish();
        }
    }
}
