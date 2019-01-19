package com.ifwaxtel.urlshortener.controllers.models;

import com.ifwaxtel.urlshortener.persistence.entities.UrlData;

import java.util.List;

/**
 * The type Statistics data.
 */
@SuppressWarnings({"FieldCanBeLocal", "unused"})
public class StatisticsData {
    private String total_urls_saved;
    private List<UrlData> urls;

    /**
     * Sets total urls saved.
     *
     * @param total_urls_saved the total urls saved
     */
    public void setTotal_urls_saved(String total_urls_saved) {
        this.total_urls_saved = total_urls_saved;
    }

    /**
     * Sets urls.
     *
     * @param urls the urls
     */
    public void setUrls(List<UrlData> urls) {
        this.urls = urls;
    }

    /**
     * Gets total urls saved.
     *
     * @return the total urls saved
     */
    public String getTotal_urls_saved() {
        return total_urls_saved;
    }

    /**
     * Gets urls.
     *
     * @return the urls
     */
    public List<UrlData> getUrls() {
        return urls;
    }
}
