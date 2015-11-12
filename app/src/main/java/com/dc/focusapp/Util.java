package com.dc.focusapp;

import android.content.Context;
import android.graphics.Typeface;

public class Util {
  static private Typeface typeface;
  public static Typeface getTypeface(Context context) {
    if (typeface == null) {
      typeface = Typeface.createFromAsset(context.getAssets(), "RobotoCondensed-LightItalic.ttf");
    }
    return typeface;
  }
}
