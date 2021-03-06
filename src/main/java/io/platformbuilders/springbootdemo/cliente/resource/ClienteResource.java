package io.platformbuilders.springbootdemo.cliente.resource;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import io.platformbuilders.springbootdemo.cliente.controller.ClienteController;
import io.platformbuilders.springbootdemo.cliente.dto.ClienteDTO;
import io.platformbuilders.springbootdemo.cliente.entity.Cliente;
import io.platformbuilders.springbootdemo.cliente.exception.ClienteNotFoundException;
import io.platformbuilders.springbootdemo.cliente.exception.NegocioException;

@Component
public class ClienteResource {

	public void createSelfLink(Cliente cliente, ClienteDTO clienteDTO) throws NegocioException {
		clienteDTO.setId(cliente.getId());
		clienteDTO.add(linkTo(methodOn(ClienteController.class).delete(clienteDTO.getId())).withRel("delete"));
		clienteDTO.add(linkTo(methodOn(ClienteController.class).update(clienteDTO, null)).withRel("update"));
		clienteDTO.add(WebMvcLinkBuilder.linkTo(ClienteController.class).slash(cliente.getId()).withSelfRel());
	}
	
	public void createSelfLinkInCollections(final ClienteDTO clienteDTO) 
			throws ClienteNotFoundException, NegocioException {
		clienteDTO.setId(clienteDTO.getId());
		clienteDTO.add(linkTo(methodOn(ClienteController.class).findById(clienteDTO.getId())).withSelfRel());
		clienteDTO.add(linkTo(methodOn(ClienteController.class).delete(clienteDTO.getId())).withRel("delete"));
		clienteDTO.add(linkTo(methodOn(ClienteController.class).update(clienteDTO, null)).withRel("update"));
	}
	
}
