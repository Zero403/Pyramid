package com.mit.pyramid.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mit.pyramid.common.constsys.SystemConst;
import com.mit.pyramid.common.util.ImportExcel;
import com.mit.pyramid.common.util.ResultUtil;
import com.mit.pyramid.common.util.TokenUtil;
import com.mit.pyramid.common.vo.*;
import com.mit.pyramid.entity.FUserBasic;
import com.mit.pyramid.entity.FUserStatus;
import com.mit.pyramid.service.FUserBasicService;
import com.mit.pyramid.service.FUserStatusService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Ery
 * @since 2019-05-01
 */
@RestController
@Api(value = "前台用户相关操作", tags = "操作前台用户")
public class FUserBasicController {

    @Autowired
    private FUserBasicService fUserBasicService;
    @Autowired
    private FUserStatusService userStatusService;

    @PostMapping("user/register.do")
    @ApiOperation(value = "注册用户" , notes = "实现用户注册")
        public ResultVO save(@RequestBody @ApiParam(name = "user" ,value = "用户相关键值对") RegisterVO user){
        return fUserBasicService.userRegister(user);
    }

    @PostMapping("user/findIllegal.do")
    @ApiOperation(value="查询所有违规用户")
    public ResultVO pagelist(@RequestParam("page") @ApiParam(name = "page",value = "起始页数") int page, @RequestParam("limit") @ApiParam(name = "limit",value = "条数")int count){
        Page<FUserBasic> list = new Page<>(page, count);
        list.setRecords(fUserBasicService.userIllegal(list));

        return ResultUtil.exec(list.getRecords().size() > 0,"",list);
    }

    @PostMapping("user/findInvitor.do")
    @ApiOperation(value = "查询我的邀请")
    public ResultVO invitorList(@RequestParam("page") @ApiParam(name = "page",value = "起始页数") int page, @RequestParam("limit") @ApiParam(name = "limit",value = "条数")int count,String token){
        IPage<FUserBasic> list = fUserBasicService.page(new Page<FUserBasic>(page, count), new QueryWrapper<FUserBasic>().eq("inviterid", TokenUtil.parseToken(token).getUid()));

        return ResultUtil.exec(list.getTotal() > 0,"",list);
    }

    @PostMapping("user/findInvited.do")
    @ApiOperation(value = "查询被我审核的")
    public ResultVO invitedList(@RequestParam("page") @ApiParam(name = "page",value = "起始页数") int page, @RequestParam("limit") @ApiParam(name = "limit",value = "条数")int count,String token){
        IPage<FUserBasic> list = fUserBasicService.page(new Page<FUserBasic>(page, count), new QueryWrapper<FUserBasic>().eq("inviterid", TokenUtil.parseToken(token).getUid()));

        return ResultUtil.exec(list.getTotal() > 0,"",list);
    }



    //下面是后台的！！！！！@author Chen
    @GetMapping("buser/listByPage.do")
    @ApiOperation(value = "后台查询会员列表")
    public ResultVO listByPage(@RequestParam("page") @ApiParam(name = "page",value = "页码") int page, @RequestParam("limit") @ApiParam(name = "limit",value = "每页条数")int limit){
        Page<BUserBasicVO> page1 = new Page<>(page, limit);

        page1.setRecords(fUserBasicService.listByPage(page1));
//        List<BUserBasicVO> list = fUserBasicService.listByPage(page1);

        return ResultUtil.exec(true,"",page1);

    }

    //下面是后台的！！！！！@author Chen
    @PostMapping("buser/addUser.do")
    @ApiOperation(value = "后台添加单条会员")
    public ResultVO addUser(@RequestBody @ApiParam(name = "userBasic",value = "会员实体类") FUserBasic userBasic,@RequestParam("status") @ApiParam(name = "status",value = "会员等级") int status){

        int i = fUserBasicService.insertUser(userBasic, status);

        return ResultUtil.exec(true,"加入成功",i);

    }


