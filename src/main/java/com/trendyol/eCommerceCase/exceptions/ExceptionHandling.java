package com.trendyol.eCommerceCase.exceptions;

import com.trendyol.eCommerceCase.model.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.Objects;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class ExceptionHandling implements ErrorController  {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    private static final String METHOD_IS_NOT_ALLOWED = "This request method is not allowed on this endpoint. Please send a '%s' request";
    private static final String INTERNAL_SERVER_ERROR_MSG = "An error occurred while processing the request";
    private static final String INCORRECT_CREDENTIALS = "Username / password incorrect. Please try again";
    public static final String ERROR_PATH = "/error";


    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<HttpResponse> badCredentialsException() {
        return createHttpResponse(BAD_REQUEST, INCORRECT_CREDENTIALS);
    }

    @ExceptionHandler(UsernameExistException.class)
    public ResponseEntity<HttpResponse> usernameExistException(UsernameExistException exception) {
        return createHttpResponse(BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<HttpResponse> categoryNotFound(CategoryNotFoundException exception) {
        return createHttpResponse(NOT_FOUND, exception.getMessage());
    }

    @ExceptionHandler(CategoryNameExistException.class)
    public ResponseEntity<HttpResponse> categoryNameExist(CategoryNameExistException exception) {
        return createHttpResponse(BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<HttpResponse> userNotFoundException(UsernameNotFoundException exception) {
        return createHttpResponse(BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<HttpResponse> methodNotSupportedException(HttpRequestMethodNotSupportedException exception) {
        HttpMethod supportedMethod = Objects.requireNonNull(exception.getSupportedHttpMethods()).iterator().next();
        return createHttpResponse(METHOD_NOT_ALLOWED, String.format(METHOD_IS_NOT_ALLOWED, supportedMethod));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public final ResponseEntity<HttpResponse> handleUserMethodFieldErrors(MethodArgumentNotValidException ex, WebRequest request) {
        final List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        StringBuilder errorMessages = new StringBuilder();
        for (FieldError fieldError : fieldErrors) {
            errorMessages.append(fieldError.getDefaultMessage());
        }
        return createHttpResponse(BAD_REQUEST, errorMessages.toString());
    }

    @RequestMapping(ERROR_PATH)
    public ResponseEntity<HttpResponse> notFound404() {
        return createHttpResponse(NOT_FOUND, "There is no mapping for this URL");
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<HttpResponse> internalServerErrorException(Exception exception) {
        LOGGER.error(exception.getMessage());
        return createHttpResponse(INTERNAL_SERVER_ERROR, INTERNAL_SERVER_ERROR_MSG);
    }

    private ResponseEntity<HttpResponse> createHttpResponse(HttpStatus httpStatus, String message) {
        return new ResponseEntity<>(new HttpResponse(httpStatus.value(), httpStatus,
                httpStatus.getReasonPhrase().toUpperCase(), message), httpStatus);
    }



}