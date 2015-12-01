package com.dc.focusapp;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class TaskFragment extends Fragment {
  public static final String ID = "ID";
  public static final String NAME = "NAME";
  public static final String COLOR = "COLOR";
  private static final String TAG = "TaskFragment";

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.task, container, false);
    Bundle args = getArguments();
    TextView textView = (TextView) view.findViewById(R.id.task_name);
    textView.setText(args.getString(NAME));
    textView.setBackgroundColor(Color.parseColor(args.getString(COLOR)));
    textView.setTypeface(Util.getTypeface(getActivity()));
    return view;
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    Log.d(TAG, this.getArguments().getString(NAME) + ".onCreate()");
    super.onCreate(savedInstanceState);
  }

  @Override
  public void onDetach() {
    Log.d(TAG, this.getArguments().getString(NAME) + ".onDetach()");
    super.onDetach();
  }

  @Override
  public void onDestroy() {
    Log.d(TAG, this.getArguments().getString(NAME) + ".onDestroy()");
    super.onDestroy();
  }

  @Override
  public String toString() {
    return super.toString() + "[" + this.getArguments().getString(NAME) + "]";
  }
}
