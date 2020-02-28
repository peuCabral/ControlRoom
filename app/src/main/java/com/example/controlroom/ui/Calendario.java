package com.example.controlroom.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.DatePicker;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.builders.DatePickerBuilder;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;
import com.example.controlroom.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Calendario extends Fragment {
    private View view;
    private CalendarView calendarView;

    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_calendario, container, false);

         calendarView = (CalendarView) view.findViewById(R.id.calendarView);

        final List<Calendar> calendars = new ArrayList<>();
        calendarView.setSelectedDates(calendars);

         calendarView.setOnDayClickListener(new OnDayClickListener() {
             @Override
             public void onDayClick(EventDay eventDay) {

                 Calendar clickedDayCalendar = eventDay.getCalendar();

               //  DatePickerBuilder builder = new DatePickerBuilder(this, calendar)
                        // .pickerType(CalendarView.ONE_DAY_PICKER);

                 //DatePicker datePicker = builder.build();
                 //datePicker.show();




             }
         });
        return view;

    }
}
