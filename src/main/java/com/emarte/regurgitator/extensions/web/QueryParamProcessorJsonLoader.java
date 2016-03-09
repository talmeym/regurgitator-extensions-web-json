package com.emarte.regurgitator.extensions.web;

import com.emarte.regurgitator.core.*;
import net.sf.json.JSONObject;

import java.util.Set;

import static com.emarte.regurgitator.core.JsonConfigUtil.loadMandatoryStr;
import static com.emarte.regurgitator.core.Log.getLog;
import static com.emarte.regurgitator.extensions.web.ExtensionsWebConfigConstants.KEY;

public class QueryParamProcessorJsonLoader implements JsonLoader<QueryParamProcessor> {
	public static final Log log = getLog(QueryParamProcessorJsonLoader.class);

	@Override
	public QueryParamProcessor load(JSONObject jsonObject, Set<Object> allIds) throws RegurgitatorException {
		log.debug("Loaded QueryParamProcessor");
		return new QueryParamProcessor(loadMandatoryStr(jsonObject, KEY));
	}
}
