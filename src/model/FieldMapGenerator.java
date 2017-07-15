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

/**
 * Created by Ithmeer on 5/8/2016.
 */
public class FieldMapGenerator
{
    public static Map generateField(int width, int height)
    {
        Map field = new Map(width, height);

        ItemDatabase itemDb = ItemDatabase.getInstance();
        BaseItem grass = itemDb.getItem("grass");
        BaseItem dirt = itemDb.getItem("dirt");

        for (int x = 0; x < width; x++)
        {
            for (int y = 0; y < height; y++)
            {
                field.setSquare(x, y, grass.clone());
            }
        }

        field.setSquare(width / 2, height / 2, itemDb.getItem("tree"));

        for (int x = 5; x < 10; x++)
        {
            for (int y = 5; y < 10; y++)
            {
                field.setSquare(x, y, dirt.clone());
            }
        }

        return field;
    }
}
