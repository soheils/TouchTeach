package com.touchteach.touchteach.coustomViews;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;

/**
 * Created by sazgar on 11/10/2017.
 */

public abstract class Utiles {
    public static int dpToPx(int dp, Context context){
        Resources r = context.getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}
