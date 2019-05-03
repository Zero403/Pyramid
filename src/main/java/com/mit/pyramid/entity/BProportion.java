package com.mit.pyramid.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @Author: Ery
 * @Date: 2019/5/3 13:29
 * @Version null
 */
@TableName("b_proportion")
public class BProportion {
    @TableId(value = "level")
    private Integer level;
    private Integer proportion;

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getProportion() {
        return proportion;
    }

    public void setProportion(Integer proportion) {
        this.proportion = proportion;
    }
}
