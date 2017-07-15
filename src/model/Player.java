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

import utilities.Loc;

import java.awt.Color;

/**
 * Created by Ithmeer on 4/19/2016.
 */
public class Player extends Actor
{
    private static final Color _playerColor = Color.orange;
    private static final char _playerSymbol = '@';

    public Player(World world, Loc loc)
    {
        super(world, loc, _playerColor, _playerSymbol);
    }
}
