package com.mtime.wordbank.domain.db;

import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Mtime on 2016/3/1.
 */
@Table(name = "director_words")
public class DirectorWords  implements Serializable {
    private String id;
    private String directorName;
    private String directorWord;
    private int status;
    private Date createDate;
    private Date updateDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    public String getDirectorWord() {
        return directorWord;
    }

    public void setDirectorWord(String directorWord) {
        this.directorWord = directorWord;
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
