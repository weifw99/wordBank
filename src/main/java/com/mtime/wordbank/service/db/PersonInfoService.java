package com.mtime.wordbank.service.db;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mtime.wordbank.domain.db.PersonInfo;
import com.mtime.wordbank.mapper.PersonInfoMapper;
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
public class PersonInfoService {
    private Logger log = LoggerFactory.getLogger(PersonInfoService.class);

    @Autowired
    private PersonInfoMapper personInfoMapper;

    public int saveList(List<PersonInfo> wordList){
        return personInfoMapper.insertList(wordList);
    }

    public PageInfo<PersonInfo> findByPage(int pageNum, int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        List<PersonInfo> PersonInfos = personInfoMapper.selectAll();
        PageInfo<PersonInfo> PersonInfoPage = new PageInfo<PersonInfo>(PersonInfos);
        return PersonInfoPage;
    }

    /**
     * 分页查询nameCn不为null的记录
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<PersonInfo> findPersonInfoIsNotNullByPage(int pageNum, int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        Example ex = new Example(PersonInfo.class);
        ex.createCriteria().andIsNotNull("nameCn").andNotEqualTo("nameCn","");
        List<PersonInfo> PersonInfoes = personInfoMapper.selectByExample(ex);
        PageInfo<PersonInfo> movieInfoPage = new PageInfo<PersonInfo>(PersonInfoes);
        return movieInfoPage;
    }
}
