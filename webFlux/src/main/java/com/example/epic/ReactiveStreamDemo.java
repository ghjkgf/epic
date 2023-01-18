package com.example.epic;

import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;

/**
 * @author zsl
 * @date 2023/1/17
 * @apiNote
 */
public class ReactiveStreamDemo {
    public static void main(String[] args) {
        SubmissionPublisher publisher = new SubmissionPublisher();
        Flow.Subscriber subscriber = new Flow.Subscriber(){
            Flow.Subscription subscription;
            @Override
            public void onSubscribe(Flow.Subscription subscription) {
                System.out.println("建立订阅关系");
                this.subscription = subscription;
                subscription.request(1);

            }

            @Override
            public void onNext(Object item) {
                System.out.println("接收数据:"+ item);
                subscription.request(0);
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onComplete() {
                System.out.println("接收完毕");
            }
        };

        publisher.subscribe(subscriber);

        publisher.submit("hello reactive stream");
        publisher.close();
        // 主线程做一个阻塞
        try {
            Thread.currentThread().join(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }
}
