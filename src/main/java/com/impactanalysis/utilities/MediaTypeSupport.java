package com.impactanalysis.utilities;

import org.springframework.http.MediaType;

import com.impactanalysis.constants.CommonConstants;

public class MediaTypeSupport {
   // public static final String V2_JSON_VALUE = Constants.API_VERSION_MEDIA_TYPE + ".v2+" + Constants.JSON_MIME_TYPE;
    public static final MediaType GITHUB_MEDIATYPE = MediaType.valueOf(CommonConstants.GITHUB_VND_VALUE);
}
