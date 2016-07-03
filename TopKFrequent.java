package leetcode;

public class TopKFrequent {

}

/*
----------- Question ------------
Amazon Onsite ����� ���� Top 10 Products
���top 10 most page view product
follow up 1.
������dvd����ʾpage view����10��dvd
������book����ʾpage view����10��book
follow up 2.
���⣬traffic�������ܸ���ô��
follow up 3
���Ҫʵ���ϸ���page view����10��dvd��ô��

�ҵĴ𰸣��ҵ�ʱ�����ù�ϵ�����ݿ���product��entry Ȼ��ֱ��sqlquery���ظ�client�ˡ�
�������Թٲ��Ǻ���������ӣ�����õĴ� лл

----------- Weak Hire ------------
productÿһ�α�����ʱ��page_view+1��

UPDATE product SET page_views = page_views + 1 WHERE id=product_id
ѡ��top10��ʱ��

SELECT * FROM product ORDER BY page_views DESC LIMIT 10;
follow up 1:

SELECT * FROM product WHERE product.category=dvd ORDER BY page_views DESC LIMIT 10;
product.category ����Ҫindex

------------- Hire ----------------

������Ҫ�����Թٷ���Ϊʲô����ķ������ã�
��������query�ǳ���������Ϊ���ݿ����Ȳ�һ��index����������Щproduct��dvd��Ȼ������product ����page_views ����ѡ��ǰ10����
����page_views�������index�Ļ�����ΪҪ��ͣ��+1+1�����Ժķѻ�ܴ�

Ȼ�������Щ�ط����ǿ������ֽ����Ż���
1. top 10 ������ʵʱ�� top 10��
2. ÿ��page view���������ݿ��һ����Ŀ+1��������ȽϷ�ʱ��������ֻ��һ������ȴ������д��
3. Page views�ľ���views���Բ���ȷ���ж�ʧ��

������һ�����õķ�����
1. ��page view��log��������Щlog���Խ���һЩ������aggregate����ÿһ̨�������ϵ������ͺ��ˡ�log�Ľṹ�������ǣ�<product_id, date, category, page_views>��
����ͬһ���ͬһ����Ʒ��page_views�����ǿ��԰Ѷ���log aggrerate��һ�𡣱��磺<1, 2016-01-01, "dvd", 1> �� <1, 2016-01-01, "dvd", 2> ����aggregateΪ��<1, 2016-01-01, "dvd", 3>����Щlog���Դ���NoSQL��DB�
2. дһ�� topk ��ͳ�Ʒ������ÿ�������� topk ͳ�ơ����������һЩ top k ���㷨������ Google һ����ͦ�ࣨtop k frequent elements�����򵥵İ취������Hash + Heap�ķ�����

follow up 2: ������Ļ������Ȳ�����ÿ��page_view�������ݿ���д������logд��db֮ǰ��������д���ڴ������aggregate���ڴ��������ÿ�������ӻ��߼������������ݿ��Ｏ��дһ�Ρ������ͼ��������ݿ��д������������εĻ���ÿ������top 10���϶���cache������Ȼ����ٶ�top 10�ķ��ʡ�
follow up 3: ��Ϊ�洢��log���ǰ���ʱ���ģ����Ծͽ�����¡�

------------- Strong Hire ----------
1. top k frequent elements ���㷨��һЩ�ܸ�Ч�ĵ�O(n)ʱ��O(k)�ռ�ĸ����㷨������ Space-saving algorithm��Lossy-Counting, Sticky Sampling��������ܴ��һЩ�Ļ������������Թٶ�δ��֪������Щ�㷨��
2. һ������ͳ��������Map Reduce��
 */
