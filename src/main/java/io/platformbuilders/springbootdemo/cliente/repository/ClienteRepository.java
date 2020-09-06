package io.platformbuilders.springbootdemo.cliente.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import io.platformbuilders.springbootdemo.cliente.entity.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente, Long> {

	Page<Cliente> findByCpfAndNome(String cpf, String nome, Pageable pg);

}
