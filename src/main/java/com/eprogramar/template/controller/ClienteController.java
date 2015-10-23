package com.eprogramar.template.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.eprogramar.template.model.Cliente;
import com.eprogramar.template.repositories.ClienteRepository;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

	@Autowired
	private ClienteRepository dao;
	
	@RequestMapping("/list")
	public String list(Model model){
		model.addAttribute("list", dao.findAll());
		return "cliente";
	}
	
	@RequestMapping
	public String add(Model model){
		return "cliente";
	}

	@RequestMapping("/{id}")
	public String edit(@PathVariable("id") Long id, Model model){
		model.addAttribute("cliente", dao.findOne(id));
		return "cliente";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String save(Cliente cliente, RedirectAttributes redirectAttributes){
		dao.save(cliente);
		redirectAttributes.addFlashAttribute("message", "Registro salvo com sucesso!!!");
		return "redirect:/cliente/list";
	}
	
	@RequestMapping("/remove/{id}")
	public String remove(@PathVariable("id") Long id, RedirectAttributes redirectAttributes){
		dao.delete(id);
		redirectAttributes.addFlashAttribute("message", "Registro excluido com sucesso!!!");
		return "redirect:/cliente/list";
	}	
	
}

