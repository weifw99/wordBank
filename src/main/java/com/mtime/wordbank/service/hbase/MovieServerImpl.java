package com.mtime.wordbank.service.hbase;

import com.mtime.wordbank.domain.hbase.Movie;
import com.mtime.wordbank.utils.ExceptionUtils;
import com.mtime.wordbank.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mtime.wordbank.commons.hbase.HbaseHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class MovieServerImpl {
    private Logger logger = LoggerFactory.getLogger(getClass());

    private static String tableName = "mtime:MovieIntegrate";

    @Autowired
    private HbaseHandler hbaseHandler;

    public List<Movie> getAllMovie(Integer start, long end) {
        List<Movie> movies = new ArrayList<Movie>();
        List<Map<String, Object>> list=null;
        logger.info("通过hbase 获取电影数据");
        logger.info("获取电影数据   ->：from record "+ start+", to record:"+end);
        try{
            list = hbaseHandler.find(tableName, String.valueOf(start), String.valueOf(end));
        }catch(Exception e){
            logger.error(ExceptionUtils.getStackTraceStr(e));
            logger.error("Table ->mtime:MovieIntegrate  can not find .");
            logger.info("HBase 请求异常 ");
        }
        if (null==list || list.size()==0) {
            return movies;
        }
        //logger.info("list info : " + list);
        movies = getMovie(list);
        return movies;
    }

    private List<Movie> getMovie(List<Map<String, Object>> list){
        List<Movie> movies = new ArrayList<Movie>();
        for(Map<String, Object> map:list){
            //logger.info("list:map.keySet:" + map.keySet().toString());
            if(Integer.parseInt((String)map.get("row"))==224172 || Integer.parseInt((String)map.get("row"))==231695){
                continue;
            }
            Movie movie = new Movie();
            movie.setMovieid(Integer.parseInt((String)map.get("row")));
            //logger.info("MovieId  " + (String)map.get("row"));
            movie.setKeyword((String)map.get("info_Keywords"));//KW
            movie.setTitlecn((String)map.get("info_TitleCn"));//MN
            String enName = StringUtil.getStandardString((String)map.get("info_TitleEn"));
            movie.setTitleen(enName);//EN
            String others = (String)map.get("info_TitleOthers");

            if(others != null) {
                String [] other = others.split("\\|\\|");  //OC OE
                if(other.length >= 1){
                    String cn = other[0];
                    if(cn.length() > 1){
                        movie.setOthersCn(cn);
                    }
                }else if(other.length >= 2){
                    String en = other[1];
                    if(en.length() > 1){
                        movie.setOthersen(en);
                    }
                }
            }

            String director = (String)map.get("info2_DirectorSet");
            if(director != null){
                String [] dires = director.split("\003");
                String directorName = "";
                int i = 0;
                for(String di:dires){
                    if(i ++ ==3) break;
                    String [] name = di.split(":");
                    if(name.length >=3){
                        directorName += name[2] + "~";
                    }
                }
                //logger.info("directorName  " + directorName);
                movie.setDirector(directorName);
            }

            String actor = (String)map.get("info2_ActorSet");
            if(actor != null){
                String [] actors = actor.split("\003");
                String actorName = "";
                int i = 0;
                for(String ac:actors){
                    if(i ++ == 5) break;
                    String [] name = ac.split(":");
                    if(name.length >=3){
                        actorName += name[2] + "~";
                    }
                }
                movie.setActor(actorName);
            }

            String infoTagSet = (String)map.get("info2_TagSet");
            if(infoTagSet != null) {
                String[] tag = infoTagSet.split(",");
                String tagName = "";
                int i = 0;
                for(String ta:tag){
                    if(i ++ ==3) break;
                    String [] name = ta.split(":");
                    if(name.length >=2){
                        tagName += name[1] + "~";
                    }
                }
                movie.setTagName(tagName);
            }
            movies.add(movie);
        }
        return movies;
    }
}
