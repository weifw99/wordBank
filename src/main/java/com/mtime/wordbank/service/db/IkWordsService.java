package com.mtime.wordbank.service.db;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mtime.wordbank.domain.db.IkWords;
import com.mtime.wordbank.mapper.IkWordsMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Mtime on 2016/2/23.
 */
@Service
@Transactional
public class IkWordsService {
    private Logger log = LoggerFactory.getLogger(IkWordsService.class);

    @Autowired
    private IkWordsMapper ikWordsMapper;

    public int saveEntity(IkWords ikWords){
        return ikWordsMapper.insertSelective(ikWords);
    }

    /***
     * 保存某个词，
     * @param s
     * @param status 状态，0停用，或 1启用
     * @param source 来源
     * @return
     */
    public int saveString(String s, int status, String source){
        IkWords entity = new IkWords();
        entity.setWord(s);
        if(0==status || 1==status){
            entity.setStatus(status);
        } else {
            entity.setStatus(0);
        }
        if (StringUtils.isNotBlank(source)){
            entity.setSource(source);
        }
        entity.setCreateDate(new Date());
        entity.setUpdateDate(new Date());
        return ikWordsMapper.insertSelective(entity);
    }

    public int saveList(List<IkWords> wordList){
        return ikWordsMapper.insertList(wordList);
    }

    /**
     * 判断是否存在word
     * @param word
     * @return
     */
    public boolean ifExistWord(String word){
        if (StringUtils.isBlank(word)){
            return false;
        }
        Example ex = new Example(IkWords.class);
        ex.createCriteria().andEqualTo("word",word.trim());
        int num = ikWordsMapper.selectCountByExample(ex);
        if (num>0){
            return true;
        }else{
            return false;
        }
    }

    public List<IkWords> getAllIkWords(){
        return ikWordsMapper.selectAll();
    }
    public Set<String> getAllStringWords(){
        Set<String> set = new HashSet<String>();
        List<IkWords> ikWordses = ikWordsMapper.selectAll();
        for (IkWords w: ikWordses) {
            set.add(w.getWord());
        }
        return set;
    }

}
