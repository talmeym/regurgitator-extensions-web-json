package com.emarte.regurgitator.extensions.web;

import com.emarte.regurgitator.core.*;
import net.sf.json.JSONObject;

import java.util.Set;

import static com.emarte.regurgitator.core.JsonConfigUtil.loadId;
import static com.emarte.regurgitator.extensions.web.WebConfigConstants.*;
import static java.lang.Integer.parseInt;

public class HttpCallThroughJsonLoader implements JsonLoader<Step> {
    private static Log log = Log.getLog(HttpCallThroughJsonLoader.class);

	private static final JsonLoaderUtil<JsonLoader<Step>> loaderUtil = new JsonLoaderUtil<JsonLoader<Step>>();

	@Override
	public Step load(JSONObject jsonObject, Set<Object> allIds) throws RegurgitatorException {
		String id = loadId(jsonObject, allIds);
		Step responseProcessing = null;

		if(jsonObject.containsKey(PROCESS_RESPONSE)) {
			JSONObject responseProcessingObject = jsonObject.getJSONObject(PROCESS_RESPONSE);
			responseProcessing = loaderUtil.deriveLoader(responseProcessingObject).load(responseProcessingObject, allIds);
		}

		log.debug("Loaded HttpCallThrough '" + id + "'");
		return new HttpCallThrough(id, new HttpMessageProxy(jsonObject.getString(HOST), parseInt(jsonObject.getString(PORT))), responseProcessing);
	}
}
