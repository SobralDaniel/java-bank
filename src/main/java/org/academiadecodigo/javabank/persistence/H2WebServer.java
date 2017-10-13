package org.academiadecodigo.javabank.persistence;

import org.academiadecodigo.javabank.Config;
import org.h2.tools.Server;

import java.sql.SQLException;

public class H2WebServer {
    private Server server;

    public H2WebServer() throws SQLException{
        server = Server.createWebServer("-web", "-webAllowOthers", "-webDaemon", "-webPort", Config.H2_PORT);
    }

    public void start() throws SQLException{
        if(server != null){
            server.start();
        }
    }

    public void stop(){
        if(server != null){
            server.stop();
        }
    }
}
