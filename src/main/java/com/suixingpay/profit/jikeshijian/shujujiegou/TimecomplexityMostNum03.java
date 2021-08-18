package com.suixingpay.profit.jikeshijian.shujujiegou;

public class TimecomplexityMostNum03 {
    /**
     * 分析：
     * 1。其中第14、15行代码都是常量级的执行时间，与n的大小无关，所以对于复杂度并没有影响
     * 2。循环执行次数最多的是第8、9行代码，所以这块代码要重点分析
     */
    //1.只关注循环执行次数最多的一段代码
    int cal01(int n) {
        int sum = 0;
        int i = 1;
        for (; i <= n; ++i) {
            sum = sum + i;
        }
        return sum;
    }

    /**
     * 分析：
     * 1。分别分析每一部分的时间复杂度，然后把它们放到一块儿，再取一个量级最大的作为整段代码 的复杂度。
     * 1。sum_1：这段代码循环执行了100次，所以是一个常量的执行时间，跟n的规模无关。
     * 2。sum_2：O(n)
     * 3。sum_3：O(n2)
     * 2。综合这三段代码的时间复杂度，我们取其中最大的量级。
     * 3。整段代码的时间复杂度就为O(n2)。，总的时间复杂度就等于量级最大的那段代码的时间复杂度
     * 抽象成公式：
     * T1(n)=O(f(n))，T2(n)=O(g(n));
     * 那么T(n)=T1(n)+T2(n)=max(O(f(n)), O(g(n))) =O(max(f(n), g(n))).
     */
    //2.加法法则:总复杂度等于量级最大的那段代码的复杂度
    int cal02(int n) {
        int sum_1 = 0;
        int p = 1;
        for (; p < 100; ++p) {
            sum_1 = sum_1 + p;
        }

        int sum_2 = 0;
        int q = 1;
        for (; q < n; ++q) {
            sum_2 = sum_2 + q;
        }

        int sum_3 = 0;
        int i = 1;
        int j = 1;
        for (; i <= n; ++i) {
            for (; j <= n; ++j) {
                sum_3 = sum_3 + i * j;
            }
        }

        return sum_1 + sum_2 + sum_3;
    }

    /**
     * 分析：
     * 1。单独看cal()函数，假设f()只是一个普通的操作，那第74~75行的时间复杂度就是，T1(n) = O(n)。
     * 2。但f()函数本身不是一个简单的操作，它的时间复杂度是T2(n) = O(n)
     * 3。整个cal()函数的时间复杂度就是，T(n) = T1(n) * T2(n) = O(n*n) = O(n2)。
     */
    // 3.乘法法则:嵌套代码的复杂度等于嵌套内外代码复杂度的乘积
    int cal03(int n) {
        int ret = 0;
        int i = 1;
        for (; i < n; ++i) {
            ret = ret + f(i);
        }
        return ret;
    }

    int f(int n) {
        int sum = 0;
        int i = 1;
        for (; i < n; ++i) {
            sum = sum + i;
        }
        return sum;
    }

    /**
     * 分析：
     * 1。m和n是表示两个数据规模。我们无法事先评估m和n谁的量级大
     * 2。在表示复杂度的时候，就不能简单地利用加法法则，省略掉其中一个，上面代码的时间复杂度就是O(m+n)。
     * 原来的加法法则(参考cal02)就不正确了：
     * 我们需要将加法规则改为:T1(m) + T2(n) = O(f(m) + g(n))
     * 3。乘法法则继续有效:T1(m)*T2(n) = O(f(m) * f(n))。
     */
    //O(m+n)、O(m*n)的例子
    int cal04(int m, int n) {
        int sum_1 = 0;
        int i = 1;
        for (; i < m; ++i) {
            sum_1 = sum_1 + i;
        }
        int sum_2 = 0;
        int j = 1;
        for (; j < n; ++j) {
            sum_2 = sum_2 + j;
        }
        return sum_1 + sum_2;
    }

    /**
     * 分析：
     * 1。第120行代码中，我们申请了一个空间存储变量i，但是它是常量阶的，跟数据规模n没有关系，所以我们可以忽略
     * 2。第121行申 请了一个大小为n的int类型数组，除此之外，剩下的代码都没有占用更多的空间
     * 3。所以整段代码的空间复杂度就是O(n)。
     */
    void print(int n) {
        int i = 0;
        int[] a = new int[n];
        for (; i < n; ++i) {
            a[i] = i * i;
        }
        for (i = n - 1; i >= 0; --i) {
            System.out.println(a[i]);
        }
    }

    /**
     * 1.这段代码要实现的功能是，在一个无序的数组(array)中，查找变量x出现的位置
     * 2.如果没有找到，就返回-1
     * 3.按照上节课讲的分析方法，这段代码的复杂度是O(n)，其中，n代表数组的长度
     */
    int find01(int[] array, int n, int x) {
        int i = 0;
        int pos = -1;
        for (; i < n; ++i) {
            if (array[i] == x)
                pos = i;
        }
        return pos;
    }

