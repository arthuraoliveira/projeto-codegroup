package br.com.codegroup.projetos.builder;

import org.springframework.stereotype.Service;

import br.com.codegroup.projetos.controller.dto.CargoDto;
import br.com.codegroup.projetos.controller.dto.PessoaDto;
import br.com.codegroup.projetos.controller.dto.ProjetoDto;
import br.com.codegroup.projetos.model.Pessoa;
import br.com.codegroup.projetos.model.Projeto;

@Service
public class ApiBuilder {
	
	public PessoaDto getGerenteDto(Projeto projeto) {
		return projeto.getGerente() != null
				&& projeto.getGerente().getId() != null
						? PessoaDto.builder()
								.idPessoa(projeto.getGerente().getId())
								.nome(projeto.getGerente().getNome())
								.cpf(projeto.getGerente().getCpf())
								.funcionario(projeto.getGerente().isFuncionario())
								.dataNascimento(projeto.getGerente().getDataNascimento())
								.cargo(getCargoDto(projeto))
							.build()
						: null;
	}

	public CargoDto getCargoDto(Projeto projeto) {
		return CargoDto.builder()
				.idCargo(projeto.getGerente().getCargo().getId())
				.nome(projeto.getGerente().getCargo().getNome()).build();
	}
	
	public Pessoa getGerente(ProjetoDto projetoDto) {
		return projetoDto.getGerenteResponsavel() != null
				&& projetoDto.getGerenteResponsavel().getIdPessoa() != null
						? Pessoa.builder().id(projetoDto.getGerenteResponsavel().getIdPessoa()).build()
						: null;
	}
	
	public Projeto getProjeto(ProjetoDto projetoRequest) {
		Projeto projeto = Projeto.builder()
									.id(projetoRequest.getIdProjeto())
									.nome(projetoRequest.getNome())
									.dataInicio(projetoRequest.getDataInicio())
									.gerente(getGerente(projetoRequest))
									.dataPrevisao(projetoRequest.getDataPrevisao())
									.dataFim(projetoRequest.getDataFim())
									.orcamento(projetoRequest.getOrcamento())
									.descricao(projetoRequest.getDescricao())
									.status(projetoRequest.getStatus())
									.risco(projetoRequest.getRisco())
								.build();
		return projeto;
	}
	
	public ProjetoDto getProjetoDto(Projeto projeto) {
		ProjetoDto projetoDto = ProjetoDto.builder()
				.idProjeto(projeto.getId())
				.nome(projeto.getNome())
				.dataInicio(projeto.getDataInicio())
				.gerenteResponsavel(getGerenteDto(projeto))
				.dataPrevisao(projeto.getDataPrevisao())
				.dataFim(projeto.getDataFim())
				.orcamento(projeto.getOrcamento())
				.descricao(projeto.getDescricao())
				.status(projeto.getStatus())
			.build();
		return projetoDto;
	}

}
