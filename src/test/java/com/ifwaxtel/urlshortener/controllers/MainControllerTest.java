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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * The type Main controller test.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = USApplication.class)
@AutoConfigureMockMvc
public class MainControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private UrlDataRepository urlDataRepository;

    /**
     * Test statistics endpoint.
     *
     * @throws Exception the exception
     */
    @Test
    public void testStatisticsEndpoint() throws Exception {

        // Clear all saved urlData
        urlDataRepository.deleteAll();

        // Create a new Url data object
        UrlData urlData = new UrlData();
        urlData.setDateCreated(new Date().getTime());
        urlData.setOriginalUrl("http://github.com/darkseidng");
        urlData.setShortenedUrlKey(UrlHelper.generateRandomUrlKey(5));

        // Save the newly created url data object
        urlDataRepository.save(urlData);

        // Test to see if the returned json contains the original url and shortened url from the newly created
        // urlData object and the total number of added UrlData objects
        mvc.perform(get("/api/gatherStatistics")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.total_urls_saved").value(1))
                .andExpect(jsonPath("$.urls[0].originalUrl").value(urlData.getOriginalUrl()))
                .andExpect(jsonPath("$.urls[0].shortenedUrlKey").value(urlData.getShortenedUrlKey()));
    }

    /**
     * Test shorten url endpoint.
     *
     * @throws Exception the exception
     */
    @Test
    public void testShortenUrlEndpoint() throws Exception {

        // Test to see if the returned json contains the original url and shortened url from the newly created
        // urlData object.
        mvc.perform(post("/api/shortenUrl")
                .param("url", "http://github.com/darkseidng")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.originalUrl").value("http://github.com/darkseidng"));
    }
}