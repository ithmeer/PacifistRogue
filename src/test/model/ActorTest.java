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

package test.model;

import main.model.Player;
import main.model.Turn;
import main.model.World;
import main.utilities.TurnType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import test.stubs.ActorStub;
import test.stubs.ScreenRendererStub;
import main.utilities.Loc;

import java.awt.Color;
import java.util.Queue;

public class ActorTest
{
    private static World _world;
    private final Loc _startLoc = new Loc(8, 1);
    private ActorStub _actor;

    @Before
    public void setUp() throws Exception
    {
        _actor = new ActorStub(_world, _startLoc, Color.white, '$');
        _world = new World(new ScreenRendererStub());
        _world.addEntity(_actor);
    }

    @Test
    public void constructor() throws Exception
    {
        Loc actual = _actor.getLoc();
        Assert.assertEquals(Color.white, _actor.getColor());
        Assert.assertEquals('$', _actor.getSymbol());
        Assert.assertEquals(_startLoc, actual);
        Assert.assertNotSame(_startLoc, actual);
        Assert.assertTrue(_actor.getActionQueue().size() == 0);
    }

    @Test
    public void queueTurn() throws Exception
    {
        Turn expected = new Turn(TurnType.Move);
        _actor.queueTurn(expected);

        Queue<Turn> actionQueue = _actor.getActionQueue();
        Assert.assertTrue(actionQueue.size() == 1);
        Assert.assertSame(actionQueue.peek(), expected);
    }

    @Test
    public void takeTurnOneStep() throws Exception
    {
        Loc expectedLoc = _startLoc.clone();
        expectedLoc.y += 1;

        _actor.queueTurn(new Turn(TurnType.Move, expectedLoc));
        _actor.takeTurn();

        Assert.assertEquals(expectedLoc, _actor.getLoc());
        Assert.assertTrue(_actor.getActionQueue().size() == 0);
    }

    @Test
    public void takeTurnMultiStep() throws Exception
    {
        Loc expectedLoc = _startLoc.clone();
        expectedLoc.y += 1;

        _actor.queueTurn(new Turn(2, TurnType.Move, expectedLoc));
        _actor.takeTurn();

        Assert.assertEquals(_startLoc, _actor.getLoc());
        Assert.assertTrue(_actor.getActionQueue().size() == 1);

        _actor.takeTurn();

        Assert.assertEquals(expectedLoc, _actor.getLoc());
        Assert.assertTrue(_actor.getActionQueue().size() == 0);
    }
    @Test
    public void takeTurnIdle() throws Exception
    {
        // just verify no errors when idle
        _actor.takeTurn();
    }

    @Test
    public void pickUp() throws Exception
    {
    }

    @Test
    public void move() throws Exception
    {
    }

    @Test
    public void use() throws Exception
    {
    }

    @Test
    public void useHeldItem() throws Exception
    {
    }

    @Test
    public void getLoc() throws Exception
    {
    }

    @Test
    public void isIdle() throws Exception
    {
    }
}