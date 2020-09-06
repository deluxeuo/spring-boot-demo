package io.platformbuilders.springbootdemo.cliente.dto.response;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.Tolerate;

/**
 * Class that implements a generic response error object to the API endpoints.
 */
@Getter
@Setter
@Accessors(chain = true)
@Builder
public class ClienteResponseError {
	
	@NotNull(message="Timestamp cannot be null")
	private LocalDateTime timestamp;
	
	@NotNull(message="Details cannot be null")
    private String details;

	@Tolerate
	public ClienteResponseError() {}
}