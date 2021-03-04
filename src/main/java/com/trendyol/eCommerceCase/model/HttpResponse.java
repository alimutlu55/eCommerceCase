package com.trendyol.eCommerceCase.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class HttpResponse {
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "MM-dd-yy hh:mm:ss",timezone = "Europa/Istanbul")
    private Date timeStamp;
    private int httpStatusCode;
    private HttpStatus httpStatus;
    private String reason;
    private String message;

    public HttpResponse(int httpStatusCode, HttpStatus httpStatus, String reason, String message) {
        this.timeStamp = new Date();
        this.httpStatusCode = httpStatusCode;
        this.httpStatus = httpStatus;
        this.reason = reason;
        this.message=message;
    }
}