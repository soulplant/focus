package com.dc.focusapp;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

public class TaskPagerAdapter extends FragmentStatePagerAdapter {
  private final Context context;
  private final List<String> tasks;
  private final List<String> colors =
      Arrays.asList("#326B98", "#70B9FB", "#5FAD4E", "#FF8826", "#348C90", "#F24747");

  public TaskPagerAdapter(Context context, FragmentManager fragmentManager, List<String> tasks) {
    super(fragmentManager);
    this.context = context;
    this.tasks = tasks;
  }

  @Override
  public Fragment getItem(int position) {
    if (position == 0) {
      TaskListFragment list = new TaskListFragment();
      list.setListAdapter(new ArrayAdapter<String>(context, R.layout.task_line, tasks) {
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
          TextView textView = (TextView) super.getView(position, convertView, parent);
          textView.setTypeface(Util.getTypeface(context));
          textView.setBackgroundColor(Color.parseColor(getColor(position)));
          return textView;
        }
      });
      return list;
    }
    int taskPosition = position - 1;
    TaskFragment task = new TaskFragment();
    Bundle args = new Bundle();
    args.putString(TaskFragment.NAME, tasks.get(taskPosition));
    args.putString(TaskFragment.COLOR, getColor(taskPosition));
    task.setArguments(args);
    return task;
  }

  private String getColor(int position) {
    return colors.get(position % colors.size());
  }

  @Override
  public int getCount() {
    // One for each task and one for the list view.
    return tasks.size() + 1;
  }
}
