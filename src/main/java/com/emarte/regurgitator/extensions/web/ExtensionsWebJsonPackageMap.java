package com.emarte.regurgitator.extensions.web;

import com.emarte.regurgitator.core.*;

import java.util.*;

public class ExtensionsWebJsonPackageMap extends AbstractJsonPackageMap {
	private static final List<String> kinds = Arrays.asList("http-call-through");

	public ExtensionsWebJsonPackageMap() {
		addPackageMapping(kinds, "com.emarte.regurgitator.extensions.web");
	}
}
