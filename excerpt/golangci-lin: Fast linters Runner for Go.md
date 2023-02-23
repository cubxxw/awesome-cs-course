> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 [nsddd.top](https://nsddd.top/archives/golangci)

> 💡 Most installations of golangci-lint are performed for CI.

*   [author(Github)](https://github.com/golangci/golangci-lint)

* * *

static analysis
---------------

**💡 Most installations of `golangci-lint` are performed for CI.**

Static analysis, also called static code analysis, is a method of computer program [debugging](https://www.techtarget.com/searchsoftwarequality/definition/debugging) that is done by examining the [code](https://www.techtarget.com/whatis/definition/code) without [executing](https://www.techtarget.com/searchsecurity/definition/executable) the program. The process provides an understanding of the code structure and can help ensure that the code adheres to industry standards. Static analysis is used in software engineering by software development and [quality assurance](https://www.techtarget.com/searchsoftwarequality/definition/quality-assurance) teams. Automated tools can assist programmers and developers in carrying out static analysis. The software will scan all code in a project to check for vulnerabilities while validating the code.

**Static analysis is generally good at finding coding issues such as:**

*   Programming errors
*   Coding standard violations
*   Undefined values
*   [Syntax](https://www.techtarget.com/whatis/definition/syntax) violations
*   Security vulnerabilities

The static analysis process is also useful for addressing weaknesses in source code that [could lead to buffer overflows](https://www.techtarget.com/searchsecurity/tip/1048483/Buffer-overflow-attacks-How-do-they-work) – a common software vulnerability.

How is static analysis done?
----------------------------

The static analysis process is relatively simple, as long as it’s automated. Generally, static analysis occurs before software testing in early development. In the [DevOps](https://www.techtarget.com/searchitoperations/definition/DevOps) development practice, it will occur in the create phases.

Once the code is written, a static code analyzer should be run to look over the code. It will check against defined coding rules from standards or custom predefined rules. Once the code is run through the static code analyzer, the analyzer will have identified whether or not the code complies with the set rules. It is sometimes possible for the software to flag false positives, so it is important for someone to go through and dismiss any. Once false positives are waived, developers can begin to fix any apparent mistakes, generally starting from the most critical ones. Once the code issues are resolved, the code can move on to testing through execution.

Without having code testing tools, static analysis will take a lot of work, since humans will have to review the code and figure out how it will behave in [runtime](https://www.techtarget.com/searchsoftwarequality/definition/runtime) environments. Therefore, it’s a good idea to find a tool that automates the process. Getting rid of any lengthy processes will make for a more efficient work environment.

Types of static analysis
------------------------

There are several static analysis methods an organization could use, which include:

*   Control analysis – focuses on the control flow in a calling structure. For example, a control flow could be a process, function, method or in a subroutine.
*   Data analysis – makes sure defined data is properly used while also making sure data [objects](https://www.techtarget.com/searchapparchitecture/definition/object) are properly operating.
*   Fault/failure analysis – analyzes faults and failures in model components.
*   Interface analysis – verifies simulations to check the code and makes sure the interface fits into the model and simulation.

In a broader sense, with less official categorization, static analysis can be broken into formal, cosmetic, design properties, error checking and predictive categories. Formal meaning if the code is correct; cosmetic meaning if the code syncs up with style standards; design properties meaning the level of complexities; error checking which looks for code violations; and predictive, which asks how code will behave when run.

Github Action
-------------

**We recommend using [our GitHub Action](https://github.com/golangci/golangci-lint-action) for running `golangci-lint` in CI for GitHub projects. It’s [fast and uses smart caching](https://github.com/golangci/golangci-lint-action#performance) inside and it can be much faster than the simple binary installation.**

Also, the action creates GitHub annotations for found issues: you don’t need to dig into build log to see found by `golangci-lint` issues:

![](http://sm.nsddd.top/sm202302202307056.png)

### Other CI

It’s important to have reproducible CI: don’t start to fail all builds at the same time. With golangci-lint this can happen if you use option `--enable-all` and a new linter is added or even without `--enable-all` when one upstream linter is upgraded.

⚠️ **IMPORTANT**: It’s highly recommended installing a specific version of golangci-lint available on the [releases page](https://github.com/golangci/golangci-lint/releases).

Here is the recommended way to install golangci-lint v1.51.2:

It is advised that you periodically update the version of `golangci-lint` as the project is under active development and is constantly being improved. For any problems with `golangci-lint`, check out recent [GitHub issues](https://github.com/golangci/golangci-lint/issues) and update if needed.

local installation
------------------

*   [On my machine](https://golangci-lint.run/usage/install/#local-installation);
*   [On CI/CD systems](https://golangci-lint.run/usage/install/#ci-installation).

### Binaries

> On Windows, you can run the above commands with Git Bash, which comes with [Git for Windows](https://git-scm.com/download/win).

### Docker

Preserving cache between consecutive runs:

Colored output:

Quick Start
-----------

To run golangci-lint execute:

It’s an equivalent of executing:

You can choose which directories and files to analyze:

Directories are NOT analyzed recursively. To analyze them recursively append `/...` to their path.

GolangCI-Lint can be used with zero configuration. By default the following linters are enabled:

and the following linters are disabled by default:

Pass `-E/--enable` to enable linter and `-D/--disable` to disable:

Integrations
------------

*   [plugin](https://marketplace.visualstudio.com/items?item>vscode plug</a></li>
    </ul>
    <p>Recommended settings for VS Code are:</p>
    
    <p>Using it in an editor without <code>--fast</code> can freeze your editor. Golangci-lint automatically discovers <code>.golangci.yml</code> config for edited file: you don’t need to configure it in VS Code settings.</p>
    <p>There is a <a href=) for SublimeLinter.
    
    Linters
    -------
    
    To see a list of supported linters and which linters are enabled/disabled:
    
    Links
    -----
    
    *   [https://www.techtarget.com/whatis/definition/static-analysis-static-code-analysis](https://www.techtarget.com/whatis/definition/static-analysis-static-code-analysis)
    *   [https://time.geekbang.org/column/article/390401](https://time.geekbang.org/column/article/390401)
    *   [https://golangci-lint.run/usage/install](https://golangci-lint.run/usage/install)
    *   [https://github.com/golangci/golangci-lint](https://github.com/golangci/golangci-lint)