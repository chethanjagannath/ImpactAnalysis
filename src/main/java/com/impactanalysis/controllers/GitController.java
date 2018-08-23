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

	@PostMapping(value = "/getCommitDetailsByCommitId", consumes = "application/json")
	public void getCommitDetailsByCommitId(@RequestBody GitRequestDTO gitUserDTO) {
		//logger.info("GetCommitDetailsByCommitId Request:" + gitUserDTO + "::Response:" + gitResponseDTO);
	}

	@PostMapping(value = "/getCommitDetailsBetweenCommitIds", consumes = "application/json")
	public void getCommitDetailsBetweenCommitIds(@RequestBody GitRequestDTO gitUserDTO) {
		//logger.info("GetCommitDetailsBetweenCommitIds Request:" + gitUserDTO + "::Response:" + gitResponseDTO);
	}
}
