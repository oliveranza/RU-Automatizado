package com.ru.automatizado.ruautomatizado.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
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
	private static final String EDICAO_VIEW = "TelaEdicaoFuncionario";
	
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
	
	@RequestMapping(value = "/cadastrar", method = RequestMethod.POST)
	public String cadastrar(@Validated Funcionario funcionario, Errors errors, RedirectAttributes attributes) {
		if (errors.hasErrors()) {
			return CADASTRO_VIEW;
		}
		
		funcionarios.save(funcionario);
		attributes.addFlashAttribute("mensagem", "Funcionário Cadastrado Com Sucesso!");
		return "redirect:/funcionario/cadastro";
	}
	
	@RequestMapping(value = "/editar", method = RequestMethod.POST)
	public String editar(@Validated Funcionario funcionario, Errors errors, RedirectAttributes attributes) {
		if (errors.hasErrors()) {
			return EDICAO_VIEW;
		}
		
		funcionarios.save(funcionario);
		attributes.addFlashAttribute("mensagem", "Funcionário Cadastrado Com Sucesso!");
		return "redirect:/funcionario/editar";
	}
}
