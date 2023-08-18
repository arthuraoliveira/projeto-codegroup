/**
 * 
 */
package br.com.codegroup.projetos.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.codegroup.projetos.model.Pessoa;
import br.com.codegroup.projetos.repository.PessoaRepository;

/**
 * @author Arthur
 *
 */
@Service
public class PessoaServiceImpl implements PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;

	@Override
	public Pessoa save(Pessoa entity) throws Exception {
		return pessoaRepository.save(entity);
	}

	@Override
	public Optional<Pessoa> findPessoaId(Long idPessoa) {
		return pessoaRepository.findById(idPessoa);
	}
}
