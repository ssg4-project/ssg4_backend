package com.ssg4.be.config.Exception.model;

import lombok.AllArgsConstructor;
import lombok.Data;

//Response DTO
@Data
@AllArgsConstructor
public class Response {
    private String code;
    private String msg;
}