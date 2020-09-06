package io.platformbuilders.springbootdemo.cliente.service.impl;

import java.time.LocalDate;
import java.time.Period;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;

import io.platformbuilders.springbootdemo.cliente.dto.ClienteDTO;
import io.platformbuilders.springbootdemo.cliente.entity.Cliente;
import io.platformbuilders.springbootdemo.cliente.enumeration.PageOrderEnum;
import io.platformbuilders.springbootdemo.cliente.excpetion.ClienteNotFoundException;
import io.platformbuilders.springbootdemo.cliente.excpetion.NegocioException;
import io.platformbuilders.springbootdemo.cliente.repository.ClienteRepository;
import io.platformbuilders.springbootdemo.cliente.service.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService {
	
	private ClienteRepository clienteRepository;
	
	@Autowired
	public ClienteServiceImpl(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}

	@Override
	public Cliente save(ClienteDTO dto) throws NegocioException {
		
		Cliente cliente = convertDTOToEntity(dto);
		
		checkCliente(cliente);
		
		return clienteRepository.save(cliente);
	}

	@Override
	public Cliente findById(Long id) throws ClienteNotFoundException {
		return clienteRepository.findById(id).orElseThrow(() -> 
			new ClienteNotFoundException());
	}

	private int calcularIdade(LocalDate dataNascimento) {
		return Period.between(dataNascimento, LocalDate.now()).getYears();
	}
	
	private void checkCliente(Cliente cli) throws NegocioException {
		
		if(cli.getDataNascimento().isAfter(LocalDate.now())) {
			throw new NegocioException("Error 001");
		}
	}
	
	@Override
	public Page<ClienteDTO> findAllCliente(String cpf, String nome, int page, 
			PageOrderEnum order) {
		Sort sort = Direction.ASC.name().equals(order.getValue()) ? 
				Sort.by("id").ascending() : Sort.by("id").descending();
		PageRequest pg = PageRequest.of(page, 1, sort);
		return clienteRepository.findByCpfAndNome(cpf, nome, pg).map(e -> convertEntityToDTO(e));
	}
	
	@Override
	public ClienteDTO convertEntityToDTO(Cliente cliente) {
		ModelMapper modelMapper = new ModelMapper();
		ClienteDTO dto = modelMapper.map(cliente, ClienteDTO.class);
		dto.setIdade(calcularIdade(cliente.getDataNascimento()));
		return dto;
	}
	
	@Override
	public Cliente convertDTOToEntity(ClienteDTO dto) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(dto, Cliente.class);
	}

	@Override
	public void deleteById(Long id) {
		clienteRepository.deleteById(id);
	}

	@Override
	public void partialUpdateCliente(Cliente clienteToFind, String dto) throws NegocioException {
		
		 ObjectMapper objectMapper = new ObjectMapper();
		 ObjectReader updater = objectMapper.readerForUpdating(clienteToFind);
		 
		 try {
			 
			Cliente e = updater.readValue(dto);
			
			checkCliente(e);
			
			clienteRepository.save(e);
			
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
}
