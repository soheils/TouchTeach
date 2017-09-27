package coustomViews;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;

/**
 * Created by sazgar on 9/27/2017.
 */

public final class PersianDatePickerDialog extends DatePickerDialog {
    @RequiresApi(api = Build.VERSION_CODES.N)
    public PersianDatePickerDialog(@NonNull Context context) {
        super(context);
    }
}
