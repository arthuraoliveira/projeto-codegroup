package br.com.codegroup.projetos.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.codegroup.projetos.model.Projeto;


@Repository
public interface ProjetoRepository extends CrudRepository<Projeto, Long> {
	
	@Query("select p from Projeto p where upper(p.nome) = upper(trim(:nome))")  
	Projeto findByNome(String nome);
	
	@Query("select p from Projeto p where upper(p.nome) = upper(trim(:nome)) and p.id <> :id")  
	Projeto findByNomeDiffId(String nome, Long id);
}
