package com.mit.pyramid.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mit.pyramid.common.vo.ResultVO;
import com.mit.pyramid.entity.BMessage;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Ery
 * @since 2019-05-01
 */
public interface BMessageService extends IService<BMessage> {
    ResultVO selectMyMessage(int uid, int type);

    ResultVO sendMessage(BMessage message);

    ResultVO batchMessage(int type, int value, String title, String description);

}
