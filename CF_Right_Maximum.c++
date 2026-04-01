#include <bits/stdc++.h>
using namespace std;

int main()
{
    int t;
    cin>>t;

    while(t--)
    {
        int n;
        cin>>n;

        vector<int>a(n);

        for(int i=0; i<n; i++)
        {
            cin>>a[i];
        }

        int op = 0;
        vector<int>rightMax(n);

        rightMax[0] = a[0];

        for(int i=0; i<n; i++)
        {   
            if( i > 0 )
            rightMax[i] = max(rightMax[i-1], a[i]);

            if(a[i] == rightMax[i])
            {
                op++;
            }
        }

        cout<<op<<endl;     
    }
}