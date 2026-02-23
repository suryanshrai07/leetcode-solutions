import java.util.*;

class Leet_1461 {
    public boolean hasAllCodes(String s, int k) {
        int power = (int)Math.pow(2,k);
        int n = s.length();
        HashSet<String>st = new HashSet<>();

        for(int i=0;i<=n-k;i++)
        {
            st.add(s.substring(i,i+k));

            if(st.size() == power) return true;
        }

        return false;
    }
}