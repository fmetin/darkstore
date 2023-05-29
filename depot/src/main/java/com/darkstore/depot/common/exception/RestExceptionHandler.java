package com.darkstore.depot.common.exception;


import com.darkstore.depot.common.response.model.RestResponse;
import com.darkstore.depot.common.response.model.RestResponseHeader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

import static com.darkstore.depot.common.response.model.RestResponseCode.UNKNOWN_ERROR;
import static com.darkstore.depot.common.response.model.RestResponseCode.VALIDATION_ERROR;


@ControllerAdvice
@Slf4j
public class RestExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public RestResponse<Void> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        Map<String, String> validationErrors = new HashMap<>();
        for (FieldError fieldError :
                e.getBindingResult().getFieldErrors()) {
            validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return new RestResponse<>(
                new RestResponseHeader(VALIDATION_ERROR.getResponseCode(),
                        VALIDATION_ERROR.getlocalizedResponseMessage()),
                null, validationErrors
        );
    }

    @ExceptionHandler(RestException.class)
    @ResponseBody
    public ResponseEntity<RestResponse<Void>> handleRestException(RestException e) {
        return ResponseEntity.status(e.getHttpStatus()).body(new RestResponse<>(
                new RestResponseHeader(e.getResponseCode(),
                        e.getResponseMessage()),
                null, null
        ));
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public RestResponse<Void> handleException(Exception e) {
        log.error(UNKNOWN_ERROR.getlocalizedResponseMessage(), e);
        return new RestResponse<>(
                new RestResponseHeader(UNKNOWN_ERROR.getResponseCode(),
                        UNKNOWN_ERROR.getlocalizedResponseMessage()),
                null, null);
    }
}