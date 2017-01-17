package com.mtime.wordbank.domain.db;

import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Mtime on 2016/3/1.
 */
@Table(name = "movie_key_words")
public class MovieKeyWords implements Serializable {
    private String id;
    private String movieKeyWord;
    private String keyWord;
    private int status;
    private Date createDate;
    private Date updateDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMovieKeyWord() {
        return movieKeyWord;
    }

    public void setMovieKeyWord(String movieKeyWord) {
        this.movieKeyWord = movieKeyWord;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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
