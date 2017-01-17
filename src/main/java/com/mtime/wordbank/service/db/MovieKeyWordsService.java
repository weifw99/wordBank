package com.mtime.wordbank.service.db;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mtime.wordbank.domain.db.MovieKeyWords;
import com.mtime.wordbank.mapper.MovieKeyWordsMapper;
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
public class MovieKeyWordsService {
    private Logger log = LoggerFactory.getLogger(MovieKeyWordsService.class);

    @Autowired
    private MovieKeyWordsMapper movieKeyWordsMapper;

    public int saveEntity(MovieKeyWords movieKeyWords){
        return movieKeyWordsMapper.insertSelective(movieKeyWords);
    }


    public int saveList(List<MovieKeyWords> wordList){
        return movieKeyWordsMapper.insertList(wordList);
    }


    /**
     * 分页查询KeyWord不为null的记录
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<MovieKeyWords> findMovieKeyWordsIsNotNullByPage(int pageNum, int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        //List<MovieKeyWords> movieInfos = MovieKeyWordsMapper.selectAll();
        Example ex = new Example(MovieKeyWords.class);
        ex.createCriteria().andIsNotNull("keyWord");
        List<MovieKeyWords> MovieKeyWordses = movieKeyWordsMapper.selectByExample(ex);
        PageInfo<MovieKeyWords> movieInfoPage = new PageInfo<MovieKeyWords>(MovieKeyWordses);
        return movieInfoPage;
    }
}
