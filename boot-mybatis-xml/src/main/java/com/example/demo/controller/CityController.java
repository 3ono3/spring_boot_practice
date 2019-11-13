package com.example.demo.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.City;
import com.example.demo.mapper.CityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author GuoJingyuan
 * @date 2019/9/11
 */
@Controller
public class CityController {

    @Autowired
    private CityMapper cityMapper;

    @RequestMapping(value = "/city", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject getById(@RequestParam(value = "id") Integer id){
        JSONObject result = new JSONObject();
        try {
            City city = cityMapper.getById(id);
            result.put("city", city.toJson());
            result.put("code", "0000");
        } catch (Exception e) {
            result.put("code", "9999");
            result.put("success", false);
            result.put("exception", e.getMessage());
        }
        return result;
    }

    @RequestMapping(value = "/city", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject addCity(@RequestBody City city) {
        JSONObject result = new JSONObject();
        try {
            result.put("city_name", city.getName());
            result.put("city_people", city.getStayPeople());
            cityMapper.insert(city);
            result.put("code", "0000");
        } catch (Exception e) {
            result.put("code", "9999");
            result.put("success", false);
            result.put("exception", e.getMessage());
        }
        return result;
    }

    @RequestMapping(value = "/citys", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject listAll() {
        JSONObject result = new JSONObject();
        try {
            List<City> cities = cityMapper.listAll();
            JSONArray cJA = new JSONArray();
            for (City city : cities) {
                cJA.add(city.toJson());
            }
            result.put("citys", cJA);
            result.put("code", "0000");
        } catch (Exception e) {
            result.put("code", "9999");
            result.put("success", false);
            result.put("exception", e.getMessage());
        }
        return result;
    }
}
