package br.mackenzie.projetops2.repository;

import br.mackenzie.projetops2.model.LogPrevisao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogPrevisaoRepository extends JpaRepository<LogPrevisao, Long> { }