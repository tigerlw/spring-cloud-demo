package com.camp.utils;

import com.google.common.collect.Multimap;
import com.camp.domain.DocumentationSwap;

import springfox.documentation.service.ApiListing;
import springfox.documentation.service.Documentation;

public class SwapDocumentation 
{
	public static DocumentationSwap swapDocument(Documentation document)
	{
		DocumentationSwap swapDoc = new DocumentationSwap();
		
		Multimap<String, ApiListing> apiList = document.getApiListings();
		
		apiList.entries();
		
		return null;
		
	}

}
