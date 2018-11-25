package com.camp.domain;

import java.util.List;
import java.util.Map;
import java.util.Set;

import springfox.documentation.schema.Model;
import springfox.documentation.service.ApiDescription;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.Tag;

public class ApiListingSwap 
{
	
	private  String apiVersion;
	private  String basePath;
	private  String resourcePath;
	private  Set<String> produces;
	private  Set<String> consumes;
	private  String host;
	private  Set<String> protocols;
	private  List<SecurityReference> securityReferences;
	private  List<ApiDescription> apis;
	private  Map<String, Model> models;
	private  String description;
	private  int position;
	private  Set<Tag> tags;
	public String getApiVersion() {
		return apiVersion;
	}
	public void setApiVersion(String apiVersion) {
		this.apiVersion = apiVersion;
	}
	public String getBasePath() {
		return basePath;
	}
	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}
	public String getResourcePath() {
		return resourcePath;
	}
	public void setResourcePath(String resourcePath) {
		this.resourcePath = resourcePath;
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
	public Set<String> getProtocols() {
		return protocols;
	}
	public void setProtocols(Set<String> protocols) {
		this.protocols = protocols;
	}
	public List<SecurityReference> getSecurityReferences() {
		return securityReferences;
	}
	public void setSecurityReferences(List<SecurityReference> securityReferences) {
		this.securityReferences = securityReferences;
	}
	public List<ApiDescription> getApis() {
		return apis;
	}
	public void setApis(List<ApiDescription> apis) {
		this.apis = apis;
	}
	public Map<String, Model> getModels() {
		return models;
	}
	public void setModels(Map<String, Model> models) {
		this.models = models;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	public Set<Tag> getTags() {
		return tags;
	}
	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}

}
