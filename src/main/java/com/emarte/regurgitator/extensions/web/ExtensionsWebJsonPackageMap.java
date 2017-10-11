/*
 * Copyright (C) 2017 Miles Talmey.
 * Distributed under the MIT License (license terms are at http://opensource.org/licenses/MIT).
 */
package com.emarte.regurgitator.extensions.web;

import com.emarte.regurgitator.core.AbstractJsonPackageMap;

import java.util.Arrays;
import java.util.List;

public class ExtensionsWebJsonPackageMap extends AbstractJsonPackageMap {
    private static final List<String> kinds = Arrays.asList("http-call", "query-param-processor", "create-http-response", "create-file-response");

    public ExtensionsWebJsonPackageMap() {
        addPackageMapping(kinds, "com.emarte.regurgitator.extensions.web");
    }
}
