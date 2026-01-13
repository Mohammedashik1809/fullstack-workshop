package io_concurrency;

import java.io.*;

public class DatabaseConfig implements Externalizable {

    private String host;
    private int port;
    private String username;
    private String password;

    public DatabaseConfig() {}

    public DatabaseConfig(String host, int port, String user, String pwd) {
        this.host = host;
        this.port = port;
        this.username = user;
        this.password = pwd;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeUTF(host);
        out.writeInt(port);
        out.writeUTF(username);
        out.writeUTF(new StringBuilder(password).reverse().toString());
    }

    @Override
    public void readExternal(ObjectInput in)
            throws IOException {
        host = in.readUTF();
        port = in.readInt();
        username = in.readUTF();
        password = new StringBuilder(in.readUTF()).reverse().toString();
    }
}
