package br.com.codegroup.projetos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.codegroup.projetos.controller.dto.MembroDto;
import br.com.codegroup.projetos.controller.dto.MembroProjetoDto;
import br.com.codegroup.projetos.controller.response.MembroResponse;
import br.com.codegroup.projetos.controller.response.MessageResponse;
import br.com.codegroup.projetos.controller.validator.ApiValidator;
import br.com.codegroup.projetos.usecase.MembroUseCase;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("/membro")
public class MembroController {

	@Autowired
	private MembroUseCase membroUseCase;
	
	@Autowired
    private ApiValidator validator;

	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MembroResponse> create(@RequestBody MembroDto membroRequest) throws Exception {

		log.info("[MembroController] Create membro {}", membroRequest);
		
		validator.validateCreateMembro(membroRequest);
		
		MembroResponse response = membroUseCase.save(membroRequest);
		response.setMessage("Membro created successfully");
		
		log.info("[MembroController] {}", response);

		return new ResponseEntity<MembroResponse>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/vincularMembroProjeto", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MessageResponse> vincularMembroProjeto(@RequestBody MembroProjetoDto membroProjetoRequest) throws Exception {

		log.info("[MembroController] Vincular membro {} projeto {}", membroProjetoRequest.getIdPessoa(), membroProjetoRequest.getIdProjeto());
		
		validator.validateVincularMembroProjeto(membroProjetoRequest);
		validator.existsPessoa(membroProjetoRequest.getIdPessoa(), "id_membro", "Membro não existe.");
		validator.existsProjeto(membroProjetoRequest.getIdProjeto());
		validator.isFuncionario(membroProjetoRequest.getIdPessoa());
		
		boolean isAssociated = membroUseCase.vincularMembroProjeto(membroProjetoRequest);
		
		MessageResponse statusResponse = new MessageResponse();
		if(isAssociated) {
			statusResponse.setMessage("Membro associated successfully");
		}
		
		log.info("[MembroController] StatusResponse {}", statusResponse);

		return new ResponseEntity<MessageResponse>(statusResponse, HttpStatus.OK);
	}

}
