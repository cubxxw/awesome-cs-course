###最好安装32位的MySQL
*  ```
    *  1.把libmysql.dll复制到C:/windows/system32/  （若是32位系统）和C:/windows/sysWOW64/  （若是64位系统）下
    
    *  2.配置好IDE：<br/>
        （1）添加包含目录：【安装位置\MySQL Server 5.6\include】<br/>
        （2）添加库目录：【安装位置\MySQL Server 5.6\lib】<br/>
        （3）让IDE包含必要的链接库文件：【安装位置\MySQL Server 5.6\lib\libmysql.lib】(vs中一般为‘链接器’-‘输入’-‘附加依赖项’:添加libmysql.lib)
    
    *  3.运行【安装位置\MySQL Server 5.6\bin】目录下的mysqld.exe，注意每次使用前都需要让这个后台运行
    ```

    
