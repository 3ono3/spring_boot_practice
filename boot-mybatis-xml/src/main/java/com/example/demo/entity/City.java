package com.example.demo.entity;

import com.alibaba.fastjson.JSONObject;

import java.math.BigDecimal;

/**
 * @author GuoJingyuan
 * @date 2019/9/11
 */
public class City {
    private Integer id;
    private String name;
    private String state;
    private String country;
    private BigDecimal stayPeople;

    public JSONObject toJson() {
        JSONObject cityJson = new JSONObject();
        cityJson.put("id", id);
        cityJson.put("name", name);
        cityJson.put("state", state);
        cityJson.put("stayPeople", stayPeople);

        return cityJson;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public BigDecimal getStayPeople() {
        return stayPeople;
    }

    public void setStayPeople(BigDecimal stayPeople) {
        this.stayPeople = stayPeople;
    }
}
