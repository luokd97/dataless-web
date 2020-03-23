package online.luokd.mapper;

import online.luokd.model.Map;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface MapMapper {
    @Select("SELECT * FROM t_map")
    List<Map> getAll();

    @Select("SELECT * FROM t_map WHERE id = #{id}")
    Map getOne(Long id);

    @Insert("INSERT INTO t_map (`key`,value) VALUES(${key}, ${value})")
    void insert(Map map);

    @Update("UPDATE t_map SET `key`=#{key},value=#{value} WHERE id =#{id}")
    void update(Map map);

    @Delete("DELETE FROM t_map WHERE id =#{id}")
    void delete(Long id);

    @Delete("DELETE FROM t_map WHERE `key` =${key}")
    void deleteByKey(Map map);
}
