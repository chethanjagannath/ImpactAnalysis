package com.impactanalysis.clients;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
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
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_NULL)
public class GitClient implements Serializable{

	private static final long serialVersionUID = 1L;
	
	RestTemplate restTemplate = new RestTemplate();
	
	private static final Logger logger = LoggerFactory.getLogger(GitClient.class);

	@Value("${git.repo.url}")
	private String gitURI;
	
	@Autowired
	MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter;

	public GitResponseDTO getCommitDetailsByDate(GitRequestDTO gitRequestDTO) {

		gitURI = "https://api.github.com/search/commits?q=repo:chethanjagannath/ImpactAnalysis+committer-date:2018-08-21";
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
		
		Map<String, Object> params = new HashMap<>();
	    
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setAccept(Collections.singletonList(MediaTypeSupport.GITHUB_MEDIATYPE));
        
		params.put("repo:", gitRequestDTO.getOwnerId()+"/"+gitRequestDTO.getProjectRepo());
	    params.put("+committer-date:", gitRequestDTO.getCommitDate());
	    
		HttpEntity<GitRequestDTO> request = new HttpEntity<GitRequestDTO>(null, httpHeaders);
		params.put("httpHeaders", request);
		
		restTemplate.getMessageConverters().add(mappingJackson2HttpMessageConverter);
		   /* restTemplate.setRequestFactory(
		        new HttpComponentsClientHttpRequestFactory());*/
		    
		GitResponseDTO responseEntity = null;
		String URI =  gitURI + "/search/commits?q=repo:"+gitRequestDTO.getOwnerId()+"/"+gitRequestDTO.getProjectRepo()+"+committer-date:"+gitRequestDTO.getCommitDate();
		logger.info("RequestDetails:"+gitRequestDTO+" URI :  "+URI);
		try {
			responseEntity = restTemplate.getForObject(URI, GitResponseDTO.class, request);
		} catch (HttpClientErrorException e) {
			//success = false;
			//Log.e(TAG, command+" was rejected for URL: "+url, e);
			System.out.println(e.getStatusCode().value());
			System.out.println(e.getResponseBodyAsString());
		} catch (HttpServerErrorException e) {
			System.out.println(e.getStatusCode().value());
			System.out.println(e.getResponseBodyAsString());
		}
		//HttpEntity<String> entity = new HttpEntity<>("parameters", httpHeaders);
		//Object obj = restTemplate.exchange(gitURI + "/search/commits", HttpMethod.GET, entity, Object.class);
		logger.info("Response Object:" + responseEntity);
		
		return responseEntity;
	}

}
