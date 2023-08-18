package br.com.codegroup.projetos.usecase;

import br.com.codegroup.projetos.controller.dto.MembroDto;
import br.com.codegroup.projetos.controller.dto.MembroProjetoDto;
import br.com.codegroup.projetos.controller.response.MembroResponse;
import br.com.codegroup.projetos.exception.ApiException;

public interface MembroUseCase {

	MembroResponse save(MembroDto membroRequest) throws ApiException;

	boolean vincularMembroProjeto(MembroProjetoDto membroProjetoRequest) throws ApiException;
}
