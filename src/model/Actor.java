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

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ithmeer on 5/23/2016.
 */
public abstract class Actor extends Entity
{
    protected final World _world;

    private final List<BaseItem> _heldItems = new ArrayList<>();
    private BaseItem _rightHand;

    public Actor(World world, Loc loc, Color color, char sym)
    {
        super(loc, color, sym);
        _world = world;
    }

    public void take()
    {
        BaseItem item = _world.getDroppedItem(_loc);
        if (item != null && item.isPortable())
        {
            _world.removeDroppedItem(item);
            _rightHand = item;
            _heldItems.add(item);
        }
    }

    public void move(Loc loc)
    {
        if (!_world.collides(loc))
        {
            _world.addDirty(_loc);
            _loc = loc;
        }
    }

    public void use()
    {

    }

    public Loc getLoc()
    {
        return _loc;
    }
}
