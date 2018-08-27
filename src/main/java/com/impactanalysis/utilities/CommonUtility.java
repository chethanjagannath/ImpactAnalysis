package com.impactanalysis.utilities;

import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class CommonUtility {
	
	private static final Logger logger = LoggerFactory.getLogger(CommonUtility.class);
	
	public static String toJson(final Object obj) {
		String jsonString = "";
        try {
            final ObjectMapper mapper = new ObjectMapper();
            jsonString = mapper.writeValueAsString(obj);
        }
        catch (final IOException e) {
        	logger.error(String.format("Exception occured while converting object to json : %s ", jsonString));
        }
        return jsonString;
	}

}
