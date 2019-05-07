package com.mit.pyramid.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mit.pyramid.common.util.ResultUtil;
import com.mit.pyramid.common.vo.ResultVO;
import com.mit.pyramid.dao.BProportionMapper;
import com.mit.pyramid.entity.BProportion;
import com.mit.pyramid.service.BProportionService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Ery
 * @Date: 2019/5/3 13:43
 * @Version null
 */
@Service
public class BProportionServiceImpl extends ServiceImpl<BProportionMapper, BProportion> implements BProportionService{




    @Override
    public ResultVO updateProportion(Integer five, Integer nine, Integer thirteen) {
        BProportion proportion = new BProportion();
        proportion.setLevel(5);
        proportion.setProportion(five);
        boolean flag5 = baseMapper.updateById(proportion) > 0;
        proportion.setLevel(9);
        proportion.setProportion(nine);
        boolean flag9 = baseMapper.updateById(proportion) > 0;
        proportion.setLevel(13);
        proportion.setProportion(thirteen);
        boolean flag13 = baseMapper.updateById(proportion) > 0;
        boolean flag = flag5 && flag9 && flag13;

        return ResultUtil.exec(flag, flag ? "操作成功" : "操作失败", null);
    }

    @Override
    public Map<Integer, Integer> findProporton() {
        Map<Integer, Integer> hs = new HashMap<>();
        List<BProportion> list = baseMapper.selectList(null);
        for(BProportion proportion: list){
            hs.put(proportion.getLevel(), proportion.getProportion());
        }
        return hs;
    }
}
