package com.mtime.wordbank.domain.db;

import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Mtime on 2016/3/1.
 */
@Table(name = "movie_info")
public class MovieInfo  implements Serializable {
    private String id;
    private String keyWords;   //关键字
    private String titleCn;   //中文名
    private String titleEn;   //英文名
    private String othersCn;  //其他中文名
    private String othersEn;  //其他英文名
    private String director;  //导演
    private String actor;     //演员
    private Date createDate;
    private Date updateDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords;
    }

    public String getTitleCn() {
        return titleCn;
    }

    public void setTitleCn(String titleCn) {
        this.titleCn = titleCn;
    }

    public String getTitleEn() {
        return titleEn;
    }

    public void setTitleEn(String titleEn) {
        this.titleEn = titleEn;
    }

    public String getOthersCn() {
        return othersCn;
    }

    public void setOthersCn(String othersCn) {
        this.othersCn = othersCn;
    }

    public String getOthersEn() {
        return othersEn;
    }

    public void setOthersEn(String othersEn) {
        this.othersEn = othersEn;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
