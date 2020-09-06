package io.platformbuilders.springbootdemo.cliente.service;


import org.springframework.data.domain.Page;

import io.platformbuilders.springbootdemo.cliente.dto.ClienteDTO;
import io.platformbuilders.springbootdemo.cliente.entity.Cliente;
import io.platformbuilders.springbootdemo.cliente.enumeration.PageOrderEnum;
import io.platformbuilders.springbootdemo.cliente.exception.NegocioException;

public interface ClienteService {

	Cliente save(ClienteDTO dto) throws NegocioException;

	Cliente findById(Long id);

	ClienteDTO convertEntityToDTO(Cliente cliente);

	Cliente convertDTOToEntity(ClienteDTO dto);

	Page<ClienteDTO> findAllCliente(String cpf, String nome, int page, PageOrderEnum sortDirection);

	void deleteById(Long id);

	void partialUpdateCliente(Cliente clienteToFind, String dto) throws NegocioException;
	
}
