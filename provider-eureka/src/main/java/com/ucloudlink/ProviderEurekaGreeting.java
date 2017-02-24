package com.ucloudlink;

public class ProviderEurekaGreeting {

	private final long id;
	private final int age;
	private final String name;

	public ProviderEurekaGreeting(long id, int age, String name) {
		this.id = id;
		this.age = age;
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public int getAge() {
		return age;
	}

	public String getName() {
		return name;
	}

}
