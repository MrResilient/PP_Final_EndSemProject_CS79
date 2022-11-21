#include <bits/stdc++.h>
using namespace std;

class Task
{
public:
    string task_name;

    //default constructor
    Task(){}

    //constructor with 1 String argument
    Task(string s){              //constructor overloading
        task_name = s;
    }

    //function to give input about Task Name
    void setTaskName(string name){      
        task_name = name;
    }

    //function to get output about Task Name
    string getTaskName(){
        return task_name;
    }
};

class ClubMember
{
    public:
        string name;
        int maxTasks;

       //default constructor
        ClubMember(){}

       //constructor with 2 argument(String, integer)
        ClubMember(string n , int maxT){                 //constructor overloading
            name = n;
            maxTasks = maxT;
        }

       //function to set input about Club Member
        void setClubMember(string s , int maxT){
            name = s;
            maxTasks = maxT;
        }
};

typedef long long int ll;
class Club
{
private:
    vector<ClubMember> club_member;
    vector<Task> task_info;

    // function to find factorial 
    ll factorial(int n)
    {
        ll fact = 1;
        for (int i = 1; i <= n; i++){
            fact *= i;
        }
        return fact;
    }

    //Kth permutation utility function
    void kthPermutationUtil(vector<int> &v, vector<bool> &arr, int len, int n, ll k)
    {
        if (len == 0)
        {
            return;
        }
        ll fact = factorial(len - 1);
        ll x = k / fact;
        int num;
        for (num = 0; num < n; num++)
        {
            if (arr[num] == false && ((x--) == 0))
            {
                break;
            }
        }
        v.push_back(num);
        arr[num] = true;
        kthPermutationUtil(v, arr, len - 1, n, k % fact);
    }

    //function to found Kth permutation
    vector<int> kthPermutation(int n, ll k)
    {
        vector<int> v;
        vector<bool> arr(n , false);
        kthPermutationUtil(v, arr, n, n, k - 1);
        return v;
    }
public:
    //function to show possible allotment combination
    void show_allotment()
    {
        int n = task_info.size();
        int mem = club_member.size();
        srand(time(0)); // seeding
        ll k = (rand() % factorial(n)) + 1;

        vector<int> ind = kthPermutation(n, k);
        
        for (int i = 0, j = 0; i < mem ; i++)
        {
            cout << setw(10) << club_member[i].name << " : ";
            for (int l = j; l < j + club_member[i].maxTasks && l < n; l++)
            {
                cout << task_info[ind[l]].task_name << " ";
            }

            cout << "\n";
            j += club_member[i].maxTasks;
            if (j >= n){
                break;
            }

        }
    }

    //function to print club members name
    void printClubMembers(){
        int n = club_member.size();
        for(int i=0 ; i<n ; i++){
            cout << club_member[i].name << " ";
        }
    }

    //function to add club member
    void addMember(ClubMember mem){
        club_member.push_back(mem);
    }

    //function to get input about total member count, member name, task limit, total task, task info
    void take_input()
    {
        int mem_count, task_count;
        cout << "Enter total no of members: ";
        cin >> mem_count;
        cin.ignore();

        for (int i = 0; i < mem_count; i++) {
            cout << "Enter name of " << (i + 1) << " person : ";
            string str;
            getline(cin,str);
            cout << "Enter max tasks : ";
            int maxTasks; cin >> maxTasks;
            cin.ignore();
            club_member.push_back({str,maxTasks});
        }

            cout << "Enter total no of Task to be alloted : ";
            cin >> task_count;
            cin.ignore();

            for (int i = 0; i < task_count; i++) {
                string task;
                cout << "Enter task " << (i + 1) << " name : ";
                getline(cin,task);
                task_info.push_back({task});
            }
    }
};
int main()
{
    Club club;
    club.take_input();
    club.show_allotment();

    return 0;
}