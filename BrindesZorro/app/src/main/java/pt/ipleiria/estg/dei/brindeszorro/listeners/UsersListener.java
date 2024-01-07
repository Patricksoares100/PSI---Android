package pt.ipleiria.estg.dei.brindeszorro.listeners;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.brindeszorro.modelo.User;


public interface UsersListener {
    void onRefreshListaUsers(ArrayList<User> users);
}
