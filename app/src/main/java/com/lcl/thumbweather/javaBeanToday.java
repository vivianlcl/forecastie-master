package com.lcl.thumbweather;

import java.util.List;

/**
 * Created by Administrator on 2016/5/26.
 */
public class javaBeanToday {

    /**
     * id : 1815286
     * dt : 1464328800
     * clouds : {"all":40}
     * coord : {"lon":104.07,"lat":30.67}
     * wind : {"speed":2,"deg":260}
     * cod : 200
     * visibility : 10000
     * sys : {"message":0.0141,"id":7461,"sunset":1464350350,"sunrise":1464300177,"type":1,"country":"CN"}
     * name : Chengdu
     * base : stations
     * weather : [{"id":802,"icon":"03d","description":"多云","main":"Clouds"}]
     * main : {"humidity":60,"pressure":1012,"temp_max":293.15,"temp_min":293.15,"temp":293.15}
     */
    private int id;
    private int dt;
    private CloudsEntity clouds;
    private CoordEntity coord;
    private WindEntity wind;
    private int cod;
    private int visibility;
    private SysEntity sys;
    private String name;
    private String base;
    private List<WeatherEntity> weather;
    private MainEntity main;
    private String rain;
    private String snow;

    public String getRain() {
        return rain;
    }

    public void setRain(String rain) {
        this.rain = rain;
    }

    public String getSnow() {
        return snow;
    }

    public void setSnow(String snow) {
        this.snow = snow;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDt(int dt) {
        this.dt = dt;
    }

    public void setClouds(CloudsEntity clouds) {
        this.clouds = clouds;
    }

    public void setCoord(CoordEntity coord) {
        this.coord = coord;
    }

    public void setWind(WindEntity wind) {
        this.wind = wind;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }

    public void setSys(SysEntity sys) {
        this.sys = sys;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public void setWeather(List<WeatherEntity> weather) {
        this.weather = weather;
    }

    public void setMain(MainEntity main) {
        this.main = main;
    }

    public int getId() {
        return id;
    }

    public int getDt() {
        return dt;
    }

    public CloudsEntity getClouds() {
        return clouds;
    }

    public CoordEntity getCoord() {
        return coord;
    }

    public WindEntity getWind() {
        return wind;
    }

    public int getCod() {
        return cod;
    }

    public int getVisibility() {
        return visibility;
    }

    public SysEntity getSys() {
        return sys;
    }

    public String getName() {
        return name;
    }

    public String getBase() {
        return base;
    }

    public List<WeatherEntity> getWeather() {
        return weather;
    }

    public MainEntity getMain() {
        return main;
    }

    public class CloudsEntity {
        /**
         * all : 40
         */
        private int all;

        public void setAll(int all) {
            this.all = all;
        }

        public int getAll() {
            return all;
        }
    }

    public class CoordEntity {
        /**
         * lon : 104.07
         * lat : 30.67
         */
        private double lon;
        private double lat;

        public void setLon(double lon) {
            this.lon = lon;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }

        public double getLon() {
            return lon;
        }

        public double getLat() {
            return lat;
        }
    }

    public class WindEntity {
        /**
         * speed : 2
         * deg : 260
         */
        private int speed;
        private int deg;

        public void setSpeed(int speed) {
            this.speed = speed;
        }

        public void setDeg(int deg) {
            this.deg = deg;
        }

        public int getSpeed() {
            return speed;
        }

        public int getDeg() {
            return deg;
        }
    }

    public class SysEntity {
        /**
         * message : 0.0141
         * id : 7461
         * sunset : 1464350350
         * sunrise : 1464300177
         * type : 1
         * country : CN
         */
        private double message;
        private int id;
        private int sunset;
        private int sunrise;
        private int type;
        private String country;

        public void setMessage(double message) {
            this.message = message;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setSunset(int sunset) {
            this.sunset = sunset;
        }

        public void setSunrise(int sunrise) {
            this.sunrise = sunrise;
        }

        public void setType(int type) {
            this.type = type;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public double getMessage() {
            return message;
        }

        public int getId() {
            return id;
        }

        public int getSunset() {
            return sunset;
        }

        public int getSunrise() {
            return sunrise;
        }

        public int getType() {
            return type;
        }

        public String getCountry() {
            return country;
        }
    }

    public class WeatherEntity {
        /**
         * id : 802
         * icon : 03d
         * description : 多云
         * main : Clouds
         */
        private int id;
        private String icon;
        private String description;
        private String main;

        public void setId(int id) {
            this.id = id;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public void setMain(String main) {
            this.main = main;
        }

        public int getId() {
            return id;
        }

        public String getIcon() {
            return icon;
        }

        public String getDescription() {
            return description;
        }

        public String getMain() {
            return main;
        }
    }

    public class MainEntity {
        /**
         * humidity : 60
         * pressure : 1012
         * temp_max : 293.15
         * temp_min : 293.15
         * temp : 293.15
         */
        private int humidity;
        private int pressure;
        private double temp_max;
        private double temp_min;
        private double temp;

        public void setHumidity(int humidity) {
            this.humidity = humidity;
        }

        public void setPressure(int pressure) {
            this.pressure = pressure;
        }

        public void setTemp_max(double temp_max) {
            this.temp_max = temp_max;
        }

        public void setTemp_min(double temp_min) {
            this.temp_min = temp_min;
        }

        public void setTemp(double temp) {
            this.temp = temp;
        }

        public int getHumidity() {
            return humidity;
        }

        public int getPressure() {
            return pressure;
        }

        public double getTemp_max() {
            return temp_max;
        }

        public double getTemp_min() {
            return temp_min;
        }

        public double getTemp() {
            return temp;
        }
    }
}

