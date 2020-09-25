package com.example.pswman.service;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pswman.model.Senha;
import com.example.pswman.repository.SenhaRepository;
import com.example.pswman.util.DateHelper;

@Service
@Transactional
public class SenhaService {

	@Autowired
	private SenhaRepository repository;

	public Senha generatePassworld(String type) throws Exception{
		if(type == null || type.equals(null)) {
			throw new Exception("Tipo de senha não especificado");
		}
		
		Senha nova = new Senha();

		Senha maior = this.repository.findGreaterPassworld(new Date(), type);
		
		String value;
		
		if(maior != null)
			value = (type + (String.format("%04d", Integer.parseInt(maior.getValor().substring(1)) + 1)));
		else
			value = (type + "0001");
		
		System.err.println("generating normal password " + value);
		nova.setValor(value);

		validate(nova);
		return this.repository.save(nova);

	}

	public boolean validate(Senha nova) {
		if (nova == null || nova.getValor() == null)
			return false;

		String senha = nova.getValor();

		if (senha.length() < 5)
			return false;

		if (senha.length() == 5) {
			if (!(senha.startsWith("N", 1) || senha.startsWith("P", 1))) {
				return false;
			}
		}

		Senha s = this.repository.findByValue(DateHelper.formatedData(new Date()), senha);
		if (s != null)
			return false;
		else
			return true;
	}
	
	public Senha getSenhaAtual() {
		Senha maior = this.repository.findLowestPassworld(new Date(), "P");
		
		if(maior == null)
			maior=  this.repository.findLowestPassworld(new Date(), "N");
		
		if(maior == null) {
			maior = new Senha();
			maior.setValor("Nenhuma senha à espera no momento");
		}
		
		return maior; 
	}
	
	public Senha chamarProxima() {
		Senha maior = getSenhaAtual();
		
		if(maior.getId() != null) {
			maior.setChamada(true);
			this.repository.save(maior);
			System.err.println("Calling password " + maior.getValor());
			
			return this.getSenhaAtual();
		}
		else {
			Senha vazia = new Senha();
			vazia.setValor("Nenhuma senha à espera no momento");
			return vazia;
		}
			
	}
	
	public void resetDay() {
		this.repository.resetDay(new Date());
	}

}
