import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Iterator;
import java.util.Random;

public class Eggs {

	int rows;
	int cols;
	int w = Yards.Bolck_Size;
	int h = Yards.Bolck_Size;
	static Random r = new Random();

	public Eggs() {
		this(r.nextInt(30), r.nextInt(30));
	}

	public Eggs(int rows, int crows) {
		this.rows = rows;
		this.cols = crows;
	}

	public void draw(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.black);
		g.fillOval(rows * w, cols * h, w, h);
		g.setColor(c);
	}

	// 碰撞计算
	public Rectangle getRec() {
		return new Rectangle(this.rows * w, this.cols * h, w, h);
	}

	// 重写设置鸡蛋出现的位置
	public void occur() {
		this.setCrows(r.nextInt(30));
		this.setRows(r.nextInt(30));
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getCrows() {
		return cols;
	}

	public void setCrows(int crows) {
		this.cols = crows;
	}

}
