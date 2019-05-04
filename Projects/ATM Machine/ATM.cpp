#include "ATM.h"

ATM::ATM()
{
	(*this).id = "";
	(*this).bills[0] = 0;
	(*this).bills[1] = 0;
	(*this).bills[2] = 0;
	(*this).bills[3] = 0;
}

ATM::ATM(std::string id, int bills[])
{
	(*this).id = id;
	(*this).bills[0] = bills[0];
	(*this).bills[1] = bills[1];
	(*this).bills[2] = bills[2];
	(*this).bills[3] = bills[3];
}

ATM::~ATM(){}
std::string ATM::getId()
{
	return (*this).id;
}
int ATM::setId(std::string id)
{
	(*this).id = id;
	return 1;
}
int ATM::getBills(int bills[])
{
	bills[0] = (*this).bills[0];
	bills[1] = (*this).bills[1];
	bills[2] = (*this).bills[2];
	bills[3] = (*this).bills[3];
	return 1;
}
int ATM::setBills(int bills[])
{
	(*this).bills[0] = bills[0];
	(*this).bills[1] = bills[1];
	(*this).bills[2] = bills[2];
	(*this).bills[3] = bills[3];
	return 1;
}

int ATM::setBill(int pos, int amount)
{
	if(pos <0 || pos >=4)
	{
		return -1;
	}
	(*this).bills[pos] = amount;
	return 1;
}
int ATM::getBill(int pos)
{
	if(pos <0 || pos >=4)
	{
		return -1;
	}
	return (*this).bills[pos];
}
int ATM::getBill500()
{
	return (*this).bills[0];
}

int ATM::setBill500(int bill500)
{
	(*this).bills[0] = bill500;
	return 1;
}

int ATM::getBill200()
{
	return (*this).bills[1];
}

int ATM::setBill200(int bill200)
{
	(*this).bills[1] = bill200;
	return 1;
}

int ATM::getBill100()
{
	return (*this).bills[2];
}
int ATM::setBill100(int bill100)
{
	(*this).bills[2] = bill100;
	return 1;
}
int ATM::getBill50()
{
	return (*this).bills[3];
}
int ATM::setBill50(int bill50)
{
	(*this).bills[3] = bill50;
	return 1;
}