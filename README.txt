+-----------------------+
| Sude                  |
+-----+-----------------+
+=====+ Bukkit Plugin   |
+-----+-----------------+
+=====+ By Nic Kiely    |
+-----+-----------------+
+=====| Release         |
+-----+-----------------+


==//=====\\==
 || About ||
==\\=====//==

Sude is a Bukkit plugin for managing supply/demand lists on a server to supplement player-player economies. It allows players to post items or services they'd like to buy or sell, with preferred prices and amounts. Players can also search for the best deals. Sude also works with a permissions plugin, but it is not required.

Sude can list any single worded item, place, service, etc, so it is not just limited to in-game items. For example, towny owners can list prices for plots, or clan leaders can list jobs.


==//=========\\==
 || For Users ||
==\\=========//==

Sude allows you to post entries for items to buy or sell at specified prices, as well as see what other players are selling.

For a crash course in using the plugin commands, enter "/sude" and "/sude-cmd" for help. Any command's documentation can be accessed "/<command name> ?", assuming the command has documentation. The only commands that lack documentation are the help commands, since it's their function anyways.

There are three groups of commands, listing commands, editing commands, and an admin command. Each group has its own permission node, and it is up to the server owner to manage the permissions. All non-helper commands have a long form and a short form. All commands dealing with buying have a sister command dealing with selling, and vice versa. For simplicity's sake, only buying commands are covered in depth, since the selling commands are virtually identical. Just replace "buying" with "selling" to use the selling command. All long form commands are prefixed with "/sude-", and all short form commands are prefixed with "/sd-".

In this document, commands are listed as:
>>>| /sude-<long command name> [arguments]
>| /sd-<short command name> …

Any arguments in square brackets [ ] are optional. Any arguments in angle brackets < > are mandatory.



*Please keep in mind that a server may have rules about what items may or may not be added, what player aliases can be created, etc, to keep the lists ordered and predictable.*



+------------------+
| Listing Commands |
+------------------+

The listing commands are the most basic and permissive, they show what other players on the server wish to sell or buy, as well as a gist of the price of items or services.


>>>| /sude-buying [item]
>| /sd-b ...

>>>| /sude-selling [item]
>| /sd-s ...


The buying command by itself lists all the items on the server that players want to buy. If an item is specified, the command lists all of the players selling the item, and for what price, how much, etc. The list will be in the form of:
<item name>, <buyer name>, <price per bundle>, <bundle size>, [max number of bundles to buy]

Note that the price is per bundle of item (default being 1). This is so player can list prices of stacks of items. For example, an entry could be:
>>> dirt, mark007, 1, 64
This says that mark007 is willing to buy stacks of 64 dirt blocks at 1 coin per stack. Or 1 dollar. Or whatever the currency the server economy is using. If mark007 only wanted to buy up to 100 stacks of dirt, the entry would be
>>> dirt mark007, 1, 64, 100.

Prices for the buy list are sorted in descending order. In other words, prices are sorted from highest per item to lowest per item, so you can see the highest buyer first. The "/sude-selling" command is a little bit different, it lists in ascending order so you can see the cheapest seller.


>>>| /sude-player-buying [player]
>| /sude-pb ...

The player buying command lists items that a particular player is buying. If the player name is not specified, you are assumed to be the player, so it shows what you are buying.



+------------------+
| Editing Commands |
+------------------+

Editing commands allow player to edit their own Sude data, such as posting to the sell list. They are the next permission level up, and are only for ops by default.

>>>| /sude-add-buying <item> <price per bundle> [bundle size] [max num of bundles]
>| /sd-ab ...

The add-buying command lets players add an entry to the list of an item/service that they would like to buy. A basic command may look like:
/sd-ab coal 2

Which is to say that the player is now on the list for buying coal at 2 coins per piece. If bundle size is not specified (as in the previous example), it is assumed to be 1 by default. Let's say a player wants to sell wheat at 1 coin per 3 pieces, then they should use this:
/sd-as wheat 1 3

If a player only wants to buy/sell a maximum number of a specific item, the last argument is the maximum number of bundles. Say a town manager wants to list 3 plots for sale at 4,000 coins per plot
/sude-add-selling plot 4000 1 3

