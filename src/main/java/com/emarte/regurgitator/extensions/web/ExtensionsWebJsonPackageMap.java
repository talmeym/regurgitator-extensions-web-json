package com.emarte.regurgitator.extensions.web;

import com.emarte.regurgitator.core.AbstractJsonPackageMap;

import java.util.*;

public class ExtensionsWebJsonPackageMap extends AbstractJsonPackageMap {
	private static final List<String> kinds = Arrays.asList("http-call", "query-param-processor", "create-http-response");

	public ExtensionsWebJsonPackageMap() {
		addPackageMapping(kinds, "com.emarte.regurgitator.extensions.web");
	}
}
