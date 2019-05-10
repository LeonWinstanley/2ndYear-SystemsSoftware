import net.aksingh.owmjapis.core.OWM;
import net.aksingh.owmjapis.api.APIException;
import net.aksingh.owmjapis.model.CurrentWeather;

import javax.xml.crypto.Data;

public class WeatherAPI {

    OWM owm = new OWM("31d338e6e7a5b58498c959460b97614a");

    CurrentWeather cwd;

//    try { cwd = owm.currentWeatherByCityName("London"); }
//    catch (APIException e) {}



    public void SetLocation(Double lat, Double lon)
    {
        try { cwd = owm.currentWeatherByCoords(lat, lon); }
        catch (APIException e) {}
        // 40.712776, -74.005974 = New York
        // 51.507351, -0.127758 = London
        // 52.954784, -1.158109 = Nottingham
    }

    public  String GetData() {
        if (cwd.hasRespCode() && cwd.getRespCode() == 200) {

            // checking if city name is available
            if (cwd.hasCityName()) {
                //printing city name from the retrieved data
                System.out.println("City: " + cwd.getCityName());
            }

            String DataToSend = "";
            try
            {
                DataToSend += cwd.getMainData().getHumidity();
            }
            catch (NullPointerException e) { DataToSend += "null"; }
            DataToSend += ",";
            try
            {
                DataToSend += cwd.getMainData().getTemp();
            }
            catch (NullPointerException e) { DataToSend += "null"; }
            DataToSend += ",";
            try
            {
                DataToSend += cwd.getWindData().getSpeed();
            }
            catch (NullPointerException e) { DataToSend += "null"; }
            DataToSend += ",";
            try
            {
                DataToSend += cwd.getWindData().getDegree();
            }
            catch (NullPointerException e) { DataToSend += "null"; }
            DataToSend += ",";
            try
            {
                DataToSend += cwd.getRainData().getPrecipVol3h();
            }
            catch (NullPointerException e) { DataToSend += "null"; }
            DataToSend += ",";
            try
            {
                DataToSend += cwd.getCloudData().getCloud();
            }
            catch (NullPointerException e) { DataToSend += "null"; }

            return DataToSend;
        }
        return "";
    }
}