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

import model.Entity;
import model.Item;
import model.ItemDatabase;
import model.World;
import utilities.Loc;

/**
 * Created by Ithmeer on 7/11/2017.
 */
public class Hoe extends BaseItem
{
    @Override
    public void use(World world, Entity user, Loc loc)
    {
        ItemDatabase itemDb = ItemDatabase.getInstance();

        Item item = world.getSquare(loc);
        if (item.getName() == "grass")
        {
            item.transform(itemDb.getItem("dirt"));
        }
    }

    @Override
    public void use(World world, Entity user)
    {

    }
}
