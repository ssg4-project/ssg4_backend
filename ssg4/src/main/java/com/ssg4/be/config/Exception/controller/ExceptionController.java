package com.ssg4.be.config.Exception.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ssg4.be.common.model.ResponseNoData;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseNoData ServerException2(Exception e) {
        e.printStackTrace();
        return new ResponseNoData("500", "서버 에러");
    }

    @ExceptionHandler(MissingRequestHeaderException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseNoData MissingRequestHeaderException(Exception e) {
        e.printStackTrace();
        return new ResponseNoData("400", "MissingRequestHeaderException");
    }

    @ExceptionHandler(UnsupportedJwtException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseNoData UnsupportedJwtException(Exception e) {
        e.printStackTrace();
        return new ResponseNoData("401", "UnsupportedJwtException");
    }

    @ExceptionHandler(MalformedJwtException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseNoData MalformedJwtException(Exception e) {
        e.printStackTrace();
        return new ResponseNoData("402", "MalformedJwtException");
    }

    @ExceptionHandler(ExpiredJwtException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseNoData ExpiredJwtException(Exception e) {
        e.printStackTrace();
        return new ResponseNoData("403", "ExpiredJwtException");
    }

    @ExceptionHandler(SignatureException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseNoData SignatureException(Exception e) {
        e.printStackTrace();
        return new ResponseNoData("404", "SignatureException");
    }


}
