package junit.test;

import java.awt.*;
import java.applet.*;

public class HelloApplet extends Applet {
	public void paint(Graphics g) {
		g.drawString("Helloworld!", 50, 25);
	}

}