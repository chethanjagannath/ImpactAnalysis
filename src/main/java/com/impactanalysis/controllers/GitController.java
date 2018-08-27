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
		long startTime = System.currentTimeMillis();
		GitResponseDTO gitResponseDTO = gitService.getCommitDetailsByDate(gitRequestDTO);
		logger.info(String.format("API::GetCommitDetailsByDate Request=%s, Response=%s, TimeTaken=%s Milliseconds", gitRequestDTO, gitResponseDTO, System.currentTimeMillis()-startTime));
		return gitResponseDTO;
	}
}
