package com.lcl.thumbweather;

import java.util.List;

/**
 * Created by Administrator on 2016/5/17.
 */
public class javaBean {

    /**
     * message : 0.0176
     * cnt : 37
     * cod : 200
     * list : [{"clouds":{"all":36},"dt":1463475600,"wind":{"speed":1.66,"deg":42.0002},"sys":{"pod":"d"},"weather":[{"id":802,"icon":"03d","description":"多云","main":"Clouds"}],"dt_txt":"2016-05-17 09:00:00","main":{"temp_kf":1.05,"humidity":52,"pressure":960.09,"temp_max":299.43,"sea_level":1018.8,"temp_min":298.386,"temp":299.43,"grnd_level":960.09}},{"clouds":{"all":64},"dt":1463486400,"wind":{"speed":1.15,"deg":346.503},"sys":{"pod":"n"},"weather":[{"id":803,"icon":"04n","description":"多云","main":"Clouds"}],"dt_txt":"2016-05-17 12:00:00","main":{"temp_kf":0.78,"humidity":50,"pressure":961.39,"temp_max":296.42,"sea_level":1020.34,"temp_min":295.638,"temp":296.42,"grnd_level":961.39}},{"clouds":{"all":80},"dt":1463497200,"wind":{"speed":1.4,"deg":343.001},"sys":{"pod":"n"},"weather":[{"id":803,"icon":"04n","description":"多云","main":"Clouds"}],"dt_txt":"2016-05-17 15:00:00","main":{"temp_kf":0.52,"humidity":56,"pressure":962.85,"temp_max":293.47,"sea_level":1022.32,"temp_min":292.95,"temp":293.47,"grnd_level":962.85}},{"clouds":{"all":88},"dt":1463508000,"wind":{"speed":1.2,"deg":343.007},"sys":{"pod":"n"},"weather":[{"id":804,"icon":"04n","description":"阴，多云","main":"Clouds"}],"dt_txt":"2016-05-17 18:00:00","main":{"temp_kf":0.26,"humidity":64,"pressure":962.31,"temp_max":291.76,"sea_level":1021.81,"temp_min":291.499,"temp":291.76,"grnd_level":962.31}},{"clouds":{"all":76},"dt":1463518800,"wind":{"speed":2.22,"deg":339.503},"sys":{"pod":"n"},"weather":[{"id":803,"icon":"04n","description":"多云","main":"Clouds"}],"dt_txt":"2016-05-17 21:00:00","main":{"temp_kf":0,"humidity":67,"pressure":961.86,"temp_max":290.63,"sea_level":1021.51,"temp_min":290.63,"temp":290.63,"grnd_level":961.86}},{"clouds":{"all":92},"dt":1463529600,"wind":{"speed":1.37,"deg":347.001},"sys":{"pod":"d"},"weather":[{"id":804,"icon":"04d","description":"阴，多云","main":"Clouds"}],"dt_txt":"2016-05-18 00:00:00","main":{"temp_kf":0,"humidity":66,"pressure":963.05,"temp_max":292.228,"sea_level":1022.47,"temp_min":292.228,"temp":292.228,"grnd_level":963.05}},{"clouds":{"all":64},"dt":1463540400,"wind":{"speed":1.88,"deg":18.5032},"sys":{"pod":"d"},"weather":[{"id":803,"icon":"04d","description":"多云","main":"Clouds"}],"dt_txt":"2016-05-18 03:00:00","main":{"temp_kf":0,"humidity":64,"pressure":963.08,"temp_max":296.494,"sea_level":1022.01,"temp_min":296.494,"temp":296.494,"grnd_level":963.08}},{"clouds":{"all":80},"dt":1463551200,"wind":{"speed":2.02,"deg":33.5007},"sys":{"pod":"d"},"weather":[{"id":803,"icon":"04d","description":"多云","main":"Clouds"}],"dt_txt":"2016-05-18 06:00:00","main":{"temp_kf":0,"humidity":59,"pressure":961.68,"temp_max":297.442,"sea_level":1020.31,"temp_min":297.442,"temp":297.442,"grnd_level":961.68}},{"clouds":{"all":92},"dt":1463562000,"wind":{"speed":2.01,"deg":17.504},"sys":{"pod":"d"},"weather":[{"id":804,"icon":"04d","description":"阴，多云","main":"Clouds"}],"dt_txt":"2016-05-18 09:00:00","main":{"temp_kf":0,"humidity":55,"pressure":960.48,"temp_max":297.362,"sea_level":1019.05,"temp_min":297.362,"temp":297.362,"grnd_level":960.48}},{"clouds":{"all":100},"dt":1463572800,"wind":{"speed":2.21,"deg":357},"sys":{"pod":"n"},"weather":[{"id":804,"icon":"04n","description":"阴，多云","main":"Clouds"}],"dt_txt":"2016-05-18 12:00:00","main":{"temp_kf":0,"humidity":55,"pressure":961.11,"temp_max":295.53,"sea_level":1020.11,"temp_min":295.53,"temp":295.53,"grnd_level":961.11}},{"clouds":{"all":92},"dt":1463583600,"wind":{"speed":2.66,"deg":348.501},"sys":{"pod":"n"},"weather":[{"id":500,"icon":"10n","description":"小雨","main":"Rain"}],"dt_txt":"2016-05-18 15:00:00","rain":{"3h":0.27},"main":{"temp_kf":0,"humidity":74,"pressure":963.7,"temp_max":292.748,"sea_level":1022.96,"temp_min":292.748,"temp":292.748,"grnd_level":963.7}},{"clouds":{"all":100},"dt":1463594400,"wind":{"speed":1.51,"deg":8.00031},"sys":{"pod":"n"},"weather":[{"id":500,"icon":"10n","description":"小雨","main":"Rain"}],"dt_txt":"2016-05-18 18:00:00","rain":{"3h":0.04},"main":{"temp_kf":0,"humidity":80,"pressure":963.2,"temp_max":291.569,"sea_level":1022.58,"temp_min":291.569,"temp":291.569,"grnd_level":963.2}},{"clouds":{"all":48},"dt":1463605200,"wind":{"speed":1.12,"deg":281.501},"sys":{"pod":"n"},"weather":[{"id":500,"icon":"10n","description":"小雨","main":"Rain"}],"dt_txt":"2016-05-18 21:00:00","rain":{"3h":0.06},"main":{"temp_kf":0,"humidity":86,"pressure":962.49,"temp_max":290.798,"sea_level":1022,"temp_min":290.798,"temp":290.798,"grnd_level":962.49}},{"clouds":{"all":20},"dt":1463616000,"wind":{"speed":1.51,"deg":99.0043},"sys":{"pod":"d"},"weather":[{"id":801,"icon":"02d","description":"晴，少云","main":"Clouds"}],"dt_txt":"2016-05-19 00:00:00","rain":{},"main":{"temp_kf":0,"humidity":85,"pressure":963.45,"temp_max":291.812,"sea_level":1022.86,"temp_min":291.812,"temp":291.812,"grnd_level":963.45}},{"clouds":{"all":0},"dt":1463626800,"wind":{"speed":0.66,"deg":124},"sys":{"pod":"d"},"weather":[{"id":800,"icon":"01d","description":"晴","main":"Clear"}],"dt_txt":"2016-05-19 03:00:00","rain":{},"main":{"temp_kf":0,"humidity":75,"pressure":963.72,"temp_max":296.373,"sea_level":1022.67,"temp_min":296.373,"temp":296.373,"grnd_level":963.72}},{"clouds":{"all":20},"dt":1463637600,"wind":{"speed":0.41,"deg":356.501},"sys":{"pod":"d"},"weather":[{"id":801,"icon":"02d","description":"晴，少云","main":"Clouds"}],"dt_txt":"2016-05-19 06:00:00","rain":{},"main":{"temp_kf":0,"humidity":65,"pressure":961.84,"temp_max":299.144,"sea_level":1020.61,"temp_min":299.144,"temp":299.144,"grnd_level":961.84}},{"clouds":{"all":32},"dt":1463648400,"wind":{"speed":1.98,"deg":6.50684},"sys":{"pod":"d"},"weather":[{"id":802,"icon":"03d","description":"多云","main":"Clouds"}],"dt_txt":"2016-05-19 09:00:00","rain":{},"main":{"temp_kf":0,"humidity":57,"pressure":960.04,"temp_max":299.681,"sea_level":1018.72,"temp_min":299.681,"temp":299.681,"grnd_level":960.04}},{"clouds":{"all":68},"dt":1463659200,"wind":{"speed":1.82,"deg":35.5021},"sys":{"pod":"n"},"weather":[{"id":803,"icon":"04n","description":"多云","main":"Clouds"}],"dt_txt":"2016-05-19 12:00:00","rain":{},"main":{"temp_kf":0,"humidity":56,"pressure":960.69,"temp_max":297.214,"sea_level":1019.66,"temp_min":297.214,"temp":297.214,"grnd_level":960.69}},{"clouds":{"all":56},"dt":1463670000,"wind":{"speed":2.4,"deg":71.0053},"sys":{"pod":"n"},"weather":[{"id":803,"icon":"04n","description":"多云","main":"Clouds"}],"dt_txt":"2016-05-19 15:00:00","rain":{},"main":{"temp_kf":0,"humidity":65,"pressure":962.17,"temp_max":294.1,"sea_level":1021.62,"temp_min":294.1,"temp":294.1,"grnd_level":962.17}},{"clouds":{"all":44},"dt":1463680800,"wind":{"speed":1.57,"deg":70.5037},"sys":{"pod":"n"},"weather":[{"id":802,"icon":"03n","description":"多云","main":"Clouds"}],"dt_txt":"2016-05-19 18:00:00","rain":{},"main":{"temp_kf":0,"humidity":79,"pressure":961.96,"temp_max":291.758,"sea_level":1021.42,"temp_min":291.758,"temp":291.758,"grnd_level":961.96}},{"clouds":{"all":68},"dt":1463691600,"wind":{"speed":1.47,"deg":20.5023},"sys":{"pod":"n"},"weather":[{"id":803,"icon":"04n","description":"多云","main":"Clouds"}],"dt_txt":"2016-05-19 21:00:00","rain":{},"main":{"temp_kf":0,"humidity":83,"pressure":961.87,"temp_max":291.013,"sea_level":1021.31,"temp_min":291.013,"temp":291.013,"grnd_level":961.87}},{"clouds":{"all":44},"dt":1463702400,"wind":{"speed":1.96,"deg":31.5061},"sys":{"pod":"d"},"weather":[{"id":802,"icon":"03d","description":"多云","main":"Clouds"}],"dt_txt":"2016-05-20 00:00:00","rain":{},"main":{"temp_kf":0,"humidity":79,"pressure":963.15,"temp_max":292.491,"sea_level":1022.5,"temp_min":292.491,"temp":292.491,"grnd_level":963.15}},{"clouds":{"all":0},"dt":1463713200,"wind":{"speed":1.46,"deg":78.5018},"sys":{"pod":"d"},"weather":[{"id":800,"icon":"01d","description":"晴","main":"Clear"}],"dt_txt":"2016-05-20 03:00:00","rain":{},"main":{"temp_kf":0,"humidity":74,"pressure":963.4,"temp_max":296.469,"sea_level":1022.14,"temp_min":296.469,"temp":296.469,"grnd_level":963.4}},{"clouds":{"all":0},"dt":1463724000,"wind":{"speed":0.51,"deg":293.501},"sys":{"pod":"d"},"weather":[{"id":800,"icon":"01d","description":"晴","main":"Clear"}],"dt_txt":"2016-05-20 06:00:00","rain":{},"main":{"temp_kf":0,"humidity":67,"pressure":961.15,"temp_max":299.649,"sea_level":1019.65,"temp_min":299.649,"temp":299.649,"grnd_level":961.15}},{"clouds":{"all":8},"dt":1463734800,"wind":{"speed":1.83,"deg":37.0054},"sys":{"pod":"d"},"weather":[{"id":800,"icon":"02d","description":"晴","main":"Clear"}],"dt_txt":"2016-05-20 09:00:00","rain":{},"main":{"temp_kf":0,"humidity":58,"pressure":959.18,"temp_max":300.849,"sea_level":1017.61,"temp_min":300.849,"temp":300.849,"grnd_level":959.18}},{"clouds":{"all":32},"dt":1463745600,"wind":{"speed":1.06,"deg":11.0023},"sys":{"pod":"n"},"weather":[{"id":802,"icon":"03n","description":"多云","main":"Clouds"}],"dt_txt":"2016-05-20 12:00:00","rain":{},"main":{"temp_kf":0,"humidity":55,"pressure":959.52,"temp_max":298.558,"sea_level":1018.21,"temp_min":298.558,"temp":298.558,"grnd_level":959.52}},{"clouds":{"all":76},"dt":1463756400,"wind":{"speed":1.26,"deg":45.501},"sys":{"pod":"n"},"weather":[{"id":803,"icon":"04n","description":"多云","main":"Clouds"}],"dt_txt":"2016-05-20 15:00:00","rain":{},"main":{"temp_kf":0,"humidity":62,"pressure":960.9,"temp_max":296.324,"sea_level":1020.18,"temp_min":296.324,"temp":296.324,"grnd_level":960.9}},{"clouds":{"all":76},"dt":1463767200,"wind":{"speed":0.67,"deg":71.5026},"sys":{"pod":"n"},"weather":[{"id":500,"icon":"10n","description":"小雨","main":"Rain"}],"dt_txt":"2016-05-20 18:00:00","rain":{"3h":0.09},"main":{"temp_kf":0,"humidity":72,"pressure":960.85,"temp_max":295.362,"sea_level":1020.06,"temp_min":295.362,"temp":295.362,"grnd_level":960.85}},{"clouds":{"all":76},"dt":1463778000,"wind":{"speed":1.21,"deg":353.001},"sys":{"pod":"n"},"weather":[{"id":500,"icon":"10n","description":"小雨","main":"Rain"}],"dt_txt":"2016-05-20 21:00:00","rain":{"3h":0.42},"main":{"temp_kf":0,"humidity":85,"pressure":960.73,"temp_max":293.534,"sea_level":1020.13,"temp_min":293.534,"temp":293.534,"grnd_level":960.73}},{"clouds":{"all":80},"dt":1463788800,"wind":{"speed":1.87,"deg":23.5002},"sys":{"pod":"d"},"weather":[{"id":500,"icon":"10d","description":"小雨","main":"Rain"}],"dt_txt":"2016-05-21 00:00:00","rain":{"3h":0.45},"main":{"temp_kf":0,"humidity":87,"pressure":962.08,"temp_max":293.637,"sea_level":1021.34,"temp_min":293.637,"temp":293.637,"grnd_level":962.08}},{"clouds":{"all":48},"dt":1463799600,"wind":{"speed":1.97,"deg":47.5017},"sys":{"pod":"d"},"weather":[{"id":500,"icon":"10d","description":"小雨","main":"Rain"}],"dt_txt":"2016-05-21 03:00:00","rain":{"3h":0.17},"main":{"temp_kf":0,"humidity":80,"pressure":963.05,"temp_max":296.33,"sea_level":1021.92,"temp_min":296.33,"temp":296.33,"grnd_level":963.05}},{"clouds":{"all":24},"dt":1463810400,"wind":{"speed":2.02,"deg":20.0014},"sys":{"pod":"d"},"weather":[{"id":500,"icon":"10d","description":"小雨","main":"Rain"}],"dt_txt":"2016-05-21 06:00:00","rain":{"3h":0.35},"main":{"temp_kf":0,"humidity":72,"pressure":961.83,"temp_max":298.943,"sea_level":1020.41,"temp_min":298.943,"temp":298.943,"grnd_level":961.83}},{"clouds":{"all":32},"dt":1463821200,"wind":{"speed":1.96,"deg":350.503},"sys":{"pod":"d"},"weather":[{"id":500,"icon":"10d","description":"小雨","main":"Rain"}],"dt_txt":"2016-05-21 09:00:00","rain":{"3h":0.36},"main":{"temp_kf":0,"humidity":70,"pressure":960.05,"temp_max":300.064,"sea_level":1018.7,"temp_min":300.064,"temp":300.064,"grnd_level":960.05}},{"clouds":{"all":64},"dt":1463832000,"wind":{"speed":1.56,"deg":7.00378},"sys":{"pod":"n"},"weather":[{"id":500,"icon":"10n","description":"小雨","main":"Rain"}],"dt_txt":"2016-05-21 12:00:00","rain":{"3h":1.4},"main":{"temp_kf":0,"humidity":75,"pressure":961.91,"temp_max":296.515,"sea_level":1020.84,"temp_min":296.515,"temp":296.515,"grnd_level":961.91}},{"clouds":{"all":92},"dt":1463842800,"wind":{"speed":2.01,"deg":13.5012},"sys":{"pod":"n"},"weather":[{"id":500,"icon":"10n","description":"小雨","main":"Rain"}],"dt_txt":"2016-05-21 15:00:00","rain":{"3h":0.67},"main":{"temp_kf":0,"humidity":81,"pressure":964.08,"temp_max":294.937,"sea_level":1023.41,"temp_min":294.937,"temp":294.937,"grnd_level":964.08}},{"clouds":{"all":92},"dt":1463853600,"wind":{"speed":1.61,"deg":352.002},"sys":{"pod":"n"},"weather":[{"id":500,"icon":"10n","description":"小雨","main":"Rain"}],"dt_txt":"2016-05-21 18:00:00","rain":{"3h":0.89},"main":{"temp_kf":0,"humidity":89,"pressure":963.5,"temp_max":293.79,"sea_level":1022.91,"temp_min":293.79,"temp":293.79,"grnd_level":963.5}},{"clouds":{"all":92},"dt":1463864400,"wind":{"speed":1.62,"deg":315.5},"sys":{"pod":"n"},"weather":[{"id":501,"icon":"10n","description":"中雨","main":"Rain"}],"dt_txt":"2016-05-21 21:00:00","rain":{"3h":3.6},"main":{"temp_kf":0,"humidity":97,"pressure":963.27,"temp_max":292.333,"sea_level":1022.6,"temp_min":292.333,"temp":292.333,"grnd_level":963.27}}]
     * city : {"coord":{"lon":104.066673,"lat":30.66667},"id":1815286,"sys":{"population":0},"name":"Chengdu","population":0,"country":"CN"}
     */
    private double message;
    private int cnt;
    private String cod;
    private List<ListEntity> list;
    private CityEntity city;

