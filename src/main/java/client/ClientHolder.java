package client;

public class ClientHolder {

    public static Client client;

    public static Client getClient() {
        return client;
    }

    public static void setClient(Client client) {
        ClientHolder.client = client;
    }
}
