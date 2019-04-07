package com.example.demo.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

/**
 * Uses api.mygasfeed.com to attain the gas prices of a given 10 mile radius. If it cannot find 
 * any gas prices, the return average is 0. 
 * @author Lisa Chen
 */
public class AverageGasPriceCalculator {
	private final int SEARCH_RADIUS = 10;
    private final String BASE_API_URL = "http://api.mygasfeed.com/stations/radius/";
    private final String API_PARAMS = "/" + SEARCH_RADIUS + "/mid/Param/rzkel0lgcd.json?";
    private final String PRICE_PARAM = "mid_price";
    private final int PARAM_LENGTH = PRICE_PARAM.length() + 3; //adds 3 for ":" after param
    private URL apiURL;
    private double averagePrice;
    
    /**
     * Constructs the calculator with the given Geolocation
     * @param geo Geolocation containing the latitude and longitude
     * @throws IOException
     */
    public AverageGasPriceCalculator(Geolocation geo) throws IOException {
        String apiURLString = BASE_API_URL + geo.getLatitude() + "/" + geo.getLongitude() + 
        		API_PARAMS;
        apiURL = new URL(apiURLString);
        averagePrice = calcAveragePrice(parseDataForPrices(readURLData()));
    }
    
    public double getAveragePrice() {
    	return averagePrice;
    }

    /**
     * Reads the gas station data from the URL.
     * @return Data of all the gas stations
     * @throws IOException
     */
    private String readURLData() throws IOException {
        String result = "";
        InputStreamReader is = new InputStreamReader(apiURL.openStream());
        BufferedReader in = new BufferedReader(is);
        String temp = in.readLine();
//        int count = 0;
        while (temp != null) {
            result += temp;
            temp = in.readLine();
        }
        return result;
    }
    
    /**
     * Finds all the prices in the given data string.
     * @param data The data string
     * @return A list of all prices from the data
     */
    private ArrayList<Double> parseDataForPrices(String data) {
        ArrayList<Double> results = new ArrayList<>();
        int paramIndex = data.indexOf(PRICE_PARAM);
        while (paramIndex >= 0) {
            int startIndex = paramIndex + PARAM_LENGTH;
            data = data.substring(startIndex);
            int quotationIndex = data.indexOf('\"');
            try {
                double result = Double.parseDouble(data.substring(0, quotationIndex));
                results.add(result);
            } catch (NumberFormatException e) {} //skip price if N/A price
            paramIndex = data.indexOf(PRICE_PARAM);
        }
        return results;
    }
    
    /**
     * Calculates the average price from a given list of prices. Returns 0 if there are no prices.
     * @param prices List of prices
     * @return Average price
     */
    private double calcAveragePrice(ArrayList<Double> prices) {
    	if (prices != null) {
            double sum = 0;
            for (double price: prices)
                sum += price;
            return sum / prices.size();	
    	}
    	else
    		return 0;
    }
//    public static void main(String[] args) throws IOException {
//        double testLat = 34.0583598;
//        double testLong = -117.82073109999999;
//        AverageGasPrice test = new AverageGasPrice(testLat, testLong);
//        System.out.println(test.averagePrice);
//    }
}
