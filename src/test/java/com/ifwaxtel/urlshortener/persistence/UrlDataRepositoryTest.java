package com.ifwaxtel.urlshortener.persistence;

import com.ifwaxtel.urlshortener.persistence.entities.UrlData;
import com.ifwaxtel.urlshortener.utils.UrlHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * The type Url data repository test.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class UrlDataRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private UrlDataRepository urlDataRepository;

    /**
     * Test persistence.
     */
    @Test
    public void testPersistence(){
        String urlKey = UrlHelper.generateRandomUrlKey(5);

        // Given
        UrlData urlData = new UrlData();
        urlData.setDateCreated(new Date().getTime());
        urlData.setOriginalUrl("http://github.com/darkseidng");
        urlData.setShortenedUrlKey(urlKey);

        testEntityManager.persist(urlData);
        testEntityManager.flush();

        // When
        UrlData found = urlDataRepository.findFirstByShortenedUrlKey(urlData.getShortenedUrlKey());

        // Then
        assertThat(found.getOriginalUrl())
                .isEqualTo(urlData.getOriginalUrl());

    }
}