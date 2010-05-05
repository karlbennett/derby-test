package org.youthnet.hibernate.embedded.derby;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.youthnet.hibernate.embedded.derby.util.ArgumentHandler;

import java.util.Arrays;
import java.util.List;

/**
 * Derby Test
 */
public class DerbyOneJar {

    private static AbstractApplicationContext context;

    private static ArgumentHandler argumentHandler;

    public static void main(String[] args) {
        context = new ClassPathXmlApplicationContext("applicationContextOneJar.xml");

        List<String> arguments = Arrays.asList(args);

        argumentHandler = context.getBean("argumentHandler", ArgumentHandler.class);

        argumentHandler.handleArguments(args);
    }
}