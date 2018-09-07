package com.impactanalysis.utilities;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.impactanalysis.exceptions.ValidationException;
import com.impactanalysis.pojo.Requestor;

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
	
	public void validateRequestor(final Requestor requestor) {
		if (ObjectUtils.isEmpty(requestor)) {
			throw new ValidationException("Requestor details is Empty");
		} else {
			if (StringUtils.isBlank(requestor.getName())) {
				throw new ValidationException(
						"Could you please provide your name under Requestor details, for Auditing");
			}
			if (StringUtils.isBlank(requestor.getEmailId())) {
				throw new ValidationException(
						"Could you please provide your emailId under Requestor details, for Auditing");
			}
		}
	}
	
//	private static List<String> strConvertClsToJava(List<String> inputList) {
//		return inputList.stream().map(e -> e.replace(".", "/")).map(e -> e + ".java").collect(Collectors.toList());
//	}
}
