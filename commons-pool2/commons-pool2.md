[源码javadoc地址 ](http://commons.apache.org/proper/commons-pool/api-2.2/index.html)  
[参考博客1 ](https://blog.csdn.net/chaofanwei2/article/details/51291401)  
[参考博客2 ](https://blog.csdn.net/qq838642798/article/details/54603450)  

在高并发的环境下写程序时通常碰到线程安全的问题，当然，最能想到的就是加锁，再进一步想就是池子了，所谓池子就是，里面可以放置多个同样的对象，每个线程需要用时，就从池中取走，用完时再放回到池中，即可解决线程的安全问题，又可提高速度（预先初始化）