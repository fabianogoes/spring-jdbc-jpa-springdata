package com.eprogramar.template.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.eprogramar.template.dao.PessoaDAO;
import com.eprogramar.template.model.Pessoa;

@Controller
public class HomeController {
	
	@Autowired
	private PessoaDAO dao;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		
		
		Pessoa pessoa = new Pessoa(null, "Teste 1");
		this.dao.save(pessoa);
		System.out.println( pessoa );
		
		
		return "home";
	}
	
}
