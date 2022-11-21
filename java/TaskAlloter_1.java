import java.util.*;

class Task
{
    String task_name;

    //default constructor
    Task(){}

    //constructor with 1 String argument
    Task(String s){                     //constructor overloading
        task_name = s;
    }

    //function to give input about Task Name
    void setTaskName(String name){
        task_name = name;
    }

    //function to get output about Task Name
    String getTaskName(){
        return task_name;
    }
}

class ClubMember
{
    String name;
    int maxTasks;

    //default constructor
    ClubMember(){}

    //constructor with 2 argument(String, integer)
    ClubMember(String n , int maxT){            //constructor overloading
        name = n;
        maxTasks = maxT;
    }

    //function to set input about Club Member
    void setClubMember(String s , int maxT){
        name = s;
        maxTasks = maxT;
    }

}

class Club
{
    private List<ClubMember> club_member;
    private List<Task> task_info;

    Club(){
        club_member = new ArrayList<ClubMember>();
        task_info = new ArrayList<Task>();
    }

    // function to find factorial 
    private long factorial(int n)
    {
        long fact = 1;
        for (int i = 1; i <= n; i++){
            fact *= i;
        }
        return fact;
    }

    //Kth permutation utility function
    private void kthPermutationUtil(List<Integer> v, List<Boolean> arr, int len, int n, long k)
    {
        if (len == 0){
            return;
        }
        long fact = factorial(len - 1);
        long x = k / fact;
        int num;
        for (num = 0; num < n; num++)
        {
            if (arr.get(num) == false && ((x--) == 0)){
                break;
            }
        }
        v.add(num);
        arr.set(num,true);
        kthPermutationUtil(v, arr, len - 1, n, k % fact);
    }

    //function to found Kth permutation
    private List<Integer> kthPermutation(int n, long k)
    {
        List<Integer> v = new ArrayList<Integer>();
        List<Boolean> arr = new ArrayList<Boolean>(n);
        for(int i=0 ; i<n ; i++){
            arr.add(i,false);
        }

        kthPermutationUtil(v, arr, n, n, k - 1);
        return v;
    }

    //function to show possible allotment combination
    public void show_allotment()
    {
        int n = task_info.size();
        int mem = club_member.size();
        Random rand = new Random();
        long k = rand.nextLong(factorial(n)) + 1;

        List<Integer> ind = kthPermutation(n, k);
        
        for (int i = 0, j = 0; i < mem ; i++)
        {
            System.out.printf("%-10s : " , club_member.get(i).name);
            for (int l = j; l < j + club_member.get(i).maxTasks && l < n; l++){
                System.out.print(task_info.get(ind.get(l)).task_name + " ");
            }

            System.out.println("");
            j += club_member.get(i).maxTasks;
            if (j >= n){
                break;
            }

        }
    }

    //function to print club members name
    public void printClubMembers(){
        int n = club_member.size();
        for(int i=0 ; i<n ; i++){
            System.out.print(club_member.get(i) + " ");
        }
    }

    //function to add club member
    public void addMember(ClubMember mem){
        club_member.add(mem);
    }

    //function to get input about total member count, member name, task limit, total task, task info
    public void()
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter total no of members : ");
        int mem_count = sc.nextInt();
        sc.nextLine();
    
        for (int i = 0; i < mem_count; i++) {
            System.out.print("Enter name of " + (i + 1) + " person : ");
            String str = sc.nextLine();
            System.out.print("Enter max tasks : ");
            int maxTasks = sc.nextInt();
            sc.nextLine();
            club_member.add(new ClubMember(str,maxTasks));
        }
    
        System.out.print("Enter total no of Task to be alloted : ");
        int task_count = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < task_count; i++) {
            System.out.print("Enter task " + (i + 1) + " name : ");
            String task = sc.nextLine();
            task_info.add(new Task(task));
        }
        sc.close();
    }
}
public class TaskAlloter_1{
    public static void main(String[] args){
        Club club = new Club();
        club.take_input();
        club.show_allotment();
    }
}