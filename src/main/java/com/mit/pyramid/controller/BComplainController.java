package com.mit.pyramid.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mit.pyramid.common.constsys.SystemConst;
import com.mit.pyramid.common.util.CheckPhone;
import com.mit.pyramid.common.util.ResultUtil;
import com.mit.pyramid.common.util.TokenUtil;
import com.mit.pyramid.common.vo.BComplainVO;
import com.mit.pyramid.common.vo.ResultVO;
import com.mit.pyramid.entity.BComplain;
import com.mit.pyramid.entity.FUserBasic;
import com.mit.pyramid.service.BComplainService;
import com.mit.pyramid.service.FUserBasicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Chen
 * @since 2019-05-01
 */
@RestController
@Api(value = "投诉相关操作",tags = "投诉管理")
public class BComplainController {

    @Autowired
    private BComplainService bComplainService;
    @Autowired
    private FUserBasicService fUserBasicService;

    @ApiOperation(value = "投诉列表",notes = "实现投诉的按状态分页展示(按时间升序)，status 投诉状态 0表示未审核 1 表示审核 点击审核按钮并对举报人进行处理（降级处理），2表示审核过，投诉理由不足驳回，1,2都要发消息反馈给投诉者")
    @GetMapping("complain/listbypage.do")
    public ResultVO listByPage(@RequestParam("page") @ApiParam(name = "page",value = "页码") int page, @RequestParam("limit") @ApiParam(name = "limit",value = "每页个数")int limit, @RequestParam("status") @ApiParam(name = "status",value = "投诉的状态")int status){

        Page<BComplain> complainPage = new Page<>(page, limit);
        IPage<BComplain> iPage = bComplainService.page(complainPage,new QueryWrapper<BComplain>().eq("status", status).orderByAsc("createdate"));

        return ResultUtil.exec(true, String.valueOf(complainPage.getTotal()),iPage.getRecords());
    }

    @ApiOperation(value = "查询我发起的投诉进度",notes = "基本的分页操作")
    @GetMapping("complain/mylist.do")
    public ResultVO myList(@RequestParam("page") @ApiParam(name = "page",value = "页码") int page, @RequestParam("limit") @ApiParam(name = "limit",value = "每页个数")int limit, String token){
        int uid = TokenUtil.parseToken(token).getUid();
        IPage<BComplain> iPage = bComplainService.page(new Page<BComplain>(page,limit),new QueryWrapper<BComplain>().eq("uid", uid).eq("status","0").orderByDesc("createdate"));
        IPage<BComplainVO> ipage2 = new Page<>();
        List<BComplainVO> list = new ArrayList<BComplainVO>();
        for (int i = 0; i < iPage.getRecords().size(); i++) {
            list.add(BComplainVO.parseBComplain(iPage.getRecords().get(i)));
        }
        ipage2.setRecords(list);
        ipage2.setCurrent(iPage.getCurrent());
        ipage2.setPages(iPage.getPages());
        ipage2.setSize(iPage.getSize());
        ipage2.setTotal(iPage.getTotal());
        return ResultUtil.exec(true, "成功",ipage2);
    }
    @ApiOperation(value = "查询我发起的投诉历史",notes = "基本的分页操作")
    @GetMapping("complain/myhistory.do")
    public ResultVO myhistory(@RequestParam("page") @ApiParam(name = "page",value = "页码") int page, @RequestParam("limit") @ApiParam(name = "limit",value = "每页个数")int limit, String token){
        int uid = TokenUtil.parseToken(token).getUid();
        IPage<BComplain> iPage = bComplainService.page(new Page<BComplain>(page,limit),new QueryWrapper<BComplain>().eq("uid", uid).orderByDesc("createdate"));
        IPage<BComplainVO> ipage2 = new Page<>();
        List<BComplainVO> list = new ArrayList<BComplainVO>();
        for (int i = 0; i < iPage.getRecords().size(); i++) {
            list.add(BComplainVO.parseBComplain(iPage.getRecords().get(i)));
        }
        ipage2.setRecords(list);
        ipage2.setCurrent(iPage.getCurrent());
        ipage2.setPages(iPage.getPages());
        ipage2.setSize(iPage.getSize());
        ipage2.setTotal(iPage.getTotal());

        return ResultUtil.exec(true, "成功",ipage2);
    }

    @ApiOperation(value = "添加投诉",notes = "发送内容：必填：被投诉人id(通过查询接口查询) rid，投诉理由content 选填项：图片imglist，其他内容不填")
    @PostMapping("complain/photoupload.do")
    public ResultVO upload(@ApiParam(value = "imglist",name = "imglist")@RequestParam("imglist") List<MultipartFile> imglist,
                           @ApiParam(value = "rid",name = "rid")@RequestParam("rid") Integer rid,
                           @ApiParam(value = "content",name = "content")@RequestParam("content") String content,
                           String token) throws IOException {

        BComplain bComplain = new BComplain();
        bComplain.setCreatedate(new Date());
        bComplain.setStatus(1);
        bComplain.setUid(TokenUtil.parseToken(token).getUid());
        bComplain.setContent(content);
        bComplain.setRid(rid);
        int count = 1;

        // 保存图片资源
        for (MultipartFile upfile:imglist) {
            // 获取上传文件的文件名
            String fileName = upfile.getOriginalFilename();
            fileName = UUID.randomUUID().toString() + fileName;
            String path = SystemConst.FILEPATH;
            // System.out.println(path);
            File parentPath = new File(path);
            File dirPath = new File(path);
            if (!dirPath.exists()) {
                dirPath.mkdirs();
            }
            //upfile.getInputStream()
            File file = new File(path, fileName);
            // 保存文件
            upfile.transferTo(file);
            Method[] methods = bComplain.getClass().getDeclaredMethods();
            for (Method method:methods) {
                if (method.getName().startsWith("set") && method.getName().endsWith(String.valueOf(count))) {
                    try {
                        method.invoke(bComplain,fileName);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                    count += 1;
                    break;
                }
            }

        }

        return ResultUtil.exec(bComplainService.save(bComplain),"添加投诉",null);
    }

    @ApiOperation(value = "查询用户",notes = "通过用户输入的投诉人id或者电话查询目标用户，查询为0时返回错误")
    @GetMapping("complain/find.do")
    public ResultVO find(@RequestParam("info") @ApiParam(name = "info",value = "查询信息") String info){
        if (CheckPhone.isMobileNO(info)) {
            FUserBasic phone = fUserBasicService.getOne(new QueryWrapper<FUserBasic>().eq("phone", info));
            return ResultUtil.exec(phone != null,"查询",phone);
        } else {
            FUserBasic username = fUserBasicService.getOne(new QueryWrapper<FUserBasic>().eq("username", info));
            return ResultUtil.exec(username != null,"查询",username);
        }
    }

}