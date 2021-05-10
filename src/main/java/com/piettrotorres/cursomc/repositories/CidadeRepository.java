package com.piettrotorres.cursomc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.piettrotorres.cursomc.domain.Cidade;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Integer>{
	
	@Transactional(readOnly = true)
	@Query("SELECT obj from Cidade obj WHERE obj.estado.id = :estadoID order by obj.nome")
	List<Cidade> findByEstado(@Param("estadoID")Integer id);

}
