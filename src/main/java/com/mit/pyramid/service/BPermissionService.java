package com.mit.pyramid.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mit.pyramid.common.vo.ResultVO;
import com.mit.pyramid.entity.BPermission;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Ery
 * @since 2019-05-01
 */
public interface BPermissionService extends IService<BPermission> {
	ResultVO delPermission(int id);
}
