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

import java.awt.event.KeyEvent;

/**
 * Created by Ithmeer on 5/23/2016.
 */
public enum Direction
{
    None, Up, Down, Left, Right;

    public static Direction keyToDirection(int key)
    {
        Direction dir = None;

        switch (key)
        {
            case KeyEvent.VK_UP:
            case KeyEvent.VK_W:
                dir = Up;
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:;
                dir = Down;
                break;
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:
                dir = Left;
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
                dir = Right;
                break;
        }

        return dir;
    }
}
