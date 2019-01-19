package com.ifwaxtel.urlshortener;

import com.ifwaxtel.urlshortener.controllers.MainController;
import com.ifwaxtel.urlshortener.controllers.RedirectController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * The type Us application.
 */
@SpringBootApplication
@ComponentScan(basePackageClasses = {MainController.class, RedirectController.class})
public class USApplication {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args)  {
        SpringApplication.run(USApplication.class, args);
    }
}
