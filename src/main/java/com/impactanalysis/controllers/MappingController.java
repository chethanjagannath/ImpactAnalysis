package com.impactanalysis.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.impactanalysis.entities.MappingEntity;
import com.impactanalysis.services.MappingService;

@CrossOrigin
@RestController
@RequestMapping(value="/ImpactAnalysis/")
public class MappingController {
	
	@Autowired
	private MappingService mappingService;
	
	@PostMapping(value="/createAPI")
	public MappingEntity createAPI(@RequestBody MappingEntity mappingEntity) {
		return mappingService.createAPI(mappingEntity);
	}
	
	@PutMapping(value="/updateAPI")
	public MappingEntity updateAPI(@RequestBody MappingEntity mappingEntity) {
		return mappingService.updateAPI(mappingEntity);
	}
	
	@DeleteMapping(value="/deleteAPI/{apiId}")
	public void deleteAPI(@PathVariable("apiId") Integer apiId) {
		mappingService.deleteAPI(apiId);
	}
	
	@GetMapping(value="/getAPIById/{apiId}")
	public MappingEntity getAPIById(@PathVariable("apiId") Integer apiId) {
		return mappingService.getAPIById(apiId);
	}
	
	@GetMapping(value="/getAPIByName/{apiName}")
	public List<MappingEntity> getAPIByName(@PathVariable("apiName") String apiName) {
		return mappingService.getAPIByName(apiName);
	}
	
	@GetMapping(value="/getAllAPI")
	public List<MappingEntity> getAllAPI() {
		return mappingService.getAllAPI();
	}
}
