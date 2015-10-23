package com.eprogramar.template.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.eprogramar.template.model.Pessoa;
import com.eprogramar.template.repositories.PessoaRepository;

@Controller
@RequestMapping("/pessoa")
public class PessoaController {

	@Autowired
	private PessoaRepository dao;
	
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
		model.addAttribute("pessoa", dao.findOne(id));
		return "pessoa";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String save(Pessoa pessoa, RedirectAttributes redirectAttributes){
		dao.save(pessoa);
		redirectAttributes.addFlashAttribute("message", "Registro salvo com sucesso!!!");
		return "redirect:/pessoa/list";
	}
	
	@RequestMapping("/remove/{id}")
	public String remove(@PathVariable("id") Long id, RedirectAttributes redirectAttributes){
		dao.delete(id);
		redirectAttributes.addFlashAttribute("message", "Registro excluido com sucesso!!!");
		return "redirect:/pessoa/list";
	}	
	
}

