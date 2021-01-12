package com.tc.yuxiu.controller;

import com.tc.yuxiu.model.Result;
import com.tc.yuxiu.model.TyPlugin;
import com.tc.yuxiu.model.TyRegion;
import com.tc.yuxiu.model.TySeller;
import com.tc.yuxiu.service.TyPluginService;
import com.tc.yuxiu.service.TyRegionService;
import com.tc.yuxiu.service.TySellerService;
import com.tc.yuxiu.util.FileUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author DELL
 * @date 2020-9-29 9:57
 */
@Api(tags = "工具")
@RestController
@RequestMapping("/util")
public class UtilController {

    @Autowired
    private TySellerService sellerService;
    @Autowired
    private TyRegionService regionService;
    @Autowired
    private TyPluginService pluginService;

    @ApiOperation("省市区")
    @GetMapping("/regions")
    public Result allRegion(
            @ApiParam @RequestParam(required = true) String token,
            @ApiParam("层级：1省，2市，3区") @RequestParam(required = false, defaultValue = "1") Short level,
            @ApiParam("父id，省分父id为0") @RequestParam(required = false, defaultValue = "0") Integer parentId
    ) {
        Result result = new Result();
        TySeller seller = sellerService.getByToken(token);
        if (seller == null) {
            result.setFailToken();
            return result;
        }
        List<Map<String, Object>> maps = new ArrayList<>();
        Map<String, Object> map = null;
        List<TyRegion> regions = regionService.getByLevelAndParentId(level, parentId);
        if (regions != null) {
            for (TyRegion region : regions) {
                map = new HashMap<>();
                map.put("id", region.getId().toString());
                map.put("name", region.getName());
                maps.add(map);
            }
        }
        result.setData(maps);
        if (level == 1) {
            if (maps.size() > 0) {
                result.setSuccess("查询省份");
            } else {
                result.setNull("查询省份");
            }
        } else if (level == 2) {
            if (maps.size() > 0) {
                result.setSuccess("查询市");
            } else {
                result.setNull("查询市");
            }
        } else if (level == 3) {
            if (maps.size() > 0) {
                result.setSuccess("查询区县");
            } else {
                result.setNull("查询区县");
            }
        }
        return result;
    }


    @ApiOperation("上传图片")
    @PostMapping("/uploadImage")
    public Result uploadImage(
            HttpServletRequest request,
            @ApiParam @RequestParam(required = false) String token,
            @ApiParam(value = "图片文件") @RequestParam(required = false) MultipartFile img,
            @ApiParam(value = "图片存放类型，0:封面图,1:相册,2:店铺设置") @RequestParam(required = false, defaultValue = "0") String type
    ) {
        Result result = new Result();
        if (token == null) {
            result.setFailMessage("token为空");
            return result;
        }
        TySeller seller = sellerService.getByToken(token);
        if (seller == null) {
            result.setFailToken();
            return result;
        }
        String path = "";

        try {
            String filename = null;
            if (img == null) {
                result.setFailMessage("请选择图片文件");
                return result;
            }
            if (img != null) {
                filename = img.getOriginalFilename();
            }
            System.out.println("filename::" + filename);
            if (filename == null || "".equals(filename)) {
                result.setFailMessage("图片文件为空");
                return result;
            }
            if ("2".equals(type)) {
                type = "Public/upload/seller";
            } else if ("1".equals(type)) {
                type = "Public/upload/goods";
            } else {
                type = "Public/upload/image";
            }
            path = FileUtil.uploadImage(img, request, type);

        } catch (Exception e) {
        }
        result.setData(path);
        if (path != null && !"".equals(path)) {
            result.setSuccess("图片上传");
        } else {
            result.setFail("图片上传");
        }
        return result;
    }

    @ApiOperation("插件信息")
    @GetMapping("/plugins")
    public Result plugin(
            @ApiParam @RequestParam String token,
            @ApiParam("插件类型 payment支付 login 登陆 shipping物流") @RequestParam(required = false) String type
    ) {
        Result result = new Result();
        TySeller seller = sellerService.getByToken(token);
        if (seller == null) {
            result.setFailToken();
            return result;
        }
        List<Map<String, Object>> maps = new ArrayList<>();
        Map<String, Object> map = null;
        List<TyPlugin> plugins = pluginService.getByType(type);
        if (plugins != null) {
            for (TyPlugin plugin : plugins) {
                map = new HashMap<>();
                map.put("code", plugin.getCode());
                map.put("version", plugin.getVersion());
                map.put("name", plugin.getName());
                maps.add(map);
            }
        }
        result.setData(maps);
        if (maps.size() > 0) {
            result.setSuccess("插件查询");
        } else {
            result.setNull("插件");
        }
        return result;
    }


}
