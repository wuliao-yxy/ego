package com.hrm.mapper;

import com.hrm.pojo.TWorkDate;
import com.hrm.pojo.TWorkDateExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TWorkDateMapper {
    int countByExample(TWorkDateExample example);

    int deleteByExample(TWorkDateExample example);

    int deleteByPrimaryKey(Integer workDateId);

    int insert(TWorkDate record);

    int insertSelective(TWorkDate record);

    List<TWorkDate> selectByExample(TWorkDateExample example);

    TWorkDate selectByPrimaryKey(Integer workDateId);

    int updateByExampleSelective(@Param("record") TWorkDate record, @Param("example") TWorkDateExample example);

    int updateByExample(@Param("record") TWorkDate record, @Param("example") TWorkDateExample example);

    int updateByPrimaryKeySelective(TWorkDate record);

    int updateByPrimaryKey(TWorkDate record);
}