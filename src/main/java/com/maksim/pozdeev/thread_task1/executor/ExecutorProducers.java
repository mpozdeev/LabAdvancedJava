package com.maksim.pozdeev.thread_task1.executor;

import com.maksim.pozdeev.thread_task1.Application;
import com.maksim.pozdeev.thread_task1.dto.HotelBookingRequest;
import com.maksim.pozdeev.thread_task1.queue.MyQueue;
import com.maksim.pozdeev.thread_task1.service.Producer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorProducers {
    private static final Logger logger = LoggerFactory.getLogger(ExecutorProducers.class);

    private static final Integer NUMBER_OF_PRODUCERS = 3;

    private MyQueue<HotelBookingRequest> myQueue;

    public ExecutorProducers(MyQueue<HotelBookingRequest> myQueue) {
        this.myQueue = myQueue;
    }

    public void start() throws InterruptedException {
        logger.info("ExecutorProducers.start()");
        ExecutorService executorService = Executors.newFixedThreadPool(NUMBER_OF_PRODUCERS);

        for (int i = 0; i < Application.REQUEST_LIMIT; i++) {
            Producer producer = new Producer(myQueue);
            executorService.submit(producer);
        }
        executorService.shutdown();
//        executorService.awaitTermination(2, TimeUnit.SECONDS);
    }
}
