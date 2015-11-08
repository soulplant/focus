package com.dc.focusapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.Arrays;
import java.util.List;

public class TaskPagerAdapter extends FragmentStatePagerAdapter {
  private final List<String> tasks;
  private final List<String> colors =
      Arrays.asList("#326B98", "#70B9FB", "#5FAD4E", "#FF8826", "#348C90", "#F24747");

  public TaskPagerAdapter(FragmentManager fragmentManager, List<String> tasks) {
    super(fragmentManager);
    this.tasks = tasks;
  }

  @Override
  public Fragment getItem(int position) {
    TaskFragment task = new TaskFragment();
    Bundle args = new Bundle();
    args.putString(TaskFragment.NAME, tasks.get(position));
    args.putString(TaskFragment.COLOR, colors.get(position % colors.size()));
    task.setArguments(args);
    return task;
  }

  @Override
  public int getCount() {
    return tasks.size();
  }
}
