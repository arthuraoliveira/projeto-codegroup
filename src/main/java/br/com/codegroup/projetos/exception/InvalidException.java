package br.com.codegroup.projetos.exception;

import java.util.Map;

import java.util.List;

public class InvalidException extends RuntimeException {

    private final Map<String, List<String>> errors;

    public InvalidException(Map<String, List<String>> pErrors){
        super("Invalid Error.");
        this.errors = pErrors;
    }

    public InvalidException(String message, Map<String, List<String>> pErrors){
        super(message);
        this.errors = pErrors;
    }

    public Map<String, List<String>> getErrors() {
		return this.errors;
	}
}
