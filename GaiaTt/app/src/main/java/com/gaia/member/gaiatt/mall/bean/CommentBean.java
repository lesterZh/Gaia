package com.gaia.member.gaiatt.mall.bean;

/**
 * @Title: CommentBean
 * @Package com.gaia.member.gaiatt.mall
 * @Description: TODO
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @author Android客户端开发组-zhangHaiTao
 * @date 2016/6/20 10:29
 * @version V1.0
 */

import java.io.Serializable;

/**
 * @author Android客户端开发组-zhangHaiTao
 * @ClassName: CommentBean
 * Description: 评论
 * @date 2016/6/20 10:29
 */

public class CommentBean implements Serializable{
    String userId;
    String commentDate;
    String BuyDate;
    String comment;//评论内容
    int type; //0 好评，1 中评 2差评
    int star;//评分 1~5

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(String commentDate) {
        this.commentDate = commentDate;
    }

    public String getBuyDate() {
        return BuyDate;
    }

    public void setBuyDate(String buyDate) {
        BuyDate = buyDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }
}