    /**
     * 优化篇：
     * 分析：
     * 我们在数组中查找一个数据，并不需要每次都把整个数组都遍历一遍
     * 原因：
     * 因为有可能中途找到就可以提前结束循环了。但是，这段代码写得不够高效。
     * 问题：
     * 我们优化完之后，这段代码的时间复杂度还是O(n)吗?
     * 答案：
     * 1。要查找的变量x可能出现在数组的任意位置。如果数组中第一个元素正好是要查找的变量x
     * 2。那就不需要继续遍历剩下的n-1个数据了，那时间复杂度就是O(1)
     * 3。但如果数组中不存在变量x，那我们就需要把整个数组都遍历一遍，时间复杂度就成了O(n)。
     * 4。不同的情况下，这段代码的时间复杂度是不一样的。
     */
    int find02(int[] array, int n, int x) {
        int i = 0;
        int pos = -1;
        for (; i < n; ++i) {
            if (array[i] == x) {
                pos = i;
                break;
            }

        }
        return pos;
    }

    /**
     * 说明：
     * 1。代码实现了一个往数组中插入数据的功能
     * 情况一：数组满了，也就是代码中的count == array.length时
     * 1。用for循环遍历数组求和，
     * 2。并清空数组，将求和之后的sum值放到数组的第一个位置
     * 3。然后再将新的数据插入
     * 情况二：如果数组一开始就有空闲空间
     * 则直接将数据插入数组。
     * 时间复杂度分析：
     * 1。最理想情况：
     * 数组中有空闲空间，我们只需要将数据插入到数组下标为count的位置就可以了，所以最好情况时间复杂度为O(1)
     * 2。最坏的情况下
     * 数组中没有 空闲空间了，我们需要先做一次数组的遍历求和，然后再将数据插入，所以最坏情况时间复杂度为O(n)。
     * 3。平均时间复杂度：
     * 分析：
     * 1。假设数组的长度是n，根据数据插入的位置的不同，我们可以分为n种情况，每种情况的时间复杂度是O(1)
     * 2。除此之外，还有一种“额外”的情况，就是在数组没有空 闲空间时插入一个数据，这个时候的时间复杂度是O(n)
     * 3。这n+1种情况发生的概率一样，都是1/(n+1)
     * 4。根据加权平均的计算方法，我们求得的平均时间复杂度就是:
     * 1*1/(n+1)+1*1/(n+1)+·····+1*1/(n+1)=O(1)
     * 4.均摊时间复杂度
     * 分析：
     * 1。继续看在数组中插入数据的这个例子。每一次O(n)的插入操作，都会跟着n-1次O(1)的插入操作
     * 2。所以把耗时多的那次操作均摊到接下来的n-1次耗时少的 操作上，均摊下来
     * 3。这一组连续的操作的均摊时间复杂度就是O(1)
     */
    // array表示一个长度为n的数组 // 代码中的array.length就等于n
    int[] array = new int[20];
    int count = 0;

    void insert(int val) {
        if (count == array.length) {
            int sum = 0;
            for (int i = 0; i < array.length; ++i) {
                sum = sum + array[i];
            }

            array[0] = sum;
            count = 1;
        }

        array[count] = val;
        ++count;
        int[] a = new int[3];
        a[3] = 10;
    }

    // 在数组a中，查找key，返回key所在的位置
    // 其中，n表示数组a的长度
    int findShaobing01(char[] a, int n, char key) {
        // 边界条件处理，如果a为空，或者n<=0，说明数组中没有数据，就不用while循环比较了
        if (a == null || n <= 0) {
            return -1;
        }
        int i = 0;
        // 这里有两个比较操作:i<n和a[i]==key.
        while (i < n) {
            if (a[i] == key) {
                return i;
            }
            ++i;
        }
        return-1;
    }


    // 在数组a中，查找key，返回key所在的位置
    // 其中，n表示数组a的长度
    //这只是为了举例说明哨兵的作用，下面的代码可读性太差了。
    int findShaobing02(char[] a, int n, char key) {
        if (a == null || n <= 0) {
            return -1;
        }
        // 这里因为要将a[n-1]的值替换成key，所以要特殊处理这个值
        if (a[n - 1] == key) {
            return n - 1;
        }
        // 把a[n-1]的值临时保存在变量tmp中，以便之后恢复。tmp=6。
        // 之所以这样做的目的是:希望find()代码不要改变a数组中的内容
        char tmp = a[n - 1];
        // 把key的值放到a[n-1]中，此时a = {4, 2, 3, 5, 9, 7} a[n-1] = key;
        int i = 0;
        // while 循环比起代码一，少了i<n这个比较操作
        while (a[i] != key) {
            ++i;
        }
        // 恢复a[n-1]原来的值,此时a= {4, 2, 3, 5, 9, 6} a[n-1] = tmp;
        if (i == n - 1) {
            // 如果i == n-1说明，在0...n-2之间都没有key，所以返回-1
            return -1;
        } else {
            // 否则，返回i，就是等于key值的元素的下标
            return i;
        }
    }



}


