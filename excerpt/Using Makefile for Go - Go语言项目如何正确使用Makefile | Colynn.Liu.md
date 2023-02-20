> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 [colynn.github.io](https://colynn.github.io/2020-03-03-using_makefile/)

> Full-Stack DevOps, Be focused and Practical

我们一直在用 Go 语言编写的 HackerRank 项目中的一个项目使用`make`作为构建工具，并且效果良好。在这篇文章中，我将指出我们使用的`GNU Make`的一些功能和复杂性，这些功能和复杂性最终提高了我们团队成员的整体生产力。

前言
--

`make` 是一个简单的工具，它可以检测大型项目的哪个部分需要重新编译和执行用户定义的命令编译或是其他需要的操作。它也广泛用作构建工具， 您可以在其中指定要运行的一组命令，这些命令本来是用来在命令行上编写的，通常是重复多次执行。下面是本文其余部分的主要内容。

为了这篇文章的目的，我们假设我们正在从事 GO 项目`stringifier` , 而且将会编写一个 Makefile, 也称为 Makefile。

Build and Run
-------------

Go 程序的这两个指令使用的相当频繁, 所以添加这些目录至我们的`Makefile`:

```
build: 
    go build -o stringifier main.go

run:
    go run -race main.go


```

我在运行命令中添加了`-race`标志，方便它在运行时在 Go 代码中检测到`race`情况。

Cleaning and DRYing
-------------------

构建二进制文件并运行应用程序后，一切正常, 确保我们在执行其他任何操作之前先清理二进制文件。我们更新`Makefile`应该看起来像这样：

```
build:
	go build -o stringifier main.go

run:
	go run -race main.go

clean:
	go clean



```

我们有两点可以改进，

1.  我们明确地重用了我们的应用程序名, 很自然我们的应用程序名称将在整个`Makefile`中的许多地方使用。
2.  每次构建应用之前，我们需要先执行`clean`的规则。

改进后的`Makefile`

```
APP=stringifier

build: clean
	go build -o ${APP} main.go

run:
	go run -race main.go

clean:
	go clean



```

**更新**: 这个例子之前使用的`rm -r ${APP}`， 但是感谢讲者的建议，现在使用`go clean`。

在顶部定义`Makefile`变量，当您调用`make`命令时 make 将自动引用它们，这样`Makefile`看起来就更整洁、规范了。

PHONY targets
-------------

**默认情况下**，如果一个前置条件或是目录文件已更改，`make`将执行规则。但是由于我们不依赖于`make`来检测文件更改的能力，因此我们会遇到潜在的麻烦。

假设我们的项目目录中有一个名为 build 的文件, 在这个场景下，当你执行`make build`, make 一定会检查文件 build 的更改，由于没有前置条件，因此将始终将`build`文件视为最新的，并且不会执行其规则定义的操作。

为了避免这个问题，你需要先知道`.PHONY` 特殊目录 (target) 是什么意思：特殊目标`.PHONY`的先决条件被视为 phony 目标（targets)。 当需要运行时，make 会无条件运行其规则，而不管该名称的文件是否存在或其最后修改时间是多少。

所以，你可以通过将目标（target）指定为特殊目标`.PHONY`的先决条件，将目标指定为`.PHONY`。

```
APP=stringifier


.PHONY: build
build: clean
	go build -o ${APP} main.go

.PHONY: run
run:
	go run -race main.go

.PHONY: clean
clean:
	go clean


```

现在你已将上述所有的`targets`指定为`phony`, 每次你调用任何`phony`目标（target) 时，make 将会执行相应的规则。你还可以一次将所有要指定为`phony`的目标指定为:

但是对于非常大的 Makefile，不建议这样做因为这可能导致歧义和无法读取。因此，首选方法是在规则定义之前显式设置`phony`目标（target）。

Recursive Make targets
----------------------

现在让我们假设我们在项目中使用的根目录中还有另一个模块`tokenizer`。现在我们的目录结构是这样的：

```
~/programming/stringifier
.
├── main.go
├── Makefile
└── tokenizer/
      ├── main.go
      └── Makefile


```

