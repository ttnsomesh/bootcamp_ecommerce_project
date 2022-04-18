//package com.ecommerce.bootcampecommerce.customexception;
//
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.context.request.WebRequest;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
//
//public class CustomizedResponseEntityExceptionHandler {
//    @RestController
//    public class CustomizedResponseEntityExceptionHandlerr extends ResponseEntityExceptionHandler {
//
//        @Override
//        protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
//                                                                      HttpHeaders headers, HttpStatus status,
//                                                                      WebRequest request) {
//            String message = ex.getBindingResult().toString();
//            return handleExceptionInternal(ex, message, new HttpHeaders(),
//                    HttpStatus.UNPROCESSABLE_ENTITY, request);
//        }
//    }
//}
