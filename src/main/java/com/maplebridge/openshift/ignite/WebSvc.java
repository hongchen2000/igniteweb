package com.maplebridge.openshift.ignite;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;
import org.apache.ignite.cluster.ClusterNode;

import javax.cache.Cache;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class WebSvc
{
        Ignite ignite;
        IgniteCache<Integer, String> cache;

        public WebSvc()
        {
                System.out.println("mcm: In WebSvc Contructor method");
                System.out.println(System.getProperty("user.dir"));
                if (System.currentTimeMillis() % 2 == 0)
                {
                        //Ignition.setClientMode(true);
                }

                ignite = Ignition.start("chen-example-ignite.xml");
                cache = ignite.getOrCreateCache("myCacheName");

                for (int i = 0; i < 20; i++)
                        cache.put(i, Integer.toString(i));
        }

        // This method is called if TEXT_PLAIN is request
        @GET
        @Produces(MediaType.TEXT_PLAIN)
        public String sayPlainTextHello()
        {
                StringBuffer sb = new StringBuffer();
                ClusterNode node = ignite.cluster().forLocal().node();
                System.out.println("Is client node = " + node.isClient() + "; Local node id = " + node.id());
                for (Cache.Entry<Integer, String> e : cache.localEntries())
                {
                        sb.append("key=" + e.getKey()).append(":val=" + e.getValue()).append("\n");
                        System.out.println("Got Local entries: [key=" + e.getKey() + ", vallll=" + e.getValue() + ']');
                }

                return sb.toString();
        }

}
