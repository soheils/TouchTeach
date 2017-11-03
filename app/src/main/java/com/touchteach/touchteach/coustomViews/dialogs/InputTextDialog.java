package com.touchteach.touchteach.coustomViews.dialogs;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.InputType;
import android.widget.EditText;

import com.touchteach.touchteach.R;

/**
 * Created by sazgar on 10/15/2017.
 */

public final class InputTextDialog extends AlertDialog {

    private EditText editText;
    private boolean isOneLine = true;
    private String beforeString = "";

    public InputTextDialog(@NonNull final Context context, final OnTextSetListener onTextSetListener) {
        super(context);
        setTitle("title");
        setView(getLayoutInflater().inflate(R.layout.input_text_dialog_layout, null));


        setButton(BUTTON_POSITIVE, "ثبت", new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
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

    public void setOneLine (boolean oneLine){
        isOneLine = oneLine;
    }

    public void setBeforeString(String beforeString){
        this.beforeString = beforeString;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        editText = findViewById(R.id.input_text_dialog_ed_text);
        editText.setSingleLine(isOneLine);
        editText.setText(beforeString);
    }
}
