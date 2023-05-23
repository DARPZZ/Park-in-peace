package com.example.park;

import Model.DaoObject.User;

public interface UserPublisher
{
    void subscribe(UserSubscriber subscriber);
    void unsubscribe(UserSubscriber subscriber);
    void notifySubscribers(User user);
}
