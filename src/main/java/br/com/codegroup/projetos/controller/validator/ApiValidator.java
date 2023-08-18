package br.com.codegroup.projetos.controller.validator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.codegroup.projetos.controller.dto.MembroDto;
import br.com.codegroup.projetos.controller.dto.MembroProjetoDto;
import br.com.codegroup.projetos.controller.dto.ProjetoDto;
import br.com.codegroup.projetos.exception.InvalidException;
import br.com.codegroup.projetos.model.Pessoa;
import br.com.codegroup.projetos.model.Projeto;
import br.com.codegroup.projetos.service.PessoaService;
import br.com.codegroup.projetos.service.ProjetoService;

@Service
public class ApiValidator {
	
	@Autowired
	private PessoaService pessoaService;
	
	@Autowired
	private ProjetoService projetoService;
	
	List<String> mensagens = new ArrayList<>();
	Map<String, List<String>> errors = new HashMap<>();

	public void validateCreateMembro(MembroDto membroRequest) {
		this.mensagens = new ArrayList<>();
		this.errors = new HashMap<>();
		if (Objects.isNull(membroRequest.getNomePessoa()) || membroRequest.getNomePessoa().isBlank()
				|| membroRequest.getNomePessoa().isEmpty()) {
			addError("nome_pessoa");
		}
		if (Objects.isNull(membroRequest.getCargo_pessoa()) || membroRequest.getCargo_pessoa().isBlank()
				|| membroRequest.getCargo_pessoa().isEmpty()) {
			addError("cargo_pessoa");
		}
		if (Objects.isNull(membroRequest.getFuncionario())) {
			addError("funcionario");
		}
		lancarError();
	}

	public void validateVincularMembroProjeto(MembroProjetoDto membroProjetoRequest) {
		this.mensagens = new ArrayList<>();
		this.errors = new HashMap<>();
		if (Objects.isNull(membroProjetoRequest.getIdPessoa())) {
			addError("id_membro");
		}
		if (Objects.isNull(membroProjetoRequest.getIdProjeto())) {
			addError("id_projeto");
		}
		lancarError();
	}
	
	public void existsPessoa(Long idPessoa, String field, String message) {
		Optional<Pessoa> pessoaOpt = pessoaService.findPessoaId(idPessoa);
		if(!pessoaOpt.isPresent()) {
			Map<String, List<String>> errors = new HashMap<>();
			errors.put(field, new ArrayList<>(Arrays.asList(message)));
			throw new InvalidException(errors);
		}
	}
	
	public void isFuncionario(Long idPessoa) {
		Optional<Pessoa> pessoaOpt = pessoaService.findPessoaId(idPessoa);
		if(pessoaOpt.isPresent()) {
			Pessoa pessoa = pessoaOpt.get();
			if(!pessoa.isFuncionario()) {
				Map<String, List<String>> errors = new HashMap<>();
				errors.put("id_membro", new ArrayList<>(Arrays.asList("Não permitido vincular membro que não seja funcionário")));
				throw new InvalidException(errors);				
			}
		}
	}
	
	public void existsProjeto(Long idProjeto) {
		 Optional<Projeto> projetoOpt = projetoService.findProjetoId(idProjeto);
		if(!projetoOpt.isPresent()) {
			Map<String, List<String>> errors = new HashMap<>();
			errors.put("id_projeto", new ArrayList<>(Arrays.asList("Projeto não existe.")));
			throw new InvalidException(errors);
		}
	}
	
	public void existsNomeProjeto(String nome) {
		Projeto projeto = projetoService.findByNome(nome);
		if(Objects.nonNull(projeto)) {
			Map<String, List<String>> errors = new HashMap<>();
			errors.put("nome", new ArrayList<>(Arrays.asList("Projeto já existe.")));
			throw new InvalidException(errors);
		}
	}
	
	public void existsNomeProjetoDiffId(String nome, Long id) {
		Projeto projeto = projetoService.findByNomeDiffId(nome, id);
		if(Objects.nonNull(projeto)) {
			Map<String, List<String>> errors = new HashMap<>();
			errors.put("nome", new ArrayList<>(Arrays.asList("Projeto já existe.")));
			throw new InvalidException(errors);
		}
	}

	public void validateCreateProjeto(ProjetoDto projetoRequest) {
		this.mensagens = new ArrayList<>();
		this.errors = new HashMap<>();
		if (Objects.isNull(projetoRequest.getNome()) || projetoRequest.getNome().isBlank()
				|| projetoRequest.getNome().isEmpty()) {
			addError("nome");
		}
		if (Objects.isNull(projetoRequest.getStatus()) || projetoRequest.getStatus().isBlank()
				|| projetoRequest.getStatus().isEmpty()) {
			addError("status");
		}
		lancarError();
	}
	
	public void validateUpdateProjeto(ProjetoDto projetoRequest) {
		this.mensagens = new ArrayList<>();
		this.errors = new HashMap<>();
		if (Objects.isNull(projetoRequest.getIdProjeto())) {
			addError("id_projeto");
		}
		if (Objects.isNull(projetoRequest.getNome()) || projetoRequest.getNome().isBlank()
				|| projetoRequest.getNome().isEmpty()) {
			addError("nome");
		}
		if (Objects.isNull(projetoRequest.getStatus()) || projetoRequest.getStatus().isBlank()
				|| projetoRequest.getStatus().isEmpty()) {
			addError("status");
		}
		lancarError();
	}
	
	public void validateIdProjeto(Long idProjeto) {
		this.mensagens = new ArrayList<>();
		this.errors = new HashMap<>();
		if (Objects.isNull(idProjeto)) {
			addError("id_projeto");
		}
		lancarError();
	}

	private void lancarError() {
		if (!this.errors.isEmpty()) {
			throw new InvalidException("Invalid values for: ", errors);
		}
	}
	
	private void addError(String field) {
		errors.put(field, new ArrayList<>(Arrays.asList("Invalid value")));
	}


}
