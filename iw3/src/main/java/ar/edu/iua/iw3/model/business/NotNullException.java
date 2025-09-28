package ar.edu.iua.iw3.model.business;

import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class NotNullException extends Exception{

	@Builder
	public NotNullException(String message, Throwable ex) {
		super(message, ex);
	}
	
	@Builder
	public NotNullException(String message) {
		super(message);
	}
	
	@Builder
	public NotNullException(Throwable ex) {
		super(ex);
	}
}
