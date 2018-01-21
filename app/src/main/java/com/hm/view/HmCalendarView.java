package com.hm.view;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hm.customcalendar.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 2018/1/20/020.
 */

public class HmCalendarView extends LinearLayout implements View.OnClickListener {
    /**
     * headerView
     */
    private View mCalendarHeaderView;
    /**
     * weekView
     */
    private View mCalendarWeekView;
    /**
     * 具体日期view
     */
    private GridView mCalendarGridView;

    private TextView mTitleTv;
    private Button mPreBtn;
    private Button mNextBtn;
    private Button mPreYearBtn;
    private Button mNextYearBtn;
    private Context mContext;
    private Calendar nowCalendar = Calendar.getInstance();
    private String dateFormat = " yyyy年MM月dd日";

    public HmCalendarView(Context context) {
        this(context, null);
    }

    public HmCalendarView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HmCalendarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        bindView();
        bindViewEvent();
        bindData();
    }

    private void bindData() {
        mTitleTv.setText(new SimpleDateFormat(dateFormat).format(nowCalendar.getTime()));

        Calendar calendar = (Calendar) nowCalendar.clone();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        int preDate = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        calendar.add(Calendar.DAY_OF_MONTH, -preDate);
        ArrayList<Date> dateList = new ArrayList<>();
        int countDateOfMonth = nowCalendar.getActualMaximum(Calendar.DATE);
        int rowCount = (countDateOfMonth + preDate) % 7 == 0 ?
                (countDateOfMonth + preDate) / 7
                : (countDateOfMonth + preDate) / 7 + 1;
        int maxDateCount = rowCount * 7;
        while (dateList.size() < maxDateCount) {
            dateList.add(calendar.getTime());
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
        MyDateAdapter myDateAdapter = new MyDateAdapter(mContext, dateList, nowCalendar);
        mCalendarGridView.setAdapter(myDateAdapter);

    }

    private void bindViewEvent() {
        mPreBtn.setOnClickListener(this);
        mNextBtn.setOnClickListener(this);
        mPreYearBtn.setOnClickListener(this);
        mNextYearBtn.setOnClickListener(this);
    }

    private void bindView() {
        LayoutInflater mInFlater = LayoutInflater.from(mContext);
        mInFlater.inflate(R.layout.layout_calendar_view, this, true);
        mCalendarHeaderView = findViewById(R.id.calendar_hearder_rl);
        mCalendarWeekView = findViewById(R.id.calendar_week_ll);
        mCalendarGridView = (GridView) findViewById(R.id.calendar_gridview);
        mTitleTv = (TextView) mCalendarHeaderView.findViewById(R.id.calendar_title_tv);
        mPreBtn = (Button) mCalendarHeaderView.findViewById(R.id.calendar_premonth_btn);
        mNextBtn = (Button) mCalendarHeaderView.findViewById(R.id.calendar_nextmonth_btn);
        mPreYearBtn = (Button) mCalendarHeaderView.findViewById(R.id.calendar_preyear_btn);
        mNextYearBtn = (Button) mCalendarHeaderView.findViewById(R.id.calendar_nextyear_btn);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.calendar_premonth_btn) {
            nowCalendar.add(Calendar.MONTH, -1);
            bindData();

        } else if (v.getId() == R.id.calendar_nextmonth_btn) {
            nowCalendar.add(Calendar.MONTH, 1);
            bindData();
        } else if (v.getId() == R.id.calendar_preyear_btn) {
            nowCalendar.add(Calendar.YEAR, -1);
            bindData();
        } else if (v.getId() == R.id.calendar_nextyear_btn) {
            nowCalendar.add(Calendar.YEAR, 1);
            bindData();
        }

    }

    class MyDateAdapter extends ArrayAdapter<Date> {
        private Context mContext;
        private Calendar mNowCalendar;

        public MyDateAdapter(Context context, ArrayList<Date> dateList, Calendar calendar) {
            super(context, R.layout.item_calendar_tv, dateList);
            mContext = context;
            mNowCalendar = calendar;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(mContext).inflate(R.layout.item_calendar_tv, parent, false);
            }
            Date date = getItem(position);
            TextView textView = (TextView) convertView;
            textView.setText(date.getDate() + "");
            Date nowDate = mNowCalendar.getTime();
            if (date.getMonth() == nowDate.getMonth()) {
                textView.setTextColor(Color.BLACK);
            } else {
                textView.setTextColor(Color.parseColor("#666666"));
            }
            nowDate = new Date();
            if (date.getDate() == nowDate.getDate()
                    && date.getMonth() == nowDate.getMonth()
                    && date.getYear() == nowDate.getYear()) {
                textView.setBackgroundColor(Color.parseColor("#FFBB00"));
                textView.setTextColor(Color.WHITE);
            }


            return convertView;
        }
    }

}
