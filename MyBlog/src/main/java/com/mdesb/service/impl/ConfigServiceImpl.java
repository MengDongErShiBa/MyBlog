package com.mdesb.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.mdesb.dao.ConfigMapper;
import com.mdesb.model.BlogConfig;
import com.mdesb.model.Config;
import com.mdesb.service.IConfigService;

@Service
public class ConfigServiceImpl implements IConfigService {
    @Autowired
    private ConfigMapper configMapper;

    @Override
    public int updateConfig(Config config) {
       return configMapper.updateConfig(config);
    }

    @Override
    public Map<String, String> getAllConfigs() {
    	
        return configMapper.getAllConfigs();
    }
}
