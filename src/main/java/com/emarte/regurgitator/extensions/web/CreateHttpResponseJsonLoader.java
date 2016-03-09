package com.emarte.regurgitator.extensions.web;

import com.emarte.regurgitator.core.*;
import net.sf.json.JSONObject;

import java.util.Set;

import static com.emarte.regurgitator.core.JsonConfigUtil.loadOptionalStr;
import static com.emarte.regurgitator.extensions.web.ExtensionsWebConfigConstants.*;

public class CreateHttpResponseJsonLoader implements JsonLoader<CreateHttpResponse> {
	private CreateResponseJsonLoader responseJsonLoader = new CreateResponseJsonLoader();

	@Override
	public CreateHttpResponse load(JSONObject jsonObject, Set<Object> allIds) throws RegurgitatorException {
		CreateResponse response = (CreateResponse) responseJsonLoader.load(jsonObject, allIds);
		String statusCodeStr = loadOptionalStr(jsonObject, STATUS_CODE);
		long statusCode = statusCodeStr != null ? Long.parseLong(statusCodeStr) : -1l;
		String contentType = loadOptionalStr(jsonObject, CONTENT_TYPE);
		return new CreateHttpResponse(response, statusCode, contentType);
	}
}
