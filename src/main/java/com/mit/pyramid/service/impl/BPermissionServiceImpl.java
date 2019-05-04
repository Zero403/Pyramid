package com.mit.pyramid.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mit.pyramid.common.util.ResultUtil;
import com.mit.pyramid.common.vo.MenuVo;
import com.mit.pyramid.common.vo.PermissionVO;
import com.mit.pyramid.common.vo.ResultVO;
import com.mit.pyramid.dao.BRolePermissionMapper;
import com.mit.pyramid.entity.BPermission;
import com.mit.pyramid.dao.BPermissionMapper;
import com.mit.pyramid.entity.BProportion;
import com.mit.pyramid.entity.BRolePermission;
import com.mit.pyramid.entity.BUser;
import com.mit.pyramid.service.BPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
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

    @Override
    public ResultVO menu(BUser user) {
        int rid = user.getId();
        List<PermissionVO> permissionVOS = baseMapper.menuList(rid);
        HashSet<String> hs = new HashSet<>();
        for(PermissionVO p : permissionVOS){
            hs.add(p.getParentName());
        }
        ArrayList<MenuVo> menu = new ArrayList<>();
        for(String parentName : hs){
            MenuVo m = new MenuVo();
            m.setParentName(parentName);
            List<PermissionVO> pVOS = new ArrayList<>();
            for(PermissionVO pVO : permissionVOS){
                if(parentName.equals(pVO.getParentName()))pVOS.add(pVO);
            }
            m.setPermissions(pVOS);
        }
        return ResultUtil.exec(true, "", menu);
    }

    public List<String> rolePermisssions(int rid){ return baseMapper.permissionList(rid);}
}
