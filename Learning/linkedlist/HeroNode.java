package linkedlist;

//定义HeroNode，每个对象就是一个node
class HeroNode {
	public int num;
	public String name;
	public String nickName;
	public HeroNode next; // 指向下一个node

	public HeroNode(int num, String name, String nickName) {
		this.num = num;
		this.name = name;
		this.nickName = nickName;
	}

	// 重新定义toString
	@Override
	public String toString() {
		return "HeroNode{" + "num=" + num + ", name='" + name + '\'' + ", nickName='" + nickName + '\'' + '}';
	}
}
