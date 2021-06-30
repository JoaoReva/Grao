package com.grao.restservice;

import org.springframework.web.bind.MethodArgumentNotValidException;
import java.util.List;
import com.grao.data.response.ErrorResponseDTO;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
import java.util.stream.Collectors;
import com.grao.exception.ErrorException;

public abstract class BasicController{
	
    /**
	 * Method that check against {@code @Valid} Objects passed to controller endpoints
	 *
	 * @param exception
	 * @return a {@code ErrorResponseDTO}
	 * @see com.grao.data.response.ErrorResponseDTO
	 */
	@ExceptionHandler(value=MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public List<ErrorResponseDTO> handleException(MethodArgumentNotValidException exception) {

		List<ErrorResponseDTO> errorMessages = exception.getBindingResult().getFieldErrors().stream()
				.map(err -> new ErrorResponseDTO(err.getField(), err.getRejectedValue(), err.getDefaultMessage()))
				.distinct()
				.collect(Collectors.toList());
		return errorMessages;
	}
	
	/**
	 * Method that check against ErrorException
	 *
	 * @param exception
	 * @return a {@code ErrorResponseDTO}
	 * @see com.grao.data.response.ErrorResponseDTO
	 */
	@ExceptionHandler(value=ErrorException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResponseDTO handleException(ErrorException exception) {
		ErrorResponseDTO errorDto = new ErrorResponseDTO();
		
		errorDto.setMessageError(exception.getMessage());
		
		return errorDto;
	}
}