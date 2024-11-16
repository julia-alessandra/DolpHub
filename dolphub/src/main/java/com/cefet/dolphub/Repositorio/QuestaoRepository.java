package com.cefet.dolphub.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cefet.dolphub.Entidades.Recursos.*;

@Repository
public interface QuestaoRepository extends JpaRepository<Questao, Long> {
}