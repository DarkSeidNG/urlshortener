package com.ifwaxtel.urlshortener.controllers;

import com.ifwaxtel.urlshortener.controllers.models.StatisticsData;
import com.ifwaxtel.urlshortener.persistence.UrlDataRepository;
import com.ifwaxtel.urlshortener.persistence.entities.UrlData;
import com.ifwaxtel.urlshortener.utils.UrlHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * The type Main controller.
 */
@RestController
@RequestMapping(path="/api")
public class MainController {

    private final UrlDataRepository urlDataRepository;

    /**
     * Instantiates a new Main controller.
     *
     * @param urlDataRepository the url data repository
     */
    @Autowired
    public MainController(UrlDataRepository urlDataRepository) {
        this.urlDataRepository = urlDataRepository;
    }

    /**
     * Shorten url map.
     *
     * @param url the url
     * @return the map
     */
    @PostMapping(path="/shortenUrl")
    public @ResponseBody
    Map<String, String> shortenUrl(@RequestParam String url)
    {

        // Save the url
        UrlData urlData = saveUrl(url);


        // Set the data to be returned as a hashmap, this will be converted to json automatically
        HashMap<String, String> returnedData = new HashMap<>();
        returnedData.put("originalUrl", urlData.getOriginalUrl());
        returnedData.put("shortenedUrl", UrlHelper.getBaseUrl() + "/" + urlData.getShortenedUrlKey());

        return returnedData;

    }

    /**
     * Gather statistics statistics data.
     * This returns some data from the persistence layer like the
     * number of urls saved and the list of urls saved
     *
     * @return the statistics data
     */
    @GetMapping(path="/gatherStatistics")
    public @ResponseBody
    StatisticsData gatherStatistics()
    {
        // Populate the SatisticsData object with data from the persistence layer
        StatisticsData statisticsData = new StatisticsData();
        statisticsData.setTotal_urls_saved(String.valueOf(urlDataRepository.count()));
        statisticsData.setUrls(urlDataRepository.findAll());

        return statisticsData;

    }

    /**
     * Saves UrlData with the original url and shortenedUrl key to the Spring Jpa persistence layer
     * The shortenedUrlKey is a random 5 character alphanumeric string
     *
     * @param url - the original url
     * @return - returns a UrlData object
     */
    private UrlData saveUrl(String url){
        UrlData urlData = new UrlData();
        urlData.setOriginalUrl(url);
        urlData.setShortenedUrlKey(UrlHelper.generateRandomUrlKey(5));
        urlData.setDateCreated(new Date().getTime());

        urlDataRepository.save(urlData);

        return urlData;
    }


}
