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

import com.impactanalysis.dto.MappingRequestDTO;
import com.impactanalysis.entities.MappingEntity;
import com.impactanalysis.services.MappingService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin
@RestController
@RequestMapping(value="/Mappings")
@Api(value="Mappings", description="APIs for Developers (API<<-->>Files Mappings) & QA (API<<-->>Test Suites Mappings)")
@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"), @ApiResponse(code = 401, message = "You are not authorized to view the resource"), @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"), @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
public class MappingController {
	
	@Autowired
	private MappingService mappingService;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@ApiOperation(value = "Mapping APIs HealthCheck", response = String.class)
	@GetMapping("/healthCheck")
	public String testApp() {
		return "Mapping APIs are fine";
	}
	
	@ApiOperation(value = "Add API details to DB (API<<-->>Files Mappings & API<<-->>Test Suites Mappings)", response = MappingEntity.class)
	@PostMapping(value="/createAPI")
	public MappingEntity createAPI(@RequestBody MappingRequestDTO mappingRequest) {
		long startTime = System.currentTimeMillis();
		MappingEntity mappingEntity =  mappingService.createAPI(mappingRequest);
		logger.info(String.format("API::createAPI Request=%s, Response=%s, TimeTaken=%s Milliseconds", mappingRequest, mappingEntity, System.currentTimeMillis()-startTime));
		return mappingEntity;
	}
	
	@ApiOperation(value = "Update API details to DB (API<<-->>Files Mappings & API<<-->>Test Suites Mappings)", response = MappingEntity.class)
	@PutMapping(value="/updateAPI")
	public MappingEntity updateAPI(@RequestBody MappingRequestDTO mappingRequest) {
		long startTime = System.currentTimeMillis();
		MappingEntity mappingEntity =  mappingService.updateAPI(mappingRequest);
		logger.info(String.format("API::updateAPI Request=%s, Response=%s, TimeTaken=%s Milliseconds", mappingRequest, mappingEntity, System.currentTimeMillis()-startTime));
		return mappingEntity;
	}
	
	@ApiOperation(value = "Delete API details from DB by passing ApiId", response = Void.class)
	@DeleteMapping(value="/deleteAPI/{apiId}")
	public void deleteAPI(@PathVariable("apiId") Integer apiId) {
		long startTime = System.currentTimeMillis();
		mappingService.deleteAPI(apiId);
		logger.info(String.format("API::deleteAPI apiId=%s, TimeTaken=%s Milliseconds", apiId, System.currentTimeMillis()-startTime));
	}
	
	@ApiOperation(value = "Get API details from DB by passing Api Id", response = MappingEntity.class)
	@GetMapping(value="/getAPIById/{apiId}")
	public MappingEntity getAPIById(@PathVariable("apiId") Integer apiId) {
		long startTime = System.currentTimeMillis();
		MappingEntity mappingEntity =  mappingService.getAPIById(apiId);
		logger.info(String.format("API::getAPIById apiId=%s, Response=%s, TimeTaken=%s Milliseconds", apiId, mappingEntity, System.currentTimeMillis()-startTime));
		return mappingEntity;
	}
	
	@ApiOperation(value = "Get API details from DB by passing Api Name", response = List.class)
	@GetMapping(value="/getAPIByName/{apiName}")
	public List<MappingEntity> getAPIByName(@PathVariable("apiName") String apiName) {
		long startTime = System.currentTimeMillis();
		List<MappingEntity> mappingEntities =  mappingService.getAPIByName(apiName);
		logger.info(String.format("API::getAPIByName apiName=%s, Response=%s, TimeTaken=%s Milliseconds", apiName, mappingEntities, System.currentTimeMillis()-startTime));
		return mappingEntities;
	}
	
	@ApiOperation(value = "Get all API details from DB (API<<-->>Files Mappings & API<<-->>Test Suites Mappings)", response = List.class)
	@GetMapping(value="/getAllAPI")
	public List<MappingEntity> getAllAPI() {
		long startTime = System.currentTimeMillis();
		List<MappingEntity> mappingEntities = mappingService.getAllAPI();
		logger.info(String.format("API::getAllAPI Response=%s, TimeTaken=%s Milliseconds", mappingEntities, System.currentTimeMillis()-startTime));
		return mappingEntities;
	}
}
