package leetcode;

public class TopKFrequent {

}

/*
----------- Question ------------
Amazon Onsite 设计题 —— Top 10 Products
设计top 10 most page view product
follow up 1.
比如有dvd，显示page view最多的10个dvd
比如有book，显示page view最多的10个book
follow up 2.
另外，traffic访问量很高怎么办
follow up 3
如果要实现上个月page view最多的10个dvd怎么办

我的答案：我当时就是用关系型数据库存的product的entry 然后直接sqlquery返回给client端。
但是面试官不是很满意的样子？求更好的答案 谢谢

----------- Weak Hire ------------
product每一次被访问时，page_view+1：

UPDATE product SET page_views = page_views + 1 WHERE id=product_id
选择top10的时候：

SELECT * FROM product ORDER BY page_views DESC LIMIT 10;
follow up 1:

SELECT * FROM product WHERE product.category=dvd ORDER BY page_views DESC LIMIT 10;
product.category 上需要index

------------- Hire ----------------

首先需要向面试官分析为什么上面的方法不好：
上面的这个query非常的慢。因为数据库首先查一下index，看看有哪些product是dvd，然后将所有product 按照page_views 排序，选出前10个。
这里page_views如果你做index的话，因为要不停的+1+1，所以耗费会很大。

然后分析哪些地方我们可以入手进行优化：
1. top 10 无需是实时的 top 10。
2. 每次page view都导致数据库的一个项目+1这个操作比较费时。（本来只是一个读，却导致了写）
3. Page views的具体views可以不精确，有丢失。

其次提出一个更好的方案：
1. 将page view的log下来，这些log可以进行一些初步的aggregate，在每一台服务器上单独做就好了。log的结构基本就是，<product_id, date, category, page_views>，
对于同一天的同一个产品的page_views，我们可以把多条log aggrerate到一起。比如：<1, 2016-01-01, "dvd", 1> 和 <1, 2016-01-01, "dvd", 2> 可以aggregate为：<1, 2016-01-01, "dvd", 3>。这些log可以存在NoSQL的DB里。
2. 写一个 topk 的统计服务，针对每个类别进行 topk 统计。这里可以用一些 top k 的算法。网上 Google 一下有挺多（top k frequent elements）。简单的办法可以用Hash + Heap的方法。

follow up 2: 流量大的话，首先不能让每次page_view都往数据库里写，所以log写到db之前，可以先写在内存里，进行aggregate，内存里的数据每隔几秒钟或者几分钟再往数据库里集体写一次。这样就减少了数据库的写操作次数。其次的话，每个类别的top 10，肯定是cache起来，然后加速读top 10的访问。
follow up 3: 因为存储的log就是按照时间存的，所以就解决了呗。

------------- Strong Hire ----------
1. top k frequent elements 的算法有一些很高效的的O(n)时间O(k)空间的概率算法，比如 Space-saving algorithm，Lossy-Counting, Sticky Sampling。如果你能答出一些的话，我相信面试官都未必知道有这些算法。
2. 一个更传统的做法是Map Reduce。
 */
