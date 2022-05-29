#include <windows.h> //必须在包含mysql.h之前包含windows.h
#include <mysql.h>
#include <stdio.h>

int main()
{
    char SqlText[256] = "";  //将用来保存要执行的SQL语句
    MYSQL *conn = NULL;      //MYSQL句柄类型(任何一个mysql操作都是基于MYSQL这个句柄来操作的)
    MYSQL_RES *res = NULL;   //数据查询结果集
    MYSQL_FIELD * fd = NULL; //MySQL表头域类型
    MYSQL_ROW row;           //一个行数据的类型安全(type-safe)的表示
    conn = mysql_init(NULL); //初始化MYSQL连接
    char  server[20] = "localhost"; //mysql服务器的IP
    char  user[20] = "root";        //用户名
    char  psd[20] = "123456";       ///密码  需要改为自己的密码
    char  dbName[1024] = "test";    ///数据库名 需要改为自己的数据库名
    unsigned short port = 3306;     //服务器端口号,默认3306
    ///建立mysql连接
    if(mysql_real_connect(conn, server, user, psd, dbName, port, NULL, 0)==0) {
        printf("Error connecting to database: %s\n", mysql_error(conn));
    } else {
        puts("数据库连接成功！");
        mysql_query(conn, "set names 'GBK'");//设置字符集,防止中文无法正常显示[可选操作]
        ///下面开始对数据库进行操作
        const char tableName[30] = "UserInfo";//要操作的表名
        //插入
        sprintf(SqlText, "insert into %s(userName,passwd) values('%s','%s')", tableName, "test1", "test1");
        printf(">>>执行: %s\n", SqlText);
        if (mysql_query(conn, SqlText) != 0) { //执行sql语句,执行成功返回0
            printf("Can't insert data to table: ");
            printf("%s\n", mysql_error(conn)); //获取最后一次查询失败的错误提示
        }
        //删除
        sprintf(SqlText, "delete from %s where userName='test1'", tableName);
        printf("将执行删除语句 %s, 按y/Y确认!", SqlText);
        char c = getchar();
        if(c == 'y' || c == 'Y') {
            printf(">>>执行: %s\n", SqlText);
            if (mysql_query(conn, SqlText) != 0) { //执行sql语句
                printf("Can't delete data from table: ");
                printf("%s\n", mysql_error(conn)); //获取最后一次查询失败的错误提示
            }
        }
        //查找
        sprintf(SqlText, "select * from %s where type='0'", tableName);
        if (mysql_query(conn, SqlText)==0) {       //执行成功则把结果输出
            res = mysql_store_result( conn );      //存储查询得到的结果集
            printf(">>>执行: %s\nrecords nums:", SqlText);
            printf("%u\n\n", mysql_num_rows(res)); //获取搜索到的结果集的条数
            //输出数据结果各字段名,即表头被选取的部分
            while((fd = mysql_fetch_field(res)) != NULL) {
                printf("%-10s ", fd->name);
            }
            puts("");
            //打印获取的数据
            int fieldNums = mysql_num_fields(res); //获取数据结果每条记录的列数
            while ((row = mysql_fetch_row(res)) != NULL) { //不断获取下一组结果
                for(int i = 0; i < fieldNums; i++) {
                    printf("%-10s ", row[i]);
                }
                puts("");
            }
            mysql_free_result(res); //释放结果集资源
        } else {
            printf("查询失败: %s\n", mysql_error(conn));
        }
        //修改
        sprintf(SqlText, "update %s set passwd='passwd' where id='2'", tableName);
        printf(">>>执行: %s\n", SqlText);
        if (mysql_query(conn, SqlText) != 0) { //执行sql语句
            printf("Can't update data on table: ");
            printf("%s\n", mysql_error(conn)); //获取最后一次查询失败的错误提示
        }
    }
    mysql_close(conn); //关闭连接，即释放连接
    getchar();
    return 0;
}
