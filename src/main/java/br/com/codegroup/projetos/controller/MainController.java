package br.com.codegroup.projetos.controller;

import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MainController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<String> main() throws Exception {

		JSONObject response = new JSONObject();
		response.put("message", "success");

		return new ResponseEntity<String>(response.toString(), HttpStatus.OK);
	}

}
