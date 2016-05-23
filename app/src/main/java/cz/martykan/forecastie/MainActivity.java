package cz.martykan.forecastie;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements LocationListener {
    private static final int MY_PERMISSIONS_ACCESS_FINE_LOCATION = 1;
    public static final int SHOW_LOCATOIN =0;
    public static final int NO_LOCATOIN = 1;

    private static Map<String, Integer> speedUnits = new HashMap<>(3);
    private static Map<String, Integer> pressUnits = new HashMap<>(3);

    Typeface weatherFont;
    Weather todayWeather = new Weather();

    TextView todayTemperature;
    TextView todayDescription;
    TextView todayWind;
    TextView todayPressure;
    TextView todayHumidity;
    TextView todaySunrise;
    TextView todaySunset;
//    TextView todayMaxTemper;
//    TextView todayMinTemper;
    TextView todayIcon;
    TextView cityIs;
    ViewPager viewPager;
    TabLayout tabLayout;


    View appView;

    ProgressDialog progressDialog;
    int loading = 0;

    boolean darkTheme;
    boolean destroyed = false;

    private List<Weather> longTermWeather;
    private List<Weather> longTermTodayWeather;
    private List<Weather> longTermTomorrowWeather;
    private List<Weather> longTemper;
    private List<Temper> temperToday = new ArrayList<>();
    private List<Temper> temperTomorrow = new ArrayList<>();
    private List<Temper> temperLater = new ArrayList<>();
    private List<Temper> temperLater1 = new ArrayList<>();
    private List<Temper> temperLater2 = new ArrayList<>();

    private String recentCity = "";
    private ArrayList temper ;
    private Handler handler;


    private static void close(Closeable x) {
        try {
            if (x != null) {
                x.close();
            }
        } catch (IOException e) {
            Log.e("IOException Data", "Error occurred while closing stream");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("-LCL-","MainActivity:onCreated.");
        // Initialize the associated SharedPreferences file with default values
        PreferenceManager.setDefaultValues(this, R.xml.prefs, false);

        darkTheme = false;
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        if (prefs.getBoolean("darkTheme", false)) {
            setTheme(R.style.AppTheme_NoActionBar_Dark);
            darkTheme = true;
        }

        // Initiate activity
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        appView = findViewById(R.id.viewApp);

        progressDialog = new ProgressDialog(MainActivity.this);

        // Load toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        toolbar.setLogo(R.mipmap.umbrella_white);
        if (darkTheme) {
            toolbar.setPopupTheme(R.style.AppTheme_PopupOverlay_Dark);
        }

        // Initialize textboxes
        todayTemperature = (TextView) findViewById(R.id.todayTemperature);
        todayDescription = (TextView) findViewById(R.id.todayDescription);
        todayWind = (TextView) findViewById(R.id.todayWind);
        todayPressure = (TextView) findViewById(R.id.todayPressure);
        todayHumidity = (TextView) findViewById(R.id.todayHumidity);

//        todayMaxTemper = (TextView)findViewById(R.id.todayMaxtemper);
//        todayMinTemper = (TextView)findViewById(R.id.todayMinTemper);
        todaySunrise = (TextView) findViewById(R.id.todaySunrise);
        todaySunset = (TextView) findViewById(R.id.todaySunset);

        todayIcon = (TextView) findViewById(R.id.todayIcon);
        cityIs = (TextView)findViewById(R.id.city);
        weatherFont = Typeface.createFromAsset(this.getAssets(), "fonts/weather.ttf");
        todayIcon.setTypeface(weatherFont);

        // Initialize viewPager
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);

        destroyed = false;

        initMappings();

        // Preload data from cache
        preloadWeather();

        // Set autoupdater
        AlarmReceiver.setRecurringAlarm(this);
    }

    public WeatherRecyclerAdapter getAdapter(int id) {
        Log.e("188-LCL-", "WeatherRecyclerAdapter:getAdapter");
        WeatherRecyclerAdapter weatherRecyclerAdapter;
        if (id == 0) {
            weatherRecyclerAdapter = new WeatherRecyclerAdapter(this, longTermTodayWeather);
        } else if (id == 1) {
            weatherRecyclerAdapter = new WeatherRecyclerAdapter(this, longTermTomorrowWeather);
        } else{
            weatherRecyclerAdapter = new WeatherRecyclerAdapter(this, longTermWeather);
        }
        return weatherRecyclerAdapter;
    }


    @Override 
    public void onResume() {
        super.onResume();
        boolean darkTheme =
                PreferenceManager.getDefaultSharedPreferences(this).getBoolean("darkTheme", false);
        if (darkTheme != this.darkTheme) {
            // Restart activity to apply theme
            overridePendingTransition(0, 0);
            finish();
            overridePendingTransition(0, 0);
            startActivity(getIntent());
        } else if (isNetworkAvailable()) {
            getTodayWeather();
            getLongTermWeather();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        destroyed = true;
    }

    private void preloadWeather() {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);


        String lastToday = sp.getString("lastToday","");
        Log.e("226-LCL-","lastToday:"+lastToday.isEmpty());

        if (!lastToday.isEmpty()) {
            Log.e("229-LCL-","TodayWeatherTask.");
            new TodayWeatherTask().execute("cachedResponse", lastToday);
        }

        String lastLongterm = sp.getString("lastLongterm","");
        Log.e("233-LCL-", "lastLongterm:" + lastLongterm.isEmpty());
        if (!lastLongterm.isEmpty()) {
            Log.e("235-LCL-","LongTermWeatherTask.");
            new LongTermWeatherTask().execute("cachedResponse", lastLongterm);
        }


    }

    private void getTodayWeather() {
        new TodayWeatherTask().execute();
    }

    private void getLongTermWeather() {
        new LongTermWeatherTask().execute();
    }

    private void searchCities() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle(this.getString(R.string.search_title));
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        input.setMaxLines(1);
        input.setSingleLine(true);
        alert.setView(input, 32, 0, 32, 0);
        alert.setPositiveButton(R.string.dialog_ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                String result = input.getText().toString();
                if (!result.isEmpty()) {
                    saveLocation(result);
                }
            }
        });
        alert.setNegativeButton(R.string.dialog_cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // Cancelled
            }
        });
        alert.show();
    }

    private void saveLocation(String result) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        recentCity = preferences.getString("city", Constants.DEFAULT_CITY);

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("city", result);
        editor.commit();

        getTodayWeather();
        getLongTermWeather();
    }

    private void aboutDialog() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("ThumbWeather");
        final WebView webView = new WebView(this);
        String about = "<p>A light,open source App.</p>" +
                "<p>Data provided by <a href='http://openweathermap.org/'>OpenWeatherMap</a>, under the <a href='http://creativecommons.org/licenses/by-sa/2.0/'>Creative Commons license</a>" +
                "<p>Icons are <a href='https://erikflowers.github.io/weather-icons/'>Weather Icons</a>, by <a href='http://www.twitter.com/artill'>Lukas Bischoff</a> and <a href='http://www.twitter.com/Erik_UX'>Erik Flowers</a>, under the <a href='http://scripts.sil.org/OFL'>SIL OFL 1.1</a> licence.";
        if (darkTheme) {
            // Style text color for dark theme
            about = "<style media=\"screen\" type=\"text/css\">" +
                    "body {\n" +
                    "    color:white;\n" +
                    "}\n" +
                    "a:link {color:cyan}\n" +
                    "</style>" +
                    about;
        }
        webView.setBackgroundColor(Color.TRANSPARENT);
        webView.loadData(about, "text/html", "UTF-8");
        alert.setView(webView, 32, 0, 32, 0);
        alert.setPositiveButton(R.string.dialog_ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

            }
        });
        alert.show();
    }

    private String setWeatherIcon(int actualId, int hourOfDay) {
        int id = actualId / 100;
        String icon = "";
        if (actualId == 800) {
            if (hourOfDay >= 7 && hourOfDay < 20) {
                icon = this.getString(R.string.weather_sunny);
            } else {
                icon = this.getString(R.string.weather_clear_night);
            }
        } else {
            switch (id) {
                case 2:
                    icon = this.getString(R.string.weather_thunder);
                    break;
                case 3:
                    icon = this.getString(R.string.weather_drizzle);
                    break;
                case 7:
                    icon = this.getString(R.string.weather_foggy);
                    break;
                case 8:
                    icon = this.getString(R.string.weather_cloudy);
                    break;
                case 6:
                    icon = this.getString(R.string.weather_snowy);
                    break;
                case 5:
                    icon = this.getString(R.string.weather_rainy);
                    break;
            }
        }
        return icon;
    }

    private String getRainString(JSONObject rainObj) {
        String rain = "0";
        if (rainObj != null) {
            rain = rainObj.optString("3h", "fail");
            if ("fail".equals(rain)) {
                rain = rainObj.optString("1h", "0");
            }
        }
        return rain;
    }

    private ParseResult parseTodayJson(String result) {
        try {
            JSONObject reader = new JSONObject(result);

            final String code = reader.optString("cod");
            if ("404".equals(code)) {
                return ParseResult.CITY_NOT_FOUND;
            }

            String city = reader.getString("name");
            String country = "";
            JSONObject countryObj = reader.optJSONObject("sys");
            if (countryObj != null) {
                country = countryObj.getString("country");
                todayWeather.setSunrise(countryObj.getString("sunrise"));
                todayWeather.setSunset(countryObj.getString("sunset"));
            }
            todayWeather.setCity(city);
            todayWeather.setCountry(country);

            JSONObject main = reader.getJSONObject("main");

            todayWeather.setTemperature(main.getString("temp"));
            todayWeather.setDescription(reader.getJSONArray("weather").getJSONObject(0).getString("description"));
            JSONObject windObj = reader.getJSONObject("wind");
            todayWeather.setWind(windObj.getString("speed"));
            if (windObj.has("deg")) {
                todayWeather.setWindDirectionDegree(windObj.getDouble("deg"));
            } else {
                Log.e("parseTodayJson", "No wind direction available");
                todayWeather.setWindDirectionDegree(null);
            }
            todayWeather.setPressure(main.getString("pressure"));
            todayWeather.setHumidity(main.getString("humidity"));

            JSONObject rainObj = reader.optJSONObject("rain");
            String rain;
            if (rainObj != null) {
                rain = getRainString(rainObj);
            } else {
                JSONObject snowObj = reader.optJSONObject("snow");
                if (snowObj != null) {
                    rain = getRainString(snowObj);
                } else {
                    rain = "0";
                }
            }
            todayWeather.setRain(rain);

            final String idString = reader.getJSONArray("weather").getJSONObject(0).getString("id");
            todayWeather.setId(idString);
            todayWeather.setIcon(setWeatherIcon(Integer.parseInt(idString), Calendar.getInstance().get(Calendar.HOUR_OF_DAY)));

            todayWeather.setTemp_max(main.getString("temp_max"));
            todayWeather.setTemp_min(main.getString("temp_min"));
            temperToday.add(mapTemper(todayWeather,0));

            SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(MainActivity.this).edit();
            editor.putString("lastToday", result);
            editor.commit();

        } catch (JSONException e) {
            Log.e("JSONException Data", result);
            e.printStackTrace();
            return ParseResult.JSON_EXCEPTION;
        }

        return ParseResult.OK;


    }

    private void updateTodayWeatherUI() {
        String city = todayWeather.getCity();
        Log.d("402-LCL-", "updateTodayWeatherUI:city = " + city);
        String country = todayWeather.getCountry();
        DateFormat timeFormat = android.text.format.DateFormat.getTimeFormat(getApplicationContext());
        Log.d("402-LCL-", "cityIS:" + city + (country.isEmpty() ? "" : ", " + country));
        cityIs.setText(city + (country.isEmpty() ? "" : ", " + country));
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);

        String temperature = todayWeather.getTemperature();
