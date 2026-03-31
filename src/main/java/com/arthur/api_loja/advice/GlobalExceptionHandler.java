package com.arthur.api_loja.advice;



import com.arthur.api_loja.exception.ResourceNotFoundException;
import com.arthur.api_loja.response.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiResponse<Object> handleNotFound(ResourceNotFoundException ex){
        return new ApiResponse<>(
                "erro",
                ex.getMessage(),
                null
        );
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<Object> handleIllegalArgument(IllegalArgumentException ex) {
        return new ApiResponse<>(
                "erro",
                ex.getMessage(),
                null
        );
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResponse<Object> handleException(Exception ex) {
        logger.error("Erro inesperado na API", ex);
        return new ApiResponse<>(
                "erro",
                "Ocorreu um erro inesperado: " + ex.getMessage(),
                null
        );
    }
}
