#include"manager.h"
//构造函数
Manager::Manager(int id,string name,int did)
{
	this->id=id;
	this->name=name;
	this->deptid=did;
}

//显示个人信息
void Manager::showInfo()
{
	cout<<"职工编号:"<<this->id
		<<"\t职工姓名:"<<this->name
		<<"\t岗位:"<<this->getDeptName()
		<<"\t岗位职责:完成总裁交给的任务,并下发任务给员工"<<endl;
}

//获取岗位名称
string Manager::getDeptName()
{
	return string("经理");
} 
