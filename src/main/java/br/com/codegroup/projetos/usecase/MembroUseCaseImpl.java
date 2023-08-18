package br.com.codegroup.projetos.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.codegroup.projetos.controller.dto.MembroDto;
import br.com.codegroup.projetos.controller.dto.MembroProjetoDto;
import br.com.codegroup.projetos.controller.response.MembroResponse;
import br.com.codegroup.projetos.exception.ApiException;
import br.com.codegroup.projetos.model.Cargo;
import br.com.codegroup.projetos.model.Membro;
import br.com.codegroup.projetos.model.MembroPK;
import br.com.codegroup.projetos.model.Pessoa;
import br.com.codegroup.projetos.service.CargoService;
import br.com.codegroup.projetos.service.MembroService;
import br.com.codegroup.projetos.service.PessoaService;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class MembroUseCaseImpl implements MembroUseCase {

	@Autowired
	private MembroService membroService;

	@Autowired
	private PessoaService pessoaService;
	
	@Autowired
	private CargoService cargoService;
	
	@Override
	public MembroResponse save(MembroDto membroRequest) throws ApiException {
		MembroResponse membroResponse = null;
		try {
			String nomeCargo = membroRequest.getCargo_pessoa();
			log.info("[MembroUseCase] save cargo {}", nomeCargo);
			Cargo cargo = Cargo.builder().nome(nomeCargo).build();
			Cargo cargoRet = cargoService.save(cargo);
			log.info("[MembroUseCase] cargo success id {} ", cargoRet.getId());

			String nomePessoa = membroRequest.getNomePessoa();
			log.info("[MembroUseCase] save pessoa {} cargo {} ", nomePessoa, cargoRet.getNome());
			Pessoa pessoa = Pessoa.builder().nome(nomePessoa).cargo(cargoRet)
					.funcionario(membroRequest.getFuncionario()).build();
			Pessoa pessoaRet = pessoaService.save(pessoa);
			log.info("[MembroUseCase] pessoa success id {} ", pessoaRet.getId());

			membroResponse = MembroResponse.builder().idMembro(pessoaRet.getId()).build();

		} catch (Exception e) {
			throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, e);
		}

		return membroResponse;
	}

	@Override
	public boolean vincularMembroProjeto(MembroProjetoDto membroProjetoRequest) throws ApiException {
		try {
			Long idPessoa = membroProjetoRequest.getIdPessoa();
			Long idProjeto = membroProjetoRequest.getIdProjeto();
				
			log.info("[MembroUseCase] Associate Membro idPessoa {} idProjeto {}", idPessoa, idProjeto);
			
			Membro membro = Membro.builder()
								.pk(MembroPK.builder()
									.idPessoa(idPessoa)
									.idProjeto(idProjeto)
								.build())
							.build();
			
			membroService.save(membro);
	
			log.info("[MembroUseCase] Membro associated with success");

		} catch (Exception e) {
			throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, e);
		}
		return true;
	}


	
}
