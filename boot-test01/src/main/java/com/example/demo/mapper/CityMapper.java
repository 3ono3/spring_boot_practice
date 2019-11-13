package com.example.demo.mapper;

import com.example.demo.entity.City;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author GuoJingyuan
 * @date 2019/9/11
 */
@Mapper
public interface CityMapper {

    @Select("select * from city")
    @Results({
            @Result(column = "stay_people", property = "stayPeople")
    })
    List<City> listAll();

    @Select("select * from city where id = #{id}")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column = "state", property = "state"),
            @Result(column = "stay_people", property = "stayPeople")
    })
    City getById(Integer id);

    @Insert("insert into city(name,state,country,stay_people) values(#{name},#{state},#{country},#{stayPeople})")
    void insert(City city);

}
