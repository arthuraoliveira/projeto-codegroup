package br.com.codegroup.projetos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;

public class ApiException extends Exception {

	private static final long serialVersionUID = 6574233186506116306L;
	
	private HttpStatus statusCode;
    private String requestBody;
    private String responseBody;

    public ApiException(String requestBody, HttpStatusCodeException e) {
        super(e);
        this.requestBody = requestBody;
        this.statusCode = e.getStatusCode();
        this.responseBody = e.getResponseBodyAsString();
    }
    
    public ApiException(HttpStatus statusCode, Throwable ex) {
        super(ex);
        this.statusCode = statusCode;
    }

    public String getRequestBody() {
        return requestBody;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public String getResponseBody() {
        return responseBody;
    }
    
}
