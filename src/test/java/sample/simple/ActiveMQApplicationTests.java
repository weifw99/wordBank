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
public class ActiveMQApplicationTests {

    @Autowired
    ApplicationContext ctx;
    @Autowired
    Producer producer;
    @Autowired
    Consumer consumer;


    @Before
    public void before() {
        System.setProperty("hadoop.home.dir", "D:\\software\\soft\\apache\\Hadoop\\hadoop-2.5.2");
    }

    /**
     * 发送消息
     * @throws Exception
     */
    @Test
    public void testProducerMassage() throws Exception {
        Queue queue = new ActiveMQQueue("sample.queue");//sample.queue
        producer.send(queue,"hello spring boot activemq");
        producer.send(queue,"hello1 spring boot activemq");
        producer.send(queue,"hello2 spring boot activemq");
        producer.send(queue,"hello3 spring boot activemq");
        producer.send(queue,"hello4 spring boot activemq");
        producer.send(queue,"hello5 spring boot activemq");
        producer.send(queue,"hello6 spring boot activemq");
        producer.send(queue,"hello7 spring boot activemq");
        producer.send(queue,"hello8 spring boot activemq");
        System.out.println("发送完毕");
        System.in.read();
    }
    /**
     * 接收消息
     * @throws Exception
     */
    @Test
    public void testConsumerMassage() throws Exception {
        //consumer.receiveQueue();
        System.in.read();
    }

}
