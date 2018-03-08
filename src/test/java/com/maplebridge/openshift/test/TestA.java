package com.maplebridge.openshift.test;

import org.junit.Test;

import com.maplebridge.openshift.ignite.WebSvc;

public class TestA {

    @Test
    public void testM()
    {
        WebSvc svc = new WebSvc();
        System.out.println(svc.sayPlainTextHello());
    }
}
