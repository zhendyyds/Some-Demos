#include"boss.h"
//构造函数
Boss::Boss(int id,string name,int did)
{
	this->id=id;
	this->name=name;
	this->deptid=did;
}

//显示个人信息
void Boss::showInfo()
{
	cout<<"职工编号:"<<this->id
		<<"\t职工姓名:"<<this->name
		<<"\t岗位:"<<this->getDeptName()
		<<"\t岗位职责:管理公司所有事物"<<endl;
}

//获取岗位名称
string Boss::getDeptName()
{
	return string("总裁");
} 
