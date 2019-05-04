#include <fstream>
#include <unordered_map>
#include <cstdlib>
#include <iostream>
#include <string>
#include "Account.h"
#include "Transaction.h"
#include "ATM.h"

/***
**function read data from file account.txt
***/
int readFileAccount(std::unordered_map<std::string,Account> &accounts);
/***
**mini project 1: function to login
***/
Account login(std::unordered_map<std::string,Account> accounts);
/***
**function read data from file transaction.txt
***/
int readFileTransaction(std::unordered_map<std::string,Transaction> &transactions);
/***
**mini project 2: function to calculate account balance of each user
***/
int calculateBalance(std::unordered_map<std::string,Transaction> transactions, std::unordered_map<std::string,Account> &accounts);
/***
**function read data from file atm.txt
***/
ATM readFileAtm();

/***
**mini project 4: calculate maximize the number of high-value bills 
***/
int withdrawAtm(long amount, ATM &atm, int bills[][2]);