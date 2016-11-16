package com.example.gym.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.gym.R;
import com.example.gym.database.RealmController;
import com.example.gym.model.Exercise;
import com.example.gym.model.ExerciseFollowDay;
import com.example.gym.model.VDetailExerciseForDay;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import lecho.lib.hellocharts.listener.ColumnChartOnValueSelectListener;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.ColumnChartView;

/**
 * Created by DefaultAccount on 10/23/2016.
 */
public class Statistical extends Fragment {
    private static final int DEFAULT_DATA = 0;

    private ColumnChartView chart;
    private ColumnChartData data;
    private boolean hasAxes = true;
    private boolean hasAxesNames = true;
    private boolean hasLabels = false;
    private boolean hasLabelForSelected = false;
    private Realm mRealm;

    public Statistical() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View rootView = inflater.inflate(R.layout.statistical, container, false);

        chart = (ColumnChartView) rootView.findViewById(R.id.chart);
        chart.setOnValueTouchListener(new ValueTouchListener());
        mRealm= RealmController.with(this).getRealm();
        generateDefaultData();

        return rootView;
    }
/*private void reset() {
        hasAxes = true;
        hasAxesNames = true;
        hasLabels = false;
        hasLabelForSelected = false;
        dataType = DEFAULT_DATA;
        chart.setValueSelectionEnabled(hasLabelForSelected);

    }*/


    private void generateDefaultData() {
        int numSubColumns = 1;
        ArrayList<ExerciseFollowDay> exerciseFollow7Days=new ArrayList<>(mRealm.where(ExerciseFollowDay.class).findAllSorted("listExerciseFollowDayId",false));
        List<Column> columns = new ArrayList<>();
        List<SubcolumnValue> values;
        for (int i = 0; i < 7; i++) {
            values = new ArrayList<>();
            int giatri=0;
            if(exerciseFollow7Days.size()>i) {
                ArrayList<VDetailExerciseForDay> dsbaitap = new ArrayList<>(mRealm.where(VDetailExerciseForDay.class).equalTo("listExerciseFollowDayId", exerciseFollow7Days.get(i).getListExerciseFollowDayId()).findAll());
                for (int e = 0; e < dsbaitap.size(); e++) {
                    if (dsbaitap.get(e).isDone()) {
                        Exercise bt = mRealm.where(Exercise.class).equalTo("exerciseId", dsbaitap.get(e).getExerciseId()).findFirst();
                        giatri += bt.getEnergy();
                    }
                }
            }
            float dienvo=(float)(giatri*0.02);
            for (int j = 0; j < numSubColumns; ++j) {

                values.add(new SubcolumnValue((dienvo), ChartUtils.pickColor()));
            }

            Column column = new Column(values);
            column.setHasLabels(hasLabels);
            column.setHasLabelsOnlyForSelected(hasLabelForSelected);
            columns.add(column);
        }

        data = new ColumnChartData(columns);

        if (hasAxes) {
            Axis axisX = new Axis();
            Axis axisY = new Axis().setHasLines(true);
            if (hasAxesNames) {
                axisX.setHasLines(false);
                axisX.setTextSize(22);
                axisX.setName("7 ngày gần nhấṭ");
                axisY.setHasLines(false);
                axisY.setTextSize(22);
                axisY.setName("Năng Lượng tiêu thụ");
            }
            data.setAxisXBottom(axisX);
            data.setAxisYLeft(axisY);
        } else {
            data.setAxisXBottom(null);
            data.setAxisYLeft(null);
        }
        chart.setColumnChartData(data);
        final Viewport v = new Viewport(chart.getMaximumViewport());
        v.left=-0.6f;
        v.right=7.6f;
        v.bottom = 0;
        v.top = 140;//a little more than 200 to keep labels visible
        chart.setMaximumViewport(v);
        chart.setCurrentViewport(v);
        //Optional step: disable viewport recalculations, thanks to this animations will not change viewport automatically.
        chart.setViewportCalculationEnabled(false);
        chart.setColumnChartData(data);

    }
    private class ValueTouchListener implements ColumnChartOnValueSelectListener {

        @Override
        public void onValueSelected(int columnIndex, int subcolumnIndex, SubcolumnValue value) {
            Toast.makeText(getActivity(), "Selected: " + value, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onValueDeselected() {
            // TODO Auto-generated method stub

        }

    }

}

