package com.example.pswman.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.pswman.model.Senha;
import com.example.pswman.service.SenhaService;

@RestController
@RequestMapping("/senha")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.OPTIONS, RequestMethod.DELETE })
public class SenhaController {

	@Autowired
	private SenhaService service;
	
	@GetMapping("/atual")
	private Senha senhaAtual(){
		return this.service.getSenhaAtual();
	}

	@GetMapping("/GNormal")
	public Senha generateNormalPassworld() throws Exception {
		return this.service.generatePassworld("N");
	}
	
	@GetMapping("/GPreferencial")
	public Senha generatePreferencialPassworld() throws Exception {
		return this.service.generatePassworld("P");
	}

	@GetMapping("/chamarProxima")
	public Senha chamarProxima() throws Exception {
		return this.service.chamarProxima();
	}
	
	@GetMapping("/resetDay")
	public void resetDay() {
		System.err.println("reseting passwords");
		this.service.resetDay();
	}
}
