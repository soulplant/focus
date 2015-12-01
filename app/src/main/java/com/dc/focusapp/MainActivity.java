package com.dc.focusapp;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class MainActivity extends AppCompatActivity {
  private static final String TAG = "MainActivity";
  private ViewPager viewPager;
  private final List<String> dummyTasks =
      new ArrayList<>(Arrays.asList("Meditate", "Exercise at the park", "Put on washing", "Do dishes"));

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_main);

    final TaskPagerAdapter adapter = new TaskPagerAdapter(this, getSupportFragmentManager(), dummyTasks);
    viewPager = (ViewPager) findViewById(R.id.pager);
    viewPager.setAdapter(adapter);
    viewPager.setCurrentItem(1);
    viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
      private int scrollState = ViewPager.SCROLL_STATE_IDLE;
      @Override
      public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

      }

      @Override
      public void onPageSelected(int position) {
        if (scrollState == ViewPager.SCROLL_STATE_IDLE) {
          Log.d(TAG, "onPageSelected(" + position + ")");
          adapter.onPageSelected(position);
          Log.d(TAG, "current item = " + viewPager.getCurrentItem());
        }
      }

      @Override
      public void onPageScrollStateChanged(int state) {
        scrollState = state;
        if (state == ViewPager.SCROLL_STATE_IDLE) {
          MainActivity.this.runOnUiThread(new Runnable() {

            @Override
            public void run() {
              int position = viewPager.getCurrentItem();
              Log.d(TAG, "onPageScrollStateChanged(IDLE) = " + position);
              int newPosition = adapter.onPageSelected(position);
              Log.d(TAG, "current item 2 = " + viewPager.getCurrentItem());
//          viewPager.setCurrentItem(newPosition);

            }
          });
        }
      }
    });

    // Hide UI first
    ActionBar actionBar = getSupportActionBar();
    if (actionBar != null) {
      actionBar.hide();
    }

    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN);
  }
}
