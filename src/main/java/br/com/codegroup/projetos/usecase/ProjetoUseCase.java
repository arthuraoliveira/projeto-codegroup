package br.com.codegroup.projetos.usecase;

import java.util.List;

import br.com.codegroup.projetos.controller.dto.ProjetoDto;
import br.com.codegroup.projetos.controller.response.ProjetoResponse;
import br.com.codegroup.projetos.exception.ApiException;

public interface ProjetoUseCase {

	ProjetoResponse save(ProjetoDto projetoRequest) throws ApiException;

	ProjetoDto findById(Long idProjeto);

	List<ProjetoDto> findAll();

	void delete(Long idProjeto);
}
