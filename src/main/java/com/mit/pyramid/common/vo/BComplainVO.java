package com.mit.pyramid.common.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.extension.api.R;
import com.mit.pyramid.entity.BComplain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BComplainVO {


    /**
     * <p>
     *
     * </p>
     *
     * @author kilo
     * @since 2019-05-01
     */


        private static final long serialVersionUID = 1L;

        private Integer id;
        private Integer uid;
        private Integer rid;
        private Date createdate;
        private String content;
        private List<String> imglist;

        public List<String> getImglist() {
            return imglist;
        }

        public void setImglist(List<String> imglist) {
            this.imglist = imglist;
        }

        /**
         * 1代表受理 0代表未受理 2代表驳回
         */
        private Integer status;


        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getUid() {
            return uid;
        }

        public void setUid(Integer uid) {
            this.uid = uid;
        }

        public Integer getRid() {
            return rid;
        }

        public void setRid(Integer rid) {
            this.rid = rid;
        }

        public Date getCreatedate() {
            return createdate;
        }

        public void setCreatedate(Date createdate) {
            this.createdate = createdate;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }


        public static BComplainVO parseBComplain(BComplain bComplain){
            List<String> list = new ArrayList<>();
            list.add(bComplain.getImg1());
            list.add(bComplain.getImg2());
            list.add(bComplain.getImg3());
            list.add(bComplain.getImg4());
            list.add(bComplain.getImg5());
            list.add(bComplain.getImg6());
            BComplainVO bComplainVO = new BComplainVO();
            bComplainVO.setImglist(list);
            bComplainVO.setContent(bComplain.getContent());
            bComplainVO.setCreatedate(bComplain.getCreatedate());
            bComplainVO.setId(bComplain.getId());
            bComplainVO.setRid(bComplain.getRid());
            bComplainVO.setUid(bComplain.getUid());
            bComplainVO.setStatus(bComplain.getStatus());
            return bComplainVO;
        }

}