//        String temperatureMax = todayWeather.getTemp_max();
//        String temperatureMin = todayWeather.getTemp_min();
        if (sp.getString("unit", "C").equals("C")) {
            temperature = Float.parseFloat(temperature) - 273.15 + "";
//            temperatureMax = Float.parseFloat(temperatureMax) - 273.15 + "";
//            temperatureMin = Float.parseFloat(temperatureMin) - 273.15 + "";
        }

        if (sp.getString("unit", "C").equals("F")) {
            temperature = (((9 * (Float.parseFloat(temperature) - 273.15)) / 5) + 32) + "";
//            temperatureMax = (((9 * (Float.parseFloat(temperatureMax) - 273.15)) / 5) + 32) + "";
//            temperatureMin = (((9 * (Float.parseFloat(temperatureMin) - 273.15)) / 5) + 32) + "";
        }

        double wind = Double.parseDouble(todayWeather.getWind());
        if (sp.getString("speedUnit", "m/s").equals("kph")) {
            wind = wind * 3.59999999712;
        }

        if (sp.getString("speedUnit", "m/s").equals("mph")) {
            wind = wind * 2.23693629205;
        }

        double pressure = Double.parseDouble(todayWeather.getPressure());
        if (sp.getString("pressureUnit", "hPa").equals("kPa")) {
            pressure = pressure / 10;
        }
        if (sp.getString("pressureUnit", "hPa").equals("mm Hg")) {
            pressure = pressure * 0.750061561303;
        }


        if (new BigDecimal(temperature).setScale(0, RoundingMode.DOWN).intValue() == 0) {
            temperature = "0";
        } else {
            temperature = temperature.substring(0, temperature.indexOf(".") + 2);
        }

