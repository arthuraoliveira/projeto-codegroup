package br.com.codegroup.projetos.service;

import java.util.List;
import java.util.Optional;

import br.com.codegroup.projetos.model.Projeto;

public interface ProjetoService {

	Projeto save(Projeto entity) throws Exception;

	Optional<Projeto> findProjetoId(Long idProjeto);
	
	Projeto findByNome(String nome);
	
	Projeto findByNomeDiffId(String nome, Long id);
	
	List<Projeto> findAll();

	void delete(Long idProjeto);

}
