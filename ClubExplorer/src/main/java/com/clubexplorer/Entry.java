package com.clubexplorer;

import java.sql.Date;

class Entry
{
    private String name;
    private int id;
    private int age;
    private String gender;
    private String title;
    private String cost;
    private Date date;
    private int distance;
    private int cyclist_id;
    private int event_id;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCost() {
        return this.cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getDistance() {
        return this.distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getCyclist_id() {
        return this.cyclist_id;
    }

    public void setCyclist_id(int cyclist_id) {
        this.cyclist_id = cyclist_id;
    }

    public int getEvent_id() {
        return this.event_id;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }

    Entry(int id, String name, int age, String gender, String title, String cost, Date date, int distance, int cyclist_id, int event_id)
    {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.title = title;
        this.cost=cost;
        this.date=date;
        this.distance=distance;
        this.cyclist_id=cyclist_id;
        this.event_id=event_id;
    }

	public Entry() {
	}}
