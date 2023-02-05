> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 [projectatomic.io](https://projectatomic.io/blog/2015/08/why-we-dont-let-non-root-users-run-docker-in-centos-fedora-or-rhel/)

> I often get bug reports from users asking why can’t I use `docker` as a non root user, by default? D......

I often get bug reports from users asking _why can’t I use `docker` as a non root user, by default?_

Docker has the ability to change the group ownership of the /run/docker.socket to have group permission of 660, with the `group` ownership the docker group. This would allow users added to the docker group to be able to run docker containers without having to execute `sudo` or `su` to become root. Sounds great…  

```
ls -l /var/run/docker.sock 
srw-rw----. 1 root docker 0 Aug  3 13:02 /var/run/docker.sock


```

**But** on Red Hat Enterprise Linux (RHEL), Fedora, and CentOS we prefer to have the docker.socket set to:

```
ls -l /var/run/docker.sock 
srw-rw----. 1 root root 0 Aug  3 13:02 /var/run/docker.sock


```

Why is that? Simple: if a user can talk to the `docker` socket, they can execute the following command:

```
docker run -ti --privileged -v /:/host fedora chroot /host


```

Giving them full root access to the host system. This is similar to giving them the following in sudo.

```
grep dwalsh /etc/sudoers
dwalsh  ALL=(ALL)   NOPASSWD: ALL


```

Which would allow them to run `sudo sh` and get the same access. But there is one big flaw with this. Docker has no auditing or logging built in, while `sudo` does.

Docker currently records events but the events disappear when the docker daemon is restarted. Docker does not currently do any auditing.

From a security perspective, Red Hat has expressed concerns with enabling access to the docker daemon from non-root users, absent auditing and proper logging. We’ve implemented those controls in [PR14446](https://github.com/docker/docker/pull/14446) though it depends on an authentication framework which is still being discussed. Until we can implement proper auditing and logging, we recommend implementing sudo rules to permit access to the docker daemon. That allows `sudo` to provide logging and audit.

### Setting up sudo

If you want to give `docker` access to non-root users we recommend setting up sudo. Here is a short guide on how to do this.

Add an entry like the following to `/etc/sudoers`.

```
grep dwalsh /etc/sudoers
dwalsh        ALL=(ALL)       NOPASSWD: /usr/bin/docker


```

This will allow the specified user to run docker as root, without a password.

**Note:** I do _not_ recommend using `NOPASSWD`, this would allow any process on your system to become root. If you require the password, the user needs to specify his password when running the docker command, making the system a bit more secure. If a password is required, `sudo` gives you a five minute grace period to run docker again without entering the password.

Now, set up an alias for running the `docker` command:

```
alias docker="sudo /usr/bin/docker"


```

Now when the user executes the `docker` command as non-root it will be allowed and get proper logging.

```
docker run -ti --privileged -v /:/host fedora chroot /host


```

Look at the journal or `/var/log/messages`.

```
journalctl -b | grep docker.*privileged
Aug 04 09:02:56 dhcp-10-19-62-196.boston.devel.redhat.com sudo[23422]:   dwalsh : TTY=pts/3 ; PWD=/home/dwalsh/docker/src/github.com/docker/docker ; USER=root ; COMMAND=/usr/bin/docker run -ti --privileged -v /:/host fedora chroot /host


```

Look at the audit log:

```
ausearch -m USER_ROLE_CHANGE -i
type=USER_ROLE_CHANGE msg=audit(08/04/2015 09:02:56.514:1460) : pid=23423 uid=root auid=dwalsh ses=1 subj=unconfined_u:unconfined_r:unconfined_t:s0-s0:c0.c1023 msg='newrole: old-context=unconfined_u:unconfined_r:unconfined_t:s0-s0:c0.c1023
new-context=unconfined_u:unconfined_r:unconfined_t:s0-s0:c0.c1023 exe=/usr/bin/sudo hostname=? addr=? terminal=/dev/pts/3 res=success'


```

#### Better Security

Better yet, if you wanted to only allow a user to access a particular container, you could write a simple script:

```
cat /usr/bin/docker-fedora
#!/bin/sh
docker run -ti --rm fedora /bin/sh


```

After writing the script, configure sudoers to run it:

```
grep dwalsh /etc/sudoers
dwalsh        ALL=(ALL)       NOPASSWD: /usr/bin/docker-fedora


```

This user would only be able to run the fedora container, without privileges.

### Authentication

We have other patches that we are working on to make the docker daemon more secure, including authentication. We have an ongoing discussion [in issue #13697 “Adding Kerberos support to Docker](https://github.com/docker/docker/issues/13697).

### Authorization

We are also developing a proposal to add Authorization/RBAC (Roles Based Access Control) to docker, to allow administrators to specify which users are allowed to do which activity on which containers/images.

That proposal is [on GitHub](https://github.com/rhatdan/docker-rbac) if you’d like to review it, add comments, or suggestions.

### Conclusion

We believe the security of managing the docker daemon needs a lot of improvement, before we can think of opening up access to non-privileged users directly. Until these fixes are made `sudo` is the best option. We’re working on better options, but for the time being we strongly recommend using `sudo`.