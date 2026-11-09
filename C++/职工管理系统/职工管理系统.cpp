//#include <iostream>
//
///* run this program using the console pauser or add your own getch, system("pause") or input loop */
//
//int main(int argc, char** argv) {
//	return 0;
//}

#include<iostream>
#include"workerManager.h"
#include"worker.h"
#include"employee.h"
#include"manager.h"
#include"boss.h"
using namespace std;

int main()
{
	//测试代码:
//	Worker *worker=NULL;
//	worker=new Employee(1,"flj",1);
//	worker->showInfo(); 
//	delete worker;
//
//	worker=new Manager(2,"Flj",2);
//	worker->showInfo();
//	delete worker;
//	
//	worker=new Boss(3,"FLJ",3);
//	worker->showInfo();
//	delete worker;
	
	//实例化管理者对象
	WorkerManager wm;
	int choice=0;//用来存储用户的选项
	while(true)
	{
		//调用展示菜单成员函数
		wm.Show_Menu();
		cout<<"请输入您的选项"<<endl; 
		cin>>choice;//接受用户的选项
		switch(choice)
		{
			case 0://退出系统 
				wm.ExitSystem(); 
				break;
			case 1://增加职工
				wm.AddEmp(); 
				break;
			case 2://显示职工 
				wm.ShowEmp();
				break;
			case 3://删除职工
				wm.DelEmp();
				break;
			case 4://修改职工 
				wm.ModEmp(); 
				break;
			case 5://查找职工 
				wm.FindEmp();
				break;
			case 6://排序职工 
				wm.SortEmp();
				break;
			case 7://清空文档 
				wm.CleanFile(); 
				break;
			default:
				system("cls");//清屏操作 
				break;	
		} 
	}
	system("pause");
	return 0;
}
//case 3的测试代码 
//		{
//			int ret=wm.IsExist(1);
//			if(ret!=-1)
//			{
//				cout<<"职工存在"<<endl;	
//			} 
//			else
//			{
//				cout<<"职工不存在"<<endl; 
//			}
//			break;
//		}
