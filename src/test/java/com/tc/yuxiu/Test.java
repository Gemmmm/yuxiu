package com.tc.yuxiu;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        JSONArray specItemIds=new JSONArray();
        specItemIds.add(1);
        specItemIds.add(2);
        specItemIds.add(3);
        JSONObject specItemsJsonObject =new JSONObject();
        specItemsJsonObject.put("specId","1");
        specItemsJsonObject.put("itemIds",specItemIds);
        JSONObject specItemsJsonObject1 =new JSONObject();
        specItemsJsonObject1.put("specId","1");
        specItemsJsonObject1.put("itemIds",specItemIds);
        JSONArray specItemsIdsesId=new JSONArray();
        specItemsIdsesId.add(specItemsJsonObject);
        specItemsIdsesId.add(specItemsJsonObject1);

        JSONObject data=new JSONObject();
        data.put("goodsId","123");
        data.put("specItemsIdses",specItemsIdsesId);

        System.out.println(data);


    }

    public static Integer eval(String str) throws Exception {
        ScriptEngineManager factory = new ScriptEngineManager();
        ScriptEngine eng = factory.getEngineByName("javascript");
        return (int)(double)eng.eval(str);
    }
}