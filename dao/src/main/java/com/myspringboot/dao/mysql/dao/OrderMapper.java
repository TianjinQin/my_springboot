package com.myspringboot.dao.mysql.dao;

import org.apache.ibatis.annotations.Param;

public interface OrderMapper {
    int updateByVersion(@Param("code") String code, @Param("version") int version, @Param("buy") int buy);

    int getAmount(String code);

    int getVersion(String code);

    int updateByBuy(@Param("code") String code, @Param("buy") int buy);
}
