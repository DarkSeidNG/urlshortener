package com.ifwaxtel.urlshortener.controllers;

import com.ifwaxtel.urlshortener.USApplication;
import com.ifwaxtel.urlshortener.persistence.UrlDataRepository;
import com.ifwaxtel.urlshortener.persistence.entities.UrlData;
import com.ifwaxtel.urlshortener.utils.UrlHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * The type Redirect controller test.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = USApplication.class)
@AutoConfigureMockMvc
public class RedirectControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private UrlDataRepository urlDataRepository;

    /**
     * Test statistics redirect endpoint.
     *
     * @throws Exception the exception
     */
    @Test
    public void testStatisticsRedirectEndpoint() throws Exception {

        urlDataRepository.deleteAll();

        UrlData urlData = new UrlData();
        urlData.setDateCreated(new Date().getTime());
        urlData.setOriginalUrl("http://github.com/darkseidng");
        urlData.setShortenedUrlKey(UrlHelper.generateRandomUrlKey(5));

        urlDataRepository.save(urlData);

        mvc.perform(get("/" + urlData.getShortenedUrlKey())).andExpect(redirectedUrl("http://github.com/darkseidng")).andExpect(status().isFound());
    }
}