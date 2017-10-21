package com.touchteach.touchteach.coustomViews.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.widget.EditText;
import android.widget.TextView;

import com.touchteach.touchteach.R;

/**
 * Created by sazgar on 10/15/2017.
 */

public final class InputTextDialog extends AlertDialog {

    public InputTextDialog(@NonNull final Context context, final OnTextSetListener onTextSetListener) {
        super(context);
        setTitle("title");
        setView(getLayoutInflater().inflate(R.layout.input_text_dialog_layout, null));

        setButton(BUTTON_POSITIVE, "ثبت", new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                EditText editText = (EditText) findViewById(R.id.input_text_dialog_ed_text);
                onTextSetListener.onTextSet(editText, editText.getText().toString());
            }
        });

        setButton(BUTTON_NEGATIVE, "صرف نظر", new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
    }

    public interface OnTextSetListener{
        void onTextSet(EditText editText, String text);
    }
}
