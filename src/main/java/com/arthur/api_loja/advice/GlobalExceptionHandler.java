package com.arthur.api_loja.advice;



import com.arthur.api_loja.exception.ResourceNotFoundException;
import com.arthur.api_loja.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiResponse<Object> handleNotFound(ResourceNotFoundException ex){
        return new ApiResponse<>(
                "erro",
                ex.getMessage(),
                null
        );
    }
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResponse<Object> handleException(Exception ex) {
        return new ApiResponse<>(
                "erro",
                "Ocorreu um erro inesperado",
                null
        );
    }
}
