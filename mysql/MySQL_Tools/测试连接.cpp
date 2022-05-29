#include <stdio.h>
#include <winsock.h>
#include <mysql.h>
#include <string.h>
#pragma comment( lib, "libmysql.lib")

int main()
{
/// 例子1
    puts("测试1...(链接)");
    //MYSQL句柄   (任何一个mysql操作都是基于MYSQL这个句柄来操作的)
    MYSQL* link_ = mysql_init(NULL);             //初始化MYSQL句柄
    //设置超时时间(链接超时时间，查询超时时间，写数据库超时时间)
    int  timeout =  3;                      //超时时间设置为3秒
    if(link_ != NULL) {
        mysql_options(link_,MYSQL_OPT_CONNECT_TIMEOUT,(const char *)&timeout);
        //设置链接超时时间.
        mysql_options(link_,MYSQL_OPT_READ_TIMEOUT,(const char *)&timeout);
        //设置查询数据库(select)超时时间
        mysql_options(link_,MYSQL_OPT_WRITE_TIMEOUT,(const char *)&timeout);
        //设置写数据库(update,delect,insert,replace等)的超时时间。
    }
    //真正建立mysql链接
    char  host_name[1024] = "localhost";		//mysql服务器的IP
    char  user_name[1024] = "root";				//用户名
    char  user_password[1024] = "123456";		///密码		需要改为自己的密码
    char  server_name[1024] = "test";			///数据库名	需要改为自己的数据库名
    unsigned short host_port = 3306;			//服务器端口

    if(!mysql_real_connect(link_,host_name,user_name,user_password,server_name,host_port,NULL,0)) {
        //失败处理
        int error_code  =  mysql_errno(link_);            //获取错误码
        //针对不同的错误码error_code，还应该做不同的处理
        mysql_close(link_);                           //释放句柄
        printf("链接建立失败! :%s\n",mysql_error(link_));
    } else {
        puts("链接建立成功");
        //链接建立成功，可以进行具体的操作(select   insert    delete   update  replace等）
    }
/// 例子2，可以和上面一个例子分开运行
    puts("\n测试2...(链接并查询)");
    char SqlText[256] = "";
    MYSQL mysql;
    MYSQL_RES *res = NULL;
    MYSQL_FIELD * fd = NULL;
    MYSQL_ROW row;
    int i = 0;

    mysql_init( &mysql );

    if ( !mysql_real_connect( &mysql, "localhost", "root",
                              "123456", "test", 3306, NULL, 0) ) {
        puts("数据库连接失败");
        printf( "Error connecting to database: %s\n",mysql_error(&mysql));
        mysql_close( &mysql );
        return FALSE;
    } else {
        puts("数据库连接成功");
        mysql_query(&mysql,"set names 'GBK'");//设置字符集，防止中文无法正常显示
        sprintf( SqlText, "insert into animals(name, kg) values ('chicken',6), ('dog', 4)");
        if ( !mysql_query(&mysql, SqlText ) ) {        //insert失败
            printf("Can't insert data to table: ");
            printf("%s\n", mysql_error(&mysql));
            mysql_close( &mysql );
            return FALSE;
        }
        sprintf( SqlText, "select * from %s","stu");	///stu需要改为自己的数据库中对应的表名
        //进行数据检索
        if ( !mysql_query(&mysql, SqlText )) {
            res = mysql_store_result( &mysql );
            i = (int)mysql_num_rows( res );
            printf("Query: %s\n%d records found:\n", SqlText, i );
            //输出各字段名
            for (; fd = mysql_fetch_field(res);)
                printf("%-*s\t",50/mysql_num_fields(res), fd->name );
            puts("");

            //打印获取的数据
            MYSQL_ROW row; //一个行数据的类型安全(type-safe)的表示
            while (row = mysql_fetch_row(res)) {    //获取下一行
                for(int i=0; i<mysql_num_fields(res); i++)
                    printf("%-*s",80/mysql_num_fields(res),row[i]);
                puts("");
            }
            mysql_free_result( res );
        } else {
            printf("查询失败: %s\n",mysql_error(&mysql));
            puts("请更改66行表名");
            mysql_close( &mysql );
            return FALSE;
        }
    }
    mysql_close(&mysql);
    getchar();
    return TRUE;
}
