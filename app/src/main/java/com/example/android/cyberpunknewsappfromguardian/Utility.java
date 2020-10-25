package com.example.android.cyberpunknewsappfromguardian;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * helper methods related to requesting and receiving news storys from the Guardian API
 */
public final class Utility {
    /** tag for logging messages */
  private static final String LOG_TAG = Utility.class.getSimpleName();
    /**
     * create a constructor object, private.
     * Holds static variables and methods accessed by the class.
     */
    private Utility() {
    }

    /**
     * Query the API and return a list of {@link NewsStory} objects.
     */
    public static List<NewsStory> fetchGuardianData(String requestUrl){
        //create URL object
        URL url;
        url = createUrl(requestUrl);

        //Perform request and receive JSON
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }
        // Extract relevant fields from the JSON response and create a list of {@link NewsStory}s
        List<NewsStory> stories = extractStory(jsonResponse);

        return stories;
    }
    /**
     * Return a new URL object from string URL.
     */

    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Problem building the URL ", e);
        }
        return url;
    }
    /**
     * Make an HTTP request to the URL and return a String response.
     */
    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        // if URL is null, leave early.
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // if the request worked, 200. Then reads input stream and parses response.
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the story JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                // Closing the input stream could throw an IOException, which is why
                // the makeHttpRequest(URL url) method signature specifies than an IOException
                // could be thrown.
                inputStream.close();
            }
        }
        return jsonResponse;
    }
    /**
     * Convert the {@link InputStream} into a String which contains the
     * whole JSON response from the server.
     */

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }
    /**
     * Return a list of stories from parsing the JSON.
     */
    public static List<NewsStory> extractStory(String cyberpunkJSON) {
        //if string is empty or null, get out.
        if (TextUtils.isEmpty(cyberpunkJSON)) {
            return null;
        }

        // Create an ArrayList that we can start adding stories to0
        List<NewsStory> stories = new ArrayList<>();
        // Try to parse the JSON. If there's a problem a JSONException will be thrown.
//      // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {
            //create JSOn object from the response string
            JSONObject baseJsonResponse = new JSONObject(cyberpunkJSON);

            JSONObject response = baseJsonResponse.getJSONObject("response");
            JSONArray resultsArray = response.getJSONArray("results");

            if (resultsArray.length() > 0) {
                for (int i = 0; i < resultsArray.length(); i++) {
                    JSONObject currentNewsStory = resultsArray.getJSONObject(i);

                    String title = currentNewsStory.getString("webTitle");
                    String author = "Unknown Author";

                    if (currentNewsStory.has("tags")) {
                        JSONArray tagsArray = currentNewsStory.getJSONArray("tags");
                        if (tagsArray.length() > 0) {
                            JSONObject story = tagsArray.getJSONObject(0);
                            author = story.getString("webTitle");
                        }
                    }

                    String webAddress = currentNewsStory.getString("webUrl");
                    String section = currentNewsStory.getString("sectionName");
                    String date = currentNewsStory
                            .getString("webPublicationDate")
                            .replace("T", "  ")
                            .replace("Z", " ");
                    SimpleDateFormat parser = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
                    SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
                    try {
                        Date parsedDate = parser.parse(date);
                        String formattedDate = formatter.format(parsedDate);
                        NewsStory story = new NewsStory(title, author,  formattedDate, webAddress, section);
                        stories.add(story);

                    } catch (ParseException e) {
                        System.out.println("date parse failure");
                    }


                }
            }
        } catch (JSONException e) {
            // If an error is thrown wihe "try" block, catch the exception,
            // so the app doesn't crash. Print a log message from the exception.
            Log.e("QueryUtils", "Problem parsing the JSON results", e);
        } finally {
            //return a list of stories
            return stories;
        }
    }

}