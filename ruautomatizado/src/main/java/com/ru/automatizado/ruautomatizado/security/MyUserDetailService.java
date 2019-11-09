package com.ru.automatizado.ruautomatizado.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.ru.automatizado.ruautomatizado.model.Funcionario;
import com.ru.automatizado.ruautomatizado.repository.FuncionarioRepository;


@Service(value = "usuarioService")
public class MyUserDetailService implements UserDetailsService {

	@Autowired
	private FuncionarioRepository fr;
	
	@Autowired
    private BCryptPasswordEncoder bcryptEncoder;

	@Override
	public UserDetails loadUserByUsername(String matricula) throws UsernameNotFoundException {
		Funcionario funcionario = fr.findByMatricula(matricula);
		if (funcionario == null) {
			throw new UsernameNotFoundException("Dados incorretos!");
		}
		return funcionario;
	}

	public Funcionario save(Funcionario usuario) {
		usuario.setSenha(bcryptEncoder.encode(usuario.getPassword()));
		return fr.save(usuario);
	}
}