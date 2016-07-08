package com.gg.githubtest;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.TextView;

/*
 * Created by GG on 2016/7/1.
 */
public class NumberPickView implements NumberPicker.OnValueChangeListener, NumberPicker.OnScrollListener,
        NumberPicker.Formatter {

    private int fromMinValue;
    private int fromMaxValue;
    private int toMinValue;
    private int toMaxValue;
    //下面的curriculumNPFrom和curriculumNPTo只要变成static那么就变成了有记忆效果的curriculumNumber
    private int curriculumNPFrom = fromMaxValue / 2;
    private int curriculumNPTo   = toMaxValue / 2;
    private Context context;

    public NumberPickView(Context context) {
        this.context = context;

    }

    public void init() {
        final AlertDialog dialog = new AlertDialog.Builder(context).create();
        View view = View.inflate(context, R.layout.curriculum_number_pick, null);
        dialog.show();
        dialog.getWindow().setContentView(view);

        NumberPicker npFrom = (NumberPicker) view.findViewById(R.id.np_from);
        NumberPicker npTo = (NumberPicker) view.findViewById(R.id.np_to);
        if (npFrom != null) {
            npFrom.setMinValue(fromMinValue);
            npFrom.setMaxValue(fromMaxValue);
            npFrom.setValue(curriculumNPFrom);
            npFrom.setFormatter(this);
            npFrom.setOnValueChangedListener(this);
            npFrom.setOnScrollListener(this);
            npFrom.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
            npFrom.setWrapSelectorWheel(false);
        }
        if (npTo != null) {
            npTo.setMinValue(toMinValue);
            npTo.setMaxValue(toMaxValue);
            npTo.setValue(curriculumNPTo);
            npTo.setFormatter(this);
            npTo.setOnValueChangedListener(this);
            npTo.setOnScrollListener(this);
            npTo.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
            npTo.setWrapSelectorWheel(false);
        }


        TextView cancel = (TextView) view.findViewById(R.id.tv_curriculum_number_cancel);
        TextView confirm = (TextView) view.findViewById(R.id.tv_curriculum_number_confirm);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNumberChangeListener.onNumberPicked(curriculumNPFrom, curriculumNPTo);
                dialog.cancel();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
    }

    public void SetMinFromValue(int value) {
        fromMinValue = value;
    }

    public void SetMaxFromValue(int value) {
        fromMaxValue = value;
    }

    public void SetMinToValue(int value) {
        toMinValue = value;
    }

    public void SetMaxToValue(int value) {
        toMaxValue = value;
        init();
    }

    public interface OnNumberChangeListener {
        void onNumberPicked(int from, int to);
    }

    OnNumberChangeListener onNumberChangeListener;

    public void setOnNumberPickListener(OnNumberChangeListener onNumberChangeListener) {
        this.onNumberChangeListener = onNumberChangeListener;
    }


    @Override
    public void onScrollStateChange(NumberPicker view, int scrollState) {
        switch (scrollState) {
            case SCROLL_STATE_FLING:
                break;
            case SCROLL_STATE_IDLE:
                break;
            case SCROLL_STATE_TOUCH_SCROLL:
                break;
        }
    }


    @Override
    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
        switch (picker.getId()) {
            case R.id.np_from:
                if (picker.getValue() > curriculumNPTo) {
                    picker.setValue(curriculumNPTo);
                }
                curriculumNPFrom = picker.getValue();
                break;
            case R.id.np_to:
                if (picker.getValue() < curriculumNPFrom) {
                    picker.setValue(curriculumNPFrom);

                }
                curriculumNPTo = picker.getValue();
                break;
        }
    }

    @Override
    public String format(int value) {
        String tmpStr = String.valueOf(value);
        if (value < 10) {
            tmpStr = "0" + tmpStr;
        }
        return tmpStr;
    }


}
