package com.mit.pyramid.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mit.pyramid.common.vo.ResultVO;
import com.mit.pyramid.entity.BProportion;

import java.util.Map;

/**
 * @Author: Ery
 * @Date: 2019/5/3 13:42
 * @Version null
 */
public interface BProportionService extends IService<BProportion> {
    ResultVO updateProportion(Integer five, Integer nine, Integer thirteen);

    Map<Integer, Integer> findProporton();
}
