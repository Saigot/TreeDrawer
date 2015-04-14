
package trees;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author Michael Scovell
 */
public class Canvas extends JPanel {
    Node Root;
    public Canvas(Node n){
        Root = n;
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Root.draw(g,50,50);
        setPreferredSize(new Dimension(Root.getWidth()+50,Math.max(500, (Root.height+1)*
                (Node.heightSeparation+Node.drawHeight + 2*Node.textPadding))));
    }
}
