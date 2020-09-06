package io.platformbuilders.springbootdemo.cliente.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Class that implements Transaction data transfer object (DTO)
 * 
 * @author Mariana Azevedo
 * @since 01/04/2020
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class ClienteDTO extends RepresentationModel<ClienteDTO> {
	
	private Long id;
	
	@NotNull(message="NOME cannot be null")
	@Size(min=1, max=100, message="NOME must contain 100 characters")
	private String nome;
	
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	@NotNull(message="DATA NASCIMENTO cannot be null")
	private LocalDate dataNascimento;
	
	@NotNull(message="CPF cannot be null")
	@CPF
	private String cpf;
	
    @ApiModelProperty(hidden = true)
	private int idade;

}
