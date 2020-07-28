package algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class GreedyAlgorithm {

	public static void main(String[] args) {

		// 创建广播电台，放入到HashMap进行管理
		HashMap<String, HashSet<String>> broadcasts = new HashMap<String, HashSet<String>>();

		// 将各个电台放入到boardcasts
		HashSet<String> hs1 = new HashSet<String>();
		hs1.add("Beijing");
		hs1.add("Shanghai");
		hs1.add("Tianjing");

		HashSet<String> hs2 = new HashSet<String>();
		hs2.add("Guangzhou");
		hs2.add("Beijing");
		hs2.add("Shenzhen");

		HashSet<String> hs3 = new HashSet<String>();
		hs3.add("Chengdu");
		hs3.add("Shanghai");
		hs3.add("Hangzhou");

		HashSet<String> hs4 = new HashSet<String>();
		hs4.add("Shanghai");
		hs4.add("Tianjing");

		HashSet<String> hs5 = new HashSet<String>();
		hs5.add("Hangzhou");
		hs5.add("Dalian");

		broadcasts.put("K1", hs1);
		broadcasts.put("K2", hs2);
		broadcasts.put("K3", hs3);
		broadcasts.put("K4", hs4);
		broadcasts.put("K5", hs5);

		// allAreas存放所有的地区
		HashSet<String> allAreas = new HashSet<String>();
		allAreas.addAll(hs1);
		allAreas.addAll(hs2);
		allAreas.addAll(hs3);
		allAreas.addAll(hs4);
		allAreas.addAll(hs5);

		// 创建ArrayList，存放选择的电台集合
		ArrayList<String> select = new ArrayList<String>();

		// 定义一个临时集合，存放在遍历的过程中的电台覆盖的地区和当前还未覆盖的地区的交集
		HashSet<String> tempSet = new HashSet<String>();

		// 定义给maxKey，保存在一次遍历过程中，能够覆盖最大的未覆盖的地区对应的电台的key
		// 如果maxKey不为null，则加入到select
		String maxKey = null;

		// 如果allAreas不为0，则表示还未覆盖到所有的地区
		while (allAreas.size() != 0) {

			// 每进行一次while，需将maxKey置空
			maxKey = null;

			// 遍历broadcasts，取出对应的key
			for (String key : broadcasts.keySet()) {

				// 每进行一次for，需将maxKey置空
				tempSet.clear();

				// 当前key能够覆盖的地区
				HashSet<String> areas = broadcasts.get(key);
				tempSet.addAll(areas);

				// 求出 tempSet 和 allAreas 集合的交集，交集会赋给tempSet
				tempSet.retainAll(allAreas);

				// 如果当前集合包含的未覆盖地区的数量，比maxKey指向的集合的地区还多
				// 需重置maxKey
				if (tempSet.size() > 0 && 
						(maxKey == null || tempSet.size() > broadcasts.get(maxKey).size())) {
					maxKey = key;
				}
			}
			// 若不为null，将maxKey加入到select
			if (maxKey != null) {
				select.add(maxKey);
				// 将maxKey指向的广播电台的覆盖的地区，从allAreas中去掉
				allAreas.removeAll(broadcasts.get(maxKey));
			}
		}
		System.out.println("电台有：" + broadcasts);
		System.out.println("遍历的的地区有：" + tempSet);
		System.out.println("得到的选择结果是：" + select);
	}
}
