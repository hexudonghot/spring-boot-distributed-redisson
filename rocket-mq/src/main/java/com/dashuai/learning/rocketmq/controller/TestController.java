package com.dashuai.learning.rocketmq.controller;

import com.dashuai.learning.rocketmq.mq.delay.DelayProducer;
import com.dashuai.learning.rocketmq.mq.filter.FilterProducer;
import com.dashuai.learning.rocketmq.mq.order.OrderedProducer;
import com.dashuai.learning.rocketmq.mq.simple.Producer;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

/**
 * Created in 2019.04.03
 *
 * @author Liaozihong
 */
@RestController
public class TestController {
    /**
     * The Producer.
     */
    @Autowired
    Producer producer;

    @Autowired
    DelayProducer delayProducer;

    @Autowired
    FilterProducer filterProducer;

    @Autowired
    OrderedProducer orderedProducer;

    /**
     * Test send api result.
     *
     * @param msg the msg
     * @return the api result
     */
    @RequestMapping(value = "/send")
    public  void  testSend(@RequestParam String msg) throws InterruptedException, RemotingException, MQClientException, UnsupportedEncodingException {
        String result = producer.sendMsg("PushTopic", "push", msg);
        System.out.println(result);
    }

    @RequestMapping(value = "/sendDelay")
    public void testSendDelay(String msg) {
        String result = delayProducer.sendDelayMsg(msg);
        System.out.println(result);
    }

    @RequestMapping(value = "/sendFilter")
    public void testSendFilter(String msg) {
        String result = filterProducer.sendConditionMsg(msg);
        System.out.println(result);
    }

    @RequestMapping(value = "/sendOrder")
    public void testSendOrder(String msg) throws InterruptedException, RemotingException, MQClientException, MQBrokerException, UnsupportedEncodingException {
        String result = orderedProducer.sendOrderMsg(msg);
        System.out.println(result);
    }
}
