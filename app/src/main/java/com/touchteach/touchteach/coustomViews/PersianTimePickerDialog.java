package com.touchteach.touchteach.coustomViews;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by sazgar on 9/27/2017.
 */

public final class PersianTimePickerDialog extends TimePickerDialog {

    public PersianTimePickerDialog(Context context, OnTimeSetListener listener) {
        // todo set time without get it
        super(context, listener, 0, 30, true);
        setButton(DialogInterface.BUTTON_POSITIVE, "ثبت", this);
        setButton(DialogInterface.BUTTON_NEGATIVE, "صرف نظر", this);
        setTitle("تنظیم زمان");
    }
}
