> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 [www.bookstack.cn](https://www.bookstack.cn/read/API-design-guide/API-design-guide-01-%E7%AE%80%E4%BB%8B.md)

> 介绍本文档的约定 这是一份网络API的通用设计指南。Google内部从2014年开始使用它，我们在设计Cloud API和Google API时也遵从了这份指南。我们把它分享给Google以外的开发人员，这也会让我们的合作变得更顺利。

这是一份网络 API 的通用设计指南。Google 内部从 2014 年开始使用它，我们在设计 [Cloud API](https://cloud.google.com/apis/docs/overview) 和 [Google API](https://github.com/googleapis/googleapis) 时也遵从了这份指南。我们把它分享给 Google 以外的开发人员，这也会让我们的合作变得更顺利。

外部开发者可能会发现在设计使用 [Google Cloud Endpoint](https://cloud.google.com/endpoints/docs/grpc)(译者注：GCE，面向开发者的 api 管理平台) 的 API 时这份指南非常有用，我们强烈建议外部开发者使用这些设计准则。尽管如此，我们并不强求任何非 Google 的开发人员使用这份指南，你可以在使用 Cloud Endpoint 或者 gRPC 时不遵从这份指南。

这份指南既适用于 REST API 也适用于 RPC API，并重点关注 gRPC API。gRPC API 使用 [Protocol Buffer](https://cloud.google.com/apis/design/proto3) 定义 API 接口，并使用 [API Service Configuration](https://github.com/googleapis/googleapis) 去配置 API 服务，包括 HTTP 映射，日志和监控。Google API 和 gRPC Cloud Endpoint API 使用 HTTP 映射特性用把 JSON/HTTP [转码](https://cloud.google.com/endpoints/docs/transcoding)到 Protocal Buffer 和 RPC。

这份指南会随着时间的变化持续更新，去采用和适应新风格和设计模式。本着这个原则，这份指南从来不会处于完备状态，API 的设计艺术和技艺总是有提升的空间。

本文档所用的需求级别描述词语”MUST”, “MUST NOT”, “REQUIRED”, “SHALL”, “SHALL NOT”, “SHOULD”, “SHOULD NOT”, “RECOMMENDED”, “MAY”, and “OPTIONAL” 在 [RFC 2119](https://www.ietf.org/rfc/rfc2119.txt) 中有详细描述，在文档中这些关键词使用**粗体**显示。