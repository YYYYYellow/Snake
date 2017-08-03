import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Eggs {

	int rows;
	int cols;
	int w = Yards.Bolck_Size;
	int h = Yards.Bolck_Size;
	static Random r = new Random();
	Color color = Color.black;

	public Eggs() {
		this(r.nextInt(10) + 5, r.nextInt(10) + 5);
	}

	public Eggs(int rows, int crows) {
		this.rows = rows;
		this.cols = crows;
	}

	public void draw(Graphics g) {
		Color c = g.getColor();
		g.setColor(color);
		g.fillOval(rows * w, cols * h, w, h);
		if (color == Color.black)
			color = Color.blue;
		else
			color = Color.black;
		g.setColor(c);
	}

	// 碰撞计算
	public Rectangle getRec() {
		return new Rectangle(this.rows * w, this.cols * h, w, h);
	}

	// 重写设置鸡蛋出现的位置
	public void occur() {
		this.setCrows(r.nextInt(35) + 5);
		this.setRows(r.nextInt(35) + 5);
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
