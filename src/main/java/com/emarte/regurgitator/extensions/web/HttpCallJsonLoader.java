/*
 * Copyright (C) 2017 Miles Talmey.
 * Distributed under the MIT License (license terms are at http://opensource.org/licenses/MIT).
 */
package com.emarte.regurgitator.extensions.web;

import com.emarte.regurgitator.core.*;
import net.sf.json.*;

import java.util.*;

import static com.emarte.regurgitator.core.CoreConfigConstants.STEPS;
import static com.emarte.regurgitator.core.JsonConfigUtil.*;
import static com.emarte.regurgitator.core.Log.getLog;
import static com.emarte.regurgitator.extensions.web.WebConfigConstants.*;
import static java.lang.Integer.parseInt;

public class HttpCallJsonLoader implements JsonLoader<Step> {
    private static final Log log = getLog(HttpCallJsonLoader.class);
    private static final JsonLoaderUtil<JsonLoader<Step>> loaderUtil = new JsonLoaderUtil<JsonLoader<Step>>();

    @Override
    public Step load(JSONObject jsonObject, Set<Object> allIds) throws RegurgitatorException {
        String id = loadId(jsonObject, allIds);
        List<Step> steps = new ArrayList<Step>();
        JSONArray jsonArray = loadOptionalArray(jsonObject, STEPS);

        if(jsonArray != null) {
            for(Iterator iterator = jsonArray.iterator(); iterator.hasNext(); ) {
                JSONObject object = (JSONObject) iterator.next();
                steps.add(loaderUtil.deriveLoader(object).load(object, allIds));
            }
        }

        String username = loadOptionalStr(jsonObject, USERNAME);
        String password = loadOptionalStr(jsonObject, PASSWORD);

        if((username == null && password != null) || (username != null && password == null)) {
            throw new RegurgitatorException("Both username and password (or neither) required");
        }

        log.debug("Loaded HttpCall '{}'", id);
        return new HttpCall(id, new HttpMessageProxy(new HttpClientWrapper(loadMandatoryStr(jsonObject, HOST), parseInt(loadMandatoryStr(jsonObject, PORT)), username, password)), steps);
    }
}
