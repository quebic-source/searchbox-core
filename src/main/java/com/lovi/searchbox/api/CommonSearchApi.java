package com.lovi.searchbox.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lovi.searchbox.api.dto.QueryDto;
import com.lovi.searchbox.exception.InputParmsNotFound;
import com.lovi.searchbox.service.SearchBoxOperations;

@RestController
@RequestMapping("/search")
public class CommonSearchApi {

	@Autowired
	private SearchBoxOperations redboxService;
	
	@RequestMapping("/query")
	public ResponseEntity<?> query(@ModelAttribute QueryDto queryDto) throws Exception{
		if(StringUtils.isEmpty(queryDto.getName())){
			throw new InputParmsNotFound("name");
		}
		return new ResponseEntity<>(redboxService.search(queryDto.getName(), queryDto.getParms(), queryDto.getPage()), HttpStatus.ACCEPTED);
		
	}
			
	
}
