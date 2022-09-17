/*
 * @Author: xiongxinwei 3293172751nss@gmail.com
 * @Date: 2022-07-31 21:04:54
 * @LastEditors: xiongxinwei 3293172751nss@gmail.com
 * @LastEditTime: 2022-07-31 21:30:40
 * @FilePath: \vuepress\docs\.vuepress\sidebar.js
 * @Description: 
 * 
 * 
 */
module.exports = {
    '/guide/':require('../docs/sidebar'),
    //再引用 --- 
    '/js_base/':[{
      title: 'js基础',
      collapsable: false, // 是否能收起来
      children: [
        { title: '基础a', path:'/js_base/array'},
        { title: '函数部分', path:'/js_base/function'}
              ]
    }],
    '/h5/':[{
      title: 'h5原生',
      collapsable: false,
      children: [
          { title: 'js-bridge', path:'/h5/js-bridge'},
          { title: '适配', path:'/h5/适配'}
              ]
    }],
  }