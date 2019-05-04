#include "Function.h"

int readFileAccount(std::unordered_map<std::string, Account> &accounts)
{
	std::string line;
	std::ifstream accountFile ("data/account.txt");
	if(!accountFile)
	{
		std::cerr << "Can not find account.txt file." << std::endl;
		exit(1);
	}

	std::string userId, name, password;
	while (getline(accountFile, line))
	{
		std::cout << line << std::endl;
		if(line.substr(0,6).compare("UserID")==0)
		{
			std::cout << line.substr(8) << std::endl;
			userId = line.substr(8);
			continue;
		}
		if(line.substr(0,4).compare("Name")==0)
		{
			std::cout << line.substr(6) << std::endl;
			name = line.substr(6);
			continue;
		}
		if(line.substr(0,8).compare("Password")==0)
		{
			std::cout << line.substr(10) << std::endl;
			password = line.substr(10);
			Account a(userId, name, password);
			std::pair<std::string,Account> account(userId,a);
			accounts.insert(account);
			userId = "";
			name = "";
			password = "";
		}
	}

	accountFile.close();
	return 1;
}
Account login(std::unordered_map<std::string,Account> accounts)
{	
	Account a;
	std::string inUserId,inPassword;
	std::cout << "Enter your userId: " << std::endl;
	std::getline(std::cin,inUserId);
	std::cout << "Enter your password: " << std::endl;
	std::getline(std::cin,inPassword);

	for(auto &account : accounts)
	{
		if(inUserId.compare(account.second.getId()) == 0)
		{
			if(inPassword.compare(account.second.getPassword()) == 0) return account.second;
		}
	}
	return a;
}

int readFileTransaction(std::unordered_map<std::string, Transaction> &transactions)
{
	std::string line = "";
	std::ifstream transactionFile("data/transaction.txt");
	if(!transactionFile)
	{
		std::cerr << "Can not find transaction.txt file." <<std::endl;
		exit(1);
	}

	
	std::string transactionId, type, fromUserId, toUserId;
	long amount;
	while(getline(transactionFile,line))
	{
		std::cout << line << std::endl;
		if(line.substr(0,13).compare("TransactionID")==0)
		{
			std::cout << line.substr(15) << std::endl;
			transactionId = line.substr(15);
			continue;
		}
		if(line.substr(0,4).compare("Type")==0)
		{
			std::cout << line.substr(6) << std::endl;
			type = line.substr(6);
			continue;
		}
		if(line.substr(0,10).compare("FromUserID")==0)
		{
			std::cout << line.substr(12) << std::endl;
			fromUserId = line.substr(12);
			continue;
		}
		if(line.substr(0,8).compare("ToUserID")==0)
		{
			std::cout << line.substr(10) << std::endl;
			toUserId = line.substr(10);
			continue;
		}
		if(line.substr(0,6).compare("Amount")==0)
		{
			std::cout << std::stol(line.substr(8)) << std::endl;
			amount = std::stol(line.substr(8));
			Transaction t(transactionId, type, fromUserId, toUserId, amount);
			std::pair<std::string,Transaction> transaction(transactionId, t);
			transactions.insert(transaction);
			transactionId = type = fromUserId = toUserId = "";
			amount = 0;
		}
	}
	transactionFile.close();
	return 1;
}

int calculateBalance(std::unordered_map<std::string,Transaction> transactions, std::unordered_map<std::string,Account> &accounts)
{
	for(auto &t : transactions)
	{
		std::string type = t.second.getType();
		if(type.compare("withdraw")==0)
		{
			std::string fromUser = t.second.getFromUserId();
			long amount = t.second.getAmount();
			accounts.at(fromUser).withdraw(amount);
			continue;
		}
		if(type.compare("deposit")==0)
		{
			std::string toUser = t.second.getToUserId();
			long amount = t.second.getAmount();
			accounts.at(toUser).deposit(amount);
			continue;
		}
		if(type.compare("transfer")==0)
		{
			std::string fromUser = t.second.getFromUserId();
			std::string toUser = t.second.getToUserId();
			long amount = t.second.getAmount();
			accounts.at(fromUser).transfer(amount);
			accounts.at(toUser).receiveTransfer(amount);
		}
	}
	return 1;
}

ATM readFileAtm()
{
	ATM a;
	std::string line = "";
	std::ifstream atmFile ("data/atm.txt");
	if(!atmFile)
	{
		std::cerr << "Can not find atm.txt file" << std::endl;
		exit(1);
	}
	std::string atmId;
	int bill500, bill200, bill100, bill50;
	while(std::getline(atmFile,line))
	{
		if(line.substr(0,6).compare("ATM ID")==0)
		{
			std::cout << line.substr(8) << std::endl;
			atmId = line.substr(8);
			continue;
		}
		if(line.substr(0,9).compare("Bill 500k")==0)
		{
			std::cout << std::stoi(line.substr(11)) << std::endl;
			bill500 = std::stoi(line.substr(11));
			continue;
		}
		if(line.substr(0,9).compare("Bill 200k")==0)
		{
			std::cout << std::stoi(line.substr(11)) << std::endl;
			bill200 = std::stoi(line.substr(11));
			continue;
		}
		if(line.substr(0,9).compare("Bill 100k")==0)
		{
			std::cout << std::stoi(line.substr(11)) << std::endl;
			bill100 = std::stoi(line.substr(11));
			continue;
		}
		if(line.substr(0,8).compare("Bill 50k")==0)
		{
			std::cout << std::stoi(line.substr(10)) << std::endl;
			bill50 = std::stoi(line.substr(10));
			int bills[4];
			bills[0] = bill500;
			bills[1] = bill200;
			bills[2] = bill100;
			bills[3] = bill50;
			ATM atm (atmId, bills);
			return atm;

		}
	}
	return a;
}

int withdrawAtm(long amount, ATM &atm, int bills[][2])
{
	int tempBills[4];
	atm.getBills(tempBills);
	int currBill = 0;
	while(amount != 0)
	{
		if(currBill == 4)
		{
			atm.setBills(tempBills);
			return -1;
		}
		bills[currBill][1] = amount/bills[currBill][0];
		if(bills[currBill][1] > atm.getBill(currBill))
		{
			bills[currBill][1] = atm.getBill(currBill);
			amount-= bills[currBill][0]* bills[currBill][1];
			atm.setBill(currBill,0);
		}
		else
		{
			amount-= bills[currBill][0]*bills[currBill][1];
			atm.setBill(currBill, atm.getBill(currBill) - bills[currBill][1]);
		}
		currBill++;
	}
	return 1;
}
