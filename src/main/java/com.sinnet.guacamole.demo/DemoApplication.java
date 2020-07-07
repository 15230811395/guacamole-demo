package com.sinnet.guacamole.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * You need only the courage to follow your heart.
 *
 * @author Li
 * @date 2020/6/17 18:24
 */
@SpringBootApplication
@Controller
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    private DummyGuacamoleTunnelServlet tunnelServlet = new DummyGuacamoleTunnelServlet();

    @RequestMapping(
        value = "tunnel",
        method = {
            RequestMethod.GET,
            RequestMethod.POST
        }
    )
    public void handleTunnel(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        tunnelServlet.doPost(request, response);
    }
}
