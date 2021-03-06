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

@CrossOrigin
@RestController
@RequestMapping(value="/ChangeAnalysis")
@Api(value="ChangeAnalysis", description="APIs to display modified set of files by contacting GitHub repositories")
@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"), @ApiResponse(code = 401, message = "You are not authorized to view the resource"), @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"), @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
public class GitController {
	
	@Autowired
	private GitService gitService;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@ApiOperation(value = "ChangeAnalysis APIs HealthCheck", response = String.class)
	@GetMapping("/healthCheck")
	public String testApp() {
		return "ChangeAnalysis APIs are fine";
	}
	
	@ApiOperation(value = "Displays modified set of files of a repository on a particular date", response = GitResponseDTO.class)
	@PostMapping(value = "/getCommitDetailsByDate" , consumes = "application/json")
	public GitResponseDTO getCommitDetailsByDate(@RequestBody GitRequestDTO gitRequestDTO) {
		long startTime = System.currentTimeMillis();
		GitResponseDTO gitResponseDTO = gitService.getCommitDetailsByDate(gitRequestDTO);
		logger.info(String.format("API::GetCommitDetailsByDate Request=%s, Response=%s, TimeTaken=%s Milliseconds", gitRequestDTO, gitResponseDTO, System.currentTimeMillis()-startTime));
		return gitResponseDTO;
	}
	
	@ApiOperation(value = "Displays Latest Commit Id of a Branch", response = GitResponseDTO.class)
	@PostMapping(value = "/getLatestCommitIdOfBranch" , consumes = "application/json")
	public String getLatestCommitIdOfBranch(@RequestBody GitRequestDTO gitRequestDTO) {
		long startTime = System.currentTimeMillis();
		String latestCommitId = gitService.getLatestCommitIdOfBranch(gitRequestDTO);
		logger.info(String.format("API::getLatestCommitIdOfBranch Request=%s, Response=%s, TimeTaken=%s Milliseconds", gitRequestDTO, latestCommitId, System.currentTimeMillis()-startTime));
		return latestCommitId;
	}
	
	@ApiOperation(value = "Displays modified set of files of a repository for a commit id", response = GitResponseDTO.class)
	@PostMapping(value = "/getCommitDetailsByCommitId", consumes = "application/json")
	public GitResponseDTO getCommitDetailsByCommitId(@RequestBody GitRequestDTO gitRequestDTO) {
		long startTime = System.currentTimeMillis();
		GitResponseDTO gitResponseDTO = gitService.getCommitDetailsByCommitId(gitRequestDTO);
		logger.info(String.format("API::GetCommitDetailsByCommitId Request=%s, Response=%s, TimeTaken=%s Milliseconds", gitRequestDTO, gitResponseDTO, System.currentTimeMillis()-startTime));
		return gitResponseDTO;
	}
	
	@ApiOperation(value = "Displays modified set of files of a repository between 2 commit ids", response = GitResponseDTO.class)
	@PostMapping(value = "/getCommitDetailsBetweenCommitIds", consumes = "application/json")
	public GitResponseDTO getCommitDetailsBetweenCommitIds(@RequestBody GitRequestDTO gitRequestDTO) {
		long startTime = System.currentTimeMillis();
		GitResponseDTO gitResponseDTO = gitService.getCommitDetailsBetweenCommitIds(gitRequestDTO);
		logger.info(String.format("API::getCommitDetailsBetweenCommitIds Request=%s, Response=%s, TimeTaken=%s Milliseconds", gitRequestDTO, gitResponseDTO, System.currentTimeMillis()-startTime));
		return gitResponseDTO;
	}
}