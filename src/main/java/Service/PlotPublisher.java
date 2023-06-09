package Service;

import Model.DaoObject.Plot;
import com.example.park.SceneName;
import javafx.util.Pair;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class PlotPublisher implements Publisher<Plot>
{
    private static PlotPublisher plotPublisher;
    private final List<Pair<SceneName, Consumer<Plot>>> listSubscriber = new ArrayList<>();

    private PlotPublisher(){};

    @Override
    public void publish(SceneName sceneName, Plot composite)
    {
        for (Pair<SceneName, Consumer<Plot>> data : listSubscriber)
        {
            if (data.getKey() == sceneName)
            {
                data.getValue().accept(composite);
            }
        }
    }

    @Override
    public void subscribe(SceneName sceneName, Consumer<Plot> consumer)
    {
        listSubscriber.add(new Pair<>(sceneName, consumer));
    }

    @Override
    public void unsubscribe(SceneName sceneName, Consumer<Plot> consumer)
    {
        listSubscriber.removeIf(subscriber -> subscriber.getKey() == sceneName && subscriber.getValue() == consumer);
    }

    @Override
    public void unsubscribeAll(SceneName sceneName)
    {
        listSubscriber.removeIf(subscriber -> subscriber.getKey() == sceneName);
    }

    public static PlotPublisher getInstance()
    {
        if (plotPublisher == null)
        {
            plotPublisher = new PlotPublisher();
        }
        return plotPublisher;
    }
}
