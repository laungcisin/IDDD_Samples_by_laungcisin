//   Copyright 2012,2013 Vaughn Vernon
//
//   Licensed under the Apache License, Version 2.0 (the "License");
//   you may not use this file except in compliance with the License.
//   You may obtain a copy of the License at
//
//       http://www.apache.org/licenses/LICENSE-2.0
//
//   Unless required by applicable law or agreed to in writing, software
//   distributed under the License is distributed on an "AS IS" BASIS,
//   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//   See the License for the specific language governing permissions and
//   limitations under the License.

package com.saasovation.common.domain.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DomainEventPublisher {

    private static final ThreadLocal<DomainEventPublisher> instance = new ThreadLocal<DomainEventPublisher>() {
        protected DomainEventPublisher initialValue() {
            return new DomainEventPublisher();
        }
    };

    private boolean publishing;

    @SuppressWarnings("rawtypes")
    private List subscribers;

    public static DomainEventPublisher instance() {
        return instance.get();
    }

    /**
     * 发布领域事件。
     * @param aDomainEvent
     * @param <T>
     */
    public <T> void publish(final T aDomainEvent) {
        if (!this.isPublishing() && this.hasSubscribers()) {

            try {
                this.setPublishing(true);

                Class<?> eventType = aDomainEvent.getClass();

                @SuppressWarnings("unchecked")
                List<DomainEventSubscriber<T>> allSubscribers = this.subscribers();

                /*
                依次遍历所有注册的订阅方。
                 */
                for (DomainEventSubscriber<T> subscriber : allSubscribers) {

                    Class<?> subscribedToType = subscriber.subscribedToEventType();

                    /*
                    调用每个订阅方的subscribedToEventType()方法来判断该订阅方是否可以
                    处理一个特定类型的事件。如果订阅方返回的是DomainEvent.class，则表明
                    该订阅方可以处理任何类型的领域事件。
                     */
                    if (eventType == subscribedToType || subscribedToType == DomainEvent.class) {
                        /*
                        所有有资格处理事件的订阅方都将使用handleEvent()方法来处理事件。
                         */
                        subscriber.handleEvent(aDomainEvent);
                    }
                }

            } finally {
                /*
                在过滤或通知完所有的订阅方之后，发布过程执行完毕。
                 */
                this.setPublishing(false);
            }
        }
    }

    public void publishAll(Collection<DomainEvent> aDomainEvents) {
        for (DomainEvent domainEvent : aDomainEvents) {
            this.publish(domainEvent);
        }
    }

    public void reset() {
        if (!this.isPublishing()) {
            this.setSubscribers(null);
        }
    }

    /**
     *
     * @param aSubscriber
     * @param <T>
     */
    @SuppressWarnings("unchecked")
    public <T> void subscribe(DomainEventSubscriber<T> aSubscriber) {
        /*
        只有当发送方没有进行发送操作时，才能注册订阅方。
        这样可以避免线程同步问题。
         */
        if (!this.isPublishing()) {
            this.ensureSubscribersList();

            this.subscribers().add(aSubscriber);
        }
    }

    private DomainEventPublisher() {
        super();

        this.setPublishing(false);
        this.ensureSubscribersList();
    }

    @SuppressWarnings("rawtypes")
    private void ensureSubscribersList() {
        if (!this.hasSubscribers()) {
            this.setSubscribers(new ArrayList());
        }
    }

    private boolean isPublishing() {
        return this.publishing;
    }

    private void setPublishing(boolean aFlag) {
        this.publishing = aFlag;
    }

    private boolean hasSubscribers() {
        return this.subscribers() != null;
    }

    @SuppressWarnings("rawtypes")
    private List subscribers() {
        return this.subscribers;
    }

    @SuppressWarnings("rawtypes")
    private void setSubscribers(List aSubscriberList) {
        this.subscribers = aSubscriberList;
    }
}
