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

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.util.HashMap;

/**
 * Store the collection of main.items.
 * Created by Ithmeer on 4/23/2016.
 */
@XmlRootElement
public class ItemDatabase
{
    @XmlElement(name= "items")
    private Items _items;

    private static ItemDatabase _instance;
    private static HashMap<String, BaseItem> _map = new HashMap<>();

    private ItemDatabase()
    {
        // disallow instantiation of ItemDatabase outside this class
    }

    public static ItemDatabase getInstance()
    {
        if (_instance == null)
        {
            try
            {
                JAXBContext context = JAXBContext.newInstance(ItemDatabase.class);
                Unmarshaller depickler = context.createUnmarshaller();
                _instance = (ItemDatabase) depickler.unmarshal(new File("resources/itemdb"));

                for (Item item : _instance._items.getItems())
                {
                    BaseItem compItem = new BaseItem();
                    try
                    {
                        String itemName = item.getName();
                        String capsName = itemName.substring(0, 1).toUpperCase() + itemName.substring(1);
                        Class cls = Class.forName(String.format("main.items.%s", capsName));
                        compItem = (BaseItem) cls.newInstance();
                    }
                    catch (Exception e)
                    {
                    }
                    finally
                    {
                        compItem.transform(item);
                    }
                    _map.put(item.getName(), compItem);
                }
            }
            catch (JAXBException e)
            {
                e.printStackTrace();
            }
        }

        return _instance;
    }

    public BaseItem getItem(String name)
    {
        return _map.get(name).clone();
    }
}
