package com.zaomeng.zaomeng.model.repository.http.bean;

/**
 * Created by Sampson on 2019-05-20.
 * FastAndroid
 */
public class ArticleDetailBean {

    /**
     * id : 402892e96a3414ba016a341563dd0003
     * title : 早盟积分规则
     * subTitle : 早盟积分规则
     * content : <p>购买商品即可获取积分</p>
     * description : 早盟积分规则
     * author : 早盟
     * listImage : null
     * faceImage : null
     */

    private String id;
    private String title;
    private String subTitle;
    private String content;
    private String description;
    private String author;
    private Object listImage;
    private Object faceImage;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Object getListImage() {
        return listImage;
    }

    public void setListImage(Object listImage) {
        this.listImage = listImage;
    }

    public Object getFaceImage() {
        return faceImage;
    }

    public void setFaceImage(Object faceImage) {
        this.faceImage = faceImage;
    }
}
