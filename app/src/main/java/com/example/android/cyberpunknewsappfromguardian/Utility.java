package com.example.android.cyberpunknewsappfromguardian;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public final class Utility {
    private static final String SAMPLE_JSON_RESPONSE = "{\"response\":{\"status\":\"ok\",\"userTier\":\"developer\",\"total\":171210,\"startIndex\":1,\"pageSize\":10,\"currentPage\":1,\"pages\":17121,\"orderBy\":\"relevance\",\"results\":[{\"id\":\"lifeandstyle/ng-interactive/2020/oct/17/stephen-collins-on-the-us-election-cartoon\",\"type\":\"interactive\",\"sectionId\":\"lifeandstyle\",\"sectionName\":\"Life and style\",\"webPublicationDate\":\"2020-10-17T05:00:13Z\",\"webTitle\":\"Stephen Collins on the US election – cartoon\",\"webUrl\":\"https://www.theguardian.com/lifeandstyle/ng-interactive/2020/oct/17/stephen-collins-on-the-us-election-cartoon\",\"apiUrl\":\"https://content.guardianapis.com/lifeandstyle/ng-interactive/2020/oct/17/stephen-collins-on-the-us-election-cartoon\",\"isHosted\":false,\"pillarId\":\"pillar/lifestyle\",\"pillarName\":\"Lifestyle\"},{\"id\":\"us-news/2020/oct/15/rudy-giuliani-daughter-endorses-joe-biden\",\"type\":\"article\",\"sectionId\":\"us-news\",\"sectionName\":\"US news\",\"webPublicationDate\":\"2020-10-16T06:57:48Z\",\"webTitle\":\"US election: Rudy Giuliani's daughter endorses Joe Biden\",\"webUrl\":\"https://www.theguardian.com/us-news/2020/oct/15/rudy-giuliani-daughter-endorses-joe-biden\",\"apiUrl\":\"https://content.guardianapis.com/us-news/2020/oct/15/rudy-giuliani-daughter-endorses-joe-biden\",\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"},{\"id\":\"world/2020/oct/17/new-zealand-election-voters-head-to-the-polls-as-ardern-collins-call-halt-to-campaign\",\"type\":\"article\",\"sectionId\":\"world\",\"sectionName\":\"World news\",\"webPublicationDate\":\"2020-10-17T02:32:59Z\",\"webTitle\":\"New Zealand election: voters head to the polls\",\"webUrl\":\"https://www.theguardian.com/world/2020/oct/17/new-zealand-election-voters-head-to-the-polls-as-ardern-collins-call-halt-to-campaign\",\"apiUrl\":\"https://content.guardianapis.com/world/2020/oct/17/new-zealand-election-voters-head-to-the-polls-as-ardern-collins-call-halt-to-campaign\",\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"},{\"id\":\"australia-news/2020/oct/09/kidnap-plot-charges-sharpen-fears-of-election-unrest\",\"type\":\"article\",\"sectionId\":\"australia-news\",\"sectionName\":\"Australia news\",\"webPublicationDate\":\"2020-10-09T06:00:00Z\",\"webTitle\":\"US election briefing for Australia: Kidnap plot charges sharpen fears of election unrest\",\"webUrl\":\"https://www.theguardian.com/australia-news/2020/oct/09/kidnap-plot-charges-sharpen-fears-of-election-unrest\",\"apiUrl\":\"https://content.guardianapis.com/australia-news/2020/oct/09/kidnap-plot-charges-sharpen-fears-of-election-unrest\",\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"},{\"id\":\"film/2020/oct/22/borat-v-trump-can-entertainment-really-affect-an-election\",\"type\":\"article\",\"sectionId\":\"film\",\"sectionName\":\"Film\",\"webPublicationDate\":\"2020-10-22T06:10:18Z\",\"webTitle\":\"Borat v Trump: can entertainment really affect an election?\",\"webUrl\":\"https://www.theguardian.com/film/2020/oct/22/borat-v-trump-can-entertainment-really-affect-an-election\",\"apiUrl\":\"https://content.guardianapis.com/film/2020/oct/22/borat-v-trump-can-entertainment-really-affect-an-election\",\"isHosted\":false,\"pillarId\":\"pillar/arts\",\"pillarName\":\"Arts\"},{\"id\":\"world/2020/oct/19/bolivia-election-exit-polls-suggest-thumping-win-evo-morales-party-luis-arce\",\"type\":\"article\",\"sectionId\":\"world\",\"sectionName\":\"World news\",\"webPublicationDate\":\"2020-10-19T16:13:40Z\",\"webTitle\":\"Bolivia election: Evo Morales's leftwing party celebrates stunning comeback\",\"webUrl\":\"https://www.theguardian.com/world/2020/oct/19/bolivia-election-exit-polls-suggest-thumping-win-evo-morales-party-luis-arce\",\"apiUrl\":\"https://content.guardianapis.com/world/2020/oct/19/bolivia-election-exit-polls-suggest-thumping-win-evo-morales-party-luis-arce\",\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"},{\"id\":\"news/2020/oct/21/the-worlds-election-inside-the-23-october-guardian-weekly\",\"type\":\"article\",\"sectionId\":\"news\",\"sectionName\":\"News\",\"webPublicationDate\":\"2020-10-21T08:00:30Z\",\"webTitle\":\"The world's election – inside the 23 October Guardian Weekly\",\"webUrl\":\"https://www.theguardian.com/news/2020/oct/21/the-worlds-election-inside-the-23-october-guardian-weekly\",\"apiUrl\":\"https://content.guardianapis.com/news/2020/oct/21/the-worlds-election-inside-the-23-october-guardian-weekly\",\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"},{\"id\":\"world/2020/oct/18/jacinda-ardern-eases-into-second-term-amid-relief-in-new-zealand-at-election-landslide\",\"type\":\"article\",\"sectionId\":\"world\",\"sectionName\":\"World news\",\"webPublicationDate\":\"2020-10-18T04:37:11Z\",\"webTitle\":\"Jacinda Ardern considers coalition despite New Zealand election landslide\",\"webUrl\":\"https://www.theguardian.com/world/2020/oct/18/jacinda-ardern-eases-into-second-term-amid-relief-in-new-zealand-at-election-landslide\",\"apiUrl\":\"https://content.guardianapis.com/world/2020/oct/18/jacinda-ardern-eases-into-second-term-amid-relief-in-new-zealand-at-election-landslide\",\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"},{\"id\":\"us-news/2020/oct/16/first-thing-election-special-crazy-uncle-vs-twinkly-grandpa\",\"type\":\"article\",\"sectionId\":\"us-news\",\"sectionName\":\"US news\",\"webPublicationDate\":\"2020-10-16T10:22:52Z\",\"webTitle\":\"Crazy uncle vs twinkly grandpa | First Thing election special\",\"webUrl\":\"https://www.theguardian.com/us-news/2020/oct/16/first-thing-election-special-crazy-uncle-vs-twinkly-grandpa\",\"apiUrl\":\"https://content.guardianapis.com/us-news/2020/oct/16/first-thing-election-special-crazy-uncle-vs-twinkly-grandpa\",\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"},{\"id\":\"us-news/2020/oct/10/republicans-presidential-election-fears-biden-trump\",\"type\":\"article\",\"sectionId\":\"us-news\",\"sectionName\":\"US news\",\"webPublicationDate\":\"2020-10-11T07:10:27Z\",\"webTitle\":\"Republicans express fears Donald Trump will lose presidential election\",\"webUrl\":\"https://www.theguardian.com/us-news/2020/oct/10/republicans-presidential-election-fears-biden-trump\",\"apiUrl\":\"https://content.guardianapis.com/us-news/2020/oct/10/republicans-presidential-election-fears-biden-trump\",\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"}]}}";

    private Utility() {

    }

    public static ArrayList<NewsStory> extractStory() {
        // Create an empty ArrayList that we can start adding storys to
        ArrayList<NewsStory> stories = new ArrayList<>();
        // Try to parse the SAMPLE_JSON_RESPONSE. If there's a problem with the way the JSON
//      // is formatted, a JSONException exception object will be thrown.
//      // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {
            JSONObject baseJsonResponse = new JSONObject(SAMPLE_JSON_RESPONSE);

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
                    String date = currentNewsStory.getString("webPublicationDate");

                    NewsStory story = new NewsStory(title, author,  date, webAddress, section);
                    stories.add(story);
                }
            }
        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the JSON results", e);
        } finally {
            return stories;
        }
    }
}