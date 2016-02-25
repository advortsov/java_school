package com.tsystems.javaschool.view;

import org.apache.log4j.PropertyConfigurator;

/**
 * @author Alexander Dvortsov
 * @version 1.0
 * @since 25.02.2016
 */
public class LoggerUtil {
    public static void configure(){
        PropertyConfigurator.configure("log4j.properties");
    }

}
