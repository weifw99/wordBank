package com.mtime.wordbank.service.db;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mtime.wordbank.domain.db.ActorWords;
import com.mtime.wordbank.mapper.ActorWordsMapper;
import com.mtime.wordbank.mapper.DirectorWordsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * Created by Mtime on 2016/2/23.
 */
@Service
@Transactional
public class ActorWordsService {
    private Logger log = LoggerFactory.getLogger(ActorWordsService.class);

    @Autowired
    private ActorWordsMapper actorWordsMapper;


    public int saveList(List<ActorWords> wordList){
        return actorWordsMapper.insertList(wordList);
    }

    /**
     * 分页查询ActorWords不为null的记录
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<ActorWords> findActorWordsIsNotNullByPage(int pageNum, int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        Example ex = new Example(ActorWords.class);
        ex.createCriteria().andIsNotNull("actorWord");
        List<ActorWords> actorWordses = actorWordsMapper.selectByExample(ex);
        PageInfo<ActorWords> actorWordsPage = new PageInfo<ActorWords>(actorWordses);
        return actorWordsPage;
    }

}
