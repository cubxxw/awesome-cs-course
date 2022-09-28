/*
 * @Author: xiongxinwei 3293172751nss@gmail.com
 * @Date: 2022-07-31 21:22:34
 * @LastEditors: xiongxinwei 3293172751nss@gmail.com
 * @LastEditTime: 2022-07-31 21:29:09
 * @FilePath: \vuepress\docs\docs\sidebar.js
 * @Description: 
 * 
 * 
 */
module.exports = {
      title: 'js基础',
      collapsable: true,   //能否收起
      children: [
        { title: '基础a', path:'/js_base/array'},
        { title: '函数部分', path:'/js_base/function'}
              ],
    '/h5/':[{
      title: 'h5原生',
      collapsable: false,
      children: [
          { title: 'js-bridge', path:'/h5/js-bridge'},
          { title: '适配', path:'/h5/适配'}
              ]
    }],
  }