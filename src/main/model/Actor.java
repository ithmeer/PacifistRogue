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

import main.items.BaseItem;
import main.utilities.Loc;
import main.utilities.TurnType;

import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * An entity that can perform actions.
 * Created by Ithmeer on 5/23/2016.
 */
public abstract class Actor extends Entity
{
    protected final World _world;
    protected final Queue<Turn> _actionQueue = new LinkedList<>();
    protected final List<BaseItem> _heldItems = new ArrayList<>();
    protected BaseItem _primaryItem;

    public Actor(World world, Loc loc, Color color, char sym)
    {
        super(loc, color, sym);
        _world = world;
    }

    /**
     * Queue up a new turn.
     * @param turn the turn to take
     */
    public void queueTurn(Turn turn)
    {
        _actionQueue.add(turn);
    }

    /**
     * Take a turn.
     */
    public void takeTurn()
    {
        if (isIdle())
        {
            return;
        }

        Turn nextMove = _actionQueue.peek();
        nextMove.consumeTurn();

        if (nextMove.isTurnComplete())
        {
            doMove(nextMove.getType(), nextMove.getArgs());
            _actionQueue.remove();
        }
    }

    /**
     * Pick up an item at the current location.
     */
    public void pickUp()
    {
        BaseItem item = _world.getDroppedItem(_loc);
        if (item != null && item.isPortable())
        {
            _world.removeDroppedItem(item);
            _primaryItem = item;
            _heldItems.add(item);
        }
    }

    /**
     * Move to a new location.
     * @param loc the new location
     */
    public void move(Loc loc)
    {
        if (!_world.collides(loc))
        {
            _world.addDirty(_loc);
            _loc = loc;
        }
    }

    /**
     * Use the item at the given location.
     * @param loc the location the item is at
     */
    public void use(Loc loc)
    {
        BaseItem item = _world.getSquare(loc);
        if (item != null)
        {
            item.use(_world, this);
        }
    }

    public void useHeldItem(Loc loc)
    {
        if (_primaryItem != null)
        {
            _primaryItem.use(_world, this, loc);
        }
    }

    /**
     * Get the location of this actor.
     * @return the actor location
     */
    public Loc getLoc()
    {
        return _loc.clone();
    }

    /**
     * The idle state of the actor.
     * @return true if idle
     */
    public boolean isIdle()
    {
        return _actionQueue.isEmpty();
    }

    private void doMove(TurnType type, Object... args)
    {
        switch (type)
        {
            case Move:
                move((Loc) args[0]);
                break;
            case PickUp:
                pickUp();
                break;
            case Use:
                use((Loc) args[0]);
                break;
            case UseHeld:
                useHeldItem((Loc) args[0]);
                break;
        }
    }
}
