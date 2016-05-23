package cz.martykan.forecastie;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/5/16.
 */
public class Temper implements Parcelable{
    private int day;
    private String highTemper0;
    private String lowTemper0;


    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getHighTemper0() {
        return highTemper0;
    }

    public void setHighTemper0(String highTemper0) {
        this.highTemper0 = highTemper0;
    }

    public String getLowTemper0() {
        return lowTemper0;
    }

    public void setLowTemper0(String lowTemper0) {
        this.lowTemper0 = lowTemper0;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(day);
        dest.writeString(highTemper0);
        dest.writeString(lowTemper0);
    }

    public static final Parcelable.Creator<Temper> CREATOR = new Creator<Temper>() {
        @Override
        public Temper createFromParcel(Parcel source) {
            Temper temper = new Temper();

            temper.setDay(source.readInt());
            temper.setHighTemper0(source.readString());
            temper.setLowTemper0(source.readString());
            return temper;
        }

        @Override
        public Temper[] newArray(int size) {
            return new Temper[size];
        }

    };
}
