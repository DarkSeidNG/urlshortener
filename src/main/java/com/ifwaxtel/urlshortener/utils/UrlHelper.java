package com.ifwaxtel.urlshortener.utils;

import com.ifwaxtel.urlshortener.constants.GlobalConstant;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

/**
 * The type Url helper.
 */
public class UrlHelper {

    /**
     * Build redirect view redirect view.
     * This class builds a RedirectView which is used to redirect the user to the url specified
     *
     * @param url the url
     * @return the redirect view
     */
    public static RedirectView buildRedirectView(String url){
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(url);
        return redirectView;
    }

    /**
     * Generate random url key string.
     * This method generates a random string of characters from an alphanumeric string stoored in
     * the GlobalConstants object
     *
     * @param count the count
     * @return the string
     */
    public static String generateRandomUrlKey(int count){

        StringBuilder builder = new StringBuilder();

        while (count-- != 0){
            int characterPosition = (int)(Math.random() * GlobalConstant.ALPHA_NUMERIC_STRING.length());
            builder.append(GlobalConstant.ALPHA_NUMERIC_STRING.charAt(characterPosition));
        }

        return builder.toString();
    }

    /**
     * Get base url string.
     * This method retrieves the base url of our request from the HttpServletRequest and returns the base url string
     *
     * @return the string
     */
    public static String getBaseUrl (){

        String baseUrl;

        HttpServletRequest currentRequest = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        baseUrl = "http://" + currentRequest.getLocalAddr();

        if (currentRequest.getLocalPort() != 80){
            baseUrl += ":" + currentRequest.getLocalPort(); // append the port if it is not port 80
        }

        if (!StringUtils.isEmpty(currentRequest.getContextPath())) {
            baseUrl += currentRequest.getContextPath();
        }

        return baseUrl;
    }
}
