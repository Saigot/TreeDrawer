
package trees;

import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Michael Scovell
 */
public abstract class Node<T> {
    protected int childNum;
    protected int immediateChildren;
    protected List<Node> children = new ArrayList<>();
    public static int drawHeight = 10;
    public static final int textPadding = 1;
    public static final int cellPadding = 5;
    public static int heightSeparation = 30;
    public T Data;
    public abstract boolean insert(T n);
    protected Font font = new Font("Tahoma", Font.PLAIN, 9);
    public int center;
    
    public int height = 0;
    
    public void draw(Graphics g, int offsetx, int offsety){
        int width = getWidth();
        int padding = (width-(getContentsWidth()+2*textPadding))/2;
        g.setFont(font);
        g.drawRect(offsetx +padding, offsety, getContentsWidth()+2*textPadding, drawHeight + 2*textPadding);
        center = offsetx +padding + (getContentsWidth()+2*textPadding)/2;
        g.drawString(getStringContents(), offsetx+padding+textPadding, offsety+drawHeight+textPadding);
        int offset = 0;
        int newoffsety = offsety+heightSeparation;
        int drawPoint = (width-(getContentsWidth()+2*textPadding)/2)/2 + offsetx;
        for(Node n : children){
            n.draw(g, offsetx + offset, newoffsety);
            int nWidth = n.getWidth();
            offset += nWidth;
            g.drawLine(drawPoint, offsety+drawHeight+2*textPadding, 
                    n.center, newoffsety);
        }
    }
    
    public int getWidth(){
        int width = 0;
        if(!children.isEmpty())
        for(Node n : children){
            width += n.getWidth();
        }
        return (width + getContentsWidth() + 2*textPadding) + cellPadding;
    }
    public abstract int getContentsWidth();
    public abstract String getStringContents();
}
