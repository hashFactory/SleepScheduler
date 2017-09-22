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

        // Draw main sleep
        for (int i = 0; i < parser.number_of_days - 1; i++)
        {
            if (parser.fall_asleep_times[i].day < parser.dates[i].day)
            {
                double percentage_start = (parser.fall_asleep_times[i].hour * 60 + parser.fall_asleep_times[i].minute);
                double percentage_end = (parser.wakeup_times[i].hour * 60 + parser.wakeup_times[i].minute);
                g2.setColor(new Color(210, 200, 200));
                if (percentage_end - percentage_start != 0) {
                    g2.fillRect((int) (i * width_of_bar), (int) ((percentage_start / (24.0 * 60.0)) * height), (int) width_of_bar, height);
                    g2.fillRect((int) (i * width_of_bar + width_of_bar), 0, (int) width_of_bar, (int) ((percentage_end / (24.0 * 60.0)) * height));
                }
            }
            else
            {
                double percentage_start = (parser.fall_asleep_times[i].hour * 60 + parser.fall_asleep_times[i].minute);
                double percentage_height = (parser.wakeup_times[i].hour * 60 + parser.wakeup_times[i].minute) - percentage_start;
                g2.setColor(new Color(210, 200, 200));
                if (percentage_height > 0)
                    g2.fillRect((int) ((i + 1) * width_of_bar), (int) ((percentage_start / 1440.0) * (double)height), (int) width_of_bar, (int) ((percentage_height / 1440.0) * (double)height));
            }
        }

        // Draw main nap
        for (int i = 0; i < parser.nap_asleep_times.length; i++)
        {
            double percentage_start = (parser.nap_asleep_times[i].hour * 60 + parser.nap_asleep_times[i].minute);
            double percentage_height = (parser.nap_wakeup_times[i].hour * 60 + parser.nap_wakeup_times[i].minute) - percentage_start;
            g2.setColor(new Color(210, 200, 200));
            g2.fillRect((int) (i * width_of_bar), (int) ((percentage_start / (24.0 * 60.0)) * height), (int)width_of_bar, (int) ((percentage_height / (24.0 * 60.0)) * height));
        }

        // Draw bars
        for (int i = 0; i < width/width_of_bar; i++)
        {
            g2.setColor(Color.gray);
            g2.drawLine((int)(i * width_of_bar), 0, (int)(i * width_of_bar), height);
            g2.setColor(Color.gray);
        }

        for (int j = 0; j < 24; j++)
            g2.drawLine(0, (int)(j * (height / 24.0)), 5, (int)(j * (height / 24.0)));

        // Draw 6 hours bars
        for (int i = 0; i < 4; i++)
        {
            g2.setColor(Color.gray);
            g2.drawLine(0, (int)((double)i * (double)height / 4.0), width, (int)((double)i * (double)height / 4.0));
        }
    }

    public void save()
    {
        File output = new File("graph.png");
        try {
            ImageIO.write(image, "png", output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
