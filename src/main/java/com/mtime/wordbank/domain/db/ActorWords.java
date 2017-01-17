package com.mtime.wordbank.domain.db;

import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Mtime on 2016/3/1.
 */
@Table(name = "actor_words")
public class ActorWords  implements Serializable {
    private String id;
    private String actorName;
    private String actorWord;
    private int status;
    private Date createDate;
    private Date updateDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getActorName() {
        return actorName;
    }

    public void setActorName(String actorName) {
        this.actorName = actorName;
    }

    public String getActorWord() {
        return actorWord;
    }

    public void setActorWord(String actorWord) {
        this.actorWord = actorWord;
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
