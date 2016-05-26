package com.lcl.thumbweather;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import com.lcl.thumbweather.R;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.LineChartView;

/**
 * Created by Administrator on 2016/5/16.
 */
public class TemperLineChartFragemnt extends Fragment{
    protected LineChartView lineChartView;
    protected LineChartData data ;
    protected List<Line> lines;
    private ArrayList temper;

    private Bundle bundle;
    public TemperLineChartFragemnt(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.e("-LCL-", "TemperLineChartFragemnt:onCreateView");
        View view = inflater.inflate(R.layout.linear_chart, container, false);
        lineChartView = (LineChartView)view.findViewById(R.id.temper_line_chart_view);
        bundle = this.getArguments();
        new Thread(new Runnable() {
            @Override
            public void run() {
                lineChartView.setLineChartData(null);
                temper = bundle.getParcelableArrayList("list");
                lines = initLine(temper);
                data = initData(lines);
                lineChartView.setLineChartData(data);
                Viewport viewport = initViewPort();
                lineChartView.setMaximumViewport(viewport);
                lineChartView.setCurrentViewport(viewport);
            }
        }).start();
        return view;
    }

    private Viewport initViewPort(){
        Log.e("-LCL-", "initViewPort");
        Viewport viewport = new Viewport();
        viewport.top = 40f;
        viewport.bottom = 0f;
        viewport.left = 0;
        viewport.right =6;

        return viewport;
    }

    private List<Line> initLine(ArrayList temper){
        Log.e("-LCL-","initLine");
        List<Line> lineList = new ArrayList<>();
        List<PointValue> pointHighValueList = new ArrayList<>();
        List<PointValue> pointLowValueList = new ArrayList<>();
        List<Temper> temperlist = null;
        for (int i = 0;i<temper.size();i++){
            temperlist = (List<Temper>)temper.get(i);
            if (temperlist.size()>0){
                    Float highTemper = Float.parseFloat(temperlist.get(0).getHighTemper0());
                    Float lowTemper = Float.parseFloat(temperlist.get(0).getLowTemper0());
                    for (int j = 1;j<temperlist.size();j++){
                        highTemper = highTemper>Float.parseFloat(temperlist.get(j).getHighTemper0())?highTemper:Float.parseFloat(temperlist.get(j).getHighTemper0());
                        lowTemper = lowTemper<Float.parseFloat(temperlist.get(j).getLowTemper0())?lowTemper:Float.parseFloat(temperlist.get(j).getLowTemper0());
                    }
                    pointHighValueList.add(new PointValue(i+1,highTemper-273.15f));
                    pointLowValueList.add(new PointValue(i+1,lowTemper-273.15f));


            }

        }

        Line line1 = new Line(pointHighValueList);
        Line line2 = new Line(pointLowValueList);
        line1.setColor(Color.RED);
        line1.setShape(ValueShape.CIRCLE);
        line1.setHasLabels(true);
        line1.setStrokeWidth(1);

        line2.setColor(getResources().getColor(R.color.colorPrimary));
        line2.setShape(ValueShape.CIRCLE);
        line2.setHasLabels(true);
        line2.setStrokeWidth(1);

        lineList.add(line1);
        lineList.add(line2);
        return lineList;
    }

    public LineChartData initData(List<Line> lines){
        Log.e("-LCL-","initData");
        LineChartData data = new LineChartData(lines);

        Axis axisX = new Axis();
        Axis axisY = new Axis();
        axisX.setTextColor(Color.WHITE);
        axisY.setTextColor(Color.WHITE);

        //axisX.setInside(true);
        //axisX.setName("Date");
        //axisY.setName("Temperature");

        data.setAxisYLeft(axisY);
        data.setAxisXBottom(axisX);
        data.setValueLabelTextSize(10);
        data.setValueLabelsTextColor(Color.WHITE);
        data.setValueLabelBackgroundEnabled(false);
        data.setValueLabelTypeface(Typeface.MONOSPACE);

        return data;
    }

}

