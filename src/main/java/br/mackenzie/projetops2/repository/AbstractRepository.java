package br.mackenzie.projetops2.repository;

import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean // Indica ao Spring Data para não tentar criar uma instância direta desta interface
public interface AbstractRepository<T, ID> {
    // Aqui você colocaria métodos genéricos customizados se fosse necessário
}