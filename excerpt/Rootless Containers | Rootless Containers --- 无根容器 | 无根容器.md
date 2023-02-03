> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 [rootlesscontaine.rs](https://rootlesscontaine.rs/)

> Rootless Containers

Rootless containers refers to the ability for an unprivileged user to create, run and otherwise manage containers. This term also includes the variety of tooling around containers that can also be run as an unprivileged user.

“Unprivileged user” in this context refers to a user who does not have any administrative rights, and is “not in the good graces of the administrator” (in other words, they do not have the ability to ask for more privileges to be granted to them, or for software packages to be installed).

Pros:

*   Can mitigate potential container-breakout vulnerabilities (Not a panacea, of course)
*   Friendly to shared machines, especially in HPC environments

Cons:

*   Complexity

See also [Caveats and Future work](https://rootlesscontaine.rs/caveats).

What are Rootless Containers and what are not?[](#what-are-rootless-containers-and-what-are-not)
------------------------------------------------------------------------------------------------

When we say Rootless Containers, it means running the entire container runtime as well as the containers without the root privileges.

Even when the containers are running as non-root users, when the runtime is still running as root, we don’t call them Rootless Containers.

While we allow using setuid (and/or setcap) binaries for some essential configurations such as [`newuidmap`](https://rootlesscontaine.rs/how-it-works/userns), when a larger part of the runtime is running with setuid, we don’t call it Rootless Containers. We also don’t call it Rootless Containers when the root user inside a container is mapped to the root user outside the container.

### Examples of Rootless Containers[](#examples-of-rootless-containers)

*   [Docker rootless-mode](https://rootlesscontaine.rs/getting-started/docker)
*   [Podman rootless-mode](https://rootlesscontaine.rs/getting-started/podman)
*   [BuildKit rootless-mode](https://rootlesscontaine.rs/getting-started/buildkit)
*   [LXC unprivileged-mode](https://rootlesscontaine.rs/getting-started/lxc)
*   [Apptainer userns-mode or fakeroot-mode](https://rootlesscontaine.rs/getting-started/apptainer)

Click the links for tutorials.

### _Non_-examples of Rootless Containers[](#non-examples-of-rootless-containers)

*   Allowing a non-root user to access to `/var/run/docker.sock`, by adding the user to `docker` group (`sudo usermod -aG docker somebody`)
*   `docker run --user somebody`
*   Docker userns-remap mode (`dockerd --userns-remap`)
*   Podman userns-remap mode (`podman run --uidmap`)
*   Kaniko
*   Makisu
*   LXD unprivileged-mode
*   Apptainer setuid-mode