package com.touchteach.touchteach.coustomViews.dialogs;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by sazgar on 9/27/2017.
 */

public final class PersianDatePickerDialog extends DatePickerDialog {

    public PersianDatePickerDialog(@NonNull Context context, @Nullable OnDateSetListener listener) {
        //todo set current date
        super(context, listener, 2017, 1, 1);
        setTitle("تنظیم تاریخ");
        setButton(DialogInterface.BUTTON_POSITIVE, "ثبت", this);
        setButton(DialogInterface.BUTTON_NEGATIVE, "صرف نظر", this);

    }
}
