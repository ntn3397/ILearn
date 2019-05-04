#include "Account.h"

int Account::count = 0;
Account::Account()
{
	(*this).id = "";
	(*this).name = "";
	(*this).password = "";
	(*this).balance = 0;
}
Account::Account(std::string id, std::string name, std::string password)
{
	(*this).id = id;
	(*this).name = name;
	(*this).password = password;
	(*this).balance = 0;
	count++;
}
Account::~Account(){}

std::string Account::getId()
{
	return (*this).id;
}

int Account::setId(std::string id)
{
	(*this).id = id;
	return 1;
}

std::string Account::getName()
{
	return (*this).name;
}

int Account::setName(std::string name)
{
	(*this).name = name;
	return 1;
}
std::string Account::getPassword()
{
	return (*this).password;
}
int Account::setPassword(std::string password)
{
	(*this).password = password;
	return 1;
}

long Account::getBalance()
{
	return (*this).balance;
}
int Account::setBalance(long balance)
{
	(*this).balance = balance;
	return 1;
}

int Account::withdraw(long amount)
{
	(*this).balance -= amount;
	return 1;
}
int Account::transfer(long amount)
{
	(*this).balance -= amount;
	return 1;
}
int Account::receiveTransfer(long amount)
{
	(*this).balance += amount;
}
int Account::deposit(long amount)
{
	(*this).balance += amount;
	return 1;
}