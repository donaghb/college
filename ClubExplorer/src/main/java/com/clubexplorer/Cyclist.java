package com.clubexplorer;

public class Cyclist
{
    private String name;
    private int id;
    private int age;
    private String gender;

    public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

}