/*
 * Copyright (C) 2017 Miles Talmey.
 * Distributed under the MIT License (license terms are at http://opensource.org/licenses/MIT).
 */
package uk.emarte.regurgitator.extensions.web;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import uk.emarte.regurgitator.core.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static java.lang.Integer.parseInt;
import static uk.emarte.regurgitator.core.CoreConfigConstants.STEPS;
import static uk.emarte.regurgitator.core.JsonConfigUtil.*;
import static uk.emarte.regurgitator.core.Log.getLog;
import static uk.emarte.regurgitator.extensions.web.ExtensionsWebConfigConstants.*;

public class HttpCallJsonLoader implements JsonLoader<Step> {
    private static final Log log = getLog(HttpCallJsonLoader.class);
    private static final JsonLoaderUtil<JsonLoader<Step>> loaderUtil = new JsonLoaderUtil<>();

    @Override
    public Step load(JSONObject jsonObject, Set<Object> allIds) throws RegurgitatorException {
        String id = loadId(jsonObject, allIds);
        List<Step> steps = new ArrayList<>();
        JSONArray jsonArray = loadOptionalArray(jsonObject, STEPS);

        if(jsonArray != null) {
            for (Object o : jsonArray) {
                JSONObject object = (JSONObject) o;
                steps.add(loaderUtil.deriveLoader(object).load(object, allIds));
            }
        }

        String protocol = loadOptionalStr(jsonObject, PROTOCOL);
        String username = loadOptionalStr(jsonObject, USERNAME);
        String password = loadOptionalStr(jsonObject, PASSWORD);

        if((username == null && password != null) || (username != null && password == null)) {
            throw new RegurgitatorException("Both username and password (or neither) required");
        }

        log.debug("Loaded HttpCall '{}'", id);
        return new HttpCall(id, new HttpMessageProxy(new HttpClientWrapper(protocol != null ? protocol : "http", loadMandatoryStr(jsonObject, HOST), parseInt(loadMandatoryStr(jsonObject, PORT)), username, password)), steps);
    }
}
