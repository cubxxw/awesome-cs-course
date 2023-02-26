> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 [en.wikipedia.org](https://en.wikipedia.org/wiki/Linux_namespaces) Feature of the Linux kernel that partitions kernel resources

.

Namespaces are a fundamental aspect of [containers](/wiki/Linux_containers "Linux containers") in Linux.

The term "namespace" is often used for a type of namespace (e.g. process ID) as well as for a particular space of names.

A Linux system starts out with a single namespace of each type, used by all processes. Processes can create additional namespaces and also join different namespaces.

History
-------

Linux namespaces were inspired by the wider namespace functionality used heavily throughout [Plan 9 from Bell Labs](/wiki/Plan_9_from_Bell_Labs "Plan 9 from Bell Labs").[[1]](#cite_note-1)

The Linux Namespaces originated in 2002 in the 2.4.19 kernel with work on the mount namespace kind. Additional namespaces were added beginning in 2006[[2]](#cite_note-pid_namespaces_commit-2) and continuing into the future.

Adequate [containers](/wiki/Container_(virtualization) "Container (virtualization)") support functionality was finished in kernel version 3.8 with the introduction of User namespaces.[[3]](#cite_note-3)

Namespace kinds
---------------

Since [kernel](/wiki/Kernel_(operating_system) "Kernel (operating system)") version 5.6, there are 8 kinds of namespaces. Namespace functionality is the same across all kinds: each process is associated with a namespace and can only see or use the resources associated with that namespace, and descendant namespaces where applicable. This way each process (or process group thereof) can have a unique view on the resources. Which resource is isolated depends on the kind of namespace that has been created for a given process group.

### Mount (mnt)

Mount namespaces control [mount points](/wiki/Mount_point "Mount point"). Upon creation the mounts from the current mount namespace are copied to the new namespace, but mount points created afterwards do not propagate between namespaces (using shared subtrees, it is possible to propagate mount points between namespaces[[4]](#cite_note-shared_subtrees-4)).

The clone flag used to create a new namespace of this type is CLONE_NEWNS - short for "NEW NameSpace". This term is not descriptive (it does not tell which kind of namespace is to be created) because mount namespaces were the first kind of namespace and designers did not anticipate there being any others.

### Process ID (pid)

The [PID](/wiki/Process_identifier "Process identifier") namespace provides processes with an independent set of process IDs (PIDs) from other namespaces. PID namespaces are nested, meaning when a new process is created it will have a PID for each namespace from its current namespace up to the initial PID namespace. Hence the initial PID namespace is able to see all processes, albeit with different PIDs than other namespaces will see processes with.

The first process created in a PID namespace is assigned the process ID number 1 and receives most of the same special treatment as the normal [init](/wiki/Init "Init") process, most notably that [orphaned processes](/wiki/Orphan_process "Orphan process") within the namespace are attached to it. This also means that the termination of this PID 1 process will immediately terminate all processes in its PID namespace and any descendants.[[5]](#cite_note-pid_namespace_description-5)

### Network (net)

Network namespaces virtualize the [network stack](/wiki/Network_stack "Network stack"). On creation a network namespace contains only a [loopback](/wiki/Localhost "Localhost") interface.

Each network interface (physical or virtual) is present in exactly 1 namespace and can be moved between namespaces.

Each namespace will have a private set of [IP addresses](/wiki/IP_address "IP address"), its own [routing table](/wiki/Routing_table "Routing table"), [socket](/wiki/Network_socket "Network socket") listing, connection tracking table, [firewall](/wiki/Firewall_(computing) "Firewall (computing)"), and other network-related resources.

Destroying a network namespace destroys any virtual interfaces within it and moves any physical interfaces within it back to the initial network namespace.

### Inter-process Communication (ipc)

IPC namespaces isolate processes from [SysV](/wiki/UNIX_System_V "UNIX System V") style inter-process communication. This prevents processes in different IPC namespaces from using, for example, the SHM family of functions to establish a range of shared memory between the two processes. Instead each process will be able to use the same identifiers for a shared memory region and produce two such distinct regions.

### UTS

UTS (UNIX [Time-Sharing](/wiki/Time-sharing "Time-sharing")) namespaces allow a single system to appear to have different [host](/wiki/Hostname "Hostname") and [domain names](/wiki/Domain_name "Domain name") to different processes. "When a process creates a new UTS namespace ... the hostname and domain of the new UTS namespace are copied from the corresponding values in the caller's UTS namespace."[[6]](#cite_note-6)

### User ID (user)

User namespaces are a feature to provide both privilege isolation and user identification segregation across multiple sets of processes available since kernel 3.8.[[7]](#cite_note-7) With administrative assistance it is possible to build a container with seeming administrative rights without actually giving elevated privileges to user processes. Like the PID namespace, user namespaces are nested and each new user namespace is considered to be a child of the user namespace that created it.

A user namespace contains a mapping table converting user IDs from the container's point of view to the system's point of view. This allows, for example, the [root](/wiki/Superuser "Superuser") user to have user id 0 in the container but is actually treated as user id 1,400,000 by the system for ownership checks. A similar table is used for group id mappings and ownership checks.

To facilitate privilege isolation of administrative actions, each namespace type is considered owned by a user namespace based on the active user namespace at the moment of creation. A user with administrative privileges in the appropriate user namespace will be allowed to perform administrative actions within that other namespace type. For example, if a process has administrative permission to change the IP address of a network interface, it may do so as long as its own user namespace is the same as (or ancestor of) the user namespace that owns the network namespace. Hence the initial user namespace has administrative control over all namespace types in the system.[[8]](#cite_note-user_namespace_description-8)

### Control group (cgroup) Namespace

The [cgroup](/wiki/Cgroup "Cgroup") namespace type hides the identity of the [control group](/wiki/Cgroups "Cgroups") of which process is a member. A process in such a namespace, checking which control group any process is part of, would see a path that is actually relative to the control group set at creation time, hiding its true control group position and identity. This namespace type has existed since March 2016 in Linux 4.6.[[9]](#cite_note-cgroup_namespace_pull_request-9)[[10]](#cite_note-cgroup_namespace_merge-10)

### Time Namespace

The time namespace allows processes to see different system times in a way similar to the UTS namespace. It was proposed in 2018 and landed on Linux 5.6, which was released in March 2020.[[11]](#cite_note-11)

### Proposed namespaces

#### syslog namespace

The syslog namespace was proposed by Rui Xiang, an engineer at [Huawei](/wiki/Huawei "Huawei"), but wasn't merged into the linux kernel.[[12]](#cite_note-12) [systemd](/wiki/Systemd "Systemd") implemented a similar feature called “journal namespace” in February 2020.[[13]](#cite_note-13)

Implementation details
----------------------

The kernel assigns each process a symbolic link per namespace kind in `/proc/<pid>/ns/`. The inode number pointed to by this symlink is the same for each process in this namespace. This uniquely identifies each namespace by the inode number pointed to by one of its symlinks.

Reading the symlink via readlink returns a string containing the namespace kind name and the inode number of the namespace.

### Syscalls

Three syscalls can directly manipulate namespaces:

*   clone, flags to specify which new namespace the new process should be migrated to.
*   unshare, allows a process (or thread) to disassociate parts of its execution context that are currently being shared with other processes (or threads)
*   setns, enters the namespace specified by a file descriptor.

### Destruction

If a namespace is no longer referenced, it will be deleted, the handling of the contained resource depends on the namespace kind. Namespaces can be referenced in three ways:

1.  by a process belonging to the namespace
2.  by an open filedescriptor to the namespace's file (`/proc/<pid>/ns/<ns-kind>`)
3.  a bind mount of the namespace's file (`/proc/<pid>/ns/<ns-kind>`)

Adoption
--------

Various container software use Linux namespaces in combination with [cgroups](/wiki/Cgroups "Cgroups") to isolate their processes, including [Docker](/wiki/Docker_(software) "Docker (software)")[[14]](#cite_note-docker-14) and [LXC](/wiki/LXC "LXC").

Other applications, such as [Google Chrome](/wiki/Google_Chrome "Google Chrome") make use of namespaces to isolate its own processes which are at risk from attack on the internet.[[15]](#cite_note-google-15)

There is also an unshare wrapper in [util-linux](/wiki/Util-linux "Util-linux"). An example of its use is:

```
SHELL=/bin/sh unshare --map-root-user --fork --pid chroot "${chrootdir}" "$@"


```

References
----------

1.  **[^](#cite_ref-1)**
2.  **[^](#cite_ref-pid_namespaces_commit_2-0)** ["Linux kernel source tree"](https://git.kernel.org/cgit/linux/kernel/git/torvalds/linux.git/commit/?id=071df104f808b8195c40643dcb4d060681742e29). kernel.org. 2016-10-02.
3.  **[^](#cite_ref-3)** ["Namespaces in operation, part 5: User namespaces [LWN.net]"](https://lwn.net/Articles/532593/).
4.  **[^](#cite_ref-shared_subtrees_4-0)** ["Documentation/filesystems/sharedsubtree.txt"](https://www.kernel.org/doc/Documentation/filesystems/sharedsubtree.txt). 2016-02-25. Retrieved 2017-03-06.
5.  **[^](#cite_ref-pid_namespace_description_5-0)** ["Namespaces in operation, part 3: PID namespaces"](https://lwn.net/Articles/531419/). lwn.net. 2013-01-16.
6.  **[^](#cite_ref-6)** ["uts_namespaces(7) - Linux manual page"](https://www.man7.org/linux/man-pages/man7/uts_namespaces.7.html). _www.man7.org_. Retrieved 2021-02-16.
7.  **[^](#cite_ref-7)** ["Namespaces in operation, part 5: User namespaces [LWN.net]"](https://lwn.net/Articles/532593/).
8.  **[^](#cite_ref-user_namespace_description_8-0)** ["Namespaces in operation, part 5: User namespaces"](https://lwn.net/Articles/532593/). lwn.net. 2013-02-27.
9.  **[^](#cite_ref-cgroup_namespace_pull_request_9-0)** Heo, Tejun (2016-03-18). ["[GIT PULL] cgroup namespace support for v4.6-rc1"](https://lkml.org/lkml/2016/3/18/564). _lkml_ (Mailing list).
10.  **[^](#cite_ref-cgroup_namespace_merge_10-0)** [Torvalds, Linus](/wiki/Linus_Torvalds "Linus Torvalds") (2016-03-26). ["Linux 4.6-rc1"](https://lkml.org/lkml/2016/3/26/132). _lkml_ (Mailing list).
11.  **[^](#cite_ref-11)** ["It's Finally Time: The Time Namespace Support Has Been Added To The Linux 5.6 Kernel - Phoronix"](https://www.phoronix.com/scan.php?page=news_item&px=Time-Namespace-In-Linux-5.6). _www.phoronix.com_. Retrieved 2020-03-30.
12.  **[^](#cite_ref-12)** ["Add namespace support for syslog [LWN.net]"](https://lwn.net/Articles/562389/). _lwn.net_. Retrieved 2022-07-11.
13.  **[^](#cite_ref-13)** ["journal: add concept of"journal namespaces"by poettering · Pull Request #14178 · systemd/systemd"](https://github.com/systemd/systemd/pull/14178). _GitHub_. Retrieved 2022-07-11.
14.  **[^](#cite_ref-docker_14-0)** ["Docker security"](https://docs.docker.com/engine/security/security/). docker.com. Retrieved 2016-03-24.
15.  **[^](#cite_ref-google_15-0)** ["Chromium Linux Sandboxing"](https://chromium.googlesource.com/chromium/src.git/+/master/docs/linux_sandboxing.md). Retrieved 2019-12-19.

External links
--------------

*   [namespaces manpage](http://man7.org/linux/man-pages/man7/namespaces.7.html)
*   [Namespaces — The Linux Kernel documentation](https://www.kernel.org/doc/html/latest/admin-guide/namespaces/index.html)
*   [Linux kernel Namespaces and cgroups by Rami Rosen](http://www.haifux.org/lectures/299/netLec7.pdf)
*   [Namespaces and cgroups, the basis of Linux containers (including cgroups v2) - slides of a talk by Rami Rosen, Netdev 1.1, Seville, Spain (2016)](http://www.netdevconf.org/1.1/proceedings/slides/rosen-namespaces-cgroups-lxc.pdf)
*   [Containers and Namespaces in the Linux Kernel by Kir Kolyshkin](https://events.static.linuxfound.org/slides/lfcs2010_kolyshkin.pdf)

*   [Adeos](/wiki/Adaptive_Domain_Environment_for_Operating_Systems "Adaptive Domain Environment for Operating Systems")
*   [CP/CMS](/wiki/CP/CMS "CP/CMS")
*   [Hyper-V](/wiki/Hyper-V "Hyper-V")
*   [KVM](/wiki/Kernel-based_Virtual_Machine "Kernel-based Virtual Machine")
    *   [oVirt](/wiki/OVirt "OVirt")
    *   [Red Hat Virtualization](/wiki/Red_Hat_Virtualization "Red Hat Virtualization")
*   [LDoms / Oracle VM Server for SPARC](/wiki/Oracle_VM_Server_for_SPARC "Oracle VM Server for SPARC")
*   [Logical partition](/wiki/Logical_partition "Logical partition") (LPAR)
*   [LynxSecure](/wiki/LynxSecure "LynxSecure")
*   [PikeOS](/wiki/PikeOS "PikeOS")
*   [Proxmox VE](/wiki/Proxmox_Virtual_Environment "Proxmox Virtual Environment")
*   [QNX](/wiki/QNX "QNX")
*   [SIMMON](/wiki/SIMMON "SIMMON")
*   [VMware ESXi](/wiki/VMware_ESXi "VMware ESXi")
    *   [VMware vSphere](/wiki/VMware_vSphere "VMware vSphere")
    *   [vCloud](/wiki/VCloud_Air "VCloud Air")
*   [VMware Infrastructure](/wiki/VMware_Infrastructure "VMware Infrastructure")
*   [Xen](/wiki/Xen "Xen")
    *   [Oracle VM Server for x86](/wiki/Oracle_VM_Server_for_x86 "Oracle VM Server for x86")
    *   [XenServer](/wiki/XenServer "XenServer")
*   [XtratuM](/wiki/XtratuM "XtratuM")
*   [z/VM](/wiki/Z/VM "Z/VM")

Hosted<table><tbody><tr><th scope="row">Specialized</th><td><ul><li><a href="/wiki/Basilisk_II" title="Basilisk II">Basilisk II</a></li><li><a href="/wiki/Bochs" title="Bochs">Bochs</a></li><li><a href="/wiki/Cooperative_Linux" title="Cooperative Linux">Cooperative Linux</a></li><li><a href="/wiki/DOSBox" title="DOSBox">DOSBox</a></li><li><a href="/wiki/DOSEMU" title="DOSEMU">DOSEMU</a></li><li><a href="/wiki/PCem" title="PCem">PCem</a></li><li><a href="/wiki/PikeOS" title="PikeOS">PikeOS</a></li><li><a href="/wiki/SheepShaver" title="SheepShaver">SheepShaver</a></li><li><a href="/wiki/SIMH" title="SIMH">SIMH</a></li><li><a href="/wiki/Windows_on_Windows" title="Windows on Windows">Windows on Windows</a><ul><li><a href="/wiki/Virtual_DOS_machine" title="Virtual DOS machine">Virtual DOS machine</a></li></ul></li><li><a href="/wiki/Win4Lin" title="Win4Lin">Win4Lin</a></li></ul></td></tr><tr><th scope="row">Independent</th><td><ul><li><a href="/wiki/Bhyve" title="Bhyve">bhyve</a></li><li><a href="/wiki/Microsoft_Virtual_Server" title="Microsoft Virtual Server">Microsoft Virtual Server</a></li><li><a href="/wiki/Parallels_Workstation" title="Parallels Workstation">Parallels Workstation</a> (<a href="/wiki/Parallels_Workstation_Extreme" title="Parallels Workstation Extreme">Extreme</a>)</li><li><a href="/wiki/Parallels_Desktop_for_Mac" title="Parallels Desktop for Mac">Parallels Desktop for Mac</a></li><li><a href="/wiki/Parallels_Server_for_Mac" title="Parallels Server for Mac">Parallels Server for Mac</a></li><li><a href="/wiki/PearPC" title="PearPC">PearPC</a></li><li><a href="/wiki/QEMU" title="QEMU">QEMU</a></li><li><a href="/wiki/VirtualBox" title="VirtualBox">VirtualBox</a></li><li><a href="/wiki/Virtual_Iron" title="Virtual Iron">Virtual Iron</a></li><li><a href="/wiki/VMware_Fusion" title="VMware Fusion">VMware Fusion</a></li><li><a href="/wiki/VMware_Server" title="VMware Server">VMware Server</a></li><li><a href="/wiki/VMware_Workstation" title="VMware Workstation">VMware Workstation</a> (<a href="/wiki/VMware_Workstation_Player" title="VMware Workstation Player">Player</a>)</li><li><a href="/wiki/Windows_Virtual_PC" title="Windows Virtual PC">Windows Virtual PC</a></li></ul></td></tr></tbody></table>Tools

*   [Ganeti](/wiki/Ganeti "Ganeti")
*   [System Center Virtual Machine Manager](/wiki/System_Center_Virtual_Machine_Manager "System Center Virtual Machine Manager")
*   [Virtual Machine Manager](/wiki/Virtual_Machine_Manager "Virtual Machine Manager")

[Operating  
system](/wiki/OS-level_virtualization "OS-level virtualization")<table><tbody><tr><th scope="row">OS containers</th><td><ul><li><a href="/wiki/FreeBSD_jail" title="FreeBSD jail">FreeBSD jail</a></li><li><a href="/wiki/ICore_Virtual_Accounts" title="ICore Virtual Accounts">iCore Virtual Accounts</a></li><li><a href="/wiki/Linux-VServer" title="Linux-VServer">Linux-VServer</a></li><li><a href="/wiki/LXC" title="LXC">LXC</a></li><li><a href="/wiki/OpenVZ" title="OpenVZ">OpenVZ</a></li><li><a href="/wiki/Solaris_Containers" title="Solaris Containers">Solaris Containers</a></li><li><a href="/wiki/Virtuozzo_(company)#Software" title="Virtuozzo (company)">Virtuozzo</a></li><li><a href="/wiki/Workload_Partitions" title="Workload Partitions">Workload Partitions</a></li></ul></td></tr><tr><th scope="row">Application containers</th><td><ul><li><a href="/wiki/Docker_(software)" title="Docker (software)">Docker</a></li><li><a href="/wiki/Lmctfy" title="Lmctfy">lmctfy</a></li><li><a href="/wiki/Container_Linux#Overview" title="Container Linux">rkt</a></li></ul></td></tr><tr><th scope="row">Virtual kernel architectures</th><td><ul><li><a href="/wiki/Rump_kernel" title="Rump kernel">Rump kernel</a></li><li><a href="/wiki/User-mode_Linux" title="User-mode Linux">User-mode Linux</a></li><li><a href="/wiki/Vkernel" title="Vkernel">vkernel</a></li></ul></td></tr><tr><th scope="row">Related kernel features</th><td><ul><li><a href="/wiki/Solaris_Containers#Branded_zones" title="Solaris Containers">BrandZ</a></li><li><a href="/wiki/Cgroups" title="Cgroups">cgroups</a></li><li><a href="/wiki/Chroot" title="Chroot">chroot</a></li><li><a>namespaces</a></li><li><a href="/wiki/Seccomp" title="Seccomp">seccomp</a></li></ul></td></tr><tr><th scope="row">Orchestration</th><td><ul><li><a href="/wiki/Amazon_Web_Services" title="Amazon Web Services">Amazon ECS</a></li><li><a href="/wiki/Kubernetes" title="Kubernetes">Kubernetes</a></li><li><a href="/wiki/OpenShift" title="OpenShift">OpenShift</a></li></ul></td></tr></tbody></table>[Desktop](/wiki/Desktop_virtualization "Desktop virtualization")

*   [Citrix Virtual Apps](/wiki/Citrix_Virtual_Apps "Citrix Virtual Apps")
*   [Citrix Virtual Desktops](/wiki/Citrix_Virtual_Desktops "Citrix Virtual Desktops")
*   [Remote Desktop Services](/wiki/Remote_Desktop_Services "Remote Desktop Services")
*   [VMware Horizon](/wiki/VMware_Horizon "VMware Horizon")

[Application](/wiki/Application_virtualization "Application virtualization")

*   [Ceedo](/wiki/Ceedo "Ceedo")
*   [Citrix Virtual Apps](/wiki/Citrix_Virtual_Apps "Citrix Virtual Apps")
*   [Dalvik](/wiki/Dalvik_(software) "Dalvik (software)")
*   [InstallFree](/wiki/InstallFree "InstallFree")
*   [Microsoft App-V](/wiki/Microsoft_App-V "Microsoft App-V")
*   [Remote Desktop Services](/wiki/Remote_Desktop_Services "Remote Desktop Services")
*   [Symantec Workspace Virtualization](/wiki/Symantec_Workspace_Virtualization "Symantec Workspace Virtualization")
*   [Turbo](/wiki/Turbo_(software) "Turbo (software)")
*   [VMware ThinApp](/wiki/VMware_ThinApp "VMware ThinApp")
*   [ZeroVM](/wiki/ZeroVM "ZeroVM")

[Network](/wiki/Network_virtualization "Network virtualization")

*   [Distributed Overlay Virtual Ethernet](/wiki/Distributed_Overlay_Virtual_Ethernet "Distributed Overlay Virtual Ethernet") (DOVE)
*   [Ethernet VPN](/wiki/Ethernet_VPN "Ethernet VPN") (EVPN)
*   [NVGRE](/wiki/Network_Virtualization_using_Generic_Routing_Encapsulation "Network Virtualization using Generic Routing Encapsulation")
*   [Open vSwitch](/wiki/Open_vSwitch "Open vSwitch")
*   [Virtual security switch](/wiki/Virtual_security_switch "Virtual security switch")
*   [Virtual Extensible LAN](/wiki/Virtual_Extensible_LAN "Virtual Extensible LAN") (VXLAN)

See also

*   [BlueStacks](/wiki/BlueStacks "BlueStacks")

See also: [List of emulators](/wiki/List_of_emulators "List of emulators"), [List of computer system emulators](/wiki/List_of_computer_system_emulators "List of computer system emulators")

<table><tbody><tr><th scope="col" colspan="2"><link rel="mw-deduplicated-inline-style" href="mw-data:TemplateStyles:r1129693374"><link rel="mw-deduplicated-inline-style" href="mw-data:TemplateStyles:r1063604349"><ul><li><a href="/wiki/Template:Linux_kernel" title="Template:Linux kernel"><abbr title="View this template">v</abbr></a></li><li><a href="/wiki/Template_talk:Linux_kernel" title="Template talk:Linux kernel"><abbr title="Discuss this template">t</abbr></a></li><li><a href="https://en.wikipedia.org/w/index.php?title=Template:Linux_kernel&amp;action=edit"><abbr title="Edit this template">e</abbr></a></li></ul><a href="/wiki/Linux_kernel" title="Linux kernel">Linux kernel</a></th></tr><tr><th scope="row">Organization</th><td><table><tbody><tr><th scope="row">Kernel</th><td><ul><li><a href="/wiki/Linux_Foundation" title="Linux Foundation">Linux Foundation</a></li><li><a href="/wiki/Linux_Mark_Institute" title="Linux Mark Institute">Linux Mark Institute</a></li><li><a href="/wiki/Linus%27s_law" title="Linus's law">Linus's law</a></li><li><a href="/wiki/Tanenbaum%E2%80%93Torvalds_debate" title="Tanenbaum–Torvalds debate">Tanenbaum–Torvalds debate</a></li><li><a href="/wiki/Tux_(mascot)" title="Tux (mascot)">Tux</a></li><li><a href="/wiki/SCO%E2%80%93Linux_disputes" title="SCO–Linux disputes">SCO disputes</a></li><li><a href="/wiki/Linaro" title="Linaro">Linaro</a></li><li><a href="/wiki/GNU_General_Public_License#Version_2" title="GNU General Public License">GNU GPL v2</a></li><li><a href="/wiki/Menuconfig" title="Menuconfig">menuconfig</a></li><li><a href="/wiki/List_of_Linux-supported_computer_architectures" title="List of Linux-supported computer architectures">Supported computer architectures</a></li><li><a href="/wiki/List_of_Linux_kernel_names" title="List of Linux kernel names">Kernel names</a></li><li><a href="/wiki/Criticism_of_Linux" title="Criticism of Linux">Criticism</a></li></ul></td></tr><tr><th scope="row">Support</th><td><ul><li>Developers<ul><li><i><a href="/wiki/The_Linux_Programming_Interface" title="The Linux Programming Interface">The Linux Programming Interface</a></i></li><li><a href="/wiki/Kernel.org" title="Kernel.org">kernel.org</a></li><li><a href="/wiki/Linux_kernel_mailing_list" title="Linux kernel mailing list">LKML</a></li><li><a href="/wiki/Linux_conference" title="Linux conference">Linux conferences</a></li></ul></li><li>Users<ul><li><a href="/wiki/Linux_user_group" title="Linux user group">Linux User Group (LUG)</a></li></ul></li></ul></td></tr></tbody></table></td></tr><tr><th scope="row">Technical</th><td><table><tbody><tr><th scope="row">Debugging</th><td><ul><li><a href="/wiki/CRIU" title="CRIU">CRIU</a></li><li><a href="/wiki/Ftrace" title="Ftrace">ftrace</a></li><li><a href="/wiki/Kdump_(Linux)" title="Kdump (Linux)">kdump</a></li><li><a href="/wiki/Linux_kernel_oops" title="Linux kernel oops">Linux kernel oops</a></li><li><a href="/wiki/SystemTap" title="SystemTap">SystemTap</a></li><li><a href="/wiki/Berkeley_Packet_Filter" title="Berkeley Packet Filter">BPF</a></li></ul></td></tr><tr><th scope="row"><a href="/wiki/Linux_startup_process" title="Linux startup process">Startup</a></th><td><ul><li><a href="/wiki/Vmlinux" title="Vmlinux">vmlinux</a></li><li><a href="/wiki/System.map" title="System.map">System.map</a></li><li><a href="/wiki/Dracut_(software)" title="Dracut (software)">dracut</a></li><li><a href="/wiki/Initrd" title="Initrd">initrd</a></li><li><a href="/wiki/Initramfs" title="Initramfs">initramfs</a></li></ul></td></tr><tr><th scope="row"><a href="/wiki/Linux_kernel_interfaces" title="Linux kernel interfaces">ABIs</a></th><td><ul><li><a href="/wiki/Linux_Standard_Base" title="Linux Standard Base">Linux Standard Base</a></li><li><a href="/wiki/X32_ABI" title="X32 ABI">x32 ABI</a></li></ul></td></tr><tr><th scope="row"><a href="/wiki/Linux_kernel_interfaces" title="Linux kernel interfaces">APIs</a></th><td><table><tbody><tr><th scope="row">Kernel</th><td><table><tbody><tr><th scope="row"><a href="/wiki/Linux_kernel_interfaces#SCI" title="Linux kernel interfaces">System Call<br>Interface</a></th><td><ul><li><a href="/wiki/POSIX" title="POSIX">POSIX</a><ul><li><a href="/wiki/Ioctl" title="Ioctl">ioctl</a></li><li><a href="/wiki/Select_(Unix)" title="Select (Unix)">select</a></li><li><a href="/wiki/Open_(system_call)" title="Open (system call)">open</a></li><li><a href="/wiki/Read_(system_call)" title="Read (system call)">read</a></li><li><a href="/wiki/Close_(system_call)" title="Close (system call)">close</a></li><li><a href="/wiki/Sync_(Unix)" title="Sync (Unix)">sync</a></li><li>…</li></ul></li><li><a href="/wiki/Linux_kernel_interfaces#Additions_to_POSIX" title="Linux kernel interfaces">Linux-only</a><ul><li><a href="/wiki/Futex" title="Futex">futex</a></li><li><a href="/wiki/Epoll" title="Epoll">epoll</a></li><li><a href="/wiki/Splice_(system_call)" title="Splice (system call)">splice</a></li><li><a href="/wiki/Dnotify" title="Dnotify">dnotify</a></li><li><a href="/wiki/Inotify" title="Inotify">inotify</a></li><li><a href="/wiki/Readahead" title="Readahead">readahead</a></li><li>…</li></ul></li></ul></td></tr><tr><th scope="row"><a href="/wiki/Linux_kernel_interfaces#In–kernel_APIs" title="Linux kernel interfaces">In-kernel</a></th><td><ul><li><a href="/wiki/Advanced_Linux_Sound_Architecture" title="Advanced Linux Sound Architecture">ALSA</a></li><li><a href="/wiki/Crypto_API_(Linux)" title="Crypto API (Linux)">Crypto API</a></li><li><a href="/wiki/Io_uring" title="Io uring">io uring</a></li><li><a href="/wiki/Direct_Rendering_Manager" title="Direct Rendering Manager">DRM</a></li><li><a href="/wiki/Kernfs_(Linux)" title="Kernfs (Linux)">kernfs</a></li><li><a href="/wiki/Memory_barrier" title="Memory barrier">Memory barrier</a></li><li><a href="/wiki/New_API" title="New API">New API</a></li><li><a href="/wiki/Read-copy-update" title="Read-copy-update">RCU</a></li><li><a href="/wiki/Video4Linux" title="Video4Linux">Video4Linux</a></li><li><a href="/wiki/IIO_Framework" title="IIO Framework">IIO</a></li></ul></td></tr></tbody></table></td></tr><tr><th scope="row"><a href="/wiki/User_space" title="User space">Userspace</a></th><td><table><tbody><tr><th scope="row"><a href="/wiki/Daemon_(computing)" title="Daemon (computing)">Daemons</a>,<br><a href="/wiki/Virtual_file_system" title="Virtual file system">File systems</a></th><td><ul><li><a href="/wiki/Devfs" title="Devfs">devfs</a></li><li><a href="/wiki/Devpts" title="Devpts">devpts</a></li><li><a href="/wiki/Debugfs" title="Debugfs">debugfs</a></li><li><a href="/wiki/Filesystem_in_userspace" title="Filesystem in userspace">FUSE</a></li><li><a href="/wiki/Procfs" title="Procfs">procfs</a></li><li><a href="/wiki/Sysfs" title="Sysfs">sysfs</a></li><li><a href="/wiki/Systemd" title="Systemd">systemd</a><ul><li><a href="/wiki/Udev" title="Udev">udev</a></li></ul></li><li><a href="/wiki/Kmscon" title="Kmscon">Kmscon</a></li></ul></td></tr><tr><th scope="row"><a href="/wiki/Wrapper_library" title="Wrapper library">Wrapper<br>libraries</a></th><td><ul><li><a href="/wiki/C_standard_library" title="C standard library">C standard library</a><ul><li><a href="/wiki/GNU_C_Library" title="GNU C Library">glibc</a></li><li><a href="/wiki/UClibc" title="UClibc">uClibc</a></li><li><a href="/wiki/Bionic_(software)" title="Bionic (software)">Bionic</a><ul><li><a href="/wiki/Hybris_(software)" title="Hybris (software)">libhybris</a></li></ul></li><li><a href="/wiki/Dietlibc" title="Dietlibc">dietlibc</a></li><li><a href="/wiki/Embedded_GLIBC" title="Embedded GLIBC">EGLIBC</a></li><li><a href="/wiki/Klibc" title="Klibc">klibc</a></li><li><a href="/wiki/Musl" title="Musl">musl</a></li><li><a href="/wiki/Newlib" title="Newlib">Newlib</a></li></ul></li><li><a href="/wiki/Cgroups" title="Cgroups">libcgroup</a></li><li><a href="/wiki/Direct_Rendering_Manager" title="Direct Rendering Manager">libdrm</a></li><li><a href="/wiki/Advanced_Linux_Sound_Architecture" title="Advanced Linux Sound Architecture">libalsa</a></li><li><a href="/wiki/Evdev" title="Evdev">libevdev</a></li><li><a href="/wiki/Libusb" title="Libusb">libusb</a></li><li><a href="/wiki/Io_uring" title="Io uring">liburing</a></li></ul></td></tr></tbody></table></td></tr></tbody></table></td></tr><tr><th scope="row">Components</th><td><ul><li><a href="/wiki/Loadable_kernel_module" title="Loadable kernel module">Kernel modules</a></li><li><a href="/wiki/BlueZ" title="BlueZ">BlueZ</a></li><li><a href="/wiki/Cgroups" title="Cgroups">cgroups</a></li><li><a href="/wiki/Linux_console" title="Linux console">Console</a></li><li><a href="/wiki/Bcache" title="Bcache">bcache</a></li><li><a href="/wiki/Device_mapper" title="Device mapper">Device mapper</a></li><li><a href="/wiki/Dm-cache" title="Dm-cache">dm-cache</a></li><li><a href="/wiki/Dm-crypt" title="Dm-crypt">dm-crypt</a></li><li><a href="/wiki/Direct_Rendering_Manager" title="Direct Rendering Manager">DRM</a></li><li><a href="/wiki/EDAC_(Linux)" title="EDAC (Linux)">EDAC</a></li><li><a href="/wiki/Evdev" title="Evdev">evdev</a></li><li><a href="/wiki/Kernel_same-page_merging" title="Kernel same-page merging">Kernel same-page merging</a> (KSM)</li><li><a href="/wiki/LIO_(SCSI_target)" title="LIO (SCSI target)">LIO</a></li><li><a href="/wiki/Linux_framebuffer" title="Linux framebuffer">Framebuffer</a></li><li><a href="/wiki/Logical_Volume_Manager_(Linux)" title="Logical Volume Manager (Linux)">LVM</a></li><li><a href="/wiki/KMS_driver" title="KMS driver">KMS driver</a></li><li><a href="/wiki/Netfilter" title="Netfilter">Netfilter</a></li><li><a href="/wiki/Netlink" title="Netlink">Netlink</a></li><li><a href="/wiki/Nftables" title="Nftables">nftables</a></li><li><a href="/wiki/Network_scheduler" title="Network scheduler">Network scheduler</a></li><li><a href="/wiki/Perf_(Linux)" title="Perf (Linux)">perf</a></li><li><a href="/wiki/SLUB_(software)" title="SLUB (software)">SLUB</a></li><li><a href="/wiki/Zram" title="Zram">zram</a></li><li><a href="/wiki/Zswap" title="Zswap">zswap</a></li></ul><ul><li><a href="/wiki/Scheduling_(computing)#Linux" title="Scheduling (computing)">Process and I/O schedulers</a>:</li><li><a href="/wiki/O(n)_scheduler" title="O(n) scheduler">O(n) scheduler</a></li><li><a href="/wiki/O(1)_scheduler" title="O(1) scheduler">O(1) scheduler</a></li><li><a href="/wiki/Completely_Fair_Scheduler" title="Completely Fair Scheduler">Completely Fair Scheduler</a> (CFS)</li><li><a href="/wiki/Brain_Fuck_Scheduler" title="Brain Fuck Scheduler">Brain Fuck Scheduler</a></li><li><a href="/wiki/Noop_scheduler" title="Noop scheduler">Noop scheduler</a></li><li><a href="/wiki/SCHED_DEADLINE" title="SCHED DEADLINE">SCHED_DEADLINE</a></li></ul><ul><li><a href="/wiki/Linux_Security_Modules" title="Linux Security Modules">Security Modules</a>: <a href="/wiki/AppArmor" title="AppArmor">AppArmor</a></li><li><a href="/wiki/Exec_Shield" title="Exec Shield">Exec Shield</a></li><li><a href="/wiki/Seccomp" title="Seccomp">seccomp</a></li><li><a href="/wiki/Security-Enhanced_Linux" title="Security-Enhanced Linux">SELinux</a></li><li><a href="/wiki/Smack_(software)" title="Smack (software)">Smack</a></li><li><a href="/wiki/Tomoyo_Linux" title="Tomoyo Linux">Tomoyo Linux</a></li><li><a href="/wiki/Linux_PAM" title="Linux PAM">Linux PAM</a></li></ul><ul><li><a href="/wiki/Device_driver" title="Device driver">Device drivers</a><ul><li><a href="/wiki/Comparison_of_open-source_wireless_drivers" title="Comparison of open-source wireless drivers">802.11</a></li><li><a href="/wiki/Free_and_open-source_graphics_device_driver" title="Free and open-source graphics device driver">graphics</a></li></ul></li><li><a href="/wiki/Raw_device" title="Raw device">Raw device</a></li></ul><ul><li><a href="/wiki/Initramfs" title="Initramfs">initramfs</a></li><li><a href="/wiki/KernelCare" title="KernelCare">KernelCare</a></li><li><a href="/wiki/Kexec" title="Kexec">kexec</a></li><li><a href="/wiki/KGraft" title="KGraft">kGraft</a></li><li><a href="/wiki/Kpatch" title="Kpatch">kpatch</a></li><li><a href="/wiki/Ksplice" title="Ksplice">Ksplice</a></li></ul></td></tr><tr><th scope="row">Variants</th><td><ul><li><a href="/wiki/Mainline_Linux" title="Mainline Linux">Mainline</a><ul><li><a href="/wiki/Linux_kernel" title="Linux kernel">Linux kernel</a></li><li><a href="/wiki/Linux-libre" title="Linux-libre">Linux-libre</a></li></ul></li><li><a href="/wiki/High-performance_computing" title="High-performance computing">High-performance computing</a><ul><li><a href="/wiki/INK_(operating_system)" title="INK (operating system)">INK</a></li><li><a href="/wiki/Compute_Node_Linux" title="Compute Node Linux">Compute Node Linux</a></li><li><a href="/wiki/Slurm_Workload_Manager" title="Slurm Workload Manager">SLURM</a></li></ul></li><li><a href="/wiki/Real-time_computing" title="Real-time computing">Real-time computing</a><ul><li><a href="/wiki/RTLinux" title="RTLinux">RTLinux</a></li><li><a href="/wiki/RTAI" title="RTAI">RTAI</a></li><li><a href="/wiki/Xenomai" title="Xenomai">Xenomai</a></li><li><a href="/wiki/Carrier_Grade_Linux" title="Carrier Grade Linux">Carrier Grade Linux</a></li></ul></li><li><a href="/wiki/Memory_management_unit" title="Memory management unit">MMU</a>-less<ul><li><a href="/wiki/%CE%9CClinux" title="ΜClinux">μClinux</a></li><li><a href="/wiki/PSXLinux" title="PSXLinux">PSXLinux</a></li></ul></li></ul><table><tbody><tr><th scope="row"><a href="/wiki/Virtualization" title="Virtualization">Virtualization</a></th><td><ul><li><a href="/wiki/Hypervisor" title="Hypervisor">Hypervisor</a><ul><li><a href="/wiki/Kernel-based_Virtual_Machine" title="Kernel-based Virtual Machine">KVM</a></li><li><a href="/wiki/Xen" title="Xen">Xen</a></li></ul></li><li><a href="/wiki/OS-level_virtualization" title="OS-level virtualization">OS-level virtualization</a><ul><li><a href="/wiki/Linux-VServer" title="Linux-VServer">Linux-VServer</a></li><li><a href="/wiki/Lguest" title="Lguest">Lguest</a></li><li><a href="/wiki/LXC" title="LXC">LXC</a></li><li><a href="/wiki/OpenVZ" title="OpenVZ">OpenVZ</a></li></ul></li><li>Other<ul><li><a href="/wiki/L4Linux" title="L4Linux">L4Linux</a></li><li><a href="/wiki/ELinOS" title="ELinOS">ELinOS</a></li><li><a href="/wiki/User-mode_Linux" title="User-mode Linux">User-mode Linux</a></li><li><a href="/wiki/MkLinux" title="MkLinux">MkLinux</a></li><li><a href="/wiki/Cooperative_Linux" title="Cooperative Linux">coLinux</a></li></ul></li></ul></td></tr></tbody></table></td></tr></tbody></table></td></tr><tr><th scope="row"><a href="/wiki/Linux_adoption" title="Linux adoption">Adoption</a></th><td><table><tbody><tr><th scope="row"><a href="/wiki/Linux_range_of_use" title="Linux range of use">Range<br>of use</a></th><td><ul><li><a href="/wiki/Linux_desktop_environments" title="Linux desktop environments">Desktop</a></li><li><a href="/wiki/Linux_on_embedded_systems" title="Linux on embedded systems">Embedded</a></li><li><a href="/wiki/Linux_gaming" title="Linux gaming">Gaming</a></li><li>Thin client:<ul><li><a href="/wiki/Linux_Terminal_Server_Project" title="Linux Terminal Server Project">LTSP</a></li></ul></li><li>Server:<ul><li><a href="/wiki/LAMP_(software_bundle)" title="LAMP (software bundle)">LAMP</a></li><li><a href="/wiki/LYME_(software_bundle)" title="LYME (software bundle)">LYME-LYCE</a></li></ul></li><li><a href="/wiki/Linux-powered_device" title="Linux-powered device">Devices</a></li></ul></td></tr><tr><th scope="row">Adopters</th><td><ul><li><a href="/wiki/List_of_Linux_adopters" title="List of Linux adopters">List of Linux adopters</a></li><li><a href="/wiki/GENIVI_Alliance" title="GENIVI Alliance">GENIVI Alliance</a></li><li><a href="/wiki/List_of_proprietary_software_for_Linux" title="List of proprietary software for Linux">Proprietary software for Linux</a></li></ul></td></tr></tbody></table></td></tr><tr><td colspan="2"><ul><li><b><a href="/wiki/File:NewTux.svg"><img class="" src="http://upload.wikimedia.org/wikipedia/commons/thumb/b/b0/NewTux.svg/13px-NewTux.svg.png"></a>&nbsp;<a href="/wiki/Portal:Linux" title="Portal:Linux">Linux portal</a></b></li><li><b><img class="" src="http://upload.wikimedia.org/wikipedia/commons/thumb/3/31/Free_and_open-source_software_logo_%282009%29.svg/16px-Free_and_open-source_software_logo_%282009%29.svg.png">&nbsp;<a href="/wiki/Portal:Free_and_open-source_software" title="Portal:Free and open-source software">Free and open-source software portal</a></b></li><li><img class="" src="http://upload.wikimedia.org/wikipedia/en/thumb/9/96/Symbol_category_class.svg/16px-Symbol_category_class.svg.png"> <b><a href="/wiki/Category:Linux_kernel" title="Category:Linux kernel">Category</a></b></li></ul></td></tr></tbody></table>

NewPP limit report Parsed by mw2414 Cached time: 20230221170016 Cache expiry: 86400 Reduced expiry: true Complications: [vary‐revision‐sha1, show‐toc] CPU time usage: 0.392 seconds Real time usage: 0.532 seconds Preprocessor visited node count: 2335/1000000 Post‐expand include size: 170392/2097152 bytes Template argument size: 12962/2097152 bytes Highest expansion depth: 19/100 Expensive parser function count: 10/500 Unstrip recursion depth: 1/20 Unstrip post‐expand size: 66108/5000000 bytes Lua time usage: 0.232/10.000 seconds Lua memory usage: 7256626/52428800 bytes Number of Wikibase entities loaded: 1/400

Transclusion expansion time report (%,ms,calls,template) 100.00% 440.174 1 -total 29.63% 130.433 5 Template:Ambox 28.70% 126.338 12 Template:Navbox 26.28% 115.689 1 Template:Reflist 20.91% 92.028 13 Template:Cite_web 19.96% 87.842 1 Template:Multiple_issues 16.24% 71.500 1 Template:Virtualization_software 11.95% 52.622 1 Template:Infobox_software 11.02% 48.492 1 Template:Infobox 10.82% 47.618 1 Template:Short_description

Saved in parser cache with key enwiki:pcache:idhash:42463173-0!canonical and timestamp 20230221170016 and revision id 1136549483. Rendering was triggered because: page-view