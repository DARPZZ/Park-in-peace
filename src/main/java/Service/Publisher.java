package Service;

import java.util.function.Consumer;

public interface Publisher<T>
{
    /**
     * Publishes an object of type T to a specific topic
     * @param topic The topic to publish to
     * @param object The object of type T to be published
     */
    void publish(Topic topic, T object);

    /**
     * Subscribes to a specific topic
     * @param topic The topic to subscribe to
     * @param consumer The consumer to be subscribed to
     */
    void subscribe(Topic topic, Consumer<T> consumer);
    void unsubscribe(Topic topic, Consumer<T> consumer);
    void unsubscribeAll(Topic topic);
}
