package org.youthnet.hibernat.embedded.derby;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class Derby
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );

        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext(
                new String[] {"applicationContext.xml"});
    }
}
