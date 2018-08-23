package com.impactanalysis.clients;

import java.io.Serializable;
import java.util.Collections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.impactanalysis.dto.GitResponseDTO;
import com.impactanalysis.utilities.MediaTypeSupport;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_NULL)
public class GitClient implements Serializable{

	private static final long serialVersionUID = 1L;
	
	RestTemplate restTemplate = new RestTemplate();
	
	private static final Logger logger = LoggerFactory.getLogger(GitClient.class);

	@Value("${git.repo.url}")
	private String gitURI;

	public GitResponseDTO getCommitDetailsByDate() {

//		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
//		headers.add("Content-Type", "application/vnd.github.cloak-preview");
//		headers.add("Accept", MediaTypeSupport.GITHUB_MEDIATYPE);
//		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
//
//		HttpEntity<GitUserDTO> request = new HttpEntity<GitUserDTO>(gitUserDTO, headers);
//
//		
//		logger.info("Printing getCommitDetailsByDate" + gitUserDTO);
//		try {
//			restTemplate.getForObject(gitURI + "search/commits", IAResponse.class, request);
//			//restTemplate.getForObject(gitURI + "search/commits", IAResponse.class);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		restTemplate = new RestTemplate();
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setAccept(Collections.singletonList(MediaTypeSupport.GITHUB_MEDIATYPE));

		HttpEntity<String> entity = new HttpEntity<>("parameters", httpHeaders);

		Object obj = restTemplate.exchange(gitURI + "/search/commits", HttpMethod.GET, entity, Object.class);
		logger.info("Response Object:" + obj);
		
		return null;
	}

}
