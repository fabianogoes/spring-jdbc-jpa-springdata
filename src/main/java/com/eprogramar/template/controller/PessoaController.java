package com.eprogramar.template.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.eprogramar.template.dao.PessoaDAO;
import com.eprogramar.template.model.Pessoa;

@Controller
@RequestMapping("/pessoa")
public class PessoaController {

	@Autowired
	private PessoaDAO dao;
	
	@RequestMapping("/list")
	public String list(Model model){
		model.addAttribute("list", dao.findAll());
		return "pessoa";
	}
	
	@RequestMapping
	public String add(Model model){
		return "pessoa";
	}

	@RequestMapping("/{id}")
	public String edit(@PathVariable("id") Long id, Model model){
		model.addAttribute("pessoa", dao.findById(id));
		return "pessoa";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String save(Pessoa pessoa){
		dao.save(pessoa);
		return "redirect:/pessoa";
	}
	
	@RequestMapping("/remove/{id}")
	public String remove(@PathVariable("id") Long id){
		dao.remove(id);
		return "redirect:/pessoa";
	}	
	
}

