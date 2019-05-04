#include <string>
#include <iostream>
class Account
{
protected:
	std::string id;
	std::string name;
	std::string password;
	long balance;
public:
	static int count;
	Account();
	Account(std::string id, std::string name, std::string password);
	~Account();
	std::string getId();
	int setId(std::string id);
	std::string getName();
	int setName(std::string name);
	std::string getPassword();
	int setPassword(std::string password);
	long getBalance();
	int setBalance(long balance);
	int withdraw(long amount);
	int transfer(long amount);
	int receiveTransfer(long amount);
	int deposit(long amount);
};