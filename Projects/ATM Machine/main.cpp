#include "Function.h"

int main(int argc, char const *argv[])
{
	//Read account.txt and store in accounts <Account.userId, Account> map for access contant time (or worst case is linear time).
	std::unordered_map<std::string,Account> accounts;
	readFileAccount(accounts);
	for(auto &a : accounts)
	{
		std::cout<< a.second.getId() << std::endl;
	}
	//Mini project 2: process transaction.txt and store in transactions <Transaction.transactionId, Transaction> map
	std::unordered_map<std::string,Transaction> transactions;
	readFileTransaction(transactions);
	//Mini project 2: calculateBalance
	calculateBalance(transactions,accounts);

	//opt
	std::string opt;
	while (true)
	{
		std::cout << "**********" << std::endl;
		std::cout << "Welcome, This is ATM Machine." << std::endl;
		//mini project 1: login function
		Account currentAccount;
		currentAccount = login(accounts);
		if(currentAccount.getId().compare("")==0)
		{
			std::cout << "username or password incorect! Do you want to continue? y/n";
			std::string c;
			std::getline(std::cin,c);
			if(c.compare("N") == 0 || c.compare("n") == 0)
			{
				continue;
			} 
			else if(c.compare("Y") == 0 || c.compare("y") == 0)
			{
				continue;
			} 
			else
			{
				while(c.compare("y")!=0 || c.compare("Y")!=0 || c.compare("n")!=0 || c.compare("N")!=0)
				{
					std::cout << "Please choose y/n Y/N";
					std::getline(std::cin,c);
					if(c.compare("N") == 0 || c.compare("n") == 0)
					{
						exit(1);
					}

					if(c.compare("Y") == 0 || c.compare("y") == 0)
					{
						break;
					}
				}
			}
			if(c.compare("Y") == 0 || c.compare("y") == 0)
			{
				continue;
			}
		}

		while(true)
		{
			std::cout <<"Select your option:" << std::endl;
			std::cout << "- Press 1 to transfer money." << std::endl;
			std::cout << "- Press 2 to withdraw money." << std::endl;
			std::cout << "- Press 3 to see current balance" << std::endl;
			std::cout << "- Press q/Q to exit." << std::endl;

			std::getline(std::cin, opt);

			while(opt.compare("1")!=0 && opt.compare("2")!=0 &&opt.compare("3")!=0 && opt.compare("q")!=0 && opt.compare("Q")!=0)
			{
				std::cout << "Unknown option. Please select again! " << opt << std::endl;
				std::getline(std::cin, opt);
			}

			if(opt.compare("1")==0)
			{
				std::string accountReceiveTransferId;
				std::cout << "Insert userId you want to transfer money: " << std::endl;
				std::getline(std::cin,accountReceiveTransferId);
				Account accountReceiveTransfer;
				try
				{
				    accountReceiveTransfer = accounts.at(accountReceiveTransferId);
				    std::cout << "Insert amount: " << std::endl;
				    std::string inAmount;
				    long amount = 0;
				    std::getline(std::cin, inAmount);
				    if(isdigit(inAmount[0]))
				    {
				    	amount = stol(inAmount);
				    }
				    else
				    {
				    	std::cout << "<!><!><!> Error! Please insert number only!<!><!><!>" <<std::endl;
				    	continue;
				    }

				    currentAccount.transfer(amount);
				    accountReceiveTransfer.receiveTransfer(amount);
				    Transaction t(std::to_string(transactions.at("1").count + 1), "transfer", currentAccount.getId(), accountReceiveTransfer.getId(),amount);
				    std::ofstream transactionFile("data/transaction.txt",std::ios_base::app);
				    
				    transactionFile << "\n";	
				    transactionFile << "TransactionID: " << t.getId() << "\n";
				    transactionFile << "Type: " << t.getType() << "\n";
				    transactionFile << "FromUserID: " << t.getFromUserId() << "\n";
				    transactionFile << "ToUserID: " << t.getToUserId() << "\n";
				    transactionFile << "Amount: " << t.getAmount() << "\n";

				    transactionFile.close();

				    std::cout << currentAccount.getId() << " " << currentAccount.getBalance() << std::endl;
				    std::cout << accountReceiveTransfer.getId() << " " << accountReceiveTransfer.getBalance() << std::endl;

				}
				catch (std::exception const &exc)
				{
				    std::cerr << "Exception caught " << exc.what() << "\n";
				    std::cout << "<!><!><!> Error! Can not find this userId.<!><!><!>" <<std::endl;
				}
				catch (...)
				{
				    std::cerr << "Unknown exception caught\n";
				}

			}
			if(opt.compare("2")==0)
			{
				std::cout << "Insert amount: " << std::endl;
				std::string inAmount;
				long amount = 0;
				std::getline(std::cin, inAmount);
				if(isdigit(inAmount[0]))
				{
					amount = stol(inAmount);
				}
				else
				{
					std::cout << "<!><!><!> Error! Please insert number only!<!><!><!>" <<std::endl;
				    continue;
				}
				if(amount%50000 != 0)
				{
					std::cout << "<!><!><!> Error! Please insert number multiples of 50000.!<!><!><!>" <<std::endl;
					continue;
				}
				if(amount > currentAccount.getBalance())
				{
					std::cout << "<!><!><!> Error! Your balance is not enough.!<!><!><!>" <<std::endl;
					continue;
				}
				ATM atm = readFileAtm();
				int bills[4][2]= {
					{500000,0},
					{200000,0},
					{100000,0},
					{50000,0}
				};

				std::cout << atm.getBill500() << " " << atm.getBill200() << " " << atm.getBill100() << " " << atm.getBill50() << std::endl;
				if(withdrawAtm(amount, atm, bills) == -1)
				{
					std::cout << "Sorry. We do not have enough money. Please come back later!" << std::endl;
					std::cout << atm.getBill500() << " " << atm.getBill200() << " " << atm.getBill100() << " " << atm.getBill50() << std::endl;
					continue;
				}
				else
				{
					std::cout << "Withdraw complete. Please take money" << std::endl;
					std::cout << "Money you receive is: " << bills[0][1] << "x500k + " << bills[1][1] << "x200k " << bills[2][1] << "x100k " << bills[3][1] << "x50k" << std::endl;
				}
				std::cout << atm.getBill500() << " " << atm.getBill200() << " " << atm.getBill100() << " " << atm.getBill50() << std::endl;

				std::ofstream atmFile("data/atm.txt", std::ofstream::out | std::ofstream::trunc);
				atmFile << "ATM ID: " << atm.getId() << "\n";
				atmFile << "Bill 500k: " << atm.getBill500() << "\n";
				atmFile << "Bill 200k: " << atm.getBill200() << "\n";
				atmFile << "Bill 100k: " << atm.getBill100() << "\n";
				atmFile << "Bill 50k: " << atm.getBill50() << "\n";

				atmFile.close();

			}
			if(opt.compare("3")==0)
			{
				std::cout << "Current balance: " << currentAccount.getBalance() << std::endl;
			}
			if(opt.compare("q")==0 || opt.compare("Q")==0)
			{
				break;
			}
			

			std::string c;
			std::cout << "Do you want to process something else? Y/n" << std::endl;
			std::getline(std::cin,c);
			if(c.compare("N") == 0 || c.compare("n") == 0)
			{
				break;
			} 
			else if(c.compare("Y") == 0 || c.compare("y") == 0)
			{
				continue;
			} 
			else
			{
				while(c.compare("y")!=0 || c.compare("Y")!=0 || c.compare("n")!=0 || c.compare("N")!=0)
				{
					std::cout << "Please choose y/n Y/N";
					std::getline(std::cin,c);
					if(c.compare("N") == 0 || c.compare("n") == 0)
					{
						break;
					}

					if(c.compare("Y") == 0 || c.compare("y") == 0)
					{
						break;
					}
				}
			}
			if(c.compare("N") == 0 || c.compare("n") == 0)
			{
				break;
			}
			if(c.compare("Y") == 0 || c.compare("y") == 0)
			{
				continue;
			}
		}
	}
	return 0;
}