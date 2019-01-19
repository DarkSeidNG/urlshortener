package com.ifwaxtel.urlshortener.persistence;

import com.ifwaxtel.urlshortener.persistence.entities.UrlData;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The interface Url data repository.
 */
public interface UrlDataRepository extends JpaRepository<UrlData, Long> {

    /**
     * Find first by shortened url key url data.
     *
     * @param urlKey the url key
     * @return the url data
     */
    UrlData findFirstByShortenedUrlKey(String urlKey);

}
