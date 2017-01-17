package com.mtime.wordbank.service.db;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mtime.wordbank.domain.db.MovieTitleWords;
import com.mtime.wordbank.mapper.MovieTitleWordsMapper;
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
public class MovieTitleWordsService {
    private Logger log = LoggerFactory.getLogger(MovieTitleWordsService.class);

    @Autowired
    private MovieTitleWordsMapper movieTitleWordsMapper;

    public int saveEntity(MovieTitleWords movieTitleWords){
        return movieTitleWordsMapper.insertSelective(movieTitleWords);
    }

    public int saveList(List<MovieTitleWords> wordList){
        return movieTitleWordsMapper.insertList(wordList);
    }



    /**
     * 分页查询keyWord不为null的记录
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<MovieTitleWords> findMovieTitleWordsIsNotNullByPage(int pageNum, int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        Example ex = new Example(MovieTitleWords.class);
        ex.createCriteria().andIsNotNull("keyWord");
        List<MovieTitleWords> MovieTitleWordses = movieTitleWordsMapper.selectByExample(ex);
        PageInfo<MovieTitleWords> movieInfoPage = new PageInfo<MovieTitleWords>(MovieTitleWordses);
        return movieInfoPage;
    }
}
