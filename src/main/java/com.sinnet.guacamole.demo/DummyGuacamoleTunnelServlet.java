package com.sinnet.guacamole.demo;

import org.apache.guacamole.GuacamoleException;
import org.apache.guacamole.net.GuacamoleSocket;
import org.apache.guacamole.net.GuacamoleTunnel;
import org.apache.guacamole.net.InetGuacamoleSocket;
import org.apache.guacamole.net.SimpleGuacamoleTunnel;
import org.apache.guacamole.protocol.ConfiguredGuacamoleSocket;
import org.apache.guacamole.protocol.GuacamoleConfiguration;
import org.apache.guacamole.servlet.GuacamoleHTTPTunnelServlet;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Simple tunnel example with hard-coded configuration parameters.
 */
@Configuration
public class DummyGuacamoleTunnelServlet extends GuacamoleHTTPTunnelServlet {

    //@Value("${host.name}")
    private static String hostName="localhost";
    //@Value("${host.port}")
    private static int hostPort=4822;


    private static String HOST_NAME="hostname";
    private static String PROTOCOL ="protocol";
    private static String PORT="port";
    private static String USER_NAME = "username";
    private static String PASSWORD = "password";




    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        super.doGet(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        super.doPost(request, response);
    }

    @Override
    protected GuacamoleTunnel doConnect(HttpServletRequest request) throws GuacamoleException {

        // guacd connection information
        /*String hostname = "localhost";
        int port = 4822;*/

        String host_ip  = request.getParameter(HOST_NAME);
        String protocol  = request.getParameter(PROTOCOL);
        String port  = request.getParameter(PORT);
        String username  =  request.getParameter(USER_NAME);
        String password  = request.getParameter(PASSWORD);

        // VNC connection information
        GuacamoleConfiguration config = new GuacamoleConfiguration();
        config.setProtocol(protocol);
        config.setParameter(HOST_NAME, host_ip);
        config.setParameter(PORT, port);
        config.setParameter(USER_NAME, username);
        if (!"".equals(password)
                &&!"null".equals(password)
                &&null!=password){
            config.setParameter(PASSWORD,password);
        }

        // Connect to guacd, proxying a connection to the VNC server above
        GuacamoleSocket socket = new ConfiguredGuacamoleSocket(
                new InetGuacamoleSocket(hostName, hostPort),
                config
        );

        // Create tunnel from now-configured socket
        GuacamoleTunnel tunnel = new SimpleGuacamoleTunnel(socket);
        return tunnel;

    }
}
