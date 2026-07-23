package com.detalles.ecommerce.product_service.exception;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalControllerAdvice {

	@ExceptionHandler(exception = ResourceNotFoundException.class)
	public ProblemDetail handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest webRequest)
	{
		ProblemDetail detail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
		
		detail.setTitle("Recurso no encontrado");
		detail.setProperty("Resource", ex.getResourceName());
		detail.setProperty("Field", ex.getFieldName());
		detail.setProperty("Value", ex.getFieldValue());
		
		return detail;
		
	}
	
	@ExceptionHandler(exception =  MethodArgumentNotValidException.class)
	public ProblemDetail handleMethodArgumentNotValidException(MethodArgumentNotValidException ex)
	{
		ProblemDetail detail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "La validacion de uno o mas campos fallo");
		
		detail.setTitle("Error de validacion");
		detail.setProperty("Timestamp", Instant.now());
		
		Map<String, String> errors = new HashMap<>();
		
		ex.getBindingResult().getFieldErrors().forEach(
				
				error->{
					errors.put(error.getField()	, error.getDefaultMessage());
				}
				
				);
		
		detail.setProperty("errores", errors);
		
		
		return detail;
		
		
	}
	
}
