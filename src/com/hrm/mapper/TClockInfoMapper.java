package com.hrm.mapper;

import com.hrm.pojo.TClockInfo;
import com.hrm.pojo.TClockInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TClockInfoMapper {
    int countByExample(TClockInfoExample example);

    int deleteByExample(TClockInfoExample example);

    int deleteByPrimaryKey(Integer clockId);

    int insert(TClockInfo record);

    int insertSelective(TClockInfo record);

    List<TClockInfo> selectByExample(TClockInfoExample example);

    TClockInfo selectByPrimaryKey(Integer clockId);

    int updateByExampleSelective(@Param("record") TClockInfo record, @Param("example") TClockInfoExample example);

    int updateByExample(@Param("record") TClockInfo record, @Param("example") TClockInfoExample example);

    int updateByPrimaryKeySelective(TClockInfo record);

    int updateByPrimaryKey(TClockInfo record);
}