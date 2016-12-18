package az.openweatherapi;

import java.util.Locale;

import az.openweatherapi.listener.OWRequestListener;
import az.openweatherapi.model.OWResponse;
import az.openweatherapi.model.gson.common.Coord;
import az.openweatherapi.model.gson.current_day.CurrentWeather;
import az.openweatherapi.model.gson.five_day.ExtendedWeather;
import az.openweatherapi.utils.OWSupportedLanguages;
import az.openweatherapi.utils.OWSupportedUnits;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by az on 15/10/16.
 */

public class OWService {

    private static final String BASE_URL = "http://api.openweathermap.org/data/2.5/";

    private final OpenWeatherAPI mOpenWeatherAPI;

    private String mToken;

    private OWSupportedUnits mSelectedUnits = OWSupportedUnits.FAHRENHEIT;

    private OWSupportedLanguages mSelectedLanguage = OWSupportedLanguages.ENGLISH;

    /**
     * Main Service constructor.
     *
     * @param token the Open Weather Token to be used for the API calls.
     */
    public OWService(final String token) {
        mToken = token;

        Retrofit mRetrofitOWInstance = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mOpenWeatherAPI = mRetrofitOWInstance.create(OpenWeatherAPI.class);
    }

    /**
     * This configures the Metric Units in which the results of the requests need to be in.
     *
     * @param units a valid OWSupportedUnits object.
     */
    public void setMetricUnits(OWSupportedUnits units) {
        mSelectedUnits = units;
    }

    /**
     * Sets the API language based on a Locale.
     * @param language Locale of current language.
     */
    public void setLanguage(Locale language) {
        switch (language.getLanguage()) {
            case "en":
                mSelectedLanguage = OWSupportedLanguages.ENGLISH;
                break;
            case "ru":
                mSelectedLanguage = OWSupportedLanguages.RUSSIAN;
                break;
            case "it":
                mSelectedLanguage = OWSupportedLanguages.ITALIAN;
                break;
            case "es":
                mSelectedLanguage = OWSupportedLanguages.SPANISH;
                break;
            case "ro":
                mSelectedLanguage = OWSupportedLanguages.ROMANIAN;
                break;
            case "pl":
                mSelectedLanguage = OWSupportedLanguages.POLISH;
                break;
            case "fi":
                mSelectedLanguage = OWSupportedLanguages.FINNISH;
                break;
            case "nl":
                mSelectedLanguage = OWSupportedLanguages.DUTCH;
                break;
            case "fr":
                mSelectedLanguage = OWSupportedLanguages.FRENCH;
                break;
            case "bg":
                mSelectedLanguage = OWSupportedLanguages.BULGARIAN;
                break;
            case "sv":
                mSelectedLanguage = OWSupportedLanguages.SWEDISH;
                break;
            case "zh_tw":
                mSelectedLanguage = OWSupportedLanguages.CHINESE_T;
                break;
            case "zh":
                mSelectedLanguage = OWSupportedLanguages.CHINESE_S;
                break;
            case "tr":
                mSelectedLanguage = OWSupportedLanguages.TURKISH;
                break;
            case "hr":
                mSelectedLanguage = OWSupportedLanguages.CROATIAN;
                break;
            case "co":
                mSelectedLanguage = OWSupportedLanguages.CATALAN;
                break;
            default:
                mSelectedLanguage = OWSupportedLanguages.ENGLISH;
                break;
        }
    }

    /**
     * Obtain five days forecast for any given Latitude/Longitude pair.
     *
     * @param coordinate the latitude/longitude pair of the location you need the weather for.
     * @param listener   the OWRequestListener of the request result.
     */
    public void getFiveDayForecast(final Coord coordinate,
                                   final OWRequestListener<ExtendedWeather> listener) {
        Call<ExtendedWeather> fiveDayForecastCall = mOpenWeatherAPI.getFiveDayExtendedWeather(
                coordinate.getLat(),
                coordinate.getLon(),
                mToken,
                mSelectedUnits.getUnit(),
                mSelectedLanguage.getLanguageLocale());
        fiveDayForecastCall.enqueue(new Callback<ExtendedWeather>() {
            @Override
            public void onResponse(Response<ExtendedWeather> response, Retrofit retrofit) {
                listener.onResponse(new OWResponse<ExtendedWeather>(response.body()));
            }

            @Override
            public void onFailure(Throwable t) {
                listener.onFailure(t);
            }
        });
    }

    /**
     * Obtain current day forecast for any given Latitude/Longitude pair.
     *
     * @param coordinate the latitude/longitude pair of the location you need the weather for.
     * @param listener   the OWRequestListener of the request result.
     */
    public void getCurrentDayForecast(final Coord coordinate,
                                      final OWRequestListener<CurrentWeather> listener) {
        Call<CurrentWeather> currentDayForecastCall = mOpenWeatherAPI.getCurrentWeather(
                coordinate.getLat(),
                coordinate.getLon(),
                mToken,
                mSelectedUnits.getUnit(),
                mSelectedLanguage.getLanguageLocale());
        currentDayForecastCall.enqueue(new Callback<CurrentWeather>() {
            @Override
            public void onResponse(Response<CurrentWeather> response, Retrofit retrofit) {
                listener.onResponse(new OWResponse<CurrentWeather>(response.body()));
            }

            @Override
            public void onFailure(Throwable t) {
                listener.onFailure(t);
            }
        });
    }

    /**
     * Method used to check current selected metric system of the service.
     * @return OWSupportedUnits selected Units System.
     */
    public OWSupportedUnits getSelectedMetricSystem() {
        return this.mSelectedUnits;
    }

}
