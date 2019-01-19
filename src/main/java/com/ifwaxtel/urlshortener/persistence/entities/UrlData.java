package com.ifwaxtel.urlshortener.persistence.entities;

import com.ifwaxtel.urlshortener.constants.GlobalConstant;

import javax.persistence.*;
import java.util.Objects;

/**
 * The type Url data.
 */
@Entity
public class UrlData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = GlobalConstant.ORIGINAL_URL)
    private String originalUrl;

    @Column(name = GlobalConstant.SHORTENED_URL_KEY)
    private String shortenedUrlKey;

    @Column(name = GlobalConstant.DATE_CREATED)
    private Long dateCreated;

    /**
     * Gets id.
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets original url.
     *
     * @return the original url
     */
    public String getOriginalUrl() {
        return originalUrl;
    }

    /**
     * Sets original url.
     *
     * @param originalUrl the original url
     */
    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    /**
     * Gets shortened url key.
     *
     * @return the shortened url key
     */
    public String getShortenedUrlKey() {
        return shortenedUrlKey;
    }

    /**
     * Sets shortened url key.
     *
     * @param shortenedUrlKey the shortened url key
     */
    public void setShortenedUrlKey(String shortenedUrlKey) {
        this.shortenedUrlKey = shortenedUrlKey;
    }

    /**
     * Gets date created.
     *
     * @return the date created
     */
    public Long getDateCreated() {
        return dateCreated;
    }

    /**
     * Sets date created.
     *
     * @param dateCreated the date created
     */
    public void setDateCreated(Long dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", originalUrl='" + originalUrl + '\'' +
                ", shortenedUrlKey='" + shortenedUrlKey + '\'' +
                ", dateCreated=" + dateCreated +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UrlData urlData = (UrlData) o;
        return Objects.equals(id, urlData.id) &&
                Objects.equals(originalUrl, urlData.originalUrl) &&
                Objects.equals(shortenedUrlKey, urlData.shortenedUrlKey) &&
                Objects.equals(dateCreated, urlData.dateCreated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, originalUrl, shortenedUrlKey, dateCreated);
    }
}
