/*
 * @Description: vuepress配置文件
 * @Author: xiongxinwei 3293172751nss@gmail.com
 * @Date: 2022-08-06 21:48:58
 * @LastEditTime: 2022-09-14 13:03:35
 * @FilePath: \undefinedd:\文档\最近的\vuepress2\docs\.vuepress\config.ts
 * @blog: https://nsddd.top
 */
import { defineUserConfig } from 'vuepress'
const { defaultTheme } = require('vuepress')
//themeConfig外部配置属于站点配置，内部配置属于主题配置
export default defineUserConfig({
  lang: 'zh-CN',
  port: 8888,  //设置端口号
  title: '你好',  //主页
  description: '致力于打造出区块链去中心化的学习平台',
   // sidebarDepth:2,   //默认显示H1 H2
  head:[
    ["link",{rel:"icon",href:"/img/1.jpg"}]
    //设置网站seo标志
  ],

  theme: defaultTheme({
    sidebarDepth: 3,  //侧边菜单深度
    
    //logo -- 夜间和白剑
    logoDark: 'https://sm.nsddd.top//typora/1.jpg?mail:3293172751@qq.com',
    logo: 'https://sm.nsddd.top//typora/4.png?mail:3293172751@qq.com',
    
    // 到github修改页面 如果你按照 `organization组织/repository存储库` 的格式设置它
    // 我们会将它作为一个 GitHub 仓库
    editLinkText: '在 GitHub 上编辑此页',
    lastUpdatedText: '上次更新',
    contributorsText: '贡献者',
    // 你也可以直接将它设置为一个 URL
    repo: '3293172751/vuepress',
    tip: '提示',
    warning: '注意',
    danger: '警告',
    // 404 page
    notFound: [
      '这里什么都没有',
      '我们怎么到这来了？',
      '这是一个 404 页面',
      '看起来我们进入了错误的链接',
    ],
    backToHome: '返回首页',
    // a11y
    openInNewWindow: '在新窗口打开',
    toggleColorMode: '切换颜色模式',
    toggleSidebar: '切换侧边栏', 
    // 导航栏
    navbar: [
        {
            text: '关于我',
            children: [
              {
                text: 'Github首页',
                link: 'https://github.com/3293172751',
                // 该元素将一直处于激活状态
                activeMatch: '/',
              },
              {
                text: '我的博客',
                link: 'http://nsddd.top',
                // 该元素将一直处于激活状态
                activeMatch: '/',
              },
              {
                text: 'Active on /foo/',
                link: '/not-foo/',
                // 该元素在当前路由路径是 /foo/ 开头时激活
                // 支持正则表达式
                activeMatch: '^/foo/',
              },
            ],
        },
        {
          text: '首页',
          link: '/',
        },
        {
          text: 'Github',
          link: 'https://github.com/3293172751/'
        }
      ],
      
    // 默认主题配置
    sidebar:[
        // SidebarItem
        {
          text: 'Vuepress静态博客搭建',
          link: '/markdown/',
          children: [
            // SidebarItem
            {
              text: '如何参与贡献？',
              link: 'https://nsddd.top/archives/contributors',
            //   children: [],
            },
            '/markdown/1.md',
            '/markdown/2.md',
            '/markdown/3.md',
            '/markdown/4.md',
            '/markdown/5.md',
            '/markdown/6.md',
            '/markdown/7.md',          
            '/markdown/8.md',
            '/markdown/9.md',
            '/markdown/10.md',
            '/markdown/11.md',
            '/markdown/12.md',
            '/markdown/13.md',
            '/markdown/14.md',
            '/markdown/15.md',
            '/markdown/16.md',
            '/markdown/17.md',          
            '/markdown/18.md',
            '/markdown/19.md',
            '/markdown/20.md',
            '/markdown/21.md',
            '/markdown/22.md',
            // 字符串 - 页面文件路径
            // '/foo/bar.md',
          ],
        },
        // 字符串 - 页面文件路径
        '/markdown/2.md',
      ],
      
  }),
})
