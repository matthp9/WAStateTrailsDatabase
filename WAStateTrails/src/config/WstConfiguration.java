package config;

import java.awt.*;

/**
 * Program configuration.
 * @author Matthew Phillips
 */
public interface WstConfiguration {

    Dimension FRAME_DEF_DIM = new Dimension(WstConfiguration.FRAME_W, WstConfiguration.FRAME_H);

    int FRAME_W = 800;
    int FRAME_H = 600;

    boolean FRAME_RESIZE = true;

    Color BG_COLOR = new Color(100, 200, 100);

    Font FONT_SMALL = new Font("Small", Font.PLAIN, 10);

}
