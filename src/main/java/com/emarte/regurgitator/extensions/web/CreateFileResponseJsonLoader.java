package com.emarte.regurgitator.extensions.web;

import com.emarte.regurgitator.core.*;
import net.sf.json.JSONObject;

import java.util.Set;

import static com.emarte.regurgitator.core.CoreConfigConstants.SOURCE;
import static com.emarte.regurgitator.core.JsonConfigUtil.*;
import static com.emarte.regurgitator.extensions.web.WebConfigConstants.PATH_PREFIX;

public class CreateFileResponseJsonLoader implements JsonLoader<CreateFileResponse> {
	private static final Log log = Log.getLog(CreateFileResponseJsonLoader.class);

	@Override
	public CreateFileResponse load(JSONObject jsonObject, Set<Object> allIds) throws RegurgitatorException {
		String id = loadId(jsonObject, allIds);
		String source = loadMandatoryStr(jsonObject, SOURCE);
		String pathPrefix = loadOptionalStr(jsonObject, PATH_PREFIX);

		ContextLocation location = source != null ? new ContextLocation(source) : null;
		log.debug("Loaded file response '" + id + "'");
		return new CreateFileResponse(id, new ValueSource(location, null), pathPrefix);
	}
}
