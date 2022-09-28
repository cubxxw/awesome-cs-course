/*
 * @Description: 导航栏
 * @Author: xiongxinwei 3293172751nss@gmail.com
 * @Date: 2022-09-12 21:28:01
 * @LastEditTime: 2022-09-12 21:29:12
 * @FilePath: \.vuepress\nav.js
 * @blog: https://nsddd.top
 */

module.exports = [
    {
        text: 'Github',link:'https://github.com/3293172751'
    },
    { text: '首页', link: '/' }, 
    { text: 'External', link: 'https://baidu.com', target:'_self', rel:'' },
    { text: 'readme', link: './markdown/readme'},
    { text: 'js-base', link: './'},
    {
      text: '语言', 
      items:[
        //展开
        { text: '中文', link: '/js_base/array' },
        { text: 'english', link: '/js_base/function' },
      ]
    }
  ]