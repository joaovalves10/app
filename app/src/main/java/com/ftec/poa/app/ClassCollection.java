package com.ftec.poa.app;

import android.content.Context;
import android.text.InputFilter;
import android.text.Spanned;

public class ClassCollection {
    Context context;
    public ClassCollection(Context c){
        this.context = c;
    }
    public InputFilter getAlphaNumericFilter(){
        InputFilter filter = new InputFilter() {
            public CharSequence filter(CharSequence source, int start, int end,
                                       Spanned dest, int dstart, int dend) {
                for (int i = start; i < end; i++) {
                    if (!Character.isLetterOrDigit(source.charAt(i))) {
                        return "";
                    }
                }
                return null;
            }

        };
        return filter;
    }
}
