package com.impactanalysis.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping(value="/ImpactAnalysis")
public class GitController {
	
	//@GetMapping(value="/Test", produces =  "application/json", consumes =  "application/json")
	@GetMapping("/Test")
	public String testApp() {
		return "Success - ImpactAnalysis Application is Up & Running";
	}

}
