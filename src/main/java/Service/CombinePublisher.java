package Service;

import Model.DaoObject.Combine;
import com.example.park.SceneName;
import javafx.util.Pair;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class CombinePublisher implements Publisher<Combine>
{
    private static CombinePublisher combinePublisher;
    private final List<Pair<SceneName, Consumer<Combine>>> listSubscriber = new ArrayList<>();

    private CombinePublisher(){};

    @Override
    public void publish(SceneName sceneName, Combine composite)
    {
        for (Pair<SceneName, Consumer<Combine>> data : listSubscriber)
        {
            if (data.getKey() == sceneName)
            {
                data.getValue().accept(composite);
            }
        }
    }

    @Override
    public void subscribe(SceneName sceneName, Consumer<Combine> consumer)
    {
        listSubscriber.add(new Pair<>(sceneName, consumer));
    }

    @Override
    public void unsubscribe(SceneName sceneName, Consumer<Combine> consumer)
    {
        listSubscriber.removeIf(subscriber -> subscriber.getKey() == sceneName && subscriber.getValue() == consumer);
    }

    @Override
    public void unsubscribeAll(SceneName sceneName)
    {
        listSubscriber.removeIf(subscriber -> subscriber.getKey() == sceneName);
    }

    public static CombinePublisher getInstance()
    {
        if (combinePublisher == null)
        {
            combinePublisher = new CombinePublisher();
        }
        return combinePublisher;
    }
}