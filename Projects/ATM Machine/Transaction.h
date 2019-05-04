#include <string>
class Transaction
{
protected:
	std::string id;
	std::string type;
	std::string fromUserId;
	std::string toUserId;
	long amount;
public:
	static int count;
	Transaction();
	Transaction(std::string id, std::string type, std::string fromUserId, std::string toUserId, long amount);
	~Transaction();
	std::string getId();
	int setId(std::string id);
	std::string getType();
	int setType(std::string type);
	std::string getFromUserId();
	int setFromUserId(std::string fromUserId);
	std::string getToUserId();
	int setToUserId(std::string toUserId);
	long getAmount();
	int setAmount(long amount);
	
};