package com.boot.exceptions;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.exception.DataException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.boot.dto.response.ResponseDto;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import javassist.NotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(NotFoundException.class)
	public @ResponseBody ResponseEntity<List<ResponseDto>> handleNotFound(Exception ex){
		List<ResponseDto> ResponseDtos = new ArrayList<ResponseDto>();
		ResponseDto er = new ResponseDto("FAIL", 500, ex.getMessage());
		ResponseDtos.add(er);
		return new ResponseEntity<List<ResponseDto>>(ResponseDtos,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(DataException.class)
	public @ResponseBody ResponseEntity<List<ResponseDto>> handleSQLException(Exception ex){
		List<ResponseDto> ResponseDtos = new ArrayList<ResponseDto>();
		ResponseDto er = new ResponseDto("FAIL", 500, ex.getMessage());
		ResponseDtos.add(er);
		return new ResponseEntity<List<ResponseDto>>(ResponseDtos,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public @ResponseBody ResponseEntity<List<ResponseDto>> handleInvalidArgument(MethodArgumentNotValidException ex){
		List<ObjectError> objectErrors = ex.getBindingResult().getGlobalErrors();
		List<FieldError> errors = ex.getBindingResult().getFieldErrors();
		List<ResponseDto> ResponseDtos = new ArrayList<ResponseDto>();
		for(ObjectError error : objectErrors) {
			ResponseDto ResponseDto = new ResponseDto("FAIL",400, error.getDefaultMessage());
			ResponseDtos.add(ResponseDto);
		}
		for(FieldError error : errors) {
			ResponseDto ResponseDto = new ResponseDto("FAIL",400, error.getField() + " " + error.getDefaultMessage());
			ResponseDtos.add(ResponseDto);
		}
		return new ResponseEntity<List<ResponseDto>>(ResponseDtos,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(InvalidFormatException.class)
	public @ResponseBody ResponseEntity<List<ResponseDto>> handleInvalidFormat(InvalidFormatException ex){
		List<ResponseDto> ResponseDtos = new ArrayList<ResponseDto>();
		ResponseDto er;
		if(ex.getCause().getClass() == DateTimeParseException.class) {
			er = new ResponseDto("FAIL",400, "Date format has been written in wrong format!"
					+ "The format should be 'dd-MM-yyyy HH:mm'.");
		}
		
		else{
			er = new ResponseDto("FAIL",400, ex.getOriginalMessage());
		}
		ResponseDtos.add(er);
		return new ResponseEntity<List<ResponseDto>>(ResponseDtos,HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(NotEnoughMoneyException.class)
	public @ResponseBody ResponseEntity<List<ResponseDto>> handleMoneyException(NotEnoughMoneyException ex){
		ResponseDto ResponseDto = new ResponseDto("FAIL", 500, ex.getMessage());
		List<ResponseDto> responseDto = new ArrayList<ResponseDto>();
		responseDto.add(ResponseDto);
		return new ResponseEntity<List<ResponseDto>>(responseDto,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(AlreadyExistException.class)
	public @ResponseBody ResponseEntity<List<ResponseDto>> handleAlreadyExistException(AlreadyExistException ex){
		ResponseDto ResponseDto = new ResponseDto("FAIL", 409, ex.getMessage());
		List<ResponseDto> responseDto = new ArrayList<ResponseDto>();
		responseDto.add(ResponseDto);
		return new ResponseEntity<List<ResponseDto>>(responseDto,HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public @ResponseBody ResponseEntity<List<ResponseDto>> handleEnumException(HttpMessageNotReadableException ex){
		ResponseDto ResponseDto = new ResponseDto("FAIL", 400, "You entered invalid enum type!");
		List<ResponseDto> responseDto = new ArrayList<ResponseDto>();
		responseDto.add(ResponseDto);
		return new ResponseEntity<List<ResponseDto>>(responseDto,HttpStatus.BAD_REQUEST);
	}
	
	
}
