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

package main.view;

import main.interfaces.RenderFilter;
import main.interfaces.ScreenRenderer;
import main.utilities.Loc;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicReferenceArray;

/**
 * Render the current screen in a JPanel.
 * Created by Ithmeer on 4/3/2016.
 */
public class PanelRenderer extends JPanel implements ScreenRenderer
{
    protected RenderFilter _filter = null;

    // rows, then columns
    private AtomicIntegerArray _charArray;
    private AtomicReferenceArray<Color> _colorArray;

    private final char[] _buffer = new char[1];

    private volatile int _rows, _cols;
    private volatile double _charWidth, _charHeight;

    public void initialize(Graphics g)
    {
        Font monospace = Font.decode(Font.MONOSPACED);
        setFont(monospace);
        Rectangle2D fontRect = getFontMetrics(getFont()).getStringBounds("W", g);
        _charWidth = fontRect.getWidth();
        _charHeight = fontRect.getHeight();
        _rows = (int) Math.round(getHeight() / _charHeight);
        _cols = (int) Math.round(getWidth() / _charWidth);

        _charArray = new AtomicIntegerArray(_rows * _cols);
        _colorArray = new AtomicReferenceArray<Color>(_rows * _cols);
    }

    public int getMaxRows()
    {
        return _rows;
    }

    public int getMaxCols()
    {
        return _cols;
    }

    @Override
    public void renderPos(Loc loc, char ch, Color color)
    {
        _charArray.set(loc.x + loc.y * _cols, ch);
        _colorArray.set(loc.x + loc.y * _cols, color);
    }

    @Override
    public void renderPos(Loc[] locs, char[] chs, Color[] colors)
    {
        for (int i = 0; i < locs.length; i++)
        {
            renderPos(locs[i], chs[i], colors[i]);
        }
    }

    @Override
    public void clearFilter()
    {
        _filter = null;
    }

    @Override
    public void setFilter(RenderFilter filter)
    {
        _filter = filter;
    }

    @Override
    public void refresh()
    {
        try
        {
            SwingUtilities.invokeAndWait(this::repaint);
        }
        catch (InterruptedException | InvocationTargetException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        setBackground(Color.black);

        for (int r = 0; r < _rows; r++)
        {
            for (int c = 0; c < _cols; c++)
            {
                g.setColor(_colorArray.get(r * _cols + c));

                // put the char in a temporary buffer then draw it
                _buffer[0] = (char) _charArray.get(r * _cols + c);
                g.drawChars(_buffer, 0, 1, (int) Math.round(_charWidth * c),
                            (int) Math.round(_charHeight * r + _charHeight));
            }
        }
    }
}
