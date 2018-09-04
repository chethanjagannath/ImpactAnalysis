package com.impactanalysis.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.impactanalysis.dto.GitRequestDTO;
import com.impactanalysis.dto.GitResponseDTO;
import com.impactanalysis.dto.ImpactDTO;
import com.impactanalysis.dto.MappingRequestDTO;
import com.impactanalysis.entities.MappingEntity;
import com.impactanalysis.services.MappingService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin
@RestController
@RequestMapping(value="/ImpactAnalysis/")
public class MappingController {
	
	@Autowired
	private MappingService mappingService;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
/*	@ApiOperation(value = "Fetch TestSuite names for the files changed between 2 Commit Ids", response = ImpactDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"), @ApiResponse(code = 401, message = "You are not authorized to view the resource"), @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"), @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
	@PostMapping(value = "/fetchImpactedTestSuites", consumes = "application/json")
	public ImpactDTO fetchImpactedTestSuites(@RequestBody GitRequestDTO gitRequestDTO) {
		long startTime = System.currentTimeMillis();
		ImpactDTO impactDTO = mappingService.fetchImpactedTestSuites(gitRequestDTO);
		logger.info(String.format("API::fetchImpactedTestSuites Request=%s, Response=%s, TimeTaken=%s Milliseconds", gitRequestDTO, impactDTO, System.currentTimeMillis()-startTime));
		return impactDTO;
	}*/
	
	@PostMapping(value="/createAPI")
	public MappingEntity createAPI(@RequestBody MappingRequestDTO mappingRequest) {
		return mappingService.createAPI(mappingRequest);
	}
	
	@PutMapping(value="/updateAPI")
	public MappingEntity updateAPI(@RequestBody MappingRequestDTO mappingRequest) {
		return mappingService.updateAPI(mappingRequest);
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