很自然，某些时候我们也想`build`和`test`我们的`tokenizer`模块。由于它是一个独立的模块也可能是一个独立的项目，在它的目录有如下内容的一个`Makefile`是很有必要的：

```
# ~/programming/stringifier/tokenizer/Makefile

APP=tokenizer

build:
	go build -o ${APP} main.go


```

现在只要您在`stringifier`项目的根目录中并且想要构建`tokenizer`应用程序，你不会想使用诸如`cd tokenizer && make build && cd -` 这样的易受攻击的命令行技巧，而具体的`Makefiles`的规则写在子目录中的方式。幸运的是，`make`可以帮助你解决这个问题。你可以使用`-C`标志和特殊的`${NAME}`变量在其他目录中调用`make targets`。下面是`stringifies`项目最初的 Makefile:

```
# ~/programming/stringifier/Makefile

APP=stringifier


.PHONY: build
build: clean
	go build -o ${APP} main.go

.PHONY: run
run:
	go run -race main.go

.PHONY: clean
clean:
	go clean

.PHONY: build-tokenizer
build-tokenizer:
	${MAKE} -C tokenizer build


```

现在只要你运行`make build-tokenizer`，`make`都将为您处理目录切换，并以更加可读和健壮的方式为您调用正确目录中的正确目标

Targets for Docker commands
---------------------------

现在您希望对应用程序进行容器化，然后为方便起见编写 make 目标，这是完全可以理解的。

你为 docker 命令定义了如下规则：

```
.PHONY: docker-build
docker-build: build
	docker build -t stringifier .
	docker tag stringifier stringifier:tag

.PHONY: docker-push
docker-push: docker-build
	docker push gcr.io/stringifier/stringifier-staging/stringifier:tag


```

docker 命令基本满足需要，但是还有改善的空间，

*   对于新手，你可以再次重用你的`${APP}`变量。
*   接下来，您想要更灵活并确保可以轻松控制将映像推送到哪里，无论是您的私人镜像仓库还是其他地方。
*   然后，您希望能够根据用户在命令行上的某些输入将镜像（image）分别推送到与预生产和生产环境有关的两个单独的镜像仓库中。
*   最后，像一个理智的开发人员一样，您想使用当前的 git commit sha 标记您的镜像（image）。 让我们基于这些问题重新修改下`Makefile`：

```
APP?=application
REGISTRY?=gcr.io/images
COMMIT_SHA=$(shell git rev-parse --short HEAD)

.PHONY: docker-build
docker-build: build
	docker build -t ${APP} .
	docker tag ${APP} ${APP}:${COMMIT_SHA}

.PHONY: docker-push
docker-push: check-environment docker-build
	docker push ${REGISTRY}/${ENV}/${APP}:${COMMIT_SHA}

check-environment:
ifndef ENV
    $(error ENV not set, allowed values - `staging` or `production`)
endif



```

现在，让我们回顾下上面的更改：

*   你开始为应用程序名称，镜像名称, 提交 sha 使用变量。
*   您使用特殊的 shell 函数生成了 commit sha。 在这种情况下，您运行了 git 命令，该命令返回了简短的提交 sha，并将其分配给变量`${COMMIT_SHA}`，以便稍后在 Makefile 中使用。
*   您添加了一个新的规则`check-environment`，该环境使用 make 条件检查在调用 make 时是否指定了`ENV`变量，这有助于区分预生产及生产环境。

`check-environment`的规则如下：

```
check-environment:
ifndef ENV
    $(error ENV not set, allowed values - `staging` or `production`)
endif


```

使用`ifndef`指令检查变量 ENV 是否为空值，如果存在，则使用另一个 make 的提供内置函数，如果出错了，将会在关键字之后抛出具体的错误消息。

```
$ make docker-push
Makefile:33: *** ENV not set, allowed values - `staging` or `production`.  Stop.

$ ENV=staging make docker-push
Success


```

本质上，您要确保 docker-push 目标具有安全保障，该保障可检查调用目标的用户是否已为 ENV 变量指定值。

Help target
-----------

一个新成员加入了该项目并想知道 Makefile 中所有规则的作用，为帮助它们您可以添加一个新目标 (target)，该目标将打印所有目标名称以及它们作用的简短描述:

