package com.suixingpay.profit.gupao.netty.rpc.provider;

import com.suixingpay.profit.gupao.netty.rpc.api.IRpcHelloService;

public class RpcHelloServiceImpl implements IRpcHelloService {

    public String hello(String name) {  
        return "Hello " + name + "!";  
    }  
  
}  
