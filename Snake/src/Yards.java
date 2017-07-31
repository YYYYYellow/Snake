import java.awt.Color;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class Yards extends Frame {

	/**
	 * 日期：2017/7/29
	 */
	private static final long serialVersionUID = 1L;

	// 装蛇的节点容器
	static ArrayList<Snakes.Node> tailList = new ArrayList<Snakes.Node>();
	// static ArrayList<Snakes.Node> headList = new ArrayList<Snakes.Node>();

	// 行
	public static int rows = 50;
	// 列
	public static int crows = 50;
	// 行列的间距
	public static int Bolck_Size = 10;

	static Snakes snakes = null;

	public static void main(String[] args) {
		// 创建一个yards
		new Yards().lauch();
		snakes = new Snakes(tailList);
	}

	public void lauch() {
		setBounds(0, 0, rows * Bolck_Size, crows * Bolck_Size);
		setBackground(Color.gray);
		setVisible(true);

		// 关闭键监听
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				Frame f = (Frame) e.getSource();
				f.setVisible(false);
				System.exit(0);
			}
		});
	}

	// 重载画画方法
	public void paint(Graphics g) {
		g.setColor(Color.darkGray);
		// 画竖线
		for (int i = 1; i <= rows; i++) {
			g.drawLine(i * Bolck_Size, 0, i * Bolck_Size, crows * Bolck_Size);
		}
		// 画横线
		for (int i = 1; i <= crows; i++) {
			g.drawLine(0, i * Bolck_Size, rows * Bolck_Size, i * Bolck_Size);
		}

		snakes.draw(g);

	}

}
