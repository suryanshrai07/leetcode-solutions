/*
You are given an array of CPU tasks, each labeled with a letter from A to Z, and a number n. Each CPU interval can be idle or allow the completion of one task. Tasks can be completed in any order, but there's a constraint: there has to be a gap of at least n intervals between two tasks with the same label.

Return the minimum number of CPU intervals required to complete all tasks.

 

Example 1:

Input: tasks = ["A","A","A","B","B","B"], n = 2

Output: 8

Explanation: A possible sequence is: A -> B -> idle -> A -> B -> idle -> A -> B.

After completing task A, you must wait two intervals before doing A again. The same applies to task B. In the 3rd interval, neither A nor B can be done, so you idle. By the 4th interval, you can do A again as 2 intervals have passed.

Example 2:

Input: tasks = ["A","C","A","B","D","B"], n = 1

Output: 6

Explanation: A possible sequence is: A -> B -> C -> D -> A -> B.

With a cooling interval of 1, you can repeat a task after just one other task.

Example 3:

Input: tasks = ["A","A","A", "B","B","B"], n = 3

Output: 10

Explanation: A possible sequence is: A -> B -> idle -> idle -> A -> B -> idle -> idle -> A -> B.

There are only two types of tasks, A and B, which need to be separated by 3 intervals. This leads to idling twice between repetitions of these tasks.

 

Constraints:

1 <= tasks.length <= 104
tasks[i] is an uppercase English letter.
0 <= n <= 100
 

*/



import java.util.*;

class Solution {
    public int leastInterval(char[] tasks, int n) {
        int [] freq = new int[26];

        for(char c : tasks){
            freq[c-'A']++;
        }

        PriorityQueue<Integer>pq = new PriorityQueue<>(Collections.reverseOrder());

        for(int i=0;i<26;i++){
            if(freq[i] != 0)
            pq.add(freq[i]);
        }

        int timer = 0;

        while(!pq.isEmpty())
        {
            int front = pq.peek();
            pq.poll();
            front--;

            ArrayList<Integer>list = new ArrayList<Integer>();

            list.add(front);
            for(int i=0;i<n;i++)
            {
                if(!pq.isEmpty())
                {
                int ele = pq.peek();
                ele--;
                pq.poll();
                list.add(ele);
                }
            }
            
            for(int i : list){
                if(i != 0)
                pq.add(i);
            }

            if(pq.isEmpty()){
                timer+=list.size();
            }
            else{
                timer+=(n+1);
            }
        }

        return timer;
    }
}