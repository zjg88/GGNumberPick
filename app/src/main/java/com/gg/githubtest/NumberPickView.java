package com.gg.githubtest;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.TextView;

/**
 * Created by GG on 2016/7/1.
 */
public class NumberPickView implements NumberPicker.OnValueChangeListener, NumberPicker.OnScrollListener,
        NumberPicker.Formatter {

    //下面的curriculumNPFrom和curriculumNPTo只要变成static那么就变成了有记忆效果的curriculumNumber
    private TextView textView;
    private static int curriculumNPFrom = 6;
    private static int curriculumNPTo   = 6;
    private Context context;

    public NumberPickView(Context context, TextView textView) {
        this.textView = textView;
        this.context = context;
        init();
    }


    //初始化NumberPick
    public void init() {
        //AlertDialog布局
        final AlertDialog dialog = new AlertDialog.Builder(context).create();
        View view = View.inflate(context, R.layout.curriculum_number_pick, null);
        dialog.show();
        dialog.getWindow().setContentView(view);

        NumberPicker npFrom = (NumberPicker) view.findViewById(R.id.np_from);
        NumberPicker npTo = (NumberPicker) view.findViewById(R.id.np_to);
        if (npFrom != null) {
            npFrom.setMaxValue(12);
            npFrom.setValue(curriculumNPFrom);
            npFrom.setMinValue(1);
            npFrom.setFormatter(this);
            npFrom.setOnValueChangedListener(this);
            npFrom.setOnScrollListener(this);
            npFrom.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
            npFrom.setWrapSelectorWheel(false);
        }
        if (npTo != null) {
            npTo.setMaxValue(12);
            npTo.setValue(curriculumNPTo);
            npTo.setMinValue(1);
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

                //  System.out.println("从:" + curriculumNPFrom + "到:" + curriculumNPTo);
                //调用接口
                textView.setText(curriculumNPFrom + "-" + curriculumNPTo);
                //不使用接口的时候一直没有数据返回式因为一旦cancel了那么数据就会失效了，所以你再使用getCurriculumNPFrom返回总是6
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

    OnNumberChangeListener onNumberChangeListener;

    public void setOnNumberPickListener(OnNumberChangeListener onNumberChangeListener) {
        this.onNumberChangeListener = onNumberChangeListener;
        onNumberChangeListener.onNumberPicked(curriculumNPFrom, curriculumNPTo);
    }

    public interface OnNumberChangeListener {
        void onNumberPicked(int from, int to);
    }


    @Override
    public void onScrollStateChange(NumberPicker view, int scrollState) {
        switch (scrollState) {
            case SCROLL_STATE_FLING:
                //  System.out.println("后续滑动");
                break;
            case SCROLL_STATE_IDLE:
                //  System.out.println("停止状态");
                break;
            case SCROLL_STATE_TOUCH_SCROLL:
                //  System.out.println("滑动中");
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
