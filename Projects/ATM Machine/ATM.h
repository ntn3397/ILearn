#include <string>
#include <array>
class ATM
{
protected:
	std::string id;
	int bills[4];
public:
	ATM();
	ATM(std::string id, int bills[]);
	~ATM();
	std::string getId();
	int setId(std::string id);
	int setBills(int bills[]);
	int getBills(int bills[]);
	int setBill(int pos,int amount);
	int getBill(int pos);
	int getBill500();
	int setBill500(int bill500);
	int getBill200();
	int setBill200(int bill200);
	int getBill100();
	int setBill100(int bill100);
	int getBill50();
	int setBill50(int bill50);
};