package com.maplebridge.openshift.ignite;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.IgniteCluster;
import org.apache.ignite.Ignition;
import org.apache.ignite.cluster.ClusterNode;

import javax.cache.Cache;

public class CacheTest
{
    public static void main(String[] args) throws Exception
    {
        System.out.println(System.getProperty("user.dir"));
        if (System.currentTimeMillis() % 2 == 0)
        {
            Ignition.setClientMode(true);
        }

        try (Ignite ignite = Ignition.start("chen-example-ignite.xml")) {
            IgniteCache<Integer, String> cache = ignite.getOrCreateCache("myCacheName");

            // Store keys in cache (values will end up on different cache nodes).
            for (int i = 0; i < 20; i++)
                cache.put(i, Integer.toString(i));

            ClusterNode node = ignite.cluster().forLocal().node();
            while(true)
            {

                System.out.println("Is client node = " + node.isClient() + "; Local node id = " + node.id());
                for (Cache.Entry<Integer, String> e : cache.localEntries())
                {
                    System.out.println("Got Local entries: [key=" + e.getKey() + ", vallll=" + e.getValue() + ']');
                }
//                for (int i = 0; i < 10; i++)
//                    System.out.println("Got [key=" + i + ", vallll=" + cache.get(i) + ']');
                Thread.currentThread().sleep(10000);
            }
        }
    }
}