    public void setMessage(double message) {
        this.message = message;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public void setList(List<ListEntity> list) {
        this.list = list;
    }

    public void setCity(CityEntity city) {
        this.city = city;
    }

    public double getMessage() {
        return message;
    }

    public int getCnt() {
        return cnt;
    }

    public String getCod() {
        return cod;
    }

    public List<ListEntity> getList() {
        return list;
    }

    public CityEntity getCity() {
        return city;
    }

    public class ListEntity {
        /**
         * clouds : {"all":92}
         * dt : 1463875200
         * wind : {"speed":4.61,"deg":200.501}
         * sys : {"pod":"n"}
         * weather : [{"id":500,"icon":"10n","description":"小雨","main":"Rain"}]
         * dt_txt : 2016-05-22 00:00:00
         * rain : {"3h":0.01}
         * main : {"temp_kf":0,"humidity":79,"pressure":1008.34,"temp_max":286.603,"sea_level":1018.04,"temp_min":286.603,"temp":286.603,"grnd_level":1008.34}
         */
        private CloudsEntity clouds;
        private int dt;
        private WindEntity wind;
        private SysEntity sys;
        private List<WeatherEntity> weather;
        private String dt_txt;
        private RainEntity rain;
        private MainEntity main;

        public RainEntity getRain() {
            return rain;
        }

        public void setRain(RainEntity rain) {
            this.rain = rain;
        }

        public void setClouds(CloudsEntity clouds) {
            this.clouds = clouds;
        }

        public void setDt(int dt) {
            this.dt = dt;
        }

        public void setWind(WindEntity wind) {
            this.wind = wind;
        }

        public void setSys(SysEntity sys) {
            this.sys = sys;
        }

        public void setWeather(List<WeatherEntity> weather) {
            this.weather = weather;
        }

        public void setDt_txt(String dt_txt) {
            this.dt_txt = dt_txt;
        }

        public void setMain(MainEntity main) {
            this.main = main;
        }

        public CloudsEntity getClouds() {
            return clouds;
        }

        public int getDt() {
            return dt;
        }

        public WindEntity getWind() {
            return wind;
        }

        public SysEntity getSys() {
            return sys;
        }

        public List<WeatherEntity> getWeather() {
            return weather;
        }

        public String getDt_txt() {
            return dt_txt;
        }

        public MainEntity getMain() {
            return main;
        }

        public class CloudsEntity {
            /**
             * all : 36
             */
            private int all;

            public void setAll(int all) {
                this.all = all;
            }

            public int getAll() {
                return all;
            }
        }

        public class WindEntity {
            /**
             * speed : 1.66
             * deg : 42.0002
             */
            private String speed;
            private double deg;

            public void setSpeed(String speed) {
                this.speed = speed;
            }

            public void setDeg(double deg) {
                this.deg = deg;
            }

            public String getSpeed() {
                return speed;
            }

            public double getDeg() {
                return deg;
            }
        }

        public class SysEntity {
            /**
             * pod : d
             */
            private String pod;

            public void setPod(String pod) {
                this.pod = pod;
            }

            public String getPod() {
                return pod;
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

        public class RainEntity{
            /**
             *  "3h":0.01
             */
            private float threeh;

            public float getThreeh() {
                return threeh;
            }

            public void setThreeh(float threeh) {
                this.threeh = threeh;
            }
        }


        public class MainEntity {
            /**
             * temp_kf : 1.05
             * humidity : 52
             * pressure : 960.09
             * temp_max : 299.43
             * sea_level : 1018.8
             * temp_min : 298.386
             * temp : 299.43
             * grnd_level : 960.09
             */
            private double temp_kf;
            private int humidity;
            private double pressure;
            private double temp_max;
            private double sea_level;
            private double temp_min;
            private double temp;
            private double grnd_level;

            public void setTemp_kf(double temp_kf) {
                this.temp_kf = temp_kf;
            }

            public void setHumidity(int humidity) {
                this.humidity = humidity;
            }

            public void setPressure(double pressure) {
                this.pressure = pressure;
            }

            public void setTemp_max(double temp_max) {
                this.temp_max = temp_max;
            }

            public void setSea_level(double sea_level) {
                this.sea_level = sea_level;
            }

            public void setTemp_min(double temp_min) {
                this.temp_min = temp_min;
            }

            public void setTemp(double temp) {
                this.temp = temp;
            }

            public void setGrnd_level(double grnd_level) {
                this.grnd_level = grnd_level;
            }

            public double getTemp_kf() {
                return temp_kf;
            }

            public int getHumidity() {
                return humidity;
            }

            public double getPressure() {
                return pressure;
            }

            public double getTemp_max() {
                return temp_max;
            }

            public double getSea_level() {
                return sea_level;
            }

            public double getTemp_min() {
                return temp_min;
            }

            public double getTemp() {
                return temp;
            }

            public double getGrnd_level() {
                return grnd_level;
            }
        }



    }

    public class CityEntity {
        /**
         * coord : {"lon":104.066673,"lat":30.66667}
         * id : 1815286
         * sys : {"population":0}
         * name : Chengdu
         * population : 0
         * country : CN
         */
        private CoordEntity coord;
        private int id;
        private SysEntity sys;
        private String name;
        private int population;
        private String country;

        public void setCoord(CoordEntity coord) {
            this.coord = coord;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setSys(SysEntity sys) {
            this.sys = sys;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setPopulation(int population) {
            this.population = population;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public CoordEntity getCoord() {
            return coord;
        }

        public int getId() {
            return id;
        }

        public SysEntity getSys() {
            return sys;
        }

        public String getName() {
            return name;
        }

        public int getPopulation() {
            return population;
        }

        public String getCountry() {
            return country;
        }

        public class CoordEntity {
            /**
             * lon : 104.066673
             * lat : 30.66667
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

        public class SysEntity {
            /**
             * population : 0
             */
            private int population;

            public void setPopulation(int population) {
                this.population = population;
            }

            public int getPopulation() {
                return population;
            }
        }
    }
}
