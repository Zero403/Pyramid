package com.mit.pyramid.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mit.pyramid.common.util.ResultUtil;
import com.mit.pyramid.common.util.TokenUtil;
import com.mit.pyramid.common.vo.ResultVO;
import com.mit.pyramid.common.vo.TokenVO;
import com.mit.pyramid.common.vo.UserLevelDownVO;
import com.mit.pyramid.entity.FRealnameaut;
import com.mit.pyramid.service.FRealnameautService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;

@RestController
@Api(value = "前台用户实名认证相关操作", tags = "前台用户实名认证")
public class FRealnameautController {

    @Autowired
    private FRealnameautService fRealnameautService;

    @GetMapping("/iscertified.do")
    @ApiOperation(value = "校验是否已完成过实名认证", notes = "是否已完成过认证")
    public ResultVO isCertified(@RequestParam("token") @ApiParam(name = "token", value = "用户的token") String token) {
        // 解析token，获取uid
        TokenVO tokenVO = TokenUtil.parseToken(token);
        int uid = tokenVO.getUid();
        return fRealnameautService.selectRealnameByUid(uid);
    }


    @PostMapping("/realnameaut.do")
    @ApiOperation(value = "实名认证信息上传", notes = "实名认证")
    public ResultVO realNameAut(@RequestParam("token") @ApiParam(name = "token", value = "用户的token") String token,
                                @RequestParam("realName") @ApiParam(name = "realName", value = "真实姓名") String realName,
                                @RequestParam("IDnumber") @ApiParam(name = "IDnumber", value = "身份证号码") String IDnumber,
                                @RequestParam("fcard") @ApiParam(name = "fcard", value = "身份证正面照") MultipartFile fcard,
                                @RequestParam("bcard") @ApiParam(name = "bcard", value = "身份证反面照") MultipartFile bcard,
                                HttpServletRequest request) {
        // 解析token，获取uid
        TokenVO tokenVO = TokenUtil.parseToken(token);
        int uid = tokenVO.getUid();

        FRealnameaut fRealnameaut = new FRealnameaut();
        fRealnameaut.setCreatetime(new Date());
        fRealnameaut.setUid(uid);
        fRealnameaut.setUname(realName);
        fRealnameaut.setIdnumber(IDnumber);

        // 获取上传文件的文件名
        String frontcard = fcard.getOriginalFilename();
        String backcard = bcard.getOriginalFilename();
        fRealnameaut.setFrontcard(frontcard);
        fRealnameaut.setBackcard(backcard);
        System.out.println(frontcard + ">>>" + backcard);

        // 执行添加实名信息操作
        ResultVO resultVO = fRealnameautService.insertRealname(fRealnameaut);

        if (1000 == resultVO.getCode()) {
            // 具备新添认证信息资格后，进行上传保存文件
            String path = request.getServletContext().getRealPath("/");
            System.out.println(path);
            File parentPath = new File(path);
            // 获取父级目录的路径
            path = parentPath.getParent() + "/pyramid/webapp/idcardimages";

            System.out.println("path == " + path);
            File dirPath = new File(path);
            if (!dirPath.exists()) {
                dirPath.mkdirs();
            }
            //upfile.getInputStream()
            File file1 = new File(path, frontcard);
            File file2 = new File(path, backcard);
            try {
                // 保存文件
                fcard.transferTo(file1);
                bcard.transferTo(file2);
            } catch (IllegalStateException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return resultVO;
        }
        return resultVO;

    }


}
