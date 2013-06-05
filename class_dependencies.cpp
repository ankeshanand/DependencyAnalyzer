#include<iostream>
#include<fstream>
#include<string>
#include<vector>

using namespace std;

int main()
{
	vector<string> class_list;
	ifstream fileInput;
	string line;
	string search="RegularFileObject";	
	fileInput.open("javac_out.txt");
	while(getline(fileInput,line))
	{
		if(line.find(search,0)!= string::npos){
			int found=line.find(search);
			found=found+search.length()+1;
			string class_found=line.substr(found,100);
			class_found=class_found.substr(0,class_found.length()-2);
			cout<<class_found<<endl;
			class_list.push_back(class_found);
		}
	}
	cout<<class_list.size()-1<<" class dependencies found."<<endl;
	/*for(int i=1;i<class_list.size();i++)
	{
		cout<<class_list[i]<<endl;
	}*/
	fileInput.close();
}


		
