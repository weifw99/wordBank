package com.mtime.wordbank.service.bus;

import com.mtime.wordbank.domain.db.MovieInfo;
import com.mtime.wordbank.domain.hbase.Movie;
import com.mtime.wordbank.service.db.ActorWordsService;
import com.mtime.wordbank.service.db.DirectorWordsService;
import com.mtime.wordbank.service.db.MovieInfoService;
import com.mtime.wordbank.service.hbase.MovieServerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Mtime on 2016/2/29.
 */
@Service
public class MovieService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MovieServerImpl movieServerImpl;
    @Autowired
    private MovieInfoService movieInfoService;

    public void createAllMovieInfo(){
        logger.info("Begin Movie word!");
        //for(int k =100 ;k<= 999;k++){
        for(int k =100 ;k<= 999;k++){// 获取电影数据   ->：from record 173, to record:174
            List<Movie> list = movieServerImpl.getAllMovie(k, k + 1);
            logger.info(list.toString());
            saveMovie(list);
        }
        for(int k =9990 ;k<= 9999;k++){
            List<Movie> list = movieServerImpl.getAllMovie(k, k + 1);
            logger.info(list.toString());
            saveMovie(list);
        }
        for(int k =99990 ;k<= 99999;k++){
            List<Movie> list = movieServerImpl.getAllMovie(k, k + 1);
            logger.info(list.toString());
            saveMovie(list);
        }
    }

    private void saveMovie(List<Movie> mList){
        if (mList!=null && mList.size()>0){
            ArrayList<MovieInfo> wordList = new ArrayList<MovieInfo>();
            for (Movie m : mList) {
                MovieInfo e = new MovieInfo();
                e.setId("movie_" +m.getMovieid());
                e.setKeyWords(m.getKeyword());
                e.setTitleCn(m.getTitlecn());
                e.setTitleEn(m.getTitleen());
                e.setOthersCn(m.getOthersCn());
                e.setOthersEn(m.getOthersen());
                e.setDirector(m.getDirector());
                e.setActor(m.getActor());
                e.setCreateDate(new Date());
                e.setUpdateDate(new Date());
                wordList.add(e);
            }
            if(wordList.size()>0){
                movieInfoService.saveList(wordList);
            }
        }
    }
}
