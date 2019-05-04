#include "Transaction.h"

int Transaction::count = 0;
Transaction::Transaction()
{
	(*this).id = "";
	(*this).type = "";
	(*this).fromUserId = "";
	(*this).toUserId = "";
	(*this).amount = 0;
}
Transaction::Transaction(std::string id, std::string type, std::string fromUserId, std::string toUserId, long amount)
{
	(*this).id = id;
	(*this).type = type;
	(*this).fromUserId = fromUserId;
	(*this).toUserId = toUserId;
	(*this).amount = amount;
	count++;
}
Transaction::~Transaction(){}
std::string Transaction::getId()
{
	return (*this).id;
}
int Transaction::setId(std::string id)
{
	(*this).id = id;
	return 1;
}
std::string Transaction::getType()
{
	return (*this).type;
}
int Transaction::setType(std::string type)
{
	(*this).type = type;
	return 1;
}
std::string Transaction::getFromUserId()
{
	return (*this).fromUserId;
}
int Transaction::setFromUserId(std::string fromUserId)
{
	(*this).fromUserId = fromUserId;
	return 1;
}
std::string Transaction::getToUserId()
{
	return (*this).toUserId;
}
int Transaction::setToUserId(std::string toUserId)
{
	(*this).toUserId = toUserId;
	return 1;
}
long Transaction::getAmount()
{
	return (*this).amount;
}
int Transaction::setAmount(long amount)
{
	(*this).amount = amount;
	return 1;
}