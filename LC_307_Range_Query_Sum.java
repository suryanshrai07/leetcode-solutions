class NumArray {
    int []nums;
    int []block;
    int n,blockSize;
    
    public NumArray(int[] nums) {
        int n = nums.length;
        this.n = n;

        this.nums = nums;

        blockSize = (int)Math.ceil(Math.sqrt(n));
        block = new int[blockSize];

        for(int i=0;i<n;i++)
        {
            int blockIndex = i / blockSize;
            block[blockIndex] += nums[i];
        }
    }
    
    public void update(int index, int val) {
        int blockIndex = index / blockSize;

        block[blockIndex] -= nums[index];
        block[blockIndex] += val;

        nums[index] = val;

    }
    
    public int sumRange(int left, int right) {
        int startBlock = left/blockSize;
        int endBlock = right/blockSize;

        int sum = 0;

        if(startBlock == endBlock)
        {
            for(int i=left;i<=right;i++)
            sum += nums[i];

            return sum;
        }

        int endOfStartBlock = (startBlock+1) * blockSize;
        for(int i=left;i<=endOfStartBlock-1;i++)
        {
            sum+=nums[i];
        }

        for(int i=startBlock+1;i<=endBlock-1;i++)
        sum += block[i];

        int startOfEndBlock = endBlock*blockSize;

        for(int i=startOfEndBlock;i<=right;i++)
        sum += nums[i];


        return sum;
    }
}

