package com.ifwaxtel.urlshortener.controllers;

import com.ifwaxtel.urlshortener.persistence.UrlDataRepository;
import com.ifwaxtel.urlshortener.persistence.entities.UrlData;
import com.ifwaxtel.urlshortener.utils.UrlHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

/**
 * The type Redirect controller.
 */
@Controller
@RequestMapping(path="/")
public class RedirectController {

    private final UrlDataRepository urlDataRepository;

    /**
     * Instantiates a new Redirect controller.
     *
     * @param urlDataRepository the url data repository
     */
    @Autowired
    public RedirectController(UrlDataRepository urlDataRepository) {
        this.urlDataRepository = urlDataRepository;
    }

    /**
     * Redirect url redirect view.
     *
     * @param urlKey the url key
     * @return the redirect view
     */
    @GetMapping("/{urlKey}")
    public @ResponseBody
    RedirectView redirectUrl(@PathVariable("urlKey") String urlKey)
    {
        if (urlKey != null) {
            UrlData urlData = urlDataRepository.findFirstByShortenedUrlKey(urlKey);
            if (urlData != null) {
                if (urlData.getOriginalUrl() != null){
                    return UrlHelper.buildRedirectView(urlData.getOriginalUrl());
                }
            }
        }

        return UrlHelper.buildRedirectView("/urlerror");
    }

    /**
     * Error redirect string.
     *
     * @return the string
     */
    @GetMapping("/urlerror")
    public @ResponseBody
    String errorRedirect()
    {
        return "The url you entered could not be found on this server, please check the url and try again";
    }

}
