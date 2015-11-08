package com.dc.focusapp;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import java.util.Arrays;
import java.util.List;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class MainActivity extends AppCompatActivity {
  private ViewPager viewPager;
  private final List<String> dummyTasks =
      Arrays.asList("Meditate", "Exercise at the park", "Put on washing", "Do dishes");

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_main);

    TaskPagerAdapter adapter = new TaskPagerAdapter(getSupportFragmentManager(), dummyTasks);
    viewPager = (ViewPager) findViewById(R.id.pager);
    viewPager.setAdapter(adapter);

    // Hide UI first
    ActionBar actionBar = getSupportActionBar();
    if (actionBar != null) {
      actionBar.hide();
    }

    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN);
  }
}
