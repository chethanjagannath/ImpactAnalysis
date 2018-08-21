package com.impactanalysis.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping(value="/ImpactAnalysis")
public class GitController {
	
	private static final Logger logger = LoggerFactory.getLogger(GitController.class);
	
	@GetMapping("/Test")
	public String testApp() {
		return "Success - ImpactAnalysis Application is Up & Running";
	}
	
	@PostMapping(value="/PostCommitDetails", consumes =  "application/json")
	public void postCommitDetails(@RequestBody Object obj) {
		logger.info("Printing PostCommitDetails" + obj);
	}

}
