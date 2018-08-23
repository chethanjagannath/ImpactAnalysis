package com.impactanalysis.utilities;

import org.springframework.http.MediaType;

import com.impactanalysis.constants.CommonConstants;

public class MediaTypeSupport {
   public static final MediaType GITHUB_MEDIATYPE_MERCY = MediaType.valueOf(CommonConstants.GITHUB_VND_MERCY_VALUE);
    public static final MediaType GITHUB_MEDIATYPE = MediaType.valueOf(CommonConstants.GITHUB_VND_VALUE);
}
