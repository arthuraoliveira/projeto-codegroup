package br.com.codegroup.projetos.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.codegroup.projetos.model.Membro;


@Repository
public interface MembroRepository extends CrudRepository<Membro, Long> {

}
