package com.mit.pyramid.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mit.pyramid.common.util.ResultUtil;
import com.mit.pyramid.common.vo.ResultVO;
import com.mit.pyramid.entity.BAgent;
import com.mit.pyramid.service.BAgentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Chen
 * @since 2019-05-01
 */

@RestController
@Api(value = "代理商相关操作",tags = "代理商管理")
public class BAgentController {

    @Autowired
    private BAgentService bAgentService;

    @ApiOperation(value = "代理商列表",notes = "实现代理商的分页展示")
    @GetMapping("agent/listbypage.do")
    public ResultVO listByPage(@RequestParam("page") int page, @RequestParam("limit") int limit){

        Page<BAgent> agentPage = new Page<>(page, limit);
        IPage<BAgent> iPage = bAgentService.page(agentPage, new QueryWrapper<BAgent>().orderByAsc("settleingtime"));

        return ResultUtil.exec(true, String.valueOf(agentPage.getTotal()),iPage.getRecords());
    }


    @ApiOperation(value = "代理商新增",notes = "实现代理商单条插入 agentname:代理商名字 agentcompany：代理商公司 agentprovide:提供给代理商了啥 settleingtime：代理商入驻时间")
    @PostMapping("agent/add.do")
    public ResultVO addAgent(@RequestBody BAgent agent){


        bAgentService.addAgent(agent);

        return ResultUtil.exec(true, "新增成功",1);
    }

    @ApiOperation(value = "代理商批量新增",notes = "实现代理商的excel的批量导入")
    @PostMapping("agent/import.do")
    public ResultVO addAgentByExcel(@RequestParam MultipartFile mFile){
        try {
            String fileName = mFile.getOriginalFilename();
            // 获取上传文件的输入流
            InputStream inputStream = mFile.getInputStream();
            // 调用工具类中方法，读取excel文件中数据
            List<Map<String, Object>> sourceList = com.cc.utils.ImportExcel.readExcel(fileName, inputStream);

            // 将对象先转为json格式字符串，然后再转为List<SysUser> 对象
            ObjectMapper objMapper = new ObjectMapper();
            String infos = objMapper.writeValueAsString(sourceList);

            // json字符串转对象
            List<BAgent> list = objMapper.readValue(infos, new TypeReference<List<BAgent>>() {});

            // 批量添加
            bAgentService.saveBatch(list);
            return ResultUtil.exec(true,"导入成功",list.size());

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

            return ResultUtil.exec(false,"导入失败",0);
        }
    }
}
