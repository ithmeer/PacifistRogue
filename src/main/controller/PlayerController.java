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

package main.controller;

import main.interfaces.ScreenRenderer;
import main.model.Player;
import main.model.Turn;
import main.model.World;
import main.utilities.Direction;
import main.utilities.Loc;
import main.utilities.TurnType;

import java.awt.KeyEventDispatcher;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * The player main.controller class.
 * Created by Ithmeer on 4/19/2016.
 */
public class PlayerController implements KeyEventDispatcher, MouseListener
{
    private final World _world;
    private final Player _player;
    private final ExecutorService _executor;
    private final Semaphore _worldGate = new Semaphore(0);
    private volatile boolean _isInterrupted;

    public PlayerController(ScreenRenderer renderer)
    {
        _executor = Executors.newCachedThreadPool();
        _world = new World(renderer);
        _player = new Player(_world, new Loc(5, 5));
        _world.addEntity(_player);
        _executor.submit(this::worldRunner);
    }

    /**
     * Main loop.
     */
    private void worldRunner()
    {
        _world.draw();

        while (!_isInterrupted)
        {
            try
            {
                _worldGate.acquire();
            }
            catch (InterruptedException e)
            {
                // should never happen
                throw new RuntimeException(e);
            }

            _world.tick();
            _world.draw();
        }
    }

    /**
     * Handle key events
     * @param e the key event
     * @return true if we handled the event
     */
    @Override
    public boolean dispatchKeyEvent(KeyEvent e)
    {
        boolean didAct = false;

        if (_player.isIdle() && e.getID() == KeyEvent.KEY_PRESSED)
        {
            didAct = true;

            switch (e.getKeyCode())
            {
                case KeyEvent.VK_A:
                case KeyEvent.VK_D:
                case KeyEvent.VK_W:
                case KeyEvent.VK_S:
                case KeyEvent.VK_LEFT:
                case KeyEvent.VK_RIGHT:
                case KeyEvent.VK_UP:
                case KeyEvent.VK_DOWN:
                    _player.queueTurn(new Turn(
                            TurnType.Move,
                            _player.getLoc().next(Direction.keyToDirection(e.getKeyCode()))));
                    break;
                case KeyEvent.VK_E:
                    _player.queueTurn(new Turn(TurnType.PickUp));
                    break;
                default:
                    didAct = false;
                    break;
            }
        }

        if (didAct)
        {
            _worldGate.release();
        }

        return didAct;
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        if (_player.isIdle())
        {
        }
    }

    @Override
    public void mousePressed(MouseEvent e)
    {
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
    }

    @Override
    public void mouseEntered(MouseEvent e)
    {
    }

    @Override
    public void mouseExited(MouseEvent e)
    {
    }
}
