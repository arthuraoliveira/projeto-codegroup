package br.com.codegroup.projetos.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.codegroup.projetos.controller.dto.ProjetoDto;
import br.com.codegroup.projetos.controller.response.MessageResponse;
import br.com.codegroup.projetos.controller.response.ProjetoResponse;
import br.com.codegroup.projetos.controller.validator.ApiValidator;
import br.com.codegroup.projetos.usecase.ProjetoUseCase;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("/projeto")
public class ProjetoController {

	@Autowired
	private ProjetoUseCase projetoUseCase;
	
	@Autowired
    private ApiValidator validator;

	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProjetoResponse> create(@RequestBody ProjetoDto projetoRequest) throws Exception {

		log.info("[ProjetoController] Create projeto {}", projetoRequest);
		
		validator.validateCreateProjeto(projetoRequest);
		validator.existsNomeProjeto(projetoRequest.getNome());
		if (Objects.nonNull(projetoRequest.getGerenteResponsavel())
				&& Objects.nonNull(projetoRequest.getGerenteResponsavel().getIdPessoa())) {
			validator.existsPessoa(projetoRequest.getGerenteResponsavel().getIdPessoa(), "gerente_responsavel",
					"Gerente não existe.");
		}		
		
		ProjetoResponse response = projetoUseCase.save(projetoRequest);
		response.setMessage("Projeto created successfully");
		
		log.info("[ProjetoController] {}", response);

		return new ResponseEntity<ProjetoResponse>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProjetoResponse> update(@RequestBody ProjetoDto projetoRequest) throws Exception {

		log.info("[ProjetoController] Update projeto {}", projetoRequest);
		
		validator.validateUpdateProjeto(projetoRequest);
		validator.existsProjeto(projetoRequest.getIdProjeto());
		validator.existsNomeProjetoDiffId(projetoRequest.getNome(), projetoRequest.getIdProjeto());
		if (Objects.nonNull(projetoRequest.getGerenteResponsavel())
				&& Objects.nonNull(projetoRequest.getGerenteResponsavel().getIdPessoa())) {
			validator.existsPessoa(projetoRequest.getGerenteResponsavel().getIdPessoa(), "gerente_responsavel",
					"Gerente não existe.");
		}		
		
		ProjetoResponse response = projetoUseCase.save(projetoRequest);
		response.setMessage("Projeto Update successfully");
		
		log.info("[ProjetoController] {}", response);

		return new ResponseEntity<ProjetoResponse>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/findById/{idProjeto}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProjetoDto> findById(@PathVariable Long idProjeto) throws Exception {

		log.info("[ProjetoController] Get projeto {}", idProjeto);
		
		validator.validateIdProjeto(idProjeto);
		
		ProjetoDto response = projetoUseCase.findById(idProjeto);
		log.info("[ProjetoController] {}", response);

		return new ResponseEntity<ProjetoDto>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/findAll", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProjetoDto>> findAll() throws Exception {

		log.info("[ProjetoController] Get projetos");
				
		List<ProjetoDto> response = projetoUseCase.findAll();
		log.info("[ProjetoController] {}", response);

		return new ResponseEntity<List<ProjetoDto>>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/delete/{idProjeto}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MessageResponse> delete(@PathVariable Long idProjeto) throws Exception {

		log.info("[ProjetoController] Delete projeto {}", idProjeto);
		
		validator.validateIdProjeto(idProjeto);
		
		projetoUseCase.delete(idProjeto);
		
		MessageResponse response = new MessageResponse();
		response.setMessage("delete success");
		
		return new ResponseEntity<MessageResponse>(response, HttpStatus.OK);
	}

}
