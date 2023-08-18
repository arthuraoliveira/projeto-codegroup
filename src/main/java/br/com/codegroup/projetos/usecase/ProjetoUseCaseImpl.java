package br.com.codegroup.projetos.usecase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.codegroup.projetos.builder.ApiBuilder;
import br.com.codegroup.projetos.controller.dto.ProjetoDto;
import br.com.codegroup.projetos.controller.response.ProjetoResponse;
import br.com.codegroup.projetos.exception.ApiException;
import br.com.codegroup.projetos.exception.InvalidException;
import br.com.codegroup.projetos.model.Projeto;
import br.com.codegroup.projetos.service.ProjetoService;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class ProjetoUseCaseImpl implements ProjetoUseCase {
	
	@Autowired
	private ProjetoService projetoService;
	
	@Autowired
	private ApiBuilder apiBuilder;
	
	@Override
	public ProjetoResponse save(ProjetoDto projetoRequest) throws ApiException {
		ProjetoResponse projetoResponse = null;
		try {
			Projeto projeto = apiBuilder.getProjeto(projetoRequest);
			log.info("[ProjetoUseCase] Save projeto {}", projeto);
			
			Projeto projetoRet = projetoService.save(projeto);
			log.info("[ProjetoUseCase] projeto success id {} ", projetoRet.getId());

			projetoResponse = ProjetoResponse.builder()
									.idProjeto(projetoRet.getId())
									.nome(projetoRequest.getNome())
								.build();

		} catch (Exception e) {
			throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, e);
		}

		return projetoResponse;
	}
	
	@Override
	public ProjetoDto findById(Long idProjeto) {
		Optional<Projeto> projetoOpt = projetoService.findProjetoId(idProjeto);
		if(projetoOpt.isPresent()) {
			return apiBuilder.getProjetoDto(projetoOpt.get());
		} else {
			Map<String, List<String>> errors = new HashMap<>();
			errors.put("message", new ArrayList<>(Arrays.asList("Projeto not found")));
			throw new InvalidException(errors);
		}
	}

	@Override
	public List<ProjetoDto> findAll() {
		List<Projeto> projetos = projetoService.findAll();
		if(projetos != null && !projetos.isEmpty()) {
			List<ProjetoDto> projetoDtos = new ArrayList<ProjetoDto>();
			projetos.forEach(projeto -> {
				projetoDtos.add(apiBuilder.getProjetoDto(projeto));
			});
			return projetoDtos;
		}  else {
			Map<String, List<String>> errors = new HashMap<>();
			errors.put("message", new ArrayList<>(Arrays.asList("Projetos not found")));
			throw new InvalidException(errors);
		}
	}

	@Override
	public void delete(Long idProjeto) { 
		ProjetoDto projetoDto = findById(idProjeto);
		if(projetoDto.isPermiteDelete()) {
			projetoService.delete(idProjeto);
		} else {
			Map<String, List<String>> errors = new HashMap<>();
			errors.put("message", new ArrayList<>(Arrays.asList("Not permission delete")));
			throw new InvalidException(errors);
		}
	}
 
}
