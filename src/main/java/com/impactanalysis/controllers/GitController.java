package com.impactanalysis.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.impactanalysis.dto.GitRequestDTO;
import com.impactanalysis.dto.GitResponseDTO;
import com.impactanalysis.services.GitService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@CrossOrigin
@RequestMapping(value = "/ImpactAnalysis")
@Api(value="ImpactAnalysisApp", description="Impact Analysis Application")
public class GitController {
	
	@Autowired
	private GitService gitService;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@ApiOperation(value = "Application HealthCheck", response = String.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"), @ApiResponse(code = 401, message = "You are not authorized to view the resource"), @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"), @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
	@GetMapping("/healthCheck")
	public String testApp() {
		return "ImpactAnalysis Application is Up & Running";
	}
	
	@ApiOperation(value = "Fetch all the commits for a day", response = GitResponseDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"), @ApiResponse(code = 401, message = "You are not authorized to view the resource"), @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"), @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
	@PostMapping(value = "/getCommitDetailsByDate" , consumes = "application/json")
	public GitResponseDTO getCommitDetailsByDate(@RequestBody GitRequestDTO gitRequestDTO) {
		long startTime = System.currentTimeMillis();
		GitResponseDTO gitResponseDTO = gitService.getCommitDetailsByDate(gitRequestDTO);
		logger.info(String.format("API::GetCommitDetailsByDate Request=%s, Response=%s, TimeTaken=%s Milliseconds", gitRequestDTO, gitResponseDTO, System.currentTimeMillis()-startTime));
		return gitResponseDTO;
	}
	

	@ApiOperation(value = "Fetch commit details for a commit id", response = GitResponseDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"), @ApiResponse(code = 401, message = "You are not authorized to view the resource"), @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"), @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
	@PostMapping(value = "/getCommitDetailsByCommitId", consumes = "application/json")
	public GitResponseDTO getCommitDetailsByCommitId(@RequestBody GitRequestDTO gitRequestDTO) {
		long startTime = System.currentTimeMillis();
		GitResponseDTO gitResponseDTO = gitService.getCommitDetailsByCommitId(gitRequestDTO);
		logger.info(String.format("API::GetCommitDetailsByCommitId Request=%s, Response=%s, TimeTaken=%s Milliseconds", gitRequestDTO, gitResponseDTO, System.currentTimeMillis()-startTime));
		return gitResponseDTO;
	}
	
	@ApiOperation(value = "Fetch commit details for a commit id", response = GitResponseDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"), @ApiResponse(code = 401, message = "You are not authorized to view the resource"), @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"), @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
	@PostMapping(value = "/getCommitDetailsBetweenCommitIds", consumes = "application/json")
	public GitResponseDTO getCommitDetailsBetweenCommitIds(@RequestBody GitRequestDTO gitRequestDTO) {
		long startTime = System.currentTimeMillis();
		GitResponseDTO gitResponseDTO = gitService.getCommitDetailsBetweenCommitIds(gitRequestDTO);
		logger.info(String.format("API::getCommitDetailsBetweenCommitIds Request=%s, Response=%s, TimeTaken=%s Milliseconds", gitRequestDTO, gitResponseDTO, System.currentTimeMillis()-startTime));
		return gitResponseDTO;
	}
	
}
