package io.platformbuilders.springbootdemo.cliente.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.platformbuilders.springbootdemo.cliente.dto.ClienteDTO;
import io.platformbuilders.springbootdemo.cliente.dto.response.ClienteResponse;
import io.platformbuilders.springbootdemo.cliente.entity.Cliente;
import io.platformbuilders.springbootdemo.cliente.enumeration.PageOrderEnum;
import io.platformbuilders.springbootdemo.cliente.exception.ClienteNotFoundException;
import io.platformbuilders.springbootdemo.cliente.exception.NegocioException;
import io.platformbuilders.springbootdemo.cliente.resource.ClienteResource;
import io.platformbuilders.springbootdemo.cliente.service.ClienteService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("/v1/clientes")
public class ClienteController {
	
	
	@Autowired
	private ClienteResource resource;
	
	@Autowired
	private ClienteService clienteService;

	ClienteController(ClienteService clienteService) {
		this.clienteService = clienteService;
	}
	
	@PostMapping
	@ApiOperation(value = "Route to create a cliente")
	public ResponseEntity<ClienteResponse<ClienteDTO>> create(@Valid @RequestBody ClienteDTO dto, BindingResult result) throws NegocioException {
		
		ClienteResponse<ClienteDTO> response = new ClienteResponse<>();
		
		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.addErrorMsgToResponse(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		Cliente cliente = clienteService.save(dto);
		ClienteDTO dtoSaved = clienteService.convertEntityToDTO(cliente);
		resource.createSelfLink(cliente, dtoSaved,result);
		
		response.setData(dtoSaved);
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
		
	}
	
	@GetMapping(value = "/{id}")
	@ApiOperation(value = "Route to find a cliente by your id in the API")
	public ResponseEntity<ClienteResponse<ClienteDTO>> findById(@PathVariable("id") Long clienteId) throws ClienteNotFoundException, NegocioException {
		
		ClienteResponse<ClienteDTO> response = new ClienteResponse<>();
		Cliente cliente = clienteService.findById(clienteId);
		
		ClienteDTO dto = clienteService.convertEntityToDTO(cliente);
		resource.createSelfLink(cliente, dto, null);
		response.setData(dto);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PutMapping(path = "/{id}")
	@ApiOperation(value = "Route to update a cliente")
	public ResponseEntity<ClienteResponse<ClienteDTO>> update(@Valid @RequestBody ClienteDTO dto, 
		BindingResult result) throws NegocioException {
		
		ClienteResponse<ClienteDTO> response = new ClienteResponse<>();

		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.addErrorMsgToResponse(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		Cliente clienteToFind = clienteService.findById(dto.getId());
		
		if (clienteToFind.getId().compareTo(dto.getId()) != 0) {
			throw new NegocioException("Error 004");
		}

		Cliente cliente = clienteService.save(dto);
		ClienteDTO dtoSaved = clienteService.convertEntityToDTO(cliente);
		
		resource.createSelfLink(cliente, dtoSaved, result);
		response.setData(dtoSaved);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}")
	@ApiOperation(value = "Route to delete a cliente in the API")
	public ResponseEntity<ClienteResponse<String>> delete(@PathVariable("id") Long clienteId) throws ClienteNotFoundException {
		
		ClienteResponse<String> response = new ClienteResponse<>();
		Cliente cliente = clienteService.findById(clienteId);
		
		clienteService.deleteById(cliente.getId());
		response.setData("Transaction id=" + cliente.getId() + " successfully deleted");
		
		return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
	}
	
	@GetMapping
	@ApiOperation(value = "Route to find all clientes of the API with cpf and name")
	public ResponseEntity<ClienteResponse<Page<ClienteDTO>>> findAllCliente(
		   @RequestParam String cpf, 
		   @RequestParam String nome,
		   @RequestParam(name="page", defaultValue = "0") int page,
		   @RequestParam(name="order", defaultValue = "ASC") String order) throws ClienteNotFoundException {
		
		ClienteResponse<Page<ClienteDTO>> response = new ClienteResponse<>();
		
		Page<ClienteDTO> clientesDTO = clienteService.findAllCliente(cpf, nome, 
				page, PageOrderEnum.getSortDirection(order));
		
		if (clientesDTO.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
		clientesDTO.stream().forEach(dto -> {
			try {
				resource.createSelfLinkInCollections(dto);
			} catch (ClienteNotFoundException e) {
				log.error("There are no clients with cpf= {} and nome= {}", cpf, nome);
			} catch (NegocioException e) {
				log.error("There are no clients with cpf= {} and nome= {}", cpf, nome);
			}
		});
		
		response.setData(clientesDTO);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PatchMapping("/{id}")
	@ApiOperation(value = "Route to update partial a cliente by your id in the API")
	public ResponseEntity<?> partialUpdateCliente(@Valid @RequestBody String dto, @PathVariable("id") Long id) throws NegocioException {
	    
		ClienteResponse<ClienteDTO> response = new ClienteResponse<>();
		
		Cliente cliente = clienteService.findById(id);
		
		clienteService.partialUpdateCliente(cliente,dto);
		
		ClienteDTO dtoSaved = clienteService.convertEntityToDTO(cliente);
	    
		resource.createSelfLink(cliente, dtoSaved,null);
		response.setData(dtoSaved);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
