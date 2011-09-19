package jstarcraft.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 通过Random类中的方法产生随机的序列
 * 
 * @author Xewee.Zhiwei.Wang
 * @version 2011-9-17 上午10:00:58
 */
public class RandomSequence {

	static Random random = new Random();
	/**
	 * 随机生成长度为n的随机序列，用List存储
	 * @author Xewee.Zhiwei.Wang
	 * @version 2011-9-17 上午10:01:31
	 * @param n
	 * @return
	 */
	public static List<Integer> generate(int n) {

		List<Integer> item = new ArrayList<Integer>();
		int i;
		while (item.size() < n) {
			i = random.nextInt(n);
			if (item.contains(i))
				continue;
			item.add(i);
		}
		return item;
	}

	public static void main(String[] args) {

		System.out.println(RandomSequence.generate(5));
	}
}
