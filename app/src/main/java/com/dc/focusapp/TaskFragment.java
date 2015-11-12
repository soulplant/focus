package com.dc.focusapp;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class TaskFragment extends Fragment {
  public static final String NAME = "NAME";
  public static final String COLOR = "COLOR";

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
}
