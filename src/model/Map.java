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

package model;

import items.BaseItem;
import utilities.Loc;

/**
 * Created by Ithmeer on 4/22/2016.
 */
public class Map
{
    private final BaseItem[][] _items;
    private final int _width, _height;

    public Map(int width, int height)
    {
        _width = width;
        _height = height;
        _items = new BaseItem[width][height];
    }

    public int getWidth()
    {
        return _width;
    }

    public int getHeight()
    {
        return _height;
    }

    public BaseItem getSquare(int x, int y)
    {
        return _items[x][y];
    }

    public BaseItem getSquare(Loc loc)
    {
        return _items[loc.x][loc.y];
    }

    public void setSquare(int x, int y, BaseItem it)
    {
        it.setLoc(new Loc(x, y));
        _items[x][y] = it;
    }

    public void setSquare(Loc loc, BaseItem it)
    {
        it.setLoc(loc);
        _items[loc.x][loc.y] = it;
    }
}
