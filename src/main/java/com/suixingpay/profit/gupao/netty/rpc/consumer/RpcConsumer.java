package com.suixingpay.profit.gupao.netty.rpc.consumer;

import com.suixingpay.profit.gupao.netty.rpc.api.IRpcHelloService;
import com.suixingpay.profit.gupao.netty.rpc.api.IRpcService;
import com.suixingpay.profit.gupao.netty.rpc.consumer.proxy.RpcProxy;

public class RpcConsumer {
	
    public static void main(String [] args){  
        IRpcHelloService rpcHello = RpcProxy.create(IRpcHelloService.class);
        
        System.out.println(rpcHello.hello("Tom老师"));

        IRpcService service = RpcProxy.create(IRpcService.class);
        
        System.out.println("8 + 2 = " + service.add(8, 2));
        System.out.println("8 - 2 = " + service.sub(8, 2));
        System.out.println("8 * 2 = " + service.mult(8, 2));
        System.out.println("8 / 2 = " + service.div(8, 2));
    }
    
}
