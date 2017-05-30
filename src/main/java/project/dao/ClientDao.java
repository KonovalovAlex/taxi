package project.dao;

import project.entity.Client;

public interface ClientDao extends Dao<Client> {
    int insertClient(Client client);
}
