/**
 * 
 */
package br.com.codegroup.projetos.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.codegroup.projetos.model.Projeto;
import br.com.codegroup.projetos.repository.ProjetoRepository;

/**
 * @author Arthur
 *
 */
@Service
public class ProjetoServiceImpl implements ProjetoService {

	@Autowired
	private ProjetoRepository projetoRepository;

	@Override
	public Projeto save(Projeto entity) throws Exception {
		return projetoRepository.save(entity);
	}

	@Override
	public Optional<Projeto> findProjetoId(Long idProjeto) {
		return projetoRepository.findById(idProjeto);
	}

	@Override
	public Projeto findByNome(String nome) {
		return projetoRepository.findByNome(nome);
	}
	
	@Override
	public Projeto findByNomeDiffId(String nome, Long id) {
		return projetoRepository.findByNomeDiffId(nome, id);
	}
	
	@Override
	public List<Projeto> findAll() {
		Iterable<Projeto> iterator = projetoRepository.findAll();
		List<Projeto> listPeojeto = new ArrayList<>();
		iterator.forEach(listPeojeto::add);
		return listPeojeto;
	}

	@Override
	public void delete(Long idProjeto) {
		projetoRepository.deleteById(idProjeto);
	}

}
