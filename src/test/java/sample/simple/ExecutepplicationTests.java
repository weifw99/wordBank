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

import com.mtime.wordbank.Application;
import com.mtime.wordbank.activemq.Consumer;
import com.mtime.wordbank.activemq.Producer;
import com.mtime.wordbank.executors.DemoAsyncTask;
import org.apache.activemq.command.ActiveMQQueue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.jms.Queue;

/**
 * Tests for {@link Application}.
 *
 * @author Dave Syer
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class ExecutepplicationTests {

    @Autowired
    ApplicationContext ctx;
    @Autowired
    DemoAsyncTask demoAsyncTask;


    @Before
    public void before() {
        System.setProperty("hadoop.home.dir", "D:\\software\\soft\\apache\\Hadoop\\hadoop-2.5.2");
    }

    @Test
    public void testDemoAsyncTask() throws Exception {
        System.out.println("间隔代码");
        for(int i =0 ;i<10;i++){
            demoAsyncTask.executeAsyncTask(i);
            System.out.println("间隔代码");
            demoAsyncTask.executeAsyncTaskPlus(i);
        }
        System.out.println("间隔代码");
        System.in.read();
    }

}
