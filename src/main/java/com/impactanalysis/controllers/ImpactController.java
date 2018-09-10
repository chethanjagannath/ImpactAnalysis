package com.impactanalysis.controllers;


import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.impactanalysis.dto.GitRequestDTO;
import com.impactanalysis.dto.ImpactDTO;
import com.impactanalysis.services.ImpactService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin
@RestController
@RequestMapping(value="/ImpactAnalysis")
@Api(value="ImpactAnalysis", description="API to display Impacted TestSuites (In between two commits to GitHub)")
@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"), @ApiResponse(code = 401, message = "You are not authorized to view the resource"), @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"), @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
public class ImpactController {
	
	@Autowired
	private ImpactService impactService;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@ApiOperation(value = "ImpactAnalysis APIs HealthCheck", response = String.class)
	@GetMapping("/healthCheck")
	public String testApp() {
		return "ImpactAnalysis APIs are fine";
	}
	
	@ApiOperation(value = "Display TestSuites names for the modified files between 2 Commit Ids", response = ImpactDTO.class)
	@PostMapping(value = "/fetchImpactedTestSuites", consumes = "application/json")
	public ImpactDTO fetchImpactedTestSuites(@RequestBody GitRequestDTO gitRequestDTO, @RequestParam("fullInfo") boolean fullInfo) {
		long startTime = System.currentTimeMillis();
		ImpactDTO impactDTO = impactService.fetchImpactedTestSuites(gitRequestDTO, fullInfo);
		logger.info(String.format("API::fetchImpactedTestSuites Request=%s, Response=%s, TimeTaken=%s Milliseconds", gitRequestDTO, impactDTO, System.currentTimeMillis()-startTime));
		return impactDTO;
	}
	
	@ApiOperation(value = "Display TestSuites names for the modified files between 2 Commit Ids", response = ImpactDTO.class)
	@GetMapping(value = "/fetchImpactedTestSuites", consumes = "application/json")
	public Set<String> fetchImpactedTestSuites(@RequestParam("repositoryName") String repositoryName, 
			@RequestParam("repositoryOwnerId") String repositoryOwnerId, @RequestParam("branchName") String branchName) {
		long startTime = System.currentTimeMillis();
		Set<String> impactedTestSuites = impactService.fetchImpactedTestSuites(repositoryName, repositoryOwnerId, branchName);
		logger.info(String.format("API::fetchImpactedTestSuites repositoryName=%s, repositoryOwner=%s, branchName=%s, Response=%s, TimeTaken=%s Milliseconds", repositoryName, repositoryOwnerId, branchName, impactedTestSuites, System.currentTimeMillis()-startTime));
		return impactedTestSuites;
	}
}