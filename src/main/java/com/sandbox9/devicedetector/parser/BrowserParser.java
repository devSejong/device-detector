package com.sandbox9.devicedetector.parser;

import com.sandbox9.devicedetector.domain.Browser;
import com.sandbox9.devicedetector.domain.type.BrowserType;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BrowserParser {

	Set<BrowserParserData> browserParserDatas;

	public BrowserParser() {
		//데이터를 읽어옴
		browserParserDatas = new HashSet<>();
		browserParserDatas.add(new BrowserParserData(BrowserType.CHROME_MOBILE, new String[]{
				".*android.*Chrome/.* Mobile.*",
				".*android.*Chrome/.* (?!Mobile)"
		}));

		browserParserDatas.add(new BrowserParserData(BrowserType.SAFARI_MOBILE, new String[]{
				"mozilla.*applewebkit.*version/([0-9a-z+-.]+).*mobile.*safari/[0-9a-z+-.]+.*"
		}));
	}


	public Browser parse(String userAgentString) {
		boolean isBrowserExist = false;
		String version = null;
		BrowserType browserType = null;

		for (BrowserParserData browserParserData : browserParserDatas) {
			for (Pattern pattern : browserParserData.getPatterns()) {
				Matcher matcher = pattern.matcher(userAgentString);

				if (matcher.matches()) {
					isBrowserExist = true;
					version = "TODO";
					browserType = browserParserData.getBrowserType();
					break;
				}
			}

			if (isBrowserExist) break;
		}

		Browser browser;

		if (isBrowserExist)
			browser = new Browser(browserType, version);
		else
			browser = new Browser(BrowserType.UNKONWN, null);

		return browser;
	}

}
