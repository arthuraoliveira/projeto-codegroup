package br.com.codegroup.projetos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.codegroup.projetos.model.Membro;
import br.com.codegroup.projetos.repository.MembroRepository;

@Service
public class MembroServiceImpl implements MembroService {

	@Autowired
	private MembroRepository membrosRepository;

	@Override
	public Membro save(Membro entity) throws Exception {
		return membrosRepository.save(entity);
	}

}
