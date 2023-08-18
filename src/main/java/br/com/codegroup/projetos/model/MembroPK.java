package br.com.codegroup.projetos.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Embeddable
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MembroPK implements Serializable {
	
	private static final long serialVersionUID = -2742441537132033667L;

	@Column(name = "id_pessoa")
	private Long idPessoa;

	@Column(name = "id_projeto")
	private Long idProjeto;
}
