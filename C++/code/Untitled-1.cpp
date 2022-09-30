#include<iostream>
#include<string>
using namespace std;
int main() {
    int select;
    while (true) {
        cout << "欢迎来到预约系统 请选择您的身份" << endl;
        cout << "1.学生" << endl << "2.老师" << endl << "3.管理员" << endl << "4.退出" << endl;
        cin >> select;
        select += 0;
        switch(select){
            case 1:
                cout << "学生" << endl;
                break;
            case 2:
                cout << "老师" << endl;
                break;
            case 3:
                cout << "管理员" << endl;
                break;
            case 4:
                cout << "退出" << endl;
                return 0;
            default:
                cout << "输入错误" << endl;
                break;
        }
    }
        system("pause");
}