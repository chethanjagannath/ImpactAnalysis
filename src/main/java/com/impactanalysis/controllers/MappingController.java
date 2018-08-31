package com.impactanalysis.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.impactanalysis.dto.MapRequestDTO;
import com.impactanalysis.dto.MapResponseDTO;
import com.impactanalysis.services.MappingService;

@CrossOrigin
@RestController
@RequestMapping(value="/api/")
public class MappingController {
	
	@Autowired
	private MappingService mappingService;
	
	@PostMapping(value="/createAPI")
	public List<MapResponseDTO> createAPI(@RequestBody MapRequestDTO mapRequest) {
		return mappingService.createAPI(mapRequest);
	}
	
	@PostMapping(value="/updateAPI")
	public List<MapResponseDTO> updateAPI(@RequestBody MapRequestDTO mapRequest) {
		return mappingService.updateAPI(mapRequest);
	}
	
	@PostMapping(value="/deleteAPI")
	public List<MapResponseDTO> deleteAPI(@PathVariable("apiId") Integer apiId) {
		return mappingService.deleteAPI(apiId);
	}
	
	@GetMapping(value="/getAPIById")
	public MapResponseDTO getAPIById(@PathVariable("apiId") Integer apiId) {
		return mappingService.getAPIById(apiId);
	}
	
	@PostMapping(value="/getAllAPI")
	public List<MapResponseDTO> getAllAPI(@RequestBody MapRequestDTO mapRequest) {
		return mappingService.getAllAPI(mapRequest);
	}
}
