package com.example.napoleonkaiser.weatherforecast.Utils;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.napoleonkaiser.weatherforecast.R;

/**
 * Created by napoleonkaiser on 30/06/2017.
 */

public class CustomView extends LinearLayout {
    public CustomView(Context context) {
        super(context);
        initialize();
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize();
    }

    private void initialize() {
        ViewGroup viewGroup = (ViewGroup) inflate(getContext(), R.layout.custom_view, this);

    }
}
