package com.github.qq44920040.mc;

import com.github.qq44920040.mc.Entity.LevelGift;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class levelgift extends JavaPlugin implements Listener {
    private List<LevelGift> LevelGiftinfo = new ArrayList<>();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("levelgift")&&sender.isOp()){
            LevelGiftinfo.clear();
            PlguinReload();
            sender.sendMessage("§e§l重载插件成功");
        }
        return super.onCommand(sender, command, label, args);
    }

    @EventHandler
    public void PlayerUseItem(PlayerInteractEvent event){
        Action action = event.getAction();
        if (action== Action.RIGHT_CLICK_AIR||action==Action.RIGHT_CLICK_BLOCK) {
            Player player = event.getPlayer();
            ItemStack itemInHand = player.getItemInHand();
            if (itemInHand != null) {
                if (itemInHand.hasItemMeta()) {
                    ItemMeta itemMeta = itemInHand.getItemMeta();
                    String displayName = itemMeta.getDisplayName();
                    int level = player.getLevel();
                    for (LevelGift levelGift:LevelGiftinfo){
                        if (displayName.contains(levelGift.getItemName())){
                            if (levelGift.getMustLevel()<=level){
                                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),levelGift.getCmd().replace("{player}",player.getName()));
                                player.sendMessage(levelGift.getMsg());
                                int amount = itemInHand.getAmount();
                                if (amount==1){
                                    player.setItemInHand(null);
                                }else {
                                    itemInHand.setAmount(amount-1);
                                    player.setItemInHand(itemInHand);
                                }
                            }else {
                                player.sendMessage(levelGift.getDisPass());
                                return;
                            }
                        }
                    }
                }
            }
        }

    }


    @Override
    public void onEnable() {
        if (!getDataFolder().exists()) {
            getDataFolder().mkdir();
        }
        File file = new File(getDataFolder(),"config.yml");
        if (!file.exists()){
            saveDefaultConfig();
        }
        Bukkit.getServer().getPluginManager().registerEvents(this,this);
        PlguinReload();
        super.onEnable();
    }

    private void PlguinReload(){
        reloadConfig();
        Set<String> luckboxpools = getConfig().getConfigurationSection("LevelGift").getKeys(false);
        for (String temp:luckboxpools) {
            String Cmd = getConfig().getString("LevelGift." + temp + ".Cmd");
            String Msg = getConfig().getString("LevelGift." + temp + ".Msg");
            String  DisPass = getConfig().getString("DisPass");
            int MustLevel = getConfig().getInt("LevelGift." + temp + ".MustLevel");
            String  ItemName = getConfig().getString("LevelGift." + temp + ".ItemName");
            LevelGiftinfo.add(new LevelGift(Cmd,MustLevel,Msg,DisPass,ItemName));
        }

    }

}
