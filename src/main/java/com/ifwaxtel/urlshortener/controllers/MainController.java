package com.ifwaxtel.urlshortener.controllers;

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

        UrlData urlData = saveUrl(url);

        HashMap<String, String> returnedData = new HashMap<>();
        returnedData.put("originalUrl", urlData.getOriginalUrl());
        returnedData.put("shortenedUrl", UrlHelper.getBaseUrl() + "/" + urlData.getShortenedUrlKey());

        return returnedData;

    }

    private UrlData saveUrl(String url){
        UrlData urlData = new UrlData();
        urlData.setOriginalUrl(url);
        urlData.setShortenedUrlKey(UrlHelper.generateRandomUrlKey(5));
        urlData.setDateCreated(new Date().getTime());

        urlDataRepository.save(urlData);

        return urlData;
    }

}