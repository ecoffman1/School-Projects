import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import javax.swing.JButton;
import java.awt.Color;

class View extends JPanel
{
	BufferedImage[] item_images;
	Model model;
	int scrollX;
	int scrollY;
	JButton save;
	JButton load;

	View(Model m)
	{
		this.save = new JButton("Save");
		this.add(save);

		this.load = new JButton("Load");
		this.add(load);

		save.setFocusable(false);
		load.setFocusable(false);
		this.model = m;
		this.scrollX = 0;
		this.scrollY = 0;
		// Load the images
		this.item_images = new BufferedImage[Main.MapItemTypes.length];
		for (int i = 0; i < Main.MapItemTypes.length; i++) {
			BufferedImage image = null;
			try
			{
				image = ImageIO.read(new File("images/" + Main.MapItemTypes[i] + ".png"));
			} catch(Exception e) {
				e.printStackTrace(System.err);
				System.exit(1);
			}
			this.item_images[i] = image;
		}	
	}

	public void paintComponent(Graphics g)
	{
		
		// Clear the background
		g.setColor(new Color(64, 255, 128));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		// Draw all the item images
		for (int i = 0; i < model.items.size(); i++) {
			MapItem item = model.items.get(i);
			BufferedImage image = this.item_images[item.type];
			int w = image.getWidth();
			int h = image.getHeight();

			// Draw the image with the bottom center at (x, y)
			g.drawImage(image, item.x - w / 2 - scrollX, item.y - h - scrollY, null);
		}
		// Draw Purple Square
		g.setColor(new Color(148,0,211));
		g.fillRect(0, 0, 200, 200);
		// Draw Item
		int num_item = this.model.current_item;
		BufferedImage current = this.item_images[num_item];
		int wi = current.getWidth();
		int he = current.getHeight();
		g.drawImage(current, 100 - wi / 2, 100 - he / 2, null);
	}
}
