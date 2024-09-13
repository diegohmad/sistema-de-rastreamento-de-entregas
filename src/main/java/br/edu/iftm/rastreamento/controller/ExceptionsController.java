package br.edu.iftm.rastreamento.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.edu.iftm.rastreamento.service.exceptions.NaoAcheiException;

@ControllerAdvice
public class ExceptionsController {

	private static final Logger logger = LoggerFactory.getLogger(ExceptionsController.class);

	@ExceptionHandler(NaoAcheiException.class)
	public ResponseEntity<String> naoAchei(NaoAcheiException e) {
		logger.info("------------------------");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	}
}
