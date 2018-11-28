package com.riahi.integration.broker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import com.riahi.entities.Categorie;
import com.riahi.service.ProductMangerService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Jalel Eddine
 *
 * 27 nov. 2018
 */

@Component
@Slf4j
@EnableBinding(ApiStreamTopicConfig.class)

public class EventProcessorBroker {

	@Autowired
	public ProductMangerService productMangerService ;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EventProcessorBroker.class) ;
	
	@StreamListener(ApiStreamTopicConfig.INPUT_CAT)
	public void handlerCategorie( Categorie categorie) throws Exception{  
		
		log.info("Received CATEGORIE : {}", categorie.toString());
		
	productMangerService.enregistrerCategorie(categorie) ;

	}
	
	@StreamListener(ApiStreamTopicConfig.INPUT_ID_CAT)
	public void handlerDeleteSousCat( Long id) {
		productMangerService.supprimerCategorie(id);
	}
	
}