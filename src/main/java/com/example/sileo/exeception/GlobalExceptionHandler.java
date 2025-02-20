package com.example.sileo.exeception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    protected ResponseEntity<APIError> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

                APIError error = new APIError();
                error.setMessage(ex.getMessage());
                error.setTitulo("Reveja os argumentos passados!");
                error.setTimestamp(System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);

    }

    @ExceptionHandler(IllegalArgumentException.class)
    private ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<APIError> handleRuntimeException(RuntimeException ex) {
        APIError error = new APIError();
        error.setMessage(ex.getMessage());
        error.setTitulo("");
        error.setTimestamp(System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    private ResponseEntity<APIError> handleUsernameNotFoundException(UsernameNotFoundException ex, WebRequest request) {
        APIError error = new APIError();
        error.setMessage(ex.getMessage());
        error.setTitulo("Usuário não encontrado, reveja o argumento passado.");
        error.setTimestamp(System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }


    @ExceptionHandler(DataIntegrityViolationException.class)
    private ResponseEntity<APIError> handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request) {
        APIError error = new APIError();
        error.setMessage("Data integrity violation");
        error.setTitulo("Erro de integridade de dados");
        error.setTimestamp(System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<APIError> handleGenericException(Exception ex) {
        APIError error = new APIError();
        error.setMessage("Ocorreu um erro inesperado.");
        error.setTitulo("Erro Interno");
        error.setTimestamp(System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }


    protected ResponseEntity<APIError> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        APIError error = new APIError();
        error.setMessage("Método não suportado: " + ex.getMethod());
        error.setTitulo("Método HTTP não permitido");
        error.setTimestamp(System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(error);
    }

    public class UserAlreadyExistsException extends RuntimeException {
        public UserAlreadyExistsException(String message) {
            super(message);
        }
    }

    public class RoleNotFoundException extends RuntimeException {
        public RoleNotFoundException(String message) {
            super(message);
        }
    }
}