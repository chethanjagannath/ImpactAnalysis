package com.impactanalysis.clients;

import java.io.Serializable;
import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.impactanalysis.dto.GitRequestDTO;
import com.impactanalysis.dto.GitResponseDTO;
import com.impactanalysis.utilities.MediaTypeSupport;

@Component
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class GitClient implements Serializable {

	private static final long serialVersionUID = 1L;

	RestTemplate restTemplate = new RestTemplate();

	private static final Logger logger = LoggerFactory.getLogger(GitClient.class);

	@Value("${git.repository.url}")
	private String gitRespositoryURL;

	@Autowired
	MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter;

	public GitResponseDTO getCommitDetailsByDate(GitRequestDTO gitRequestDTO) {

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		httpHeaders.setAccept(Collections.singletonList(MediaTypeSupport.GITHUB_MEDIATYPE));

		HttpEntity<GitRequestDTO> request = new HttpEntity<GitRequestDTO>(null, httpHeaders);

		restTemplate.getMessageConverters().add(mappingJackson2HttpMessageConverter);

		GitResponseDTO responseEntity = null;
		String URI = gitRespositoryURL + "/search/commits?q=repo:" + gitRequestDTO.getRepositoryOwnerId() + "/"
				+ gitRequestDTO.getRepositoryName()+ "+committer-date:" + gitRequestDTO.getCommitDate();

		logger.info("API::getCommitDetailsByDate -> Requesting URI:" + URI);
		
		try {
			responseEntity = restTemplate.getForObject(URI, GitResponseDTO.class, request);
		} catch (HttpClientErrorException e) {
			logger.error("StatusCode:" + e.getStatusCode().value() + "::ResponseBody:" + e.getResponseBodyAsString());
		} catch (HttpServerErrorException e) {
			logger.error("StatusCode:" + e.getStatusCode().value() + "::ResponseBody:" + e.getResponseBodyAsString());
		}

		return responseEntity;
	}
	
	public GitResponseDTO getCommitDetailsByCommitId(GitRequestDTO gitRequestDTO) {

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		httpHeaders.setAccept(Collections.singletonList(MediaTypeSupport.GITHUB_MEDIATYPE_MERCY));

		HttpEntity<GitRequestDTO> request = new HttpEntity<GitRequestDTO>(null, httpHeaders);

		restTemplate.getMessageConverters().add(mappingJackson2HttpMessageConverter);

		GitResponseDTO responseEntity = null;

		String URI = gitRespositoryURL + "/repos/" + gitRequestDTO.getRepositoryOwnerId() + "/"
				+ gitRequestDTO.getRepositoryName() + "/commits/" + gitRequestDTO.getStartCommitId();

		logger.info("API::getCommitDetailsByCommitId -> Requesting URI:" + URI);
		
		try {
			responseEntity = restTemplate.getForObject(URI, GitResponseDTO.class, request);
		} catch (HttpClientErrorException e) {
			logger.error("StatusCode:" + e.getStatusCode().value() + "::ResponseBody:" + e.getResponseBodyAsString());
		} catch (HttpServerErrorException e) {
			logger.error("StatusCode:" + e.getStatusCode().value() + "::ResponseBody:" + e.getResponseBodyAsString());
		}

		return responseEntity;
	}
}