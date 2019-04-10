/*
 * Copyright (C) 2017 Miles Talmey.
 * Distributed under the MIT License (license terms are at http://opensource.org/licenses/MIT).
 */
package com.emarte.regurgitator.extensions.web;

import com.emarte.regurgitator.core.CreateResponse;
import com.emarte.regurgitator.core.CreateResponseJsonLoader;
import com.emarte.regurgitator.core.JsonLoader;
import com.emarte.regurgitator.core.RegurgitatorException;
import net.sf.json.JSONObject;

import java.util.Set;

import static com.emarte.regurgitator.core.JsonConfigUtil.loadOptionalStr;
import static com.emarte.regurgitator.extensions.web.ExtensionsWebConfigConstants.CONTENT_TYPE;
import static com.emarte.regurgitator.extensions.web.ExtensionsWebConfigConstants.STATUS_CODE;

public class CreateHttpResponseJsonLoader implements JsonLoader<CreateHttpResponse> {
    private final CreateResponseJsonLoader responseJsonLoader = new CreateResponseJsonLoader();

    @Override
    public CreateHttpResponse load(JSONObject jsonObject, Set<Object> allIds) throws RegurgitatorException {
        CreateResponse response = (CreateResponse) responseJsonLoader.load(jsonObject, allIds);
        String statusCodeStr = loadOptionalStr(jsonObject, STATUS_CODE);
        long statusCode = statusCodeStr != null ? Long.parseLong(statusCodeStr) : -1L;
        String contentType = loadOptionalStr(jsonObject, CONTENT_TYPE);
        return new CreateHttpResponse(response, statusCode, contentType);
    }
}
