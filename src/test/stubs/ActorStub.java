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

package test.stubs;

import main.model.Actor;
import main.model.Turn;
import main.model.World;
import main.utilities.Loc;

import java.awt.Color;
import java.util.Queue;

public class ActorStub extends Actor
{
    public ActorStub(World world, Loc loc, Color color, char sym)
    {
        super(world, loc, color, sym);
    }

    public Queue<Turn> getActionQueue()
    {
        return _actionQueue;
    }
}
