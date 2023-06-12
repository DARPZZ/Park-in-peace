package Service;

import Model.DaoObject.User;

public interface UserSubscriber
{
    void onUserReceived(User user);
}
