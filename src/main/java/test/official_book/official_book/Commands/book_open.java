package test.official_book.official_book.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

import static test.official_book.official_book.Official_book.plugin;

public class book_open implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player e = (Player) sender;
        if (args.length == 0) {
            List<String> book_titles = plugin.getConfig().getStringList("books.names");
            int gyousuu = book_titles.size() / 9 + 1;
            Inventory inv = Bukkit.createInventory(null, gyousuu * 9, "[BookViewer]");
            int amari = gyousuu * 9 - book_titles.size();
            for (int i = 0; i < book_titles.size(); i++) {
                inv.addItem(createGuiItem(Material.BOOK, book_titles.get(i), ""));
            }
            for (int i = 0; i < amari; i++) {
                inv.setItem(book_titles.size() + i, createGuiItem(Material.GRAY_STAINED_GLASS, "", ""));
            }
            e.openInventory(inv);
            return true;
        } else {
            if (plugin.getConfig().getStringList("books.names").contains(args[0])) {
                ItemStack book = new ItemStack(Material.WRITTEN_BOOK, 1);
                BookMeta book_meta = (BookMeta) book.getItemMeta();
                book_meta.setTitle(args[0]);
                book_meta.setAuthor(ChatColor.GRAY + "Man10");
                List<String> naiyou = plugin.getConfig().getStringList("books." + args[0]);
                for (int i = 0; i < naiyou.size(); i++) {
                    book_meta.addPage(naiyou.get(i));
                }
                book.setItemMeta(book_meta);
                e.openBook(book);
                return true;

            } else {
                sender.sendMessage("[BookViewer]" + ChatColor.RED + "その名前の本はありません!");
            }
        }
        return true;
    }

    protected ItemStack createGuiItem(final Material material, final String name, final String... lore) {
        final ItemStack item = new ItemStack(material, 1);
        final ItemMeta meta = item.getItemMeta();

        // Set the name of the item
        meta.setDisplayName(name);

        // Set the lore of the item
        meta.setLore(Arrays.asList(lore));

        item.setItemMeta(meta);

        return item;
    }


}
