package com.camp.domain;

import io.swagger.models.Swagger;
import springfox.documentation.spring.web.json.Json;

public class SwaggerInput {
	
	private String groupName;
	
	private String swagger;

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getSwagger() {
		return swagger;
	}

	public void setSwagger(String swagger) {
		this.swagger = swagger;
	}

}
