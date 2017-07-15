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

package items;

import interfaces.CompositeItem;
import model.Entity;
import model.Item;
import model.World;
import utilities.Loc;

/**
 * Created by Ithmeer on 7/11/2017.
 */
public class BaseItem extends Item implements CompositeItem
{
    @Override
    public void use(World world, Entity user, Loc loc)
    {
    }

    @Override
    public void use(World world, Entity user)
    {
    }

    @Override
    public BaseItem clone()
    {
        BaseItem clone;
        try
        {
            clone = this.getClass().newInstance();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }

        copy(clone, this);
        return clone;
    }
}
