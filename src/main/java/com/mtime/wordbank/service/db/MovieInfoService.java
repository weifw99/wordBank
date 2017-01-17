package com.mtime.wordbank.service.db;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mtime.wordbank.domain.db.MovieInfo;
import com.mtime.wordbank.mapper.MovieInfoMapper;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Mtime on 2016/2/23.
 */
@Service
@Transactional
public class MovieInfoService {
    private Logger log = LoggerFactory.getLogger(MovieInfoService.class);

    @Autowired
    private MovieInfoMapper movieInfoMapper;

    public int saveList(List<MovieInfo> wordList){
        return movieInfoMapper.insertList(wordList);
    }

    public PageInfo<MovieInfo> findByPage(int pageNum, int pageSize){
        //RowBounds bounds = new RowBounds(pageNum, pageSize);
        PageHelper.startPage(pageNum, pageSize);
        //List<MovieInfo> movieInfos = movieInfoMapper.selectByRowBounds(null, bounds);
        List<MovieInfo> movieInfos = movieInfoMapper.selectAll();
        PageInfo<MovieInfo> movieInfoPage = new PageInfo<MovieInfo>(movieInfos);
        return movieInfoPage;
    }

}
