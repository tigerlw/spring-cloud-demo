package com.ucloudlink.domain;

public class ResInput 
{
	private String name;
	
	private String desc;
	
	public ResInput()
	{
		
	}
	
	public ResInput(String name)
	{
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
