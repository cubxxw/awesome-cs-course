/*
 * @Description: 
 * @Author: xiongxinwei 3293172751nss@gmail.com
 * @Date: 2022-09-19 22:09:39
 * @LastEditTime: 2022-09-20 08:15:48
 * @FilePath: \.vuepressd:\文档\git\CS_COURSE\vuepress2\cub-docs\.vuepress\config.ts
 * @blog: https://nsddd.top
 */
import { defineUserConfig } from "vuepress";
import theme from "./theme.js";
import { hopeTheme } from "vuepress-theme-hope";
export default defineUserConfig({
  base: "/",

  dest: "./dist",

  theme: hopeTheme({

  hostname: "http://www.nsddd.top.com",
 
  author: {
    name: "xiongxinwei",
    url: "http://www.macrozheng.com",
  },
 
  iconPrefix: "iconfont icon-",
 
  logo: "/logo.png",
 
  repo: "https://github.com/macrozheng",
 
  docsDir: "demo/src",
 
  footer: "默认页脚",
 
  displayFooter: true,
 
  blog: {
    description: "SpringBoot实战电商项目mall（50K+Star）的作者",
    intro: "https://github.com/macrozheng",
    medias: {
      Gitee: "https://gitee.com/macrozheng",
      GitHub: "https://github.com/macrozheng",
      Wechat: "https://example.com",
      Juejin: "https://juejin.cn/user/958429871749192",
      Zhihu: "https://www.zhihu.com/people/macrozheng",
    },
  },
    // 此处放置主题配置
  }),

  locales: {
    "/": {
      lang: "en-US",
      title: "Docs Demo",
      description: "A docs demo for vuepress-theme-hope",
    },
    "/zh/": {
      lang: "zh-CN",
      title: "文档演示",
      description: "vuepress-theme-hope 的文档演示",
    },
  },
  shouldPrefetch: false,
});
