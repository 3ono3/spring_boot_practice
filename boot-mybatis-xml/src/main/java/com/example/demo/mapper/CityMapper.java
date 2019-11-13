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

    List<City> listAll();

    City getById(Integer id);

    void insert(City city);

}
