import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;

public class Snakes {

	// 头结点，尾节点，大小
	private Node head = null;
	private Node tail = null;
	private int size = 0;

	int rows;
	int cols;
	int w = Yards.Bolck_Size;
	int h = Yards.Bolck_Size;
	// 装蛇的节点容器

	private Yards y;
	// 初始化第2个节点
	Node node = new Node(Yards.rows / 2, Yards.cols / 2, Dir.L);

	public Snakes(Yards y) {
		this.y = y;
		//
		this.head = node;
		this.tail = node;
		tail.next = null;
		this.size = 1;
	}

	// 蛇的节点
	class Node {
		// 节点所在行
		int rows;
		// 节点所在列
		int cols;
		// 节点方向
		Dir dir;

		Node next = null;
		Node pre = null;

		int w = Yards.Bolck_Size;
		int h = Yards.Bolck_Size;

		public Node(int x, int y, Dir dir) {
			this.rows = x;
			this.cols = y;
			this.dir = dir;
		}

		public void draw(Graphics g) {
			Color c = g.getColor();
			g.setColor(Color.red);
			g.fillRect(this.rows * w, this.cols * h, Yards.Bolck_Size, Yards.Bolck_Size);
			g.setColor(c);
		}

	}

	// 尾部增加节点
	public void addToTail() {
		Node node = null;
		// 右上角是(0,0)
		// 往右是rows(x)变大
		// 往下是cols(y)变大
		switch (tail.dir) {
		case L:
			node = new Node(tail.rows + 1, tail.cols, tail.dir);
			break;
		case R:
			node = new Node(tail.rows - 1, tail.cols, tail.dir);
			break;
		case U:
			node = new Node(tail.rows, tail.cols - 1, tail.dir);
			break;
		case D:
			node = new Node(tail.rows, tail.cols + 1, tail.dir);
			break;
		}
		tail.next = node;
		node.pre = tail;
		tail = node;
		// tail.next = null;
		size++;
	}

	// 尾部增加节点
	public void addToHead() {
		Node node = null;
		switch (head.dir) {

		case L:
			node = new Node(head.rows - 1, head.cols, head.dir);
			break;
		case R:
			node = new Node(head.rows + 1, head.cols, head.dir);
			break;
		case U:
			node = new Node(head.rows, head.cols - 1, head.dir);
			break;
		case D:
			node = new Node(head.rows, head.cols + 1, head.dir);
			break;
		}
		head.pre = node;
		node.next = head;
		head = node;
		size++;
	}

	// 画蛇
	public void draw(Graphics g) {
		move();
		Node n = head;
		while (n != null) {
			n.draw(g);
			n = n.next;
		}
	}

	// 移动
	private void move() {
		addToHead();
		deleteLast();
		checkout();
	}

	// 删除最后一个元素
	public void deleteLast() {

		Node n = tail;
		tail = tail.pre;
		tail.next = null;
		size++;
	}

	// 碰撞
	public void eat(Eggs e) {

		if (this.getRec().intersects(e.getRec())) {
			System.out.println("碰到了碰到了碰到了碰到了碰到了");
			e.occur();
			addToTail();
			y.setScore(y.getScore() + 5);
		}

	}

	// 碰撞计算
	public Rectangle getRec() {

		return new Rectangle(head.rows * w, head.cols * h, head.w, head.h);
	}

	// 判断结束了没
	public void checkout() {
		// 判断是否碰触边界
		if (head.rows < 2 || head.cols < 4 || head.rows > 48 || head.cols > 47) {
			y.gameOver();
		}
		// 判断是否吃到自己
		// 4个不可能吃到自己
		if (size <= 4) {
			return;
		}
		Node n = head.next;
		while (n != null) {
			if (n.rows == head.rows && n.cols == head.cols) {
				y.gameOver();
			}
			n = n.next;
		}

	}

	// 键盘
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		switch (key) {
		case KeyEvent.VK_LEFT:
			if (head.dir == Dir.R)
				return;
			head.dir = Dir.L;
			break;
		case KeyEvent.VK_RIGHT:
			if (head.dir == Dir.L)
				return;
			head.dir = Dir.R;
			break;
		case KeyEvent.VK_UP:
			if (head.dir == Dir.D)
				return;
			head.dir = Dir.U;
			break;
		case KeyEvent.VK_DOWN:
			if (head.dir == Dir.U)
				return;
			head.dir = Dir.D;
			break;
		}
	}
}
