package com.impactanalysis.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import com.impactanalysis.utilities.MediaTypeSupport;

@Configuration
public class CommonConfig {
	@Bean
	public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
	    MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
	    jsonConverter.setSupportedMediaTypes(Arrays.asList(MediaTypeSupport.GITHUB_MEDIATYPE));
	   // jsonConverter.setSupportedMediaTypes(Arrays.asList(Version.V2_JSON));
	    return jsonConverter;
	}
}
