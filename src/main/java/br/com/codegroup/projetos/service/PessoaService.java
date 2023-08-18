package br.com.codegroup.projetos.service;

import java.util.Optional;

import br.com.codegroup.projetos.model.Pessoa;

public interface PessoaService {

	Pessoa save(Pessoa entity) throws Exception;

	Optional<Pessoa> findPessoaId(Long idPessoa);

}
