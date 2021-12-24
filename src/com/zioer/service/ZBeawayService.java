package com.zioer.service;

import java.util.List;
import com.zioer.model.ZBeaway;

public interface ZBeawayService {
    int insert(ZBeaway record);
    int deleteByPrimaryKey(String id);
    int update(ZBeaway record);
    ZBeaway selectByPrimaryKey(String id);
    List<ZBeaway> listAll(ZBeaway record);
}
