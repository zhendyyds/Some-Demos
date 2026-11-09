#pragma once//防止头文件重复包含(弹幕说可用#IFNDEFIEN)
#include<iostream>//包含输入输出流头文件 
#include"worker.h"
#include"worker.h"
#include"employee.h"
#include"manager.h"
#include"boss.h"
#include<fstream>
#define FILENAME "empFile.text"
using namespace std;//使用标准命名空间 
class WorkerManager
{
	public:
		//构造函数 
		WorkerManager();
		
		//展示菜单
		void Show_Menu(); 
		 
		//退出系统
		void ExitSystem(); 
		
		//记录职工人数
		int EmpNum;
		
		//职工数组指针
		Worker **EmpArray;
		
		//添加职工
		void AddEmp();
		
		//保存文件
		void save(); 
		
		//判断文件是否为空  标志
		bool  FileIsEmpty;
		
		//统计文件中人数
		int getEmpNum();
		
		//初始化员工
		void initEmp(); 
		
		//显示职工
		void ShowEmp(); 
		
		//删除职工
		void DelEmp(); 
		
		//判断职工是否存在,如果存在返回职工所在数组中的位置,不存在返回-1
		int IsExist(int id); 
		
		//修改职工
		void ModEmp(); 
		
		//查找职工
		void FindEmp();
		
		//排序职工(按照编号排序)
		void SortEmp(); 
		
		//清空文件
		void CleanFile(); 
		 
		//析构函数 
		~WorkerManager();
		
};
