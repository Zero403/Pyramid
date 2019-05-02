package com.mit.pyramid.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mit.pyramid.common.util.ResultUtil;
import com.mit.pyramid.common.vo.ResultVO;
import com.mit.pyramid.dao.FUserBasicMapper;
import com.mit.pyramid.dao.FUserStatusMapper;
import com.mit.pyramid.entity.BMessage;
import com.mit.pyramid.dao.BMessageMapper;
import com.mit.pyramid.entity.FUserBasic;
import com.mit.pyramid.entity.FUserStatus;
import com.mit.pyramid.service.BMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
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
public class BMessageServiceImpl extends ServiceImpl<BMessageMapper, BMessage> implements BMessageService {

    @Autowired
    private BMessageMapper messageDao;

    /**
     *
     * @param uid 用户ID
     * @param type 0全部1未读2已读3发送过的消息 其他出错
     * @return ResultVO 返回结果为查询到列表按时间逆序排列
     */
    @Override
    public ResultVO selectMyMessage(int uid, int type) {
        switch (type){
            case 0:
                return ResultUtil.exec(true, "查询成功", messageDao.myMessage(uid));
            case 1:
                return ResultUtil.exec(true,"查询成功",messageDao.myMessageByType(uid, type));
            case 2:
                return ResultUtil.exec(true,"查询成功",messageDao.myMessageByType(uid, type));
            case 3:
                return ResultUtil.exec(true, "查询成功",messageDao.sendedMessage(uid));
            default:
                return ResultUtil.setERROR("类型错误");
        }
    }

    /**
     *
     * @param message message对象
     * @return ResultVO
     */
    @Override
    public ResultVO sendMessage(BMessage message) {
        message.setCreatetime(new Date());
        message.setType(1);
        //boolean flag = messageDao.insert(message) > 0;
        boolean flag = messageDao.insertMessage(message) > 0;
        return ResultUtil.exec(flag, flag?"消息发送成功":"消息发送失败","null");
    }

    @Autowired
    private FUserBasicMapper fUserDao;
    @Autowired
    private FUserStatusMapper fuserstatusDao;
    @Override
    public ResultVO batchMessage(int type, int value, String title, String description) {
        List<FUserBasic> users = new ArrayList<>();
        List<FUserStatus> userStatuses = new ArrayList<>();
        List<BMessage> messages = new ArrayList<>();

        if (1 == type){
            QueryWrapper<FUserBasic> wrapper = new QueryWrapper<>();
            users = fUserDao.selectList(wrapper);
            for(FUserBasic user: users){
                BMessage message = new BMessage();
                message.setType(1);
                message.setCreatetime(new Date());
                message.setSendid(0);
                message.setOrderid(user.getId());
                message.setTitle(title);
                message.setDiscription(description);
                messages.add(message);
            }


        }
        if (2 == type){
            QueryWrapper<FUserStatus> wrapper = new QueryWrapper<>();
            wrapper.eq("sid", value + 100);
            userStatuses = fuserstatusDao.selectList(wrapper);
            for(FUserStatus user: userStatuses){
                BMessage message = new BMessage();
                message.setType(1);
                message.setCreatetime(new Date());
                message.setSendid(0);
                message.setOrderid(user.getUid());
                message.setTitle(title);
                message.setDiscription(description);
                messages.add(message);
            }
        }
        if(3 == type){
            BMessage message = new BMessage();
            message.setType(1);
            message.setCreatetime(new Date());
            message.setSendid(0);
            message.setOrderid(value);
            message.setTitle(title);
            message.setDiscription(description);
            messages.add(message);
        }
        if(messages.size() > 0){
            saveBatch(messages, messages.size());
            return ResultUtil.setOK("发送完成");
        }

        return null;
    }


}
