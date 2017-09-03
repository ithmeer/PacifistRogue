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

import main.utilities.TurnType;

/**
 * An actor's turn.
 * Created by Ithmeer on 7/15/2017.
 */
public class Turn
{
    private final TurnType _type;
    private final Object[] _args;
    private final int _initialDuration;
    private int _duration;

    /**
     * Initialize a turn with default duration.
     * @param type the move type
     * @param args the arguments
     */
    public Turn(TurnType type, Object... args)
    {
        this(1, type, args);
    }

    /**
     * Initialize a turn.
     * @param type the move type
     * @param duration the duration
     * @param args the arguments
     */
    public Turn(int duration, TurnType type, Object... args)
    {
        _type = type;
        _initialDuration = duration;
        _duration = duration;
        _args = args;
    }

    public void consumeTurn()
    {
        _duration--;
    }

    public boolean isTurnComplete()
    {
        return _duration == 0;
    }

    public TurnType getType()
    {
        return _type;
    }

    public Object[] getArgs()
    {
        return _args;
    }
}
