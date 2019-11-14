package com.ru.automatizado.ruautomatizado.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ru.automatizado.ruautomatizado.model.Funcionario;
import com.ru.automatizado.ruautomatizado.security.MyUserDetailService;

@Controller
public class UserController {
	
	@Autowired
	private MyUserDetailService usuarioService;
	
	@GetMapping("/login")
	public String root() {
		return "TelaLogin";
	}

	@GetMapping("/gerenciar")
	public String gerenciar() {
		return "Inicio";
	}

	/*@GetMapping("/new-user")
	public String newUser() {
		Funcionario usuario = new Funcionario();
		usuario.setMatricula("admin");
		usuario.setNome("admin");
		usuario.setSenha("admin");
		usuarioService.save(usuario);
		return usuario.toString();
	}*/
}