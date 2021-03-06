/*
 * Copyright 2012-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mtime.wordbank.service.bus;

import com.github.pagehelper.PageInfo;
import com.mtime.wordbank.Application;
import com.mtime.wordbank.domain.db.*;
import com.mtime.wordbank.service.db.*;
import com.mtime.wordbank.utils.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Tests for {@link Application}.
 *
 * @author Dave Syer
 */
@Service
public class MovieAndPersonExportToDbService {

    @Autowired
    private DirectorWordsService directorWordsService;
    @Autowired
    private ActorWordsService actorWordsService;
    @Autowired
    private MovieInfoService movieInfoService;
    @Autowired
    private MovieKeyWordsService movieKeyWordsService;
    @Autowired
    private MovieTitleWordsService movieTitleWordsService;
    @Autowired
    private MovieService movieService;
    @Autowired
    PersonService personService;

    /**
     * 从hbase中获取影人和电影的信息入数据库
     *
     * @throws Exception
     */
    public void getMovieAndPersonInfoToDB() throws Exception {
        personService.createAllPersonInfo();
        movieService.createAllMovieInfo();
    }



    /**
     * 从电影表中的数据导出导演和演员的数据
     * @throws Exception
     */
    public void trimMovieInfo() throws Exception {
        PageInfo<MovieInfo> pageInfo = movieInfoService.findByPage(1, 5000);
        //List<MovieInfo> list = pageInfo.getList();
        saveMovieInfo(pageInfo.getList());
        for (int i = 2; i <= pageInfo.getPages(); i++) {
            PageInfo<MovieInfo> p = movieInfoService.findByPage(i, 5000);
            //List<MovieInfo> list = pageInfo.getList();
            saveMovieInfo(p.getList());
        }
        System.out.println(pageInfo);
    }

    private void saveMovieInfo(List<MovieInfo> list) {
        if (list != null && list.size() > 0) {
            List<ActorWords> actors = new ArrayList<ActorWords>();
            List<DirectorWords> directors = new ArrayList<DirectorWords>();
            List<MovieKeyWords> MovieKeys = new ArrayList<MovieKeyWords>();
            List<MovieTitleWords> MovieTitles = new ArrayList<MovieTitleWords>();
            for (MovieInfo m : list) {
                if (StringUtils.isNotBlank(m.getActor())) {
                    ActorWords actorWords = new ActorWords();
                    actorWords.setId(m.getId());
                    actorWords.setActorName(m.getActor());
                    actorWords.setCreateDate(new Date());
                    actorWords.setUpdateDate(new Date());
                    String s = parseString(StringUtil.parseString(m.getActor()));
                    if (StringUtils.isNotBlank(s)){
                        actorWords.setActorWord(s);
                    }
                    actors.add(actorWords);
                }
                if (StringUtils.isNotBlank(m.getDirector())) {
                    DirectorWords directorWords = new DirectorWords();
                    directorWords.setId(m.getId());
                    directorWords.setDirectorName(m.getDirector());
                    directorWords.setCreateDate(new Date());
                    directorWords.setUpdateDate(new Date());
                    String s = parseString(StringUtil.parseString(m.getDirector()));
                    if (StringUtils.isNotBlank(s)){
                        directorWords.setDirectorWord(s);
                    }
                    directors.add(directorWords);
                }
                if (StringUtils.isNotBlank(m.getKeyWords())) {
                    MovieKeyWords keyWords = new MovieKeyWords();
                    keyWords.setId(m.getId());
                    keyWords.setMovieKeyWord(m.getKeyWords());
                    keyWords.setCreateDate(new Date());
                    keyWords.setUpdateDate(new Date());
                    String s = parseString(StringUtil.parseString(m.getDirector()));
                    if (StringUtils.isNotBlank(s)){
                        keyWords.setKeyWord(s);
                    }
                    MovieKeys.add(keyWords);
                }
                if (StringUtils.isNotBlank(m.getTitleCn()) || StringUtils.isNotBlank(m.getOthersCn())) {
                    MovieTitleWords titleWords = new MovieTitleWords();
                    titleWords.setId(m.getId());
                    String str = "";
                    if (StringUtils.isNotBlank(m.getTitleCn())){
                        str += m.getTitleCn() + "|";
                    }
                    if (StringUtils.isNotBlank(m.getOthersCn())){
                        str += m.getOthersCn();
                    }
                    titleWords.setMovieTitle(str);
                    String s = parseString(StringUtil.parseString(str));
                    if (StringUtils.isNotBlank(s)){
                        titleWords.setKeyWord(s);
                    }
                    titleWords.setCreateDate(new Date());
                    titleWords.setUpdateDate(new Date());
                    MovieTitles.add(titleWords);
                }
            }
            if (actors.size()>0){
                actorWordsService.saveList(actors);
            }
            if (directors.size()>0){
                directorWordsService.saveList(directors);
            }
            if (MovieKeys.size()>0){
                movieKeyWordsService.saveList(MovieKeys);
            }
            if (MovieTitles.size()>0){
                movieTitleWordsService.saveList(MovieTitles);
            }
        }
    }

    private String parseString(String str) {
        if (StringUtils.isBlank(str.trim())){
            return "";
        }
        if("~".equals(str.trim())){
            return "";
        }
        String[] strArr = str.split("[~·•,\\|]");
        StringBuilder sb = new StringBuilder("#");
        for (String s: strArr){
            if (StringUtils.isNotBlank(s.trim())){
                if ("#".equals(sb.substring(sb.length()-1))){
                    sb.append(s.trim());
                    sb.append("#");
                }
            }
        }
        if("#".equals(sb.toString())){
            return "";
        }
        return sb.toString();
    }


}
