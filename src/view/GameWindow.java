/*
 *     PacifistRogue, a roguelike game without combat.
 *     Copyright (C) 2017 Ithmeer
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package view;

import controller.PlayerController;

import javax.swing.*;
import java.awt.*;

/**
 * The main window for PacifistRogue.
 * Created by Ithmeer on 4/3/2016.
 */
public class GameWindow extends JFrame
{
    private final int _width;
    private final int _height;
    private final PanelRenderer _panel;
    private final PlayerController _controller;

    public GameWindow(int width, int height)
    {
        _width = width;
        _height = height;

        setTitle("PacifistRogue");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(width, height);

        _panel = new PanelRenderer();
        _panel.setPreferredSize(new Dimension(width, height));

        add(_panel);
        pack();

        _panel.initialize(getGraphics());
        _controller = new PlayerController(_panel);
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(_controller);

        _panel.setFocusable(true);
        _panel.requestFocusInWindow();

        setVisible(true);
    }
}
