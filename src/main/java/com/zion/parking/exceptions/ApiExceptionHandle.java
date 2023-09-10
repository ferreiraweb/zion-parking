package com.zion.parking.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandle {

    private static final Long serialVersionUID = 1L;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleGlobalExceptions
            (Exception ex, HttpServletRequest request, BindingResult result) {

        if (ex instanceof MethodArgumentNotValidException subEx) {
            ErrorMessage error = new ErrorMessage(request,
                    HttpStatus.UNPROCESSABLE_ENTITY,
                    "Dados inválidos informados", result);

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }

        if (ex instanceof DataIntegrityViolationException) {
            ErrorMessage error = new ErrorMessage(request,
                    HttpStatus.UNPROCESSABLE_ENTITY,
                    "Violação do banco", result);

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }


        // global exception
        ErrorMessage error = new ErrorMessage(request,
                HttpStatus.BAD_REQUEST, ex.getMessage(), result);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);

    }

    @ExceptionHandler(UserNameUniqueViolationExcetion.class)
    public ResponseEntity<ErrorMessage> userNameUniqueViolationExceptionHandle
            (UserNameUniqueViolationExcetion ex,
             HttpServletRequest request
            ) {

        ErrorMessage error = new ErrorMessage(request,
                HttpStatus.CONFLICT,
                "Nome deve ser único");

        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);

    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorMessage> entityNotFoundExceptionHandle(
            EntityNotFoundException ex,
            HttpServletRequest request
    ) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(
                        new ErrorMessage(request, HttpStatus.NOT_FOUND, ex.getMessage())
                );
    }


}
