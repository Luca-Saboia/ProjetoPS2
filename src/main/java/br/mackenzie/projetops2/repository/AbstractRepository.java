package br.mackenzie.projetops2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean 
public interface AbstractRepository<T, ID> extends JpaRepository<T, ID> {}