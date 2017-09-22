// Returns an image of the graph of data

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Visualizer
{
    Parser parser;
    BufferedImage image;

    public Visualizer(Parser _parser)
    {
        parser = _parser;
        image = new BufferedImage(600, 600, BufferedImage.TYPE_3BYTE_BGR);
    }

    public void display()
    {
        JFrame frame = new JFrame();
        frame.getContentPane().setLayout(new FlowLayout());
        frame.getContentPane().add(new JLabel(new ImageIcon(image)));

        frame.pack();
        frame.setVisible(true);
    }

    public void paint()
    {
        Graphics2D g2 = image.createGraphics();

        int width = image.getWidth();
        int height = image.getHeight();

        g2.setColor(Color.white);
        g2.fillRect(0, 0, width, height);

        double width_of_bar = width / parser.number_of_days;
        g2.setColor(Color.lightGray);

        for (int i = 0; i < width; i+= width_of_bar)
            g2.drawLine(i, 0, i, height);

        for (int i = 0; i < parser.number_of_days - 1; i++)
        {
            double percentage = (parser.fall_asleep_times[i].hour * 60 + parser.fall_asleep_times[i].minute);
            if (parser.fall_asleep_times[i].day < parser.dates[i].day)
            {
                g2.setColor(Color.green);
                g2.fillRect((int) (i * width_of_bar), (int) ((percentage / (24.0 * 60.0)) * height), (int)width_of_bar, (height));
                g2.setColor(Color.black);
                g2.drawRect((int) (i * width_of_bar), (int) ((percentage / (24.0 * 60.0)) * height), (int)width_of_bar, (height));
            }
            else
            {

            }
        }
    }

    public void save()
    {
        File output = new File("graph.jpg");
        try {
            ImageIO.write(image, "jpg", output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
