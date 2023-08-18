package br.com.codegroup.projetos.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.codegroup.projetos.model.Pessoa;


@Repository
public interface PessoaRepository extends CrudRepository<Pessoa, Long> {
	
}
