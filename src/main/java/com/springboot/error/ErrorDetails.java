package com.springboot.error;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ErrorDetails {

	private int httpStatus;
	
	// private String errorDetails; //OK
	private List<String> errorDetails;
	
	// private Date dateTime; //OK
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime timestamp;
	
	private String requestedURI;

	public ErrorDetails() {
	}

	public ErrorDetails(int status, List<String> errorDetails, LocalDateTime timestamp, String requestedURI) {
		super();
		this.httpStatus = status;
		this.errorDetails = errorDetails;
		this.timestamp = timestamp;
		this.requestedURI = requestedURI;
	}

	public int getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(int httpStatus) {
		this.httpStatus = httpStatus;
	}

	public List<String> getErrorDetails() {
		return errorDetails;
	}

	public void setErrorDetails(List<String> errorDetails) {
		this.errorDetails = errorDetails;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public String getRequestedURI() {
		return requestedURI;
	}

	public void setRequestedURI(String requestedURI) {
		this.requestedURI = requestedURI;
	}

}
