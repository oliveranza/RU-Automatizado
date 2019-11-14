package com.ru.automatizado.ruautomatizado.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ru.automatizado.ruautomatizado.model.Funcionario;
import com.ru.automatizado.ruautomatizado.repository.FuncionarioRepository;
import com.ru.automatizado.ruautomatizado.security.MyUserDetailService;

@Controller
@RequestMapping("/funcionario")
public class FuncionarioController {

	@Autowired
	private MyUserDetailService funcionarios;
	private static final String CADASTRO_VIEW = "TelaCadastroFuncionario";
	private static final String LISTAR_VIEW = "TelaListarFuncionarios";
	
	@RequestMapping("/cadastro")
	public ModelAndView telaCadastroFuncionario() {
		ModelAndView modelAndView = new ModelAndView(CADASTRO_VIEW);
		modelAndView.addObject(new Funcionario());
		return modelAndView;
	}
	
	@RequestMapping("/lista")
	public ModelAndView telaListarFuncionario() {
		ModelAndView modelAndView = new ModelAndView(LISTAR_VIEW);
		modelAndView.addObject(new Funcionario());
		return modelAndView;
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
}
