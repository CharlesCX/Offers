package com.offers.charles.offers;

import android.view.Display;
import android.view.WindowManager;

import com.offers.charles.offers.utils.DimenUtils;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;

import static org.mockito.Mockito.when;

@RunWith(RobolectricTestRunner.class)
public class DimenUtilsTest {

    @Mock
    WindowManager windowManager;
    @Mock
    Display display;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(windowManager.getDefaultDisplay()).thenReturn(display);
    }

    @Test
    public void testGetScreenWidth() {
        int width = DimenUtils.getScreenWidth(windowManager);
        Assert.assertEquals(width, 0);
    }
}
