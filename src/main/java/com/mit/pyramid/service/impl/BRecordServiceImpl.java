package com.mit.pyramid.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mit.pyramid.dao.BRecordMapper;
import com.mit.pyramid.entity.BRecord;
import com.mit.pyramid.service.BRecordService;
import org.springframework.stereotype.Service;

@Service
public class BRecordServiceImpl extends ServiceImpl<BRecordMapper, BRecord> implements BRecordService {
}
