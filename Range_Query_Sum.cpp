/*
Range Minimum Query
Difficulty: MediumAccuracy: 56.27%Submissions: 12K+Points: 4
Given an array A[ ] and its size N your task is to complete two functions  a constructST  function which builds the segment tree  and a function RMQ which finds range minimum query in a range [a,b] of the given array.

Input:
The task is to complete two functions constructST and RMQ.
The constructST function builds the segment tree and takes two arguments the array A[ ] and the size of the array N.
It returns a pointer to the first element of the segment tree array.
The RMQ function takes 4 arguments the first being the segment tree st constructed, second being the size N and then third and forth arguments are the range of query a and b. The function RMQ returns the min of the elements in the array from index range a and b. There are multiple test cases. For each test case, this method will be called individually.

Output:
The function RMQ should return the min element in the array from range a to b.

Example:

Input (To be used only for expected output) 
1
4
1 2 3 4
2
0 2 2 3
Output
1 3
Explanation
1. For query 1 ie 0 2 the element in this range are 1 2 3 
   and the min element is 1. 
2. For query 2 ie 2 3 the element in this range are 3 4 
   and the min element is 3.
Constraints:
1<=T<=100
1<=N<=10^3+1

1<=A[i]<=10^9
1<=Q(no of queries)<=10000
0<=a<=b

*/

/* The functions which
builds the segment tree */

#include <bits/stdc++.h>
using namespace std;    
void build(int i,int l,int r,int arr[],int *st)
{
    if(l == r){
        st[i] = arr[l];
        return;
    }
    
    int mid = (l+r)/2;
    
    build(2*i+1,l,mid,arr,st);
    build(2*i+2,mid+1,r,arr,st);
    
    st[i] = min(st[2*i+1],st[2*i+2]);
}


int *constructST(int arr[], int n) {
    // Your code here
   int *st = new int[4*n]{};
   build(0,0,n-1,arr,st);
   return st;
}

/* The functions returns the
 min element in the range
 from a and b */
 
int mini(int start,int end,int i ,int l,int r,int st[])
{
    if(r < start || l > end)
    return INT_MAX;
    
    if(l >= start && r <= end)
    return st[i];
    
    int mid = (l+r)/2;
    
    return min(mini(start,end,2*i+1,l,mid,st),mini(start,end,2*i+2,mid+1,r,st));
}

int RMQ(int st[], int n, int a, int b) {
    
    return mini(a,b,0,0,n-1,st);
    
}