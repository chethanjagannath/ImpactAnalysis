package com.impactanalysis.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.impactanalysis.dto.GitUserDTO;
import com.impactanalysis.response.IAResponse;

@RestController
@CrossOrigin
@RequestMapping(value = "/ImpactAnalysis")
public class GitController {

	private static final Logger logger = LoggerFactory.getLogger(GitController.class);

	// @Autowired
	RestTemplate restTemplate = new RestTemplate();;

	@Value("${git.repo.url}")
	private String gitURI;

	@GetMapping("/Test")
	public String testApp() {
		return "Success - ImpactAnalysis Application is Up & Running";
	}

	@PostMapping(value = "/PostCommitDetails", consumes = "application/json")
	public void postCommitDetails(@RequestBody GitUserDTO gitUserDTO) {
		logger.info("Printing PostCommitDetails" + gitUserDTO);

	}
	@PostMapping(value = "/getCommitDetailsByDate" , consumes="application/vnd.github.VERSION.sha",produces="application/vnd.github.VERSION.sha")
	public void getCommitDetailsByDate(@RequestBody GitUserDTO gitUserDTO) {
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
		//headers.add("Authorization", "Basic " + base64Creds);
		headers.add("Content-type", "application/vnd.github.VERSION.sha");
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

		HttpEntity<GitUserDTO> request = new HttpEntity<GitUserDTO>(gitUserDTO, headers);

		
		logger.info("Printing getCommitDetailsByDate" + gitUserDTO);
		try {
			restTemplate.getForObject(gitURI + "search/commits", IAResponse.class, request);
			//restTemplate.getForObject(gitURI + "search/commits", IAResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@PostMapping(value = "/getCommitDetailsByCommitId", consumes = "application/json")
	public void getCommitDetailsByCommitId(@RequestBody GitUserDTO gitUserDTO) {
		logger.info("Printing getCommitDetailsByCommitId" + gitUserDTO);

	}

	@PostMapping(value = "/getCommitDetailsBetweenCommitIds", consumes = "application/json")
	public void getCommitDetailsBetweenCommitIds(@RequestBody GitUserDTO gitUserDTO) {
		logger.info("Printing getCommitDetailsBetweenCommitIds" + gitUserDTO);

	}

}