//        if (new BigDecimal(temperatureMax).setScale(0, RoundingMode.DOWN).intValue() == 0) {
//            temperatureMax = "0";
//        } else {
//            temperatureMax = temperature.substring(0, temperatureMax.indexOf(".") + 2);
//        }
//
//        if (new BigDecimal(temperatureMin).setScale(0, RoundingMode.DOWN).intValue() == 0) {
//            temperatureMin = "0";
//        } else {
//            temperatureMin = temperature.substring(0, temperatureMin.indexOf(".") + 2);
//        }


        todayTemperature.setText(temperature + " °" + sp.getString("unit", "C"));
//        todayMaxTemper.setText(temperatureMax + " °" + sp.getString("unit", "C"));
//        todayMinTemper.setText(temperatureMin + " °" + sp.getString("unit", "C"));
        Log.e("-LCL-","todayWeather.getRain().isEmpty()"+todayWeather.getRain().isEmpty());
        if (!todayWeather.getRain().isEmpty()) {
            if (Float.parseFloat(todayWeather.getRain()) > 0.1) {
                todayDescription.setText(todayWeather.getDescription().substring(0, 1).toUpperCase() +
                        todayWeather.getDescription().substring(1) +
                        " (" + todayWeather.getRain().substring(0, todayWeather.getRain().indexOf(".") + 2) + " mm)");
            } else {
                todayDescription.setText(todayWeather.getDescription().substring(0, 1).toUpperCase() +
                        todayWeather.getDescription().substring(1));
            }
            todayWind.setText(getString(R.string.wind) + ": " + (wind + "").substring(0, (wind + "").indexOf(".") + 2) + " " +
                    localize(sp, "speedUnit", "m/s") +
                    (todayWeather.isWindDirectionAvailable() ? " " + getWindDirectionString(sp, this, todayWeather) : ""));
            todayPressure.setText(getString(R.string.pressure) + ": " + (pressure + "").substring(0, (pressure + "").indexOf(".") + 2) + " " +
                    localize(sp, "pressureUnit", "hPa"));
            todayHumidity.setText(getString(R.string.humidity) + ": " + todayWeather.getHumidity() + " %");
        todaySunrise.setText(getString(R.string.sunrise) + ": " + timeFormat.format(todayWeather.getSunrise()));
        todaySunset.setText(getString(R.string.sunset) + ": " + timeFormat.format(todayWeather.getSunset()));
            todayIcon.setText(todayWeather.getIcon());
        }
    }
    public ParseResult parseLongTermJson(String result) {
        int i;
        if (!result.isEmpty()) {
            Log.e("-LCL-", "---parseLongTermJson---:result:" + result);
            final Gson gson = new Gson();
            javaBean javaben = gson.fromJson(result, javaBean.class);

            final String code = javaben.getCod();
            if ("404".equals(code)) {
                return ParseResult.CITY_NOT_FOUND;
            }

            longTermWeather = new ArrayList<>();
            longTermTodayWeather = new ArrayList<>();
            longTermTomorrowWeather = new ArrayList<>();
            longTemper = new ArrayList<>();

            List<javaBean.ListEntity> listEntities = javaben.getList();
            Log.e("-LCL-", "list.size():" + listEntities.size());
            int today_time = 0;
            int tomorrow_time = 0;
            int later_time = 0;
            int later_time1 = 0;
            int later_time2 = 0;

            Log.e("525-LCL-", "listEntities.size():" + listEntities.size());
            for (i = 0; i < listEntities.size(); i++) {
                Weather weather = new Weather();
                javaBean.ListEntity list = listEntities.get(i);
                javaBean.ListEntity.MainEntity main = list.getMain();
                weather.setDate(list.getDt() + "");
                weather.setTemperature(main.getTemp() + "");
                weather.setTemp_max(main.getTemp_max() + "");
                weather.setTemp_min(main.getTemp_min() + "");
                weather.setDescription(list.getWeather().get(0).getDescription());

                weather.setRain(list.getRain().getThreeh() + "");
                weather.setWind(list.getWind().getSpeed());
                weather.setWindDirectionDegree(list.getWind().getDeg());
                weather.setPressure(main.getPressure() + "");
                weather.setHumidity(main.getHumidity() + "");

                javaBean.ListEntity.RainEntity rainEntity = list.getRain();
                String rain = "";
                if (rainEntity != null) {
                    rain = rainEntity.getThreeh() + "";
                }
                weather.setRain(rain);

                final String idString = list.getWeather().get(0).getId() + "";
                weather.setId(idString);

                final String dateMsString = list.getDt()+"000";
                Calendar cal = Calendar.getInstance();
                cal.setTimeInMillis(Long.parseLong(dateMsString));
                weather.setIcon(setWeatherIcon(Integer.parseInt(idString), cal.get(Calendar.HOUR_OF_DAY)));

                Calendar today = Calendar.getInstance();

                if (cal.get(Calendar.DAY_OF_YEAR) == today.get(Calendar.DAY_OF_YEAR)) {
                    Log.e("561-LCL-","Calendar.DAY_OF_YEAR:"+today.get(Calendar.DAY_OF_YEAR));
                    //today's all weather that reported per 3 hours.
                    longTermTodayWeather.add(weather);
                    temperToday.add(mapTemper(weather, today_time));
                    today_time++;
                } else if (cal.get(Calendar.DAY_OF_YEAR) == today.get(Calendar.DAY_OF_YEAR) + 1) {
                    Log.e("569-LCL-","Calendar.DAY_OF_YEAR:"+cal.get(Calendar.DAY_OF_YEAR));
                    //tomorrow's all weather that reported per 3 hours.
                    longTermTomorrowWeather.add(weather);
                    temperTomorrow.add(mapTemper(weather, tomorrow_time));
                    tomorrow_time++;
                } else {
                    //later's all weather that reported per 3 hours.
                    longTermWeather.add(weather);
                    if (cal.get(Calendar.DAY_OF_YEAR) == today.get(Calendar.DAY_OF_YEAR) + 2){
                        temperLater.add(mapTemper(weather, later_time));
                        later_time++;
                    }
                    else if (cal.get(Calendar.DAY_OF_YEAR) == today.get(Calendar.DAY_OF_YEAR) + 3){
                        temperLater1.add(mapTemper(weather, later_time1));
                        later_time1++;
                    }else if (cal.get(Calendar.DAY_OF_YEAR) == today.get(Calendar.DAY_OF_YEAR) + 4){
                        temperLater1.add(mapTemper(weather, later_time2));
                        later_time2++;
                    }


                }
                //add everyday's weather information into longTemper list.
                longTemper.add(weather);
            }
            Log.e("564-LCL","today_time:"+today_time+",tomorrow_time:"+tomorrow_time+",later_time:"+later_time+",later_time1:"+later_time1+",later_time2:"+later_time2);
            SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(MainActivity.this).edit();
            editor.putString("lastLongterm", result);
            editor.commit();
        }else {
            return ParseResult.JSON_EXCEPTION;
        }
        //add everyday's temper information into temperlist.
        temper = new ArrayList();
        temper.add(temperToday);
        temper.add(temperTomorrow);
        temper.add(temperLater);
        temper.add(temperLater1);
        temper.add(temperLater2);
        Log.e("-LCL-", "longTermTodayWeather:" + longTermTodayWeather.size());
        Log.e("-LCL-", "longTermTomorrowWeather:" + longTermTomorrowWeather.size());
        Log.e("-LCL-", "longTermWeather:" + longTermWeather.size());
        Log.e("-LCL-", "temper:" + temper.size());

        return ParseResult.OK;
    }

    public Temper mapTemper(Weather weather, int day){
        Temper temp = new Temper();
        temp.setDay(day);
        temp.setHighTemper0(weather.getTemp_max());
        temp.setLowTemper0(weather.getTemp_min());
        return temp;
    }


    private void updateLongTermWeatherUI() {
        if (destroyed) {
            return;
        }

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        //temper line chart
        Bundle bundleTemper = new Bundle();
        bundleTemper.putInt("day", 3);
        bundleTemper.putParcelableArrayList("list",temper);
        TemperLineChartFragemnt temperLineChartFragemnt = new TemperLineChartFragemnt();
        temperLineChartFragemnt.setArguments(bundleTemper);
        viewPagerAdapter.addFragment(temperLineChartFragemnt,getString(R.string.temper));

        Bundle bundleToday = new Bundle();
        bundleToday.putInt("day", 0);
        RecyclerViewFragment recyclerViewFragmentToday = new RecyclerViewFragment();
        recyclerViewFragmentToday.setArguments(bundleToday);
        viewPagerAdapter.addFragment(recyclerViewFragmentToday, getString(R.string.today));

        Bundle bundleTomorrow = new Bundle();
        bundleTomorrow.putInt("day", 1);
        RecyclerViewFragment recyclerViewFragmentTomorrow = new RecyclerViewFragment();
        recyclerViewFragmentTomorrow.setArguments(bundleTomorrow);
        viewPagerAdapter.addFragment(recyclerViewFragmentTomorrow, getString(R.string.tomorrow));

        Bundle bundle = new Bundle();
        bundle.putInt("day", 2);
        RecyclerViewFragment recyclerViewFragment = new RecyclerViewFragment();
        recyclerViewFragment.setArguments(bundle);
        viewPagerAdapter.addFragment(recyclerViewFragment, getString(R.string.later));

        int currentPage = viewPager.getCurrentItem();

        viewPagerAdapter.notifyDataSetChanged();
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        if (currentPage == 0 && longTermTodayWeather.isEmpty()) {
            currentPage = 1;
        }
        viewPager.setCurrentItem(currentPage, false);
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo= connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_refresh) {
            if(isNetworkAvailable()) {
                getTodayWeather();
                getLongTermWeather();
            }
            else {
                Snackbar.make(appView, getString(R.string.msg_connection_not_available), Snackbar.LENGTH_LONG).show();
            }
            return true;
        }
        if (id == R.id.action_search) {
            searchCities();
            return true;
        }
        if (id == R.id.action_location) {
            getCityByLocation();
            return true;
        }
        if (id == R.id.action_settings) {
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(intent);
        }
        if (id == R.id.action_about) {
            aboutDialog();
            return true;
        }
        if (id == R.id.action_share){

        }
        return super.onOptionsItemSelected(item);
    }

    private void restorePreviousCity() {
        if (!TextUtils.isEmpty(recentCity)) {
            SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(MainActivity.this).edit();
            editor.putString("city", recentCity);
            editor.commit();
            recentCity = "";
        }
    }

    private void initMappings() {
        speedUnits.put("m/s", R.string.speed_unit_mps);
        speedUnits.put("kph", R.string.speed_unit_kph);
        speedUnits.put("mph", R.string.speed_unit_mph);

        pressUnits.put("hPa", R.string.pressure_unit_hpa);
        pressUnits.put("kPa", R.string.pressure_unit_kpa);
        pressUnits.put("mm Hg", R.string.pressure_unit_mmhg);
    }

    private String localize(SharedPreferences sp, String preferenceKey, String defaultValueKey) {
        return localize(sp, this, preferenceKey, defaultValueKey);
    }

    public static String localize(SharedPreferences sp, Context context, String preferenceKey, String defaultValueKey) {
        String preferenceValue = sp.getString(preferenceKey, defaultValueKey);
        String result = preferenceValue;
        if ("speedUnit".equals(preferenceKey)) {
            if (speedUnits.containsKey(preferenceValue)) {
                result = context.getString(speedUnits.get(preferenceValue));
            }
        } else if ("pressureUnit".equals(preferenceKey)) {
            if (pressUnits.containsKey(preferenceValue)) {
                result = context.getString(pressUnits.get(preferenceValue));
            }
        }
        return result;
    }

    public static String getWindDirectionString(SharedPreferences sp, Context context, Weather weather) {
        String pref = sp.getString("windDirectionFormat", null);
        if("arrow".equals(pref)) {
            return weather.getWindDirection(8).getArrow(context);
        } else if("abbr".equals(pref)) {
            return weather.getWindDirection().getLocalizedString(context);
        }

        return "";
    }

    void getCityByLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_CONTACTS)) {
                // Explanation not needed, since user requests this himself

            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_ACCESS_FINE_LOCATION);
            }

        } else {
            String provider;
            Location location = null;
            progressDialog.setMessage(getString(R.string.getting_location));
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
            LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
            List<String> providerList = locationManager.getProviders(true);
            if (providerList.contains(LocationManager.GPS_PROVIDER)){
            Log.d("LCL", "GPS");
            provider = LocationManager.GPS_PROVIDER;
            }else if (providerList.contains(LocationManager.NETWORK_PROVIDER)){
            Log.d("LCL","NET");
            provider = LocationManager.NETWORK_PROVIDER;
            }else {
            //There is no location provider can use,then Pop-up prompts
            Toast.makeText(this,"No location provider to use",Toast.LENGTH_SHORT).show();
            return;
            }
            location = locationManager.getLastKnownLocation(provider);
        Log.d("LCL", "location:" + location);
        if (location != null){
            //show the current device information
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
            preferences.edit().putString("Location", location.toString());
            preferences.edit().commit();
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();
            new ProvideCityNameTask().execute("coords", Double.toString(latitude), Double.toString(longitude));
        }else if (location == null){
            searchCities();
        }
        locationManager.requestLocationUpdates(provider,5000,1,this);
        }
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_ACCESS_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getCityByLocation();
                }
                return;
            }
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        progressDialog.hide();
        Log.i("GPS LOCATION", location.getLatitude() + ", " + location.getLongitude());
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();
        new ProvideCityNameTask().execute("coords", Double.toString(latitude), Double.toString(longitude));
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    abstract class GenericRequestTask extends AsyncTask<String, String, TaskOutput> {
        private void incLoadingCounter() {
            loading++;
        }

        private void decLoadingCounter() {
            loading--;
        }

        @Override
        protected void onPreExecute() {
            incLoadingCounter();
            if(!progressDialog.isShowing()) {
                progressDialog.setMessage(getString(R.string.downloading_data));
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();
            }
        }

        @Override
        protected TaskOutput doInBackground(String... params) {
            Log.e("794-LCL-", "doInBackground.");
            final TaskOutput output = new TaskOutput();

            String response = "";
//            volleyResponse = "";
            String[] coords = new String[]{};

            if (params != null && params.length > 0) {
                final String zeroParam = params[0];
                if ("cachedResponse".equals(zeroParam)) {
                    response = params[1];
                    // Actually we did nothing in this case :)
                    output.taskResult = TaskResult.SUCCESS;
                } else if ("coords".equals(zeroParam)) {
                    String lat = params[1];
                    String lon = params[2];
                    coords = new String[]{lat, lon};
                }
            }

            if (response.isEmpty()) {
                Log.e("822-LCL-","response.isEmpty():"+response.isEmpty());
                try {
                    URL url = provideURL(coords);
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    if (urlConnection.getResponseCode() == 200) {
                        InputStreamReader inputStreamReader = new InputStreamReader(urlConnection.getInputStream());
                        BufferedReader r = new BufferedReader(inputStreamReader);

                        int responseCode = urlConnection.getResponseCode();
                        String line = null;
                        while ((line = r.readLine()) != null) {
                            response += line + "\n";
                        }
                        close(r);
                        urlConnection.disconnect();
                        // Background work finished successfully
                        Log.i("Task", "done successfully");
                        output.taskResult = TaskResult.SUCCESS;
                    }
                    else if (urlConnection.getResponseCode() == 429) {
                        // Too many requests
                        Log.i("Task", "too many requests");
                        output.taskResult = TaskResult.TOO_MANY_REQUESTS;
                    }
                    else {
                        // Bad response from server
                        Log.i("Task", "bad response");
                        output.taskResult = TaskResult.BAD_RESPONSE;
                    }
                } catch (IOException e) {
                    Log.e("IOException Data", response);
                    e.printStackTrace();
                    // Exception while reading data from url connection
                    output.taskResult = TaskResult.IO_EXCEPTION;
                }
//                    RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
//                    StringRequest stringRequest = new StringRequest(url.toString(), new Response.Listener<String>() {
//                        @Override
//                        public void onResponse(String res) {
//                            volleyResponse = res;
//                            Log.d("830--LCL--", "response:" + res);
//                            output.taskResult = TaskResult.SUCCESS;
//                        }
//                    }, new Response.ErrorListener() {
//                        @Override
//                        public void onErrorResponse(VolleyError volleyError) {
//                            Log.e("--LCL--","onErrorResponse:"+volleyError.getMessage(),volleyError);
//                            output.taskResult = TaskResult.BAD_RESPONSE;
//                            Toast.makeText(MainActivity.this, "获取天气信息失败，请检查您的网络", Toast.LENGTH_SHORT).show();
//                        }
//                    });
//                    queue.add(stringRequest);
//                }catch (Exception e){
//                    e.printStackTrace();
//                }
            }

            if (TaskResult.SUCCESS.equals(output.taskResult)) {
                // Parse JSON data
                ParseResult parseResult = parseResponse(response);
                Log.e("850-LCL-","success-->response:"+response);
                if (ParseResult.CITY_NOT_FOUND.equals(parseResult)) {
                    // Retain previously specified city if current one was not recognized
                    restorePreviousCity();
                }
                output.parseResult = parseResult;
            }
            Log.e("857-LCL-","parseResult:"+output.parseResult);
            return output;
        }

        @Override
        protected void onPostExecute(TaskOutput output) {
            if(loading == 1) {
                progressDialog.dismiss();
            }
            decLoadingCounter();

            updateMainUI();

            handleTaskOutput(output);
        }

        protected final void handleTaskOutput(TaskOutput output) {
            switch (output.taskResult) {
                case SUCCESS: {
                    ParseResult parseResult = output.parseResult;
                    if (ParseResult.CITY_NOT_FOUND.equals(parseResult)) {
                        Snackbar.make(appView, getString(R.string.msg_city_not_found), Snackbar.LENGTH_LONG).show();
                    } else if (ParseResult.JSON_EXCEPTION.equals(parseResult)) {
                        Snackbar.make(appView, getString(R.string.msg_err_parsing_json), Snackbar.LENGTH_LONG).show();
                    }
                    break;
                }
                case TOO_MANY_REQUESTS: {
                    Snackbar.make(appView, getString(R.string.msg_too_many_requests), Snackbar.LENGTH_LONG).show();
                    break;
                }
                case BAD_RESPONSE: {
                    Snackbar.make(appView, getString(R.string.msg_connection_problem), Snackbar.LENGTH_LONG).show();
                    break;
                }
                case IO_EXCEPTION: {
                    Snackbar.make(appView, getString(R.string.msg_connection_not_available), Snackbar.LENGTH_LONG).show();
                    break;
                }
            }
        }

        private String getLanguage() {
            String language = Locale.getDefault().getLanguage();
            if (language.equals("cs")) {
                language = "cz";
            }
            return language;
        }

        private URL provideURL(String[] coords) throws UnsupportedEncodingException, MalformedURLException {
            SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
            final String apiKey = sp.getString("apiKey", getResources().getString(R.string.apiKey));

            StringBuilder urlBuilder = new StringBuilder("http://api.openweathermap.org/data/2.5/");
            urlBuilder.append(getAPIName()).append("?");
            if (coords.length == 2) {
                urlBuilder.append("lat=").append(coords[0]).append("&lon=").append(coords[1]);
            } else {
                final String city = sp.getString("city", Constants.DEFAULT_CITY);
                urlBuilder.append("q=").append(URLEncoder.encode(city, "UTF-8"));
            }
            urlBuilder.append("&lang=").append(getLanguage());
            urlBuilder.append("&mode=json");
            urlBuilder.append("&appid=").append(apiKey);

            Log.e("-LCL-", "doInBackground:URI:" + urlBuilder.toString());
            return new URL(urlBuilder.toString());

        }



        protected void updateMainUI() { }

        protected abstract ParseResult parseResponse(String response);
        protected abstract String getAPIName();
    }

    class TodayWeatherTask extends GenericRequestTask {
        @Override
        protected void onPreExecute() {
            loading = 0;
            super.onPreExecute();
        }

        @Override
        protected ParseResult parseResponse(String response) {
            return parseTodayJson(response);
        }

        @Override
        protected String getAPIName() {
            return "weather";
        }

        @Override
        protected void updateMainUI() {
            Log.e("-LCL-","updateMainUI:updateTodayWeatherUI");
            updateTodayWeatherUI();
        }
    }

    class LongTermWeatherTask extends GenericRequestTask {
        @Override
        protected ParseResult parseResponse(String response) {
            return parseLongTermJson(response);
        }

        @Override
        protected String getAPIName() {
            return "forecast";
        }

        @Override
        protected void updateMainUI() {
            Log.e("1010-LCL-","updateMainUI:updateLongTermWeatherUI");
            updateLongTermWeatherUI();
        }
    }

    class ProvideCityNameTask extends GenericRequestTask {

        @Override
        protected void onPreExecute() { /*Nothing*/ }

        @Override
        protected String getAPIName() {
            return "weather";
        }

        @Override
        protected ParseResult parseResponse(String response) {
            Log.i("993-LCL-.RESULT", response.toString());
            if (!response.isEmpty()){

                final Gson gson = new Gson();
                javaBean javabean = gson.fromJson(response,javaBean.class);

                final String code = javabean.getCod();
                if ("404".equals(code)) {
                    Log.e("Geolocation", "No city found");
                    return ParseResult.CITY_NOT_FOUND;
                }

                javaBean.CityEntity cityEntity = javabean.getCity();

                String city = cityEntity.getName();
                String country = "";
                country = cityEntity.getCountry();

                saveLocation(city + country);

            } else {
                Log.e("JSONException Data", response);
                return ParseResult.JSON_EXCEPTION;
            }

            return ParseResult.OK;
        }

        @Override
        protected void onPostExecute(TaskOutput output) {
            /* Handle possible errors only */
            handleTaskOutput(output);
        }
    }

    private class TaskOutput {
        /**
         * Indicates result of parsing server response
         */
        ParseResult parseResult;
        /**
         * Indicates result of background task
         */
        TaskResult taskResult;
    }

    private enum ParseResult {OK, JSON_EXCEPTION, CITY_NOT_FOUND}

    private enum TaskResult { SUCCESS, BAD_RESPONSE, IO_EXCEPTION, TOO_MANY_REQUESTS; }
}
