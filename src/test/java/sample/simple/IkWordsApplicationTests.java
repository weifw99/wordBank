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

package sample.simple;

import com.github.pagehelper.PageInfo;
import com.mtime.wordbank.Application;
import com.mtime.wordbank.domain.db.*;
import com.mtime.wordbank.service.db.*;
import com.mtime.wordbank.utils.StringUtil;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.FileWriter;
import java.io.Writer;
import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Tests for {@link Application}.
 *
 * @author Dave Syer
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class IkWordsApplicationTests {

    @Autowired
    private DirectorWordsService directorWordsService;
    @Autowired
    private ActorWordsService actorWordsService;
    @Autowired
    private MovieInfoService movieInfoService;
    @Autowired
    private IkWordsService ikWordsService;
    @Autowired
    private MovieKeyWordsService movieKeyWordsService;
    @Autowired
    private MovieTitleWordsService movieTitleWordsService;
    @Autowired
    private PersonInfoService personInfoService;


    @Before
    public void before() {
        System.setProperty("hadoop.home.dir", "D:\\software\\soft\\apache\\Hadoop\\hadoop-2.5.2");
    }

    /**
     * 判断词库中是否存在该词
     * @throws Exception
     */
    @Test
    public void testIfExistWord() throws Exception {
        boolean b = ikWordsService.ifExistWord("庞祖云");
        System.out.println(b);
    }

    /**
     * 导出影人词库
     * @throws Exception
     */
    @Test
    public void testImportPersonName() throws Exception {
        PageInfo<PersonInfo> personInfoPageInfo = this.personInfoService.findPersonInfoIsNotNullByPage(1, 5000);
        Set<String> words = new HashSet<String>();
        getPersonName(words, personInfoPageInfo.getList());
        for (int i = 2; i <= personInfoPageInfo.getPages() ; i++) {
            PageInfo<PersonInfo> p = this.personInfoService.findPersonInfoIsNotNullByPage(i, 5000);
            getPersonName(words, p.getList());
        }
        System.out.println(words.size());
        Set<String> allStringWords = ikWordsService.getAllStringWords();

        //保存词库：
        URL url = Thread.currentThread().getContextClassLoader().getResource("person_name.dic");
        System.out.println(url.getPath());
        Writer output = new FileWriter(url.getFile());
        for (String s:words){
            boolean b = allStringWords.contains(s);
            if (!b){
                allStringWords.add(s);
                IOUtils.write(s+"\n",output);
                ikWordsService.saveString(s, 1, "person_name");
            }
        }
        output.close();
    }

    private void getPersonName(Set<String> words, List<PersonInfo> WordsList) {
        if (WordsList!=null && WordsList.size()>0){
            for (PersonInfo d: WordsList){
                String nameCn = StringUtil.parseString(d.getNameCn());
                String[] split = nameCn.split("·");
                for (String s:split){
                    if(StringUtils.isNotBlank(s) && s.length()>1){
                        words.add(s);
                    }
                }
            }
        }
    }

    /**
     * 导出整理后的movie_title
     * @throws Exception
     */
    @Test
    public void testImportMovieTitle() throws Exception {
        PageInfo<MovieTitleWords> keyWordsPageInfo = movieTitleWordsService.findMovieTitleWordsIsNotNullByPage(1, 5000);
        Set<String> words = new HashSet<String>();
        getMovieTitle(words, keyWordsPageInfo.getList());

        for (int i = 2; i <= keyWordsPageInfo.getPages() ; i++) {
            PageInfo<MovieTitleWords> p = movieTitleWordsService.findMovieTitleWordsIsNotNullByPage(i, 5000);
            getMovieTitle(words, p.getList());
        }
        System.out.println(words.size());
        Set<String> allStringWords = ikWordsService.getAllStringWords();
        //保存词库：
        URL url = Thread.currentThread().getContextClassLoader().getResource("movie_title.dic");
        URL url2 = Thread.currentThread().getContextClassLoader().getResource("movie_titleLong.dic");
        System.out.println(url.getPath());
        Writer output = new FileWriter(url.getFile());
        Writer output2 = new FileWriter(url2.getFile());
        for (String s:words){
            //boolean b = ikWordsService.ifExistWord(s);
            boolean b = allStringWords.contains(s);
            if (!b){
                allStringWords.add(s);
                if (s.length()<5){
                    //words.add(s);
                    IOUtils.write(s+"\n",output);
                    ikWordsService.saveString(s, 1, "movie_title");
                } else{
                    IOUtils.write(s+"\n",output2);
                    ikWordsService.saveString(s, 0, "movie_title");
                }
            }
        }
        output.close();
        output2.close();
    }

    /**
     * 导出整理后的keyWords
     * @throws Exception
     */
    @Test
    public void testImportMovieKeyWords() throws Exception {
        PageInfo<MovieKeyWords> keyWordsPageInfo = movieKeyWordsService.findMovieKeyWordsIsNotNullByPage(1, 5000);
        Set<String> words = new HashSet<String>();
        getMovieKeyWords(words, keyWordsPageInfo.getList());

        for (int i = 2; i <= keyWordsPageInfo.getPages() ; i++) {
            PageInfo<MovieKeyWords> p = movieKeyWordsService.findMovieKeyWordsIsNotNullByPage(i, 5000);
            getMovieKeyWords(words, p.getList());
        }
        System.out.println(words);
        Set<String> allStringWords = ikWordsService.getAllStringWords();
        //保存词库：
        URL url = Thread.currentThread().getContextClassLoader().getResource("movie_keywords.dic");
        System.out.println(url.getPath());
        Writer output = new FileWriter(url.getFile());
        for (String s:words){
            //boolean b = ikWordsService.ifExistWord(s);
            boolean b = allStringWords.contains(s);
            if (!b){
                allStringWords.add(s);
                if (s.length()<5){
                    //words.add(s);
                    IOUtils.write(s+"\n",output);
                    ikWordsService.saveString(s, 1, "movie_keyWords");
                } else{
                    ikWordsService.saveString(s, 0, "movie_keyWords");
                }
            }
        }
        output.close();
    }


    /**
     * 导出整理后的导演和演员的词库
     * @throws Exception
     */
    @Test
    public void testImportDirectorAndActorWords() throws Exception {
        PageInfo<DirectorWords> directorWordsPageInfo = directorWordsService.findDirectorWordsIsNotNullByPage(1, 5000);
        Set<String> words = new HashSet<String>();
        getDirectorWords(words, directorWordsPageInfo.getList());

        for (int i = 2; i <= directorWordsPageInfo.getPages() ; i++) {
            PageInfo<DirectorWords> p = directorWordsService.findDirectorWordsIsNotNullByPage(i, 5000);
            getDirectorWords(words, p.getList());
        }

        PageInfo<ActorWords> actorWordsPageInfo = actorWordsService.findActorWordsIsNotNullByPage(1, 5000);
        getActorWords(words, actorWordsPageInfo.getList());

        for (int i = 2; i <= actorWordsPageInfo.getPages() ; i++) {
            PageInfo<ActorWords> p = actorWordsService.findActorWordsIsNotNullByPage(i, 5000);
            getActorWords(words, p.getList());
        }

        System.out.println(words);
        Set<String> allStringWords = ikWordsService.getAllStringWords();
        //保存词库：
        URL url = Thread.currentThread().getContextClassLoader().getResource("directorandactorwords.dic");
        System.out.println(url.getPath());
        Writer output = new FileWriter(url.getFile());
        for (String s:words){
            boolean b = allStringWords.contains(s);
            if (!b){
                allStringWords.add(s);
                ikWordsService.saveString(s, 1, "movie_actorAndDircetor");
                IOUtils.write(s+"\n",output);
            }
        }
        output.close();
    }

    private void getDirectorWords(Set<String> words, List<DirectorWords> directorWordsList) {
        if (directorWordsList!=null && directorWordsList.size()>0){
            for (DirectorWords d: directorWordsList){
                String[] split = d.getDirectorWord().split("#");
                for (String s:split){
                    if(StringUtils.isNotBlank(s) && s.length()>1){
                        words.add(s);
                    }
                }
            }
        }
    }
    private void getMovieTitle(Set<String> words, List<MovieTitleWords> WordsList) {
        if (WordsList!=null && WordsList.size()>0){
            for (MovieTitleWords d: WordsList){
                String[] split = d.getKeyWord().split("[~#《》？ （）;、，！：:…!\\(\\)\\+=]");
                for (String s:split){
                    if(StringUtils.isNotBlank(s) && s.length()>1){
                        words.add(s);
                    }
                }
            }
        }
    }
    private void getMovieKeyWords(Set<String> words, List<MovieKeyWords> WordsList) {
        if (WordsList!=null && WordsList.size()>0){
            for (MovieKeyWords d: WordsList){
                String[] split = d.getKeyWord().split("#");
                for (String s:split){
                    if(StringUtils.isNotBlank(s) && s.length()>1){
                        words.add(s);
                    }
                }
            }
        }
    }
    private void getActorWords(Set<String> words, List<ActorWords> ActorWordsList) {
        if (ActorWordsList!=null && ActorWordsList.size()>0){
            for (ActorWords a: ActorWordsList){
                String[] split = a.getActorWord().split("#");
                for (String s:split){
                    if(StringUtils.isNotBlank(s) && s.length()>1){
                        words.add(s);
                    }
                }
            }
        }
    }
}