```
.PHONY: build
## build: build the application
build: clean
    @echo "Building..."
    @go build -o ${APP} main.go

.PHONY: run
## run: runs go run main.go
run:
	go run -race main.go

.PHONY: clean
## clean: cleans the binary
clean:
    @echo "Cleaning"
    @go clean

.PHONY: setup
## setup: setup go modules
setup:
	@go mod init \
		&& go mod tidy \
		&& go mod vendor
	
.PHONY: help
## help: prints this help message
help:
	@echo "Usage: \n"
	@sed -n 's/^##//p' ${MAKEFILE_LIST} | column -t -s ':' |  sed -e 's/^/ /'


```

你先注意下最后一条规则，`help` 在这里，您只是使用一些 sed 魔术来解析和在命令行上打印。 但是要做到这一点，您必要在每条规则之前写了目标名称和简短描述作为注释。 注意另一个特殊变量 $ {MAKEFILE_LIST}，它是您所引用的所有 Makefile 的列表，在本例中仅是 Makefile。

您会将文件 Makefile 作为输入传递给 sed 命令，该命令将解析所有帮助注释并以表格格式将其打印到 stdout，以便于阅读。 上一个代码段的`help`目标的输出如下所示：

```
$ make help
Usage:
	build             Build the application
	clean             cleans the binary
	run               runs go run main.go
	docker-build      builds docker image
	docker-push       pushes the docker image
	setup             set up modules
	help              prints this help message


```

这些消息很有帮助，对于其他人甚至有时对自己都是一个不错的提示。

Conclusion 结论
-------------

Make 是一个简单但可高度配置的工具。 在本文中，您遍历了 make 提供的许多配置和功能，从而为 Go 应用程序编写了有效而高效的 Makefile。

下面是完整的 Makefile，其中添加了一些琐碎的规则和变量：

```
GO111MODULES=on
APP?=stringifier
REGISTRY?=gcr.io/images
COMMIT_SHA=$(shell git rev-parse --short HEAD)



.PHONY: build
## build: build the application
build: clean
    @echo "Building..."
    @go build -o ${APP} main.go

.PHONY: run
## run: runs go run main.go
run:
	go run -race main.go

.PHONY: clean
## clean: cleans the binary
clean:
    @echo "Cleaning"
    @go clean

.PHONY: test
## test: runs go test with default values
test:
	go test -v -count=1 -race ./...


.PHONY: build-tokenizer
## build-tokenizer: build the tokenizer application
build-tokenizer:
	${MAKE} -c tokenizer build

.PHONY: setup
## setup: setup go modules
setup:
	@go mod init \
		&& go mod tidy \
		&& go mod vendor
	
# helper rule for deployment
check-environment:
ifndef ENV
    $(error ENV not set, allowed values - `staging` or `production`)
endif

.PHONY: docker-build
## docker-build: builds the stringifier docker image to registry
docker-build: build
	docker build -t ${APP}:${COMMIT_SHA} .

.PHONY: docker-push
## docker-push: pushes the stringifier docker image to registry
docker-push: check-environment docker-build
	docker push ${REGISTRY}/${ENV}/${APP}:${COMMIT_SHA}

.PHONY: help
## help: Prints this help message
help:
	@echo "Usage: \n"
	@sed -n 's/^##//p' ${MAKEFILE_LIST} | column -t -s ':' |  sed -e 's/^/ /'


```

Refer to:

1.  [https://danishpraka.sh/2019/12/07/using-makefiles-for-go.html](https://danishpraka.sh/2019/12/07/using-makefiles-for-go.html)
2.  [https://www.gnu.org/software/make/manual/html_node/Special-Targets.html#Special-Targets](https://www.gnu.org/software/make/manual/html_node/Special-Targets.html#Special-Targets)

Author: Colynn.Liu

Link: [](https://colynn.github.io/2020-03-03-using_makefile)https://colynn.github.io/2020-03-03-using_makefile/

本文采用[知识共享署名 - 非商业性使用 4.0 国际许可协议](http://creativecommons.org/licenses/by-nc/4.0/)进行许可