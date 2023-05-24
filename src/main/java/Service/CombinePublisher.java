package Service;

import Model.DaoObject.Combine;
import javafx.util.Pair;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class CombinePublisher implements Publisher<Combine>
{
    private static CombinePublisher combinePublisher;
    private final List<Pair<Topic, Consumer<Combine>>> listSubscriber = new ArrayList<>();

    private CombinePublisher(){};

    @Override
    public void publish(Topic topic, Combine composite)
    {
        for (Pair<Topic, Consumer<Combine>> data : listSubscriber)
        {
            if (data.getKey() == topic)
            {
                data.getValue().accept(composite);
            }
        }
    }

    @Override
    public void subscribe(Topic topic, Consumer<Combine> consumer)
    {
        listSubscriber.add(new Pair<>(topic, consumer));
    }

    @Override
    public void unsubscribe(Topic topic, Consumer<Combine> consumer)
    {
        listSubscriber.removeIf(subscriber -> subscriber.getKey() == topic && subscriber.getValue() == consumer);
    }

    @Override
    public void unsubscribeAll(Topic topic)
    {
        listSubscriber.removeIf(subscriber -> subscriber.getKey() == topic);
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