If an entry by a player already exists, it is updated with the new data. So the add command is actually a full-blown editing command.



>>>| /sude-clear-buying [item]
>| /sd-cb ...

The clear-buying clears an entry. If an item is specified, the player's entry for that item is removed. If nothing is specified, all of the player's entries are removed. It's like Unix's rm command, in that it's always amusing to watch users delete their much beloved data with a careless command >:) .


+---------------+
| Admin Command |
+---------------+

As of now, there is only one admin command, and that is to let the admin act as any other player (existent or not). This has two purposes (and may be split into two commands in the future, but no promises), to create and modify non-player entities (such as town shops) as well as to modify real player's data. Only ops have admin powers by default.


>>>| /sude-sudo [player name]
>| /sd-u

The sudo command treats you as if you were named [player name]. The player name doesn't have to be an actual player. If no player name is given, the command merely tells who you are sudo-ed as. To get back to your normal status, either sudo to your name or use sudo to # (ie "/sude-sudo #"). Note that console is automatically sudo-ed as #console, so it is always treated as an op player.

For example, a shop manager may update the price for gold to 12 coins per ingot by doing this:
/sd-u gold_shop
/sd-as gold 12
/sd-u #

Or an admin may remove items with:
/sd-u mark007
/sd-cb stupid_item
/sd-cb another_stupid_item
/sd-u #




==//=================\\==
 || For Server Owners ||
==\\=================//==


+--------------+
| Installation |
+--------------+

Sude should be ready to install and run out of the box. Installation is simple, just put the distribution Sude jar (in the /dist folder) in the bucket plugins folder. No other plugins are needed, unless the server admin want to use the permissions plugin. In that case, having permissions plugin installed is probably a good idea.


+---------------+
| Configuration |
+---------------+

The two primarily configurable things in the plugin are the permissions drivers and the runtime documentation, both specified in the config.yml file in the /plugins/Sude data folder. If something goes terribly wrong in the configurations file, it can be deleted and the Sude plugin will generate a fresh config the next time it boots up.

To change runtime documentation or permissions, edit the description text or permissions description in the config file. Note that the internal permission drivers, which are used by default, do not recognize permission nodes other than 'sude.list', 'sude.edit', and 'sude.admin'. On the other hand, the plugin shouldn't care what custom permissions you set for the commands if you are using the Permissions plugin driver.


+--------------------+
| Permission Drivers |
+--------------------+

In order to have some flexibility ease with managing different permission systems, such as the usual permissions plugin or possibly bukkit's permissions (not coded in, no promises), the plugin has a permissions driver layer. There are two main classes of permission drivers, internal and external. External means the driver is hooked into another plugin or bucket, while Internal means the driver is driver implements it's own logic for handling permissions. The default permission driver is 'internal-strict'. Permission drivers can be specified in the config file with a "permission_driver: '<driver name>' ". If you want to hook into a permissions plugin, put in a "permission_driver: 'external-permissions' " line.

Also, the console has all privileges.




Here are the current permission drivers:

>>>| internal-strict
Strict is the default driver, and is a safe bet to start off with in a medium traffic server. All members can have listing privileges, but only ops may have editing or admin privileges.


>>>| internal-free
Free is the whatever-goes driver, anybody can do anything. Only recommended for small private servers since anybody can do anything with the buy/sell lists.


>>>| internal-lax
Lax is the easy going driver, okay for small server. Everybody has listing and editing powers, but only server ops have admin powers.


>>>| external-permissions
Permissions driver hooks into a permissions plugin.


The permission nodes are:
>| sude.list
>| sude.edit
>| sude.admin

See commands listed in the "For Users" for specifics on the commands, but each permission maps to each command group. If permissions plugin is being used, the permissions in Sude's config file can be changed for fine tuning.


+------------+
| Management |
+------------+

Right now, it's highly advisable for server admin to have some rules and standards about adding item entries to enforce order. For instance, there may be entries for woodaxe, woodaxes, wood_axe, wood_axes, wooden_axe etc and that's a mess. However, if a server has rules and standards on naming, that will make everybody's life much easier. For example, if the server has the standard naming where a diamond axes is diamond_axe, then it is much easier to look all of the specified items up.
