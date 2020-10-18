package com.tank.tcp.server;

import com.tank.tcp.codec.ObjectEncoder;
import com.tank.tcp.handler.client.ConnectedHandler;
import com.tank.tcp.protocol.Customer;
import com.tank.tcp.protocol.Packet;
import com.tank.tcp.util.Codec;
import com.tank.tcp.util.Command;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.val;

/**
 * @author tank198435163.com
 */
public class SimpleClient {

  public static void main(String[] args) throws InterruptedException {

    val customer = new Customer();
    customer.setUsername("Jack");
    customer.setPassword("123456");
    Packet packet = Codec.toPacket(Command.LOGIN, Codec.toObjectBytes(customer));

    val bootstrap = new Bootstrap();
    val group = new NioEventLoopGroup();
    val connectFuture = bootstrap.group(group)
            .channel(NioSocketChannel.class)
            .handler(new ChannelInitializer<Channel>() {
              @Override
              protected void initChannel(Channel channel) throws Exception {
//                channel.pipeline().addLast(new StringEncoder());
//                channel.pipeline().addLast(new ConnectedHandler());
                channel.pipeline().addLast(new ObjectEncoder());
                channel.pipeline().addLast(new ConnectedHandler());
              }
            })
            .connect("localhost", 9000)
            .sync()
            .addListener(future -> {
              if (future.isSuccess()) {
                System.out.println("connect server success");
              } else {
                System.out.println("connect server failure");
              }
            });


    while (true) {
      connectFuture.channel().writeAndFlush(packet);
    }


  }
}
