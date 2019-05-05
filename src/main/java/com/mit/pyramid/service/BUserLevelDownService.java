package com.mit.pyramid.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mit.pyramid.common.vo.UserLevelDownVO;
import com.mit.pyramid.entity.BUserLevelDown;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 */
public interface BUserLevelDownService extends IService<BUserLevelDown> {
    public List<UserLevelDownVO> findAll(Page<UserLevelDownVO> page);
}
