package com.mtime.wordbank.service.db;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mtime.wordbank.domain.db.DirectorWords;
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
public class DirectorWordsService {
    private Logger log = LoggerFactory.getLogger(DirectorWordsService.class);

    @Autowired
    private DirectorWordsMapper directorWordsMapper;


    public int saveList(List<DirectorWords> wordList){
        return directorWordsMapper.insertList(wordList);
    }

    /**
     * 分页查询directorWords不为null的记录
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<DirectorWords> findDirectorWordsIsNotNullByPage(int pageNum, int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        //List<DirectorWords> movieInfos = directorWordsMapper.selectAll();
        Example ex = new Example(DirectorWords.class);
        ex.createCriteria().andIsNotNull("directorWord");
        List<DirectorWords> directorWordses = directorWordsMapper.selectByExample(ex);
        PageInfo<DirectorWords> movieInfoPage = new PageInfo<DirectorWords>(directorWordses);
        return movieInfoPage;
    }
}
