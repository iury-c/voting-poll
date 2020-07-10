package com.demoapp.votingpoll.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.DefaultResponseErrorHandler;

/**
 * Changes default behaviour for rest template, so it won't throw eception when getting either 4xx or 5xx.
 */
public class RestTemplateCustomErrorHandler extends DefaultResponseErrorHandler {

    @Override
    public boolean hasError(HttpStatus statusCode) {
        return statusCode.series() == HttpStatus.Series.SERVER_ERROR || statusCode.value() == HttpStatus.FORBIDDEN.value();
    }
}
