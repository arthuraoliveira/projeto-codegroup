package br.com.codegroup.projetos.exception.error;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
public class BadRequestApiError {
	private String title = "One or more validation errors occurred.";
	private Integer status;
	private Map<String, List<String>> errors;

	public BadRequestApiError() {
		this.status = HttpStatus.BAD_REQUEST.value();
		this.errors = new HashMap<>();
	}

	public BadRequestApiError(String element, String message) {
		this.status = HttpStatus.BAD_REQUEST.value();
		this.errors = Map.of(element, new ArrayList<>(List.of(message)));
	}

	public BadRequestApiError(Map<String, List<String>> pErrors) {
		this.status = HttpStatus.BAD_REQUEST.value();
		this.errors = pErrors;
	}
	
	public BadRequestApiError(Integer status, String element, String message) {
		this.status = status;
		this.errors = Map.of(element, new ArrayList<>(List.of(message)));
	}
    
	public String getTitle() {
		return this.title;
	}

	public Integer getStatus() {
		return this.status;
	}
	
	public Map<String, List<String>> getErrors() {
		return this.errors;
	}
	
	public void setStatus(Integer status) {
        this.status = status;	    
	}

	public void addError(String element, String message){
		List<String> lList = this.errors.get(element);
		if (lList != null) {
			lList.add(message);

		} else {
			lList = new ArrayList<>();
			lList.add(message);
			this.errors.put(element, lList);
		}
	}
}
