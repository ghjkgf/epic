首先，值得说明的是，我们构建WebFlux环境启动时，应用服务器默认是Netty的：



![](https://pic3.zhimg.com/v2-1d2dc90e5851d8ca9aa69bf8d7023326_b.jpg)

![](https://pic3.zhimg.com/80/v2-1d2dc90e5851d8ca9aa69bf8d7023326_720w.webp)



我们分别来访问一下SpringMVC的接口和WebFlux的接口，看一下有什么区别：

SpringMVC：



![](https://pic2.zhimg.com/v2-d02612e7913230e9cb04d247850017b5_b.png)

![](https://pic2.zhimg.com/80/v2-d02612e7913230e9cb04d247850017b5_720w.webp)



WebFlux：



![](https://pic1.zhimg.com/v2-b47c1a953c60d3420112607967a45634_b.png)

![](https://pic1.zhimg.com/80/v2-b47c1a953c60d3420112607967a45634_720w.webp)



从调用者(浏览器)的角度而言，是感知不到有什么变化的，因为都是得等待5s才返回数据。但是，从服务端的日志我们可以看出，WebFlux是**直接返回Mono对象的**(而不是像SpringMVC一直同步阻塞5s，线程才返回)。

这正是WebFlux的好处：能够以**固定的线程来处理高并发**（充分发挥机器的性能）。

WebFlux还支持**服务器推送**(SSE - >Server Send Event)，我们来看个例子：



效果就是**每秒**会给浏览器推送数据