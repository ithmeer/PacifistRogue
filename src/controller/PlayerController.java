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

package controller;

import interfaces.ScreenRenderer;
import model.Player;
import model.Turn;
import model.World;
import utilities.Direction;
import utilities.Loc;
import utilities.MoveType;

import java.awt.KeyEventDispatcher;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * The player controller class.
 * Created by Ithmeer on 4/19/2016.
 */
public class PlayerController implements KeyEventDispatcher, MouseListener
{
    private final World _world;
    private final Player _player;
    private final ExecutorService _executor;

    public PlayerController(ScreenRenderer renderer)
    {
        _executor = Executors.newCachedThreadPool();
        _world = new World(renderer);
        _player = new Player(_world, new Loc(5, 5));
        _world.addEntity(_player);
        _executor.submit(_world);
    }

    /**
     * Handle key events
     * @param e the key event
     * @return true if we handled the event
     */
    @Override
    public boolean dispatchKeyEvent(KeyEvent e)
    {
        if (_player.isIdle() && e.getID() == KeyEvent.KEY_PRESSED)
        {
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
                    _player.queueTurn(new Turn(MoveType.Move,
                                      _player.getLoc().next(
                                              Direction.keyToDirection(e.getKeyCode()))));
                    break;
                case KeyEvent.VK_E:
                    _player.queueTurn(new Turn(MoveType.PickUp));
                    break;
            }

            return true;
        }

        return false;
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
