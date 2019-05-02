package com.mit.pyramid.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mit.pyramid.common.util.ResultUtil;
import com.mit.pyramid.common.vo.ResultVO;
import com.mit.pyramid.dao.BRolePermissionMapper;
import com.mit.pyramid.entity.BPermission;
import com.mit.pyramid.dao.BPermissionMapper;
import com.mit.pyramid.entity.BRolePermission;
import com.mit.pyramid.service.BPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Ery
 * @since 2019-05-01
 */
@Service
public class BPermissionServiceImpl extends ServiceImpl<BPermissionMapper, BPermission> implements BPermissionService {

    @Autowired
    private BPermissionMapper permissionDao;
    @Autowired
    private BRolePermissionMapper rpDao;

    @Override
    public ResultVO delPermission(int id) {
        BPermission permission = permissionDao.selectOne(new QueryWrapper<BPermission>().eq("id", id));
        boolean flag = true;
        if (0 == permission.getParentid()){
            flag = null == permissionDao.selectOne(new QueryWrapper<BPermission>().eq("parentid", permission.getId()));
        }

        if (flag){
            permissionDao.deleteById(id);
            rpDao.delete(new QueryWrapper<BRolePermission>().eq("pid", id));
        }
        return ResultUtil.exec(flag, flag ? "操作成功" : "操作失败", null);
    }
}
