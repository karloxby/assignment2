import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.util.ArrayList;


// This panel represents the animated part of the view with the car images.

public class DrawPanel extends JPanel {
    ArrayList<Car> cars;
    // Just a single image, TODO: Generalize
    BufferedImage volvoImage;
    BufferedImage saabImage;
    // To keep track of a single car's position
    Point volvoPoint = new Point();


    // Initializes the panel and reads the images
    public DrawPanel(int x, int y, ArrayList<Car> cars) {
        this.cars = cars;
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);
        // Print an error message in case file is not found with a try/catch block
        try {
            // You can remove the "pics" part if running outside of IntelliJ and
            // everything is in the same folder.
            // volvoImage = ImageIO.read(new File("Volvo240.jpg"));
            volvoImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Volvo240.jpg"));
            saabImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Saab95.jpg"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    // This method is called each time the panel updates/refreshes/repaints itself
    // TODO: Change to suit your needs.
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (Car car : cars) {
            int x = (int) Math.round(car.getCurrentX());
            int y = (int) Math.round(car.getCurrentY());

            if (car instanceof Volvo240) {
                g.drawImage(volvoImage, x, y, null);
            } else if (car instanceof  Saab95) {
                g.drawImage(saabImage, x, y, null);
            }

        }
    }
}