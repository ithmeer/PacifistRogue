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

package main.model;

import main.utilities.CharAdapter;
import main.utilities.ColorAdapter;
import main.utilities.Loc;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.awt.Color;

/**
 * Created by Ithmeer on 5/29/2016.
 */
public abstract class Square
{
    public Square(Loc loc, Color color, char sym)
    {
        _loc = loc;
        _color = color;
        _symbol = sym;
    }

    public Square()
    {
        // empty constructor
    }

    @XmlJavaTypeAdapter(ColorAdapter.class)
    @XmlAttribute(name="color")
    protected Color _color;

    @XmlJavaTypeAdapter(value= CharAdapter.class, type=char.class)
    @XmlAttribute(name="char")
    protected char _symbol;

    protected Loc _loc;

    public Color getColor()
    {
        return _color;
    }

    public Loc getLoc()
    {
        return _loc;
    }

    public char getSymbol()
    {
        return _symbol;
    }

    public void setLoc(Loc loc)
    {
        _loc = loc;
    }
}
