package com.ucloudlink.domain;

import java.util.Set;

import com.google.common.collect.Multimap;

import springfox.documentation.service.ApiListing;
import springfox.documentation.service.ResourceListing;
import springfox.documentation.service.Tag;

public class DocumentationSwap {

	private String groupName;
	private String basePath;
	private Multimap<String, ApiListingSwap> apiListings;
	private Set<Tag> tags;
	private ResourceListing resourceListing;
	private Set<String> produces;
	private Set<String> consumes;
	private String host;
	private Set<String> schemes;

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getBasePath() {
		return basePath;
	}

	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}

	public Multimap<String, ApiListingSwap> getApiListings() {
		return apiListings;
	}

	public void setApiListings(Multimap<String, ApiListingSwap> apiListings) {
		this.apiListings = apiListings;
	}

	public Set<Tag> getTags() {
		return tags;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}

	public ResourceListing getResourceListing() {
		return resourceListing;
	}

	public void setResourceListing(ResourceListing resourceListing) {
		this.resourceListing = resourceListing;
	}

	public Set<String> getProduces() {
		return produces;
	}

	public void setProduces(Set<String> produces) {
		this.produces = produces;
	}

	public Set<String> getConsumes() {
		return consumes;
	}

	public void setConsumes(Set<String> consumes) {
		this.consumes = consumes;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public Set<String> getSchemes() {
		return schemes;
	}

	public void setSchemes(Set<String> schemes) {
		this.schemes = schemes;
	}

}
