package com.impactanalysis;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import com.impactanalysis.utilities.MediaTypeSupport;

@SpringBootApplication
@ComponentScan(basePackages="com.impactanalysis.*")
public class ImpactAnalysisApplication {

	public static void main(String[] args) {
		SpringApplication.run(ImpactAnalysisApplication.class, args);
	}

	@Bean
	public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
	    MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
	    jsonConverter.setSupportedMediaTypes(Arrays.asList(MediaTypeSupport.GITHUB_MEDIATYPE));
	   // jsonConverter.setSupportedMediaTypes(Arrays.asList(Version.V2_JSON));
	    return jsonConverter;
	}
}
