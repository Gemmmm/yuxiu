package com.tc.yuxiu.service;

import com.tc.yuxiu.model.TyPlugin;

import java.util.List;

public interface TyPluginService {
    List<TyPlugin> getByType(String type);
}
