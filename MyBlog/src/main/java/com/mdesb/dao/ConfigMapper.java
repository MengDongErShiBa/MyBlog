package com.mdesb.dao;

import java.util.Map;

import com.mdesb.model.Config;

public interface ConfigMapper {
    /**
     * 修改配置项
     *
     * @param configName
     * @param configValue
     * @return
     */
    int updateConfig(Config config);

    /**
     * 获取所有的配置项
     *
     * @return
     */
    Map<String,String> getAllConfigs();
}