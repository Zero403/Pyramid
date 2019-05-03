package com.mit.pyramid.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mit.pyramid.common.util.ResultUtil;
import com.mit.pyramid.common.vo.BRoleVO;
import com.mit.pyramid.common.vo.ResultVO;
import com.mit.pyramid.dao.BPermissionMapper;
import com.mit.pyramid.dao.BRolePermissionMapper;
import com.mit.pyramid.entity.BPermission;
import com.mit.pyramid.entity.BRole;
import com.mit.pyramid.dao.BRoleMapper;
import com.mit.pyramid.entity.BRolePermission;
import com.mit.pyramid.service.BRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Ery
 * @since 2019-05-01
 */
@Service
public class BRoleServiceImpl extends ServiceImpl<BRoleMapper, BRole> implements BRoleService {

    @Autowired
    private BRoleMapper roleDao;
    @Autowired
    private BRolePermissionMapper rpDao;
    @Autowired
    private BPermissionMapper permissionDao;
    @Override
    public ResultVO findroleList() {
        List<BRole> bRoles = roleDao.selectList(null);
        List<BRoleVO> bRoleVOS = new ArrayList<>();
        for(BRole role : bRoles){
            BRoleVO bRoleVO = new BRoleVO();
            bRoleVO.setId(role.getId());
            bRoleVO.setInfo(role.getInfo());
            bRoleVO.setName(role.getName());
            List<BRolePermission> rps = rpDao.selectList(new QueryWrapper<BRolePermission>().eq("rid", role.getId()));
            List<Integer> pids = new ArrayList<Integer>();
            for(BRolePermission bRolePermission : rps){
                pids.add(bRolePermission.getPid());
            }
            bRoleVO.setPermissions(permissionDao.selectList(new QueryWrapper<BPermission>().in("id", pids)));
            bRoleVOS.add(bRoleVO);
        }


        return ResultUtil.exec(true, "", bRoleVOS);
    }

}
