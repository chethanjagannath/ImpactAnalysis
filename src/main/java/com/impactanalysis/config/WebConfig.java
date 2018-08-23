package com.impactanalysis.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.impactanalysis.utilities.MediaTypeSupport;

@Configuration
public class WebConfig extends WebMvcConfigurationSupport {

	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		configurer.favorPathExtension(false).favorParameter(true).defaultContentType( MediaTypeSupport.GITHUB_MEDIATYPE)
				.mediaType("git.vnd", MediaTypeSupport.GITHUB_MEDIATYPE);
	}
}
