/*

A binary watch has 4 LEDs on the top to represent the hours (0-11), and 6 LEDs on the bottom to represent the minutes (0-59). Each LED represents a zero or one, with the least significant bit on the right.

For example, the below binary watch reads "4:51".


Given an integer turnedOn which represents the number of LEDs that are currently on (ignoring the PM), return all possible times the watch could represent. You may return the answer in any order.

The hour must not contain a leading zero.

For example, "01:00" is not valid. It should be "1:00".
The minute must consist of two digits and may contain a leading zero.

For example, "10:2" is not valid. It should be "10:02".
 

Example 1:

Input: turnedOn = 1
Output: ["0:01","0:02","0:04","0:08","0:16","0:32","1:00","2:00","4:00","8:00"]
Example 2:

Input: turnedOn = 9
Output: []
 

Constraints:

0 <= turnedOn <= 10

*/

class Solution {
    List<String>ans = new ArrayList<String>();
    List<Integer>leds = new ArrayList<Integer>(Arrays.asList(1,2,4,8,1,2,4,8,16,32));

    void backtrack(int turnedOn,int idx,int h,int m)
    {
        if(h > 11 || m > 59) return;

        if(turnedOn == 0)
        {
            if(m < 10)
            ans.add(h+":0"+m);
            else
            ans.add(h+":"+m);

            return;
        }

        if(idx == leds.size()) return;

        if(idx < 4){
            backtrack(turnedOn-1,idx+1,h+leds.get(idx),m);
        }
        else{
            backtrack(turnedOn-1,idx+1,h,m+leds.get(idx));
        }

        backtrack(turnedOn,idx+1,h,m);
    }

    public List<String> readBinaryWatch(int turnedOn) {
        backtrack(turnedOn,0,0,0);

        return ans;
    }
}