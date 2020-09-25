package com.example.pswman.repository;

import java.util.Date;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.example.pswman.model.Senha;

public interface SenhaRepository extends JpaRepository<Senha, Integer> {

	@Query("select s from Senha s where valor = (select max(s.valor) from Senha s where s.valor like ?2% and CAST(dataCriacao as date) = ?1 and chamada = 'false') and CAST(dataCriacao as date) = ?1 and chamada = 'false'")
	public Senha findGreaterPassworld(Date atual, String type);
	
	@Query("select s from Senha s where valor = (select min(s.valor) from Senha s where s.valor like ?2% and CAST(dataCriacao as date) = ?1 and chamada = 'false') and CAST(dataCriacao as date) = ?1 and chamada = 'false'")
	public Senha findLowestPassworld(Date atual, String type);

	@Query("select s from Senha s where valor = ':value' and dataCriacao = ':atual' ")
	public Senha findByValue(String atual, String value);
	
	@Transactional
	@Modifying
	@Query("update Senha s set s.chamada = true where CAST(s.dataCriacao as date) = ?1")
	public void resetDay(Date date);
}
