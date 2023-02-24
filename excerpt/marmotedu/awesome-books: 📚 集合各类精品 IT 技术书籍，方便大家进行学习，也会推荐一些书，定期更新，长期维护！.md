> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 [github.com](https://github.com/marmotedu/awesome-books#go)

> 📚 集合各类精品 IT 技术书籍，方便大家进行学习，也会推荐一些书，定期更新，长期维护！. Contribute to marmotedu/awesome-books development by c......

[](#it经典资料分享)IT 经典资料分享
======================

[](#分享背景)分享背景
-------------

看了一些书，学了一些技术。在技术资料选择上，有一点强迫症，喜欢搜：**最好、最清** 等字样，所以平日里整理了不少精品书籍，这里做了些分类分享出来。分享分为 3 类，后期会慢慢更新：

1.  **经典书籍推荐：** 会按类别推荐经典的书籍
2.  **珍藏书籍推荐：** 在经典书籍中按分类推荐 1 ~ 5 本非常经典的书
3.  **按标签推荐：** 比如学习微服务需要看哪些书、学习虚拟化要看哪些书、学习容器需要看哪些书

*   github 会定期更新、长期维护，也可以提 pull request，把一些精品的书籍分享出来；
*   水平有限，里面有很多书没有看过，如有出入还请见谅，贵在分享；
*   这些精品书都是有版权的，不是开源书籍，所以只能放豆瓣链接了（抱歉），需要 PDF 的可以网上自己搜了

[](#table-of-contents)Table of Contents
=======================================

*   [IT 经典资料分享](#it%E7%BB%8F%E5%85%B8%E8%B5%84%E6%96%99%E5%88%86%E4%BA%AB)
    *   [分享背景](#%E5%88%86%E4%BA%AB%E8%83%8C%E6%99%AF)
    *   [珍藏书籍推荐](#%E7%8F%8D%E8%97%8F%E4%B9%A6%E7%B1%8D%E6%8E%A8%E8%8D%90)
    *   [语言类](#%E8%AF%AD%E8%A8%80%E7%B1%BB)
        *   [C 语言](#c%E8%AF%AD%E8%A8%80)
            *   [C 基础资料](#c%E5%9F%BA%E7%A1%80%E8%B5%84%E6%96%99)
            *   [C 进阶资料](#c%E8%BF%9B%E9%98%B6%E8%B5%84%E6%96%99)
            *   [C 网络编程](#c%E7%BD%91%E7%BB%9C%E7%BC%96%E7%A8%8B)
        *   [C](#c)
        *   [Java](#java)
        *   [Go](#go)
        *   [Python](#python)
        *   [JavaScript](#javascript)
        *   [NodeJS](#nodejs)
        *   [PHP](#php)
        *   [Perl](#perl)
        *   [Ruby](#ruby)
        *   [Scala](#scala)
        *   [Lua](#lua)
        *   [Shell](#shell)
        *   [汇编语言](#%E6%B1%87%E7%BC%96%E8%AF%AD%E8%A8%80)
        *   [Lisp](#lisp)
        *   [正则表达式](#%E6%AD%A3%E5%88%99%E8%A1%A8%E8%BE%BE%E5%BC%8F)
        *   [AWK](#awk)
        *   [SED](#sed)
        *   [HTML & CSS](#html--css)
    *   [计算机网络](#%E8%AE%A1%E7%AE%97%E6%9C%BA%E7%BD%91%E7%BB%9C)
        *   [网络基础](#%E7%BD%91%E7%BB%9C%E5%9F%BA%E7%A1%80)
        *   [TCP/IP](#tcpip)
        *   [HTTP](#http)
    *   [存储](#%E5%AD%98%E5%82%A8)
        *   [存储基础](#%E5%AD%98%E5%82%A8%E5%9F%BA%E7%A1%80)
        *   [Ceph](#ceph)
    *   [操作系统](#%E6%93%8D%E4%BD%9C%E7%B3%BB%E7%BB%9F)
        *   [操作系统基础](#%E6%93%8D%E4%BD%9C%E7%B3%BB%E7%BB%9F%E5%9F%BA%E7%A1%80)
        *   [Linux](#linux)
        *   [嵌入式](#%E5%B5%8C%E5%85%A5%E5%BC%8F)
        *   [Linux 内核](#linux%E5%86%85%E6%A0%B8)
    *   [防火墙](#%E9%98%B2%E7%81%AB%E5%A2%99)
    *   [云计算](#%E4%BA%91%E8%AE%A1%E7%AE%97)
        *   [系统虚拟化](#%E7%B3%BB%E7%BB%9F%E8%99%9A%E6%8B%9F%E5%8C%96)
        *   [Docker](#docker)
        *   [Kubernetes](#kubernetes)
        *   [Serverless](#serverless)
        *   [云计算基础](#%E4%BA%91%E8%AE%A1%E7%AE%97%E5%9F%BA%E7%A1%80)
        *   [微服务基础](#%E5%BE%AE%E6%9C%8D%E5%8A%A1%E5%9F%BA%E7%A1%80)
        *   [负载均衡](#%E8%B4%9F%E8%BD%BD%E5%9D%87%E8%A1%A1)
        *   [服务网格](#%E6%9C%8D%E5%8A%A1%E7%BD%91%E6%A0%BC)
    *   [消息队列](#%E6%B6%88%E6%81%AF%E9%98%9F%E5%88%97)
        *   [kafka](#kafka)
        *   [RabbitMQ](#rabbitmq)
    *   [数据库](#%E6%95%B0%E6%8D%AE%E5%BA%93)
        *   [SQL](#sql)
        *   [MySQL](#mysql)
        *   [Oracle](#oracle)
        *   [Redis](#redis)
        *   [Mongo](#mongo)
    *   [大数据](#%E5%A4%A7%E6%95%B0%E6%8D%AE)
        *   [Hadoop](#hadoop)
        *   [Spark](#spark)
        *   [Flink](#flink)
        *   [Hbase](#hbase)
    *   [人工智能](#%E4%BA%BA%E5%B7%A5%E6%99%BA%E8%83%BD)
        *   [人工智能基础](#%E4%BA%BA%E5%B7%A5%E6%99%BA%E8%83%BD%E5%9F%BA%E7%A1%80)
        *   [TensorFlow](#tensorflow)
    *   [分布式](#%E5%88%86%E5%B8%83%E5%BC%8F)
        *   [分布式基础](#%E5%88%86%E5%B8%83%E5%BC%8F%E5%9F%BA%E7%A1%80)
        *   [Etcd](#etcd)
        *   [Zookpeer](#zookpeer)
    *   [数据结构](#%E6%95%B0%E6%8D%AE%E7%BB%93%E6%9E%84)
    *   [算法](#%E7%AE%97%E6%B3%95)
    *   [运维](#%E8%BF%90%E7%BB%B4)
        *   [运维基础](#%E8%BF%90%E7%BB%B4%E5%9F%BA%E7%A1%80)
        *   [日志](#%E6%97%A5%E5%BF%97)
        *   [监控告警](#%E7%9B%91%E6%8E%A7%E5%91%8A%E8%AD%A6)
    *   [DevOps](#devops)
    *   [区块链](#%E5%8C%BA%E5%9D%97%E9%93%BE)
    *   [性能](#%E6%80%A7%E8%83%BD)
    *   [测试](#%E6%B5%8B%E8%AF%95)
        *   [测试基础](#%E6%B5%8B%E8%AF%95%E5%9F%BA%E7%A1%80)
        *   [性能测试](#%E6%80%A7%E8%83%BD%E6%B5%8B%E8%AF%95)
    *   [工具类](#%E5%B7%A5%E5%85%B7%E7%B1%BB)
    *   [面试](#%E9%9D%A2%E8%AF%95)
    *   [其它资料](#%E5%85%B6%E5%AE%83%E8%B5%84%E6%96%99)
    *   [按标签推荐系列](#%E6%8C%89%E6%A0%87%E7%AD%BE%E6%8E%A8%E8%8D%90%E7%B3%BB%E5%88%97)
        *   [微服务系列推荐](#%E5%BE%AE%E6%9C%8D%E5%8A%A1%E7%B3%BB%E5%88%97%E6%8E%A8%E8%8D%90)
        *   [云原生系列推荐](#%E4%BA%91%E5%8E%9F%E7%94%9F%E7%B3%BB%E5%88%97%E6%8E%A8%E8%8D%90)

[](#云原生技术栈阅读材料推荐)云原生技术栈阅读材料推荐
-----------------------------

<table><thead><tr><th>类别</th><th>书名</th></tr></thead><tbody><tr><td>微服务</td><td>[微服务设计](<a href="https://book.douban.com/subject/26772677" rel="nofollow">https://book.douban.com/subject/26772677</a></td></tr><tr><td>Docker</td><td><a href="https://book.douban.com/subject/30329430/" rel="nofollow">《Docker 技术入门与实战》（第 3 版）</a>、<a href="https://book.douban.com/subject/26894736/" rel="nofollow">《Docker 容器与容器云》（第 2 版）</a></td></tr><tr><td>Kubernetes</td><td><a href="https://book.douban.com/subject/33444476/" rel="nofollow">Kubernetes 权威指南：从 Docker 到 Kubernetes 实践全接触（第 4 版）</a></td></tr><tr><td>Serverless</td><td><a href="https://knative.dev/docs/" rel="nofollow">Knative Documentation</a></td></tr><tr><td>KVM</td><td><a href="https://book.douban.com/subject/25743939/" rel="nofollow">KVM 虚拟化技术 : 实战与原理解析</a></td></tr><tr><td>监控告警</td><td><a href="https://prometheus.io/docs/introduction/overview/" rel="nofollow">Prometheus Documentation</a></td></tr><tr><td>调用链</td><td><a href="https://www.jaegertracing.io/docs/1.26/" rel="nofollow">Jaeger Documentation</a></td></tr><tr><td>存储</td><td><a href="https://time.geekbang.org/column/intro/391?tab=catalog" rel="nofollow">Etcd</a></td></tr><tr><td>网关</td><td><a href="https://tyk.io/docs/apim/open-source/" rel="nofollow">Tyk Open Source</a></td></tr><tr><td>消息队列</td><td><a href="https://book.douban.com/subject/30221096/" rel="nofollow">Apache Kafka 实战</a></td></tr><tr><td>ServiceMesh</td><td><a href="https://book.douban.com/subject/34438220/" rel="nofollow">云原生服务网格 Istio：原理、实践、架构与源码解析</a></td></tr><tr><td>服务发现</td><td><a href="https://www.consul.io/docs" rel="nofollow">Consul Documentation</a></td></tr><tr><td>网络</td><td><a href="https://docs.cilium.io/" rel="nofollow">Cilium Documentation</a></td></tr></tbody></table>

[](#珍藏书籍推荐)珍藏书籍推荐
-----------------

**每种分类推荐 1 ~ 5 本需要珍藏书籍：**

> 因为个人爱好、水平优先，推荐的书可能也不一样，不服预期，大佬勿喷，可以留言，我会修正 :-)

<table><thead><tr><th>类别</th><th>书籍</th><th>豆瓣读书链接</th></tr></thead><tbody><tr><td>C</td><td>C 程序设计 (第五版)<br>C 程序设计语言 (第 2 版)<br>GNU/LINUX 环境编程 (第 2 版)<br>UNIX 环境高级编程 (第 3 版)<br>C 和指针<br>C 专家编程<br>Linux 网络编程（第 2 版）<br>UNIX 网络编程卷 1：套接字联网 API（第 3 版）<br>LNIX 网络编程卷 2：进程间通信（第 2 版）</td><td><a href="https://book.douban.com/subject/30385709/" rel="nofollow">https://book.douban.com/subject/30385709/</a><br><a href="https://book.douban.com/subject/1139336/" rel="nofollow">https://book.douban.com/subject/1139336/</a><br><a href="https://book.douban.com/subject/4725273/" rel="nofollow">https://book.douban.com/subject/4725273/</a><br><a href="https://book.douban.com/subject/25900403/" rel="nofollow">https://book.douban.com/subject/25900403/</a><br><a href="https://book.douban.com/subject/3012360/" rel="nofollow">https://book.douban.com/subject/3012360/</a><br><a href="https://book.douban.com/subject/2377310/" rel="nofollow">https://book.douban.com/subject/2377310/</a><br><a href="https://book.douban.com/subject/26669330/" rel="nofollow">https://book.douban.com/subject/26669330/</a><br><a href="https://book.douban.com/subject/4859464/" rel="nofollow">https://book.douban.com/subject/4859464/</a><br><a href="https://book.douban.com/subject/4886882/" rel="nofollow">https://book.douban.com/subject/4886882/</a></td></tr><tr><td>C++</td><td>C++ 程序设计教程（第 2 版）<br>C++ 程序设计语言（第 1-4 部分）<br>C++ Primer Plus（第 6 版）<br>C++ 编程思想（两卷合订本）</td><td><a href="https://book.douban.com/subject/1444656/" rel="nofollow">https://book.douban.com/subject/1444656/</a><br><a href="https://book.douban.com/subject/26857943/" rel="nofollow">https://book.douban.com/subject/26857943/</a><br><a href="https://book.douban.com/subject/10789789/" rel="nofollow">https://book.douban.com/subject/10789789/</a><br><a href="https://book.douban.com/subject/6558198/" rel="nofollow">https://book.douban.com/subject/6558198/</a></td></tr><tr><td>Java</td><td>Java 编程思想（第 4 版）</td><td><a href="https://book.douban.com/subject/2130190/" rel="nofollow">https://book.douban.com/subject/2130190/</a></td></tr><tr><td>Go</td><td>Go 程序设计语言<br>Go 语言编程</td><td><a href="https://book.douban.com/subject/27044219/" rel="nofollow">https://book.douban.com/subject/27044219/</a><br><a href="https://book.douban.com/subject/11577300/" rel="nofollow">https://book.douban.com/subject/11577300/</a></td></tr><tr><td>Python</td><td>Python 基础教程（第 3 版）<br>Python 核心编程（第 3 版）</td><td><a href="https://book.douban.com/subject/27667375/" rel="nofollow">https://book.douban.com/subject/27667375/</a><br><a href="https://book.douban.com/subject/26801374/" rel="nofollow">https://book.douban.com/subject/26801374/</a></td></tr><tr><td>JavaScript</td><td>JavaScript 高级程序设计（第 3 版）<br>JavaScript 权威指南（第 6 版）</td><td><a href="https://book.douban.com/subject/10549733/" rel="nofollow">https://book.douban.com/subject/10549733/</a><br><a href="https://book.douban.com/subject/10546125/" rel="nofollow">https://book.douban.com/subject/10546125/</a></td></tr><tr><td>NodeJS</td><td>深入浅出 Node.js<br>Node.js 高级编程</td><td><a href="https://book.douban.com/subject/25768396/" rel="nofollow">https://book.douban.com/subject/25768396/</a><br><a href="https://book.douban.com/subject/25799431/" rel="nofollow">https://book.douban.com/subject/25799431/</a></td></tr><tr><td>PHP</td><td>PHP 与 MySQL 程序设计（第 4 版）</td><td><a href="https://book.douban.com/subject/6516132/" rel="nofollow">https://book.douban.com/subject/6516132/</a></td></tr><tr><td>Perl</td><td>Perl 语言入门（第 5 版）</td><td><a href="https://book.douban.com/subject/4088038/" rel="nofollow">https://book.douban.com/subject/4088038/</a></td></tr><tr><td>Ruby</td><td>Ruby 元编程（第 2 版）</td><td><a href="https://book.douban.com/subject/26575429/" rel="nofollow">https://book.douban.com/subject/26575429/</a></td></tr><tr><td>Scala</td><td>Scala 编程（第 3 版）<br>Scala 程序设计（第 2 版）</td><td><a href="https://book.douban.com/subject/27591387/" rel="nofollow">https://book.douban.com/subject/27591387/</a><br><a href="https://book.douban.com/subject/26740545/" rel="nofollow">https://book.douban.com/subject/26740545/</a></td></tr><tr><td>Lua</td><td>Lua 程序设计（第 2 版）</td><td><a href="https://book.douban.com/subject/3076942/" rel="nofollow">https://book.douban.com/subject/3076942/</a></td></tr><tr><td>Shell</td><td>实战 Linux Shell 编程与服务器管理<br>Shell 脚本专家指南</td><td><a href="https://book.douban.com/subject/4722707/" rel="nofollow">https://book.douban.com/subject/4722707/</a><br><a href="https://book.douban.com/subject/4935288/" rel="nofollow">https://book.douban.com/subject/4935288/</a></td></tr><tr><td>汇编语言</td><td>汇编语言（第 3 版）</td><td><a href="https://item.jd.com/12259774.html?dist=jd" rel="nofollow">https://item.jd.com/12259774.html?dist=jd</a></td></tr><tr><td>Lisp</td><td>实用 Common Lisp 编程</td><td><a href="https://book.douban.com/subject/6859720/" rel="nofollow">https://book.douban.com/subject/6859720/</a></td></tr><tr><td>正则表达式</td><td>正则指引（第 2 版）<br>精通正则表达式（第 3 版）</td><td><a href="https://book.douban.com/subject/30352656/" rel="nofollow">https://book.douban.com/subject/30352656/</a><br><a href="https://book.douban.com/subject/2154713/" rel="nofollow">https://book.douban.com/subject/2154713/</a></td></tr><tr><td>AWK</td><td>sed 与 awk（第 3 版）</td><td><a href="https://book.douban.com/subject/1236944/" rel="nofollow">https://book.douban.com/subject/1236944/</a></td></tr><tr><td>Sed</td><td>sed 与 awk（第 3 版）</td><td><a href="https://book.douban.com/subject/1236944/" rel="nofollow">https://book.douban.com/subject/1236944/</a></td></tr><tr><td>HTML &amp; CSS</td><td>CSS 权威指南（中文第 3 版）<br>HTML5 权威指南</td><td><a href="https://book.douban.com/subject/2308234/" rel="nofollow">https://book.douban.com/subject/2308234/</a><br><a href="https://book.douban.com/subject/25786074/" rel="nofollow">https://book.douban.com/subject/25786074/</a></td></tr><tr><td>计算机网络</td><td>计算机网络（第 7 版）</td><td><a href="https://book.douban.com/subject/26960678/" rel="nofollow">https://book.douban.com/subject/26960678/</a></td></tr><tr><td>TCP/IP</td><td>图解 TCP/IP（第 5 版）<br>TCP/IP 详解 卷 1：协议（第 2 版）</td><td><a href="https://book.douban.com/subject/24737674/" rel="nofollow">https://book.douban.com/subject/24737674/</a><br><a href="https://book.douban.com/subject/26825411/" rel="nofollow">https://book.douban.com/subject/26825411/</a></td></tr><tr><td>HTTP</td><td>图解 HTTP<br>HTTP 权威指南</td><td><a href="https://book.douban.com/subject/25863515/" rel="nofollow">https://book.douban.com/subject/25863515/</a><br><a href="https://book.douban.com/subject/10746113/" rel="nofollow">https://book.douban.com/subject/10746113/</a></td></tr><tr><td>操作系统基础</td><td>计算机操作系统（第 3 版）<br>计算机系统概论（第 2 版）</td><td><a href="https://book.douban.com/subject/1058576/" rel="nofollow">https://book.douban.com/subject/1058576/</a><br><a href="https://book.douban.com/subject/2185076/" rel="nofollow">https://book.douban.com/subject/2185076/</a></td></tr><tr><td>Linux</td><td>鸟哥的 Linux 私房菜: 基础学习篇 (第 4 版)<br>循序渐进 Linux（第 2 版） : 基础知识 服务器搭建 系统管理 性能调优 虚拟化与集群应用</td><td><a href="https://book.douban.com/subject/30359954/" rel="nofollow">https://book.douban.com/subject/30359954/</a><br><a href="https://book.douban.com/subject/26758194/" rel="nofollow">https://book.douban.com/subject/26758194/</a></td></tr><tr><td>嵌入式</td><td>嵌入式 Linux 设备驱动开发详解</td><td><a href="https://book.douban.com/subject/2985887/" rel="nofollow">https://book.douban.com/subject/2985887/</a></td></tr><tr><td>Linux 内核</td><td>Linux 内核设计与实现（原书第 3 版）<br>LINUX 内核源代码情景分析（全册）<br>深入理解 LINUX 内核（第 3 版）<br>LINUX 设备驱动程序（第 3 版）<br>Linux 内核完全注释（修订版）<br>深入 Linux 内核架构<br></td><td><a href="https://book.douban.com/subject/6097773/" rel="nofollow">https://book.douban.com/subject/6097773/</a><br><a href="https://book.douban.com/subject/1231584/" rel="nofollow">https://book.douban.com/subject/1231584/</a><br><a href="https://book.douban.com/subject/2287506/" rel="nofollow">https://book.douban.com/subject/2287506/</a><br><a href="https://book.douban.com/subject/1420480/" rel="nofollow">https://book.douban.com/subject/1420480/</a><br><a href="https://book.douban.com/subject/1231236/" rel="nofollow">https://book.douban.com/subject/1231236/</a><br><a href="https://book.douban.com/subject/4843567/" rel="nofollow">https://book.douban.com/subject/4843567/</a></td></tr><tr><td>内核存储</td><td>Linux 内核模块编程<br>Linux 内核探秘：深入解析文件系统和设备驱动的架构与设计<br>存储技术原理分析：基于 Linux_2.6 内核源代码<br>LINUX 设备驱动程序 (第 3 版)</td><td>这本没有书籍，但是编写内核模块的经典 Hello World 入门书籍<br><a href="https://book.douban.com/subject/25817503/" rel="nofollow">https://book.douban.com/subject/25817503/</a><br><a href="https://book.douban.com/subject/6822367/" rel="nofollow">https://book.douban.com/subject/6822367/</a><br><a href="https://book.douban.com/subject/1420480/" rel="nofollow">https://book.douban.com/subject/1420480/</a></td></tr><tr><td>防火墙</td><td>Linux 防火墙 -(原书第 3 版)</td><td><a href="https://book.douban.com/subject/1838749/" rel="nofollow">https://book.douban.com/subject/1838749/</a></td></tr><tr><td>虚拟化原理</td><td>系统虚拟化 : 原理与实现</td><td><a href="https://book.douban.com/subject/3619896/" rel="nofollow">https://book.douban.com/subject/3619896/</a></td></tr><tr><td>KVM</td><td>KVM 虚拟化技术 : 实战与原理解析</td><td><a href="https://book.douban.com/subject/25743939/" rel="nofollow">https://book.douban.com/subject/25743939/</a></td></tr><tr><td>Docker</td><td>Docker 技术入门与实战（第 3 版）<br>Docker 容器与容器云（第 2 版）</td><td><a href="https://book.douban.com/subject/30329430/" rel="nofollow">https://book.douban.com/subject/30329430/</a><br><a href="https://book.douban.com/subject/26894736/" rel="nofollow">https://book.douban.com/subject/26894736/</a></td></tr><tr><td>Kubernetes</td><td>Kubernetes 权威指南：从 Docker 到 Kubernetes 实践全接触（第 4 版）<br>基于 Kubernetes 的容器云平台实战</td><td><a href="https://book.douban.com/subject/33444476/" rel="nofollow">https://book.douban.com/subject/33444476/</a><br><a href="https://book.douban.com/subject/30333237/" rel="nofollow">https://book.douban.com/subject/30333237/</a></td></tr><tr><td>Serverless</td><td>Serverless 架构：从原理、设计到项目实战<br>Serverless 架构：无服务器应用与 AWS Lambda</td><td>我们团队刘宇大佬写的一本书，没有盗版 PDF<br><a href="https://book.douban.com/subject/30290516/" rel="nofollow">https://book.douban.com/subject/30290516/</a></td></tr><tr><td>微服务</td><td>微服务架构与实践（第 1 版）<br>微服务设计</td><td><a href="https://book.douban.com/subject/26693152/" rel="nofollow">https://book.douban.com/subject/26693152/</a><br><a href="https://book.douban.com/subject/26772677/" rel="nofollow">https://book.douban.com/subject/26772677/</a></td></tr><tr><td>ServiceMesh</td><td>云原生服务网格 Istio：原理、实践、架构与源码解析</td><td><a href="https://book.douban.com/subject/34438220/" rel="nofollow">https://book.douban.com/subject/34438220/</a></td></tr><tr><td>kafka</td><td>Apache Kafka 实战<br>Apache Kafka 源码剖析<br>Kafka 权威指南</td><td><a href="https://book.douban.com/subject/30221096/" rel="nofollow">https://book.douban.com/subject/30221096/</a><br><a href="https://book.douban.com/subject/27038473/" rel="nofollow">https://book.douban.com/subject/27038473/</a><br><a href="https://book.douban.com/subject/27665114/" rel="nofollow">https://book.douban.com/subject/27665114/</a></td></tr><tr><td>RabbitMQ</td><td>RabbitMQ 实战 : 高效部署分布式消息队列</td><td><a href="https://book.douban.com/subject/26649178/" rel="nofollow">https://book.douban.com/subject/26649178/</a></td></tr><tr><td>SQL</td><td>精通 SQL 结构化查询语言详解</td><td><a href="https://book.douban.com/subject/2022427/" rel="nofollow">https://book.douban.com/subject/2022427/</a></td></tr><tr><td>MySQL</td><td>MySQL 技术内幕 InnoDB 存储引擎（第 1 版）<br>MySQL 必知必会</td><td><a href="https://book.douban.com/subject/5373022/" rel="nofollow">https://book.douban.com/subject/5373022/</a><br><a href="https://book.douban.com/subject/3354490/" rel="nofollow">https://book.douban.com/subject/3354490/</a></td></tr><tr><td>Oracle</td><td>Oracle 10g 数据库管理应用与开发标准教程</td><td><a href="https://book.douban.com/subject/2316500/" rel="nofollow">https://book.douban.com/subject/2316500/</a></td></tr><tr><td>Redis</td><td>Redis 设计与实现</td><td><a href="https://book.douban.com/subject/25900156/" rel="nofollow">https://book.douban.com/subject/25900156/</a></td></tr><tr><td>Mongo</td><td>MongoDB 大数据处理权威指南（第 2 版）</td><td><a href="https://book.douban.com/subject/26269829/" rel="nofollow">https://book.douban.com/subject/26269829/</a></td></tr></tbody></table>

[](#语言类)语言类
-----------

### [](#c语言)C 语言

#### [](#c基础资料)C 基础资料

<table><thead><tr><th>书名</th><th>豆瓣读书链接</th></tr></thead><tbody><tr><td><a href="https://book.douban.com/subject/30385709/" rel="nofollow">C 程序设计 (第五版)</a><br><a href="https://book.douban.com/subject/4864940/" rel="nofollow">C 程序设计 (第四版)</a></td><td><a href="https://book.douban.com/subject/30385709/" rel="nofollow">https://book.douban.com/subject/30385709/</a><br><a href="https://book.douban.com/subject/4864940/" rel="nofollow">https://book.douban.com/subject/4864940/</a></td></tr><tr><td><a href="https://book.douban.com/subject/1236999/" rel="nofollow">The C Programming language</a></td><td><a href="https://book.douban.com/subject/1236999/" rel="nofollow">https://book.douban.com/subject/1236999/</a></td></tr><tr><td><a href="https://book.douban.com/subject/1139336/" rel="nofollow">C 程序设计语言 (第 2 版)</a></td><td><a href="https://book.douban.com/subject/1139336/" rel="nofollow">https://book.douban.com/subject/1139336/</a></td></tr><tr><td><a href="https://book.douban.com/subject/6025290/" rel="nofollow">一站式学习 C 编程</a></td><td><a href="https://book.douban.com/subject/6025290/" rel="nofollow">https://book.douban.com/subject/6025290/</a></td></tr><tr><td><a href="https://book.douban.com/subject/26792521/" rel="nofollow">C Primer Plus（第 6 版）</a></td><td><a href="https://book.douban.com/subject/26792521/" rel="nofollow">https://book.douban.com/subject/26792521/</a></td></tr></tbody></table>

#### [](#c进阶资料)C 进阶资料

<table><thead><tr><th>书名</th><th>豆瓣读书链接</th></tr></thead><tbody><tr><td><a href="https://book.douban.com/subject/25900403/" rel="nofollow">UNIX 环境高级编程 (第 3 版)</a></td><td><a href="https://book.douban.com/subject/25900403/" rel="nofollow">https://book.douban.com/subject/25900403/</a></td></tr><tr><td><a href="https://book.douban.com/subject/3012360/" rel="nofollow">C 和指针</a></td><td><a href="https://book.douban.com/subject/3012360/" rel="nofollow">https://book.douban.com/subject/3012360/</a></td></tr><tr><td><a href="https://book.douban.com/subject/1102097/" rel="nofollow">C 陷阱与缺陷</a></td><td><a href="https://book.douban.com/subject/1102097/" rel="nofollow">https://book.douban.com/subject/1102097/</a></td></tr><tr><td><a href="https://book.douban.com/subject/2377310/" rel="nofollow">C 专家编程</a></td><td><a href="https://book.douban.com/subject/2377310/" rel="nofollow">https://book.douban.com/subject/2377310/</a></td></tr><tr><td><a href="https://book.douban.com/subject/4725273/" rel="nofollow">GNU/LINUX 环境编程 (第 2 版)</a></td><td><a href="https://book.douban.com/subject/4725273/" rel="nofollow">https://book.douban.com/subject/4725273/</a></td></tr><tr><td><a href="https://book.douban.com/subject/1231823/" rel="nofollow">GNU/Linux 编程指南 (第 2 版)</a></td><td><a href="https://book.douban.com/subject/1231823/" rel="nofollow">https://book.douban.com/subject/1231823/</a></td></tr><tr><td><a href="https://book.douban.com/subject/25809330/" rel="nofollow">Linux/UNIX 系统编程手册（上册）</a></td><td><a href="https://book.douban.com/subject/25809330/" rel="nofollow">https://book.douban.com/subject/25809330/</a></td></tr><tr><td><a href="https://book.douban.com/subject/25809330/" rel="nofollow">Linux/UNIX 系统编程手册（下册）</a></td><td><a href="https://book.douban.com/subject/25809330/" rel="nofollow">https://book.douban.com/subject/25809330/</a></td></tr><tr><td><a href="https://book.douban.com/subject/4831448/" rel="nofollow">Linux 程序设计（第 4 版）</a></td><td><a href="https://book.douban.com/subject/4831448/" rel="nofollow">https://book.douban.com/subject/4831448/</a></td></tr><tr><td><a href="https://book.douban.com/subject/25906154/" rel="nofollow">Linux 系统编程（第 2 版）</a></td><td><a href="https://book.douban.com/subject/25906154/" rel="nofollow">https://book.douban.com/subject/25906154/</a></td></tr></tbody></table>

#### [](#c网络编程)C 网络编程

<table><thead><tr><th>书名</th><th>豆瓣读书链接</th></tr></thead><tbody><tr><td><a href="https://book.douban.com/subject/26669330/" rel="nofollow">Linux 网络编程（第 2 版）</a></td><td><a href="https://book.douban.com/subject/26669330/" rel="nofollow">https://book.douban.com/subject/26669330/</a></td></tr><tr><td><a href="https://book.douban.com/subject/4859464/" rel="nofollow">UNIX 网络编程卷 1：套接字联网 API（第 3 版）</a></td><td><a href="https://book.douban.com/subject/4859464/" rel="nofollow">https://book.douban.com/subject/4859464/</a></td></tr><tr><td><a href="https://book.douban.com/subject/4886882/" rel="nofollow">UNIX 网络编程卷 2：进程间通信（第 2 版）</a></td><td><a href="https://book.douban.com/subject/4886882/" rel="nofollow">https://book.douban.com/subject/4886882/</a></td></tr></tbody></table>

### [](#c)C++

<table><thead><tr><th>书名</th><th>豆瓣读书链接</th></tr></thead><tbody><tr><td><a href="https://book.douban.com/subject/10789789/" rel="nofollow">C++ Primer Plus（第 6 版）</a></td><td><a href="https://book.douban.com/subject/10789789/" rel="nofollow">https://book.douban.com/subject/10789789/</a></td></tr><tr><td><a href="https://book.douban.com/subject/1842426/" rel="nofollow">Effective C++（中文第 3 版）</a></td><td><a href="https://book.douban.com/subject/1842426/" rel="nofollow">https://book.douban.com/subject/1842426/</a></td></tr><tr><td><a href="https://book.douban.com/subject/1174290/" rel="nofollow">C++ 程序设计（第 2 版）</a></td><td><a href="https://book.douban.com/subject/1174290/" rel="nofollow">https://book.douban.com/subject/1174290/</a></td></tr><tr><td><a href="https://book.douban.com/subject/1444656/" rel="nofollow">C++ 程序设计教程（第 2 版）</a></td><td><a href="https://book.douban.com/subject/1444656/" rel="nofollow">https://book.douban.com/subject/1444656/</a></td></tr><tr><td><a href="https://book.douban.com/subject/6558198/" rel="nofollow">C++ 编程思想（两卷合订本）</a></td><td><a href="https://book.douban.com/subject/6558198/" rel="nofollow">https://book.douban.com/subject/6558198/</a></td></tr><tr><td><a href="https://book.douban.com/subject/26378198/" rel="nofollow">C++ 高级编程 (中文第 3 版)</a></td><td><a href="https://book.douban.com/subject/26378198/" rel="nofollow">https://book.douban.com/subject/26378198/</a></td></tr><tr><td><a href="https://book.douban.com/subject/30189176/" rel="nofollow">C++ 高级编程 (英文第 4 版)</a></td><td><a href="https://book.douban.com/subject/30189176/" rel="nofollow">https://book.douban.com/subject/30189176/</a></td></tr><tr><td><a href="https://book.douban.com/subject/27666339/" rel="nofollow">C++ 性能优化指南</a></td><td><a href="https://book.douban.com/subject/27666339/" rel="nofollow">https://book.douban.com/subject/27666339/</a></td></tr><tr><td><a href="https://book.douban.com/subject/2970056/" rel="nofollow">C++ 沉思录（第 2 版）</a></td><td><a href="https://book.douban.com/subject/2970056/" rel="nofollow">https://book.douban.com/subject/2970056/</a></td></tr><tr><td><a href="https://book.douban.com/subject/26857943/" rel="nofollow">C++ 程序设计语言（第 1-4 部分）</a></td><td><a href="https://book.douban.com/subject/26857943/" rel="nofollow">https://book.douban.com/subject/26857943/</a></td></tr></tbody></table>

### [](#java)Java

<table><thead><tr><th>书名</th><th>豆瓣读书链接</th></tr></thead><tbody><tr><td><a href="https://book.douban.com/subject/2130190/" rel="nofollow">Java 编程思想（第 4 版）</a></td><td><a href="https://book.douban.com/subject/2130190/" rel="nofollow">https://book.douban.com/subject/2130190/</a></td></tr><tr><td><a href="https://book.douban.com/subject/24722612/" rel="nofollow">深入理解 Java 虚拟机（第 2 版）：JVM 高级特性与最佳实践</a></td><td><a href="https://book.douban.com/subject/24722612/" rel="nofollow">https://book.douban.com/subject/24722612/</a></td></tr><tr><td><a href="https://book.douban.com/subject/26900033/" rel="nofollow">Java 从入门到精通（第 4 版）</a></td><td><a href="https://book.douban.com/subject/26900033/" rel="nofollow">https://book.douban.com/subject/26900033/</a></td></tr><tr><td><a href="https://book.douban.com/subject/10484692/" rel="nofollow">Java 并发编程实战</a></td><td><a href="https://book.douban.com/subject/10484692/" rel="nofollow">https://book.douban.com/subject/10484692/</a></td></tr><tr><td><a href="https://book.douban.com/subject/26591326/" rel="nofollow">Java 并发编程的艺术</a></td><td><a href="https://book.douban.com/subject/26591326/" rel="nofollow">https://book.douban.com/subject/26591326/</a></td></tr><tr><td><a href="https://book.douban.com/subject/30323325/" rel="nofollow">深入浅出 Spring Boot 2.x</a></td><td><a href="https://book.douban.com/subject/30323325/" rel="nofollow">https://book.douban.com/subject/30323325/</a></td></tr><tr><td><a href="https://book.douban.com/subject/26857423/" rel="nofollow">Spring Boot 实战</a></td><td><a href="https://book.douban.com/subject/26857423/" rel="nofollow">https://book.douban.com/subject/26857423/</a></td></tr><tr><td><a href="https://book.douban.com/subject/27594722/" rel="nofollow">Java 函数式编程</a></td><td><a href="https://book.douban.com/subject/27594722/" rel="nofollow">https://book.douban.com/subject/27594722/</a></td></tr><tr><td><a href="https://book.douban.com/subject/26346017/" rel="nofollow">Java 8 函数式编程</a></td><td><a href="https://book.douban.com/subject/26346017/" rel="nofollow">https://book.douban.com/subject/26346017/</a></td></tr><tr><td><a href="https://book.douban.com/subject/26581686/" rel="nofollow">Java Web 高级编程 : 涵盖 WebSockets、Spring Framework、JPA Hibernate 和 Spring Security</a></td><td><a href="https://book.douban.com/subject/26581686/" rel="nofollow">https://book.douban.com/subject/26581686/</a></td></tr><tr><td><a href="https://book.douban.com/subject/26740520/" rel="nofollow">Java 性能权威指南</a></td><td><a href="https://book.douban.com/subject/26740520/" rel="nofollow">https://book.douban.com/subject/26740520/</a></td></tr><tr><td><a href="https://book.douban.com/subject/30358019/" rel="nofollow">实战 Java 高并发程序设计（第 2 版）</a></td><td><a href="https://book.douban.com/subject/30358019/" rel="nofollow">https://book.douban.com/subject/30358019/</a></td></tr><tr><td><a href="https://book.douban.com/subject/1433583/" rel="nofollow">Java Nio</a></td><td><a href="https://book.douban.com/subject/1433583/" rel="nofollow">https://book.douban.com/subject/1433583/</a></td></tr></tbody></table>

### [](#go)Go

<table><thead><tr><th>书名</th><th>豆瓣读书链接</th></tr></thead><tbody><tr><td><a href="https://book.douban.com/subject/27044219/" rel="nofollow">Go 程序设计语言</a></td><td><a href="https://book.douban.com/subject/27044219/" rel="nofollow">https://book.douban.com/subject/27044219/</a></td></tr><tr><td><a href="https://book.douban.com/subject/11577300/" rel="nofollow">Go 语言编程</a></td><td><a href="https://book.douban.com/subject/11577300/" rel="nofollow">https://book.douban.com/subject/11577300/</a></td></tr><tr><td><a href="https://book.douban.com/subject/27016236/" rel="nofollow">Go 并发编程实战（第 2 版）</a></td><td><a href="https://book.douban.com/subject/27016236/" rel="nofollow">https://book.douban.com/subject/27016236/</a></td></tr><tr><td><a href="https://book.douban.com/subject/27015617/" rel="nofollow">Go 语言实战</a></td><td><a href="https://book.douban.com/subject/27015617/" rel="nofollow">https://book.douban.com/subject/27015617/</a></td></tr><tr><td><a href="https://book.douban.com/subject/24869910/" rel="nofollow">Go 语言程序设计</a></td><td><a href="https://book.douban.com/subject/24869910/" rel="nofollow">https://book.douban.com/subject/24869910/</a></td></tr><tr><td><a href="/marmotedu/awesome-books/blob/master">Go 语言学习笔记</a></td><td><a href="https://book.douban.com/subject/26832468/" rel="nofollow">https://book.douban.com/subject/26832468/</a></td></tr><tr><td><a href="https://book.douban.com/subject/30325764/" rel="nofollow">Go 语言编程入门与实战技巧</a></td><td><a href="https://book.douban.com/subject/30325764/" rel="nofollow">https://book.douban.com/subject/30325764/</a></td></tr><tr><td><a href="https://book.douban.com/subject/26859123/" rel="nofollow">Go 语言圣经（中文版）</a></td><td><a href="https://book.douban.com/subject/26859123/" rel="nofollow">https://book.douban.com/subject/26859123/</a></td></tr><tr><td><a href="https://book.douban.com/subject/30351288/" rel="nofollow">Go 语言核心编程</a></td><td><a href="https://book.douban.com/subject/30351288/" rel="nofollow">https://book.douban.com/subject/30351288/</a></td></tr><tr><td><a href="https://book.douban.com/subject/27204133/" rel="nofollow">Go Web 编程</a></td><td><a href="https://book.douban.com/subject/27204133/" rel="nofollow">https://book.douban.com/subject/27204133/</a></td></tr></tbody></table>

### [](#python)Python

<table><thead><tr><th>书名</th><th>豆瓣读书链接</th></tr></thead><tbody><tr><td><a href="https://book.douban.com/subject/27667375/" rel="nofollow">Python 基础教程（第 3 版）</a></td><td><a href="https://book.douban.com/subject/27667375/" rel="nofollow">https://book.douban.com/subject/27667375/</a></td></tr><tr><td><a href="https://book.douban.com/subject/26801374/" rel="nofollow">Python 核心编程（第 3 版）</a></td><td><a href="https://book.douban.com/subject/26801374/" rel="nofollow">https://book.douban.com/subject/26801374/</a></td></tr><tr><td><a href="https://book.douban.com/subject/27133480/" rel="nofollow">Python 高级编程（第 2 版）</a></td><td><a href="https://book.douban.com/subject/27133480/" rel="nofollow">https://book.douban.com/subject/27133480/</a></td></tr><tr><td><a href="https://book.douban.com/subject/6049132/" rel="nofollow">Python 学习手册（中文第 4 版）</a></td><td><a href="https://book.douban.com/subject/6049132/" rel="nofollow">https://book.douban.com/subject/6049132/</a></td></tr><tr><td><a href="https://book.douban.com/subject/22139956/" rel="nofollow">Python 学习手册（英文第 5 版）</a></td><td><a href="https://book.douban.com/subject/22139956/" rel="nofollow">https://book.douban.com/subject/22139956/</a></td></tr><tr><td><a href="https://book.douban.com/subject/26314833/" rel="nofollow">Python 编程（第 4 版，上下册）</a></td><td><a href="https://book.douban.com/subject/26314833/" rel="nofollow">https://book.douban.com/subject/26314833/</a></td></tr><tr><td><a href="https://book.douban.com/subject/4828875/" rel="nofollow">Python Cookbook（中文第 2 版）</a></td><td><a href="https://book.douban.com/subject/4828875/" rel="nofollow">https://book.douban.com/subject/4828875/</a></td></tr><tr><td><a href="https://book.douban.com/subject/27064848/" rel="nofollow">Python 高性能编程</a></td><td><a href="https://book.douban.com/subject/27064848/" rel="nofollow">https://book.douban.com/subject/27064848/</a></td></tr><tr><td><a href="https://book.douban.com/subject/5336893/" rel="nofollow">Python 自然语言处理（中文版）</a></td><td><a href="https://book.douban.com/subject/5336893/" rel="nofollow">https://book.douban.com/subject/5336893/</a></td></tr><tr><td><a href="https://book.douban.com/subject/10760444/" rel="nofollow">Python For Data Analysis（英文）</a></td><td><a href="https://book.douban.com/subject/10760444/" rel="nofollow">https://book.douban.com/subject/10760444/</a></td></tr></tbody></table>

### [](#javascript)JavaScript

<table><thead><tr><th>书名</th><th>豆瓣读书链接</th></tr></thead><tbody><tr><td><a href="https://book.douban.com/subject/11506062/" rel="nofollow">JavaScript 模式</a></td><td><a href="https://book.douban.com/subject/11506062/" rel="nofollow">https://book.douban.com/subject/11506062/</a></td></tr><tr><td><a href="https://book.douban.com/subject/26382780/" rel="nofollow">JavaScript 设计模式与开发实践</a></td><td><a href="https://book.douban.com/subject/26382780/" rel="nofollow">https://book.douban.com/subject/26382780/</a></td></tr><tr><td><a href="https://book.douban.com/subject/10549733/" rel="nofollow">JavaScript 权威指南（第 6 版）</a></td><td><a href="https://book.douban.com/subject/10549733/" rel="nofollow">https://book.douban.com/subject/10549733/</a></td></tr><tr><td><a href="https://book.douban.com/subject/10546125/" rel="nofollow">JavaScript 高级程序设计（第 3 版）</a></td><td><a href="https://book.douban.com/subject/10546125/" rel="nofollow">https://book.douban.com/subject/10546125/</a></td></tr><tr><td><a href="https://book.douban.com/subject/3329540/" rel="nofollow">JavaScript 设计模式</a></td><td><a href="https://book.douban.com/subject/3329540/" rel="nofollow">https://book.douban.com/subject/3329540/</a></td></tr><tr><td><a href="https://book.douban.com/subject/4089837/" rel="nofollow">JavaScript 学习指南（第 2 版）</a></td><td><a href="https://book.douban.com/subject/4089837/" rel="nofollow">https://book.douban.com/subject/4089837/</a></td></tr><tr><td><a href="https://book.douban.com/subject/26382780/" rel="nofollow">JavaScript 设计模式与开发实践</a></td><td><a href="https://book.douban.com/subject/26382780/" rel="nofollow">https://book.douban.com/subject/26382780/</a></td></tr><tr><td><a href="https://book.douban.com/subject/6038371/" rel="nofollow">JavaScript DOM 编程艺术（第 2 版）</a></td><td><a href="https://book.douban.com/subject/6038371/" rel="nofollow">https://book.douban.com/subject/6038371/</a></td></tr><tr><td><a href="https://book.douban.com/subject/26638316/" rel="nofollow">JavaScript 忍者秘籍（第 2 版）</a></td><td><a href="https://book.douban.com/subject/26638316/" rel="nofollow">https://book.douban.com/subject/26638316/</a></td></tr><tr><td><a href="https://book.douban.com/subject/3590768/" rel="nofollow">JavaScript 语言精粹</a></td><td><a href="https://book.douban.com/subject/3590768/" rel="nofollow">https://book.douban.com/subject/3590768/</a></td></tr><tr><td><a href="https://book.douban.com/subject/26773411/" rel="nofollow">JavaScript 经典实例（第 2 版）</a></td><td><a href="https://book.douban.com/subject/26773411/" rel="nofollow">https://book.douban.com/subject/26773411/</a></td></tr><tr><td><a href="https://book.douban.com/subject/25767719/" rel="nofollow">JavaScript 编程全解</a></td><td><a href="https://book.douban.com/subject/25767719/" rel="nofollow">https://book.douban.com/subject/25767719/</a></td></tr><tr><td><a href="https://book.douban.com/subject/26707144/" rel="nofollow">JavaScript 编程精解（第 2 版）</a></td><td><a href="https://book.douban.com/subject/26707144/" rel="nofollow">https://book.douban.com/subject/26707144/</a></td></tr><tr><td><a href="https://book.douban.com/subject/25945449/" rel="nofollow">学习 JavaScript 数据结构与算法</a></td><td><a href="https://book.douban.com/subject/25945449/" rel="nofollow">https://book.douban.com/subject/25945449/</a></td></tr><tr><td><a href="https://book.douban.com/subject/26579320/" rel="nofollow">JavaScript 函数式编程</a></td><td><a href="https://book.douban.com/subject/26579320/" rel="nofollow">https://book.douban.com/subject/26579320/</a></td></tr><tr><td><a href="https://book.douban.com/subject/11534729/" rel="nofollow">JavaScript 从入门到精通（标准版）</a></td><td><a href="https://book.douban.com/subject/11534729/" rel="nofollow">https://book.douban.com/subject/11534729/</a></td></tr><tr><td><a href="https://book.douban.com/subject/25945449/" rel="nofollow">数据结构与算法 JavaScript 描述</a></td><td><a href="https://book.douban.com/subject/25945449/" rel="nofollow">https://book.douban.com/subject/25945449/</a></td></tr><tr><td><a href="https://book.douban.com/subject/3120941/" rel="nofollow">JavaScript 开发技术大全</a></td><td><a href="https://book.douban.com/subject/3120941/" rel="nofollow">https://book.douban.com/subject/3120941/</a></td></tr></tbody></table>

### [](#nodejs)NodeJS

<table><thead><tr><th>书名</th><th>豆瓣读书链接</th></tr></thead><tbody><tr><td><a href="https://book.douban.com/subject/25768396/" rel="nofollow">深入浅出 Node.js</a></td><td><a href="https://book.douban.com/subject/25768396/" rel="nofollow">https://book.douban.com/subject/25768396/</a></td></tr><tr><td><a href="https://book.douban.com/subject/25870705/" rel="nofollow">Node.js 实战</a></td><td><a href="https://book.douban.com/subject/25870705/" rel="nofollow">https://book.douban.com/subject/25870705/</a></td></tr><tr><td><a href="https://book.douban.com/subject/30288107/" rel="nofollow">Node.js 实战（第 2 版）</a></td><td><a href="https://book.douban.com/subject/30288107/" rel="nofollow">https://book.douban.com/subject/30288107/</a></td></tr><tr><td><a href="https://book.douban.com/subject/25892704/" rel="nofollow">Node.js 权威指南</a></td><td><a href="https://book.douban.com/subject/25892704/" rel="nofollow">https://book.douban.com/subject/25892704/</a></td></tr><tr><td><a href="https://book.douban.com/subject/10789820/" rel="nofollow">Node.js 开发指南</a></td><td><a href="https://book.douban.com/subject/10789820/" rel="nofollow">https://book.douban.com/subject/10789820/</a></td></tr><tr><td><a href="https://book.douban.com/subject/25799431/" rel="nofollow">Node.js 高级编程</a></td><td><a href="https://book.douban.com/subject/25799431/" rel="nofollow">https://book.douban.com/subject/25799431/</a></td></tr><tr><td><a href="https://book.douban.com/subject/26970060/" rel="nofollow">Node.js 进阶之路</a></td><td><a href="https://book.douban.com/subject/26970060/" rel="nofollow">https://book.douban.com/subject/26970060/</a></td></tr><tr><td><a href="https://book.douban.com/subject/25767596/" rel="nofollow">了不起的 Node.js 将 JavaScript 进行到底</a></td><td><a href="https://book.douban.com/subject/25767596/" rel="nofollow">https://book.douban.com/subject/25767596/</a></td></tr></tbody></table>

### [](#php)PHP

<table><thead><tr><th>书名</th><th>豆瓣读书链接</th></tr></thead><tbody><tr><td><a href="https://book.douban.com/subject/6516132/" rel="nofollow">PHP 与 MySQL 程序设计（第 4 版）</a></td><td><a href="https://book.douban.com/subject/6516132/" rel="nofollow">https://book.douban.com/subject/6516132/</a></td></tr><tr><td><a href="https://book.douban.com/subject/26635862/" rel="nofollow">Modern PHP（中文版）</a></td><td><a href="https://book.douban.com/subject/26635862/" rel="nofollow">https://book.douban.com/subject/26635862/</a></td></tr><tr><td><a href="https://book.douban.com/subject/25982663/" rel="nofollow">Modern PHP（英文版）</a></td><td><a href="https://book.douban.com/subject/25982663/" rel="nofollow">https://book.douban.com/subject/25982663/</a></td></tr><tr><td><a href="https://book.douban.com/subject/30769463/" rel="nofollow">PHP 高性能开发：基础、框架与项目实战</a></td><td><a href="https://book.douban.com/subject/30769463/" rel="nofollow">https://book.douban.com/subject/30769463/</a></td></tr><tr><td><a href="https://book.douban.com/subject/6559267/" rel="nofollow">深入 PHP：面向对象、模式与实践（第 3 版）</a></td><td><a href="https://book.douban.com/subject/6559267/" rel="nofollow">https://book.douban.com/subject/6559267/</a></td></tr></tbody></table>

### [](#perl)Perl

<table><thead><tr><th>书名</th><th>豆瓣读书链接</th></tr></thead><tbody><tr><td><a href="https://book.douban.com/subject/4088038/" rel="nofollow">Perl 语言入门（第 5 版</a></td><td><a href="https://book.douban.com/subject/4088038/" rel="nofollow">https://book.douban.com/subject/4088038/</a></td></tr><tr><td><a href="https://book.douban.com/subject/30549490/" rel="nofollow">Perl 高效编程（第 2 版）</a></td><td><a href="https://book.douban.com/subject/30549490/" rel="nofollow">https://book.douban.com/subject/30549490/</a></td></tr><tr><td><a href="https://book.douban.com/subject/3335419/" rel="nofollow">PERL 实例精解（第 4 版）</a></td><td><a href="https://book.douban.com/subject/3335419/" rel="nofollow">https://book.douban.com/subject/3335419/</a></td></tr></tbody></table>

### [](#ruby)Ruby

<table><thead><tr><th>书名</th><th>豆瓣读书链接</th></tr></thead><tbody><tr><td><a href="https://book.douban.com/subject/26575429/" rel="nofollow">Ruby 元编程（第 2 版）</a></td><td><a href="https://book.douban.com/subject/26575429/" rel="nofollow">https://book.douban.com/subject/26575429/</a></td></tr></tbody></table>

### [](#scala)Scala

<table><thead><tr><th>书名</th><th>豆瓣读书链接</th></tr></thead><tbody><tr><td><a href="https://book.douban.com/subject/5377415/" rel="nofollow">Scala 编程</a></td><td><a href="https://book.douban.com/subject/5377415/" rel="nofollow">https://book.douban.com/subject/5377415/</a></td></tr><tr><td><a href="https://book.douban.com/subject/27591387/" rel="nofollow">Scala 编程（第 3 版）</a></td><td><a href="https://book.douban.com/subject/27591387/" rel="nofollow">https://book.douban.com/subject/27591387/</a></td></tr><tr><td><a href="https://book.douban.com/subject/26740545/" rel="nofollow">Scala 程序设计（第 2 版）</a></td><td><a href="https://book.douban.com/subject/26740545/" rel="nofollow">https://book.douban.com/subject/26740545/</a></td></tr></tbody></table>

### [](#lua)Lua

<table><thead><tr><th>书名</th><th>豆瓣读书链接</th></tr></thead><tbody><tr><td><a href="https://book.douban.com/subject/3076942/" rel="nofollow">Lua 程序设计（第 2 版</a></td><td><a href="https://book.douban.com/subject/3076942/" rel="nofollow">https://book.douban.com/subject/3076942/</a></td></tr></tbody></table>

### [](#shell)Shell

<table><thead><tr><th>书名</th><th>豆瓣读书链接</th></tr></thead><tbody><tr><td><a href="https://book.douban.com/subject/4722707/" rel="nofollow">实战 Linux Shell 编程与服务器管理</a></td><td><a href="https://book.douban.com/subject/4722707/" rel="nofollow">https://book.douban.com/subject/4722707/</a></td></tr><tr><td><a href="https://book.douban.com/subject/4935288/" rel="nofollow">Shell 脚本专家指南</a></td><td><a href="https://book.douban.com/subject/4935288/" rel="nofollow">https://book.douban.com/subject/4935288/</a></td></tr><tr><td><a href="https://book.douban.com/subject/26854226/" rel="nofollow">Linux 命令行与 shell 脚本编程大全 (第 3 版)</a></td><td><a href="https://book.douban.com/subject/26854226/" rel="nofollow">https://book.douban.com/subject/26854226/</a></td></tr><tr><td><a href="https://book.douban.com/subject/26752891/" rel="nofollow">Linux Shell 命令行及脚本编程实例详解</a></td><td><a href="https://book.douban.com/subject/26752891/" rel="nofollow">https://book.douban.com/subject/26752891/</a></td></tr><tr><td><a href="https://book.douban.com/subject/25803528/" rel="nofollow">Linux 系统命令及 Shell 脚本实践指南</a></td><td><a href="https://book.douban.com/subject/25803528/" rel="nofollow">https://book.douban.com/subject/25803528/</a></td></tr><tr><td><a href="https://book.douban.com/subject/25886372/" rel="nofollow">Shell 从入门到精通（第 2 版）</a></td><td><a href="https://book.douban.com/subject/25886372/" rel="nofollow">https://book.douban.com/subject/25886372/</a></td></tr><tr><td><a href="https://book.douban.com/subject/30172987/" rel="nofollow">Linux Shell 脚本攻略（第 3 版）</a></td><td><a href="https://book.douban.com/subject/30172987/" rel="nofollow">https://book.douban.com/subject/30172987/</a></td></tr><tr><td><a href="https://book.douban.com/subject/5988663/" rel="nofollow">Linux Shell 编程从初学到精通</a></td><td><a href="https://book.douban.com/subject/5988663/" rel="nofollow">https://book.douban.com/subject/5988663/</a></td></tr></tbody></table>

### [](#汇编语言)汇编语言

<table><thead><tr><th>书名</th><th>豆瓣读书链接</th></tr></thead><tbody><tr><td><a href="https://item.jd.com/12259774.html?dist=jd" rel="nofollow">汇编语言（第 3 版</a></td><td><a href="https://item.jd.com/12259774.html?dist=jd" rel="nofollow">https://item.jd.com/12259774.html?dist=jd</a></td></tr><tr><td><a href="https://book.douban.com/subject/1446250/" rel="nofollow">Professional Assembly Language（中文）</a></td><td><a href="https://book.douban.com/subject/1446250/" rel="nofollow">https://book.douban.com/subject/1446250/</a></td></tr><tr><td><a href="https://book.douban.com/subject/2039913/" rel="nofollow">Professional Assembly Language（英文）</a></td><td><a href="https://book.douban.com/subject/2039913/" rel="nofollow">https://book.douban.com/subject/2039913/</a></td></tr><tr><td><a href="https://book.douban.com/subject/26769528/" rel="nofollow">汇编语言：基于 x86 处理器（第 7 版）</a></td><td><a href="https://book.douban.com/subject/26769528/" rel="nofollow">https://book.douban.com/subject/26769528/</a></td></tr><tr><td><a href="https://book.douban.com/subject/2250326/" rel="nofollow">Intel 汇编语言程序设计</a></td><td><a href="https://book.douban.com/subject/2250326/" rel="nofollow">https://book.douban.com/subject/2250326/</a></td></tr><tr><td><a href="https://book.douban.com/subject/1783103/" rel="nofollow">Windows 环境下 32 位汇编语言程序设计（第 2 版）</a></td><td><a href="https://book.douban.com/subject/1783103/" rel="nofollow">https://book.douban.com/subject/1783103/</a></td></tr><tr><td><a href="https://book.douban.com/subject/24846626/" rel="nofollow">Windows 环境下 32 位汇编语言程序设计（典藏版）</a></td><td><a href="https://book.douban.com/subject/24846626/" rel="nofollow">https://book.douban.com/subject/24846626/</a></td></tr></tbody></table>

### [](#lisp)Lisp

<table><thead><tr><th>书名</th><th>豆瓣读书链接</th></tr></thead><tbody><tr><td><a href="https://book.douban.com/subject/6859720/" rel="nofollow">实用 Common Lisp 编程</a></td><td><a href="https://book.douban.com/subject/6859720/" rel="nofollow">https://book.douban.com/subject/6859720/</a></td></tr></tbody></table>

### [](#正则表达式)正则表达式

<table><thead><tr><th>书名</th><th>豆瓣读书链接</th></tr></thead><tbody><tr><td><a href="https://book.douban.com/subject/30352656/" rel="nofollow">正则指引（第 2 版）</a></td><td><a href="https://book.douban.com/subject/30352656/" rel="nofollow">https://book.douban.com/subject/30352656/</a></td></tr><tr><td><a href="https://book.douban.com/subject/2154713/" rel="nofollow">精通正则表达式（第 3 版）</a></td><td><a href="https://book.douban.com/subject/2154713/" rel="nofollow">https://book.douban.com/subject/2154713/</a></td></tr><tr><td><a href="https://book.douban.com/subject/2269648/" rel="nofollow">正则表达式必知必会</a></td><td><a href="https://book.douban.com/subject/2269648/" rel="nofollow">https://book.douban.com/subject/2269648/</a></td></tr><tr><td><a href="https://book.douban.com/subject/22601258/" rel="nofollow">学习正则表达式</a></td><td><a href="https://book.douban.com/subject/22601258/" rel="nofollow">https://book.douban.com/subject/22601258/</a></td></tr></tbody></table>

### [](#awk)AWK

<table><thead><tr><th>书名</th><th>豆瓣读书链接</th></tr></thead><tbody><tr><td><a href="https://book.douban.com/subject/1236944/" rel="nofollow">sed 与 awk（第 3 版</a></td><td><a href="https://book.douban.com/subject/1236944/" rel="nofollow">https://book.douban.com/subject/1236944/</a></td></tr><tr><td><a href="https://book.douban.com/subject/26257265/" rel="nofollow">Effective awk Programming（英文）</a></td><td><a href="https://book.douban.com/subject/26257265/" rel="nofollow">https://book.douban.com/subject/26257265/</a></td></tr></tbody></table>

### [](#sed)SED

<table><thead><tr><th>书名</th><th>豆瓣读书链接</th></tr></thead><tbody><tr><td><a href="https://book.douban.com/subject/1236944/" rel="nofollow">sed 与 awk（第 3 版）</a></td><td><a href="https://book.douban.com/subject/1236944/" rel="nofollow">https://book.douban.com/subject/1236944/</a></td></tr></tbody></table>

### [](#html--css)HTML & CSS

<table><thead><tr><th>书名</th><th>豆瓣读书链接</th></tr></thead><tbody><tr><td><a href="https://book.douban.com/subject/2308234/" rel="nofollow">CSS 权威指南（中文第 3 版）</a></td><td><a href="https://book.douban.com/subject/2308234/" rel="nofollow">https://book.douban.com/subject/2308234/</a></td></tr><tr><td><a href="https://book.douban.com/subject/25786074/" rel="nofollow">HTML5 权威指南</a></td><td><a href="https://book.douban.com/subject/25786074/" rel="nofollow">https://book.douban.com/subject/25786074/</a></td></tr><tr><td><a href="https://book.douban.com/subject/24708139/" rel="nofollow">HTML5+CSS3 从入门到精通</a></td><td><a href="https://book.douban.com/subject/24708139/" rel="nofollow">https://book.douban.com/subject/24708139/</a></td></tr><tr><td><a href="https://book.douban.com/subject/25878992/" rel="nofollow">HTML5 与 CSS3 基础教程（第 8 版）</a></td><td><a href="https://book.douban.com/subject/25878992/" rel="nofollow">https://book.douban.com/subject/25878992/</a></td></tr><tr><td><a href="https://book.douban.com/subject/10608238/" rel="nofollow">HTML5 程序设计（第 2 版）</a></td><td><a href="https://book.douban.com/subject/10608238/" rel="nofollow">https://book.douban.com/subject/10608238/</a></td></tr><tr><td><a href="https://book.douban.com/subject/3879846/" rel="nofollow">CSS Web 设计高级教程</a></td><td><a href="https://book.douban.com/subject/3879846/" rel="nofollow">https://book.douban.com/subject/3879846/</a></td></tr></tbody></table>

[](#计算机网络)计算机网络
---------------

### [](#网络基础)网络基础

<table><thead><tr><th>书名</th><th>豆瓣读书链接</th></tr></thead><tbody><tr><td><a href="https://book.douban.com/subject/26960678/" rel="nofollow">计算机网络（第 7 版）</a></td><td><a href="https://book.douban.com/subject/26960678/" rel="nofollow">https://book.douban.com/subject/26960678/</a></td></tr><tr><td><a href="https://book.douban.com/subject/26176870/" rel="nofollow">计算机网络：自顶向下方法 (第 6 版)</a></td><td><a href="https://book.douban.com/subject/26176870/" rel="nofollow">https://book.douban.com/subject/26176870/</a></td></tr><tr><td><a href="https://book.douban.com/subject/26420010/" rel="nofollow">精通 Linux 内核网络</a></td><td><a href="https://book.douban.com/subject/26420010/" rel="nofollow">https://book.douban.com/subject/26420010/</a></td></tr><tr><td><a href="https://book.douban.com/subject/30866578/" rel="nofollow">网络工程师教程（第 5 版）</a></td><td><a href="https://book.douban.com/subject/30866578/" rel="nofollow">https://book.douban.com/subject/30866578/</a></td></tr></tbody></table>

### [](#tcpip)TCP/IP

<table><thead><tr><th>书名</th><th>豆瓣读书链接</th></tr></thead><tbody><tr><td><a href="https://book.douban.com/subject/26825411/" rel="nofollow">TCP/IP 详解 卷 1：协议（第 2 版）</a></td><td><a href="https://book.douban.com/subject/26825411/" rel="nofollow">https://book.douban.com/subject/26825411/</a></td></tr><tr><td><a href="https://book.douban.com/subject/24737674/" rel="nofollow">图解 TCP/IP（第 5 版）</a></td><td><a href="https://book.douban.com/subject/24737674/" rel="nofollow">https://book.douban.com/subject/24737674/</a></td></tr><tr><td><a href="https://book.douban.com/subject/25911735/" rel="nofollow">TCP/IP 网络编程</a></td><td><a href="https://book.douban.com/subject/25911735/" rel="nofollow">https://book.douban.com/subject/25911735/</a></td></tr><tr><td><a href="https://book.douban.com/subject/4124130/" rel="nofollow">TCP/IP Sockets 编程 : C 语言实现（第 2 版）</a></td><td><a href="https://book.douban.com/subject/4124130/" rel="nofollow">https://book.douban.com/subject/4124130/</a></td></tr><tr><td><a href="https://book.douban.com/subject/26220771/" rel="nofollow">TCP/IP 协议族 (第 4 版)</a></td><td><a href="https://book.douban.com/subject/26220771/" rel="nofollow">https://book.douban.com/subject/26220771/</a></td></tr><tr><td><a href="https://book.douban.com/subject/10556677/" rel="nofollow">TCP/IP 入门经典（第 5 版）</a></td><td><a href="https://book.douban.com/subject/10556677/" rel="nofollow">https://book.douban.com/subject/10556677/</a></td></tr></tbody></table>

### [](#http)HTTP

<table><thead><tr><th>书名</th><th>豆瓣读书链接</th></tr></thead><tbody><tr><td><a href="https://book.douban.com/subject/10746113/" rel="nofollow">HTTP 权威指南</a></td><td><a href="https://book.douban.com/subject/10746113/" rel="nofollow">https://book.douban.com/subject/10746113/</a></td></tr><tr><td><a href="https://book.douban.com/subject/25863515/" rel="nofollow">图解 HTTP</a></td><td><a href="https://book.douban.com/subject/25863515/" rel="nofollow">https://book.douban.com/subject/25863515/</a></td></tr><tr><td><a href="https://book.douban.com/subject/25919428/" rel="nofollow">图解网络硬件</a></td><td><a href="https://book.douban.com/subject/25919428/" rel="nofollow">https://book.douban.com/subject/25919428/</a></td></tr><tr><td><a href="https://book.douban.com/subject/27665112/" rel="nofollow">HTTP/2 基础教程</a></td><td><a href="https://book.douban.com/subject/27665112/" rel="nofollow">https://book.douban.com/subject/27665112/</a></td></tr><tr><td><a href="https://book.douban.com/subject/26869219/" rel="nofollow">HTTPS 权威指南：在服务器和 Web 应用上部署 SSL-TLS 和 PKI</a></td><td><a href="https://book.douban.com/subject/26869219/" rel="nofollow">https://book.douban.com/subject/26869219/</a></td></tr></tbody></table>

[](#存储)存储
---------

### [](#存储基础)存储基础

<table><thead><tr><th>书名</th><th>豆瓣读书链接</th></tr></thead><tbody><tr><td><a href="https://book.douban.com/subject/26831614/" rel="nofollow">软件定义存储：原理、实践与生态</a></td><td><a href="https://book.douban.com/subject/26831614/" rel="nofollow">https://book.douban.com/subject/26831614/</a></td></tr><tr><td><a href="https://book.douban.com/subject/25723658/" rel="nofollow">大规模分布式存储系统 : 原理解析与架构实战</a></td><td><a href="https://book.douban.com/subject/25723658/" rel="nofollow">https://book.douban.com/subject/25723658/</a></td></tr></tbody></table>

### [](#ceph)Ceph

<table><thead><tr><th>书名</th><th>豆瓣读书链接</th></tr></thead><tbody><tr><td><a href="https://book.douban.com/subject/27019132/" rel="nofollow">Ceph 分布式存储学习指南</a></td><td><a href="https://book.douban.com/subject/27019132/" rel="nofollow">https://book.douban.com/subject/27019132/</a></td></tr><tr><td><a href="https://book.douban.com/subject/27178824/" rel="nofollow">Ceph 设计原理与实现</a></td><td><a href="https://book.douban.com/subject/27178824/" rel="nofollow">https://book.douban.com/subject/27178824/</a></td></tr><tr><td><a href="https://book.douban.com/subject/26937692/" rel="nofollow">Ceph 分布式存储实战</a></td><td><a href="https://book.douban.com/subject/26937692/" rel="nofollow">https://book.douban.com/subject/26937692/</a></td></tr></tbody></table>

[](#操作系统)操作系统
-------------

### [](#操作系统基础)操作系统基础

<table><thead><tr><th>书名</th><th>豆瓣读书链接</th></tr></thead><tbody><tr><td><a href="https://book.douban.com/subject/2185076/" rel="nofollow">计算机系统概论（第 2 版）</a></td><td><a href="https://book.douban.com/subject/2185076/" rel="nofollow">https://book.douban.com/subject/2185076/</a></td></tr><tr><td><a href="https://book.douban.com/subject/26912767/" rel="nofollow">深入理解计算机系统（原书第 3 版）</a></td><td><a href="https://book.douban.com/subject/26912767/" rel="nofollow">https://book.douban.com/subject/26912767/</a></td></tr><tr><td><a href="https://book.douban.com/subject/4289836/" rel="nofollow">操作系统概念（第 7 版）</a></td><td><a href="https://book.douban.com/subject/4289836/" rel="nofollow">https://book.douban.com/subject/4289836/</a></td></tr><tr><td><a href="https://book.douban.com/subject/1058576/" rel="nofollow">计算机操作系统（第 3 版）</a></td><td><a href="https://book.douban.com/subject/1058576/" rel="nofollow">https://book.douban.com/subject/1058576/</a></td></tr><tr><td><a href="https://book.douban.com/subject/26993995/" rel="nofollow">操作系统 - 精髓与设计原理（第 8 版）</a></td><td><a href="https://book.douban.com/subject/26993995/" rel="nofollow">https://book.douban.com/subject/26993995/</a></td></tr><tr><td><a href="https://book.douban.com/subject/27096665/" rel="nofollow">现代操作系统（第 4 版）</a></td><td><a href="https://book.douban.com/subject/27096665/" rel="nofollow">https://book.douban.com/subject/27096665/</a></td></tr><tr><td><a href="https://book.douban.com/subject/26604008/" rel="nofollow">计算机组成与设计 - 硬件软件接口（第 5 版）</a></td><td><a href="https://book.douban.com/subject/26604008/" rel="nofollow">https://book.douban.com/subject/26604008/</a></td></tr><tr><td><a href="https://book.douban.com/subject/2044818/" rel="nofollow">操作系统设计与实现 (第三版，上册)</a></td><td><a href="https://book.douban.com/subject/2044818/" rel="nofollow">https://book.douban.com/subject/2044818/</a></td></tr></tbody></table>

### [](#linux)Linux

<table><thead><tr><th>书名</th><th>豆瓣读书链接</th></tr></thead><tbody><tr><td><a href="https://book.douban.com/subject/30359954/" rel="nofollow">鸟哥的 Linux 私房菜: 基础学习篇 (第 4 版)</a></td><td><a href="https://book.douban.com/subject/30359954/" rel="nofollow">https://book.douban.com/subject/30359954/</a></td></tr><tr><td><a href="https://book.douban.com/subject/26758194/" rel="nofollow">循序渐进 Linux（第 2 版） : 基础知识 服务器搭建 系统管理 性能调优 虚拟化与集群应用</a></td><td><a href="https://book.douban.com/subject/26758194/" rel="nofollow">https://book.douban.com/subject/26758194/</a></td></tr><tr><td><a href="https://book.douban.com/subject/25743846/" rel="nofollow">深度探索 Linux 操作系统 : 系统构建和原理解析</a></td><td><a href="https://book.douban.com/subject/25743846/" rel="nofollow">https://book.douban.com/subject/25743846/</a></td></tr><tr><td><a href="https://book.douban.com/subject/3042029/" rel="nofollow">Linux 系统管理技术手册（第 2 版）</a></td><td><a href="https://book.douban.com/subject/3042029/" rel="nofollow">https://book.douban.com/subject/3042029/</a></td></tr><tr><td><a href="https://book.douban.com/subject/7564094/" rel="nofollow">高性能 Linux 服务器构建实战：运维监控、性能调优与集群应用</a></td><td><a href="https://book.douban.com/subject/7564094/" rel="nofollow">https://book.douban.com/subject/7564094/</a></td></tr><tr><td><a href="https://book.douban.com/subject/2306842/" rel="nofollow">Linux 安全体系分析与编程</a></td><td><a href="https://book.douban.com/subject/2306842/" rel="nofollow">https://book.douban.com/subject/2306842/</a></td></tr><tr><td><a href="https://book.douban.com/subject/4027707/" rel="nofollow">Linux 玩家技术宝典 : 你所不知道的 Linux</a></td><td><a href="https://book.douban.com/subject/4027707/" rel="nofollow">https://book.douban.com/subject/4027707/</a></td></tr><tr><td><a href="https://book.douban.com/subject/1237488/" rel="nofollow">Red Hat Linux 安全与优化</a></td><td><a href="https://book.douban.com/subject/1237488/" rel="nofollow">https://book.douban.com/subject/1237488/</a></td></tr></tbody></table>

### [](#嵌入式)嵌入式

<table><thead><tr><th>书名</th><th>豆瓣读书链接</th></tr></thead><tbody><tr><td><a href="https://book.douban.com/subject/3670713/" rel="nofollow">嵌入式 Linux 程序设计案例与实验教程</a></td><td><a href="https://book.douban.com/subject/3670713/" rel="nofollow">https://book.douban.com/subject/3670713/</a></td></tr><tr><td><a href="https://book.douban.com/subject/24522809/" rel="nofollow">构建嵌入式 Linux 核心软件系统实战 : 构建嵌入式 Linux 核心软件系统实战</a></td><td><a href="https://book.douban.com/subject/24522809/" rel="nofollow">https://book.douban.com/subject/24522809/</a></td></tr><tr><td><a href="https://book.douban.com/subject/3152027/" rel="nofollow">嵌入式 Linux 应用开发完全手册</a></td><td><a href="https://book.douban.com/subject/3152027/" rel="nofollow">https://book.douban.com/subject/3152027/</a></td></tr><tr><td><a href="https://book.douban.com/subject/2985887/" rel="nofollow">嵌入式 Linux 设备驱动开发详解</a></td><td><a href="https://book.douban.com/subject/2985887/" rel="nofollow">https://book.douban.com/subject/2985887/</a></td></tr><tr><td><a href="https://book.douban.com/subject/6533463/" rel="nofollow">深入浅出嵌入式底层软件开发</a></td><td><a href="https://book.douban.com/subject/6533463/" rel="nofollow">https://book.douban.com/subject/6533463/</a></td></tr></tbody></table>

### [](#linux内核)Linux 内核

<table><thead><tr><th>书名</th><th>豆瓣读书链接</th></tr></thead><tbody><tr><td><a href="https://book.douban.com/subject/6097773/" rel="nofollow">Linux 内核设计与实现（原书第 3 版）</a></td><td><a href="https://book.douban.com/subject/6097773/" rel="nofollow">https://book.douban.com/subject/6097773/</a></td></tr><tr><td><a href="https://book.douban.com/subject/2287506/" rel="nofollow">深入理解 LINUX 内核（第 3 版）</a></td><td><a href="https://book.douban.com/subject/2287506/" rel="nofollow">https://book.douban.com/subject/2287506/</a></td></tr><tr><td><a href="https://book.douban.com/subject/6433169/" rel="nofollow">Linux 内核设计的艺术 : 图解 Linux 操作系统架构设计与实现原理</a></td><td><a href="https://book.douban.com/subject/6433169/" rel="nofollow">https://book.douban.com/subject/6433169/</a></td></tr><tr><td><a href="https://book.douban.com/subject/1420480/" rel="nofollow">LINUX 设备驱动程序（第 3 版）</a></td><td><a href="https://book.douban.com/subject/1420480/" rel="nofollow">https://book.douban.com/subject/1420480/</a></td></tr><tr><td><a href="https://book.douban.com/subject/26600201/" rel="nofollow">Linux 设备驱动开发详解 : 基于最新的 Linux 4.0 内核</a></td><td><a href="https://book.douban.com/subject/26600201/" rel="nofollow">https://book.douban.com/subject/26600201/</a></td></tr><tr><td><a href="https://book.douban.com/subject/5351818/" rel="nofollow">Linux 设备驱动开发详解（第 2 版）</a></td><td><a href="https://book.douban.com/subject/5351818/" rel="nofollow">https://book.douban.com/subject/5351818/</a></td></tr><tr><td><a href="https://book.douban.com/subject/4843567/" rel="nofollow">深入 Linux 内核架构</a></td><td><a href="https://book.douban.com/subject/4843567/" rel="nofollow">https://book.douban.com/subject/4843567/</a></td></tr><tr><td><a href="https://book.douban.com/subject/21332497/" rel="nofollow">Linux 内核精髓 : 精通 Linux 内核必会的 75 个绝技</a></td><td><a href="https://book.douban.com/subject/21332497/" rel="nofollow">https://book.douban.com/subject/21332497/</a></td></tr><tr><td><a href="https://book.douban.com/subject/1231236/" rel="nofollow">Linux 内核完全注释（修订版）</a></td><td><a href="https://book.douban.com/subject/1231236/" rel="nofollow">https://book.douban.com/subject/1231236/</a></td></tr><tr><td><a href="https://book.douban.com/subject/10433743/" rel="nofollow">深入 Linux 设备驱动程序内核机制</a></td><td><a href="https://book.douban.com/subject/10433743/" rel="nofollow">https://book.douban.com/subject/10433743/</a></td></tr><tr><td><a href="https://book.douban.com/subject/4015134/" rel="nofollow">深入理解 LINUX 网络技术内幕</a></td><td><a href="https://book.douban.com/subject/4015134/" rel="nofollow">https://book.douban.com/subject/4015134/</a></td></tr><tr><td><a href="https://book.douban.com/subject/6078728/" rel="nofollow">Linux 驱动程序开发实例</a></td><td><a href="https://book.douban.com/subject/6078728/" rel="nofollow">https://book.douban.com/subject/6078728/</a></td></tr></tbody></table>

[](#防火墙)防火墙
-----------

<table><thead><tr><th>书名</th><th>豆瓣读书链接</th></tr></thead><tbody><tr><td><a href="https://book.douban.com/subject/1838749/" rel="nofollow">Linux 防火墙 -(原书第 3 版)</a></td><td><a href="https://book.douban.com/subject/1838749/" rel="nofollow">https://book.douban.com/subject/1838749/</a></td></tr><tr><td><a href="https://book.douban.com/subject/3678862/" rel="nofollow">Linux 防火墙</a></td><td><a href="https://book.douban.com/subject/3678862/" rel="nofollow">https://book.douban.com/subject/3678862/</a></td></tr></tbody></table>

[](#云计算)云计算
-----------

### [](#系统虚拟化)系统虚拟化

<table><thead><tr><th>书名</th><th>豆瓣读书链接</th></tr></thead><tbody><tr><td><a href="https://book.douban.com/subject/3768550/" rel="nofollow">Xen 虚拟化技术</a></td><td><a href="https://book.douban.com/subject/3768550/" rel="nofollow">https://book.douban.com/subject/3768550/</a></td></tr><tr><td><a href="https://book.douban.com/subject/25743939/" rel="nofollow">KVM 虚拟化技术 : 实战与原理解析</a></td><td><a href="https://book.douban.com/subject/25743939/" rel="nofollow">https://book.douban.com/subject/25743939/</a></td></tr><tr><td><a href="https://book.douban.com/subject/26766965/" rel="nofollow">Linux KVM 虚拟化架构实战指南</a></td><td><a href="https://book.douban.com/subject/26766965/" rel="nofollow">https://book.douban.com/subject/26766965/</a></td></tr><tr><td><a href="https://book.douban.com/subject/3619896/" rel="nofollow">系统虚拟化 : 原理与实现</a></td><td><a href="https://book.douban.com/subject/3619896/" rel="nofollow">https://book.douban.com/subject/3619896/</a></td></tr><tr><td><a href="https://book.douban.com/subject/19986436/" rel="nofollow">虚拟化技术原理与实现</a></td><td><a href="https://book.douban.com/subject/19986436/" rel="nofollow">https://book.douban.com/subject/19986436/</a></td></tr></tbody></table>

### [](#docker)Docker

<table><thead><tr><th>书名</th><th>豆瓣读书链接</th></tr></thead><tbody><tr><td><a href="https://book.douban.com/subject/26894736/" rel="nofollow">Docker 容器与容器云（第 2 版）</a></td><td><a href="https://book.douban.com/subject/26894736/" rel="nofollow">https://book.douban.com/subject/26894736/</a></td></tr><tr><td><a href="https://book.douban.com/subject/30329430/" rel="nofollow">Docker 技术入门与实战（第 3 版）</a></td><td><a href="https://book.douban.com/subject/30329430/" rel="nofollow">https://book.douban.com/subject/30329430/</a></td></tr><tr><td><a href="https://book.douban.com/subject/26581184/" rel="nofollow">Docker 源码分析</a></td><td><a href="https://book.douban.com/subject/26581184/" rel="nofollow">https://book.douban.com/subject/26581184/</a></td></tr><tr><td><a href="https://book.douban.com/subject/27013734/" rel="nofollow">Docker 开发指南</a></td><td><a href="https://book.douban.com/subject/27013734/" rel="nofollow">https://book.douban.com/subject/27013734/</a></td></tr></tbody></table>

### [](#kubernetes)Kubernetes

<table><thead><tr><th>书名</th><th>豆瓣读书链接</th></tr></thead><tbody><tr><td><a href="https://book.douban.com/subject/33444476/" rel="nofollow">Kubernetes 权威指南：从 Docker 到 Kubernetes 实践全接触（第 4 版）</a></td><td><a href="https://book.douban.com/subject/33444476/" rel="nofollow">https://book.douban.com/subject/33444476/</a></td></tr><tr><td><a href="https://book.douban.com/subject/27088186/" rel="nofollow">开源容器云 OpenShift 构建基于 Kubernetes 的企业应用云平台</a></td><td><a href="https://book.douban.com/subject/27088186/" rel="nofollow">https://book.douban.com/subject/27088186/</a></td></tr><tr><td><a href="https://book.douban.com/subject/30333237/" rel="nofollow">基于 Kubernetes 的容器云平台实战</a></td><td><a href="https://book.douban.com/subject/30333237/" rel="nofollow">https://book.douban.com/subject/30333237/</a></td></tr></tbody></table>

### [](#serverless)Serverless

<table><thead><tr><th>书名</th><th>豆瓣读书链接</th></tr></thead><tbody><tr><td><a href="https://book.douban.com/subject/30290516/" rel="nofollow">Serverless 架构：无服务器应用与 AWS Lambda</a></td><td><a href="https://book.douban.com/subject/30290516/" rel="nofollow">https://book.douban.com/subject/30290516/</a></td></tr></tbody></table>

### [](#云计算基础)云计算基础

<table><thead><tr><th>书名</th><th>豆瓣读书链接</th></tr></thead><tbody><tr><td><a href="https://book.douban.com/subject/26602591/" rel="nofollow">数据中心虚拟化技术权威指南</a></td><td><a href="https://book.douban.com/subject/26602591/" rel="nofollow">https://book.douban.com/subject/26602591/</a></td></tr><tr><td><a href="https://book.douban.com/subject/4212925/" rel="nofollow">云计算的关键技术与应用实例</a></td><td><a href="https://book.douban.com/subject/4212925/" rel="nofollow">https://book.douban.com/subject/4212925/</a></td></tr><tr><td><a href="https://book.douban.com/subject/10803946/" rel="nofollow">云计算与数据中心自动化</a></td><td><a href="https://book.douban.com/subject/10803946/" rel="nofollow">https://book.douban.com/subject/10803946/</a></td></tr><tr><td><a href="https://book.douban.com/subject/26425400/" rel="nofollow">云计算 : 概念、技术与架构</a></td><td><a href="https://book.douban.com/subject/26425400/" rel="nofollow">https://book.douban.com/subject/26425400/</a></td></tr></tbody></table>

## 微服务

### [](#微服务基础)微服务基础

<table><thead><tr><th>书名</th><th>豆瓣读书链接</th></tr></thead><tbody><tr><td><a href="https://book.douban.com/subject/26772677/" rel="nofollow">微服务设计</a></td><td><a href="https://book.douban.com/subject/26772677/" rel="nofollow">https://book.douban.com/subject/26772677/</a></td></tr><tr><td><a href="https://book.douban.com/subject/26693152/" rel="nofollow">微服务架构与实践（第 1 版）</a></td><td><a href="https://book.douban.com/subject/26693152/" rel="nofollow">https://book.douban.com/subject/26693152/</a></td></tr><tr><td><a href="https://book.douban.com/subject/30278673/" rel="nofollow">Spring Cloud 与 Docker 微服务架构实战</a></td><td><a href="https://book.douban.com/subject/30278673/" rel="nofollow">https://book.douban.com/subject/30278673/</a></td></tr><tr><td><a href="https://book.douban.com/subject/27081188/" rel="nofollow">架构解密：从分布式到微服务</a></td><td><a href="https://book.douban.com/subject/27081188/" rel="nofollow">https://book.douban.com/subject/27081188/</a></td></tr><tr><td><a href="https://book.douban.com/subject/27025912/" rel="nofollow">Spring Cloud 微服务实战</a></td><td><a href="https://book.douban.com/subject/27025912/" rel="nofollow">https://book.douban.com/subject/27025912/</a></td></tr><tr><td><a href="https://book.douban.com/subject/26106868/" rel="nofollow">Java RESTful Web Service 实战</a></td><td><a href="https://book.douban.com/subject/26106868/" rel="nofollow">https://book.douban.com/subject/26106868/</a></td></tr></tbody></table>

### [](#负载均衡)负载均衡

<table><thead><tr><th>书名</th><th>豆瓣读书链接</th></tr></thead><tbody><tr><td><a href="https://book.douban.com/subject/26745255/" rel="nofollow">深入理解 Nginx（第 2 版） : 模块开发与架构解析</a></td><td><a href="https://book.douban.com/subject/26745255/" rel="nofollow">https://book.douban.com/subject/26745255/</a></td></tr><tr><td><a href="https://book.douban.com/subject/22793675/" rel="nofollow">深入理解 Nginx : 模块开发与架构解析</a></td><td><a href="https://book.douban.com/subject/22793675/" rel="nofollow">https://book.douban.com/subject/22793675/</a></td></tr><tr><td><a href="https://book.douban.com/subject/10746087/" rel="nofollow">决战 Nginx 系统卷 : 高性能 Web 服务器详解与运维</a></td><td><a href="https://book.douban.com/subject/10746087/" rel="nofollow">https://book.douban.com/subject/10746087/</a></td></tr><tr><td><a href="https://book.douban.com/subject/25773187/" rel="nofollow">Nginx 高性能 Web 服务器详解</a></td><td><a href="https://book.douban.com/subject/25773187/" rel="nofollow">https://book.douban.com/subject/25773187/</a></td></tr></tbody></table>

### [](#服务网格)服务网格

<table><thead><tr><th>书名</th><th>豆瓣读书链接</th></tr></thead><tbody><tr><td><a href="https://book.douban.com/subject/34438220/" rel="nofollow">云原生服务网格 Istio：原理、实践、架构与源码解析</a></td><td><a href="https://book.douban.com/subject/34438220/" rel="nofollow">https://book.douban.com/subject/34438220/</a></td></tr></tbody></table>

[](#消息队列)消息队列
-------------

### [](#kafka)kafka

<table><thead><tr><th>书名</th><th>豆瓣读书链接</th></tr></thead><tbody><tr><td><a href="https://book.douban.com/subject/30221096/" rel="nofollow">Apache Kafka 实战</a></td><td><a href="https://book.douban.com/subject/30221096/" rel="nofollow">https://book.douban.com/subject/30221096/</a></td></tr><tr><td><a href="https://book.douban.com/subject/27038473/" rel="nofollow">Apache Kafka 源码剖析</a></td><td><a href="https://book.douban.com/subject/27038473/" rel="nofollow">https://book.douban.com/subject/27038473/</a></td></tr><tr><td><a href="https://book.douban.com/subject/27665114/" rel="nofollow">Kafka 权威指南</a></td><td><a href="https://book.douban.com/subject/27665114/" rel="nofollow">https://book.douban.com/subject/27665114/</a></td></tr><tr><td><a href="https://book.douban.com/subject/30128444/" rel="nofollow">Kafka 源码解析与实战</a></td><td><a href="https://book.douban.com/subject/30128444/" rel="nofollow">https://book.douban.com/subject/30128444/</a></td></tr><tr><td><a href="https://book.douban.com/subject/27179953/" rel="nofollow">Kafka 技术内幕</a></td><td><a href="https://book.douban.com/subject/27179953/" rel="nofollow">https://book.douban.com/subject/27179953/</a></td></tr><tr><td><a href="https://book.douban.com/subject/30437872/" rel="nofollow">深入理解 Kafka：核心设计与实践原理</a></td><td><a href="https://book.douban.com/subject/30437872/" rel="nofollow">https://book.douban.com/subject/30437872/</a></td></tr></tbody></table>

### [](#rabbitmq)RabbitMQ

<table><thead><tr><th>书名</th><th>豆瓣读书链接</th></tr></thead><tbody><tr><td><a href="https://book.douban.com/subject/26649178/" rel="nofollow">RabbitMQ 实战 : 高效部署分布式消息队列</a></td><td><a href="https://book.douban.com/subject/26649178/" rel="nofollow">https://book.douban.com/subject/26649178/</a></td></tr></tbody></table>

[](#数据库)数据库
-----------

### [](#sql)SQL

<table><thead><tr><th>书名</th><th>豆瓣读书链接</th></tr></thead><tbody><tr><td><a href="https://book.douban.com/subject/2022427/" rel="nofollow">精通 SQL 结构化查询语言详解</a></td><td><a href="https://book.douban.com/subject/2022427/" rel="nofollow">https://book.douban.com/subject/2022427/</a></td></tr><tr><td><a href="https://book.douban.com/subject/26579980/" rel="nofollow">SQL 学习指南（第 2 版）</a></td><td><a href="https://book.douban.com/subject/26579980/" rel="nofollow">https://book.douban.com/subject/26579980/</a></td></tr><tr><td><a href="https://book.douban.com/subject/3691315/" rel="nofollow">SQL 宝典</a></td><td><a href="https://book.douban.com/subject/3691315/" rel="nofollow">https://book.douban.com/subject/3691315/</a></td></tr></tbody></table>

### [](#mysql)MySQL

<table><thead><tr><th>书名</th><th>豆瓣读书链接</th></tr></thead><tbody><tr><td><a href="https://book.douban.com/subject/3354490/" rel="nofollow">MySQL 必知必会</a></td><td><a href="https://book.douban.com/subject/3354490/" rel="nofollow">https://book.douban.com/subject/3354490/</a></td></tr><tr><td><a href="https://book.douban.com/subject/26591051/" rel="nofollow">MySQL 排错指南</a></td><td><a href="https://book.douban.com/subject/26591051/" rel="nofollow">https://book.douban.com/subject/26591051/</a></td></tr><tr><td><a href="https://book.douban.com/subject/23008813/" rel="nofollow">高性能 MySQL（第 3 版）</a></td><td><a href="https://book.douban.com/subject/23008813/" rel="nofollow">https://book.douban.com/subject/23008813/</a></td></tr><tr><td><a href="https://book.douban.com/subject/5373022/" rel="nofollow">MySQL 技术内幕 InnoDB 存储引擎（第 1 版）</a></td><td><a href="https://book.douban.com/subject/5373022/" rel="nofollow">https://book.douban.com/subject/5373022/</a></td></tr><tr><td><a href="https://book.douban.com/subject/6524185/" rel="nofollow">MySQL 技术内幕（第 4 版）</a></td><td><a href="https://book.douban.com/subject/6524185/" rel="nofollow">https://book.douban.com/subject/6524185/</a></td></tr><tr><td><a href="https://book.douban.com/subject/4188364/" rel="nofollow">深入理解 MySQL</a></td><td><a href="https://book.douban.com/subject/4188364/" rel="nofollow">https://book.douban.com/subject/4188364/</a></td></tr><tr><td><a href="https://book.douban.com/subject/26340413/" rel="nofollow">MariaDB 原理与实现</a></td><td><a href="https://book.douban.com/subject/26340413/" rel="nofollow">https://book.douban.com/subject/26340413/</a></td></tr><tr><td><a href="https://book.douban.com/subject/26630834/" rel="nofollow">高可用 MySQL（第 2 版）</a></td><td><a href="https://book.douban.com/subject/26630834/" rel="nofollow">https://book.douban.com/subject/26630834/</a></td></tr><tr><td><a href="https://book.douban.com/subject/25872763/" rel="nofollow">MySQL 内核：InnoDB 存储引擎 卷 1</a></td><td><a href="https://book.douban.com/subject/25872763/" rel="nofollow">https://book.douban.com/subject/25872763/</a></td></tr></tbody></table>

### [](#oracle)Oracle

<table><thead><tr><th>书名</th><th>豆瓣读书链接</th></tr></thead><tbody><tr><td><a href="https://book.douban.com/subject/2316500/" rel="nofollow">Oracle 10g 数据库管理应用与开发标准教程</a></td><td><a href="https://book.douban.com/subject/2316500/" rel="nofollow">https://book.douban.com/subject/2316500/</a></td></tr><tr><td><a href="https://book.douban.com/subject/1503909/" rel="nofollow">Oracle 高效设计</a></td><td><a href="https://book.douban.com/subject/1503909/" rel="nofollow">https://book.douban.com/subject/1503909/</a></td></tr></tbody></table>

### [](#redis)Redis

<table><thead><tr><th>书名</th><th>豆瓣读书链接</th></tr></thead><tbody><tr><td><a href="https://book.douban.com/subject/25900156/" rel="nofollow">Redis 设计与实现</a></td><td><a href="https://book.douban.com/subject/25900156/" rel="nofollow">https://book.douban.com/subject/25900156/</a></td></tr></tbody></table>

### [](#mongo)Mongo

<table><thead><tr><th>书名</th><th>豆瓣读书链接</th></tr></thead><tbody><tr><td><a href="https://book.douban.com/subject/26269829/" rel="nofollow">MongoDB 大数据处理权威指南（第 2 版）</a></td><td><a href="https://book.douban.com/subject/26269829/" rel="nofollow">https://book.douban.com/subject/26269829/</a></td></tr></tbody></table>

[](#大数据)大数据
-----------

### [](#hadoop)Hadoop

<table><thead><tr><th>书名</th><th>豆瓣读书链接</th></tr></thead><tbody><tr><td><a href="/marmotedu/awesome-books/blob/master"></a></td><td></td></tr></tbody></table>

### [](#spark)Spark

<table><thead><tr><th>书名</th><th>豆瓣读书链接</th></tr></thead><tbody><tr><td><a href="/marmotedu/awesome-books/blob/master"></a></td><td></td></tr></tbody></table>

### [](#flink)Flink

<table><thead><tr><th>书名</th><th>豆瓣读书链接</th></tr></thead><tbody><tr><td><a href="/marmotedu/awesome-books/blob/master"></a></td><td></td></tr></tbody></table>

### [](#hbase)Hbase

<table><thead><tr><th>书名</th><th>豆瓣读书链接</th></tr></thead><tbody><tr><td><a href="/marmotedu/awesome-books/blob/master"></a></td><td></td></tr></tbody></table>

[](#人工智能)人工智能
-------------

### [](#人工智能基础)人工智能基础

<table><thead><tr><th>书名</th><th>豆瓣读书链接</th></tr></thead><tbody><tr><td><a href="/marmotedu/awesome-books/blob/master"></a></td><td></td></tr></tbody></table>

### [](#tensorflow)TensorFlow

<table><thead><tr><th>书名</th><th>豆瓣读书链接</th></tr></thead><tbody><tr><td><a href="/marmotedu/awesome-books/blob/master"></a></td><td></td></tr></tbody></table>

[](#分布式)分布式
-----------

### [](#分布式基础)分布式基础

<table><thead><tr><th>书名</th><th>豆瓣读书链接</th></tr></thead><tbody><tr><td><a href="/marmotedu/awesome-books/blob/master"></a></td><td></td></tr></tbody></table>

### [](#etcd)Etcd

<table><thead><tr><th>书名</th><th>豆瓣读书链接</th></tr></thead><tbody><tr><td><a href="/marmotedu/awesome-books/blob/master"></a></td><td></td></tr></tbody></table>

### [](#zookpeer)Zookpeer

<table><thead><tr><th>书名</th><th>豆瓣读书链接</th></tr></thead><tbody><tr><td><a href="/marmotedu/awesome-books/blob/master"></a></td><td></td></tr></tbody></table>

[](#数据结构)数据结构
-------------

<table><thead><tr><th>书名</th><th>豆瓣读书链接</th></tr></thead><tbody><tr><td><a href="/marmotedu/awesome-books/blob/master"></a></td><td></td></tr></tbody></table>

[](#算法)算法
---------

<table><thead><tr><th>书名</th><th>豆瓣读书链接</th></tr></thead><tbody><tr><td><a href="/marmotedu/awesome-books/blob/master"></a></td><td></td></tr></tbody></table>

[](#运维)运维
---------

### [](#运维基础)运维基础

<table><thead><tr><th>书名</th><th>豆瓣读书链接</th></tr></thead><tbody><tr><td><a href="/marmotedu/awesome-books/blob/master"></a></td><td></td></tr></tbody></table>

### [](#日志)日志

<table><thead><tr><th>书名</th><th>豆瓣读书链接</th></tr></thead><tbody><tr><td><a href="/marmotedu/awesome-books/blob/master"></a></td><td></td></tr></tbody></table>

### [](#监控告警)监控告警

<table><thead><tr><th>书名</th><th>豆瓣读书链接</th></tr></thead><tbody><tr><td><a href="/marmotedu/awesome-books/blob/master"></a></td><td></td></tr></tbody></table>

[](#devops)DevOps
-----------------

<table><thead><tr><th>书名</th><th>豆瓣读书链接</th></tr></thead><tbody><tr><td><a href="/marmotedu/awesome-books/blob/master"></a></td><td></td></tr></tbody></table>

[](#区块链)区块链
-----------

<table><thead><tr><th>书名</th><th>豆瓣读书链接</th></tr></thead><tbody><tr><td><a href="/marmotedu/awesome-books/blob/master"></a></td><td></td></tr></tbody></table>

[](#性能)性能
---------

<table><thead><tr><th>书名</th><th>豆瓣读书链接</th></tr></thead><tbody><tr><td><a href="/marmotedu/awesome-books/blob/master"></a></td><td></td></tr></tbody></table>

[](#测试)测试
---------

### [](#测试基础)测试基础

<table><thead><tr><th>书名</th><th>豆瓣读书链接</th></tr></thead><tbody><tr><td><a href="/marmotedu/awesome-books/blob/master"></a></td><td></td></tr></tbody></table>

### [](#性能测试)性能测试

<table><thead><tr><th>书名</th><th>豆瓣读书链接</th></tr></thead><tbody><tr><td><a href="/marmotedu/awesome-books/blob/master"></a></td><td></td></tr></tbody></table>

[](#工具类)工具类
-----------

<table><thead><tr><th>书名</th><th>豆瓣读书链接</th></tr></thead><tbody><tr><td><a href="/marmotedu/awesome-books/blob/master"></a></td><td></td></tr></tbody></table>

[](#面试)面试
---------

<table><thead><tr><th>书名</th><th>豆瓣读书链接</th></tr></thead><tbody><tr><td><a href="/marmotedu/awesome-books/blob/master"></a></td><td></td></tr></tbody></table>

[](#其它资料)其它资料
-------------

<table><thead><tr><th>书名</th><th>豆瓣读书链接</th></tr></thead><tbody><tr><td><a href="/marmotedu/awesome-books/blob/master"></a></td><td></td></tr></tbody></table>

[](#按标签推荐系列)按标签推荐系列
-------------------

### [](#微服务系列推荐)微服务系列推荐

<table><thead><tr><th>书名</th><th>豆瓣读书链接</th></tr></thead><tbody><tr><td><a href="/marmotedu/awesome-books/blob/master"></a></td><td></td></tr></tbody></table>

### [](#云原生系列推荐)云原生系列推荐

<table><thead><tr><th>书名</th><th>豆瓣读书链接</th></tr></thead><tbody><tr><td><a href="/marmotedu/awesome-books/blob/master"></a></td><td></td></tr></tbody></table>