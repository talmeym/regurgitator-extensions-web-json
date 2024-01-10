/*
 * Copyright (C) 2017 Miles Talmey.
 * Distributed under the MIT License (license terms are at http://opensource.org/licenses/MIT).
 */
package uk.emarte.regurgitator.extensions.web;

import net.sf.json.JSONObject;
import uk.emarte.regurgitator.core.*;

import java.util.Set;

import static uk.emarte.regurgitator.core.CoreConfigConstants.SOURCE;
import static uk.emarte.regurgitator.core.JsonConfigUtil.*;
import static uk.emarte.regurgitator.extensions.web.ExtensionsWebConfigConstants.PATH_PREFIX;

public class CreateFileResponseJsonLoader implements JsonLoader<CreateFileResponse> {
    private static final Log log = Log.getLog(CreateFileResponseJsonLoader.class);

    @Override
    public CreateFileResponse load(JSONObject jsonObject, Set<Object> allIds) throws RegurgitatorException {
        String id = loadId(jsonObject, allIds);
        String source = loadMandatoryStr(jsonObject, SOURCE);
        String pathPrefix = loadOptionalStr(jsonObject, PATH_PREFIX);
        ContextLocation location = source != null ? new ContextLocation(source) : null;
        log.debug("Loaded file response '{}'", id);
        return new CreateFileResponse(id, new ValueSource(location, null), pathPrefix);
    }
}
