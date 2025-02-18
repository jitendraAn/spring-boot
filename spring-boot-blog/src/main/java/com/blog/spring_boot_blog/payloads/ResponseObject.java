package com.blog.spring_boot_blog.payloads;

import lombok.Data;

@Data
public class ResponseObject {
    private Object responseData;
    private boolean responseStatus;
    private String responseMessage;
}