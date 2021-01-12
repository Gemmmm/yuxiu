package com.tc.yuxiu.controller;

import com.tc.yuxiu.model.Result;
import com.tc.yuxiu.model.TyActivity;
import com.tc.yuxiu.model.TySeller;
import com.tc.yuxiu.service.TyActivityService;
import com.tc.yuxiu.service.TySellerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author DELL
 * @date 2020/9/21 15:29
 */
//@Api(tags = "订货列表")
@RestController
@RequestMapping("/activity")
public class ActivityController {
    @Autowired
    private TySellerService sellerService;
    @Autowired
    private TyActivityService activityService;

    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public Result allActivities(@ApiParam @RequestParam String token) {
        Result result = new Result();
        TySeller seller = sellerService.getByToken(token);
        if (seller == null) {
            result.setFailToken();
            return result;
        }
        Integer storeId = seller.getStoreId();
        List<Map<String,Object>>  maps=new ArrayList<>();
        Map<String,Object> map=null;
        List<TyActivity> activities=activityService.getByStoreId(storeId);
        if(activities!=null){
            for (TyActivity activity : activities) {
                map=new HashMap<>();

            }
        }

        return result;
    }
}
