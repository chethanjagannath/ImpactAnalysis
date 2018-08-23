package com.impactanalysis.controllers;

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
import com.impactanalysis.dto.GitResponseDTO;
import com.impactanalysis.services.GitService;

@RestController
@CrossOrigin
@RequestMapping(value = "/ImpactAnalysis")
public class GitController {
	
	@Autowired
	private GitService gitService;

	private static final Logger logger = LoggerFactory.getLogger(GitController.class);

	@GetMapping("/testApplication")
	public String testApp() {
		return "ImpactAnalysis Application is Up & Running";
	}
	
	@PostMapping(value = "/getCommitDetailsByDate" , consumes = "application/json")
	public GitResponseDTO getCommitDetailsByDate(@RequestBody GitRequestDTO gitRequestDTO) {
		GitResponseDTO gitResponseDTO = gitService.getCommitDetailsByDate(gitRequestDTO);
		logger.info("GetCommitDetailsByDate Request:" + gitRequestDTO + "::Response:" + gitResponseDTO);
		return gitResponseDTO;
	}

	@GetMapping(value = "/getCommitDetailsByCommitId", consumes = "application/json")
	public GitResponseDTO getCommitDetailsByCommitId(@RequestParam (value="commitId") String commitId) {
		GitResponseDTO gitResponseDTO = gitService.getCommitDetailsByCommitId(commitId);
		logger.info("GetCommitDetailsByDate Request:" + commitId + "::Response:" + gitResponseDTO);
		return gitResponseDTO;
	}

	@GetMapping(value = "/getCommitDetailsBetweenCommitIds", consumes = "application/json")
	public GitResponseDTO getCommitDetailsBetweenCommitIds(@RequestParam (value="commitId1") String commitId1, @RequestParam (value="commitId1") String commitId2) {
		GitResponseDTO gitResponseDTO = gitService.getCommitDetailsBetweenCommitIds(commitId1 , commitId1);
		logger.info("::Response:" + gitResponseDTO);
		return gitResponseDTO;
	}
}