    //后台的 @author Chen
    @PostMapping("buser/addUserBatch.do")
    @ApiOperation(value = "后台批量添加会员")
    public ResultVO addUserBatch(@RequestParam MultipartFile mFile){

        try {
            String fileName = mFile.getOriginalFilename();
            // 获取上传文件的输入流
            InputStream inputStream = mFile.getInputStream();
            // 调用工具类中方法，读取excel文件中数据
            List<Map<String, Object>> sourceList = ImportExcel.readExcel(fileName, inputStream);

            // 将对象先转为json格式字符串，然后再转为List<SysUser> 对象
            ObjectMapper objMapper = new ObjectMapper();
            String infos = objMapper.writeValueAsString(sourceList);

            // json字符串转对象
            List<BUserBasicVO> list = objMapper.readValue(infos, new TypeReference<List<BUserBasicVO>>() {});

            // 批量添加

            List<FUserBasic> userList = null;

            List<FUserStatus> statusList = null;
            for(BUserBasicVO bUserBasicVO : list){
                FUserStatus userStatus = new FUserStatus();
                FUserBasic userBasic = new FUserBasic();
//                userBasic.setId(bUserBasicVO.getId());
                userBasic.setFlag(bUserBasicVO.getFlag());
                userBasic.setInfomation(bUserBasicVO.getInfomation());
                userBasic.setInviterid(bUserBasicVO.getInviterid());
                userBasic.setPhone(bUserBasicVO.getPhone());
                userBasic.setUsername(bUserBasicVO.getUsername());
                userBasic.setCreatedate(bUserBasicVO.getCreatedate());
                userBasic.setPassword("");
                userList.add(userBasic);
                userStatus.setUid(bUserBasicVO.getId());
                userStatus.setSid(bUserBasicVO.getStatus());
                statusList.add(userStatus);
//                userList.add(userBasic);
            }
            fUserBasicService.saveBatch(userList);
            userStatusService.saveBatch(statusList);


            return ResultUtil.exec(true,"导入成功",list.size());

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

            return ResultUtil.exec(false,"导入失败",0);
        }


    }

    //下面是后台的！！！！！@author Chen
    @GetMapping("buser/rankByPage.do")
    @ApiOperation(value = "后台等级排名")
    public ResultVO rankByPage(@RequestParam("page") @ApiParam(name = "page",value = "页码") int page, @RequestParam("limit") @ApiParam(name = "limit",value = "每页条数")int limit){
        List<BUserRankVO> list = fUserBasicService.rankList();

        List<BUserRankVO> list1 = list.subList((page - 1) * limit, limit * page);
        return ResultUtil.exec(true, String.valueOf(list.size()),list1);

    }

    //下面是后台的！！！！！@author Chen
    @GetMapping("buser/inviteRankByPage.do")
    @ApiOperation(value = "后台邀请排名")
    public ResultVO levalRankByPage(@RequestParam("page") @ApiParam(name = "page",value = "页码") int page, @RequestParam("limit") @ApiParam(name = "limit",value = "每页条数")int limit){
        List<BUserRankVO> list = fUserBasicService.inviteList();

        List<BUserRankVO> list1 = list.subList((page - 1) * limit, limit * page);
        return ResultUtil.exec(true, String.valueOf(list.size()),list1);

    }

    @GetMapping("user/info.do")
    @ApiOperation(value = "查询用户信息")
    public ResultVO findInfo(String token){
        FUserBasic user = fUserBasicService.getById(TokenUtil.parseToken(token).getUid());
        return ResultUtil.exec(true, "",user);

    }

    @PostMapping("user/invitername.do")
    @ApiOperation(value = "邀请人的信息")
    public ResultVO findName(@ApiParam("邀请人的id") Integer inviterid){
        FUserBasic user = fUserBasicService.getById(inviterid);
        Map<String, Object> map = new HashMap<>();
        map.put("iname", user.getUsername());
        return ResultUtil.exec(true, "",map);

    }

    @PutMapping("user/updatehead.do")
    @ApiOperation(value = "修改用户头像")
    public ResultVO updateHead(@RequestParam("headpic")@ApiParam(name = "headpic",value = "头像图片") MultipartFile headpic, String token, HttpServletRequest request) {
        TokenVO tokenVO = TokenUtil.parseToken(token);
        FUserBasic fUserBasic = fUserBasicService.getById(tokenVO.getUid());
        // 获取上传文件的文件名
        String fileName = headpic.getOriginalFilename();

        String path = request.getServletContext().getRealPath("/");
        System.out.println(path);
        File parentPath = new File(path);
        // 获取父级目录的路径
        // path = parentPath.getParent() + "/webapp/images";
        path = SystemConst.FILEPATH;
        System.out.println("path == " + path);
        File dirPath = new File(path);
        if (!dirPath.exists()) {
            dirPath.mkdirs();
        }
        //upfile.getInputStream()
        File file = new File(path, fileName);
        try {
            // 保存文件
            headpic.transferTo(file);
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        fUserBasic.setHeadpic(fileName);
//        fUserBasic.setHeadpic(path);
//        fUserBasic.setId(1);
        return ResultUtil.exec(fUserBasicService.updateById(fUserBasic),"修改用户信息",fileName);
    }
    @PutMapping("user/updateInfo.do")
    @ApiOperation(value = "修改用户除头像外信息")
    public ResultVO update(@RequestBody @ApiParam(name = "fUserBasic",value = "用户信息类") FUserBasic fUserBasic, String token) {

        TokenVO tokenVO = TokenUtil.parseToken(token);
        fUserBasic.setId(tokenVO.getUid());
        return ResultUtil.exec(fUserBasicService.updateById(fUserBasic),"修改用户信息",fUserBasic);
    }

    @PostMapping("user/level.do")
    @ApiOperation(value = "我的等级")
    public ResultVO myLevel(@ApiParam("token") String token) {
        int uid = TokenUtil.parseToken(token).getUid();
        return fUserBasicService.getStatus(uid);
    }
}
