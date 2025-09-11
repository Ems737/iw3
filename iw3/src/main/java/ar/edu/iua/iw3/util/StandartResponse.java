package ar.edu.iua.iw3.util;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StandartResponse {

	private String message;

	//Con @JsonIgnore, no se va a mostrar ese valor en la respuesta
	@JsonIgnore
	private Throwable ex;

	@JsonIgnore
	private HttpStatus httpStatus;

	public int getCode() {
		return httpStatus.value(); 
	}
	
	@JsonIgnore
	private boolean devInfoEnabled; 
	
	//Éste Getter se va a ejecutar si la variable devInfo esta en true
	//True en Dev, False en Prod
	public String getDevInfo() {
		if(devInfoEnabled) {
			if(ex!=null) {
				return ExceptionUtils.getStackTrace(ex);
			} else {
				return "No stack trace";
			}
		} else {
			return null;
		}
	}
	
	//Con este Get anulo el Getter que me genera lombok
	//suplantandoló por el creado
	public String getMessage() {
		if(message!=null)
			return message;
		if (ex!=null)
			return ex.getMessage();
		return null;
	}
}
/*
 {
  "message": "lfdkjsldkfjl",
  "dfevInfo":"kjdhkfghskdjfh"
 }
  */