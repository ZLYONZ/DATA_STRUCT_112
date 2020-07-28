package hashtable;

//创建EmpLinkedList，表示链表
class EmpLinkedList {
	
	// 头指针，指向第一个Emp，因此这个链表的head是有效的，直接指向第一个Emp
	private Employee head; // 默认为null

	// add employee
	// 1. 添加雇员时，id自增长，即id的分配总是从小到大
	// 2. 因此将该雇员直接加入到链表的最后
	public void add(Employee emp) {
		// 如果是添加第一个雇员
		if (head == null) {
			head = emp;
			return;
		}
		// 如果不是第一个emp，则使用辅助指针，帮助定位到最后
		Employee curEmp = head;
		while (true) {
			if (curEmp.next == null) { // 说明到链表最后
				break;
			}
			curEmp = curEmp.next; // 后移
		}
		curEmp.next = emp;
	}

	// list info.
	public void list(int num) {
		if (head == null) {
			System.out.println("第" + (num + 1) + "链表为空~");
			return;
		}
		System.out.printf("第" + (num + 1) + "链表的信息为：");
		Employee curEmp = head;
		while (true) {
			System.out.printf(" => id=%d name=%s \t", curEmp.id, curEmp.name);
			if (curEmp.next == null) {
				break;
			}
			curEmp = curEmp.next; // 后移，遍历
		}
		System.out.println();
	}

	// find employee using id
	public Employee findEmpByID(int id) {
		if (head == null) {
			return null;
		}
		// 辅助指针
		Employee curEmp = head;
		while (true) {
			// 退出
			if (curEmp.next == null) { // 说明遍历当前链表未找到该雇员
				curEmp = null;
				break;
			}
			if (curEmp.id == id) {
				break; // 此时curEmp就指向要查找的雇员
			}

			curEmp = curEmp.next;
		}
		return curEmp;
	}

	// delete employee（not finish）
	public Employee deleteEmp(int id) {
		if (head == null) {
			return null;
		}
		// 辅助指针
		Employee curEmp = head;
		while (true) {
			// 退出
			if (curEmp.next == null) {
				curEmp = null;
				break;
			}
			if (curEmp.next.id == id) {
				break;
			}
			curEmp = curEmp.next;
		}
		curEmp.next = curEmp.next.next;
		return curEmp;
	}
}
