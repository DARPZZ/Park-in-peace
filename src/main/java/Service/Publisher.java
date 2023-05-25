package Service;

import com.example.park.SceneName;

import java.util.function.Consumer;

public interface Publisher<T>
{
    /**
     * Publishes an object of type T to a specific topic
     * @param sceneName The scene name to publish to
     * @param object The object of type T to be published
     */
    void publish(SceneName sceneName, T object);

    /**
     * Subscribes to a specific topic
     * @param sceneName The name of scene to subscribe to
     * @param consumer The consumer to be subscribed to
     */
    void subscribe(SceneName sceneName, Consumer<T> consumer);

    /**
     * Unsubscribes to a specific topic
     * @param sceneName The scene name to unsubscribe from
     * @param consumer The consumer to unsubscribe to
     */
    void unsubscribe(SceneName sceneName, Consumer<T> consumer);

    /**
     * Removes all subscribers of a specific scene
     * @param sceneName The name of the scene to unsubscribe from
     */
    void unsubscribeAll(SceneName sceneName);
}