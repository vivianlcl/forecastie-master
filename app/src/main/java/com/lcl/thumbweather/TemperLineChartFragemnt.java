package com.lcl.thumbweather;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import com.lcl.thumbweather.R;

import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
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
    private String dateString;
    private Context context;
    private Weather weatherItem;
    private Date todayDate;

    private int i = 0;


    public final static String[] days = new String[] { "Mon", "Tue", "Wen",
            "Thu", "Fri", "Sat", "Sun", };

    private Bundle bundle;
    public TemperLineChartFragemnt(Context context,Weather weatherItem){
            this.context = context;
            this.weatherItem = weatherItem;
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
                i = getI();
                lineChartView.setLineChartData(null);
                temper = bundle.getParcelableArrayList("list");
                lines = initLine(temper);
                data = initData(lines,i);
                lineChartView.setLineChartData(data);
                Viewport viewport = initViewPort();
                lineChartView.setMaximumViewport(viewport);
                lineChartView.setCurrentViewport(viewport);
                lineChartView.setZoomType(ZoomType.HORIZONTAL);
            }
        }).start();
        return view;
    }

    private Viewport initViewPort(){
        Log.e("-LCL-", "initViewPort");
        Viewport viewport = new Viewport(0, 50, 6, 0);

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
                    pointHighValueList.add(new PointValue(i,highTemper-273.15f));
                    pointLowValueList.add(new PointValue(i,lowTemper-273.15f));


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

    public LineChartData initData(List<Line> lines,int j){
        Log.e("-LCL-", "initData");
        LineChartData data = new LineChartData(lines);
        int numValues = 7;
        List<AxisValue> axisValues = new ArrayList<AxisValue>();
        for (int i = 0; i < numValues; ++i) {
            axisValues.add(new AxisValue(i).setLabel(days[(i+j)%7]));
        }
//        axisX.setName("Date");
//        axisY.setName("Temperature");
//
//        data.setAxisYLeft(axisY);
//        data.setAxisXBottom(axisX);
        data.setAxisXBottom(new Axis(axisValues).setHasLines(false));
        data.setAxisYLeft(new Axis().setHasLines(false)
                .setMaxLabelChars(3));

        data.setValueLabelTextSize(10);
        data.setValueLabelsTextColor(Color.WHITE);
        data.setValueLabelBackgroundEnabled(false);
        data.setValueLabelTypeface(Typeface.MONOSPACE);

        return data;
    }

    public int getI(){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        String defaultDateFormat = context.getResources().getStringArray(R.array.dateFormatsValues)[0];
        String dateFormat = sp.getString("dateFormat", defaultDateFormat);
        if ("custom".equals(dateFormat)) {
            dateFormat = sp.getString("dateFormatCustom", defaultDateFormat);
        }

        TimeZone tz = TimeZone.getDefault();

        try {
            SimpleDateFormat resultFormat = new SimpleDateFormat(dateFormat);
            resultFormat.setTimeZone(tz);
            dateString = resultFormat.format(weatherItem.getDate());
        } catch (IllegalArgumentException e) {
            dateString = context.getResources().getString(R.string.error_dateFormat);
        }
        i = 0;
        Log.d("-LCL-","dateString="+dateString);
        if (dateString.contains("周二")){
            i = 1;
        }else if (dateString.contains("周三")){
            i = 2;
        }else if (dateString.contains("周四")){
            i = 3;

        }else if (dateString.contains("周五")){
            i = 4;

        }else if (dateString.contains("周六")){
            i = 5;

        }else if (dateString.contains("周日")){
            i = 6;

        }else {
            //do nothing
        }

        return i;
    }

}

