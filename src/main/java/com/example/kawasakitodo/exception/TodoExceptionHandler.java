package com.example.kawasakitodo.exception;

import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.slf4j.Logger;

/**
 * 例外のハンドラクラスです。
 *
 */
public class TodoExceptionHandler  {
    private static final Logger logger = LoggerFactory.getLogger(TodoExceptionHandler.class);

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFoundError(Exception e) {
        logger.error(e.getMessage(), e);
        return "error/404";
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String internalServerError(Exception e) {
        logger.error(e.getMessage(), e);
        return "error/500";
    }


}