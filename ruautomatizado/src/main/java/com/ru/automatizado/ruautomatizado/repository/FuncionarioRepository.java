package com.ru.automatizado.ruautomatizado.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ru.automatizado.ruautomatizado.model.Funcionario;

@Repository
public interface FuncionarioRepository extends CrudRepository<Funcionario, String>{
	Funcionario findByMatricula(String matricula);
	Funcionario save(Funcionario funcionario);
}