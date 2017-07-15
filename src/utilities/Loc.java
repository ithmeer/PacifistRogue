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

package utilities;

/**
 * Created by Ithmeer on 5/23/2016.
 */
public class Loc
{
    public int x, y;

    public Loc(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    @Override
    public Loc clone()
    {
        return new Loc(x, y);
    }

    @Override
    public boolean equals(Object other)
    {
        Loc loc2 = other instanceof Loc ? (Loc) other : null;
        return loc2 != null && x == loc2.x && y == loc2.y;
    }

    public boolean isAdjacent(Loc loc)
    {
        int xDist = Math.abs(loc.x - x);
        int yDist = Math.abs(loc.y - y);
        return xDist * xDist + yDist * yDist == 1;
    }

    public Loc next(Direction dir)
    {
        Loc next = clone();

        switch (dir)
        {
            case Up:
                next.y -= 1;
                break;
            case Down:
                next.y += 1;
                break;
            case Left:
                next.x -= 1;
                break;
            case Right:
                next.x += 1;
                break;
        }

        return next;
    }

}
