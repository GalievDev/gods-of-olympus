package dev.galiev.gofo.mixin;

import dev.galiev.gofo.utils.GodsData;
import net.minecraft.network.ClientConnection;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ConnectedClientData;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerManager.class)
public class PlayerManagerMixin {
    @Inject(at = @At(value = "TAIL"), method = "onPlayerConnect")
    private void onPlayerJoin(ClientConnection connection, ServerPlayerEntity player, ConnectedClientData clientData, CallbackInfo ci) {
        if (player.getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(Stats.LEAVE_GAME)) < 1) {
            GodsData.addRepPoseidon(player, (short) 7);
            GodsData.addRepZeus(player, (short) 7);
            player.sendMessage(Text.literal("Welcome to Gods Of Olympus.").formatted(Formatting.GOLD).formatted(Formatting.BOLD));
            player.sendMessage(Text.literal("Here you will find Zeus and Poseidon, gods of the sky and sea. When you first join, you will have 7 reputation points from the two gods, with a maximum of 15."));
            player.sendMessage(Text.literal("If you become a raider and have less than 5 reputation points, you will face the wrath of Zeus.").formatted(Formatting.YELLOW));
            player.sendMessage(Text.literal("To gain respect from Zeus, you must either kill raiders or sacrifice a sheep in the windswept hills. If you have a reputation of more than 12, you will receive his thanks. Additionally, you can find his statue in the hills and obtain his lightning by clicking on the statue with a netherite sword in your hand (you should have maximum respect from Zeus)."));
            player.sendMessage(Text.literal("If you want to witness Poseidon's wrath, you should avoid killing dolphins, turtles, or horses (as he likes them). His wrath will be evident when your reputation is less than 5.").formatted(Formatting.AQUA));
            player.sendMessage(Text.literal("To gain Poseidon's respect, eliminate any zombies in the world. Similar to Zeus, Poseidon can also be worshipped by finding his statue at the bottom of the sea. To obtain Poseidon's trident, simply click on it while holding your own trident (and ensure you have maximum respect from Poseidon)."));
        }
    }
}
