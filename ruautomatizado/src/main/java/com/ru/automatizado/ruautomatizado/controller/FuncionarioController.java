package com.ru.automatizado.ruautomatizado.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ru.automatizado.ruautomatizado.model.Funcionario;
import com.ru.automatizado.ruautomatizado.repository.Funcionarios;
import com.ru.automatizado.ruautomatizado.security.MyUserDetailService;

@Controller
@RequestMapping("/funcionario")
public class FuncionarioController {

	@Autowired
	private MyUserDetailService funcionarios;
	//Criei um repositório
	@Autowired
	private Funcionarios funcionariosJPA;
	private static final String CADASTRO_VIEW = "TelaCadastroFuncionario";
	private static final String LISTAR_VIEW = "TelaListarFuncionarios";
	
	@RequestMapping("/cadastro")
	public ModelAndView telaCadastroFuncionario() {
		ModelAndView modelAndView = new ModelAndView(CADASTRO_VIEW);
		modelAndView.addObject(new Funcionario());
		return modelAndView;
	}	
	
	@RequestMapping
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView(LISTAR_VIEW);
		List<Funcionario>todosFuncionarios = funcionariosJPA.findAll();
		mv.addObject("allFuncionarios",todosFuncionarios);
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String cadastrar(@Validated Funcionario funcionario, Errors errors, RedirectAttributes attributes) {
		if (errors.hasErrors()) {
			return CADASTRO_VIEW;
		}
		
		funcionariosJPA.save(funcionario);
		attributes.addFlashAttribute("mensagem", "Funcionário Salvo Com Sucesso!");
		
		
		return "redirect:/funcionario/cadastro";
		
	}
	
	
	@RequestMapping("{matricula}")
	public ModelAndView editar(@PathVariable("matricula")Funcionario funcionario) {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(funcionario);
		return mv;
	}
}
