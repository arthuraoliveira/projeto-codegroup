/**
 * 
 */
package br.com.codegroup.projetos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.codegroup.projetos.model.Cargo;
import br.com.codegroup.projetos.repository.CargoRepository;

/**
 * @author Arthur
 *
 */
@Service
public class CargoServiceImpl implements CargoService {

	@Autowired
	private CargoRepository cargoRepository;

	@Override
	public Cargo save(Cargo entity) throws Exception {
		return cargoRepository.save(entity);
	}
}
