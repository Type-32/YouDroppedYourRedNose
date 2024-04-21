package cn.crtlprototypestudios.youdroppedyourrednose.items.discs;

import cn.crtlprototypestudios.youdroppedyourrednose.Main;
import cn.crtlprototypestudios.youdroppedyourrednose.items.ModItems;
import cn.crtlprototypestudios.youdroppedyourrednose.script.classes.ItemRecordBase;
import cn.crtlprototypestudios.youdroppedyourrednose.script.interfaces.IHasModel;
import cn.crtlprototypestudios.youdroppedyourrednose.util.handlers.SoundHandler;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemRecord;
import net.minecraftforge.client.model.ModelLoader;

public class RickRollDisc extends ItemRecordBase {
    public RickRollDisc() {
        super("surprise_disc", SoundHandler.RECORD_RICKROLL, CreativeTabs.MISC);
    }
}
