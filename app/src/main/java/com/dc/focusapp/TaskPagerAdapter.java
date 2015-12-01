package com.dc.focusapp;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaskPagerAdapter extends ViewPagerAdapter {
  private static final String TAG = "TaskPagerAdapter";
  private final Context context;
  private final List<String> tasks;
  private final List<String> colors =
      new ArrayList<>(Arrays.asList("#326B98", "#70B9FB", "#5FAD4E", "#FF8826", "#348C90", "#F24747"));
  private final ArrayList<Integer> taskIds;

  public TaskPagerAdapter(Context context, FragmentManager fragmentManager, List<String> tasks) {
    super(fragmentManager);
    this.context = context;
    this.tasks = tasks;
    this.taskIds = new ArrayList<>();
    for (int i = 0; i < tasks.size(); i++) {
      taskIds.add(i);
    }
  }

  private TaskFragment createTask(int i) {
    int id = taskIds.get(i);
    TaskFragment taskFragment = new TaskFragment();
    Bundle args = new Bundle();
    args.putInt(TaskFragment.ID, id);
    args.putString(TaskFragment.NAME, tasks.get(id));
    args.putString(TaskFragment.COLOR, getColor(id));
    taskFragment.setArguments(args);
    return taskFragment;
  }

  @Override
  public Fragment getItem(int position) {
    Log.d(TAG, "getItem(" + position + ")");
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
    return createTask(taskPosition);
  }

  @Override
  public int getCount() {
    // One for each task and one for the list view.
    int result = taskIds.size() + 1;
    return result;
  }

  @Override
  public int getItemPosition(Object object) {
    int result = getItemPositionInner(object);
    String r = "" + result;
    if (result == POSITION_NONE) {
      r = "POSITION_NONE";
    }
    if (result == POSITION_UNCHANGED) {
      r = "POSITION_UNCHANGED";
    }
    Log.d(TAG, "getItemPosition(" + object + ") = " + r);
    return result;
  }

  public int getItemPositionInner(Object object) {
    if (object instanceof TaskFragment) {
      int i = taskIds.indexOf(((TaskFragment) object).getArguments().getInt(TaskFragment.ID));
      if (i == -1) {
        return POSITION_NONE;
      }
      return i + 1;
    }
    return 0;
  }

  private String getColor(int position) {
    return colors.get(position % colors.size());
  }

  /**
   * @param position of the page that was selected.
   * @return the position the pager should move to.
   */
  public int onPageSelected(int position) {
    if (position == 0) {
      // The task list.
      return position;
    }
    if (position == 1) {
      // The first task.
      return position;
    }
    if (position == 2) {
      // We have completed the task at position 1, so we need to remove it.
      taskIds.remove(0);

      notifyDataSetChanged();
      return 1;
    }
    throw new IllegalArgumentException("User shouldn't be able to navigate past the second item");
  }
}
