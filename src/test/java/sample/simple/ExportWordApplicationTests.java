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
import com.mtime.wordbank.service.bus.ExportIkWordsService;
import com.mtime.wordbank.service.bus.MovieAndPersonExportToDbService;
import com.mtime.wordbank.service.bus.MovieService;
import com.mtime.wordbank.service.bus.PersonService;
import com.mtime.wordbank.service.db.*;
import com.mtime.wordbank.utils.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Tests for {@link Application}.
 *
 * @author Dave Syer
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class ExportWordApplicationTests {

    @Autowired
    private MovieAndPersonExportToDbService movieAndPersonExportToDbService;
    @Autowired
    private ExportIkWordsService exportIkWordsService;


    @Before
    public void before() {
        System.setProperty("hadoop.home.dir", "D:\\software\\soft\\apache\\Hadoop\\hadoop-2.5.2");
    }

    /**
     * 从导出整理的词库,运行前清空数据库的表结构
     *
     * @throws Exception
     */
    @Test
    public void testExportWordsInfo() throws Exception {
        movieAndPersonExportToDbService.getMovieAndPersonInfoToDB();
        movieAndPersonExportToDbService.trimMovieInfo();
        exportIkWordsService.importPersonName();
        exportIkWordsService.importMovieTitle();
        exportIkWordsService.importMovieKeyWords();
        exportIkWordsService.importDirectorAndActorWords();
    }

}
