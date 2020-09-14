package com.springboot.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.springboot.error.ErrorDetails;

@ControllerAdvice
@RestController
//OR
//@RestControllerAdvice
//public class CustomizedResponseEntityExceptionHandler{ //OK
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	private  Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/*
	200 OK
	201 Created
	204 NO Content
	400 Bad Resquest
	401 Not Authoried
	403 Forbidden
	406 Method not allowed
	409 Conflict
	412 precondition failed - when invalid data is supplied.
	415  UNSUPPORTED MEDIA TYPE
	500 - iNTERNAL SERVER ERROR
	*/

	// when Input data Validation Failed , This will be raised.
	@Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
       
		//way-01, , Here wea re returning exception class obj as response.
		//return new ResponseEntity<Object>(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR); //OK
		//OR
		//return new ResponseEntity<Object>(ex.getMessage(),new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR); //OK
		
		//way-02, , Here wea re returning our custom error class obj as response.
		List<String> errDetails = new ArrayList<>();
		
        for(ObjectError error : ex.getBindingResult().getAllErrors()) {
        	errDetails.add(error.getDefaultMessage());
        }
        
        ErrorDetails errorDetails = new ErrorDetails(HttpStatus.BAD_REQUEST.value(),errDetails,LocalDateTime.now(),((HttpServletRequest) request).getRequestURI());
    
        // return new ResponseEntity<Object>(errorDetails,HttpStatus.BAD_REQUEST); //OK
        //OR
        return new ResponseEntity<Object>(errorDetails, new HttpHeaders(),HttpStatus.BAD_REQUEST); //OK
    }
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	//public ResponseEntity<ErrorResponse> handleAllExceptions(Exception ex,final HttpServletRequest request) {
	public final ResponseEntity<ErrorDetails> handleAllExceptions(Exception e, WebRequest request) {
		
	String errMsg = e.getLocalizedMessage();
	if (errMsg == null)
		errMsg = e.toString();
	
	List<String> errDetails = new ArrayList<String>();
	errDetails.add(errMsg);

	ErrorDetails errorResponse = new ErrorDetails();
	errorResponse.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
	errorResponse.setErrorDetails(errDetails);
	errorResponse.setTimestamp(LocalDateTime.now());
	errorResponse.setRequestedURI(((HttpServletRequest) request).getRequestURI());
	return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	@ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
	@ExceptionHandler(MissingHeaderInfoException.class)
	public final ResponseEntity<ErrorDetails> handleInvalidTraceIdException(MissingHeaderInfoException ex,WebRequest request) {
		
		List<String> details = new ArrayList<>();
		details.add(ex.getLocalizedMessage());
		            
        ErrorDetails errorResponse = new ErrorDetails();
		errorResponse.setHttpStatus(HttpStatus.BAD_REQUEST.value());
		errorResponse.setErrorDetails(details);
		errorResponse.setTimestamp(LocalDateTime.now());
		errorResponse.setRequestedURI(((HttpServletRequest) request).getRequestURI());
		
		logger.error("Error Details:{}",details);
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}
	/*
	//OR
	 @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	 @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrResponse error = new ErrResponse("Internal Server Error", details);
        return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    //OR
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorResponse> handleAllExceptions(Exception ex, WebRequest request) {
	  ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
	  return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	*/
	
	//@ResponseStatus(HttpStatus.NOT_FOUND)
		@ExceptionHandler(value=UserNotFoundException.class)
		public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException e,final HttpServletRequest request) {
		//public final ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex, WebRequest request) { //OK
			
			logger.error("Exception Details:"+e.getMessage());
			
			String errMsg = e.getLocalizedMessage();
			if (errMsg == null)
				errMsg = e.toString();
			
			List<String> errDetails = new ArrayList<String>();
			errDetails.add(errMsg);
			
			ErrorDetails errorResponse = new ErrorDetails();
			errorResponse.setHttpStatus(HttpStatus.NOT_FOUND.value());
			errorResponse.setErrorDetails(errDetails);
			errorResponse.setTimestamp(LocalDateTime.now());
			errorResponse.setRequestedURI(request.getRequestURI());
			
			return new ResponseEntity<Object>(errorResponse, HttpStatus.NOT_FOUND);
		}
		
		//OR
		 /*@ExceptionHandler(value = UserNotFoundException.class)
		   public ResponseEntity<Object> handleUserNotFoundException2(UserNotFoundException exception) {
		      return new ResponseEntity<>("User not found", new HttpHeaders(),HttpStatus.NOT_FOUND);
		   }*/
}
