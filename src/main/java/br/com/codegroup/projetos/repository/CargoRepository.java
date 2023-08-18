package br.com.codegroup.projetos.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.codegroup.projetos.model.Cargo;


@Repository
public interface CargoRepository extends CrudRepository<Cargo, Long> {

}
