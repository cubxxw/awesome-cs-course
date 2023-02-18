> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 [github.com](https://github.com/ohmyzsh/ohmyzsh)

> 🙃 A delightful community-driven (with 2,100+ contributors) framework for managing your zsh configuration. Includes 300+ optional plugins (rails, git, macOS, hub, docker, homebrew, node, php, pyth...

[![](https://camo.githubusercontent.com/4db3e4069e59f51d03dd3e7fa5e89ab8fb95c9f4acda36cd5bfdf58d95269d92/68747470733a2f2f6f686d797a73682e73332e616d617a6f6e6177732e636f6d2f6f6d7a2d616e73692d6769746875622e706e67)](https://camo.githubusercontent.com/4db3e4069e59f51d03dd3e7fa5e89ab8fb95c9f4acda36cd5bfdf58d95269d92/68747470733a2f2f6f686d797a73682e73332e616d617a6f6e6177732e636f6d2f6f6d7a2d616e73692d6769746875622e706e67)

Oh My Zsh is an open source, community-driven framework for managing your [zsh](https://www.zsh.org/) configuration.

Sounds boring. Let's try again.

**Oh My Zsh will not make you a 10x developer...but you may feel like one.**

Once installed, your terminal shell will become the talk of the town _or your money back!_ With each keystroke in your command prompt, you'll take advantage of the hundreds of powerful plugins and beautiful themes. Strangers will come up to you in cafés and ask you, _"that is amazing! are you some sort of genius?"_

Finally, you'll begin to get the sort of attention that you have always felt you deserved. ...or maybe you'll use the time that you're saving to start flossing more often. 😬

To learn more, visit [ohmyz.sh](https://ohmyz.sh), follow [@ohmyzsh](https://twitter.com/ohmyzsh) on Twitter, and join us on [Discord](https://discord.gg/ohmyzsh).

[![](https://github.com/ohmyzsh/ohmyzsh/workflows/CI/badge.svg)](https://github.com/ohmyzsh/ohmyzsh/actions?query=workflow%3ACI) [](https://twitter.com/intent/follow?screen_><img class=) [![](https://camo.githubusercontent.com/64e8b917c8acd4f1b4c183948adc71b474cd574e5b36882f06c13631ad57ec6f/68747470733a2f2f696d672e736869656c64732e696f2f646973636f72642f363432343936383636343037323834373436)](https://discord.gg/ohmyzsh) [![](https://camo.githubusercontent.com/eed956a921d6c38546e378ad7ee005b423b76361eef47db35b6afb2c71c8a090/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f476974706f642d72656164792d626c75653f6c6f676f3d676974706f64)](https://gitpod.io/#https://github.com/ohmyzsh/ohmyzsh) [![](https://camo.githubusercontent.com/30c28e8a19d49b9af85a85ca1c2911731b9659e2e8596315faf92162888f162f/68747470733a2f2f63646e2e68756e74722e6465762f68756e74725f73656375726974795f62616467655f6d6f6e6f2e737667)](https://huntr.dev/bounties/disclose/?utm_campaign=ohmyzsh%2Fohmyzsh&utm_medium=social&utm_source=github&target=https%3A%2F%2Fgithub.com%2Fohmyzsh%2Fohmyzsh)

Table of Contents

*   [Getting Started](#getting-started)
    *   [Prerequisites](#prerequisites)
    *   [Basic Installation](#basic-installation)
        *   [Manual inspection](#manual-inspection)
*   [Using Oh My Zsh](#using-oh-my-zsh)
    *   [Plugins](#plugins)
        *   [Enabling Plugins](#enabling-plugins)
        *   [Using Plugins](#using-plugins)
    *   [Themes](#themes)
        *   [Selecting a Theme](#selecting-a-theme)
    *   [FAQ](#faq)
*   [Advanced Topics](#advanced-topics)
    *   [Advanced Installation](#advanced-installation)
        *   [Custom Directory](#custom-directory)
        *   [Unattended install](#unattended-install)
        *   [Installing from a forked repository](#installing-from-a-forked-repository)
        *   [Manual Installation](#manual-installation)
    *   [Installation Problems](#installation-problems)
    *   [Custom Plugins and Themes](#custom-plugins-and-themes)
*   [Getting Updates](#getting-updates)
    *   [Manual Updates](#manual-updates)
*   [Uninstalling Oh My Zsh](#uninstalling-oh-my-zsh)
*   [How do I contribute to Oh My Zsh?](#how-do-i-contribute-to-oh-my-zsh)
    *   [Do NOT send us themes](#do-not-send-us-themes)
*   [Contributors](#contributors)
*   [Follow Us](#follow-us)
*   [Merchandise](#merchandise)
*   [License](#license)
*   [About Planet Argon](#about-planet-argon)

[](#getting-started)Getting Started
-----------------------------------

### [](#prerequisites)Prerequisites

*   A Unix-like operating system: macOS, Linux, BSD. On Windows: WSL2 is preferred, but cygwin or msys also mostly work.
*   [Zsh](https://www.zsh.org) should be installed (v4.3.9 or more recent is fine but we prefer 5.0.8 and newer). If not pre-installed (run `zsh --version` to confirm), check the following wiki instructions here: [Installing ZSH](https://github.com/ohmyzsh/ohmyzsh/wiki/Installing-ZSH)
*   `curl` or `wget` should be installed
*   `git` should be installed (recommended v2.4.11 or higher)

### [](#basic-installation)Basic Installation

Oh My Zsh is installed by running one of the following commands in your terminal. You can install this via the command-line with either `curl`, `wget` or another similar tool.

<table><thead><tr><th align="left">Method</th><th align="left">Command</th></tr></thead><tbody><tr><td align="left"><strong>curl</strong></td><td align="left"><code>sh -c "$(curl -fsSL https://raw.githubusercontent.com/ohmyzsh/ohmyzsh/master/tools/install.sh)"</code></td></tr><tr><td align="left"><strong>wget</strong></td><td align="left"><code>sh -c "$(wget -O- https://raw.githubusercontent.com/ohmyzsh/ohmyzsh/master/tools/install.sh)"</code></td></tr><tr><td align="left"><strong>fetch</strong></td><td align="left"><code>sh -c "$(fetch -o - https://raw.githubusercontent.com/ohmyzsh/ohmyzsh/master/tools/install.sh)"</code></td></tr></tbody></table>

_Note that any previous `.zshrc` will be renamed to `.zshrc.pre-oh-my-zsh`. After installation, you can move the configuration you want to preserve into the new `.zshrc`._

#### [](#manual-inspection)Manual inspection

It's a good idea to inspect the install script from projects you don't yet know. You can do that by downloading the install script first, looking through it so everything looks normal, then running it:

```
wget https://raw.githubusercontent.com/ohmyzsh/ohmyzsh/master/tools/install.sh
sh install.sh

```

[](#using-oh-my-zsh)Using Oh My Zsh
-----------------------------------

### [](#plugins)Plugins

Oh My Zsh comes with a shitload of plugins for you to take advantage of. You can take a look in the [plugins](https://github.com/ohmyzsh/ohmyzsh/tree/master/plugins) directory and/or the [wiki](https://github.com/ohmyzsh/ohmyzsh/wiki/Plugins) to see what's currently available.

#### [](#enabling-plugins)Enabling Plugins

Once you spot a plugin (or several) that you'd like to use with Oh My Zsh, you'll need to enable them in the `.zshrc` file. You'll find the zshrc file in your `$HOME` directory. Open it with your favorite text editor and you'll see a spot to list all the plugins you want to load.

```
vi ~/.zshrc

```

For example, this might begin to look like this:

```
plugins=(
  git
  bundler
  dotenv
  macos
  rake
  rbenv
  ruby
)

```

_Note that the plugins are separated by whitespace (spaces, tabs, new lines...). **Do not** use commas between them or it will break._

#### [](#using-plugins)Using Plugins

Each built-in plugin includes a **README**, documenting it. This README should show the aliases (if the plugin adds any) and extra goodies that are included in that particular plugin.

### [](#themes)Themes

We'll admit it. Early in the Oh My Zsh world, we may have gotten a bit too theme happy. We have over one hundred and fifty themes now bundled. Most of them have [screenshots](https://github.com/ohmyzsh/ohmyzsh/wiki/Themes) on the wiki (We are working on updating this!). Check them out!

#### [](#selecting-a-theme)Selecting a Theme

_Robby's theme is the default one. It's not the fanciest one. It's not the simplest one. It's just the right one (for him)._

Once you find a theme that you'd like to use, you will need to edit the `~/.zshrc` file. You'll see an environment variable (all caps) in there that looks like:

```
ZSH_THEME="robbyrussell"

```

To use a different theme, simply change the value to match the name of your desired theme. For example:

```
ZSH_THEME="agnoster" # (this is one of the fancy ones)
# see https://github.com/ohmyzsh/ohmyzsh/wiki/Themes#agnoster

```

_Note: many themes require installing a [Powerline Font](https://github.com/powerline/fonts) or a [Nerd Font](https://github.com/ryanoasis/nerd-fonts) in order to render properly. Without them, these themes will render [weird prompt symbols](https://github.com/ohmyzsh/ohmyzsh/wiki/FAQ#i-have-a-weird-character-in-my-prompt)_

Open up a new terminal window and your prompt should look something like this:

[![](https://cloud.githubusercontent.com/assets/2618447/6316862/70f58fb6-ba03-11e4-82c9-c083bf9a6574.png)](https://cloud.githubusercontent.com/assets/2618447/6316862/70f58fb6-ba03-11e4-82c9-c083bf9a6574.png)

In case you did not find a suitable theme for your needs, please have a look at the wiki for [more of them](https://github.com/ohmyzsh/ohmyzsh/wiki/External-themes).

If you're feeling feisty, you can let the computer select one randomly for you each time you open a new terminal window.

```
ZSH_THEME="random" # (...please let it be pie... please be some pie..)

```

And if you want to pick random theme from a list of your favorite themes:

```
ZSH_THEME_RANDOM_CANDIDATES=(
  "robbyrussell"
  "agnoster"
)

```

If you only know which themes you don't like, you can add them similarly to an ignored list:

```
ZSH_THEME_RANDOM_IGNORED=(pygmalion tjkirch_mod)

```

### [](#faq)FAQ

If you have some more questions or issues, you might find a solution in our [FAQ](https://github.com/ohmyzsh/ohmyzsh/wiki/FAQ).

[](#advanced-topics)Advanced Topics
-----------------------------------

If you're the type that likes to get their hands dirty, these sections might resonate.

### [](#advanced-installation)Advanced Installation

Some users may want to manually install Oh My Zsh, or change the default path or other settings that the installer accepts (these settings are also documented at the top of the install script).

#### [](#custom-directory)Custom Directory

The default location is `~/.oh-my-zsh` (hidden in your home directory, you can access it with `cd ~/.oh-my-zsh`)

If you'd like to change the install directory with the `ZSH` environment variable, either by running `export ZSH=/your/path` before installing, or by setting it before the end of the install pipeline like this:

```
ZSH="$HOME/.dotfiles/oh-my-zsh" sh install.sh

```

#### [](#unattended-install)Unattended install

If you're running the Oh My Zsh install script as part of an automated install, you can pass the `--unattended` flag to the `install.sh` script. This will have the effect of not trying to change the default shell, and it also won't run `zsh` when the installation has finished.

```
sh -c "$(curl -fsSL https://raw.githubusercontent.com/ohmyzsh/ohmyzsh/master/tools/install.sh)" "" --unattended

```

#### [](#installing-from-a-forked-repository)Installing from a forked repository

The install script also accepts these variables to allow installation of a different repository:

*   `REPO` (default: `ohmyzsh/ohmyzsh`): this takes the form of `owner/repository`. If you set this variable, the installer will look for a repository at `https://github.com/{owner}/{repository}`.
    
*   `REMOTE` (default: `https://github.com/${REPO}.git`): this is the full URL of the git repository clone. You can use this setting if you want to install from a fork that is not on GitHub (GitLab, Bitbucket...) or if you want to clone with SSH instead of HTTPS (`git@github.com:user/project.git`).
    
    _NOTE: it's incompatible with setting the `REPO` variable. This setting will take precedence._
    
*   `BRANCH` (default: `master`): you can use this setting if you want to change the default branch to be checked out when cloning the repository. This might be useful for testing a Pull Request, or if you want to use a branch other than `master`.
    

For example:

```
REPO=apjanke/oh-my-zsh BRANCH=edge sh install.sh

```

#### [](#manual-installation)Manual Installation

##### [](#1-clone-the-repository-)1. Clone the repository

```
git clone https://github.com/ohmyzsh/ohmyzsh.git ~/.oh-my-zsh

```

##### [](#2-optionally-backup-your-existing-zshrc-file-)2. _Optionally_, backup your existing `~/.zshrc` file

```
cp ~/.zshrc ~/.zshrc.orig

```

##### [](#3-create-a-new-zsh-configuration-file-)3. Create a new zsh configuration file

You can create a new zsh config file by copying the template that we have included for you.

```
cp ~/.oh-my-zsh/templates/zshrc.zsh-template ~/.zshrc

```

##### [](#4-change-your-default-shell-)4. Change your default shell

```
chsh -s $(which zsh)

```

You must log out from your user session and log back in to see this change.

##### [](#5-initialize-your-new-zsh-configuration-)5. Initialize your new zsh configuration

Once you open up a new terminal window, it should load zsh with Oh My Zsh's configuration.

### [](#installation-problems)Installation Problems

If you have any hiccups installing, here are a few common fixes.

*   You _might_ need to modify your `PATH` in `~/.zshrc` if you're not able to find some commands after switching to `oh-my-zsh`.
*   If you installed manually or changed the install location, check the `ZSH` environment variable in `~/.zshrc`.

### [](#custom-plugins-and-themes)Custom Plugins and Themes

If you want to override any of the default behaviors, just add a new file (ending in `.zsh`) in the `custom/` directory.

If you have many functions that go well together, you can put them as a `XYZ.plugin.zsh` file in the `custom/plugins/` directory and then enable this plugin.

If you would like to override the functionality of a plugin distributed with Oh My Zsh, create a plugin of the same name in the `custom/plugins/` directory and it will be loaded instead of the one in `plugins/`.

### [](#remove-directories-aliases)Remove directories aliases

If you want to skip ohmyzsh default [directories aliases](https://github.com/ohmyzsh/ohmyzsh/blob/master/lib/directories.zsh) you can add the following snippet to your `zshrc`, before loading `oh-my-zsh.sh` script:

```
zstyle ':omz:directories' aliases no

```

[](#getting-updates)Getting Updates
-----------------------------------

By default, you will be prompted to check for updates every 2 weeks. You can choose other update modes by adding a line to your `~/.zshrc` file, **before Oh My Zsh is loaded**:

1.  Automatic update without confirmation prompt:
    
    ```
    zstyle ':omz:update' mode auto
    
    ```
    
2.  Just offer a reminder every few days, if there are updates available:
    
    ```
    zstyle ':omz:update' mode reminder
    
    ```
    
3.  To disable automatic updates entirely:
    
    ```
    zstyle ':omz:update' mode disabled
    
    ```
    

NOTE: you can control how often Oh My Zsh checks for updates with the following setting:

```
# This will check for updates every 7 days
zstyle ':omz:update' frequency 7
# This will check for updates every time you open the terminal (not recommended)
zstyle ':omz:update' frequency 0

```

### [](#manual-updates)Manual Updates

If you'd like to update at any point in time (maybe someone just released a new plugin and you don't want to wait a week?) you just need to run:

```
omz update

```

Magic! 🎉

[](#uninstalling-oh-my-zsh)Uninstalling Oh My Zsh
-------------------------------------------------

Oh My Zsh isn't for everyone. We'll miss you, but we want to make this an easy breakup.

If you want to uninstall `oh-my-zsh`, just run `uninstall_oh_my_zsh` from the command-line. It will remove itself and revert your previous `bash` or `zsh` configuration.

[](#how-do-i-contribute-to-oh-my-zsh)How do I contribute to Oh My Zsh?
----------------------------------------------------------------------

Before you participate in our delightful community, please read the [code of conduct](/ohmyzsh/ohmyzsh/blob/master/CODE_OF_CONDUCT.md).

I'm far from being a [Zsh](https://www.zsh.org/) expert and suspect there are many ways to improve – if you have ideas on how to make the configuration easier to maintain (and faster), don't hesitate to fork and send pull requests!

We also need people to test out pull requests. So take a look through [the open issues](https://github.com/ohmyzsh/ohmyzsh/issues) and help where you can.

See [Contributing](/ohmyzsh/ohmyzsh/blob/master/CONTRIBUTING.md) for more details.

### [](#do-not-send-us-themes)Do NOT send us themes

We have (more than) enough themes for the time being. Please add your theme to the [external themes](https://github.com/ohmyzsh/ohmyzsh/wiki/External-themes) wiki page.

[](#contributors)Contributors
-----------------------------

Oh My Zsh has a vibrant community of happy users and delightful contributors. Without all the time and help from our contributors, it wouldn't be so awesome.

Thank you so much!

[](#follow-us)Follow Us
-----------------------

We're on social media:

*   [@ohmyzsh](https://twitter.com/ohmyzsh) on Twitter. You should follow it.
*   [Facebook](https://www.facebook.com/Oh-My-Zsh-296616263819290/) poke us.
*   [Instagram](https://www.instagram.com/_ohmyzsh/) tag us in your post showing Oh My Zsh!
*   [Discord](https://discord.gg/ohmyzsh) to chat with us!

[](#merchandise)Merchandise
---------------------------

We have [stickers, shirts, and coffee mugs available](https://shop.planetargon.com/collections/oh-my-zsh?utm_source=github) for you to show off your love of Oh My Zsh. Again, you will become the talk of the town!

[](#license)License
-------------------

Oh My Zsh is released under the [MIT license](/ohmyzsh/ohmyzsh/blob/master/LICENSE.txt).

[](#about-planet-argon)About Planet Argon
-----------------------------------------

[![](https://camo.githubusercontent.com/170065022b50bc9f78cdca4c602820da0992b180490a1eb8b5000b154f11c938/68747470733a2f2f70612d6769746875622d6173736574732e73332e616d617a6f6e6177732e636f6d2f504152474f4e5f6c6f676f5f6469676974616c5f434f4c2d736d616c6c2e6a7067)](https://camo.githubusercontent.com/170065022b50bc9f78cdca4c602820da0992b180490a1eb8b5000b154f11c938/68747470733a2f2f70612d6769746875622d6173736574732e73332e616d617a6f6e6177732e636f6d2f504152474f4e5f6c6f676f5f6469676974616c5f434f4c2d736d616c6c2e6a7067)

Oh My Zsh was started by the team at [Planet Argon](https://www.planetargon.com/?utm_source=github), a [Ruby on Rails development agency](https://www.planetargon.com/skills/ruby-on-rails-development?utm_source=github). Check out our [other open source projects](https://www.planetargon.com/open-source?utm_source=github).