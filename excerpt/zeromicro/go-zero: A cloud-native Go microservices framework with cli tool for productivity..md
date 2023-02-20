> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 [github.com](https://github.com/zeromicro/go-zero)

> A cloud-native Go microservices framework with cli tool for productivity. - zeromicro/go-zero: A cloud-native Go microservices framework with cli tool for productivity.

[](#go-zero)go-zero
===================

[![](https://raw.githubusercontent.com/zeromicro/zero-doc/main/doc/images/go-zero.png)](https://raw.githubusercontent.com/zeromicro/zero-doc/main/doc/images/go-zero.png)

go-zero is a web and rpc framework with lots of builtin engineering practices. It’s born to ensure the stability of the busy services with resilience design and has been serving sites with tens of millions of users for years.

[![](https://github.com/zeromicro/go-zero/workflows/Go/badge.svg?branch=master)](https://github.com/zeromicro/go-zero/actions) [![](https://camo.githubusercontent.com/057dcd41df16bff3a61742c5c2376da59848900a9d6ccfb44e64562548178189/68747470733a2f2f636f6465636f762e696f2f67682f7a65726f6d6963726f2f676f2d7a65726f2f6272616e63682f6d61737465722f67726170682f62616467652e737667)](https://codecov.io/gh/zeromicro/go-zero) [![](https://camo.githubusercontent.com/5ee85c574ad508d4bb4ba5a809b356fc6f1bf45ad4b53211ffedaef38f051245/68747470733a2f2f676f7265706f7274636172642e636f6d2f62616467652f6769746875622e636f6d2f7a65726f6d6963726f2f676f2d7a65726f)](https://goreportcard.com/report/github.com/zeromicro/go-zero) [![](https://camo.githubusercontent.com/af159d94ebce7b4d2c5df1260c0011eae89a9f9e9bfb936acd92c3d9efcef5cf/68747470733a2f2f696d672e736869656c64732e696f2f6769746875622f762f72656c656173652f7a65726f6d6963726f2f676f2d7a65726f2e7376673f7374796c653d666c61742d737175617265)](https://github.com/zeromicro/go-zero) [![](https://camo.githubusercontent.com/520af295929dab78a45170d344c818dd9b8da073f22930743675652357ae0263/68747470733a2f2f706b672e676f2e6465762f62616467652f6769746875622e636f6d2f7a65726f6d6963726f2f676f2d7a65726f2e737667)](https://pkg.go.dev/github.com/zeromicro/go-zero) [![](https://camo.githubusercontent.com/abb97269de2982c379cbc128bba93ba724d8822bfbe082737772bd4feb59cb54/68747470733a2f2f63646e2e7261776769742e636f6d2f73696e647265736f726875732f617765736f6d652f643733303566333864323966656437386661383536353265336136336531353464643865383832392f6d656469612f62616467652e737667)](https://github.com/avelino/awesome-go) [![](https://camo.githubusercontent.com/78f47a09877ba9d28da1887a93e5c3bc2efb309c1e910eb21135becd2998238a/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f4c6963656e73652d4d49542d79656c6c6f772e737667)](https://opensource.org/licenses/MIT) [![](https://camo.githubusercontent.com/87a436cd0117d591c6fbe9f7408e11d655e5db49d62babd49f45f47ef4f0cc5a/68747470733a2f2f696d672e736869656c64732e696f2f646973636f72642f3739343533303737343436333431343239323f6c6162656c3d63686174266c6f676f3d646973636f7264)](https://discord.gg/4JQvC5A4Fe)

[](#-what-is-go-zero)🤷‍ What is go-zero?
-----------------------------------------

English | [简体中文](/zeromicro/go-zero/blob/master/readme-cn.md)

[![](https://camo.githubusercontent.com/55fc9c16aa951a4761557dc908e5517c8315d7dc016dedd015c746dde6e7c55f/68747470733a2f2f6170692e70726f6475637468756e742e636f6d2f776964676574732f656d6265642d696d6167652f76312f66656174757265642e7376673f706f73745f69643d333334303330267468656d653d6c69676874)](https://www.producthunt.com/posts/go-zero?utm_source=badge-featured&utm_medium=badge&utm_souce=badge-go-zero)

go-zero (listed in CNCF Landscape: [https://landscape.cncf.io/?selected=go-zero](https://landscape.cncf.io/?selected=go-zero)) is a web and rpc framework with lots of builtin engineering practices. It’s born to ensure the stability of the busy services with resilience design and has been serving sites with tens of millions of users for years.

go-zero contains simple API description syntax and code generation tool called `goctl`. You can generate Go, iOS, Android, Kotlin, Dart, TypeScript, JavaScript from .api files with `goctl`.

#### [](#advantages-of-go-zero)Advantages of go-zero:

*   improve the stability of the services with tens of millions of daily active users
*   builtin chained timeout control, concurrency control, rate limit, adaptive circuit breaker, adaptive load shedding, even no configuration needed
*   builtin middlewares also can be integrated into your frameworks
*   simple API syntax, one command to generate a couple of different languages
*   auto validate the request parameters from clients
*   plenty of builtin microservice management and concurrent toolkits

[![](https://raw.githubusercontent.com/zeromicro/zero-doc/main/doc/images/architecture-en.png)](https://raw.githubusercontent.com/zeromicro/zero-doc/main/doc/images/architecture-en.png)

[](#backgrounds-of-go-zero)Backgrounds of go-zero
-------------------------------------------------

At the beginning of 2018, we decided to re-design our system, from monolithic architecture with Java+MongoDB to microservice architecture. After research and comparison, we chose to:

*   Golang based
    *   great performance
    *   simple syntax
    *   proven engineering efficiency
    *   extreme deployment experience
    *   less server resource consumption
*   Self-designed microservice architecture
    *   I have rich experience in designing microservice architectures
    *   easy to locate the problems
    *   easy to extend the features

[](#design-considerations-on-go-zero)Design considerations on go-zero
---------------------------------------------------------------------

By designing the microservice architecture, we expected to ensure stability, as well as productivity. And from just the beginning, we have the following design principles:

*   keep it simple
*   high availability
*   stable on high concurrency
*   easy to extend
*   resilience design, failure-oriented programming
*   try best to be friendly to the business logic development, encapsulate the complexity
*   one thing, one way

After almost half a year, we finished the transfer from a monolithic system to microservice system and deployed on August 2018. The new system guaranteed business growth and system stability.

[](#the-implementation-and-features-of-go-zero)The implementation and features of go-zero
-----------------------------------------------------------------------------------------

go-zero is a web and rpc framework that integrates lots of engineering practices. The features are mainly listed below:

*   powerful tool included, less code to write
*   simple interfaces
*   fully compatible with net/http
*   middlewares are supported, easy to extend
*   high performance
*   failure-oriented programming, resilience design
*   builtin service discovery, load balancing
*   builtin concurrency control, adaptive circuit breaker, adaptive load shedding, auto-trigger, auto recover
*   auto validation of API request parameters
*   chained timeout control
*   auto management of data caching
*   call tracing, metrics, and monitoring
*   high concurrency protected

As below, go-zero protects the system with a couple of layers and mechanisms:

[![](https://raw.githubusercontent.com/zeromicro/zero-doc/main/doc/images/resilience-en.png)](https://raw.githubusercontent.com/zeromicro/zero-doc/main/doc/images/resilience-en.png)

[](#the-simplified-architecture-that-we-use-with-go-zero)The simplified architecture that we use with go-zero
-------------------------------------------------------------------------------------------------------------

[![](https://user-images.githubusercontent.com/1918356/171880372-5010d846-e8b1-4942-8fe2-e2bbb584f762.png)](https://user-images.githubusercontent.com/1918356/171880372-5010d846-e8b1-4942-8fe2-e2bbb584f762.png)

[](#installation)Installation
-----------------------------

Run the following command under your project:

```
go get -u github.com/zeromicro/go-zero

```

[](#upgrade)Upgrade
-------------------

To upgrade from versions eariler than v1.3.0, run the following commands.

```
go install github.com/zeromicro/go-zero/tools/goctl@latest

```

```
goctl migrate —verbose —version v1.4.3

```

[](#quick-start)Quick Start
---------------------------

1.  full examples can be checked out from below:
    
    [Rapid development of microservice systems](https://github.com/zeromicro/zero-doc/blob/main/doc/shorturl-en.md)
    
    [Rapid development of microservice systems - multiple RPCs](https://github.com/zeromicro/zero-doc/blob/main/docs/zero/bookstore-en.md)
    
2.  install goctl
    
    `goctl`can be read as `go control`. `goctl` means not to be controlled by code, instead, we control it. The inside `go` is not `golang`. At the very beginning, I was expecting it to help us improve productivity, and make our lives easier.
    
    ```
    # for Go 1.15 and earlier
    GO111MODULE=on go get -u github.com/zeromicro/go-zero/tools/goctl@latest
    
    # for Go 1.16 and later
    go install github.com/zeromicro/go-zero/tools/goctl@latest
    
    # For Mac
    brew install goctl
    
    # docker for amd64 architecture
    docker pull kevinwan/goctl
    # run goctl like
    docker run --rm -it -v `pwd`:/app kevinwan/goctl goctl --help
    
    # docker for arm64 (M1) architecture
    docker pull kevinwan/goctl:latest-arm64
    # run goctl like
    docker run --rm -it -v `pwd`:/app kevinwan/goctl:latest-arm64 goctl --help
    
    ```
    
    make sure goctl is executable.
    
3.  create the API file, like greet.api, you can install the plugin of goctl in vs code, api syntax is supported.
    
    ```
    type (
      Request {
        Name string `path:"name,options=[you,me]"` // parameters are auto validated
      }
    
      Response {
        Message string `json:"message"`
      }
    )
    
    service greet-api {
      @handler GreetHandler
      get /greet/from/:name(Request) returns (Response)
    }
    
    ```
    
    the .api files also can be generated by goctl, like below:
    
    ```
    goctl api -o greet.api
    
    ```
    
4.  generate the go server-side code
    
    ```
    goctl api go -api greet.api -dir greet
    
    ```
    
    the generated files look like:
    
    ```
    ├── greet
    │   ├── etc
    │   │   └── greet-api.yaml        // configuration file
    │   ├── greet.go                  // main file
    │   └── internal
    │       ├── config
    │       │   └── config.go         // configuration definition
    │       ├── handler
    │       │   ├── greethandler.go   // get/put/post/delete routes are defined here
    │       │   └── routes.go         // routes list
    │       ├── logic
    │       │   └── greetlogic.go     // request logic can be written here
    │       ├── svc
    │       │   └── servicecontext.go // service context, mysql/redis can be passed in here
    │       └── types
    │           └── types.go          // request/response defined here
    └── greet.api                     // api description file
    
    
    ```
    
    the generated code can be run directly:
    
    ```
    cd greet
    go mod init
    go mod tidy
    go run greet.go -f etc/greet-api.yaml
    
    ```
    
    by default, it’s listening on port 8888, while it can be changed in the configuration file.
    
    you can check it by curl:
    
    ```
    curl -i http://localhost:8888/greet/from/you
    
    ```
    
    the response looks like below:
    
    ```
    HTTP/1.1 200 OK
    Date: Sun, 30 Aug 2020 15:32:35 GMT
    Content-Length: 0
    
    ```
    
5.  Write the business logic code
    
    *   the dependencies can be passed into the logic within servicecontext.go, like mysql, reds, etc.
    *   add the logic code in a logic package according to .api file
6.  Generate code like Java, TypeScript, Dart, JavaScript, etc. just from the api file
    
    ```
    goctl api java -api greet.api -dir greet
    goctl api dart -api greet.api -dir greet
    ...
    
    ```
    

[](#benchmark)Benchmark
-----------------------

[![](https://raw.githubusercontent.com/zeromicro/zero-doc/main/doc/images/benchmark.png)](https://raw.githubusercontent.com/zeromicro/zero-doc/main/doc/images/benchmark.png)

[Checkout the test code](https://github.com/smallnest/go-web-framework-benchmark)

[](#documents)Documents
-----------------------

*   [Documents](https://go-zero.dev/)
*   [Rapid development of microservice systems](https://github.com/zeromicro/zero-doc/blob/main/doc/shorturl-en.md)
*   [Rapid development of microservice systems - multiple RPCs](https://github.com/zeromicro/zero-doc/blob/main/docs/zero/bookstore-en.md)
*   [Examples](https://github.com/zeromicro/zero-examples)

[](#chat-group)Chat group
-------------------------

Join the chat via [https://discord.gg/4JQvC5A4Fe](https://discord.gg/4JQvC5A4Fe)

[](#cloud-native-landscape)Cloud Native Landscape
-------------------------------------------------

[![](https://camo.githubusercontent.com/72874858224f1be4f277d5e858c6cc63c785459927a4ab0c1dc63b7adeaea7b7/68747470733a2f2f6c616e6473636170652e636e63662e696f2f696d616765732f6c6566742d6c6f676f2e737667)](https://camo.githubusercontent.com/72874858224f1be4f277d5e858c6cc63c785459927a4ab0c1dc63b7adeaea7b7/68747470733a2f2f6c616e6473636170652e636e63662e696f2f696d616765732f6c6566742d6c6f676f2e737667)    [![](https://camo.githubusercontent.com/c5aee535d6f21df20c644791c2c999d670980166a9497e60b5bf2b496c902c6e/68747470733a2f2f6c616e6473636170652e636e63662e696f2f696d616765732f72696768742d6c6f676f2e737667)](https://camo.githubusercontent.com/c5aee535d6f21df20c644791c2c999d670980166a9497e60b5bf2b496c902c6e/68747470733a2f2f6c616e6473636170652e636e63662e696f2f696d616765732f72696768742d6c6f676f2e737667)

go-zero enlisted in the [CNCF Cloud Native Landscape](https://landscape.cncf.io/?selected=go-zero).

[](#give-a-star-)Give a Star! ⭐
-------------------------------

If you like or are using this project to learn or start your solution, please give it a star. Thanks!

[](#buy-me-a-coffee)Buy me a coffee
-----------------------------------

[![](https://camo.githubusercontent.com/28aae05a0fba45679e8e27d90609601e249b64a5fe30dfef05495de4f4e318d4/68747470733a2f2f63646e2e6275796d6561636f666665652e636f6d2f627574746f6e732f76322f64656661756c742d79656c6c6f772e706e67)](https://www.buymeacoffee.com/kevwan)