package com.mit.pyramid.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mit.pyramid.common.vo.PermissionVO;
import com.mit.pyramid.entity.BPermission;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author Ery
 * @since 2019-05-01
 */
public interface BPermissionMapper extends BaseMapper<BPermission> {
    @Select("SELECT p.permission parentName,s.permission permission, s.url url FROM b_permission s INNER JOIN b_permission p ON s.parentid = p.id INNER JOIN b_role_permission brp ON brp.pid = s.id WHERE brp.rid = #{rid}")
    List<PermissionVO> menuList(int rid);

    @Select("SELECT permission FROM b_role_permission brp INNER JOIN b_permission p ON brp.pid = p.id WHERE brp.rid = #{rid}")
    List<String> permissionList(int rid);
}