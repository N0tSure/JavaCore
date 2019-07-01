package com.artemsirosh.tij.concurrency;

import java.util.List;

/**
 * Created at 29-06-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
class MessageProviderTask<T> implements Runnable {

    private final List<T> messages;
    private final MessageStack<T> exchange;

    MessageProviderTask(List<T> messages, MessageStack<T> exchange) {
        this.messages = messages;
        this.exchange = exchange;
    }

    @Override
    public void run() {
        messages.stream()
                .peek(m -> System.out.println(this + ": send message='" + m + "'"))
                .forEach(exchange::push);

        System.out.println(this + ": stopping.");
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
